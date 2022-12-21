package by.zastr.repository.dao.jdbc;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.dao.mapper.ProductMapper;
import by.zastr.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static final String FIND_BY_ID = "SELECT id, name, price, promotional FROM products WHERE id=?";
    public static final String FIND_ALL = "SELECT id, name, price, promotional FROM products";

    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper mapper;

    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate, ProductMapper mapper){
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Product find(int id){
        Product product= null;
        try{
            product = jdbcTemplate.queryForObject(FIND_BY_ID, mapper , id);
        }catch (EmptyResultDataAccessException e) {
            System.out.println("Product not found");
        }
        return product;
    }

    @Override
    public List<Product> findAll(){
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public boolean delete(int id){
        return false;
    }
}
