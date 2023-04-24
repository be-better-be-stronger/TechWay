package com.techway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.ScreenTech;
import com.techway.service.ScreenTechService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/screentechs")
public class ScreenTechController {
	@Autowired
	ScreenTechService screenTechService;
	
	@GetMapping
    public ResponseEntity<List<ScreenTech>> getAll(@RequestParam(required = false) String name) {
        List<ScreenTech> list = new ArrayList<>();
        if (name != null) {
            screenTechService.findByNameContaining(name).forEach(list::add);
        } else {
            screenTechService.findAll().forEach(list::add);
        }
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ScreenTech> getById(@PathVariable("id") long id) {
        ScreenTech screenTech = screenTechService.findById(id);
        if (screenTech == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(screenTech);
    }
	
	@PostMapping
    public ResponseEntity<ScreenTech> create(@RequestBody ScreenTech screenTech) {
        ScreenTech createdScreenTech = screenTechService.save(screenTech);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScreenTech);
    }

	@PutMapping("/{id}")
    public ResponseEntity<ScreenTech> update(@PathVariable("id") long id, @RequestBody ScreenTech screenTech) {
        ScreenTech updatedScreenTech = screenTechService.update(id, screenTech);
        if (updatedScreenTech == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedScreenTech);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") long id) {
        boolean isDeleted = screenTechService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }	
}
