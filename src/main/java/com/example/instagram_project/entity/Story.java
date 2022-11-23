package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.instagram_project.dto.StoryForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Story {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private long story_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String story_photo;

	public static Story createStory(StoryForm dto, User user) {
		// 예외 처리
		if (dto.getStory_id() != 0)
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

		// 엔티티 생성 및 반환
		return new Story(dto.getStory_id(), user, dto.getStory_photo());
	}
	
}
