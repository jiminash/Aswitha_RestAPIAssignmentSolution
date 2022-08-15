package com.springboot.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employeemanagement.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
