package com.furkanreyhan.studentservice.controller;

import com.furkanreyhan.studentservice.request.CreateStudentRequest;
import com.furkanreyhan.studentservice.response.StudentResponse;
import com.furkanreyhan.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.createStudent(createStudentRequest);
	}
	
	@GetMapping("getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}
	
}
