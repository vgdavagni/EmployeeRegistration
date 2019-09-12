package com.sg.registration.service;

import com.sg.registration.model.Employee;
import com.sg.registration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

	public Employee findByName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee){
		saveEmployee(employee);
	}

	public void deleteEmployeeById(Long id){
		employeeRepository.delete(id);
	}

	public void deleteAllEmployees(){
		employeeRepository.deleteAll();
	}

	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}

	public boolean isEmployeeExist(Employee employee) {
		return findByName(employee.getFirstName()) != null;
	}

}
