package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.entity.storage.DiscountCardDB;
import by.zastr.repository.entity.storage.Warehouse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmbeddedProductDaoTest {

    private static final int AMOUNT_OF_PRODUCTS = 9;

    private static EmbeddedProductDao dao;

    @BeforeAll
    static void init(){
        dao = new EmbeddedProductDao(new Warehouse());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 9, 10})
    void checkFindShouldReturnProduct(int id){
        assertThat(dao.find(id)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {30, 40, 50, 11102})
    void checkFindShouldReturnNull(int id){
        assertThat(dao.find(id)).isNull();
    }

    @Test
    void checkFindAll(){
        assertThat(dao.findAll()).hasSize(AMOUNT_OF_PRODUCTS);
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 43, 55, 11102})
    void checkDeleteShouldReturnFalse(int id){
        assertThat(dao.delete(id)).isFalse();
    }
}