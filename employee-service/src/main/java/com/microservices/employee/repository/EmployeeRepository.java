package com.microservices.employee.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservices.employee.model.Employee;

@Repository
public class EmployeeRepository {

	public List<Employee> employees = new ArrayList<>();

	public Employee addEmployee(Employee d) {
		employees.add(d);
		return d;
	}

	public Employee findById(Long id) {
		return employees.stream().filter(employee -> employee.id().equals(id)).findFirst().orElseThrow();
	}

	public List<Employee> findAll() {
		return employees;
	}
	
	public List<Employee> findEmployeeByDepartmentId(Long deptId) {
		return employees.stream().filter(employee -> (employee.departmentId() == deptId)).toList();
	}

}
