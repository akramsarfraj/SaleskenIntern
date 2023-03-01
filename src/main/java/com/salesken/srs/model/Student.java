package com.salesken.srs.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@jakarta.persistence.Table
public class Student{

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="city")
	private String city;
	
	@OneToMany
	@JoinTable(name="student_semestermark", joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="markid"))
	private List<SemesterMark> sem ;
	
	public Student(){
		
	}

	public Student(int id, String name, String city ) {
	
		this.id = id;
		this.name = name;
		this.city = city;
		//this.sem.add(sem);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	
   
 	public List<SemesterMark> getSem() {
		return sem;
	}

	public void setSem(List<SemesterMark> sem) {
		this.sem = sem;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

	
	
	
	
	
}
