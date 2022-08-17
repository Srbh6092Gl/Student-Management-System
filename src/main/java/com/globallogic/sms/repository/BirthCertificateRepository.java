package com.globallogic.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.sms.entity.BirthCertificate;

public interface BirthCertificateRepository extends JpaRepository<BirthCertificate, Integer>{
	public BirthCertificate findBirthCertificateByCertificateId(String certId);
}