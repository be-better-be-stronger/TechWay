package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

}
