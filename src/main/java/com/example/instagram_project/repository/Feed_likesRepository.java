package com.example.instagram_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.Feed_likes;

public interface Feed_likesRepository extends JpaRepository<Feed_likes, Long> {

	@Query(value =
            "SELECT * FROM feed_likes s WHERE s.feed_id = ?1",
            nativeQuery = true)
	List<Feed_likes> findByFeedId(Long id);
	
}
