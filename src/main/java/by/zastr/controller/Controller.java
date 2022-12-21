package by.zastr.controller;

import by.zastr.controller.util.ArgsReader;
import by.zastr.controller.view.ReceiptWriter;
import by.zastr.controller.view.impl.ReceiptWriterToFile;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import by.zastr.service.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Controller {
    private final ReceiptService receiptService;
    private final ArgsReader reader;
    private final ReceiptWriter writer;

    @Autowired
    public Controller(ReceiptService receiptService, ArgsReader reader, ReceiptWriter writer){
        this.receiptService = receiptService;
        this.reader = reader;
        this.writer = writer;
    }

    public void start(String[] args){
        Map<Integer, Integer> products = reader.readProducts(args);
        int cardId = reader.readCard(args);
        ReceiptWriterToFile writerFile = new ReceiptWriterToFile(writer);
        try {
            Receipt receipt = receiptService.create(products, cardId);
            writerFile.write(receipt);
        } catch (EntityException e){
            System.out.println("Error = " + e.getErrorCode());
            if (e.getErrorCode() == ExceptionCode.PRODUCT_NOT_FOUND.getErrorCode()){
                System.out.println(ExceptionCode.PRODUCT_NOT_FOUND);
            }
            if (e.getErrorCode() == ExceptionCode.DISCOUNT_CARD_NOT_FOUND.getErrorCode()){
                System.out.println(ExceptionCode.DISCOUNT_CARD_NOT_FOUND);
            }
        }

    }
}
