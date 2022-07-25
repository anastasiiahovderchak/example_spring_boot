package courseproject.instashop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main(Model model) {
        model.addAttribute("title", "Main Page");
        return "home";
    }

    @GetMapping("/contacts")
    public String Contacts(Model model) {
        model.addAttribute("title", "Contacts");
        return "contacts";
    }

    @GetMapping("/about")
    public String AboutInfo(Model model) {
        model.addAttribute("title", "About");
        return "about";
    }

}