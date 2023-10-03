package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserve")
public class Reserve {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reserveId;
	private Date reserveDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "areaId")
	private Area area;

	public Reserve() {
		super();
	}

	public Reserve(Integer reserveId, Date reserveDate, User user, Area area) {
		super();
		this.reserveId = reserveId;
		this.reserveDate = reserveDate;
		this.user = user;
		this.area = area;
	}

	public Integer getReserveId() {
		return reserveId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setOwner(User user) {
		this.user = user;
	}


	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	
}