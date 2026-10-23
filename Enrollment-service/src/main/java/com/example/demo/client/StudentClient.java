package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.StudentDto;


@FeignClient(name="student-service")
public interface StudentClient 
{
	@GetMapping("/student/{id}")
	public StudentDto findById(@PathVariable("id") int id);

}
