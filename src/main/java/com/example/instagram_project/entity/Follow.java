package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.example.instagram_project.dto.FollowDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@IdClass(FollowIDclass.class)
public class Follow {

	@Id
	@Column(name = "user_id", nullable = false)
	private String user_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user1;

	@Id
	@Column(name = "follow_id", nullable = false)
	private String follow_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "follow_id")
	private User user2;

	public static Follow createFollow(String userid, String followid, User user1, User user2) {
		
		// 엔티티 생성 및 반환
		return new Follow(userid, user1, followid, user2);
	}
	
}