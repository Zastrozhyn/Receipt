package by.zastr.repository.dao.jdbc;

import by.zastr.repository.dao.DiscountCardDao;
import by.zastr.repository.entity.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountCardDaoImpl implements DiscountCardDao {

    public static final String FIND_BY_ID = "SELECT id, discount FROM discount_card WHERE id=?";
    public static final String FIND_ALL = "SELECT id, discount FROM discount_card";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DiscountCardDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DiscountCard find(int id){
        DiscountCard card= null;
        try{
            card = jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(DiscountCard.class), id);
        }catch (EmptyResultDataAccessException e) {
            System.out.println("Product not found");
        }
        return card;
    }

    @Override
    public List<DiscountCard> findAll(){
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(DiscountCard.class));
    }

    @Override
    public boolean delete(int id){
        return false;
    }
}
