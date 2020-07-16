package dataStructure.hash;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lijian
 * @description
 * @date 2020/6/18
 */
public class LRUCache<K,V> extends LinkedHashMap {

    private final int CACHE_SIZE;

    public LRUCache(int CACHE_SIZE) {
        super((int)Math.ceil(CACHE_SIZE/0.75)+1,0.75f,true);
        this.CACHE_SIZE = CACHE_SIZE;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>CACHE_SIZE;
    }
}
