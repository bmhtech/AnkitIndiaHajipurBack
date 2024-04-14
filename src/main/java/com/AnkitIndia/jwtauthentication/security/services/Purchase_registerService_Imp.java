package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Purchase_register;
import com.AnkitIndia.jwtauthentication.repository.Purchase_registerRepository;

@Service
public class Purchase_registerService_Imp {
	
	@Autowired
	Purchase_registerRepository purchase_registerRepository;
	
	public List<Purchase_register> findAll()
	{
		return purchase_registerRepository.findAll();
	}

}
