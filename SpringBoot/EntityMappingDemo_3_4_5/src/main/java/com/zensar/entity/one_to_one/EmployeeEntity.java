package com.zensar.entity.one_to_one;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long employeeId;

	@Column(name="name")
	private String name;
	
	@Column(name="sal")
	private double sal;

	@OneToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="profile_id") //FK column
	private ProfileEntity profile;
	
	public EmployeeEntity() {
		
	}
	
	public EmployeeEntity(String name, double sal) {
		this.name = name;
		this.sal = sal;
	}

	public EmployeeEntity(String name, double sal, ProfileEntity profile) {
		this.name = name;
		this.sal = sal;
		this.profile = profile;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}
	public String toString() {
		return employeeId + " - " + name + " - " + sal + " - profile: " + profile;
	}
	
}
/*
CREATE TABLE EMPLOYEE (ID NUMBER(4) PRIMARY KEY, NAME VARCHAR2(15), SAL NUMBER(7,2), 
	DEPARTMENT_ID NUMBER(4) NOT NULL,
	CONSTRAINT FK_DEPARTMENT FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID))
	 
*/