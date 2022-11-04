package com.example.instagram_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.repository.SalaryRepository;

@Controller
public class SalaryController {

	@Autowired
	public SalaryRepository salaryRepository;
	
	@GetMapping("/show")
	public String show(Model model) {
		List<Salary> salaryEntityList = salaryRepository.findAll();

		model.addAttribute("salaryList", salaryEntityList);

		return "show";
	}

}
