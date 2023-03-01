package by.zastr.repository.util.impl;

import by.zastr.repository.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;

class LRUCacheImplTest {

    private static final int CAPACITY = 2;
    private static final int ID = 1;
    private static final int ID_2 = 2;
    private static final int ID_3 = 3;
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.ONE;
    private static final String PRODUCT_NAME = "Name";
    private static final int ID_NOT_EXIST = 111;
    private static LRUCacheImpl<Product> cache;
    private static Product product;

    @BeforeAll
    static void init(){
        cache = new LRUCacheImpl<>(CAPACITY);
        product = Product.builder()
                .id(ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();
    }

    @Test
    void checkCapacity(){
        assertThat(cache.getCapacity()).isEqualTo(CAPACITY);
    }

    @Test
    void checkGetShouldReturnNull(){
        assertThat(cache.get(ID_NOT_EXIST)).isNull();
    }

    @Test
    void checkGetAfterPut(){
        assertThat(cache.get(ID)).isNull();
        cache.put(ID, product);
        assertThat(cache.get(ID)).isEqualTo(product);
    }

    @Test
    void checkOutOfCapacity(){
        assertThat(cache.get(ID)).isNull();
        cache.put(ID, product);
        assertThat(cache.get(ID)).isEqualTo(product);
        cache.put(ID_2, new Product());
        cache.put(ID_3, new Product());
        assertThat(cache.get(ID)).isNull();
    }
}