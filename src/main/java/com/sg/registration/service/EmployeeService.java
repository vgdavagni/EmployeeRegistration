package com.sg.registration.service;

import com.sg.registration.model.Employee;

import java.util.List;

public interface EmployeeService {
	
	Employee findById(Long id);

	Employee findByName(String name);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

	List<Employee> findAllEmployees();

	boolean isEmployeeExist(Employee employee);
}