package com.example.instagram_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.instagram_project.Service.InstagramService;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;
import com.example.instagram_project.repository.UserRepository;
import com.example.instagram_project.repository.User_infoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstagramController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private User_infoRepository user_infoRepository;
	@Autowired
	private InstagramService instagramService;

	@GetMapping("/user")
	public String userIndex(Model model) {
		List<User> dtos = userRepository.findAll();
		log.info(dtos.toString());

		model.addAttribute("userEntity", dtos);

		return "user";
	}

	@GetMapping("/{id}")
	public String infoIndex(@PathVariable String id, Model model) {
		User_info infoEntity = user_infoRepository.findById(id).orElse(null);
		log.info(infoEntity.toString());

		model.addAttribute("infoEntity", infoEntity);

		return "info";
	}

	@GetMapping("/story/{id}")
	public String showStory(@PathVariable String id, Model model) {
		log.info("id = " + id);
		List<StoryForm> storyForms = instagramService.stories(id);

		log.info(storyForms.toString());

		model.addAttribute("stories", storyForms);

		return "story";
	}

}
