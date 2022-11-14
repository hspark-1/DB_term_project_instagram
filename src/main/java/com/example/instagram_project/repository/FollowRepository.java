package com.example.instagram_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, String> {

	@Query(value =
            "SELECT * FROM follow s WHERE s.user_id = ?1",
            nativeQuery = true)
	List<Follow> findByUserId(String userId);
	
	@Query(value =
            "SELECT * FROM follow s WHERE s.follow_id = ?1",
            nativeQuery = true)
	List<Follow> findByFollowId(String userId);
	
}
