package com.demo.springboot.service;

import java.util.List;

import com.demo.springboot.entity.Company;

public interface CompanyService {
	public Company findById(int id);

	public List<Company> findByName(String name);

	public Company create(Company company) throws Exception;

	public Company update(Company company) throws Exception;

	public Boolean deleteById(int id) throws Exception;

}
