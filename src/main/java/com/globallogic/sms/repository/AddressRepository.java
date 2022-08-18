package com.globallogic.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.sms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	public Address findAddressByPincode(int pin);
	
}