package com.user.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.crud.entity.User;
import com.user.crud.exception.UserAlreadyExists;
import com.user.crud.exception.UserDoesntFound;
import com.user.crud.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User updateUser(int id, User user) {
		// 1.Check if that user exist in Db
		Optional<User> existingUser = getUserById(id);
		// 2 If yes then get that object and set updated values to it
		existingUser.get().setName(user.getName());
		existingUser.get().setAge(user.getAge());
		// 3 Save updated values in database
		User updatedUser = userRepository.save(existingUser.get());
		return updatedUser;
	}

	@Override
	public User patchUser(int id, String name) {
		// 1.Check if that user exist in Db
		Optional<User> existingUser = getUserById(id);
		// 2 If yes then get that object and set updated values to it
		existingUser.get().setName(name);
		// 3 Save updated values in database
		User updatedUser = userRepository.save(existingUser.get());
		return updatedUser;
	}

	@Override
	public void deleteUser(int id) {
		// 1.Check if that user exist in Db
		// Optional<User> existingUser = getUserById(id);

		User existingCustomer = userRepository.findById(id).orElse(null);
		if (existingCustomer == null) {
			throw new UserDoesntFound("User Doesn't exists");

		} else
			userRepository.deleteById(id);
	}
}
