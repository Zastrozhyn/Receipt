package by.zastr.service.service;

import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;

import java.util.List;
import java.util.Map;

public interface ReceiptService {
    Receipt find(int id);
    List<Receipt> findAll();
    Receipt create(Map <Integer, Integer> products, int idCard);
}
