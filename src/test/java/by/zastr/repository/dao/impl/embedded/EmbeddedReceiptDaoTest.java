package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.entity.storage.DiscountCardDB;
import by.zastr.repository.entity.storage.ReceiptDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmbeddedReceiptDaoTest {

    private static final int AMOUNT_OF_RECEIPT = 0;

    private static EmbeddedReceiptDao dao;

    @BeforeAll
    static void init(){
        dao = new EmbeddedReceiptDao(new ReceiptDB());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 11102})
    void checkFindShouldReturnNull(int id){
        assertThat(dao.find(id)).isNull();
    }

    @Test
    void checkFindAll(){
        assertThat(dao.findAll()).hasSize(AMOUNT_OF_RECEIPT);
    }
}