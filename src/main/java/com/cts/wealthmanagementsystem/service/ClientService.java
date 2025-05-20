package com.cts.wealthmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import com.cts.wealthmanagementsystem.entity.Client;

public interface ClientService {
 
	public List<Client> getAllClient();
	public Client addClient(Client client);
	public Optional<Client> getClientByEmail(String email);
	public Optional<Client> validateLogin(String email, String password);
	
	
	
} 
