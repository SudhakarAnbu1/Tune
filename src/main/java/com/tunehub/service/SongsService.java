package com.tunehub.service;

import java.util.List;

import com.tunehub.entity.Songs;

public interface SongsService
{
	public void addSongs(Songs songs);

	public List<Songs> viewAllSongs();

	public boolean songExist(String name);

	public void updateSong(Songs s);

	
	
}
