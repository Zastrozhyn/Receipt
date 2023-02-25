package by.zastr.repository.dao.proxy;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.dao.jdbc.ProductDaoImpl;
import by.zastr.repository.entity.Product;
import by.zastr.repository.util.CacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoProxy implements ProductDao {

    private final ProductDaoImpl dao;
    private final CacheFactory<Product> factory;


    @Autowired
    public ProductDaoProxy(ProductDaoImpl dao, CacheFactory<Product> factory){
        this.dao = dao;
        this.factory = factory;
    }

    @Override
    public Product find(int id){
        Product product = factory.cache().get(id);
        if(product == null){
            product = dao.find(id);
        }
        factory.cache().put(id, product);
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
        int id = product.getId();
        factory.cache().put(id, product);
        return dao.update(product);
    }

    @Override
    public Product create(Product product){
        Product created = dao.create(product);
        factory.cache().put(created.getId(), created);
        return created;
    }
}
