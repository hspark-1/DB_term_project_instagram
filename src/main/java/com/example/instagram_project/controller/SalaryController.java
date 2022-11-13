package com.example.instagram_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.instagram_project.entity.Salary;
import com.example.instagram_project.entity.SalaryIDcalss;
import com.example.instagram_project.entity.Salary_info;
import com.example.instagram_project.repository.SalaryRepository;
import com.example.instagram_project.repository.Salary_infoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SalaryController {

	@Autowired
	public SalaryRepository salaryRepository;
	
	@Autowired
	public Salary_infoRepository salary_infoRepository;
	
	@GetMapping("/show")
	public String show(Model model) {
		List<Salary> salaryEntityList = salaryRepository.findAll();

		model.addAttribute("salaryList", salaryEntityList);

		return "show";
	}

	@GetMapping("/{id}/{name}")
	public String index(Model model, @PathVariable Long id, @PathVariable String name) {
		log.info("id = " + id);
		log.info("name = " + name);
		SalaryIDcalss salary_info_id = new SalaryIDcalss();
		salary_info_id = salary_info_id.toEntity(id, name);
		log.info("salary_info = " + salary_info_id.toString());

		// // 조회: 댓글 목록
		// List<Comment> comments = commentRepository.findByArticleId(articleId);

		// // 변환: 엔티티 -> DTO
		// List<CommentDto> dtos = new ArrayList<CommentDto>();
		// for (int i=0; i<comments.size(); i++) {
		// 	Comment c = comments.get(i);
		// 	CommentDto dto = CommentDto.createCommentDto(c);
		// 	dtos.add(dto);
		// }

		// // 반환
		// return dtos;
		Salary salary_id = salaryRepository.findByIdAndName(id, name);
		log.info(salary_id.toString());

		// Salary_info a = salary_infoRepository.findbysalaryId(salary_id);
		// Salary_infoIDclass abc = new Salary_infoIDclass();
		// List<Salary_info> infos = salary_infoRepository.findByIdAndName(id, name);
		// List<Salary_infoForm> dtos = new ArrayList<Salary_infoForm>();
		// for(int i=0; i<infos.size(); i++) {
		// 	Salary_info s = infos.get(i);
		// 	Salary_infoForm dto = Salary_infoForm.create(s);
		// 	dtos.add(dto);
		// }

		// Map<Long, Object> a = new HashMap<Long, Object>();
		// a.put(id, name);
		// Salary_info salary_info = (Salary_info) salary_infoRepository.findByIdAndName(id, name);

		log.info(salary_infoRepository.findAll().toString());
		List<Salary_info> dtos = salary_infoRepository.findAll();
		log.info(dtos.toString());
		// model.addAttribute("infoEntityList", dtos);

		Salary_info entity = salary_infoRepository.findById(id).orElse(null);
		
		model.addAttribute("infoEntityList", entity);

		
		// Salary_info salary_info = salary_infoRepository.findByIdAndName(id, name);
		// log.info(salary_info.toString());

		// model.addAttribute("info", salary_info);

		return "index";
	}

}
