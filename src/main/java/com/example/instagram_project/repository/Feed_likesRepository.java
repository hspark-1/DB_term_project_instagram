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
	
	@Query(value =
            "SELECT * FROM feed_likes s WHERE s.user_id = ?1 order by s.feed_id",
            nativeQuery = true)
	List<Feed_likes> findByUserId(String id);
	
	@Query(value =
            "SELECT * FROM feed_likes s WHERE s.feed_id = ?1 and s.user_id = ?2",
            nativeQuery = true)
	Feed_likes findByFeedIdanduserId(long feedid, String id);
	
	@Query(value =
            "SELECT feed_id, count(*) FROM feed_likes s group by feed_id having s.feed_id=?1 order by feed_id",
            nativeQuery = true)
	int findByuserId(long feedId);
	
	@Query(value =
            "SELECT feed_id, count(*) FROM feed_likes s group by feed_id order by feed_id",
            nativeQuery = true)
	int findByAllFeedlikes();
	
}
