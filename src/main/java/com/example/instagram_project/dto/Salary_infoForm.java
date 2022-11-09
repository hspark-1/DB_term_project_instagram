package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.SalaryIDcalss;
import com.example.instagram_project.entity.Salary_info;

public class Salary_infoForm {

	private SalaryIDcalss id;
	private Salary salar;
	private long salary;

	public Salary_info toEntity() {
		return new Salary_info(id, salar, salary);
	}
	
}
