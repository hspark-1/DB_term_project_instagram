package com.example.instagram_project.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.example.instagram_project.dto.User_infoForm;
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

	public List<User> jdbcUserFindAll() throws SQLException, ParseException {
		Connection con = null;
		try {
			log.info(user_id);
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/practice";
			String user = "root", passwd = "12345";
			con = DriverManager.getConnection(url, user, passwd);
			log.info(con.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 데이터 읽어오기
		Statement stmt=null;
		ResultSet rs=null;
		List<User> userEntity = new ArrayList<User>();
		try {
			stmt=con.createStatement();
			String sql="select * from user u order by password";
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				String user_id = rs.getString(1);
				if (rs.wasNull()) {
					user_id = "null";
				}
				String password = rs.getString(2);
				if (rs.wasNull()) {
					password = "null";
				}
				String name = rs.getString(3);
				if (rs.wasNull()) {
					name = "null";
				}
				String phone_number = rs.getString(4);
				if (rs.wasNull()) {
					phone_number = "null";
				}
				String birthday = rs.getString(5);
				if (rs.wasNull()) {
					birthday = "null";
				}
				String profile_photo = rs.getString(6);
				if (rs.wasNull()) {
					profile_photo = "null";
				}
				Date date = java.sql.Date.valueOf(birthday);
				User user = UserForm.toEntity(user_id, password, name, phone_number, date, profile_photo);
				userEntity.add(user);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (stmt != null && !stmt.isClosed()) stmt.close();
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return userEntity;
	}

	public User jdbcUserFindById(String userId) throws SQLException, ParseException {
		Connection con = null;
		try {
			log.info(user_id);
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/practice";
			String user = "root", passwd = "12345";
			con = DriverManager.getConnection(url, user, passwd);
			log.info(con.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 데이터 읽어오기
		Statement stmt=null;
		ResultSet rs=null;
		User user = null;
		try {
			stmt=con.createStatement();
			String sql="select * from user u where u.user_id = \"" + userId + "\"";
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				String user_id = rs.getString(1);
				if (rs.wasNull()) {
					user_id = "null";
				}
				String password = rs.getString(2);
				if (rs.wasNull()) {
					password = "null";
				}
				String name = rs.getString(3);
				if (rs.wasNull()) {
					name = "null";
				}
				String phone_number = rs.getString(4);
				if (rs.wasNull()) {
					phone_number = "null";
				}
				String birthday = rs.getString(5);
				if (rs.wasNull()) {
					birthday = "null";
				}
				String profile_photo = rs.getString(6);
				if (rs.wasNull()) {
					profile_photo = "null";
				}
				Date date = java.sql.Date.valueOf(birthday);
				user = UserForm.toEntity(user_id, password, name, phone_number, date, profile_photo);
				log.info(user.toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (stmt != null && !stmt.isClosed()) stmt.close();
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
		try {
			if (con != null && !con.isClosed()) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User jdbcUserSave(User user1) throws SQLException, ParseException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/practice";
			String user = "root", passwd = "12345";
			con = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 데이터 삽입하기 
		PreparedStatement pstmt = null;
		try {
			String psql = "insert into user value (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, user1.getUser_id());
			pstmt.setString(2, user1.getPassword());
			pstmt.setString(3, user1.getName());
			pstmt.setString(4, user1.getPhone_number());
			pstmt.setDate(5, user1.getBirthday());
			pstmt.setString(6, user1.getProfile_photo());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

				
		try {
			if (con != null && !con.isClosed()) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user1;
	}

	public int jdbcUser_infoSave(String userId, String Email, int gender, String comment) throws SQLException, ParseException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/practice";
			String user = "root", passwd = "12345";
			con = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 데이터 삽입하기 
		PreparedStatement pstmt = null;
		try {
			String psql = "insert into user_info value (?, ?, ?, ?)";
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, userId);
			pstmt.setString(2, Email);
			pstmt.setInt(3, gender);
			pstmt.setString(4, comment);
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

				
		try {
			if (con != null && !con.isClosed()) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
	}

	@GetMapping("/login")
	public String loginIndex() {
		return "login";
	}

	@GetMapping("/logout")
	public String logOutForm() {
		user_id = null;
		return "redirect:/login";
	}

	@GetMapping("/signup")
	public String signupIndex() {
		return "signup";
	}

	@PostMapping("/create/user")
	public String usercreate(UserForm form, RedirectAttributes rttr) throws SQLException, ParseException {
		User user = form.toEntity();
		User user1 = jdbcUserFindById(user.getUser_id());
		if(user1 != null) {
			rttr.addFlashAttribute("msg", "Duplicate ID! Please sign up again.");
			return "redirect:/signup";
		}

		User saved = jdbcUserSave(user);
		User_infoForm infoForm = User_infoForm.createUser_infoForm(user);
		log.info(infoForm.toString());
		User_info savedInfo = User_info.createInfo(infoForm);
		log.info(savedInfo.toString());
		int i = jdbcUser_infoSave(savedInfo.getUser().getUser_id(), savedInfo.getEmail(), savedInfo.getGender(), savedInfo.getIntro_comment());
		
		log.info("i = " + i);
		log.info(saved.toString());

		return "redirect:/login";
	}

	@PostMapping("/profile/update")
	public String updatePhoto(UserForm form) throws SQLException, ParseException {
		log.info(form.toString());
		User user = form.toEntity();
		log.info(user.toString());
		User target = jdbcUserFindById(user.getUser_id());
		log.info(target.toString());
		
		if(target!=null) {
			userRepository.save(user);
		}

		return "redirect:/mainpage";
	}

	@PostMapping("/checkID")
	public String checkID(UserForm form, RedirectAttributes rttr) {
		User article = form.toEntity();
		log.info(article.toString());
		String id = article.getUser_id();
		log.info(id);
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
		User user = userRepository.findById(user_id).orElse(null);

		model.addAttribute("feedEntity", feedDtos);
		model.addAttribute("storyEntity", storyForms);
		model.addAttribute("userEntity", user);

		log.info(feedDtos.toString());
		log.info(storyForms.toString());

		return "mainpage";
	}

	@GetMapping("/profile/photo/edit")
	public String profileEdit(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		User user = userRepository.findById(user_id).orElse(null);
		log.info(user.toString());
		model.addAttribute("userEntity", user);

		return "profilephotoEdit";
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
	public String DMindex(Model model) throws SQLException, ParseException {
		if(user_id == null) {
			return "redirect:/login";
		}

		// List<DMDto> dmDtos = instagramService.DMs(user_id);
		User user = userRepository.findById(user_id).orElse(null);
		List<User> userEntity = jdbcUserFindAll();
		model.addAttribute("DMentity", userEntity);
		model.addAttribute("userEntity", user);

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
	public String showprofile(@PathVariable String userId, Model model, RedirectAttributes rttr) {
		if(user_id == null) {
			return "redirect:/login";
		}
		User finduser = userRepository.findById(userId).orElse(null);
		if(finduser == null) {
			rttr.addFlashAttribute("msg", "사용자를 찾을 수 없습니다.");
			return "redirect:/mainpage";
		}

		log.info(userId);
		User userEntity = userRepository.findById(userId).orElse(null);
		User userEntity1 = userRepository.findById(user_id).orElse(null);
		if(userEntity == null) {
			return "redirect:/mainpage";
		}
		User_info infoEntity = user_infoRepository.findById(userId).orElse(null);
		int follow = followRepository.findByuser_followId(user_id, userId);
		List<FeedDto> feedDtos = instagramService.feedsById(userId);
		int followcount = followRepository.findByFollowcount(userId);
		int followingcount = followRepository.findByUsercount(userId);

		log.info(feedDtos.toString());
		log.info(userEntity.toString());
		log.info(infoEntity.toString());
		log.info(followcount + "count");

		model.addAttribute("feedEntity", feedDtos);
		model.addAttribute("userEntity", userEntity);
		model.addAttribute("loginEntity", userEntity1);
		model.addAttribute("infoEntity", infoEntity);
		model.addAttribute("followcount", followcount);
		model.addAttribute("followingcount", followingcount);
		model.addAttribute("followbool", follow);

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

	@GetMapping("/profile/edit/{userId}")
	public String editprofile(@PathVariable String userId, Model model, RedirectAttributes rttr) {
		if(user_id == null) {
			return "redirect:/login";
		}
		if(!(userId.equals(user_id))) {
			rttr.addFlashAttribute("msg", "편집 권한이 없습니다.");
			return "redirect:/profile/" + userId;
		}

		User userEntity = userRepository.findById(userId).orElse(null);
		User_info userInfoEntity = user_infoRepository.findById(userId).orElse(null);

		model.addAttribute("userEntity", userEntity);
		model.addAttribute("userInfoEntity", userInfoEntity);

		return "profileedit";
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
		User user = userRepository.findById(user_id).orElse(null);
		List<StoryForm> storyForms = instagramService.stories(userId);

		log.info(storyForms.toString());

		model.addAttribute("countstory", storyForms.size());
		model.addAttribute("userEntity", user);
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

	@GetMapping("/newstory")
	public String newstoryIndex(Model model) {
		if(user_id == null) {
			return "redirect:/login";
		}

		User user = userRepository.findById(user_id).orElse(null);
		model.addAttribute("userEntity", user);

		return "newstory";
	}

}
