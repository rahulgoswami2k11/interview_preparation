package lowleveldesign.cachesystem;

import lowleveldesign.cachesystem.cache.Cache;

public class CacheApplication {
    public static void main(String[] args) {
        Cache<Integer, Integer> cache = Cache.getInstance();
        //put(1 ,1) , put(2 ,2) , get(1) , put(3 ,3) , get(2) , put(4 ,4) , get(1) , get(3) , get(4)]
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));
//        cache.put(3, 3);
//        System.out.println(cache.get(2));
//        cache.put(4, 4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
//        cache.put(5, 5);
//        System.out.println(cache.get(5));
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(5));
//        System.out.println(cache.get(4));
//
        cache.replayEvents();
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));


    }
}
