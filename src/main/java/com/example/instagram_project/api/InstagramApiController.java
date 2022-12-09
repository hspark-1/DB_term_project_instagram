package com.example.instagram_project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram_project.Service.InstagramService;
import com.example.instagram_project.annotation.RunningTime;
import com.example.instagram_project.dto.DMDto;
import com.example.instagram_project.dto.FeedDto;
import com.example.instagram_project.dto.Feed_commentDto;
import com.example.instagram_project.dto.Feed_likesDto;
import com.example.instagram_project.dto.FollowDto;
import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.dto.User_infoForm;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InstagramApiController {
	
	@Autowired
	private InstagramService instagramService;

	@PostMapping("/api/create/DM")
	public ResponseEntity<DMDto> create(@RequestBody DMDto dto) {
		log.info("value from json : " + dto.toString());
		DMDto createdDto = instagramService.create(dto);

		log.info(createdDto.toString());
		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PostMapping("/api/create/feed/{id}/comments")
	public ResponseEntity<Feed_commentDto> createFC(@PathVariable String id, @RequestBody Feed_commentDto dto) {
		log.info("value from json : " + dto.toString());
		Feed_commentDto createdDto = instagramService.createFC(dto, id);

		log.info(createdDto.toString());

		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PostMapping("/api/create/newfeed/{id}")
	public ResponseEntity<FeedDto> createFeed(@PathVariable String id, @RequestBody FeedDto dto) {
		log.info("value from json : " + dto.toString());
		FeedDto createdDto = instagramService.createFeed(dto, id);

		log.info(createdDto.toString());

		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PostMapping("/api/update/info/{userId}")
	public ResponseEntity<User_infoForm> updateInfo(@PathVariable String userId, @RequestBody User_infoForm dto) {
		log.info("value from json : " + dto.toString());
		User_infoForm createdDto = instagramService.updateInfo(dto, userId);

		log.info(createdDto.toString());

		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PostMapping("/api/create/newstory/{userId}")
	public ResponseEntity<StoryForm> createStory(@PathVariable String userId, @RequestBody StoryForm dto) {
		log.info("value from json : " + dto.toString());
		StoryForm createdDto = instagramService.createStory(dto, userId);

		log.info(createdDto.toString());

		return (createdDto != null) ? ResponseEntity.status(HttpStatus.OK).body(createdDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	// 댓글 삭제
	@RunningTime
	@DeleteMapping("/api/feed/delete/{id}")
	public ResponseEntity<FeedDto> delete(@PathVariable Long id) {
		// 서비스에게 위임
		FeedDto updatedDto = instagramService.delete(id);

		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

	// 스토리 삭제
	@RunningTime
	@DeleteMapping("/api/story/delete/{id}")
	public ResponseEntity<StoryForm> deletestory(@PathVariable Long id) {
		// 서비스에게 위임
		StoryForm updatedDto = instagramService.deletestory(id);

		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

	// 팔로우 생성
	@PostMapping("/api/follow/create/{followid}/{userid}")
	public ResponseEntity<FollowDto> createfollow(@PathVariable String followid, @PathVariable String userid) {
		log.info(followid + " + " + userid);
		// 서비스에게 위임
		FollowDto updatedDto = instagramService.createfollow(followid, userid);

		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

	// 팔로우 삭제
	@RunningTime
	@DeleteMapping("/api/follow/delete/{followid}/{userid}")
	public ResponseEntity<FollowDto> deletefollow(@PathVariable String followid, @PathVariable String userid) {
		log.info(followid + " + " + userid);
		// 서비스에게 위임
		FollowDto updatedDto = instagramService.deletefollow(followid, userid);

		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

	// 팔로우 삭제
	@RunningTime
	@DeleteMapping("/api/feed/likes/{feedId}/{userId}")
	public ResponseEntity<Feed_likesDto> deletelikes(@PathVariable long feedId, @PathVariable String userId) {
		log.info(feedId + " + " + userId);
		// 서비스에게 위임
		Feed_likesDto updatedDto = instagramService.deletelikes(feedId, userId);

		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

}
