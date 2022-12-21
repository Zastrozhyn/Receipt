package by.zastr.controller.view.impl;

import by.zastr.controller.view.ReceiptWriter;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptWriterToFile implements ReceiptWriter {

    private final static String SOURCE = "src\\main\\resources\\output.txt";
    private final static double PROMOTIONAL_DISCOUNT = 0.1;

    private final ReceiptWriter receiptWriter;

    public ReceiptWriterToFile(ReceiptWriter writer){
        this.receiptWriter = writer;
    }

    @Override
    public void write(Receipt receipt){
        receiptWriter.write(receipt);
        FileWriter writer = null;
        try {
            writer = new FileWriter(SOURCE);
            writer.write("Time" + LocalDateTime.now() + "\n");
            writer.write("Receipt -----> \n");
            int count = 1;
            for (Product product: receipt.getProducts().keySet()){
                writer.write(count + ". ");
                BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(receipt.getProducts().get(product)));
                writer.write(product.getName() + ", amount :"
                        + receipt.getProducts().get(product) + ", price: " + total);
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
        } catch (IOException e) {
            System.out.println("Error during writing file");;
        }
    }
}
