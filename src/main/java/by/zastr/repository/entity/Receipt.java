package by.zastr.repository.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class Receipt {
    private static int count = 1;
    private int id;
    private Map<Product, Integer> products;
    private DiscountCard card;
    private BigDecimal total;
    private boolean isPromotional;

    public Receipt(){
        products = new HashMap<>();
        total = BigDecimal.ZERO;
        this.id = count;
        count++;
    }

    public void addProduct(Product product, int amount){
        products.put(product, amount);
    }
}
