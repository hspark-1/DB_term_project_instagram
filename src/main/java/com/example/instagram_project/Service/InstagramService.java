package com.example.instagram_project.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram_project.dto.Comment_likesDto;
import com.example.instagram_project.dto.DMDto;
import com.example.instagram_project.dto.FeedDto;
import com.example.instagram_project.dto.Feed_commentDto;
import com.example.instagram_project.dto.Feed_likesDto;
import com.example.instagram_project.dto.FollowDto;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.dto.Story_checkForm;
import com.example.instagram_project.repository.Comment_likesRepository;
import com.example.instagram_project.repository.DMRepository;
import com.example.instagram_project.repository.FeedRepository;
import com.example.instagram_project.repository.Feed_commentRepository;
import com.example.instagram_project.repository.Feed_likesRepository;
import com.example.instagram_project.repository.FollowRepository;
import com.example.instagram_project.repository.StoryRepository;
import com.example.instagram_project.repository.Story_chekcRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstagramService {

	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private Story_chekcRepository story_chekcRepository;
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private DMRepository dmRepository;
	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private Feed_likesRepository feed_likesRepository;
	@Autowired
	private Feed_commentRepository feed_commentRepository;
	@Autowired
	private Comment_likesRepository comment_likesRepository;

	public List<StoryForm> stories(String userId) {
		log.info(userId);
		return storyRepository.findByUserId(userId)
				.stream()
				.map(story -> StoryForm.createStoryDto(story))
				.collect(Collectors.toList());
	}
	
	public List<Story_checkForm> checks(Long id) {
		log.info("id = " + id);
		return story_chekcRepository.findByStoryId(id)
				.stream()
				.map(story_check -> Story_checkForm.createCheckDto(story_check))
				.collect(Collectors.toList());
	}

	public List<FollowDto> followings(String userId) {
		return followRepository.findByUserId(userId)
				.stream()
				.map(follow -> FollowDto.createFollowDto(follow))
				.collect(Collectors.toList());
	}

	public List<FollowDto> followers(String userId) {
		return followRepository.findByFollowId(userId)
				.stream()
				.map(follow -> FollowDto.createFollowDto(follow))
				.collect(Collectors.toList());
	}

	public List<DMDto> DMdetails(String senderId, String receiverId) {
		return dmRepository.findBySenderId_ReceiverId(senderId, receiverId)
				.stream()
				.map(dm -> DMDto.createDMDto(dm))
				.collect(Collectors.toList());
	}

	public List<DMDto> DMs(String userId) {
		return dmRepository.findByUserId(userId)
				.stream()
				.map(dm -> DMDto.createDMDto(dm))
				.collect(Collectors.toList());
	}

	public List<FeedDto> feeds() {
		return feedRepository.findAll()
				.stream()
				.map(feed -> FeedDto.createFeedDto(feed))
				.collect(Collectors.toList());
	}

	public List<Feed_likesDto> feed_likes(Long id) {
		return feed_likesRepository.findByFeedId(id)
				.stream()
				.map(feed_likes -> Feed_likesDto.createFeed_likesDto(feed_likes))
				.collect(Collectors.toList());
	}

	public List<Feed_commentDto> feed_comments(Long id) {
		return feed_commentRepository.findByFeedId(id)
				.stream()
				.map(feed_comment -> Feed_commentDto.createFeed_commentDto(feed_comment))
				.collect(Collectors.toList());
	}

	public List<Comment_likesDto> comment_likes(Long id) {
		return comment_likesRepository.findByCommentId(id)
				.stream()
				.map(comment_likes -> Comment_likesDto.createComment_likesDto(comment_likes))
				.collect(Collectors.toList());
	}

}
