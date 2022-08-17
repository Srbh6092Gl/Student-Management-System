package com.globallogic.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public Student findStudentByName(String name);
}