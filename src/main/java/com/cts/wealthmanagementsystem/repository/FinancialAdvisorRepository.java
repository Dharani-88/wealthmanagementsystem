package com.cts.wealthmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;
import java.util.Optional;

public interface FinancialAdvisorRepository extends JpaRepository<FinancialAdvisor, Integer> {
    
    Optional<FinancialAdvisor> findByEmailAddress(String emailAddress); // <-- Add this line
    Optional<FinancialAdvisor> findByEmailAddressAndPassWord(String emailAddress, String passWord);

}
