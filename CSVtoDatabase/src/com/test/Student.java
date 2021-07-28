package com.test;

public class Student
{
	private String studRollNo;
	private String studName;
	private String studCity;
	private Float studMarks;
	public String getStudRollNo() {
		return studRollNo;
	}
	public void setStudRollNo(String studRollNo) {
		this.studRollNo = studRollNo;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public String getStudCity() {
		return studCity;
	}
	public void setStudCity(String studCity) {
		this.studCity = studCity;
	}
	public Float getStudMarks() {
		return studMarks;
	}
	public void setStudMarks(Float studMarks) {
		this.studMarks = studMarks;
	}
	@Override
	public String toString() {
		return "Student [studRollNo=" + studRollNo + ", studName=" + studName + ", studCity=" + studCity
				+ ", studMarks=" + studMarks + "]";
	} 
	
}