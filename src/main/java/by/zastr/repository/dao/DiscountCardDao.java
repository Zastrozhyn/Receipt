package by.zastr.repository.dao;

import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;

import java.util.List;

public interface DiscountCardDao {
    DiscountCard find(int id);
    List<DiscountCard> findAll();
    boolean delete(int id);
}
