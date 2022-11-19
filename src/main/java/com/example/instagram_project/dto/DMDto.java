package com.example.instagram_project.dto;

import java.sql.Timestamp;

import com.example.instagram_project.entity.DM;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class DMDto {

	private Long DM_id;
	private String sender_id;
	private String receiver_id;
	@JsonProperty("Comment")
	private String Comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp time;

	public static DMDto createDMDto(DM dm) {
		return new DMDto(dm.getDM_id(), dm.getUser_sender().getUser_id(), dm.getUser_receiver().getUser_id(), dm.getComment(), dm.getTime());
	}
	
}
