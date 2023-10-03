package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="owner")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ownerId;
	private String  ownerFname;
	private String  ownerLname;
	private String  ownerTel;
	
	@OneToOne
	@JoinColumn(name ="userId")
	private User user;
	
	public Owner() {
		super();
	}

	public Owner(Integer ownerId, String ownerFname, String ownerLname, String ownerTel, User user) {
		super();
		this.ownerId = ownerId;
		this.ownerFname = ownerFname;
		this.ownerLname = ownerLname;
		this.ownerTel = ownerTel;
		this.user = user;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerFname() {
		return ownerFname;
	}

	public void setOwnerFname(String ownerFname) {
		this.ownerFname = ownerFname;
	}

	public String getOwnerLname() {
		return ownerLname;
	}

	public void setOwnerLname(String ownerLname) {
		this.ownerLname = ownerLname;
	}

	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
