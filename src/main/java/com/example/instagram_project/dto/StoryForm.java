package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class StoryForm {
	
	private long story_id;
	private User user;
	private String story_photo;

	public static StoryForm createStoryDto(Story story) {
		return new StoryForm(
			story.getStory_id(),
			story.getUser(),
			story.getStory_photo()
		);
	}

	public static StoryForm createStoryForm(Story story) {
		return new StoryForm(story.getStory_id(), story.getUser(), story.getStory_photo());
	}

}
