package com.microservices.department.model;

import java.util.ArrayList;
import java.util.List;

public class Department {

	private Long id;

	private String name;

	public List<Employee> empList = new ArrayList<>();

	public Department() {

	}

	public Department(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", empList=" + empList + "]";
	}

}
