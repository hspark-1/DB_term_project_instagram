package com.example.instagram_project.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User {

	@Id
	private String user_id;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String phone_number;

	@Column
	private Date birthday;

	@Column
	private String profile_photo;
	
	public boolean checkID(User target) {
		String pw = target.password;

		if(this.password.equals(pw)){
			return true;
		}

		return false;
	}

}
