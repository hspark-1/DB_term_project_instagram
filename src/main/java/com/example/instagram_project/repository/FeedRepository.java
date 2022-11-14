package com.example.instagram_project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagram_project.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
	
	@Override
	ArrayList<Feed> findAll();
	
}
