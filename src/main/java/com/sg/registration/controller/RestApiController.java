package com.sg.registration.controller;

import com.sg.registration.model.Employee;
import com.sg.registration.service.EmployeeService;
import com.sg.registration.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	EmployeeService employeeService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Employees---------------------------------------------

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		if (employees.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	// -------------------Retrieve Single Employee------------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
		logger.info("Fetching Employee with id {}", id);
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Employee with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	// -------------------Create a Employee-------------------------------------------

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Employee : {}", employee);

		if (employeeService.isEmployeeExist(employee)) {
			logger.error("Unable to create. A Employee with name {} already exist", employee.getFirstName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Employee with name " + employee.getFirstName() + " already exist."),HttpStatus.CONFLICT);
		}
		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employee/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Employee ------------------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		logger.info("Updating Employee with id {}", id);

		Employee currentEmployee = employeeService.findById(id);

		if (currentEmployee == null) {
			logger.error("Unable to update. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentEmployee.setFirstName(employee.getFirstName());
		currentEmployee.setLastName(employee.getLastName());
		currentEmployee.setDepartment(employee.getDepartment());
		currentEmployee.setDob(employee.getDob());
		currentEmployee.setGender(employee.getGender());

		employeeService.updateEmployee(currentEmployee);
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}

	// ------------------- Delete a Employee-----------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Employee with id {}", id);

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Unable to delete. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Employees-----------------------------

	@RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteAllEmployees() {
		logger.info("Deleting All Employees");

		employeeService.deleteAllEmployees();
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}