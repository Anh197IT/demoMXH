package com.mxh.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class PostDto {
	private Integer authorId;
    private String title;
    private String body;
    private String authorUsername; 
    private Timestamp createdAt;
}