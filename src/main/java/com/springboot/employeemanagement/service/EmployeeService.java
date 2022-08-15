package com.springboot.employeemanagement.service;


import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.springboot.employeemanagement.entity.Employee;
import com.springboot.employeemanagement.entity.Role;
import com.springboot.employeemanagement.entity.User;

public interface EmployeeService {

	Role saveRole(Role role);
	User saveUser(User user);
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployee();
	Employee getEmployeeById(int id);
	void deleteById(int id);
	List<Employee> getEmployeeByName(String username);
	List<Employee> orderByName(Direction order);

}
