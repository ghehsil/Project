package com.ls.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ls.entity.Goods;
import com.ls.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class GoodsRedisDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    //序列化
    private RuntimeSchema<Goods> schema = RuntimeSchema.createFrom(Goods.class);

    public GoodsRedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Goods getGoods(String md5Id) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "goods:" + md5Id;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Goods goods = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, goods, schema);
                    return goods;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putGoods(Goods goods) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "goods:" + goods.getMd5Id();
                //缓存器
                byte[] bytes = ProtostuffIOUtil.toByteArray(goods, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;//one hour
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
