package com.example.instagram_project.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.instagram_project.entity.Salary_info;

public interface Salary_infoRepository extends CrudRepository<Salary_info, Long> {
	
	@Override
	ArrayList<Salary_info> findAll();
	
	// @Query(value =
    //         "SELECT * " +
    //         "FROM salary_info " +
    //         "WHERE id = :#{#salary_info.id}" +
	// 		"AND name = :#{#salary_info.name}",
    //         nativeQuery = true)
	// Salary_info findbysalaryId(@Param("salary_info") Salary salary_id);

	// @Query(value =
    //         "SELECT * " +
    //         "FROM salary_info " +
    //         "WHERE id = :salaryId" +
	// 		"AND name = :salaryName",
    //         nativeQuery = true)
	// Salary_info findbysalaryId(@Param("salaryId") Long id, @Param("salaryName") String name);

	// @Query(value =
    //         "SELECT * " +
    //         "FROM salary_info " +
    //         "WHERE id = :salaryId " +
	// 		"AND name = :salaryName",
	// 		nativeQuery = true)
	// List<Salary_info> findByIdAndName(@Param("salaryId") Long id, @Param("salaryName") String name);

	// @Query("SELECT * FROM salary_info where salary_info.id = :salaryId AND salary_info.name = \":salaryName\"")
	// Salary_info findbysalaryId(@Param("salaryId") Long id, @Param("salaryName") String name);

	// @Query(value =
	// 		"SELECT * " +
	// 		"FROM salary_info " +
	// 		"WHERE ")
	// Salary_info findbySalaryId(Salary salary_id);

	// Salary_info findbysalaryId(Salary salary_id);

	// List<Map<Long, Object>> findByIdAndName(Long id, String name);

}
