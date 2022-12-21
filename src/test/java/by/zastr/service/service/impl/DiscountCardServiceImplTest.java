package by.zastr.service.service.impl;

import by.zastr.repository.dao.impl.embedded.EmbeddedDiscountCardDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.storage.DiscountCardDB;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.DiscountCardService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardServiceImplTest {

    private static DiscountCardDB db;
    private static DiscountCardService service;
    public static final int ID_EXIST = 1;
    public static final int AMOUNT_OF_CARDS = 2;
    public static final int ID_NOT_EXIST = 111;

    @BeforeAll
    static void init(){
        db = new DiscountCardDB();
        service = new DiscountCardServiceImpl(new EmbeddedDiscountCardDao(db));
    }

    @Test
    void find(){
        DiscountCard card = service.find(ID_EXIST);
        assertNotNull(card);
    }

    @Test
    void findThrow(){
        assertThrows(EntityException.class, () -> service.find(ID_NOT_EXIST));
    }

    @Test
    void findAll(){
        List<DiscountCard> list = service.findAll();
        assertEquals(list.size(), AMOUNT_OF_CARDS);
    }

    @Test
    void delete(){
        assertFalse(service.delete(ID_NOT_EXIST));
    }
}