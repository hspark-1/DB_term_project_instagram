package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Follow;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FollowDto {

	private User user1;
	private User user2;

	public static FollowDto createFollowDto(Follow follow) {
		return new FollowDto(follow.getUser1(), follow.getUser2());
	}
	
}
