package by.zastr.repository.dao.mapper;

import by.zastr.repository.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
        Product product = new Product();
        product.setId(rs.getInt("id"))
                .setPrice(BigDecimal.valueOf(rs.getInt("price")))
                .setName(rs.getString("name"))
                .setPromotional(rs.getBoolean("promotional"));
        return product;
    }
}
