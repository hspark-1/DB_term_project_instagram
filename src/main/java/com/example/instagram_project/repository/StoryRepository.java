package com.example.instagram_project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.instagram_project.entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

	// @Query("select m from Story m where m.user_id = :userId")
	// List<Story> findByUserId(@Param("userId") String id);

	// @Query(value =
    //         "SELECT * " +
    //         "FROM story s " +
    //         "WHERE s.user_id = :userId",
    //         nativeQuery = true)
	// List<Story> findByUser_UserId(@Param("userId") String userId);

	@Query(value =
            "SELECT * FROM Story s WHERE s.user_id = ?1",
            nativeQuery = true)
	List<Story> findByUserId(String userId);

	@Override
	ArrayList<Story> findAll();
	
}
