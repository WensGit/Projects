package com.demo.springboot.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.dao.ClientDao;
import com.demo.springboot.entity.Client;
import com.demo.springboot.service.ClientService;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao ClientDao;

	@Override
	public Client findById(int id) {
		Client client = null;
		Optional<Client> clientOpt = ClientDao.findById(id);
		if (clientOpt.isPresent()) {
			client = clientOpt.get();
		}
		return client;
	}

	@Override
	public List<Client> findByName(String name) {
		return ClientDao.findByName(name);
	}

	@Override
	public Client create(Client client) throws Exception {
		if (ClientDao.existsById(client.getId())) {
			throw new Exception("Data Exist");
		}
		client.setCreatedAt(new Date());
		client.setCreatedBy("test");
		return ClientDao.save(client);
	}

	@Transactional
	@Override
	public Boolean createMulti(List<Client> clients) throws Exception {
		Integer result = 0;
		for(Client client : clients){
			if (ClientDao.existsById(client.getId())) {
				throw new Exception("Data Exist");
			}
			client.setCreatedAt(new Date());
			client.setCreatedBy("test");
			ClientDao.save(client);
			result++;
		}
		
		return result.equals(clients.size());
	}

	@Override
	public Client update(Client client) throws Exception {
		Client clientOld = ClientDao.findById(client.getId()).orElse(null);
		if (Objects.isNull(client) || Objects.isNull(client.getId())) {
			throw new Exception("Data Not Exist");
		}
		client.setCreatedAt(clientOld.getCreatedAt());
		client.setCreatedBy(clientOld.getCreatedBy());
		client.setUpdatedAt(new Date());
		client.setUpdatedBy("test");
		return ClientDao.save(client);
	}

	@Override
	public Boolean deleteById(int id) throws Exception {
		Boolean result = false;
		if (!ClientDao.existsById(id)) {
			throw new Exception("Data Not Exist");
		}
		ClientDao.deleteById(id);
		result = true;
		return result;
	}

}
