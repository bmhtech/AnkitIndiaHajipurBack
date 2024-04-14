package com.AnkitIndia.jwtauthentication.security.services;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AnkitIndia.jwtauthentication.model.Sales_register_details;
import com.AnkitIndia.jwtauthentication.repository.Sales_register_detailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;



@Service
public class Sales_register_detailsService_Imp implements Sales_register_detailsService{

	@Autowired
	Sales_register_detailsRepository sales_register_detailsRepository;
	
	public List<Sales_register_detailsDTO> getSalesRegisterdto(String sales_report)
	{

		
		List<Sales_register_details> cbuList=new ArrayList<Sales_register_details>();
		cbuList.addAll(sales_register_detailsRepository.getSalesRegister1(sales_report.trim()));
		
		
		List<Sales_register_details> allData = cbuList
				  .stream()
				  .filter(c -> c.getSales_report().equals(sales_report) )
				  .sorted(Comparator.comparing(Sales_register_details::getSales_report))
				  .collect(Collectors.toList());
		
			
			
			// Create Conversion Type
			Type listType = new TypeToken<List<Sales_register_detailsDTO>>() {}.getType();
			
			// Convert List of Entity objects to a List of DTOs objects 
			List<Sales_register_detailsDTO> compBunitList = new ModelMapper().map(allData,listType);
			System.out.println("Name: "+compBunitList.size()+","+compBunitList.get(0));
			
			return compBunitList;

	}
	
	
}
