package by.zastr.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private BigDecimal price;
    private String name;
    private boolean isPromotional;
}
