package com.example.instagram_project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.Story;

public interface StoryRepository extends CrudRepository<Story, Long> {
	
}
