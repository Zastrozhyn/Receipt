package by.zastr.service.service.impl;

import by.zastr.repository.dao.ReceiptDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.DiscountCardService;
import by.zastr.service.service.ProductService;
import by.zastr.service.service.ReceiptService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ReceiptServiceImplTest {

    private static final int RECEIPT_ID_EXIST = 1;
    private static final int CARD_ID_EXIST = 1;
    private static final int AMOUNT_OF_CARDS = 1;
    private static final int RECEIPT_ID_NOT_EXIST = 111;

    private static final int PRODUCT_ID = 1;
    private static final int PRODUCT_ID_2 = 2;
    private static final int AMOUNT_OF_PRODUCT = 1;

    private static ReceiptService service;
    private static ReceiptDao dao;
    private static ProductService productService;
    private static DiscountCardService discountCardService;
    private static Map<Integer, Integer> products;

    @BeforeAll
    static void init(){
        dao = Mockito.mock(ReceiptDao.class);
        productService = Mockito.mock(ProductService.class);
        discountCardService = Mockito.mock(DiscountCardService.class);
        service = new ReceiptServiceImpl(dao, productService, discountCardService);

        products = new HashMap<>();
        products.put(PRODUCT_ID, AMOUNT_OF_PRODUCT);
        products.put(PRODUCT_ID_2, AMOUNT_OF_PRODUCT);

        when(dao.find(RECEIPT_ID_EXIST)).thenReturn(new Receipt());
        when(dao.findAll()).thenReturn(List.of(new Receipt()));
        when(dao.find(RECEIPT_ID_NOT_EXIST)).thenReturn(null);
        when(discountCardService.find(CARD_ID_EXIST)).thenReturn(new DiscountCard(CARD_ID_EXIST, 0.1));
        when(productService.find(PRODUCT_ID)).thenReturn(new Product()
                .setPromotional(false)
                .setPrice(BigDecimal.TEN));
        when(productService.find(PRODUCT_ID_2)).thenReturn(new Product()
                .setPromotional(false)
                .setPrice(BigDecimal.ONE));
    }

    @AfterEach
    void afterEachTest(){
        verifyNoMoreInteractions(dao);
    }

    @Test
    void checkFind(){
        assertThat(service.find(RECEIPT_ID_EXIST)).isNotNull();
        verify(dao).find(RECEIPT_ID_EXIST);
    }

    @Test
    void checkFindShouldThrowException(){
        assertThatThrownBy(() -> service.find(RECEIPT_ID_NOT_EXIST)).isInstanceOf(EntityException.class);
        verify(dao).find(RECEIPT_ID_NOT_EXIST);
    }

    @Test
    void checkFindAll(){
        assertThat(service.findAll()).hasSize(AMOUNT_OF_CARDS);
        verify(dao).findAll();
    }

    @Test
    void checkCreate(){
        assertThat(service.create(products, CARD_ID_EXIST)).isNotNull();
    }


}