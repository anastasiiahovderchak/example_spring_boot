package courseproject.instashop.controllers;

import courseproject.instashop.models.Cart;
import courseproject.instashop.models.Order;
import courseproject.instashop.models.User;
import courseproject.instashop.repos.CartRepository;
import courseproject.instashop.repos.OrderRepository;
import courseproject.instashop.repos.UserRepository;
import courseproject.instashop.services.CartService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;

    @GetMapping("/order")
    public String fillOrder(@AuthenticationPrincipal User user, Model model){
        Iterable<Cart> carts = cartRepository.findAll();
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        model.addAttribute("cart", carts);
        return "order";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam String region, @RequestParam String city, @RequestParam String street,
                              @RequestParam int streetnum, @RequestParam int officenum, Model model){
        Order order = new Order(region, city, street, streetnum, officenum);
        double sum = cartService.getSummary();
        order.setStatus("created");
        order.setSummary_price(sum);
        orderRepository.save(order);
        return "redirect:/order/view";
    }


    @GetMapping("/order/{id}/view")
    public String viewOrder(@PathVariable(value = "id") long id, Model model){
        Iterable<Cart> carts = cartRepository.findAll();
        model.addAttribute("carts", carts);
        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);
        model.addAttribute("orders", res);
        return "view_order";
    }

    @GetMapping("/order/view")
    public String viewAllOrders(Model model){
        Iterable<Cart> carts = cartRepository.findAll();
        model.addAttribute("carts", carts);
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "view_order";
    }
}
