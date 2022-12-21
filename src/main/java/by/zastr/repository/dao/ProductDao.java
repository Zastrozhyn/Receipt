package by.zastr.repository.dao;

import by.zastr.repository.entity.Product;

import java.util.List;

public interface ProductDao {
    Product find(int id);
    List<Product> findAll();
    boolean delete(int id);
}
