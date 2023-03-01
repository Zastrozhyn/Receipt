package by.zastr.controller.rest;

import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.Receipt;
import by.zastr.service.service.ProductService;
import by.zastr.service.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ReceiptService receiptService;

    @Autowired
    public ProductController(ProductService service, ReceiptService receiptService){
        this.service = service;
        this.receiptService = receiptService;
    }


    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Integer id) {
        return service.find(id);
    }

    @GetMapping("/create")
    public Receipt create(@RequestParam(required = true, name = "products") List<Integer> products,
                          @RequestParam(required = true, name = "amount") List<Integer> amount,
                          @RequestParam(required = false, name = "card") Integer card){
        if (card == null){
            card = 0;
        }
        Map<Integer, Integer>  map = new HashMap<>();
        for (int i = 0; i < products.size(); i++){
            map.put(products.get(i), amount.get(i));
        }
        return receiptService.create(map,card);
    }
}
