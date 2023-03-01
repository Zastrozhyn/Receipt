package by.zastr.repository.util.impl;

import by.zastr.repository.util.MyCache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCacheImpl<T> implements MyCache<T> {
    private static final Integer ONE = 1;
    private final HashMap<Integer, T> values = new HashMap<>();
    private final HashMap<Integer, Integer> counts = new HashMap<>();
    private final HashMap<Integer, LinkedHashSet<Integer>> lists = new HashMap<>();
    private final int capacity;
    private int minFrequent = -1;

    public LFUCacheImpl(int capacity) {
        this.capacity = capacity;
        lists.put(ONE, new LinkedHashSet<>());
    }

    @Override
    public T get(Integer key) {
        if (!values.containsKey(key))
            return null;
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);

        if (count == minFrequent && lists.get(count).size() == 0)
            minFrequent++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return values.get(key);
    }

    @Override
    public void put(int key, T value) {
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            int idToRemove = lists.get(minFrequent).iterator().next();
            System.out.println(lists.get(minFrequent));
            lists.get(minFrequent).remove(idToRemove);
            values.remove(idToRemove);
            counts.remove(idToRemove);
        }
        values.put(key, value);
        counts.put(key, 1);
        minFrequent = 1;
        lists.get(ONE).add(key);
    }

    public int getCapacity(){
        return capacity;
    }
}
