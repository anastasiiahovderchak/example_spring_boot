package courseproject.instashop.controllers;

import courseproject.instashop.models.Role;
import courseproject.instashop.models.User;
import courseproject.instashop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String Registration(Model model){
        model.addAttribute("title", "Regastration page");
        return "registration";
    }

    @PostMapping("/registration")
    public String AddUser(User user, Map<String, Object> model){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB != null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
