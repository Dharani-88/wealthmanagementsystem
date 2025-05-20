package com.cts.wealthmanagementsystem.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.service.ClientService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired 
	private ClientService clientService;
	
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
		Client client=new Client();
		model.addAttribute("client", client);
		return "signup";
	}
	
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Client client, Model model) {
	    // Check if a user with the same email already exists
	    Optional<Client> getClient = clientService.getClientByEmail(client.getEmail());

	    if (getClient.isPresent()) {
	        model.addAttribute("errorMessage", "A user with this email already exists.");
	        return "signup"; // return to signup page with error
	    }
	    clientService.addClient(client);
	    return "home";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email,
	                    @RequestParam String password,
	                    Model model,
	                    HttpSession session) {
		if(email.equals("admin@123") && password.equals("AdminLogin123")) {
    		return "Admin";
    	}
	    Optional<Client> client = clientService.validateLogin(email,password);

	    if (client.isPresent()) {
	    
	        session.setAttribute("loggedInUser", client.get());
	        return "main"; 
	   
	    } else {
	        model.addAttribute("errorMessage", "Invalid username or password.");
	        return "login"; 
	    }
	}

	

}
