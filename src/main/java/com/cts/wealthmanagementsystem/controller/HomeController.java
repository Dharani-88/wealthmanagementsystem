package com.cts.wealthmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;

import com.cts.wealthmanagementsystem.service.FinancialAdvisorServiceImplementation;

@Controller
public class HomeController {
	@Autowired 
	private FinancialAdvisorServiceImplementation financialAdvisorServiceImplementation;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		FinancialAdvisor financialAdvisor=new FinancialAdvisor();
		model.addAttribute("financialAdvisor", financialAdvisor);
		return "signup";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute ("financialAdvisor") FinancialAdvisor financialAdvisor,BindingResult result ) {
		financialAdvisorServiceImplementation.addFinancialAdvisor(financialAdvisor);
		
		return "home";
	} 

}
