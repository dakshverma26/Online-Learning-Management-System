package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int studentid;
	private int enrollmentid;
	private int amount;
	private LocalDate paymentDate;
	private String status;
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
	public int getEnrollmentid() {
		return enrollmentid;
	}
	public void setEnrollmentid(int enrollmentid) {
		this.enrollmentid = enrollmentid;
	}
	public int getAmount() {
		return amount;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", studentid=" + studentid + ", enrollmentid=" + enrollmentid + ", amount="
				+ amount + ", paymentDate=" + paymentDate + ", status=" + status + "]";
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
