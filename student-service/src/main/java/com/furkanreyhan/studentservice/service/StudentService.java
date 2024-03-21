package com.furkanreyhan.studentservice.service;

import com.furkanreyhan.studentservice.entity.Student;
import com.furkanreyhan.studentservice.repository.StudentRepository;
import com.furkanreyhan.studentservice.request.CreateStudentRequest;
import com.furkanreyhan.studentservice.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class StudentService {

	StudentRepository studentRepository;

	@Autowired
	WebClient webClient;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());

		student = studentRepository.save(student);

		return new StudentResponse(student);
	}
	
	public StudentResponse getById (long id) {
		Optional<Student> student = studentRepository.findById(id);

		return new StudentResponse(student.get());
	}
}
