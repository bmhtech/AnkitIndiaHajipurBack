package com.AnkitIndia.jwtauthentication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Uniqueid;
//import com.AnkitIndia.jwtauthentication.repository.UniqueidRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.UniqueIdDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;


@Service
public class UniqueidService_Imp implements UniqueidService{

	//@Autowired
	//UniqueidRepository uniqueidRepository;
	
	/*public UniqueIdDTO getUniqueId() {
		
		Uniqueid modelList=uniqueidRepository.getUniqueId();
		
		// Create Conversion Type
		Type listType = new TypeToken<UniqueIdDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		UniqueIdDTO itemCategoryList = new ModelMapper().map(modelList,listType);
		
		return itemCategoryList;	
	}*/
	
	
}
