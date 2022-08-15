package com.springboot.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employeemanagement.entity.Employee;
import com.springboot.employeemanagement.entity.Role;
import com.springboot.employeemanagement.entity.User;
import com.springboot.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saverole")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}
	
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		return employeeService.saveEmployee(employee);
	}
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		int id = employee.getId();
		Employee emp = employeeService.getEmployeeById(id);
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		return employeeService.saveEmployee(emp);
	}
	
	@GetMapping("/list")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/sort")
	public List<Employee> getEmployeesSortedByFirstName(@RequestParam Direction order) {
		return employeeService.orderByName(order);
	}
		
	@GetMapping("/get/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.getEmployeeById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.getEmployeeById(employeeId);
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}
	
	@GetMapping("/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.getEmployeeByName(firstName);
	}

	


}
