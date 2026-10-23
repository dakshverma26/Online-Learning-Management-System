package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.InstructorRepositary;
import com.example.demo.model.Instructor;

@RequestMapping("/instructor")
@RestController
public class InstructorController 
{
	private final InstructorRepositary instructorrepo;
	
	
	
	
	public InstructorController(InstructorRepositary instructorrepo) {
		super();
		this.instructorrepo = instructorrepo;
	}




	@PostMapping
	public Instructor save(@RequestBody Instructor instr)
	{
		return instructorrepo.save(instr);
	}
	
	@GetMapping
	public List<Instructor> findAll()
	{
		return instructorrepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Instructor findById(@PathVariable("id")int id)
	{
		return instructorrepo.findById(id).orElse(null);
	}
	
	@PutMapping
	public Instructor update(@RequestBody Instructor ins)
	{
		Instructor inst = instructorrepo.findById(ins.getId()).orElse(null);
		
		if(inst != null )
			return instructorrepo.save(ins);
		return null;
	}
	
	@DeleteMapping("/{id}")
	public String Delete(@PathVariable("id") int id)
	{
		Instructor inst = instructorrepo.findById(id).orElse(null);
		
		if(inst==null)
			return "Instructor Not Found!!";
		instructorrepo.deleteById(inst.getId());
		return "Instructor  Deleted";
	}
	

}




















