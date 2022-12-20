package by.zastr.controller.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArgsReader {

    public Map<Integer, Integer> readProducts(String[] args){
        Map<Integer, Integer> products = new HashMap<>();
        for (String str: args){
            if (Character.isDigit(str.charAt(0))){
                StringBuilder temp = new StringBuilder();
                int i = 0;
                while (i < str.length() && Character.isDigit(str.charAt(i))){
                    temp.append(str.charAt(i));
                    i++;
                }
                int id = Integer.parseInt(temp.toString());
                while (i < str.length() && !Character.isDigit(str.charAt(i))){
                    i++;
                }
                temp = new StringBuilder();
                while (i < str.length() && Character.isDigit(str.charAt(i))){
                    temp.append(str.charAt(i));
                    i++;
                }
                int amount = Integer.parseInt(temp.toString());
                products.put(id, amount);
            }
        }
        return products;
    }

    public int readCard(String[] args){
        int idCard = 0;
        for (String str: args){
            if (str.toLowerCase().charAt(0) == 'c'){
                int i =0;
                while (i < str.length() && !Character.isDigit(str.charAt(i))){
                    i++;
                }
                StringBuilder temp = new StringBuilder();
                while (i < str.length() && Character.isDigit(str.charAt(i))){
                    temp.append(str.charAt(i));
                    i++;
                }
                idCard = Integer.parseInt(temp.toString());
            }
        }
        return idCard;
    }
}
