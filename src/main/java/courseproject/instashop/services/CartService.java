package courseproject.instashop.services;

import courseproject.instashop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public double getSummary(){
        return cartRepository.findSummary();
    }
}
