package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story;

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
	private String user_id;
	private String story_photo;

	public static StoryForm createStoryDto(Story story) {
		return new StoryForm(
			story.getStory_id(),
			story.getUser().getUser_id(),
			story.getStory_photo()
		);
	}

}
