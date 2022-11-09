package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Story_chekcIDclass implements Serializable {
	
	private long story_id;
	private String check_id;

}
