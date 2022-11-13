package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story;
import com.example.instagram_project.entity.Story_check;
import com.example.instagram_project.entity.User;

public class Story_checkForm {
	
	private long id;
	private Story story;
	private String user_id;
	private User user;
	private int likes;

	public Story_check toEntity() {
		return new Story_check(id, story, user_id, user, likes);
	}
	
}
