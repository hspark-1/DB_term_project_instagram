package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class StoryForm {
	
	private long story_id;
	private User user;
	private String story_photo;

	public Story toEntity() {
		return new Story(story_id, user, story_photo);
	}

}
