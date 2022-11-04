package com.example.instagram_project.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.Salary;

public interface SalaryRepository extends CrudRepository<Salary, Long> {
	
	@Override
	ArrayList<Salary> findAll();
	
}
