package com.user.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.crud.dto.DepartmentDto;
import com.user.crud.dto.ResponseDto;
import com.user.crud.dto.UserDto;
import com.user.crud.entity.User;
import com.user.crud.exception.UserAlreadyExists;
import com.user.crud.exception.UserDoesntFound;
import com.user.crud.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User createUser(User user) {
		User existingCustomer = userRepository.findById(user.getUserId()).orElse(null);
		if (existingCustomer == null) {
			return userRepository.save(user);
		} else
			throw new UserAlreadyExists("User already exists!!");
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(Integer id, User user) {
		// 1.Check if that user exist in Db
		User existingUser = getUserById(id);
		// 2 If yes then get that object and set updated values to it
		existingUser.setName(user.getName());
		existingUser.setAge(user.getAge());
		// 3 Save updated values in database
		User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}

	@Override
	public User patchUser(Integer id, String name) {
		// 1.Check if that user exist in Db
		User existingUser = getUserById(id);
		// 2 If yes then get that object and set updated values to it
		existingUser.setName(name);
		// 3 Save updated values in database
		User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}

	@Override
	public void deleteUser(Integer id) {
		// 1.Check if that user exist in Db
		// Optional<User> existingUser = getUserById(id);

		User existingCustomer = userRepository.findById(id).orElse(null);
		if (existingCustomer == null) {
			throw new UserDoesntFound("User Doesn't exists");

		} else
			userRepository.deleteById(id);
	}

	@Override
	public ResponseDto getDepartmentBasedOnUser(Integer userId) {

		ResponseDto responseDto = new ResponseDto();
		//Fetching user from database
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			//UserDto userDto = mapToUser(user);
			//1.Setting response dto -> userdto
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setName(user.getName());
			userDto.setAge(user.getAge());
			
			/**
			 * 2.Calling another microservice and setting department dto for responsedto
			 */
			DepartmentDto deptDto= restTemplate.getForObject("http://department-service/api/departments/"+user.getDepartmentId(), DepartmentDto.class);
					/*
						 * getForEntity( "http://localhost:8081/api/departments/" +
						 * user.getDepartmentId(), DepartmentDto.class);
						 */
			
			//return Arrays.asList(objects);
			//DepartmentDto departmentDto = responseEntity.getBody();

			//System.out.println(responseEntity.getStatusCode());

			responseDto.setUser(userDto);
			responseDto.setDepartment(deptDto);
		}
		else {
			throw new UserDoesntFound("User Doesn't exists");
		}
		return responseDto;
	}

	/*private UserDto mapToUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setAge(user.getAge());
		return userDto;
	}*/
}
