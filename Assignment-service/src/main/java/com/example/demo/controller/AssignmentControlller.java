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

import com.example.demo.client.CourseClient;
import com.example.demo.client.InstructorClient;
import com.example.demo.dao.AssignmentRepositary;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.InstructorDto;
import com.example.demo.model.Assignment;

@RequestMapping("/assignment")
@RestController
public class AssignmentControlller 
{
	private final AssignmentRepositary assignrepo;
	private final CourseClient courseclient;
	private final InstructorClient instrucclient;
	public AssignmentControlller(AssignmentRepositary assignrepo, CourseClient courseclient,
			InstructorClient instrucclient) {
		super();
		this.assignrepo = assignrepo;
		this.courseclient = courseclient;
		this.instrucclient = instrucclient;
	}
	
	
	@PostMapping
	public String save(@RequestBody Assignment  assign)
	{
		CourseDto course = courseclient.findById(assign.getCourseid());
		if(course ==null)
			return "Course Id Doesnt Exist!!";
		
		InstructorDto ins = instrucclient.findById(assign.getInstructorid());
		
		if(ins == null)
			return "Assignment Id Doesnt Exist!!!";
		
		
	    assign.setDueDate(LocalDate.now());
		
	    Assignment assi  = assignrepo.save(assign);
	    return ""+assi+"Data Inserted";
		
	}
	
	
	@GetMapping
	public List<Assignment> findAll()
	{
		return assignrepo.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Assignment findById(@PathVariable("id") int id )
	{
	     return 	 assignrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public String update(@RequestBody Assignment assign)
	{
		Assignment assignc = assignrepo.findById(assign.getId()).orElse(null);
		if(assign!=null)
		{
		    if(assignc.getCourseid()!=assign.getCourseid())
			return "Course Id Doens Match";
		    else if(assignc.getInstructorid()!=assign.getInstructorid())
		    	return "Instruction Id Doesnt Match";
		    
		    assignc = assignrepo.save(assign);
		    return ""+assignc+"\n Data Updated ";
		  
		}
		return "Assignment Id Doesnt Exist";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id")int id)
	{
		Assignment assign = assignrepo.findById(id).orElse(null);
		if(assign==null)
			return "Id Doesnt Exist";
		assignrepo.deleteById(assign.getId());
		return "Id Successfull Deleted";
	}
 	

}

















