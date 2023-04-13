package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.model.Employee;
@Service
public class EmployeeService {
	
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees=new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add);
		return employees;
	}
	
	public Employee getEmployee( Integer id) {
		
		return employeeRepository.findById(id).get();
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee( Integer id, Employee employee) {
		employeeRepository.save(employee);
		
	}

	public void deleteEmployee( Integer id) {
		employeeRepository.deleteById(id);
		
	}
	
}

