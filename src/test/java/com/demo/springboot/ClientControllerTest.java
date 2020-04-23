package com.demo.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.springboot.entity.Client;

public class ClientControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * 依ID 查詢用戶資料
	 * 
	 * @throws Exception
	 */
	@Test
	public void getClient() throws Exception {

		String uri = "/client/searchById";
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
	 * 依名稱模糊查詢用戶資料
	 * 
	 */
	@Test
	public void getClientsByName() throws Exception {

		String uri = "/client/searchByName";
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
	 * 新增用戶資料
	 * 
	 */
	@Test
	public void createClient() throws Exception {

		String uri = "/client/create";
		Client client = new Client();
		client.setName("client1");
		client.setCompanyId(1);
		client.setEmail("email1");
		client.setPhone("0910101010");
		String inputJson = super.mapToJson(client);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "client is created successfully");
	}

	/**
	 * 更新用戶資料
	 */
	@Test
	public void saveClient() throws Exception {
		String uri = "/client/create";
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
		assertEquals(content, "client is updated successsfully");
	}

	/**
	 * 刪除用戶資料
	 */
	@Test
	public void deleteClientById() throws Exception {
		String uri = "/client/delById";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, param)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "client is deleted successsfully");
	}
}
