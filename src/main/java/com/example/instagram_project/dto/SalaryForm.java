package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Salary;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SalaryForm {

	private Long id;
	private String name;
	private String email;
	private String time;

	public Salary toEntity() {
		return new Salary(id, name, email, time);
	}
	
}
