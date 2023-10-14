package com.example.demo.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Area;
import com.example.demo.model.Status;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.StatusRepository;

@CrossOrigin(origins = "*")
@RestController
public class AreaController {

	@Autowired
	AreaRepository areaRepository;
	@Autowired
	StatusRepository statusRepository;
	
	@GetMapping("/area")
	public ResponseEntity<Object> getArea() {

		try {

			List<Area> areas = areaRepository.findAll();
			return new ResponseEntity<>(areas, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/area")
	public ResponseEntity<Object> addarea(@RequestBody Area body) {
		try {
			
			Optional<Status> status = statusRepository.findById(1);		
			body.setStatus(status.get());
			
			Area area = areaRepository.save(body);
			
			return new ResponseEntity<>(area, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@DeleteMapping("/area/{areaId}")
	public ResponseEntity<Object> deletearea(@PathVariable Integer areaId) {
		try {
			Optional<Area> area = areaRepository.findById(areaId);
			if (area.isPresent()) {
				areaRepository.delete(area.get());

				return new ResponseEntity<>("DELETE SUCSESS", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Area not found", HttpStatus.OK);
			}
		} catch (Exception e) {
		
		}
		return new ResponseEntity<>("Internal server error", HttpStatus.OK);
	}

}
