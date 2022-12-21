package by.zastr.service.service.impl;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.entity.Product;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import by.zastr.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao dao;

    @Autowired
    public ProductServiceImpl(ProductDao dao){
        this.dao = dao;
    }

    @Override
    public Product find(int id){
        Product product = dao.find(id);
        if (product == null){
            throw new EntityException(ExceptionCode.PRODUCT_NOT_FOUND.getErrorCode());
        }
        return product;
    }

    @Override
    public List<Product> findAll(){
        return dao.findAll();
    }

    @Override
    public boolean delete(int id){
        return dao.delete(id);
    }
}
