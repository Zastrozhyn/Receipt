package by.zastr.repository.dao.jdbc;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.dao.mapper.ProductMapper;
import by.zastr.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static final String FIND_BY_ID = "SELECT id, name, price, promotional FROM products WHERE id=?";
    public static final String FIND_ALL = "SELECT id, name, price, promotional FROM products";
    private static final String CREATE_PRODUCT = "INSERT INTO products (name, price, promotional) VALUES (NULL,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE products SET name=?, price=?, promotional=? WHERE id=?)";
    private final static String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";

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
    public void delete(int id){
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }

    @Override
    public Product update(Product product){
        jdbcTemplate.update(UPDATE_PRODUCT, product.getName(), product.getPrice(), product.isPromotional(), product.getId());
        return product;
    }

    @Override
    public Product create(Product product){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("promotional", product.isPromotional());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        KeyHolder keyHolder = simpleJdbcInsert
                .withTableName("products")
                .usingColumns("name", "price", "promotional")
                .usingGeneratedKeyColumns("id")
                .withoutTableColumnMetaDataAccess()
                .executeAndReturnKeyHolder(params);
        product.setId(keyHolder.getKey().intValue());
        return product;
    }
}
