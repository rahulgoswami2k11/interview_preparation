package lowleveldesign.cachesystem.cacheevictionpolicies;

public interface CacheEvictionPolicy<Key> {

    void accessKey(Key key);

    Key evict();

}
