package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@IdClass(SalaryIDcalss.class)
public class Salary {

	@Id
	@GeneratedValue
	private Long id;

	@Id
	private String name;

	@Column
	private String email;

	@Column
	private String time;
	
}