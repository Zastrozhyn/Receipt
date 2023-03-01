package by.zastr.service.service.impl;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.entity.Product;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import by.zastr.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao dao;

    @Autowired
    public ProductServiceImpl(@Qualifier("productDaoProxy") ProductDao dao){
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
    public void delete(int id){
        dao.delete(id);
    }

    @Override
    public Product update(Product product){
        return null;
    }

    @Override
    public Product create(Product product){
        return dao.create(product);
    }
}
