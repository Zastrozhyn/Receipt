package by.zastr.service.util;

import org.springframework.stereotype.Component;

@Component
public class ProductNameValidator {
    public boolean isNameValid(String name){
        return name.matches("\\b\\w{1,20}\\b");
    }
}
