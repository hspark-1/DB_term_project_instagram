package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Comment_likes;
import com.example.instagram_project.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment_likesDto {
	
	private Long comment_id;
	private User user1;
	
	public static Comment_likesDto createComment_likesDto(Comment_likes comment_likes) {
		return new Comment_likesDto(comment_likes.getFeed_comment().getComment_id(), comment_likes.getUser());
	}

}
