package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.dao.DiscountCardDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.storage.DiscountCardDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmbeddedDiscountCardDao implements DiscountCardDao {

    private final DiscountCardDB storage;

    @Autowired
    public EmbeddedDiscountCardDao(DiscountCardDB storage){
        this.storage = storage;
    }

    @Override
    public DiscountCard find(int id){
        return storage.getCards().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DiscountCard> findAll(){
        return storage.getCards();
    }

    @Override
    public boolean delete(int id){
        DiscountCard card = find(id);
        if(card == null){
            return false;
        } else {
            storage.getCards().remove(card);
        }
        return true;
    }
}
