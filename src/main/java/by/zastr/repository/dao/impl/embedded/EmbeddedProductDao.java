package by.zastr.repository.dao.impl.embedded;

import by.zastr.repository.dao.ProductDao;
import by.zastr.repository.entity.Product;
import by.zastr.repository.entity.storage.Warehouse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Repository
public class EmbeddedProductDao implements ProductDao {

    private final Warehouse warehouse;

    @Autowired
    public EmbeddedProductDao(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    @Override
    public Product find(int id){
        return warehouse.getProducts().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll(){
        return warehouse.getProducts();
    }

    @Override
    public boolean delete(int id){
        Product product = find(id);
        if(product == null){
            return false;
        } else {
            warehouse.getProducts().remove(product);
        }
        return true;
    }
}
