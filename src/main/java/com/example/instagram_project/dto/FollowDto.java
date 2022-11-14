package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FollowDto {

	private String user_id;
	private String follow_id;

	public static FollowDto createFollowDto(Follow follow) {
		return new FollowDto(follow.getUser1().getUser_id(), follow.getUser2().getUser_id());
	}
	
}
