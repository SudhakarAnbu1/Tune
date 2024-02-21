package com.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController 
{
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	@GetMapping("/newSongs")
	public String newSongs()
	{
		return "addSongs";
	}
	@GetMapping("/forgotPassword")
	public String forgotPassword()
	{
		return "forgotPassword";
	}
}
