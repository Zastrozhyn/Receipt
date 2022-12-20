package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.dao.ReceiptDao;
import by.zastr.repository.entity.Receipt;
import by.zastr.repository.entity.storage.ReceiptDB;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmbeddedReceiptDao implements ReceiptDao {

    private final ReceiptDB storage;

    @Autowired
    public EmbeddedReceiptDao(ReceiptDB storage){
        this.storage = storage;
    }

    @Override
    public Receipt find(int id){
        return storage.getReceipts().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Receipt> findAll(){
        return storage.getReceipts();
    }
}
