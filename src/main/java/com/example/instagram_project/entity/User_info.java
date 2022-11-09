package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User_info {

	@Id
	// @OneToOne
	// @JoinColumn(name="user_id")
	private String user_id;

	@Column
	private String intro_comment;

	@Column
	private String email;

	@Column
	private int gender;
	
}
