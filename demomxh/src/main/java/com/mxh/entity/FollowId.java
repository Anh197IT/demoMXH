package com.mxh.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowId implements Serializable {

    @Column(name = "following_user_id")
    private Integer followingUserId;

    @Column(name = "followed_user_id")
    private Integer followedUserId;

    
    public FollowId() {}

    public FollowId(Integer followingUserId, Integer followedUserId) {
        this.followingUserId = followingUserId;
        this.followedUserId = followedUserId;
    }

    
    public Integer getFollowingUserId() { return followingUserId; }
    public void setFollowingUserId(Integer followingUserId) { this.followingUserId = followingUserId; }
    public Integer getFollowedUserId() { return followedUserId; }
    public void setFollowedUserId(Integer followedUserId) { this.followedUserId = followedUserId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowId followId = (FollowId) o;
        return Objects.equals(followingUserId, followId.followingUserId) &&
               Objects.equals(followedUserId, followId.followedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingUserId, followedUserId);
    }
}