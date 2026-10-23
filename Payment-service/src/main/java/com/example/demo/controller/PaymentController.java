package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.PaymentRepositary;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Payment;

@RequestMapping("/payment")
@RestController
public class PaymentController 
{
	private final StudentClient  studentclient;
	private final EnrollmentClient enrollmentclient;
	private final PaymentRepositary paymentrepo;
	public PaymentController(StudentClient studentclient, EnrollmentClient enrollmentclient,
			PaymentRepositary paymentrepo) {
		super();
		this.studentclient = studentclient;
		this.enrollmentclient = enrollmentclient;
		this.paymentrepo = paymentrepo;
	}
	
	
	
	
	@PostMapping
	public String save(@RequestBody Payment payment)
	{
		StudentDto student = studentclient.findById(payment.getStudentid());
		
		if(student==null)
			return "Student Id Doesnt Exist";
		
		EnrollmentDto enrol  = enrollmentclient.findById(payment.getEnrollmentid());
		if(enrol==null)
			 return "Enrollment Id Donst Exist!!";
		
		payment.setPaymentDate(LocalDate.now());
		
		Payment data = paymentrepo.save(payment);
		
		return ""+ data +"\n Data Inserted";
		
	}
	
	
	@GetMapping
	public List<Payment> findAll()
	{
		return paymentrepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Payment findById(@PathVariable("id")int id)
	{
		return paymentrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public String update(@RequestBody Payment payment)
	{
		Payment paymentcheck  = paymentrepo.findById(payment.getId()).orElse(null);
		
		if(paymentcheck != null)
		{
			if(paymentcheck.getStudentid()!=payment.getStudentid())
				return "Student Id Doenst Match";
			else if( paymentcheck.getEnrollmentid() != payment.getEnrollmentid() )
				return "Enrollment Id Doesnt Match";
			else if( paymentcheck.getAmount() != payment.getAmount() )
				return "Enrollment Id Amount Match";
			
			
			Payment payment2 =  paymentrepo.save(payment);
			return ""+payment2+"\n Data Updated";	
			
		}
		else
			return "Payment Id Doesnt Exist!!";
		
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id")int id)
	{
		Payment payment = paymentrepo.findById(id).orElse(null);
		
		if(payment==null)
			return "Payment Id Doent Exist!!";
	     paymentrepo.deleteById(payment.getId());
		return "Payment Data Deleted";
	}
	

}















