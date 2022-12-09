package com.example.instagram_project.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import com.example.instagram_project.dto.User_infoForm;
import com.example.instagram_project.entity.Comment_likes;
import com.example.instagram_project.entity.DM;
import com.example.instagram_project.entity.Feed;
import com.example.instagram_project.entity.Feed_comment;
import com.example.instagram_project.entity.Feed_likes;
import com.example.instagram_project.entity.Follow;
import com.example.instagram_project.entity.Story;
import com.example.instagram_project.entity.Story_check;
import com.example.instagram_project.entity.User;
import com.example.instagram_project.entity.User_info;
import com.example.instagram_project.repository.Comment_likesRepository;
import com.example.instagram_project.repository.DMRepository;
import com.example.instagram_project.repository.FeedRepository;
import com.example.instagram_project.repository.Feed_commentRepository;
import com.example.instagram_project.repository.Feed_likesRepository;
import com.example.instagram_project.repository.FollowRepository;
import com.example.instagram_project.repository.StoryRepository;
import com.example.instagram_project.repository.Story_chekcRepository;
import com.example.instagram_project.repository.UserRepository;
import com.example.instagram_project.repository.User_infoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstagramService {

	@Autowired
	private UserRepository userRepository;
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
	@Autowired
	private User_infoRepository user_infoRepository;

	public List<StoryForm> stories(String userId) {
		log.info(userId);
		return storyRepository.findByUserId(userId)
				.stream()
				.map(story -> StoryForm.createStoryDto(story))
				.collect(Collectors.toList());
	}

	public List<StoryForm> allstory() {
		return storyRepository.findAllUserId()
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

	public List<FeedDto> feedsById(String user_id) {
		return feedRepository.feedsById(user_id)
				.stream()
				.map(feed -> FeedDto.createFeedDto(feed))
				.collect(Collectors.toList());
	}

	public List<FeedDto> feedId(long feedId) {
		return feedRepository.findById(feedId)
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

	@Transactional
	public DMDto create(DMDto dto) {
		User sender = userRepository.findById(dto.getSender_id()).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
		User receiver = userRepository.findById(dto.getReceiver_id()).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
		// 댓글 엔티티 생성
		DM dm = DM.createDM(dto, sender, receiver);
		log.info(dm.toString());

		// 댓글 엔티티를 DB로 저장
		DM created = dmRepository.save(dm);

		//DTo로 변경하여 반환
		return DMDto.createDMDto(created);
	}

	@Transactional
	public Feed_commentDto createFC(Feed_commentDto dto, String id) {
		Feed feed = feedRepository.findById(dto.getFeed_id()).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
		log.info(feed.toString());
		log.info("user_id = " + id);
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 유저가 없습니다."));
		log.info(user.toString());
		// 댓글 엔티티 생성
		Feed_comment feed_comment = Feed_comment.createFeedC(dto, feed, user);
		log.info(feed_comment.toString());

		// 댓글 엔티티를 DB로 저장
		Feed_comment created = feed_commentRepository.save(feed_comment);

		//DTo로 변경하여 반환
		return Feed_commentDto.createFeed_commentDto(created);
	}

	public FeedDto createFeed(FeedDto dto, String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 생성 실패! 대상 유저가 없습니다."));
		log.info(user.toString());

		Feed feed = Feed.createFeed(dto, user);
		Feed created = feedRepository.save(feed);

		return FeedDto.createFeedDto(created);
	}

	public StoryForm createStory(StoryForm dto, String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
		log.info(user.toString());

		Story story = Story.createStory(dto, user);
		log.info(story.toString());
		Story created = storyRepository.save(story);
		log.info(created.toString());

		return StoryForm.createStoryForm(created);
	}

	@Transactional
	public User_infoForm updateInfo(User_infoForm dto, String userId) {
		// 댓글 조회 및 예외 발생
		User_info target = user_infoRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
		log.info(target.toString());
		// 댓글 수정
		target.patch(dto);
		log.info(dto.toString());
		// DB로 갱신
		User_info updated = user_infoRepository.save(target);

		log.info(updated.toString());
		// 댓글 엔티티를 DTO로 변환 및 반환
		return User_infoForm.createUser_infoForm(updated);
	}

	@Transactional
	public FeedDto delete(Long id) {
		// 댓글 조회(및 예외 발생)
		Feed target = feedRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
		List<Feed_comment> commenttargets = feed_commentRepository.findByFeedId(id);
		List<Feed_likes> feed_likes = feed_likesRepository.findByFeedId(id);
		for(int i=0; i<feed_likes.size(); i++) {
			Feed_likes like = feed_likes.get(i);
			feed_likesRepository.delete(like);
		}
		for(int i=0; i<commenttargets.size(); i++) {
			Feed_comment comment = commenttargets.get(i);
			List<Comment_likes> likes = comment_likesRepository.findByCommentId(comment.getComment_id());
			for(int j=0; j<likes.size(); j++) {
				Comment_likes like = likes.get(j);
				comment_likesRepository.delete(like);
			}
			feed_commentRepository.delete(comment);
		}

		// 댓글 DB에서 삭제
		feedRepository.delete(target);

		// 삭제 댓글을 DTO로 반환
		return FeedDto.createFeedDto(target);
	}

	@Transactional
	public StoryForm deletestory(Long id) {
		// 댓글 조회(및 예외 발생)
		Story target = storyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
		List<Story_check> commenttargets = story_chekcRepository.findByStoryId(id);
		for(int i=0; i<commenttargets.size(); i++) {
			Story_check comment = commenttargets.get(i);
			story_chekcRepository.delete(comment);
		}

		// 댓글 DB에서 삭제
		storyRepository.delete(target);

		// 삭제 댓글을 DTO로 반환
		return StoryForm.createStoryDto(target);
	}

	public FollowDto createfollow(String follow, String user) {
		User user1 = userRepository.findById(user).orElse(null);
		User user2 = userRepository.findById(follow).orElse(null);
		Follow target = Follow.createFollow(user, follow, user1, user2);

		Follow created = followRepository.save(target);
		log.info(created.toString());

		return FollowDto.createFollowDto(created);
	}

	@Transactional
	public FollowDto deletefollow(String follow, String user) {
		// 댓글 조회(및 예외 발생)
		Follow target = followRepository.findByuser_FollowId(user, follow);

		// 댓글 DB에서 삭제
		followRepository.delete(target);

		// 삭제 댓글을 DTO로 반환
		return FollowDto.createFollowDto(target);
	}

	public List<Feed_likesDto> feedlikesDto(String user_id) {
		return feed_likesRepository.findByUserId(user_id)
				.stream()
				.map(feed_likes -> Feed_likesDto.createFeed_likesDto(feed_likes))
				.collect(Collectors.toList());
	}

	@Transactional
	public Feed_likesDto deletelikes(long feedId, String userId) {
		// 댓글 조회(및 예외 발생)
		Feed_likes target = feed_likesRepository.findByFeedIdanduserId(feedId, userId);

		// 댓글 DB에서 삭제
		feed_likesRepository.delete(target);

		// 삭제 댓글을 DTO로 반환
		return Feed_likesDto.createFeed_likesDto(target);
	}

}
