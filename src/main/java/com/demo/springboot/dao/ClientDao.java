package com.demo.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.springboot.entity.Client;

public interface ClientDao extends JpaRepository<Client, Integer> {
	@Query(value = "from Client where name like %:name%")
	public List<Client> findByName(@Param("name") String name);

}
