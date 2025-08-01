package com.mxh.controller;

import com.mxh.entity.User;
import com.mxh.repository.UserRepository;
import com.mxh.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    
    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "danh-sach-bai-viet";
    }

    @GetMapping("/posts/new")
    public String showNewPostForm(Model model) { 
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "tao-bai-viet";
    }

    
    @PostMapping("/posts")
    public String createPost(@RequestParam String title, 
                             @RequestParam String body, 
                             @RequestParam Integer userId) {       
        postService.createPost(title, body, userId);
        return "redirect:/";
    }
}