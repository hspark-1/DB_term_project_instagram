package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Comment_likesIDclass implements Serializable {
	
	private Long comment_id;
	private String user_id;
	
}
