package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.entity.storage.DiscountCardDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmbeddedDiscountCardDaoTest {

    private static final int AMOUNT_OF_CARDS = 2;

    private static EmbeddedDiscountCardDao dao;

    @BeforeAll
    static void init(){
        dao = new EmbeddedDiscountCardDao(new DiscountCardDB());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void checkFindShouldReturnCard(int id){
        assertThat(dao.find(id)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 11102})
    void checkFindShouldReturnNull(int id){
        assertThat(dao.find(id)).isNull();
    }

    @Test
    void checkFindAll(){
        assertThat(dao.findAll()).hasSize(AMOUNT_OF_CARDS);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 11102})
    void checkDeleteShouldReturnFalse(int id){
        assertThat(dao.delete(id)).isFalse();
    }
}