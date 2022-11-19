package com.example.instagram_project.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.instagram_project.dto.DMDto;

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
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
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

	public static DM createDM(DMDto dto, User sender, User receiver) {
	
		if (dto.getComment() == null)
			throw new IllegalArgumentException("dm의 내용이 없습니다. 생성 실패!");

		// 엔티티 생성 및 반환
		return new DM(
			dto.getDM_id(),
			sender,
			receiver,
			dto.getComment(),
			dto.getTime()
		);
	}

}