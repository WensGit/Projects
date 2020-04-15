package com.demo.springboot.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.springboot.model.Company;

@RestController
public class CompanyController {

	@GetMapping(value = "/company/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable("id") Long id) {
		Company company = new Company();
		company.setId(id);
		company.setName("John");

	    return ResponseEntity.ok().body(company);
	}
	 
	@PostMapping(value = "/company/create")
	public ResponseEntity<Company> createCompany(@RequestBody Company request) {
//	    boolean isIdDuplicated = productDB.stream()
//	                    .anyMatch(p -> p.getId().equals(request.getId()));
//	    if (isIdDuplicated) {
//	        return ResponseEntity.unprocessableEntity().build();
//	    }

		Company company = new Company();
		company.setId(request.getId());
		company.setName(request.getName());
	    //productDB.add(product);

	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(company.getId())
	            .toUri();

	    return ResponseEntity.created(location).body(company);
	}
}
