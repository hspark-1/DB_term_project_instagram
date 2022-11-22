package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.instagram_project.dto.FeedDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Feed {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long feed_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String feed_photo;

	@Column
	private String feed_comment;

	@Column
	private Long likes;

	public static Feed createFeed(FeedDto dto, User user) {
		// 예외 처리
		if (dto.getFeed_id() != null)
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

		// 엔티티 생성 및 반환
		return new Feed(dto.getFeed_id(), user, dto.getFeed_photo(), dto.getFeed_comment(), dto.getLikes());
	}

}
