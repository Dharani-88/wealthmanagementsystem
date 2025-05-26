package com.cts.wealthmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.service.ClientService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControllerClass {
	 @Autowired
	    private ClientService clientService;
	@GetMapping("/")
    public String mainPage(Model model) {
        return "login"; // ✅ Ensure "main.html" exists in "/templates/"
    }
	 @GetMapping("/profile")
	    public String getProfile(Model model, HttpSession session) {
	        // Retrieve user data from session
	        Client newClient = (Client) session.getAttribute("loggedInUser");
            System.out.println(newClient);
	        // Redirect to login if session is empty
	        if (newClient == null) {
	            return "redirect:/auth/login";
	        }

	        // Add user details to model for Thymeleaf rendering
	        model.addAttribute("advisor", newClient);
	        
	        // Debugging
	        
	        return "profile"; // Loads profile.html
	    }
	 @GetMapping("/dashboard")
	    public String viewDraftUsers(Model model) {
	        model.addAttribute("draftUsers", clientService.getAllDraftUsers());
	        return "admin-dashboard";
	    }
	 @PostMapping("/update-profile")
	 public String updateProfile(@ModelAttribute Client updatedclient, HttpSession session) {
	     Client newClient = (Client) session.getAttribute("loggedInUser");

	     if (newClient == null) {
	         return "redirect:/auth/login";
	     }

	     updatedclient.setIdentity(newClient.getIdentity());
	     updatedclient.setEmailAddress(newClient.getEmailAddress());

	     clientService.addClient(updatedclient);
	     session.setAttribute("loggedInUser", updatedclient);

	     return "redirect:/auth/main"; 
	 }

}
