package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Purchase_reg_master;
import com.AnkitIndia.jwtauthentication.model.Purchase_register_details;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;
import com.AnkitIndia.jwtauthentication.repository.Purchase_reg_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Purchase_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;

@Service
public class Purchase_reg_masterService_Imp implements Purchase_reg_masterService{

	@Autowired
	Purchase_reg_masterRepository purchase_reg_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	/*@Transactional
	public Purchase_reg_master save(Purchase_reg_master purchase_reg_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		purchase_reg_master.setPurchase_report(purchase_reg_master.getPurchase_report());
		purchase_reg_master.setReportfields(purchase_reg_master.getReportfields());
		purchase_reg_master.setReportname(purchase_reg_master.getReportname());
		
		purchase_reg_master.setModified_type("INSERTED");
		purchase_reg_master.setInserted_by(userRepository.getUserDetails(purchase_reg_master.getUsername()).getName());
		purchase_reg_master.setInserted_on(ldt);
		purchase_reg_master.setUpdated_by("NA");
		purchase_reg_master.setUpdated_on(ldt);
		purchase_reg_master.setDeleted_by("NA");
		purchase_reg_master.setDeleted_on(ldt);
		
		return purchase_reg_masterRepository.save(purchase_reg_master);
	}*/
	
	@Transactional
	public Purchase_reg_master save(Purchase_reg_master purchase_reg_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		purchase_reg_master.setPurchase_report(purchase_reg_master.getPurchase_report());
		purchase_reg_master.setReportfields(purchase_reg_master.getReportfields());
		purchase_reg_master.setReportname(purchase_reg_master.getReportname());
		purchase_reg_master.setTable_name(purchase_reg_master.getTable_name());
		
		purchase_reg_master.setModified_type("INSERTED");
		purchase_reg_master.setInserted_by(userRepository.getUserDetails(purchase_reg_master.getUsername()).getName());
		//purchase_reg_master.setInserted_by("bb");
		purchase_reg_master.setInserted_on(ldt);
		purchase_reg_master.setUpdated_by("NA");
		purchase_reg_master.setUpdated_on(ldt);
		purchase_reg_master.setDeleted_by("NA");
		purchase_reg_master.setDeleted_on(ldt);
		
		return purchase_reg_masterRepository.save(purchase_reg_master);
	}
	
	public List<Purchase_register_detailsDTO> getPurchaseRegisterList()
	{
		List<Purchase_reg_master> registerlist=new ArrayList<Purchase_reg_master>();
		registerlist.addAll(purchase_reg_masterRepository.findAll());
	
		List<Purchase_reg_master> allData = registerlist
				  .stream()
				  .sorted(Comparator.comparing(Purchase_reg_master::getPurchase_report))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Purchase_register_detailsDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Purchase_register_detailsDTO> regis_list = new ModelMapper().map(allData,listType);
		
		return regis_list;
	}
	
	public List<Purchase_register_detailsDTO> getPurReportNameList(String purchase_report)
	{
	
		List<Purchase_reg_master> registerlist=new ArrayList<Purchase_reg_master>();
		registerlist.addAll(purchase_reg_masterRepository.getPurReportNameList(purchase_report));
		
		List<Purchase_reg_master> allData = registerlist
				  .stream()
				  .filter(c -> c.getPurchase_report().equals(purchase_report) )
				  .sorted(Comparator.comparing(Purchase_reg_master::getPurchase_report))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Purchase_register_detailsDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Purchase_register_detailsDTO> regis_name_list = new ModelMapper().map(allData,listType);
		
		return regis_name_list;
	}
	public List getPurReportDynamic(String purchase_report,String reportname)
	{
		List abc=new ArrayList<>();
		abc.addAll(purchase_reg_masterRepository.getPurReportDynamic(purchase_report,reportname));
		return abc;
	}
	
	public List getPurReportCol(String purchase_report,String reportname)
	{
		List column=new ArrayList<>();
		column.addAll(purchase_reg_masterRepository.getPurReportCol(purchase_report,reportname));
		return column;
	}
	
}
