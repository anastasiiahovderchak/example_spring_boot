package courseproject.instashop.controllers;

import courseproject.instashop.models.Order;
import courseproject.instashop.models.Post;
import courseproject.instashop.models.Product;
import courseproject.instashop.models.User;
import courseproject.instashop.repos.OrderRepository;
import courseproject.instashop.repos.ProductsRepository;
import courseproject.instashop.repos.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String userList(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/catalog")
    public String adminCatalog(Model model){
        Iterable<Product> products = productsRepository.findAll();
        model.addAttribute("products", products);
        return "products_admin";
    }

    @GetMapping("/catalog/{id}/edit")
    public String editProduct(@PathVariable(value = "id") long id, Model model){
        if(!productsRepository.existsById(id)) {
            return "redirect:/admin/catalog";
        }

        Optional<Product> prods = productsRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        prods.ifPresent(res::add);
        model.addAttribute("prods", res);
        return "products_admin_edit";
    }

    @PostMapping("/catalog/{id}/edit")
    public String updateProduct(@PathVariable(value = "id") long id, @RequestParam String collection, @RequestParam String name, @RequestParam double price, @RequestParam int size, @RequestParam String color, @RequestParam String shortdesc, @RequestParam String fulldesc, Model model){
        Product product = productsRepository.findById(id).orElseThrow();
        product.setCollection(collection);
        product.setName(name);
        product.setPrice(price);
        product.setSize(size);
        product.setColor(color);
        product.setShortdesc(shortdesc);
        product.setFulldesc(fulldesc);
        productsRepository.save(product);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/catalog/{id}/remove")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model){
        Product product = productsRepository.findById(id).orElseThrow();
        productsRepository.delete(product);
        return "redirect:/admin/catalog";
    }

    @GetMapping("/orders")
    public String adminOrders(Model model){
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders_admin";
    }

    @GetMapping("/orders/{id}/edit")
    public String editOrder(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)) {
            return "redirect:/admin/orders";
        }

        Optional<Order> orders = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        orders.ifPresent(res::add);
        model.addAttribute("orders", res);
        return "orders_admin_edit";
    }

    @PostMapping("/orders/{id}/edit")
    public String updatePOrder(@PathVariable(value = "id") long id, @RequestParam String status, Model model){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
        return "redirect:/admin/orders";
    }


//    @GetMapping("/{user}")
//    public String editUser(@PathVariable User user, Model model){
//        model.addAttribute("user", user);
//        return "userdetails";
//    }

}
