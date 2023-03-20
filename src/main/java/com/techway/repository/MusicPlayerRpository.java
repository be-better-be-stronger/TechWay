package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.MusicPlayer;

public interface MusicPlayerRpository extends JpaRepository<MusicPlayer, Integer> {

}
