package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.instagram_project.dto.Feed_commentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Feed_comment {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long comment_id;

	@ManyToOne
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String comment;

	@Column
	private Long likes;

	public static Feed_comment createFeedC(Feed_commentDto dto, Feed feed, User user) {
		// 예외 처리
		if (dto.getComment_id() != null)
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
		if (dto.getFeed_id() != feed.getFeed_id())
			throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

		// 엔티티 생성 및 반환
		return new Feed_comment(dto.getComment_id(), feed, user, dto.getComment(), dto.getLikes());
	}

}
