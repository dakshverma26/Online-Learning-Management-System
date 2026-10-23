package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;

@RequestMapping("/student")
@RestController
public class StudentController 
{
	
	private final StudentRepo studentrepo;
	
	
	
	public StudentController(StudentRepo studentrepo) {
		super();
		this.studentrepo = studentrepo;
	}



	@PostMapping
	public Student save(@RequestBody Student student)
	{
		return  studentrepo.save(student);
	}
	
	
	@GetMapping
	public List<Student> findAll()
	{
		return studentrepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Student findById(@PathVariable("id") int id)
	{
		return studentrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public String Update(@RequestParam("id") int id,@RequestParam("email") String email,@RequestParam("name")String name,@RequestParam("mobile")String mobile  )
	{
		Student stu  = studentrepo.findById(id).orElse(null);
		
		if(stu==null)
		{
			return "Student Not Exist!! ";
		}
		else
		{
			
		 studentrepo.save(stu);
		}
		 return "Student Data Updated";
		
	}
	
	@DeleteMapping("/{id}")
	public String DeleteStudent(@PathVariable("id")  int id)
	{
		Student student = studentrepo.findById(id).orElse(null);
		
		if(student!=null)
		{
			studentrepo.deleteById(id);
			return "Student Removed";
		}
		return "Student Doent Exist";
	}

}
