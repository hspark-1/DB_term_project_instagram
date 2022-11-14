package com.example.instagram_project.entity;

import java.sql.Timestamp;

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
@Getter
@ToString
public class DM {
	
	@Id
	@GeneratedValue
	private Long DM_id;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User user_sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User user_receiver;

	@Column
	private String Comment;

	@Column
	private Timestamp time;

}