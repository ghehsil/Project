package com.ls.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ls.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class UserRedisDao {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    //序列化
    private RuntimeSchema<User> schema=RuntimeSchema.createFrom(User.class);

    public UserRedisDao(String ip, int port){
        jedisPool=new JedisPool(ip,port);
    }

    public User getUser(String userName){
        try {
            Jedis jedis=jedisPool.getResource();
            try {
                String key="user:"+userName;
                byte[] bytes=jedis.get(key.getBytes());
                if(bytes!=null){
                    User user=schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,user,schema);
                    return user;
                }
            }finally {
                jedis.close();
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putUser(User user){
        try {
            Jedis jedis=jedisPool.getResource();
            try {
                String key="user:"+user.getUserName();
                //缓存器
                byte[] bytes=ProtostuffIOUtil.toByteArray(user,schema,LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout=60*60;//one hour
                String result=jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
