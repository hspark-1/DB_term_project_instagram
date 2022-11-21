package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Feed_comment;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Feed_commentDto {
	
	private Long comment_id;
	private Long feed_id;
	private User user;
	private String comment;
	private Long likes;

	public static Feed_commentDto createFeed_commentDto(Feed_comment feed_comment) {
		return new Feed_commentDto(feed_comment.getComment_id(), feed_comment.getFeed().getFeed_id(), feed_comment.getUser(), feed_comment.getComment(), feed_comment.getLikes());
	}

}
