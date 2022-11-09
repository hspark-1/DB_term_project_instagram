package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class StoryForm {
	
	private long story_id;
	private String user_id;
	private String story_photo;

	public Story toEntity() {
		return new Story(story_id, user_id, story_photo);
	}

}
