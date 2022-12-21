package by.zastr.repository.dao;

import by.zastr.repository.entity.Receipt;

import java.util.List;

public interface ReceiptDao {
    Receipt find(int id);
    List<Receipt> findAll();
}
