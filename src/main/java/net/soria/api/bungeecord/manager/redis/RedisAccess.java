package net.soria.api.bungeecord.manager.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

public class RedisAccess {

    public static RedisAccess instance;
    private RedissonClient redissonClient;

    public RedisAccess(RedisCredentials redisCredentials){
        instance = this;
        this.redissonClient = initRedisson(redisCredentials);
    }

    public static void init(){
        new RedisAccess(new RedisCredentials("127.0.0.1", "", 6379));
    }

    public static void close(){
        RedisAccess.instance.getRedissonClient().shutdown();
    }

    public RedissonClient initRedisson(RedisCredentials redisCredentials){

        final Config config = new Config();

        config.setCodec(new JsonJacksonCodec());
        config.setThreads(2);
        config.setNettyThreads(2);
        config.useSingleServer()
                .setAddress(redisCredentials.toRedisURL())
                .setPassword(redisCredentials.getPassword())
                .setDatabase(3)
                .setClientName(redisCredentials.getClientName());

        return Redisson.create(config);
    }

    public RedissonClient getRedissonClient(){
        return redissonClient;
    }

}
