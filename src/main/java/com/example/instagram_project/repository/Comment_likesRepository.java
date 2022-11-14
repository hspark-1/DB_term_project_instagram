package com.example.instagram_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.Comment_likes;

public interface Comment_likesRepository extends JpaRepository<Comment_likes, Long> {

	@Query(value =
            "SELECT * FROM comment_likes s WHERE s.comment_id = ?1",
            nativeQuery = true)
	List<Comment_likes> findByCommentId(Long id);
	
}
