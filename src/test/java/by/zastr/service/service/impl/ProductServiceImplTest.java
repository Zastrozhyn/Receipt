package by.zastr.service.service.impl;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.dao.jdbc.ProductDaoImpl;
import by.zastr.repository.entity.Product;
import by.zastr.service.exception.EntityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static final int ID_EXIST = 1;
    private static final int ID_EXIST_2 = 2;
    private static final int ID_EXIST_3 = 3;
    private static final int ID_EXIST_4 = 4;
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.ONE;
    private static final String PRODUCT_NAME = "Name";
    private static final int AMOUNT_OF_PRODUCTS = 3;
    private static final int ID_NOT_EXIST = 111;

    private static ProductDao dao;
    private static ProductServiceImpl service;
    private static Product product;

    @BeforeAll
    static void init(){
        dao = Mockito.mock(ProductDaoImpl.class);
        service = new ProductServiceImpl(dao);
        product = Product.builder()
                .id(ID_EXIST)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();

        when(dao.find(ID_EXIST)).thenReturn(product);
        when(dao.find(ID_EXIST_2)).thenReturn(product);
        when(dao.find(ID_EXIST_3)).thenReturn(product);
        when(dao.find(ID_EXIST_4)).thenReturn(product);
        when(dao.find(ID_NOT_EXIST)).thenReturn(null);
        when(dao.findAll()).thenReturn(List.of(new Product(), product, product));
        when(dao.delete(ID_NOT_EXIST)).thenReturn(false);
        when(dao.delete(ID_EXIST)).thenReturn(true);
    }

    @AfterEach
    void afterEachTest(){
        verifyNoMoreInteractions(dao);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void checkFind(int id){
        assertThat(service.find(id)).isEqualTo(product);
        verify(dao).find(id);
    }

    @Test
    void checkFindShouldThrowException(){
        assertThatThrownBy(() -> service.find(ID_NOT_EXIST)).isInstanceOf(EntityException.class);
        verify(dao).find(ID_NOT_EXIST);
    }

    @Test
    void checkFindAll(){
        assertThat(service.findAll()).hasSize(AMOUNT_OF_PRODUCTS);
        verify(dao).findAll();
    }

    @Test
    void checkDeleteShouldReturnFalse(){
        assertThat(service.delete(ID_NOT_EXIST)).isFalse();
        verify(dao).delete(ID_NOT_EXIST);
    }

    @Test
    void checkDeleteShouldReturnTrue(){
        assertThat(service.delete(ID_EXIST)).isTrue();
        verify(dao).delete(ID_EXIST);
    }
}