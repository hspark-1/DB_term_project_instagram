package com.example.instagram_project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.User_info;

public interface User_infoRepository extends JpaRepository<User_info, String> {

	@Transactional
	@Modifying
	@Query(value = "insert into user_info values (?1, ?2, ?3, ?4)", nativeQuery = true)
	int save(String a, String b, long c, String d);
	
}
