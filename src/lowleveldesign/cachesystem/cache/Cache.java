package lowleveldesign.cachesystem.cache;

import lombok.Getter;
import lombok.Setter;
import lowleveldesign.cachesystem.CacheEvent;
import lowleveldesign.cachesystem.cacheevictionpolicies.CacheEvictionPolicy;
import lowleveldesign.cachesystem.cacheevictionpolicies.LRUCacheEvictionPolicy;
import lowleveldesign.cachesystem.cachestorage.CacheStorage;
import lowleveldesign.cachesystem.cachestorage.FileCacheStorage;
import lowleveldesign.cachesystem.config.CacheConfig;

import java.util.List;

@Getter
@Setter
public class Cache<Key, Value> {

    private int cacheSize;
    private CacheStorage<Key, Value> cacheStorage;
    private CacheEvictionPolicy<Key> cacheEvictionPolicy;
    private static CacheConfig config;

    private static volatile Cache INSTANCE = null;

    public static <Key, Value> Cache getInstance() {
        if(INSTANCE == null) {
            synchronized (Cache.class) {
                if(INSTANCE == null) {
                    CacheConfig config = new CacheConfig();
                    INSTANCE = new Cache<Key, Value>(config.getCacheSize(),
                            new FileCacheStorage<>(),
                            new LRUCacheEvictionPolicy<>());
                }
            }
        }

        return INSTANCE;
    }

    private Cache(int cacheSize, CacheStorage<Key, Value> cacheStorage, CacheEvictionPolicy<Key> cacheEvictionPolicy) {
        this.cacheSize = cacheSize;
        this.cacheStorage = cacheStorage;
        this.cacheEvictionPolicy = cacheEvictionPolicy;
    }

    public void put(Key key, Value value) {
        if (cacheStorage.get(key) == null) {
            if (cacheStorage.currentCacheSize() == cacheSize) {
                Key evictedKey = cacheEvictionPolicy.evict();
                cacheStorage.remove(evictedKey);
            }
        }
        cacheStorage.add(key, value);
        cacheEvictionPolicy.accessKey(key);
    }

    public Value get(Key key) {
        Value value = cacheStorage.get(key);
        if(value != null) {
            cacheEvictionPolicy.accessKey(key);
        }
        return value;
    }

    public void replayEvents() {
        List<CacheEvent<Key, Value>> events = cacheStorage.getEvents();
        for(CacheEvent<Key, Value> event: events) {
            switch (event.getAction()) {
                case ADD -> put(event.getKey(), event.getValue());
                case GET -> get(event.getKey());
            }
        }
    }
}
