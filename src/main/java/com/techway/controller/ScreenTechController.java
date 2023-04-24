package com.techway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techway.entity.ScreenTech;
import com.techway.service.IScreenTechService;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/v1/screentechs")
public class ScreenTechController {
	@Autowired
	IScreenTechService screenTechService;
	
	@GetMapping
	public ResponseEntity<List<ScreenTech >> findByName(@RequestParam(required = false) String name){
		List<ScreenTech> list = new ArrayList<>();
		if(name != null)
			screenTechService.findByNameContaining(name).forEach(list::add);
		else
			screenTechService.findAll().forEach(list::add);		
		if (list.isEmpty())
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);		    
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping({"id"})
	public ResponseEntity<ScreenTech> getOne(@PathVariable("id") long id) {
		return new ResponseEntity<>(screenTechService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ScreenTech> create(@RequestBody ScreenTech o) {
		return new ResponseEntity<ScreenTech>(screenTechService.save(o), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<ScreenTech> update(@PathVariable("id") long id, ScreenTech o) {
		return new ResponseEntity<>(screenTechService.update(id, o), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") long id) {
		return new ResponseEntity<>(screenTechService.deleteById(id), HttpStatus.OK);
	}
	
}
