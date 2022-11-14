package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowIDclass implements Serializable {
	
	private String user_id;
	private String follow_id;

}
