package com.ls.dao;

import com.ls.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class GoodsDaoTest {

    @Resource
    private GoodsDao goodsDao;

    /*@Test
    public void queryAll() throws Exception {
        List<Goods> goods=goodsDao.queryAll();
        System.out.println(goods.get(0).getName());
    }*/

    /*@Test
    public void addGoods(){
        Goods goods=new Goods("鸡蛋",1.5,50,"egg");
        System.out.println(goodsDao.addGoods(goods));
    }*/

}