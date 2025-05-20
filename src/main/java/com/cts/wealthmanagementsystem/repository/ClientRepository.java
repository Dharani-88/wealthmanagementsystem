package com.cts.wealthmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.wealthmanagementsystem.entity.Client;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Optional<Client> findByEmail(String emailAddress); // <-- Add this line
    Optional<Client> findByEmailAndPassword(String emailAddress, String passWord);

}
