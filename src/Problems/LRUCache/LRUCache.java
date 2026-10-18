package Problems.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

//Linked Hash map best example

public class  LRUCache<K,V> extends LinkedHashMap<K,V>{

    public static void main(String[] args){

        LRUCache<String, Integer> lruCache= new LRUCache<>(5, 0.75F);
        lruCache.put("ABC",1);
        lruCache.put("A",2);
        lruCache.put("AB",3);
        lruCache.put("AC",4);
        lruCache.put("BC",5);
        lruCache.put("D",6);

        System.out.println(lruCache);
    }

    private final int initialCapacity;

    public LRUCache(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.initialCapacity = initialCapacity;
    }

    public boolean removeEldestEntry(Map.Entry<K, V> remove){
        return size() > initialCapacity;
    }


}