package com.cts.wealthmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.wealthmanagementsystem.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Optional<Client> findByEmailAddress(String emailAddress); // <-- Add this line
    Optional<Client> findByEmailAddressAndPassWord(String emailAddress, String passWord);
    List<Client> findByIsApprovedFalse();
    List<Client> findAll(); 
    List<Client> findByIsApprovedTrue(); 
   }
