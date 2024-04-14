package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AnkitIndia.jwtauthentication.model.Sales_register;
import com.AnkitIndia.jwtauthentication.repository.Sales_registerRepository;

@Service
public class Sales_registerService_Imp implements Sales_registerService{

	
	@Autowired
	Sales_registerRepository sales_registerRepository;
	
	@Override
	public List < Sales_register > findAll() {
        return sales_registerRepository.findAll();
    }
  
}
