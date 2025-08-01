package com.mxh.repository;

import com.mxh.entity.Follow;
import com.mxh.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
   
    boolean existsById(FollowId id);
 
    long countByFollowingUserId(Integer userId);

   
    long countByFollowedUserId(Integer userId);
}