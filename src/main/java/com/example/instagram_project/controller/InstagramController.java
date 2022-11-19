package com.example.instagram_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.instagram_project.Service.InstagramService;
import com.example.instagram_project.dto.DMDto;
import com.example.instagram_project.dto.FeedDto;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.dto.UserForm;
import com.example.instagram_project.entity.User;
import com.example.instagram_project.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstagramController {

	public String user_id = null;

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public InstagramService instagramService;

	@GetMapping("/login")
	public String loginIndex() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupIndex() {
		return "signup";
	}

	@PostMapping("/create/user")
	public String usercreate(UserForm form) {
		User user = form.toEntity();
		User saved = userRepository.save(user);
		log.info(saved.toString());

		return "redirect:/login";
	}
	@PostMapping("/checkID")
	public String checkID(UserForm form, RedirectAttributes rttr) {
		User article = form.toEntity();
		log.info(article.toString());
		String id = article.getUser_id();
		User target = userRepository.findById(id).orElse(null);

		if(target!=null && article.checkID(target)) {
			user_id = target.getUser_id();
			return "redirect:/mainpage";
		}

		rttr.addFlashAttribute("msg", "Incorrect password. Check again.");

		return "redirect:/login";
	}

	@GetMapping("/mainpage")
	public String mainpageIndex(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<FeedDto> feedDtos = instagramService.feeds();
		List<StoryForm> storyForms = instagramService.allstory();

		model.addAttribute("feedEntity", feedDtos);
		model.addAttribute("storyEntity", storyForms);

		log.info(feedDtos.toString());
		log.info(storyForms.toString());

		return "mainpage";
	}

	@GetMapping("/direct/inbox")
	public String DMindex(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<DMDto> dmDtos = instagramService.DMs(user_id);
		User user = userRepository.findById(user_id).orElse(null);
		model.addAttribute("DMentity", dmDtos);
		model.addAttribute("user", user);

		log.info(dmDtos.toString());

		return "DM_list";
	}

	@GetMapping("/direct/{senderId}")
	public String DMdetailIndex(@PathVariable String senderId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}
		
		List<DMDto> dmDtos = instagramService.DMdetails(senderId, user_id);
		User user = userRepository.findById(user_id).orElse(null);
		User send_user = userRepository.findById(senderId).orElse(null);

		log.info(dmDtos.toString());
		log.info(user.toString());
		log.info(send_user.toString());

		model.addAttribute("DMdetail", dmDtos);
		model.addAttribute("user", user);
		model.addAttribute("senderId", send_user);

		return "DM_user";
	}

}
