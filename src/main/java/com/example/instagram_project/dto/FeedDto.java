package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Feed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FeedDto {
	
	private Long feed_id;
	private String user_id;
	private String feed_photo;
	private String feed_comment;
	private Long likes;

	public static FeedDto createFeedDto(Feed feed) {
		return new FeedDto(feed.getFeed_id(), feed.getUser().getUser_id(), feed.getFeed_photo(), feed.getFeed_comment(), feed.getLikes());
	}

}