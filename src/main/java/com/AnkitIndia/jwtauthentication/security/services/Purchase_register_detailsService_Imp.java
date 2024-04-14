package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Purchase_register_details;

import com.AnkitIndia.jwtauthentication.repository.Purchase_register_detailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Purchase_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;

@Service
public class Purchase_register_detailsService_Imp implements Purchase_register_detailsService{

	@Autowired
	Purchase_register_detailsRepository purchase_register_detailsRepository;
	
	public List<Purchase_register_detailsDTO> getPurchaseRegisterdt(String purchase_report)
	{
		
		List<Purchase_register_details> purlistdt=new ArrayList<Purchase_register_details>();
		purlistdt.addAll(purchase_register_detailsRepository.getPurchaseRegister(purchase_report));
		
		for(int i=0;i<purlistdt.size();i++) 
		{
			System.out.println("size :: " + purlistdt.get(i));
		}
		
		List<Purchase_register_details> allData = purlistdt
				  .stream()
				  .filter(c -> c.getPurchase_report().equals(purchase_report) )
				  .sorted(Comparator.comparing(Purchase_register_details::getPurchase_report))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Purchase_register_detailsDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Purchase_register_detailsDTO> compBunitList = new ModelMapper().map(allData,listType);	
		
		
		
		return compBunitList;
	}
}
