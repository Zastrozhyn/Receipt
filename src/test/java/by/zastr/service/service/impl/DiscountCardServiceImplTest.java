package by.zastr.service.service.impl;

import by.zastr.repository.dao.DiscountCardDao;
import by.zastr.repository.dao.jdbc.DiscountCardDaoImpl;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.service.exception.EntityException;
import by.zastr.service.service.DiscountCardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DiscountCardServiceImplTest {

    private static final int ID_EXIST = 1;
    private static final int ID_EXIST_2 = 2;
    private static final int ID_EXIST_3 = 3;
    private static final int ID_EXIST_4 = 4;
    private static final int AMOUNT_OF_CARDS = 4;
    private static final int ID_NOT_EXIST = 111;
    private static final double DISCOUNT = 0.1;

    private static DiscountCardDao dao;
    private static DiscountCardService service;
    private static DiscountCard card;

    @BeforeAll
    static void init(){
        dao = mock(DiscountCardDaoImpl.class);
        service = new DiscountCardServiceImpl(dao);

        card = DiscountCard.builder()
                .id(ID_EXIST)
                .discount(DISCOUNT)
                .build();

        when(dao.find(ID_EXIST)).thenReturn(card);
        when(dao.find(ID_EXIST_2)).thenReturn(card);
        when(dao.find(ID_EXIST_3)).thenReturn(card);
        when(dao.find(ID_EXIST_4)).thenReturn(card);
        when(dao.find(ID_NOT_EXIST)).thenReturn(null);
        when(dao.findAll()).thenReturn(List.of(new DiscountCard(), card, card, card));
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
        assertThat(service.find(id)).isEqualTo(card);
        verify(dao).find(id);
    }

    @Test
    void checkFindShouldThrowException(){
        assertThatThrownBy(() -> service.find(ID_NOT_EXIST)).isInstanceOf(EntityException.class);
        verify(dao).find(ID_NOT_EXIST);
    }

    @Test
    void checkFindAll(){
        assertThat(service.findAll()).hasSize(AMOUNT_OF_CARDS);
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