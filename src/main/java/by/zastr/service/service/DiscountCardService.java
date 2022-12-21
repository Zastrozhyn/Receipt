package by.zastr.service.service;

import by.zastr.repository.entity.DiscountCard;

import java.util.List;

public interface DiscountCardService {
    DiscountCard find(int id);
    List<DiscountCard> findAll();
    boolean delete(int id);
}
