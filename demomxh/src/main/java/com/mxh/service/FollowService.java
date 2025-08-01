package com.mxh.service;

import com.mxh.entity.Follow;
import com.mxh.entity.FollowId;
import com.mxh.entity.User;
import com.mxh.repository.FollowRepository;
import com.mxh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean isFollowing(Integer currentUserId, Integer targetUserId) {
        return followRepository.existsById(new FollowId(currentUserId, targetUserId));
    }

    @Transactional
    public void followUser(Integer currentUserId, Integer targetUserId) {
        if (isFollowing(currentUserId, targetUserId)) {
            return; 
        }
        User following = userRepository.findById(currentUserId).orElseThrow();
        User followed = userRepository.findById(targetUserId).orElseThrow();

        Follow follow = new Follow();
        follow.setId(new FollowId(currentUserId, targetUserId));
        follow.setFollowingUser(following);
        follow.setFollowedUser(followed);
        follow.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        followRepository.save(follow);
    }

    @Transactional
    public void unfollowUser(Integer currentUserId, Integer targetUserId) {
        FollowId followId = new FollowId(currentUserId, targetUserId);
        if (followRepository.existsById(followId)) {
            followRepository.deleteById(followId);
        }
    }

    public long getFollowingCount(Integer userId) {
        return followRepository.countByFollowingUserId(userId);
    }

    public long getFollowersCount(Integer userId) {
        return followRepository.countByFollowedUserId(userId);
    }
}