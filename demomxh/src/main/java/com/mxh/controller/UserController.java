package com.mxh.controller;

import com.mxh.entity.Post;
import com.mxh.entity.User;
import com.mxh.repository.PostRepository;
import com.mxh.repository.UserRepository;
import com.mxh.service.FollowService; // Import FollowService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowService followService; 

    @GetMapping("/users/{id}")
    public String viewUserProfile(@PathVariable("id") Integer id, Model model) {
        
        Integer currentUserId = 1;

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID người dùng không hợp lệ: " + id));
        List<Post> posts = postRepository.findByUserIdOrderByCreatedAtDesc(id);

        
        boolean isFollowing = followService.isFollowing(currentUserId, id);

     
        long followingCount = followService.getFollowingCount(id);
        long followersCount = followService.getFollowersCount(id);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("isFollowing", isFollowing);
        model.addAttribute("currentUserId", currentUserId);
        
        
        model.addAttribute("followingCount", followingCount);
        model.addAttribute("followersCount", followersCount);

        return "trang-ho-so";
    }
}
