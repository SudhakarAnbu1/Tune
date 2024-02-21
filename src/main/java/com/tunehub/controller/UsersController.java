package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entity.Songs;
import com.tunehub.entity.Users;
import com.tunehub.service.SongsService;
import com.tunehub.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController 
{
	@Autowired
	UsersService service;
	
	@Autowired
	SongsService songService;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.addUsers(user);
			System.out.println("User added");
			
		}
		else
		{
			System.out.println("User already exist");
		}
		return "home";	
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session,Model model)
	{
		
	
		
		if(service.validate(email, password)==true)
		{
			session.setAttribute("email", email);
			if(service.getRole(email).equals("admin"))
			{
			return "adminHome";
			}
			else
			{
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				
				List<Songs> viewSongs=songService.viewAllSongs();
				model.addAttribute("songs", viewSongs);
				
				model.addAttribute("isPremium", userStatus);
				return "customerHome";
			}
		}
		else
		{
			return "login";
		}	
	}
	@PostMapping("/generatePassword")
	public String generatePassword(@RequestParam("email")String email,@RequestParam("newPassword")String newPassword,@RequestParam("confirmPassword")String confirmPassword)
	{
		if(newPassword.equals(confirmPassword))
		{
			service.updatePassword(email,newPassword);
			return "login";
		}
		else
		{
			return "forgotPassword";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
