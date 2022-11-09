package com.example.instagram_project.dto;

import java.sql.Date;

import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
	
	private String user_id;
	private String password;
	private String name;
	private String phone_number;
	private Date birthday;
	private String profile_photo;

	public User toEntity() {
		return new User(user_id, password, name, phone_number, birthday, profile_photo);
	}

}
