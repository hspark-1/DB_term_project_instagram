package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Feed_likes;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Feed_likesDto {

	private User user;
	private Long feed_id;

	public static Feed_likesDto createFeed_likesDto(Feed_likes feed_likes) {
		return new Feed_likesDto(feed_likes.getUser(), feed_likes.getFeed().getFeed_id());
	}
	
}
