package com.example.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="area")
public class Area {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer areaId;
		private String  areaName;
		private String  areaPrice;
		
		
		@ManyToOne
		@JoinColumn(name = "statusId")
		private Status status;
		
		public Area() {
			super();
		}

		public Area(Integer areaId, String areaName, String areaPrice, Status status) {
			super();
			this.areaId = areaId;
			this.areaName = areaName;
			this.areaPrice = areaPrice;
			this.status = status;
		}

		public Integer getAreaId() {
			return areaId;
		}

		public void setAreaId(Integer areaId) {
			this.areaId = areaId;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public String getAreaPrice() {
			return areaPrice;
		}

		public void setAreaPrice(String areaPrice) {
			this.areaPrice = areaPrice;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}
		
		
}