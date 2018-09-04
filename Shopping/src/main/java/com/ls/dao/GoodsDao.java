package com.ls.dao;

import com.ls.dto.paymentDetail;
import com.ls.dto.shoppingCartDetail;
import com.ls.entity.Goods;
import com.ls.entity.goodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {
    List<Goods> getPageList(@Param("begin") int begin, @Param("pageNum") int pageNum);

    List<Goods> getPageListByGoodsType(@Param("begin") int begin, @Param("pageNum") int pageNum, @Param("typeName") String typeName);

    Integer getGoodsTotalNum();

    Integer getGoodsTotalNumByGoodsType(@Param("typeName") String typeName);

    Integer getShoppingCartTotalNum(Long Id);

    int addGoods(@Param("goods") Goods goods);

    int addMd5Id(@Param("goods") Goods goods);

    int deleteGoodsById(@Param("md5Id") String md5Id);

    List<Goods> insertRepeat(@Param("name") String name);

    List<shoppingCartDetail> showShoppingCart(@Param("Id") Long Id, @Param("begin") Integer begin, @Param("pageNum") Integer pageNum);

    Integer addShoppingCart(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer addShoppingCartNum(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer queryShoppingNum(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer queryGoodsNum(@Param("Id") Long Id);

    Integer reduceGoodsNumber(@Param("Id") Long Id);

    Long getMd5Id(@Param("md5Id") String md5Id);

    Integer deleteOne(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer deleteAll(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer queryShoppingGoodsNum(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer addPayment(@Param("number") Integer number, @Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer deleteShoppingCart(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    List<paymentDetail> queryPayment(@Param("Id") Long Id, @Param("begin") Integer begin, @Param("pageNum") Integer pageNum);

    Integer queryShoppingCartNum(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    Integer addOneGoodsNum(@Param("Id") Long Id);

    Integer addAllGoodsNum(@Param("Id") Long Id, @Param("number") Integer number);

    Goods queryGoodsDetail(@Param("md5Id") String md5Id);

    Integer updateGoodsDetail(@Param("goods") Goods goods);

    Integer updateGoodsDetailWithoutImg(@Param("goods") Goods goods);

    List<goodsType> queryGoodsType();
}
