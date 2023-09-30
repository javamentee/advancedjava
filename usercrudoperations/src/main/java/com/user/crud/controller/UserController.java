package com.user.crud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.crud.dto.Activity;
import com.user.crud.dto.ResponseDto;
import com.user.crud.entity.User;
import com.user.crud.exception.UserAlreadyExists;
import com.user.crud.exception.UserDoesntFound;
import com.user.crud.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
//ALL<Trace<Debug<info<warn<error
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	private String DEPARTMENT_API = "http://localhost:8085/api/departments/dept/";

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
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
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

	@GetMapping("/dept/{uid}")
	public ResponseEntity<ResponseDto> getDeptAndUser(@PathVariable("uid") Integer userId) {
		ResponseDto responseDto = null;
		try {
			responseDto = userService.getDepartmentBasedOnUser(userId);
			return ResponseEntity.ok(responseDto);
		} catch (UserDoesntFound e) {
			throw new UserDoesntFound(e.getMessage());
		}
	}
	@GetMapping
	@CircuitBreaker(name = "randomActivity", fallbackMethod = "getFallBackResponse")
	public String getFallBackResponse(int userId) {
		ResponseEntity<Activity> responseEntity = restTemplate.getForEntity(DEPARTMENT_API+userId, Activity.class);
		Activity activity = responseEntity.getBody();
		return activity.getActivity();
	}
}
