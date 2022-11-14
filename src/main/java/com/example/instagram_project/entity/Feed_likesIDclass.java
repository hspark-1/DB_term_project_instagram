package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Feed_likesIDclass implements Serializable {
	
	private String user_id;
	private Long feed_id;
	
}
