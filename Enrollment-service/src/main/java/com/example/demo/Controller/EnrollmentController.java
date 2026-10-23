package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.CourseClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.EnrollmentRepositary;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Enrollment;

@RequestMapping("/enrollment")
@RestController
public class EnrollmentController 
{
	private final StudentClient studentclient;
	private final CourseClient courseclient;
	private final EnrollmentRepositary enrollmentrepo;
	
	public EnrollmentController(StudentClient studentclient, CourseClient courseclient,
			EnrollmentRepositary enrollmentrepo) {
		super();
		this.studentclient = studentclient;
		this.courseclient = courseclient;
		this.enrollmentrepo = enrollmentrepo;
	}
	
	
	@PostMapping
	public String save(@RequestParam("studentid") int studentid , @RequestParam("courseid") int courseid)
	{
		StudentDto student =  (StudentDto)studentclient.findById(studentid);
		
		if(student==null)
			return "Student Id Doesnt Exist!!";
		
		CourseDto course = (CourseDto)courseclient.findById(courseid);
			
		if(course==null)
			return "Course Doesnt Exist!!";
		
	    Enrollment enrol = 	new Enrollment();
	    enrol.setStudentid(studentid);
	    enrol.setCourseid(courseid);
	    enrol.setEnrollmentDate( LocalDate.now());
	    enrol.setStatus("ongoing");
	    
	    Enrollment enrol2 = enrollmentrepo.save(enrol);
		return  ""+ enrol2 + "\n  Data Saved";
	}
	
	
	@GetMapping
	public List<Enrollment> FindAll()
	{
		return enrollmentrepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Enrollment  findById(@PathVariable("id")int id)
	{
		return enrollmentrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public String update(@RequestBody Enrollment enrol )
	{
		Enrollment enroll = enrollmentrepo.findById(enrol.getId()).orElse(null);
		if(enroll==null)
			return "Enrollment Id Doesnt Exist!!";
		
		enrol.setEnrollmentDate(enroll.getEnrollmentDate());
		
		 enroll = enrollmentrepo.save(enrol); 
		return ""+enroll+"\n Data Updated";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id")int id)
	{
		Enrollment enrol =  enrollmentrepo.findById(id).orElse(null);
		
		if(enrol==null)
			return "Enrollment Doesnt Exist";
		
		enrollmentrepo.deleteById(id);
		return "Enrollment Deleted";
	}

}
















