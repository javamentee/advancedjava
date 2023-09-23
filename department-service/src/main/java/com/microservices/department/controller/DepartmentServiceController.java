package com.microservices.department.controller;

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

import com.microservices.department.client.EmployeeClient;
import com.microservices.department.model.Department;
import com.microservices.department.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentServiceController {

	final static Logger logger = LoggerFactory.getLogger(DepartmentServiceController.class);

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private EmployeeClient employeeClient;

	@PostMapping("")
	public Department addDepartment(@RequestBody Department d) {
		logger.info("Adding Department in department controller");
		return deptRepository.addDepartment(d);
	}

	@GetMapping("/{id}")
	public Department getById(@PathVariable Long id) {
		logger.info("Fetching Department by department id in department controller");
		return deptRepository.findById(id);
	}

	@GetMapping("")
	public List<Department> getAll() {
		logger.info("Fetching all Departments in department controller");
		return deptRepository.findAll();
	}

	@GetMapping("/with-employees")
	public List<Department> getAllWithEmployee() {
		logger.info("Fetching all employee with department id in department controller");
		List<Department> departments = deptRepository.findAll();
		departments.forEach(
				department -> department.setEmpList(employeeClient.getEmployeeByDepartmentId(department.getId())));
		return departments;
	}

}
