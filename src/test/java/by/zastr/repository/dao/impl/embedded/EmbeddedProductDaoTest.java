package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.entity.storage.Warehouse;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.ProductService;
import by.zastr.service.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmbeddedProductDaoTest {

    private static EmbeddedProductDao dao;
    public static final int ID_EXIST = 1;
    public static final int AMOUNT_OF_PRODUCTS = 9;
    public static final int ID_NOT_EXIST = 111;

    @BeforeAll
    static void init(){
        dao = new EmbeddedProductDao(new Warehouse());
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
        assertEquals(dao.findAll().size(), AMOUNT_OF_PRODUCTS);
    }

    @Test
    void delete(){
        assertFalse(dao.delete(ID_NOT_EXIST));
    }
}