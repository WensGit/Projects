package com.demo.springboot.service;

import java.util.List;

import com.demo.springboot.entity.Client;

public interface ClientService {
	public Client findById(int id);

	public List<Client> findByName(String name);

	public Client create(Client client) throws Exception;

	public Boolean createMulti(List<Client> clients) throws Exception;

	public Client update(Client client) throws Exception;

	public Boolean deleteById(int id) throws Exception;

}
