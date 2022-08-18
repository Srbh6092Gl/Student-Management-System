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

import com.globallogic.sms.entity.Address;
import com.globallogic.sms.repository.AddressRepository;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressRepository addRepo;
	
	//GET requests
	
	//GET request for fetching all Address
	@GetMapping
	public List<Address> getAllAddresss(){
		return addRepo.findAll();
	}
	
	//GET request for fetching a Address by ID
	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable int id) throws Exception {
		Optional<Address> response = addRepo.findById(id);
		if(response.isEmpty())
			throw new Exception("Address not found");
		return response.get();
	}
	
	//GET request for fetching a Address by pincode
	@GetMapping("/pincode/{pincode}")
	public Address getAddressByPincode(@PathVariable("pincode") int pincode) throws Exception {
		Address response = addRepo.findAddressByPincode(pincode);
		if(response==null)
			throw new Exception("Address not found");
		return response;
	}
	
	
	//POST requests
	
	//POST request to add a Address
	@PostMapping
	public Address addAddress(@RequestBody Address Address) {
		return addRepo.save(Address);
	}
	
	
	//DELETE requests
	
	//DELETE request to delete a Address by id
	@DeleteMapping("/{id}")
	public String deleteAddressById(@PathVariable int id) {
		addRepo.deleteById(id);
		return "Delete ID="+id+": SUCCESS";
	}
	
	//PUT requests
	
	//PUT request to update a Address
	//If any field is missing, it will take default value for the respective field
	@PutMapping
	public Address updateAddress(@RequestBody Address Address) {
		return addRepo.save(Address);
	}
	
	//PUT request to update a Address by id
	//If any field is missing, it will take default value for the respective field
	@PutMapping("/{id}")
	public Address updateAddressById(@RequestBody Address changes,@PathVariable int id) throws Exception {
		Address address = getAddressById(id);
		if(changes.getCity()!=null)
			address.setCity(changes.getCity());
		if(changes.getPincode()!=0)
			address.setPincode(changes.getPincode());
		return addRepo.save(address);
	}
	
}
