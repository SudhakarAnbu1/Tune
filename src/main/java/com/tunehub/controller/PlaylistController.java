package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entity.Playlist;
import com.tunehub.entity.Songs;
import com.tunehub.service.PlaylistService;
import com.tunehub.service.SongsService;


@Controller
public class PlaylistController 
{
	@Autowired
	SongsService songservice;
	
	@Autowired
	PlaylistService playlistservice;
	
	@GetMapping("/createPlayList")
	public String createPlaylist(Model model)
	{
		List<Songs> songList=songservice.viewAllSongs();
		model.addAttribute("songs", songList);
		
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist)
	{
		playlistservice.addPlaylist(playlist);
		List<Songs>songList=playlist.getSongs();
		for(Songs s:songList)
		{
			s.getPlaylist().add(playlist);
			songservice.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlayList")
	public String viewPlayList(Model model)
	{
		List<Playlist> allPlaylists=playlistservice.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylists";
	}
}
