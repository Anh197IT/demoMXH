package com.mxh.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Lob 
    private String body;

    private String status;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;
}