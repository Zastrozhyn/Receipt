package by.zastr.service.service.impl;

import by.zastr.repository.dao.ReceiptDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.DiscountCardService;
import by.zastr.service.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static by.zastr.service.service.constant.TestConstant.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceImplTest {

    @Spy
    private static DiscountCardService discountCardService;
    @Spy
    private static ProductService productService;
    @Spy
    private static ReceiptDao dao;

    @InjectMocks
    private static ReceiptServiceImpl service;

    private static Map<Integer, Integer> products;

    @BeforeAll
    static void init(){

        products = new HashMap<>();
        products.put(PRODUCT_ID, AMOUNT_OF_PRODUCT);
        products.put(PRODUCT_ID_2, AMOUNT_OF_PRODUCT);
    }

    @AfterEach
    void afterEachTest(){
        verifyNoMoreInteractions(dao);
    }

    @Test
    void checkFind(){
        when(dao.find(ID_EXIST)).thenReturn(new Receipt());
        assertThat(service.find(ID_EXIST)).isNotNull();
        verify(dao).find(ID_EXIST);
    }

    @Test
    void checkFindShouldThrowException(){
        when(dao.find(ID_NOT_EXIST)).thenReturn(null);
        assertThatThrownBy(() -> service.find(RECEIPT_ID_NOT_EXIST)).isInstanceOf(EntityException.class);
        verify(dao).find(RECEIPT_ID_NOT_EXIST);
    }

    @Test
    void checkFindAll(){
        when(dao.findAll()).thenReturn(List.of(new Receipt()));
        assertThat(service.findAll()).hasSize(AMOUNT_OF_CARDS);
        verify(dao).findAll();
    }

    @Test
    void checkCreate(){
        when(discountCardService.find(CARD_ID_EXIST)).thenReturn(new DiscountCard(CARD_ID_EXIST, 0.1));
        when(productService.find(PRODUCT_ID)).thenReturn(new Product()
                .setPromotional(false)
                .setPrice(BigDecimal.TEN));
        when(productService.find(PRODUCT_ID_2)).thenReturn(new Product()
                .setPromotional(false)
                .setPrice(BigDecimal.ONE));
        assertThat(service.create(products, CARD_ID_EXIST)).isNotNull();
    }

}