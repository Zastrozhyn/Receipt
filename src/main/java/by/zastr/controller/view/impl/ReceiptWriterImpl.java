package by.zastr.controller.view.impl;

import by.zastr.controller.view.ReceiptWriter;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ReceiptWriterImpl implements ReceiptWriter {

    private final static double PROMOTIONAL_DISCOUNT = 0.1;
    private final static String SOURCE = "src\\main\\resources\\output.txt";

    @Override
    public void write(Receipt receipt){
        if (receipt == null){
            throw new EntityException(ExceptionCode.RECEIPT_NOT_FOUND.getErrorCode());
        }
        System.out.println("Time" + LocalDateTime.now());
        System.out.println("Receipt ----->");
        int count = 1;
        for (Product product: receipt.getProducts().keySet()){
            System.out.print(count + ". ");
            BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(receipt.getProducts().get(product)));
            System.out.println(product.getName() + ", amount :"
                    + receipt.getProducts().get(product) + ", total: " + total);
            if (receipt.isPromotional() && product.isPromotional()){
                System.out.println("discount= " + total.multiply(BigDecimal.valueOf(PROMOTIONAL_DISCOUNT)));
            }
            System.out.println();
            count++;
        }
        System.out.println("-------------------------------------");
        System.out.println("Total= " + receipt.getTotal());
        if(receipt.getCard() != null){
            BigDecimal total = receipt.getTotal();
            BigDecimal discount = total.multiply(BigDecimal.valueOf(receipt.getCard().getDiscount()));
            System.out.println("total discount for card id= " + receipt.getCard().getId() + ", discount= "+ discount);
            System.out.println("to pay = " + total.subtract(discount));
        }
        try {
            writeToFile(receipt);
        } catch (IOException e) {
            System.out.println("Error during writing file");
        }
    }

    private void writeToFile(Receipt receipt) throws IOException{
        FileWriter writer = new FileWriter(SOURCE);
        writer.write("Time" + LocalDateTime.now() + "\n");
        writer.write("Receipt -----> \n");
        int count = 1;
        for (Product product: receipt.getProducts().keySet()){
            writer.write(count + ". ");
            BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(receipt.getProducts().get(product)));
            writer.write(product.getName() + ", amount :"
                    + receipt.getProducts().get(product) + ", total: " + total);
            if (receipt.isPromotional() && product.isPromotional()){
                writer.write("discount= " + total.multiply(BigDecimal.valueOf(PROMOTIONAL_DISCOUNT)));
            }
            writer.write("\n");
            count++;
        }
        writer.write("-------------------------------------\n");
        writer.write("Total= " + receipt.getTotal() + "\n");
        if(receipt.getCard() != null){
            BigDecimal total = receipt.getTotal();
            BigDecimal discount = total.multiply(BigDecimal.valueOf(receipt.getCard().getDiscount()));
            writer.write("total discount for card id= " + receipt.getCard().getId() + ", discount= "+ discount + "\n");
            writer.write("to pay = " + total.subtract(discount));
        }
        writer.close();
    }
}
