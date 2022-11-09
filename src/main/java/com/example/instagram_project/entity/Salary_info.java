package com.example.instagram_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
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
public class Salary_info {

	@Id
	@Column(name = "salary_info_id")
	private SalaryIDcalss id;

	@OneToOne
	@MapsId("salary_info_id")
	@JoinColumns({
		@JoinColumn(name="id", referencedColumnName = "id"),
		@JoinColumn(name = "name", referencedColumnName = "name")
	})
	private Salary salary_info_id;

	@Column
	private long salary;
	
}
