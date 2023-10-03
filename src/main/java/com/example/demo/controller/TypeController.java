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

import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class TypeController {

	@Autowired
	TypeRepository typeRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/type")
	public ResponseEntity<Object> getUser() {

		try {

			List<Type> types = typeRepository.findAll();
			return new ResponseEntity<>(types, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping("/type")
	public ResponseEntity<Object> addType(@RequestBody Type body) {
		try {
			
			Type type = typeRepository.save(body);
			
			return new ResponseEntity<>(type, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	@GetMapping("/type/{typeId}")
	public ResponseEntity<Object> getTypeDetai(@PathVariable Integer typeId) {

		try {
			Optional<Type> type = typeRepository.findById(typeId);
			if (type.isPresent()) {
				return new ResponseEntity<>(type, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
