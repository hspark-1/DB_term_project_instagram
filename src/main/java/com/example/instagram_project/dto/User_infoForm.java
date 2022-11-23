package com.example.instagram_project.dto;

import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User_infoForm {
	
	private User user;
	private String intro_comment;
	private String email;
	private int gender;

	public static User_infoForm createUser_infoForm(User user) {
		return new User_infoForm(user, " ", " ", 3);
	}

	public static User_infoForm createUser_infoForm(User_info user) {
		return new User_infoForm(
			user.getUser(),
			user.getIntro_comment(),
			user.getEmail(),
			user.getGender()
		);
	}
	
}
