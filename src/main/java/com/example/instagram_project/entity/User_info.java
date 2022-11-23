package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.example.instagram_project.dto.User_infoForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User_info {

	@Id
	@Column(name = "user_id", nullable = false)
	private String id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String intro_comment;

	@Column
	private String email;

	@Column
	private int gender;

	public static User_info createInfo(User_infoForm dto) {
		// 예외 처리
		if (dto.getUser() == null)
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

		// 엔티티 생성 및 반환
		return new User_info(dto.getUser().getUser_id(), dto.getUser(), dto.getIntro_comment(), dto.getEmail(), dto.getGender());
	}
	
	public void patch(User_infoForm dto) {
		// // 예외 발생i

		if (dto.getIntro_comment() != null)
			this.intro_comment = dto.getIntro_comment();

		// 객체를 갱신
		if (dto.getEmail() != null)
			this.email = dto.getEmail();

		if (dto.getGender() != 3)
			this.gender = dto.getGender();
	}
	
}
