package com.user.crud.service;

import java.util.List;
import java.util.Optional;

import com.user.crud.entity.User;

public interface UserService {

	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(int id);
	
	public User updateUser(int id,User u);
	
	public User patchUser(int id,String name);
	
	public void deleteUser(int id);
}
