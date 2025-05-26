package com.cts.wealthmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cts.wealthmanagementsystem.entity.Client;
//import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.service.ClientService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClientService clientService;

    // 🔹 Render Admin Login Page
    @GetMapping("/login")
    public String call() {
        return "admin-login";
    }

    // 🔹 Handle Admin Login (No Session Tracking)
    @PostMapping("/login")
    public String processAdminLogin(@RequestParam String email, @RequestParam String password, Model model) {
        if ("admin@wealthy.com".equals(email) && "Admin@123".equals(password)) {
            return "redirect:/admin/dashboard"; // ✅ Redirect Admin to dashboard without session
        }

        model.addAttribute("errorMessage", "Invalid credentials!");
        return "admin-login";
    }

    // 🔹 Admin Dashboard - View Drafted Users
    @GetMapping("/dashboard")
    public String viewDraftUsers(Model model) {
        model.addAttribute("draftUsers", clientService.getAllDraftUsers());
        return "admin-dashboard";
    }
    @GetMapping("/users")
    public String showApprovedClients(Model model) {
        List<Client> approvedClients = clientService.getApprovedClients();
        model.addAttribute("clients", approvedClients);
        return "users"; // Redirects to users.html
    }

    // 🔹 Approve User (Move Draft to Active Users)
    @PostMapping("/approve/{identity}")
    public String approveUser(@PathVariable Integer identity) {
    	clientService.approveUser(identity);
        return "redirect:/admin/dashboard";
    }

    // 🔹 Reject User (Deletes Draft Entry)
    @PostMapping("/reject/{identity}")
    public String rejectUser(@PathVariable Integer identity) {
    	clientService.rejectUser(identity);
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/logout")
    public String out()
    {
    	return "login";
    }
   
}
