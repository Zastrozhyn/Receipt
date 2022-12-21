package by.zastr.repository.entity.storage;

import by.zastr.repository.entity.Receipt;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ReceiptDB {
    private List<Receipt> receipts;
}
