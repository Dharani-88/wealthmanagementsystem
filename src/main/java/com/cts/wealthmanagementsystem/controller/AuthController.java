package com.cts.wealthmanagementsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cts.wealthmanagementsystem.entity.Client;
import com.cts.wealthmanagementsystem.service.ClientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClientService ClientService;
    //private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @GetMapping("/login")
    public String get()
    {
    	return "login";
    	// 🔹 Render Home Page (Main Dashboard)
    }
    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        Client newClient = (Client) session.getAttribute("loggedInUser");

        if (newClient == null) {
            return "redirect:/auth/login"; // Redirect to login if session is empty
        }

        model.addAttribute("loggedInUser", newClient); // ✅ Passing to Thymeleaf
        return "main";
    }

    // 🔹 Render Admin Login Page
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin-login";
    }
    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("financialAdvisor", new Client());
        return "signup";
    }
    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session) {
        // Retrieve user data from session
        Client newClient = (Client) session.getAttribute("loggedInUser");
        //System.out.println(advisor);
        // Redirect to login if session is empty
        if (newClient == null) {
            return "redirect:/auth/login";
        }

        // Add user details to model for Thymeleaf rendering
        model.addAttribute("advisor", newClient);
        
        // Debugging
        //System.out.println("Retrieved profile data: " + advisor);

        return "profile"; // Loads profile.html
    }
    @GetMapping("/login/edit-profile")
    public String editProfilePage(Model model, HttpSession session) {
        // Retrieve logged-in user from session
        Client newClient = (Client) session.getAttribute("loggedInUser");

        // Redirect to login if session is empty
        if (newClient == null) {
            return "redirect:/auth/login";
        }

        // Pass user details to Thymeleaf for pre-filled form fields
        model.addAttribute("advisor", newClient);
        
        // Debugging
   //     System.out.println("Editing profile for: " + advisor);

        return "edit-profile"; // Loads edit-profile.html
    }

    // 🔹 Handle User Login (Database Validation) - No Session Tracking
    @PostMapping("/login")
    public String processUserLogin(@RequestParam String emailAddress, @RequestParam String passWord, Model model,HttpSession session) {
        Optional<Client> newClient = ClientService.getClientByEmail(emailAddress);

        if (newClient.isPresent()&& newClient.get().isApproved()) {
            String storedPassword =newClient.get().getPassWord();

            // ✅ Remove `{noop}` if it exists
            if (storedPassword.startsWith("{noop}")) {
                storedPassword = storedPassword.substring(6); // ✅ Removes "{noop}" from password
            }

            // ✅ Compare plain password
            if (passWord.equals(storedPassword)) {
            	 session.setAttribute("loggedInUser", newClient.get()); 
            	 System.out.println(session.getAttribute("loggedInUser"));
            	 model.addAttribute("loggedInUser", newClient.get()); // ✅ Ensure it's not wrapped in Optional
// ✅ Store in session
                 //System.out.println("User stored in session: " + session.getAttribute("loggedInUser"));
                return "main"; // ✅ Load main page with user details
            }
        }

        model.addAttribute("errorMessage", "Invalid credentials.");
        return "login"; // ❌ Stay on login page if authentication fails
    }
    @GetMapping("/login/logout")
    public String logout()
    {
    	return "login";
    }

    // 🔹 Render Signup Page
   
    @PostMapping("/admin/login")
    public String processAdminLogin(@RequestParam String email, @RequestParam String password, Model model) {
        if ("admin@wealthy.com".equals(email) && "Admin@123".equals(password)) {
            return "redirect:/admin/dashboard"; // ✅ Redirect Admin to dashboard without session
        }

        model.addAttribute("errorMessage", "Invalid credentials!");
        return "admin-login";
    }
    
    
    
    
    // 🔹 Handle Signup (Saves Users as Draft Until Admin Approval)
    @PostMapping("/signup/saveEmployee")
    public String saveClient(@ModelAttribute Client financialAdvisor) {
    	ClientService.addClient(financialAdvisor);
        return "redirect:/auth/login"; // ✅ Redirect to login after signup
    }
}
