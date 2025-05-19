package com.cts.wealthmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;
import com.cts.wealthmanagementsystem.repository.FinancialAdvisorRepository;

@Service
public class FinancialAdvisorServiceImplementation implements FinancialAdvisorService{
	@Autowired
	private FinancialAdvisorRepository financialAdvisorRepository ;


	@Override
	public List<FinancialAdvisor> getAllFinancialAdvisor() {
		
		return financialAdvisorRepository.findAll() ;
	}

	@Override
	public FinancialAdvisor addFinancialAdvisor(FinancialAdvisor financialAdvisor) {
	
		return financialAdvisorRepository.save(financialAdvisor);
	}

}
