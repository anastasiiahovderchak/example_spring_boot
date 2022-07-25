package courseproject.instashop.services;

import courseproject.instashop.models.Cart;
import courseproject.instashop.models.Order;
import courseproject.instashop.models.Product;
import courseproject.instashop.repos.CartRepository;
import courseproject.instashop.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public double getSummary(){
        return orderRepository.findSummary();
    }
}
