package com.example.instagram_project.dto;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.Salary_info;

public class Salary_infoForm {

	// private Salary salary_id;

	private Long info_id;
	// private String name;
	private long salary;
	private Salary salary_id;

	public Salary_info toEntity() {
		return new Salary_info(info_id, salary, salary_id);
	}

	public static Salary_infoForm create(Salary_info s) {
		Salary_infoForm a = new Salary_infoForm();
		a.salary = s.getSalary();
		a.info_id = s.getInfo_id();
		return a;
	}

}
