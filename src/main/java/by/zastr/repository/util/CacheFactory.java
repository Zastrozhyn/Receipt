package by.zastr.repository.util;

import by.zastr.repository.util.impl.LFUCacheImpl;
import by.zastr.repository.util.impl.LRUCacheImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheFactory<T> {

    @Value("${cache.type}")
    private String cacheType;
    @Value("${cache.capacity}")
    private int cacheCapacity;

    private MyCache<T> cache;

    private CacheFactory(){
    }

    @PostConstruct
    private void init(){
        if (cacheType.equals("LFU")){
            cache =  new LFUCacheImpl<>(cacheCapacity);
        } else {
            cache = new LRUCacheImpl<T>(cacheCapacity);
        }

    }

    public MyCache<T> cache(){
        return cache;
    }
}
