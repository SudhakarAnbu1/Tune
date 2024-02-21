package com.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>
{

}
