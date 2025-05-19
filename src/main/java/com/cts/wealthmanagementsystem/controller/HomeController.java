package com.cts.wealthmanagementsystem.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.wealthmanagementsystem.entity.FinancialAdvisor;
import com.cts.wealthmanagementsystem.service.FinancialAdvisorService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired 
	private FinancialAdvisorService financialAdvisorService;
	
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
	public String saveEmployee(@ModelAttribute FinancialAdvisor financialAdvisor, Model model) {
	    // Check if a user with the same email already exists
	    Optional<FinancialAdvisor> existingAdvisor = financialAdvisorService.getFinancialAdvisorByEmail(financialAdvisor.getEmailAddress());

	    if (existingAdvisor.isPresent()) {
	        model.addAttribute("errorMessage", "A user with this email already exists.");
	        return "signup"; // return to signup page with error
	    }
	    financialAdvisorService.addFinancialAdvisor(financialAdvisor);
	    return "home";
	}

	@PostMapping("/login")
	public String login(@RequestParam String emailAddress,
	                    @RequestParam String passWord,
	                    Model model,
	                    HttpSession session) {

	    Optional<FinancialAdvisor> advisor = financialAdvisorService.validateLogin(emailAddress, passWord);

	    if (advisor.isPresent()) {
	        session.setAttribute("loggedInUser", advisor.get());
	        return "main"; 
	    } else {
	        model.addAttribute("errorMessage", "Invalid username or password.");
	        return "login"; 
	    }
	}

	

}
