package by.zastr.repository.entity.storage;

import by.zastr.repository.entity.DiscountCard;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DiscountCardDB {
    private List<DiscountCard> cards;
    public DiscountCardDB() {
        cards = new ArrayList<>(List.of(new DiscountCard(1,0.1), new DiscountCard(2,0.05)));
    }
}
