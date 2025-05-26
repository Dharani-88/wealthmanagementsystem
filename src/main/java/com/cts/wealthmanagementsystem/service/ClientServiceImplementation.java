package com.cts.wealthmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

   

    @Override
    public Client addClient(Client newClient) {
        newClient.setPassWord("{noop}" + newClient.getPassWord()); 
        return clientRepository.save(newClient);
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmailAddress(email);
    }

    @Override
    public List<Client> getAllDraftUsers() {
        return clientRepository.findByIsApprovedFalse();
    }

    @Override
    public void approveUser(Integer identity) {
        Optional<Client> advisor = clientRepository.findById(identity);
        advisor.ifPresent(user -> {
            user.setApproved(true); // Move from draft to approved
            clientRepository.save(user);
        });
    }

    @Override
    public void rejectUser(Integer identity) {
    	clientRepository.deleteById(identity);
    }
    public List<Client> getApprovedClients() {
        return clientRepository.findByIsApprovedTrue(); // Fetch only approved clients
    }
}   
