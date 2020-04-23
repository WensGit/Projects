package com.demo.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.entity.Company;
import com.demo.springboot.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;

	/**
	 * 依ID 查詢公司資料
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
	@GetMapping(value = "/searchById")
	@ResponseBody
	public Company getCompany(@RequestParam(value = "id", required = true) int id) {
		Company company = companyService.findById(id);
		return company;
	}

	/**
	 * 依名稱模糊查詢公司資料
	 * 
	 * @param name
	 * @return
	 */
	@PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
	@GetMapping(value = "/searchByName")
	@ResponseBody
	public List<Company> getCompanysByName(@RequestParam(value = "name", required = false) String name) {
		List<Company> companyList = companyService.findByName(name);
		return companyList;
	}

	/**
	 * 新增公司資料
	 * 
	 * @param company
	 * @return
	 */
	@PreAuthorize("hasRole('OPERATOR')")
	@PutMapping(value = "/create")
	@ResponseBody
	public Company createCompany(@RequestBody Company company) {

		try {
			company = companyService.create(company);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return company;
	}

	/**
	 * 更新公司資料
	 * 
	 * @param company
	 * @return
	 */
	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping(value = "/save")
	@ResponseBody
	public Company saveCompany(@RequestBody Company company) {
		try {
			company = companyService.update(company);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return company;
	}

	/**
	 * 刪除公司資料
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('MANAGER')")
	@DeleteMapping(value = "/delById")
	@ResponseBody
	public String deleteCompanyById(@RequestParam(value = "id", required = true) Integer id) {

		try {
			companyService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILD :" + e.getMessage();
		}

		return "SUCCESS";
	}
}
