package com.demo.springboot.service;

import java.util.List;

import com.demo.springboot.entity.Client;

public interface ClientService {
	public Client findById(int id);

	public List<Client> findByName(String name);

	public Client create(Client company) throws Exception;

	public Client update(Client company) throws Exception;

	public Boolean deleteById(int id) throws Exception;

}
