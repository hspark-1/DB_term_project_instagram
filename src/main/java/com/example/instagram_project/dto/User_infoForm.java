package com.example.instagram_project.dto;

import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class User_infoForm {
	
	private String id;
	private User user;
	private String intro_comment;
	private String email;
	private int gender;

	public User_info toEntity() {
		return new User_info(id, user, intro_comment, email, gender);
	}
	
}
