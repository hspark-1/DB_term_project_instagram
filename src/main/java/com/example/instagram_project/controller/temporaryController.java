package com.example.instagram_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.instagram_project.Service.InstagramService;
import com.example.instagram_project.dto.Comment_likesDto;
import com.example.instagram_project.dto.DMDto;
import com.example.instagram_project.dto.FeedDto;
import com.example.instagram_project.dto.Feed_commentDto;
import com.example.instagram_project.dto.Feed_likesDto;
import com.example.instagram_project.dto.FollowDto;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.dto.Story_checkForm;
import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;
import com.example.instagram_project.repository.UserRepository;
import com.example.instagram_project.repository.User_infoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class temporaryController {

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

	@GetMapping("/user/{id}")
	public String infoIndex(@PathVariable String id, Model model) {
		User_info infoEntity = user_infoRepository.findById(id).orElse(null);
		log.info(infoEntity.toString());

		model.addAttribute("infoEntity", infoEntity);

		return "info";
	}

	// @GetMapping("/story/{id}")
	// public String showStory(@PathVariable String id, Model model) {
	// 	log.info("id = " + id);
	// 	List<StoryForm> storyForms = instagramService.stories(id);

	// 	log.info(storyForms.toString());

	// 	model.addAttribute("stories", storyForms);

	// 	return "story";
	// }

	// @GetMapping("/storycheck/{storyid}")
	// public String storyCheckIndex(@PathVariable Long storyid, Model model) {
	// 	log.info("id = " + storyid);
	// 	List<Story_checkForm> story_checkForms = instagramService.checks(storyid);

	// 	log.info(story_checkForms.toString());

	// 	model.addAttribute("checkEntity", story_checkForms);

	// 	return "story_check";
	// }

	@GetMapping("/following/{userId}")
	public String followingCheckIndex(@PathVariable String userId, Model model) {
		List<FollowDto> followDtos = instagramService.followings(userId);

		log.info(followDtos.toString());

		model.addAttribute("follow", followDtos);

		return "follow";
	}

	@GetMapping("/follower/{userId}")
	public String followerCheckIndex(@PathVariable String userId, Model model) {
		List<FollowDto> followDtos = instagramService.followers(userId);

		log.info(followDtos.toString());

		model.addAttribute("follow", followDtos);

		return "follower";
	}

	@GetMapping("/DM/{senderId}/{receiverId}")
	public String DMdetailIndex(@PathVariable String senderId, @PathVariable String receiverId, Model model) {
		List<DMDto> dmDtos = instagramService.DMdetails(senderId, receiverId);

		log.info(dmDtos.toString());

		model.addAttribute("DMdetail", dmDtos);

		return "DMdetail";
	}

	@GetMapping("/DM/{userId}")
	public String DMIndex(@PathVariable String userId, Model model) {
		List<DMDto> dmDtos = instagramService.DMs(userId);
		log.info(dmDtos.toString());
		model.addAttribute("DMentity", dmDtos);

		return "DM";
	}

	@GetMapping("/Feed")
	public String FeedIndex(Model model) {
		List<FeedDto> feedDtos = instagramService.feeds();
		log.info(feedDtos.toString());
		model.addAttribute("feedEntity", feedDtos);

		return "feed";
	}

	@GetMapping("/Feedlikes/{id}")
	public String FeedlikesIndex(@PathVariable Long id, Model model) {
		List<Feed_likesDto> feed_likesDtos = instagramService.feed_likes(id);
		log.info(feed_likesDtos.toString());
		model.addAttribute("feed_liksEntity", feed_likesDtos);

		return "feedlikes";
	}

	@GetMapping("/Feedcomments/{id}")
	public String FeedcommentIndex(@PathVariable Long id, Model model) {
		List<Feed_commentDto> feed_commentDtos = instagramService.feed_comments(id);
		log.info(feed_commentDtos.toString());
		model.addAttribute("feed_commentsEntity", feed_commentDtos);

		return "feedcomment";
	}

	@GetMapping("/Feedcommentslikes/{id}")
	public String FeedcommentslikesIndex(@PathVariable Long id, Model model) {
		List<Comment_likesDto> comment_likesDtos = instagramService.comment_likes(id);
		log.info(comment_likesDtos.toString());
		model.addAttribute("comment_likesEntity", comment_likesDtos);

		return "commentlikes";
	}

}
