package by.zastr.service.service;

import by.zastr.repository.entity.Product;

import java.util.List;

public interface ProductService {
    Product find(int id);
    List<Product> findAll();
    boolean delete(int id);
}
