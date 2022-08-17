package com.globallogic.sms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	BirthCertificate birthCertificate;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Address> address;
	
	@ManyToMany(cascade=CascadeType.ALL)
	List<Teacher> teacher;

	public Student() {
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BirthCertificate getBirthCertificate() {
		return birthCertificate;
	}
	public void setBirthCertificate(BirthCertificate birthCertificate) {
		this.birthCertificate = birthCertificate;
	}
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthCertificate=" + birthCertificate + ", address="
				+ address + ", teacher=" + teacher + "]";
	}
	
}