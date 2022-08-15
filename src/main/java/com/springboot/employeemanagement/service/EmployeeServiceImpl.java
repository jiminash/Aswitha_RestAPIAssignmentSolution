package com.springboot.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.employeemanagement.dao.EmployeeRepository;
import com.springboot.employeemanagement.dao.RoleRepository;
import com.springboot.employeemanagement.dao.UserRepository;
import com.springboot.employeemanagement.entity.Employee;
import com.springboot.employeemanagement.entity.Role;
import com.springboot.employeemanagement.entity.User;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return theEmployee;
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeeByName(String firstname) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstname);
	}

	@Override
	public List<Employee> orderByName(Direction order) {
		return employeeRepository.findAll(Sort.by(order,"firstName"));
	}
	
	

}
