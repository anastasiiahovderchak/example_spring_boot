package courseproject.instashop.controllers;

import courseproject.instashop.models.User;
import courseproject.instashop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal User user, Model model){
        if(!userRepository.existsById(user.getId())) {
            return "redirect:/catalog";
        }

        Optional<User> users = userRepository.findById(user.getId());
        ArrayList<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        return "userprofile";
    }

    @GetMapping("/{id}/edit")
    public String editProfile(@PathVariable(value = "id") long id, Model model){
        if(!userRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<User> users = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        return "useredit";
    }

    @PostMapping("/{id}/edit")
    public String updateProfile(@PathVariable(value = "id") long id, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String username, @RequestParam String password, @RequestParam int phonenumber, Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        userRepository.save(user);
        return "redirect:/user/{id}";
    }

//    @GetMapping("/{user}")
//    public String editUser(@PathVariable User user, Model model){
//        model.addAttribute("user", user);
//        return "userdetails";
//    }

}