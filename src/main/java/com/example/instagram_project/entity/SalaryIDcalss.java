package com.example.instagram_project.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SalaryIDcalss implements Serializable {
	
	private Long id;
	private String name;

	public SalaryIDcalss toEntity(Long id, String name) {
		SalaryIDcalss salary_info_id = new SalaryIDcalss();
		salary_info_id.id = id;
		salary_info_id.name = name;
		return salary_info_id;
	}

}
