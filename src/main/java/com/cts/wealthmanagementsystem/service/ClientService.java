package com.cts.wealthmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import com.cts.wealthmanagementsystem.entity.Client;

public interface ClientService {
    //List<Client> getAllClients();
    Client addClient(Client newClients);
    Optional<Client> getClientByEmail(String emailAddress);
    List<Client> getAllDraftUsers();
    void approveUser(Integer identity);
    void rejectUser(Integer identity);
	List<Client> getApprovedClients();
	
}
