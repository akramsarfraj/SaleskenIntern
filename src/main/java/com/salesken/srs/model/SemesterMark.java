package com.salesken.srs.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class SemesterMark implements Comparable<SemesterMark> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="markid")
	private int markid;
	
	@Column(name="semid")
	private int semesterNo;
	
	@Column(name="english")
	private int english;
	
	@Column(name="math")
	private int math;
	
	@Column(name="science")
	private int science;

	public SemesterMark() {

	}

	public SemesterMark(int semesterNo, int english, int math, int science) {
	
		this.semesterNo = semesterNo;
		this.english = english;
		this.math = math;
		this.science = science;
	}

	public int getSemesterNo() {
		return semesterNo;
	}

	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	@Override
	public String toString() {
		return "SemesterMark [markid=" + markid + ", semesterNo=" + semesterNo + ", english=" + english + ", math="
				+ math + ", science=" + science + "]";
	}

	@Override
	public int compareTo(SemesterMark semesterMark) {
		// TODO Auto-generated method stub
		
		return semesterMark.getSemesterNo()- this.semesterNo;
	}

	

}
