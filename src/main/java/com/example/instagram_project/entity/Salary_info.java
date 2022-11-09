package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@IdClass(Salary_infoIDclass.class)
public class Salary_info {
	
	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "id", referencedColumnName = "id"),
		@JoinColumn(name = "name", referencedColumnName = "name")
	})
	private Salary salary_id;

	@Column
	private long salary;
	
}
