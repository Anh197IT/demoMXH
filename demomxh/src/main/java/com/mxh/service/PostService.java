package com.mxh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mxh.dto.PostDto;
import com.mxh.entity.Post;
import com.mxh.entity.User;
import com.mxh.repository.PostRepository;
import com.mxh.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void createPost(String title, String body, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + userId));

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setUser(user);
        post.setStatus("published"); 
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        postRepository.save(post);
    }
    
    private PostDto convertToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setCreatedAt(post.getCreatedAt());
        if (post.getUser() != null) {
            dto.setAuthorId(post.getUser().getId()); 
            dto.setAuthorUsername(post.getUser().getUsername());
        } else {
            dto.setAuthorUsername("Người dùng ẩn danh");
        }
        return dto;
    }
}
