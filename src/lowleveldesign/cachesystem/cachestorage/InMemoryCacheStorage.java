package lowleveldesign.cachesystem.cachestorage;

import lombok.Getter;
import lowleveldesign.cachesystem.CacheEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class InMemoryCacheStorage<Key, Value> implements CacheStorage<Key, Value> {

    private final Map<Key, Value> cacheMap;

    public InMemoryCacheStorage() {
        this.cacheMap = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) {
        cacheMap.put(key, value);
    }

    @Override
    public Value get(Key key) {
        return cacheMap.getOrDefault(key, null);
    }

    @Override
    public void remove(Key key) {
        cacheMap.remove(key);
    }

    @Override
    public int currentCacheSize() {
        return cacheMap.size();
    }

    @Override
    public List<CacheEvent<Key, Value>> getEvents() {
        return new ArrayList<>();
    }
}
