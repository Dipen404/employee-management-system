package com.ipen.ems.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/profile")
	public String getUserHomePage() {
		return "redirect:/";
	}

}