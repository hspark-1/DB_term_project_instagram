package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Story_check;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Story_checkForm {
	
	private long story_id;
	private User user1;
	private int likes;
	
	public static Story_checkForm createCheckDto(Story_check story_check) {
		return new Story_checkForm(
			story_check.getStory().getStory_id(),
			story_check.getUser(),
			story_check.getLikes()
		);
	}


}
