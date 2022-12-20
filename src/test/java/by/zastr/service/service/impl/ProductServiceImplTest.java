package by.zastr.service.service.impl;

import by.zastr.repository.dao.impl.embedded.EmbeddedDiscountCardDao;
import by.zastr.repository.dao.impl.embedded.EmbeddedProductDao;
import by.zastr.repository.entity.storage.DiscountCardDB;
import by.zastr.repository.entity.storage.Warehouse;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.DiscountCardService;
import by.zastr.service.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private static Warehouse warehouse;
    private static ProductService service;
    public static final int ID_EXIST = 1;
    public static final int AMOUNT_OF_PRODUCTS = 9;
    public static final int ID_NOT_EXIST = 111;

    @BeforeAll
    static void init(){
        warehouse = new Warehouse();
        service = new ProductServiceImpl(new EmbeddedProductDao(warehouse));
    }

    @Test
    void find(){
        assertNotNull(service.find(ID_EXIST));
    }

    @Test
    void findThrow(){
        assertThrows(EntityException.class, () -> service.find(ID_NOT_EXIST));
    }

    @Test
    void findAll(){
        assertEquals(service.findAll().size(), AMOUNT_OF_PRODUCTS);
    }

    @Test
    void delete(){
        assertFalse(service.delete(ID_NOT_EXIST));
    }
}