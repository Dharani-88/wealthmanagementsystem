package com.cts.wealthmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService{
	@Autowired
	private ClientRepository clientRepository ;


	@Override
	public List<Client> getAllClient() {
		
		return clientRepository.findAll() ;
	}

	@Override
	public Client addClient(Client client) {
	
		return clientRepository.save(client);
	}

	public Optional<Client> getClientByEmail(String email) {
	    return clientRepository.findByEmail(email);
	}
	public Optional<Client> validateLogin(String email, String password) {
	    return clientRepository.findByEmailAndPassword(email, password);
	}

	

}
