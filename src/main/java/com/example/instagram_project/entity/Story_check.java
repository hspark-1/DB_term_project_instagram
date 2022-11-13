package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@IdClass(Story_checkIDclass.class)
public class Story_check {

	@Id
	@Column(name = "story_id", nullable = false)
	@GeneratedValue
	private long id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "story_id")
	private Story story;

	@Id
	@Column(name = "check_id", nullable = false)
	private String user_id;

	@ManyToOne
	@MapsId
	@JoinColumn(name = "check_id")
	private User user;

	@Column
	private int likes;
	
}
