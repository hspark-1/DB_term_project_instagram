package com.example.instagram_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram_project.entity.DM;

public interface DMRepository extends JpaRepository<DM, Long> {

	@Query(value =
            "SELECT * FROM DM s WHERE (s.sender_id = ?1 OR s.sender_id = ?2) AND (s.receiver_id = ?1 OR s.receiver_id = ?2) ORDER BY time asc",
            nativeQuery = true)
	List<DM> findBySenderId_ReceiverId(String senderId, String receiverId);

	@Query(value =
            "SELECT * FROM DM s WHERE s.receiver_id = ?1 GROUP BY sender_id ORDER BY time desc",
            nativeQuery = true)
	List<DM> findByUserId(String userId);

}
