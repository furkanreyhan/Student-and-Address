package com.furkanreyhan.studentservice.service;

import com.furkanreyhan.studentservice.entity.Student;
import com.furkanreyhan.studentservice.feignclients.AddressFeignClient;
import com.furkanreyhan.studentservice.repository.StudentRepository;
import com.furkanreyhan.studentservice.request.CreateStudentRequest;
import com.furkanreyhan.studentservice.response.AddressResponse;
import com.furkanreyhan.studentservice.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

//	@Autowired
//	WebClient webClient;

	@Autowired
	AddressFeignClient addressFeignClient;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());

		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);

		//webclient kullanarak yaptığımız çağrı
		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);

		//webclient kullanarak yaptığımız çağrı
		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

		return studentResponse;

	}

	//mainde tanımladığımız webclientı burada adres ayrıntılarını address serviceden çağırmak için kullanıyoruz.
//	public AddressResponse getAddressById(long addressId){
//		Mono<AddressResponse> addressResponse =
//				webClient.get().uri("/getById/" + addressId)//adres controllerdaki get isteğinin urlsi.
//						.retrieve().bodyToMono(AddressResponse.class);
//		return addressResponse.block();
//	}
}
