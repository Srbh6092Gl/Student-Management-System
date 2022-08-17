package com.globallogic.sms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.sms.entity.Student;
import com.globallogic.sms.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentRepository stuRepo;
	
	//GET requests
	
	//GET request for fetching all Student
	@GetMapping
	public List<Student> getAllStudents(){
		return stuRepo.findAll();
	}
	
	//GET request for fetching a Student by ID
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable int id) throws Exception {
		Optional<Student> response = stuRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Student not found");
		return response.get();
	}
	
	//GET request for fetching a Student by name
	@GetMapping("/name/{name}")
	public Student getStudentByName(@PathVariable String name) throws Exception {
		Student response = stuRepo.findStudentByName(name);
		if(response==null)
			throw new Exception("Student not found");
		return response;
	}
	
	
	//POST requests
	
	//POST request to add a Student
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return stuRepo.save(student);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a Student by id
	@DeleteMapping("/{id}")
	public String deleteStudentById(@PathVariable int id) throws Exception {
		if(getStudentById(id)!=null);
		stuRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a Student
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public Student updateStudent(@RequestBody Student student) {
		return stuRepo.save(student);
	}
	
	//PUT request to update a Student
	//If any field is missing, it will take default value for the respective field
	@PutMapping("/{id}")
	public Student updateStudentById(@RequestBody Student changes, @PathVariable int id) throws Exception {
		Student student= getStudentById(id);
		if(changes.getName()!=null)
			student.setName(changes.getName());
		if(changes.getAddress()!=null)
			student.setAddress(changes.getAddress());
		if(changes.getBirthCertificate()!=null)
			student.setBirthCertificate(changes.getBirthCertificate());
		if(changes.getTeacher()!=null)
			student.setTeacher(changes.getTeacher());
		return stuRepo.save(student);
	}

}
