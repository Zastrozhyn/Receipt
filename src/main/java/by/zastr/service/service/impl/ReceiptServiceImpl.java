package by.zastr.service.service.impl;

import by.zastr.repository.dao.ReceiptDao;
import by.zastr.repository.entity.DiscountCard;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import by.zastr.service.service.DiscountCardService;
import by.zastr.service.service.ProductService;
import by.zastr.service.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final static int AMOUNT_OF_PROMOTIONAL_PRODUCTS = 5;
    private final static double PERCENTAGE_CONVERSION = 0.01;
    private final static double PROMOTIONAL_DISCOUNT = 0.9;

    private final ReceiptDao dao;
    private final ProductService productService;
    private final DiscountCardService discountCardService;

    @Autowired
    public ReceiptServiceImpl(ReceiptDao dao, ProductService productService, DiscountCardService discountCardService){
        this.dao = dao;
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    @Override
    public Receipt find(int id){
        Receipt receipt = dao.find(id);
        if (receipt == null){
            throw new EntityException(ExceptionCode.PRODUCT_NOT_FOUND.getErrorCode());
        }
        return receipt;
    }

    @Override
    public List<Receipt> findAll(){
        return dao.findAll();
    }

    @Override
    public Receipt create(Map<Integer, Integer> products, int idCard){
        Receipt receipt = new Receipt();
        if(idCard != 0){
            DiscountCard card = discountCardService.find(idCard);
            receipt.setCard(card);
        }
        receipt.setPromotional(isEnoughPromotionalProducts(products));
        for (int id: products.keySet()){
            Product product = productService.find(id);
            int amount = products.get(id);
            BigDecimal total = receipt.getTotal();
            BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(amount));
            if (product.isPromotional() && receipt.isPromotional()){
                cost = cost.multiply(BigDecimal.valueOf(PROMOTIONAL_DISCOUNT));
            }
            receipt.setTotal(total.add(cost));
            receipt.addProduct(product, amount);
        }
        return receipt;
    }

    private boolean isEnoughPromotionalProducts(Map<Integer, Integer> products) {
        int count = 0;
        for (int id: products.keySet()){
            Product product = productService.find(id);
            if (product.isPromotional()){
                count+=products.get(id);
            }
        }
        return count >= AMOUNT_OF_PROMOTIONAL_PRODUCTS;
    }
}
