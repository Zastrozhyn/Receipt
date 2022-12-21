package by.zastr.controller.rest;

import by.zastr.repository.entity.DiscountCard;
import by.zastr.service.service.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/cards")
public class DiscountCardController {

    private final DiscountCardService service;

    @Autowired
    public DiscountCardController(DiscountCardService service){
        this.service = service;
    }

    @GetMapping
    public List<DiscountCard> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DiscountCard findById(@PathVariable Integer id) {
        return service.find(id);
    }
}
