package com.example.instagram_project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.SalaryIDcalss;
import com.example.instagram_project.entity.Salary_info;

public interface Salary_infoRepository extends CrudRepository<Salary_info, Salary> {
	
	// Salary_info findBysalary_info_id(SalaryIDcalss salaryIDcalss);

}
