package com.user.crud.entity;

import jakarta.persistence.*;


@Table(name = "User")
@Entity
public class User {

	@Id
	@GeneratedValue
	private int userId;
	@Column(name = "uname")
	private String name;
	private int age;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
