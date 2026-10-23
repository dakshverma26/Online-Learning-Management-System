package com.example.demo.dto;

import java.time.LocalDate;

public class EnrollmentDto
{

	private int id;
	
	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", studentid=" + studentid + ", courseid=" + courseid + ", enrollmentDate="
				+ enrollmentDate + ", status=" + status + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private  int studentid;
	
	private int  courseid;
	
	private LocalDate enrollmentDate;
	
	private String status;
	

}
