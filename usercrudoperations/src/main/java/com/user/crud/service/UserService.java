package com.user.crud.service;

import java.util.List;

import com.user.crud.dto.ResponseDto;
import com.user.crud.entity.User;

public interface UserService {

	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(Integer id);
	
	public User updateUser(Integer id,User u);
	
	public User patchUser(Integer id,String name);
	
	public void deleteUser(Integer id);
	
	public ResponseDto getDepartmentBasedOnUser(Integer id);
}
