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

import com.example.demo.client.AssignmentClient;
import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.ResultRepositary;
import com.example.demo.dto.AssignmentDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Result;

@RequestMapping("/result")
@RestController
public class ResultController 
{
	private StudentClient studentclient;
	private EnrollmentClient enrolclient;
	private AssignmentClient assignclient;
	
	private ResultRepositary resultrepo;

	public ResultController(StudentClient studentclient, EnrollmentClient enrolclient, AssignmentClient assignclient,
			ResultRepositary resultrepo) {
		super();
		this.studentclient = studentclient;
		this.enrolclient = enrolclient;
		this.assignclient = assignclient;
		this.resultrepo = resultrepo;
	}
	
	
	@PostMapping
	public String save(@RequestBody Result result)
	{
		StudentDto student = studentclient.findById(result.getStudentid());
		
		if(student==null)
			return "Student Id Doenst Exist";
		
		EnrollmentDto enrol = enrolclient.findById(result.getEnrollmentid());
		
		if(enrol == null)
			 return "Enrollment Id Doesnt Exist";
		
		AssignmentDto assign  =  assignclient.findById(result.getAssignmentid());
		
		if(assign==null)
			return "Assignment Id Doest Exist";
		
		result.setResultDate(LocalDate.now());
		resultrepo.save(result);
		
		return ""+result+"\n Data Inserted";
	}
	
	
	@GetMapping
	public List<Result> findAll()
	{
		return resultrepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Result findById(@PathVariable("id")int id)
	{
		return resultrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public String Update(@RequestBody Result  result)
	{
	     Result resultc =  resultrepo.findById(result.getId()).orElse(null);
	     
	     if(resultc != null)
	     {
	    	 if( result.getStudentid() != resultc.getStudentid() )
	    		 return "Student Doesnt Match";
	    	 else if(result.getAssignmentid()!=resultc.getAssignmentid())
	    		 return "Assignment ID Doenst Match";
	    	 else if( result.getEnrollmentid() != resultc.getEnrollmentid() )
	    		 return "Enrollement Id DOesnt Match";
	    	 
	    	 
	    	 result.setResultDate(LocalDate.now());
	    	 
	    	 resultc = resultrepo.save(result);
	    	 
	    	 return ""+resultc+"\n Data Updated";
	    	 
	    	 
	     }
	     return "Result Id Doesnt Exists!!!";
	}
	
	
	@DeleteMapping("/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		Result result = resultrepo.findById(id).orElse(null);
		
		if(result==null)
			return "Result Id Doesnt Exist";
		return "Id Succesfully Deleted ";
	}
	
	
	
	
	

}





























