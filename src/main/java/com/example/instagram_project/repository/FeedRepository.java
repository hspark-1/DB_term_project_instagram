package com.example.instagram_project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
	
	@Query(value =
			"select * from feed order by feed_id desc;",
			nativeQuery = true)
	ArrayList<Feed> findAll();
	
	@Query(value =
            "SELECT * FROM feed s WHERE s.user_id = ?1",
            nativeQuery = true)
	List<Feed> feedsById(String userId);

}
