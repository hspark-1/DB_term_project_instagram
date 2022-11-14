package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@IdClass(Comment_likesIDclass.class)
public class Comment_likes {

	@Id
	@Column(name = "comment_id", nullable = false)
	private Long comment_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "comment_id")
	private Feed_comment feed_comment;

	@Id
	@Column(name = "user_id")
	private String user_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

}
