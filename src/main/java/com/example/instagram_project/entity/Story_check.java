package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@IdClass(Story_chekcIDclass.class)
public class Story_check {

	@Id
	@GeneratedValue
	private long story_id;

	@Id
	private String check_id;

	@Column
	private int likes;
	
}
