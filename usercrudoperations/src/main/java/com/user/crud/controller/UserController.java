package com.user.crud.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.user.crud.entity.User;
import com.user.crud.exception.UserAlreadyExists;
import com.user.crud.exception.UserDoesntFound;
import com.user.crud.service.UserService;

@RestController
@RequestMapping("/user")
//ALL<Trace<Debug<info<warn<error
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		logger.info("Inside CreateUser");
		User u;
		try {
			u = userService.createUser(user);
		} catch (UserAlreadyExists e) {
			throw new UserAlreadyExists(e.getMessage());
			
		}
		logger.info("Existing CreateUser");
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id) {
		Optional<User> user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		User u = userService.updateUser(id, user);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> patchUser(@PathVariable int id, @RequestBody User user) {
		User u = userService.patchUser(id, user.getName());
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		try {
			userService.deleteUser(id);
		} catch (UserDoesntFound e) {
			throw new UserDoesntFound(e.getMessage());
		}
		return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
	}

}
