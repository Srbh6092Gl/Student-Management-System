package com.globallogic.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.sms.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	public Teacher findTeacherByName(String name);

}