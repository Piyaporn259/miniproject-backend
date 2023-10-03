package com.example.demo.model;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="status")
public class Status {


		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer statusId;
		private String  statusName;
		
		
		
		public Status() {
			super();
		}



		public Status(Integer statusId, String statusName) {
			super();
			this.statusId = statusId;
			this.statusName = statusName;
		}



		public Integer getStatusId() {
			return statusId;
		}



		public void setStatusId(Integer statusId) {
			this.statusId = statusId;
		}



		public String getStatusName() {
			return statusName;
		}



		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		

		
		

		
}



