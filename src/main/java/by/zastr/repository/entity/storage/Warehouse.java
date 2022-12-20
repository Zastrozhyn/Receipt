package by.zastr.repository.entity.storage;

import by.zastr.repository.entity.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Warehouse {
    private List<Product> products;
    public Warehouse(){
        products = new ArrayList<>(List.of(new Product(1, BigDecimal.TEN, "Cheese", false),
                                            new Product(2, BigDecimal.TEN, "Milk", true),
                                            new Product(3, BigDecimal.valueOf(5), "water", false),
                                            new Product(4, BigDecimal.valueOf(12), "Cream", true),
                                            new Product(5, BigDecimal.TEN, "Bread", true),
                                            new Product(6, BigDecimal.valueOf(40), "Vine", true),
                                            new Product(7, BigDecimal.valueOf(20), "Pizza", true),
                                            new Product(9, BigDecimal.valueOf(23), "Sushi", true)));
        Product product = new Product();
        product.setId(10)
                .setName("Something")
                .setPrice(BigDecimal.valueOf(100))
                .setPromotional(true);
        products.add(product);
    }
}
