package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entity.Songs;
import com.tunehub.service.SongsService;

@Controller
public class SongsController 
{
	@Autowired
	SongsService service;
	@PostMapping("/addSongs")
	public String addSongs(@ModelAttribute Songs songs)
	{
		boolean songStatus=service.songExist(songs.getName());
		if(songStatus==false)
		{
		service.addSongs(songs);
		System.out.println("Song is added Successfully");
		}
		else
		{
			//for checking purpose only.
			System.out.println("Song already Exist");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model)
	{
		List<Songs> viewSongs=service.viewAllSongs();
		model.addAttribute("songs", viewSongs);
		return "displaySongs";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model)
	{
		boolean premium=false;
		if(premium==true)
		{
		List<Songs> viewSongs=service.viewAllSongs();
		model.addAttribute("songs", viewSongs);
		return "displaySongs";
		}
		else
		{
			return "makePayment";
		}
		
	}
}
