package courseproject.instashop.controllers;

import courseproject.instashop.models.Post;
import courseproject.instashop.repos.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/post")
    public String MainPosts(Model model){
        Iterable<Post> posts = postsRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
}
