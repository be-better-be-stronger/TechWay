package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.VideoPlayer;

public interface VideoPlayerRepository extends JpaRepository<VideoPlayer, Integer> {

}
