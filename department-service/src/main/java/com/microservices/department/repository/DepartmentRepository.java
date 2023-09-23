package com.microservices.department.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservices.department.model.Department;

@Repository
public class DepartmentRepository {

	public List<Department> departments = new ArrayList<>();

	public Department addDepartment(Department d) {
		departments.add(d);
		return d;
	}

	public Department findById(Long id) {
		return departments.stream().filter(department -> department.getId().equals(id)).findFirst().orElseThrow();
	}
	public List<Department> findAll(){
		return departments;
	}

}
