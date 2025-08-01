package com.mxh.controller;

import com.mxh.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    
    private final Integer currentUserId = 1;

    @PostMapping("/follow/{userId}")
    public String followUser(@PathVariable("userId") Integer userId) {
        followService.followUser(currentUserId, userId);
        return "redirect:/users/" + userId; 
    }

    @PostMapping("/unfollow/{userId}")
    public String unfollowUser(@PathVariable("userId") Integer userId) {
        followService.unfollowUser(currentUserId, userId);
        return "redirect:/users/" + userId; 
    }
}