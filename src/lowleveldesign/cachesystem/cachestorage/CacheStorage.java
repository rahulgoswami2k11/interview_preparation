package lowleveldesign.cachesystem.cachestorage;

import lowleveldesign.cachesystem.CacheEvent;

import java.util.List;

public interface CacheStorage<Key, Value> {

    void add(Key key, Value value);

    Value get(Key key);

    void remove(Key key);

    int currentCacheSize();

    List<CacheEvent<Key, Value>> getEvents();
}
