package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.Salary_info;

public class Salary_infoForm {

	private Salary salary_id;
	private long salary;

	public Salary_info toEntity() {
		return new Salary_info(salary_id, salary);
	}
	
}
