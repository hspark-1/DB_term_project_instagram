package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.Salary_info;

public class Salary_infoForm {

	private Salary salary_id;
	private long salary;

	public Salary_info toEntity() {
		return new Salary_info(salary_id, salary);
	}

	public static Salary_infoForm create(Salary_info s) {
		Salary_infoForm a = new Salary_infoForm();
		a.salary = s.getSalary();
		a.salary_id = s.getSalary_id();
		return a;
	}

}
