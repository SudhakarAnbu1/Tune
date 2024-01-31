package com.tunehub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entity.Playlist;
import com.tunehub.repository.PlaylistRepository;

@Service
public class PlaylistServiceImplement implements PlaylistService
{
	@Autowired
	PlaylistRepository repos;

	@Override
	public void addPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		repos.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		// TODO Auto-generated method stub
			return repos.findAll();
		
	}

}
