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

import com.globallogic.sms.entity.Teacher;
import com.globallogic.sms.repository.TeacherRepository;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	TeacherRepository teachRepo;
	
	//GET requests
	
	//GET request for fetching all Teacher
	@GetMapping
	public List<Teacher> getAllTeachers(){
		return teachRepo.findAll();
	}
	
	//GET request for fetching a Teacher by ID
	@GetMapping("/{id}")
	public Teacher getTeacherById(@PathVariable int id) throws Exception {
		Optional<Teacher> response = teachRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Teacher not found");
		return response.get();
	}
	
	//GET request for fetching a Teacher by name
	@GetMapping("/name/{name}")
	public Teacher getTeacherById(@PathVariable String name) throws Exception {
		Teacher response = teachRepo.findTeacherByName(name);
		if(response==null)
			throw new Exception("Teacher not found");
		return response;
	}
	
	
	//POST requests
	
	//POST request to add a Teacher
	@PostMapping
	public Teacher addTeacher(@RequestBody Teacher teacher) {
		return teachRepo.save(teacher);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a Teacher by id
	@DeleteMapping("/{id}")
	public String deleteTeacherById(@PathVariable int id) {
		teachRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a Teacher
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public Teacher updateTeacher(@RequestBody Teacher teacher) {
		return teachRepo.save(teacher);
	}

	
	//PUT request to update a Teacher by id
	//If any field is missing, it will take default value for the respective field
	@PutMapping("/{id}")
	public Teacher updateTeacherById(@RequestBody Teacher changes,@PathVariable int id) throws Exception {
		Teacher teacher = getTeacherById(id);
		if(changes.getName()!=null)
			teacher.setName(changes.getName());
		if(changes.getSubject()!=null)
			teacher.setSubject(changes.getSubject());
		return teachRepo.save(teacher);
	}
}