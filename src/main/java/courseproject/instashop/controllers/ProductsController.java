package courseproject.instashop.controllers;

import courseproject.instashop.models.Cart;
import courseproject.instashop.models.Product;
import courseproject.instashop.repos.CartRepository;
import courseproject.instashop.repos.ProductsRepository;
import courseproject.instashop.services.CartService;
import courseproject.instashop.services.ProdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProdsService prodsService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;

    @GetMapping("/catalog")
    public String MainProducts(Model model){
        Iterable<Product> products = productsRepository.findAll();
        Iterable<Cart> carts = cartRepository.findAll();
        double sum = cartRepository.findSummary();
        model.addAttribute("products", products);
        model.addAttribute("carts", carts);
        model.addAttribute("cart", sum);
        return "products";
    }

    @GetMapping("/catalog/{id}")
    public String ProductsDetails(@PathVariable(value = "id") long id, Model model){
        if(!productsRepository.existsById(id)) {
            return "redirect:/catalog";
        }

        Optional<Product> products = productsRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        products.ifPresent(res::add);
        model.addAttribute("products", res);
        return "products-details";
    }

    @PostMapping("/catalog/search")
    public String searchProducts(String name, Model model){
        List<Product> product = prodsService.getByName(name);
        model.addAttribute("prod", product);
        return "products";
    }

    @PostMapping("/catalog/{id}")
    public String addingToCart(@PathVariable(value = "id") long id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "price", required = false) Double price,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "color", required = false) String color, Model model){
        Product product = productsRepository.findById(id).orElseThrow();
        Cart cart = new Cart();
        cart.setName(product.getName());
        cart.setPrice(product.getPrice());
        cart.setSize(product.getSize());
        cart.setColor(product.getColor());
        cartRepository.save(cart);
        return "redirect:/catalog";
    }

}
