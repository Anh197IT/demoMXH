package com.mxh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mxh.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

