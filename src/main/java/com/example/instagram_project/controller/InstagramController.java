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
import com.example.instagram_project.dto.Comment_likesDto;
import com.example.instagram_project.dto.DMDto;
import com.example.instagram_project.dto.FeedDto;
import com.example.instagram_project.dto.Feed_commentDto;
import com.example.instagram_project.dto.Feed_likesDto;
import com.example.instagram_project.dto.FollowDto;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.dto.Story_checkForm;
import com.example.instagram_project.dto.UserForm;
import com.example.instagram_project.entity.Feed;
import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;
import com.example.instagram_project.repository.FeedRepository;
import com.example.instagram_project.repository.FollowRepository;
import com.example.instagram_project.repository.UserRepository;
import com.example.instagram_project.repository.User_infoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstagramController {

	public String user_id = null;

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public FeedRepository feedRepository;
	@Autowired
	public User_infoRepository user_infoRepository;
	@Autowired
	public InstagramService instagramService;
	@Autowired
	public FollowRepository followRepository;

	@GetMapping("/login")
	public String loginIndex() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupIndex() {
		return "signup";
	}

	@PostMapping("/create/user")
	public String usercreate(UserForm form, RedirectAttributes rttr) {
		User user = form.toEntity();
		User user1 = userRepository.findById(user.getUser_id()).orElse(null);
		if(user1 != null) {
			rttr.addFlashAttribute("msg", "Duplicate ID! Please sign up again.");
			return "redirect:/signup";
		}

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

	@GetMapping("/feed/create")
	public String feedcreate(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		User user = userRepository.findById(user_id).orElse(null);
		log.info(user.toString());
		model.addAttribute("userEntity", user);

		return "feedcreate";
	}
	
	@PostMapping("/feed/createnew/{userId}")
	public String createArticle(@PathVariable String userId, FeedDto form) {
		log.info(form.toString());

		User user = userRepository.findById(userId).orElse(null);
		log.info(user.toString());
		Feed article = form.toEntity(user);
		log.info(article.toString());

		Feed saved = feedRepository.save(article);
		log.info(saved.toString());

		return "redirect:/mainpage";
	}

	@GetMapping("/direct/inbox")
	public String DMindex(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		// List<DMDto> dmDtos = instagramService.DMs(user_id);
		User user = userRepository.findById(user_id).orElse(null);
		List<User> userEntity = userRepository.findAll();
		model.addAttribute("DMentity", userEntity);
		model.addAttribute("user", user);

		log.info(userEntity.toString());

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

	@GetMapping("/feed/comments/{feedId}")
	public String showFeedComments(@PathVariable long feedId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<FeedDto> feedDto = instagramService.feedId(feedId);
		List<Feed_commentDto> feed_commentDtos = instagramService.feed_comments(feedId);

		model.addAttribute("feedDto", feedDto);
		model.addAttribute("feed_commentDtos", feed_commentDtos);

		User user = userRepository.findById(user_id).orElse(null);
		model.addAttribute("user", user);

		log.info(feedDto.toString());
		log.info(feed_commentDtos.toString());
		
		return "feed_comments";
	}

	@GetMapping("/feed/likes/{feed_id}")
	public String showFeedLikes(@PathVariable long feed_id, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		log.info("feedLikes = " + feed_id);
		List<Feed_likesDto> feed_likesDtos = instagramService.feed_likes(feed_id);
		log.info(feed_likesDtos.toString());
		model.addAttribute("feed_liksEntity", feed_likesDtos);

		return "feed_likes";
	}

	@GetMapping("/profile/{userId}")
	public String showprofile(@PathVariable String userId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		log.info(userId);
		User userEntity = userRepository.findById(userId).orElse(null);
		if(userEntity == null) {
			return "redirect:/mainpage";
		}
		User_info infoEntity = user_infoRepository.findById(userId).orElse(null);
		List<FeedDto> feedDtos = instagramService.feedsById(userId);
		int followcount = followRepository.findByFollowcount(userId);
		int followingcount = followRepository.findByUsercount(userId);

		log.info(feedDtos.toString());
		log.info(userEntity.toString());
		log.info(infoEntity.toString());
		log.info(followcount + "count");

		model.addAttribute("feedEntity", feedDtos);
		model.addAttribute("userEntity", userEntity);
		model.addAttribute("infoEntity", infoEntity);
		model.addAttribute("followcount", followcount);
		model.addAttribute("followingcount", followingcount);

		return "profiledetail";
	}

	@GetMapping("/profile/following/{userId}")
	public String showfollowing(@PathVariable String userId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<FollowDto> followDtos = instagramService.followings(userId);

		log.info(followDtos.toString());

		model.addAttribute("follow", followDtos);

		return "profilefollowing";
	}

	@GetMapping("/profile/follower/{userId}")
	public String showfollower(@PathVariable String userId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<FollowDto> followDtos = instagramService.followers(userId);

		log.info(followDtos.toString());

		model.addAttribute("follow", followDtos);

		return "profilefollower";
	}

	@GetMapping("/feed/comment/likes/{comment_id}")
	public String feedcommentlikes(@PathVariable Long comment_id, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		List<Comment_likesDto> comment_likesDtos = instagramService.comment_likes(comment_id);
		log.info(comment_likesDtos.toString());
		model.addAttribute("comment_likesEntity", comment_likesDtos);
		
		return "feedcommentlikes";
	}

	@GetMapping("/story/{userId}")
	public String showstory(@PathVariable String userId, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		log.info("id = " + userId);
		List<StoryForm> storyForms = instagramService.stories(userId);

		log.info(storyForms.toString());

		model.addAttribute("stories", storyForms);


		return "showstory";
	}

	@GetMapping("/story/check/{story_id}")
	public String showstorycheck(@PathVariable Long story_id, Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		log.info("id = " + story_id);
		List<Story_checkForm> story_checkForms = instagramService.checks(story_id);

		log.info(story_checkForms.toString());

		model.addAttribute("checkEntity", story_checkForms);

		return "showstorycheck";
	}

}
