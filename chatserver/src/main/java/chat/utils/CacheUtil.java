package chat.utils;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * 缓存工具类
 */
public class CacheUtil {
    //缓存数据存储
    private static HashMap<String,Channel> cacheSC = new HashMap <>();
    private static HashMap<Channel,String> cacheCS = new HashMap <>();
    private static HashMap<Integer,Channel> cache = new HashMap<>();
    private static HashMap<Channel,Integer> caches = new HashMap<>();

    //添加缓存
    public static void put(String name, Channel channel) {
        cacheSC.put(name,channel);
        cacheCS.put(channel,name);
    }
    //添加缓存
    public static void put(Integer id,Channel channel){
        caches.put(channel,id);
        cache.put(id,channel);
    }

    //删除缓存
    public static void del(String name) {
        Channel channel = cacheSC.get(name);
        if (channel != null) {
            cacheSC.remove(name);
            cacheCS.remove(channel);
        }
    }

    public static void del(Channel channel) {
        String s = cacheCS.get(channel);
        if (s!=null) {
            cacheCS.remove(channel);
            cacheSC.remove(s);
        }
    }


    //获取数据
    public static Channel get(String name) {
        return cacheSC.get(name);
    }

    public static Channel get(Integer id){
        return cache.get(id);
    }
}
