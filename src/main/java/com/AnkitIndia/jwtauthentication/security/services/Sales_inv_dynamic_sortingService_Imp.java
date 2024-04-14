package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.repository.Sales_inv_dynamic_sortingRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_reg_dynamicRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_inv_dynamic_sortingDTO;

@Service
public class Sales_inv_dynamic_sortingService_Imp implements Sales_inv_dynamic_sortingService{

	
	@Autowired
	Sales_inv_dynamic_sortingRepository sales_inv_dynamic_sortingRepository;
	
	@Autowired
	Sales_reg_dynamicRepository sales_reg_dynamicRepository;
	
	@Autowired
	UserRepository userRepository;
	
/*	@Transactional
	public Sales_inv_dynamic_sorting save(Sales_reg_dynamic sales_reg_dynamic){
		
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Sales_reg_dynamic> op = sales_reg_dynamicRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Sales_inv_dynamic_sorting salesSort=op.get();
		
		sales_reg_dynamic.setSales_report(sales_reg_dynamic.getSales_report());
		sales_reg_dynamic.setReportfields(sales_reg_dynamic.getReportfields());
		sales_reg_dynamic.setReportname(sales_reg_dynamic.getReportname());
		sales_reg_dynamic.setTable_name(sales_reg_dynamic.getTable_name());
		sales_reg_dynamic.setStatic_report(sales_reg_dynamic.getStatic_report());
		sales_reg_dynamic.setDisplay_col(sales_reg_dynamic.getDisplay_col());
		
		sales_reg_dynamic.setModified_type("UPDATED");
		sales_reg_dynamic.setInserted_by("NA");
		//sales_inv_dynamic_sorting.setInserted_by("bb");
		sales_reg_dynamic.setInserted_on(ldt);
		sales_reg_dynamic.setUpdated_by(userRepository.getUserDetails(sales_reg_dynamic.getUsername()).getName());
		sales_reg_dynamic.setUpdated_on(ldt);
		sales_reg_dynamic.setDeleted_by("NA");
		sales_reg_dynamic.setDeleted_on(ldt);
		
		return sales_reg_dynamicRepository.save(sales_reg_dynamic);
	}*/ 
	
	
	public List<Sales_inv_dynamic_sortingDTO> getSalesSortDynList()
	{
		List<Sales_inv_dynamic_sorting> sortlist=new ArrayList<Sales_inv_dynamic_sorting>();
		sortlist.addAll(sales_inv_dynamic_sortingRepository.getSalesSortDynList());
		System.out.println(sortlist.toString());
		List<Sales_inv_dynamic_sorting> allData = sortlist
				  .stream()
				  .sorted(Comparator.comparing(Sales_inv_dynamic_sorting::getId))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Sales_inv_dynamic_sortingDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Sales_inv_dynamic_sortingDTO> sort_list = new ModelMapper().map(allData,listType);
		
		return sort_list;
	}
	
	
}
