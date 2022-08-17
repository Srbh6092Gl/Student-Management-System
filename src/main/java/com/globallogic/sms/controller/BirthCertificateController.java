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

import com.globallogic.sms.entity.BirthCertificate;
import com.globallogic.sms.repository.BirthCertificateRepository;

@RestController
@RequestMapping("/birthcertificate")
public class BirthCertificateController {
	
	@Autowired
	BirthCertificateRepository bCertRepo;
	
	//GET requests
	
	//GET request for fetching all BirthCertificate
	@GetMapping
	public List<BirthCertificate> getAllBirthCertificates(){
		return bCertRepo.findAll();
	}
	
	//GET request for fetching a BirthCertificate by ID
	@GetMapping("/{id}")
	public BirthCertificate getBirthCertificateById(@PathVariable int id) throws Exception {
		Optional<BirthCertificate> response = bCertRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("BirthCertificate not found");
		return response.get();
	}
	
	//GET request for fetching a BirthCertificate by name
	@GetMapping("/certificateId/{certId}")
	public BirthCertificate getBirthCertificateByName(@PathVariable String certId) throws Exception {
		BirthCertificate response = bCertRepo.findBirthCertificateByCertificateId(certId);
		if(response==null)
			throw new Exception("BirthCertificate not found");
		return response;
	}
	
	
	//POST requests
	
	//POST request to add a BirthCertificate
	@PostMapping
	public BirthCertificate addBirthCertificate(@RequestBody BirthCertificate BirthCertificate) {
		return bCertRepo.save(BirthCertificate);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a BirthCertificate by id
	@DeleteMapping("/{id}")
	public String deleteBirthCertificateById(@PathVariable int id) {
		bCertRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a BirthCertificate
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public BirthCertificate updateBirthCertificate(@RequestBody BirthCertificate BirthCertificate) {
		return bCertRepo.save(BirthCertificate);
	}
	
	//PUT request to update a BirthCertificate by id
	//If any field is missing, it will take default value for the respective field
	@PutMapping("/{id}")
	public BirthCertificate updateBirthCertificateById(@RequestBody BirthCertificate changes,@PathVariable int id) throws Exception {
		BirthCertificate birthCertificate = getBirthCertificateById(id);
		if(changes.getCertificateId()!=null)
			birthCertificate.setCertificateId(changes.getCertificateId());
		return bCertRepo.save(birthCertificate);
	}
	
}