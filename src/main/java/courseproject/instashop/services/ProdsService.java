package courseproject.instashop.services;

import courseproject.instashop.models.Product;
import courseproject.instashop.repos.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdsService {
    @Autowired
    private ProductsRepository productsRepository;

    public Iterable<Product> listall(){
        return productsRepository.findAll();
    }

    public List<Product> getByName(String name){
        return productsRepository.findByKeyword(name);
    }
}

