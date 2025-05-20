package com.cts.wealthmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/complaints")
	public String complaints() {
		return "complaints";
	}
	@GetMapping("/Admin")
	public String admin() {
		return "Admin";
	}
	@GetMapping("/createplan")
	public String createplan() {
		return "createplan";
	}
	@GetMapping("/updateplan")
	public String updateplan() {
		return "updateplan";
	}
}
