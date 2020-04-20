package com.demo.springboot.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.dao.CompanyDao;
import com.demo.springboot.entity.Company;
import com.demo.springboot.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao CompanyDao;

	@Override
	public Company findById(int id) {
		Company company = null;
		Optional<Company> companyOpt = CompanyDao.findById(id);
		if (companyOpt.isPresent()) {
			company = companyOpt.get();
		}
		return company;
	}

	@Override
	public List<Company> findByName(String name) {
		return CompanyDao.findByName(name);
	}

	@Override
	public Company create(Company company) throws Exception {
		if (CompanyDao.existsById(company.getId())) {
			throw new Exception("Data Exist");
		}
		company.setCreatedAt(new Date());
		company.setCreatedBy("test");
		return CompanyDao.save(company);
	}

	@Override
	public Company update(Company company) throws Exception {
		Company companyOld = CompanyDao.findById(company.getId()).orElse(null);
		if (Objects.isNull(company) || Objects.isNull(company.getId())) {
			throw new Exception("Data Not Exist");
		}
		company.setCreatedAt(companyOld.getCreatedAt());
		company.setCreatedBy(companyOld.getCreatedBy());
		company.setUpdatedAt(new Date());
		company.setUpdatedBy("test");
		return CompanyDao.save(company);
	}

	@Override
	public Boolean deleteById(int id) throws Exception {
		Boolean result = false;
		if (!CompanyDao.existsById(id)) {
			throw new Exception("Data Not Exist");
		}
		CompanyDao.deleteById(id);
		result = true;
		return result;
	}

}
