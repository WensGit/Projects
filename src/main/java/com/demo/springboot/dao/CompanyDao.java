package com.demo.springboot.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.springboot.entity.Company;

public interface CompanyDao extends JpaRepository<Company, Integer> {
	@Query(value = "from Company where name like %:name%")
	public List<Company> findByName(@Param("name") String name);

	@Modifying(clearAutomatically = true)
	@Query("update Company set name =:name,address=:address,updatedBy=:updatedBy,updatedAt=:updatedAt where id =:id")
	public int update(@Param("id") int id, @Param("name") String name, @Param("address") String address,
			@Param("updatedBy") String updatedBy, @Param("updatedAt") Date updatedAt);

}
