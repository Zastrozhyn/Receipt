package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.entity.storage.DiscountCardDB;
import by.zastr.repository.entity.storage.Warehouse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmbeddedDiscountCardDaoTest {

    private static EmbeddedDiscountCardDao dao;
    public static final int ID_EXIST = 1;
    public static final int AMOUNT_OF_CARDS = 2;
    public static final int ID_NOT_EXIST = 111;

    @BeforeAll
    static void init(){
        dao = new EmbeddedDiscountCardDao(new DiscountCardDB());
    }

    @Test
    void find(){
        assertNotNull(dao.find(ID_EXIST));
    }

    @Test
    void notFind(){
        assertNull(dao.find(ID_NOT_EXIST));
    }

    @Test
    void findAll(){
        assertEquals(dao.findAll().size(), AMOUNT_OF_CARDS);
    }

    @Test
    void delete(){
        assertFalse(dao.delete(ID_NOT_EXIST));
    }
}