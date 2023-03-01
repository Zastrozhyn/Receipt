package by.zastr.service.service.impl;

import by.zastr.repository.dao.DiscountCardDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.service.exception.EntityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static by.zastr.service.service.constant.TestConstant.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @InjectMocks
    private static DiscountCardServiceImpl service;
    @Spy
    private static  DiscountCardDao dao;

    private static DiscountCard card;

    @BeforeAll
    static void init(){
        card = DiscountCard.builder()
                .id(ID_EXIST)
                .discount(DISCOUNT)
                .build();
    }

    @AfterEach
    void afterEachTest(){
        verifyNoMoreInteractions(dao);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void checkFind(int id){
        when(dao.find(ID_EXIST)).thenReturn(card);
        when(dao.find(ID_EXIST_2)).thenReturn(card);
        when(dao.find(ID_EXIST_3)).thenReturn(card);
        assertThat(service.find(id)).isEqualTo(card);
        verify(dao).find(id);
    }

    @Test
    void checkFindShouldThrowException(){
        when(dao.find(ID_NOT_EXIST)).thenReturn(null);
        assertThatThrownBy(() -> service.find(ID_NOT_EXIST)).isInstanceOf(EntityException.class);
        verify(dao).find(ID_NOT_EXIST);
    }

    @Test
    void checkFindAll(){
        when(dao.findAll()).thenReturn(List.of(card));
        assertThat(service.findAll()).hasSize(AMOUNT_OF_CARDS);
        verify(dao).findAll();
    }

    @Test
    void checkDeleteShouldReturnFalse(){
        when(dao.delete(ID_NOT_EXIST)).thenReturn(false);
        assertThat(service.delete(ID_NOT_EXIST)).isFalse();
        verify(dao).delete(ID_NOT_EXIST);
    }

    @Test
    void checkDeleteShouldReturnTrue(){
        when(dao.delete(ID_EXIST)).thenReturn(true);
        assertThat(service.delete(ID_EXIST)).isTrue();
        verify(dao).delete(ID_EXIST);
    }

}