package com.example.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	private String  adminFname;
	private String  adminLname;
	private String  adminTel;
	
	@OneToOne
	@JoinColumn(name ="userId")
	private User user;

	public Admin() {
		super();
	}

	public Admin(Integer adminId, String adminFname, String adminLname, String adminTel, User user) {
		super();
		this.adminId = adminId;
		this.adminFname = adminFname;
		this.adminLname = adminLname;
		this.adminTel = adminTel;
		this.user = user;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminFname() {
		return adminFname;
	}

	public void setAdminFname(String adminFname) {
		this.adminFname = adminFname;
	}

	public String getAdminLname() {
		return adminLname;
	}

	public void setAdminLname(String adminLname) {
		this.adminLname = adminLname;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

	
}
