package by.zastr.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private BigDecimal price;
    private String name;
    private boolean isPromotional;

    public Product setId(int id){
        this.id = id;
        return this;
    }

    public Product setPrice(BigDecimal price){
        this.price = price;
        return this;
    }

    public Product setName(String name){
        this.name = name;
        return this;
    }

    public Product setPromotional(boolean promotional){
        isPromotional = promotional;
        return this;
    }
}
