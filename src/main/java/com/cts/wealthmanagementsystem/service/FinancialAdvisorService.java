package com.cts.wealthmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;

public interface FinancialAdvisorService {
 
	public List<FinancialAdvisor> getAllFinancialAdvisor();
	public FinancialAdvisor addFinancialAdvisor(FinancialAdvisor financialAdvisor);
	public Optional<FinancialAdvisor> getFinancialAdvisorByEmail(String emailAddress);
	public Optional<FinancialAdvisor> validateLogin(String emailAddress, String passWord);
	
	
	
} 
