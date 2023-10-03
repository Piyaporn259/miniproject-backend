package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Reserve;

import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ReserveRepository;

@RestController
public class ReserveController {

	@Autowired
	ReserveRepository reserveRepository;
	@Autowired
	AreaRepository areaRepository;
	@Autowired
	OwnerRepository ownerRepository;
	
	@PostMapping("/reserve")
	public ResponseEntity<Object> addreserve(@RequestBody Reserve body) {
		try {
			
	
			Reserve reserve = reserveRepository.save(body);
			
			return new ResponseEntity<>(reserve, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping("/reserve/{userId}")
	public ResponseEntity<Object> getReserveDetai(@PathVariable Long userId) {

		try {
			List<Reserve> reserve = reserveRepository.findAllByUserUserId(userId);
			if (!reserve.isEmpty()) {
				return new ResponseEntity<>(reserve, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Reserve not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
