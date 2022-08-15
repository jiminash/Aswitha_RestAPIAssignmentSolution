package com.springboot.employeemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userroles",
	joinColumns = @JoinColumn(name = "userid"),
	inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<Role> roles = new ArrayList<>();

}
