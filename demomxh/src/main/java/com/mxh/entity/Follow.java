package com.mxh.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followingUserId") 
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followedUserId") 
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    
    public Follow() {}

    
    public FollowId getId() { return id; }
    public void setId(FollowId id) { this.id = id; }
    public User getFollowingUser() { return followingUser; }
    public void setFollowingUser(User followingUser) { this.followingUser = followingUser; }
    public User getFollowedUser() { return followedUser; }
    public void setFollowedUser(User followedUser) { this.followedUser = followedUser; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
