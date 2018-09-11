package com.ls.service.Impl;

import com.ls.dao.GoodsDao;
import com.ls.dao.cache.GoodsRedisDao;
import com.ls.dto.paymentDetail;
import com.ls.dto.shoppingCartDetail;
import com.ls.entity.Goods;
import com.ls.entity.goodsType;
import com.ls.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsRedisDao goodsRedisDao;

    //混淆md5
    private final String slat = "sa#d$23*5a%s^da5s4da57+a*sf-/a8+f9";

    //md5加密
    private String getMd5(Long Id) {
        String base = Id + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public List<Goods> getPageList(Integer showNum, Integer pageNum) {
        int begin = (showNum - 1) * pageNum;
        return goodsDao.getPageList(begin, pageNum);
    }

    public List<Goods> getPageListByGoodsType(Integer showNum, Integer pageNum, String typeName) {
        int begin = (showNum - 1) * pageNum;
        return goodsDao.getPageListByGoodsType(begin, pageNum, typeName);
    }

    //得到goods的总数
    public Integer getGoodsTotalNum() {
        return goodsDao.getGoodsTotalNum();
    }

    public Integer getGoodsTotalNumByGoodsType(String typeName) {
        return goodsDao.getGoodsTotalNumByGoodsType(typeName);
    }

    //先插基本信息,再插md5加密的Id,事务不成功回滚。
    @Transactional
    public int insertGoods(Goods goods) {
        int temp = goodsDao.addGoods(goods);
        if (temp < 1) {
            return 0;
        }
        Goods md5Goods = goodsDao.insertRepeat(goods.getName()).get(0);
        md5Goods.setMd5Id(getMd5(md5Goods.getId()));
        if (goodsDao.addMd5Id(md5Goods) < 1) {
            return 0;
        }
        return temp;
    }

    public int deleteGoodsById(String md5Id) {
        return goodsDao.deleteGoodsById(md5Id);
    }

    //插入重复
    public List<Goods> insertRepeat(String name) {
        return goodsDao.insertRepeat(name);
    }

    public List<shoppingCartDetail> showShoppingCart(Long Id, Integer showNum, Integer pageNum) {
        int begin = (showNum - 1) * pageNum;
        return goodsDao.showShoppingCart(Id, begin, pageNum);
    }

    public Long getMd5Id(String md5Id) {
        return goodsDao.getMd5Id(md5Id);
    }

    //得到购物车的总数
    public Integer getShoppingCartTotalNum(Long Id) {
        return goodsDao.getShoppingCartTotalNum(Id);
    }

    @Transactional
    public Integer addShoppingCart(Long userId, Long goodsId) {
        if (goodsDao.queryGoodsNum(goodsId) < 1) {
            return 0;
        }
        if (goodsDao.queryShoppingNum(userId, goodsId) == null) {
            if (goodsDao.addShoppingCart(userId, goodsId) < 1 || goodsDao.reduceGoodsNumber(goodsId) < 1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (goodsDao.addShoppingCartNum(userId, goodsId) < 1 || goodsDao.reduceGoodsNumber(goodsId) < 1) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    @Transactional
    public Integer deleteOne(Long userId, Long goodsId) {
        if (goodsDao.queryShoppingGoodsNum(userId, goodsId) <= 1) {
            if (goodsDao.addOneGoodsNum(goodsId) < 1 || goodsDao.deleteAll(userId, goodsId) < 1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (goodsDao.addOneGoodsNum(goodsId) < 1 || goodsDao.deleteOne(userId, goodsId) < 1) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    @Transactional
    public Integer deleteAll(Long userId, Long goodsId) {
        if (goodsDao.addAllGoodsNum(goodsId, goodsDao.queryShoppingGoodsNum(userId, goodsId)) < 1 || goodsDao.deleteAll(userId, goodsId) < 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Transactional
    public Integer pay(Long userId, Long goodsId) {
        if (goodsDao.addPayment(goodsDao.queryShoppingCartNum(userId, goodsId), userId, goodsId) < 1 || goodsDao.deleteShoppingCart(userId, goodsId) < 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public List<paymentDetail> showPayment(Long Id, Integer showNum, Integer pageNum) {
        int begin = (showNum - 1) * pageNum;
        return goodsDao.queryPayment(Id, begin, pageNum);
    }

    @Transactional
    public Goods queryGoodsDetail(String md5Id) {
        Goods goods = goodsRedisDao.getGoods(md5Id);
        if (goods == null) {
            goods = goodsDao.queryGoodsDetail(md5Id);
            goodsRedisDao.putGoods(goods);
            return goods;
        }
        return goods;
    }

    public Integer updateGoodsDetail(Goods goods) {
        return goodsDao.updateGoodsDetail(goods);
    }

    public Integer updateGoodsDetailWithoutImg(Goods goods) {
        return goodsDao.updateGoodsDetailWithoutImg(goods);
    }

    public List<goodsType> queryGoodsType() {
        return goodsDao.queryGoodsType();
    }
}
