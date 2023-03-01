package by.zastr.repository.util;

public interface MyCache<T> {

    void put(int id, T val);
    T get(Integer id);
}
