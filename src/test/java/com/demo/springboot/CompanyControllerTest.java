package com.demo.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.springboot.entity.Client;
import com.demo.springboot.entity.Company;

public class CompanyControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * 依ID 查詢公司資料
	 */
	@WithMockUser(username="admin")
	@Test
	public void getCompany() throws Exception {
		String uri = "/company/searchById";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 1);
		String inputJson = super.mapToJson(param);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri, inputJson).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Client[] clientlist = super.mapFromJson(content, Client[].class);
		assertTrue(clientlist.length > 0);
	}

	/**
	 * 依名稱模糊查詢公司資料
	 */
	@WithMockUser(username="admin")
	@Test
	public void getCompanysByName() throws Exception {

		String uri = "/company/searchByName";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "");
		String inputJson = super.mapToJson(param);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri, inputJson).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Client[] clientlist = super.mapFromJson(content, Client[].class);
		assertTrue(clientlist.length > 0);
	}

	/**
	 * 新增公司資料
	 */
	@WithMockUser(username="admin")
	@Test
	public void createCompany() throws Exception {

		String uri = "/company/create";
		Company company = new Company();
		company.setName("company1");
		String inputJson = super.mapToJson(company);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "company is created successfully");
	}

	/**
	 * 更新公司資料
	 */
	@WithMockUser(username="admin")
	@Test
	public void saveCompany() throws Exception {
		String uri = "/company/save";
		Client client = new Client();
		client.setName("client-1");
		client.setCompanyId(1);
		client.setEmail("email-1");
		client.setPhone("0910-101010");
		String inputJson = super.mapToJson(client);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "company is updated successsfully");
	}

	/**
	 * 刪除公司資料
	 */
	@WithMockUser(username="admin")
	@Test
	public void deleteCompanyById() throws Exception {
		String uri = "/company/delById";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, param)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "company is deleted successsfully");
	}
}
