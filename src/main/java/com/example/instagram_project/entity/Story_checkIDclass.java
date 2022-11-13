package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Story_checkIDclass implements Serializable {
	
	private long id;
	private String user_id;

}
