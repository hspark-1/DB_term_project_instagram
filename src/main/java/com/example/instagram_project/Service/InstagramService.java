package com.example.instagram_project.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram_project.dto.StoryForm;
import com.example.instagram_project.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstagramService {

	@Autowired
	private StoryRepository storyRepository;

	public List<StoryForm> stories(String userId) {
		log.info(userId);
		return storyRepository.findByUserId(userId)
				.stream()
				.map(story -> StoryForm.createStoryDto(story))
				.collect(Collectors.toList());
	}

}
