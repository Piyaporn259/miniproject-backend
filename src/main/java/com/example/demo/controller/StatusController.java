package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Status;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.StatusRepository;

@CrossOrigin(origins = "*")
@RestController
public class StatusController {
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	AreaRepository areaRepository;
	
	@GetMapping("/status")
	public ResponseEntity<Object> getStatus() {

		try {

			List<Status> status = statusRepository.findAll();
			return new ResponseEntity<>(status, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/status")
	public ResponseEntity<Object> addstatus(@RequestBody Status body) {
		try {
			
			Status status = statusRepository.save(body);
			
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	@GetMapping("/status/{statusId}")
	public ResponseEntity<Object> getStatusDetai(@PathVariable Integer statusId) {

		try {
			Optional<Status> status = statusRepository.findById(statusId);
			if (status.isPresent()) {
				return new ResponseEntity<>(status, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Status not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
