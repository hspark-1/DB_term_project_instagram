package com.example.instagram_project.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class Story_checkEmbedded implements Serializable {

	private int story_id;
	private String chekc_id;
	
}
