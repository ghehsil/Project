package com.ls.service;

import com.ls.dto.paymentDetail;
import com.ls.dto.shoppingCartDetail;
import com.ls.entity.Goods;
import com.ls.entity.goodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsService {
    List<Goods> getPageList(Integer showNum, Integer pageNum);

    List<Goods> getPageListByGoodsType(Integer begin, Integer pageNum, String typeName);

    Integer getGoodsTotalNum();

    Integer getGoodsTotalNumByGoodsType(String typeName);

    Integer getShoppingCartTotalNum(Long Id);

    int insertGoods(Goods goods);

    int deleteGoodsById(String md5Id);

    List<Goods> insertRepeat(String name);

    //返回购物车组合细节
    List<shoppingCartDetail> showShoppingCart(Long Id, Integer showNum, Integer pageNum);

    Integer addShoppingCart(Long userId, Long goodsId);

    Long getMd5Id(String md5Id);

    Integer deleteOne(Long userId, Long goodsId);

    Integer deleteAll(Long userId, Long goodsId);

    Integer pay(Long userId, Long goodsId);

    List<paymentDetail> showPayment(Long Id, Integer begin, Integer pageNum);

    Goods queryGoodsDetail(String md5Id);

    Integer updateGoodsDetail(Goods goods);

    Integer updateGoodsDetailWithoutImg(Goods goods);

    List<goodsType> queryGoodsType();
}
