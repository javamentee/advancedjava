package com.microservices.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.model.Employee;
import com.microservices.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository empRepository;

	@PostMapping("")
	public Employee addEmployee(@RequestBody Employee e) {
		logger.info("Adding employee in department controller");
		return empRepository.addEmployee(e);
	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable Long id) {
		logger.info("Fetching Employee by department id in Employee controller");
		return empRepository.findById(id);
	}

	@GetMapping("")
	public List<Employee> getAll() {
		logger.info("Fetching all Employee in Employee controller");
		return empRepository.findAll();
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> getEmployeeByDepartmentId(@PathVariable Long departmentId) {
		logger.info("Fetching  department information based on department id in Employee controller");
		return empRepository.findEmployeeByDepartmentId(departmentId);
	}
}
