package com.cts.wealthmanagementsystem.service;

import java.util.List;

import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;

public interface FinancialAdvisorService {
 
	public List<FinancialAdvisor> getAllFinancialAdvisor();
	public FinancialAdvisor addFinancialAdvisor(FinancialAdvisor financialAdvisor);
	
} 
