package com.example.instagram_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.Feed_comment;

public interface Feed_commentRepository extends JpaRepository<Feed_comment, Long> {

	@Query(value =
            "SELECT * FROM feed_comment s WHERE s.feed_id = ?1",
            nativeQuery = true)
	List<Feed_comment> findByFeedId(Long id);
	
}
