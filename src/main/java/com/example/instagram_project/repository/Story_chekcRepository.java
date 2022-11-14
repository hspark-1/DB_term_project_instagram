package com.example.instagram_project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.Story_check;

public interface Story_chekcRepository extends CrudRepository<Story_check, Long> {

	@Query(value =
            "SELECT * FROM story_check s WHERE s.story_id = ?1",
            nativeQuery = true)
	List<Story_check> findByStoryId(Long id);

	@Override
	ArrayList<Story_check> findAll();

}
