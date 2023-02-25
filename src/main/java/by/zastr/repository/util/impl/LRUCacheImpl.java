package by.zastr.repository.util.impl;

import by.zastr.repository.util.MyCache;

import java.util.*;

public class LRUCacheImpl<T> implements MyCache<T> {
    private final HashMap<Integer, T> data = new HashMap<>();
    private final LinkedList<Integer> order = new LinkedList<>();
    private final int capacity;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(int id, T val) {

        if (order.size() >= capacity) {
            int keyRemoved = order.removeLast();
            data.remove(keyRemoved);
        }
        order.addFirst(id);
        data.put(id, val);

    }

    @Override
    public T get(int id) {
        T result = data.get(id);
        if(result != null) {
            order.remove(id);
            order.addFirst(id);
        }
        return result;
    }
}
