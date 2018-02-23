package com.wutue.context;

import com.wutue.common.utils.SerializeUtils;
import com.wutue.redis.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisMessage implements MessageService {

    @Autowired
    JedisClient jedisClient;

    private String PREFIX = "MESSAGE_REDIS_";

    @Override
    public void set(String key,MessageInfo value) {
        jedisClient.set(SerializeUtils.serialize(PREFIX + key), SerializeUtils.serialize(value));
    }

    @Override
    public MessageInfo get(String key) {
        byte[] k = SerializeUtils.serialize(PREFIX + key);
        byte[] rawValue =jedisClient.get(k);
        jedisClient.del(k);
        return (MessageInfo) SerializeUtils.deserialize(rawValue);
    }
}
