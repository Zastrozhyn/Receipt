package by.zastr.service.service.impl;

import by.zastr.repository.dao.DiscountCardDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import by.zastr.service.service.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardDao dao;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardDao dao){
        this.dao = dao;
    }

    @Override
    public DiscountCard find(int id){
        DiscountCard card = dao.find(id);
        if (card == null){
            throw new EntityException(ExceptionCode.DISCOUNT_CARD_NOT_FOUND.getErrorCode());
        }
        return card;
    }

    @Override
    public List<DiscountCard> findAll(){
        return dao.findAll();
    }

    @Override
    public boolean delete(int id){
        return dao.delete(id);
    }
}
