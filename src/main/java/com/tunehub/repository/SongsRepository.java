package com.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entity.Songs;
import java.util.List;


public interface SongsRepository extends JpaRepository<Songs, Integer>
{
	public Songs findByName(String name);
}
