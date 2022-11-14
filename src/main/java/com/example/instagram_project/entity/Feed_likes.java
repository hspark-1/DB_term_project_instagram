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
@IdClass(Feed_likesIDclass.class)
public class Feed_likes {

	@Id
	@Column(name = "user_id", nullable = false)
	private String user_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@Column(name = "feed_id")
	private Long feed_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "feed_id")
	private Feed feed;
	
}
