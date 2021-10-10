package com.gzq.algorithm.map;

import sun.misc.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    //定义缓存容量
    private int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    //访问数据get方法
    public int get(int key) {
        if (super.get(key)==null)return -1;
        return super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    //重写是否删除元素的方法
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheWithLinkedHashMap lruCache = new LRUCacheWithLinkedHashMap(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
