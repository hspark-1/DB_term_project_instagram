package com.example.instagram_project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	
}
