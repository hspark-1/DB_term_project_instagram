package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story_check;

public class Story_checkForm {
	
	private long story_id;
	private String check_id;
	private int likes;

	public Story_check toEntity() {
		return new Story_check(story_id, check_id, likes);
	}
	
}
