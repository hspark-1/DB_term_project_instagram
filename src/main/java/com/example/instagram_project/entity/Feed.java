package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@GeneratedValue
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

}
