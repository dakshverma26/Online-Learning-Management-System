package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CourseRepositary;
import com.example.demo.model.Course;

@RequestMapping("/course")
@RestController
public class CourseController
{
	
	private final CourseRepositary courserepo;

	public CourseController(CourseRepositary courserepo) {
		super();
		this.courserepo = courserepo;
	}
	
	@PostMapping
	public Course save(@RequestBody Course course)
	{
		return courserepo.save(course);
 	}
	
	@GetMapping
	public List<Course> findAllById()
	{
		return courserepo.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Course findById(@PathVariable("id") int id )
	{
		return courserepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public Course update(@RequestBody Course course)
	{
		Course cou = courserepo.findById(course.getId()).orElse(null);
		
		if(cou == null)
		{
			return null;
		}
		return courserepo.save(course);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id")int id)
	{
		Course cou = courserepo.findById(id).orElse(null);
		
		if(cou==null)
		{
			return "Course Not Found!!";
		}
		courserepo.deleteById(id);
		return "Course Deleted";
	}
	
}
