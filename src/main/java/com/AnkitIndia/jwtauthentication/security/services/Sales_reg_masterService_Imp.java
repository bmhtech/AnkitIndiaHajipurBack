package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.repository.Sales_reg_masterRepository;

import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;

import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;




@Service
public class Sales_reg_masterService_Imp implements Sales_reg_masterService{
	
	@Autowired
	Sales_reg_masterRepository sales_reg_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@Transactional
	public Sales_reg_master save(Sales_reg_master sales_reg_master) {
		
		LocalDateTime ldt = LocalDateTime.now();
		sales_reg_master.setSales_report(sales_reg_master.getSales_report());
		sales_reg_master.setReportfields(sales_reg_master.getReportfields());
		sales_reg_master.setReportname(sales_reg_master.getReportname());
		
		sales_reg_master.setModified_type("INSERTED");
		sales_reg_master.setInserted_by(userRepository.getUserDetails(sales_reg_master.getUsername()).getName());
		//sales_reg_master.setInserted_by("bb");
		sales_reg_master.setInserted_on(ldt);
		sales_reg_master.setUpdated_by("NA");
		sales_reg_master.setUpdated_on(ldt);
		sales_reg_master.setDeleted_by("NA");
		sales_reg_master.setDeleted_on(ldt);
		
		return sales_reg_masterRepository.save(sales_reg_master);
	}
	
	public Sales_reg_master findOne(long id)
	{
		Optional<Sales_reg_master> op=this.sales_reg_masterRepository.findById(id);
		return op.get();
	}

	
	@Transactional
	public Sales_reg_master update(Sales_reg_master sales_reg_master,long id) {
		Optional<Sales_reg_master> sales =sales_reg_masterRepository.findById(id);
		
		sales_reg_master.setId(sales.get().getId());
		sales_reg_master.setSales_report(sales_reg_master.getSales_report());
		sales_reg_master.setReportfields(sales_reg_master.getReportfields());
		sales_reg_master.setReportname(sales_reg_master.getReportname());
		
		LocalDateTime ldt = LocalDateTime.now();
		sales_reg_master.setModified_type("UPDATED");
		sales_reg_master.setInserted_by(sales_reg_master.getInserted_by());
		sales_reg_master.setInserted_on(ldt);
		sales_reg_master.setUpdated_by(userRepository.getUserDetails(sales_reg_master.getUsername()).getName());
		
		sales_reg_master.setUpdated_on(ldt);
		sales_reg_master.setDeleted_by("NA");
		sales_reg_master.setDeleted_on(ldt);
		
		return sales_reg_masterRepository.save(sales_reg_master);
	}
		
	
	
	
	
	public List<Sales_register_detailsDTO> getSalesRegisterList()
	{
		List<Sales_reg_master> registerlist=new ArrayList<Sales_reg_master>();
		registerlist.addAll(sales_reg_masterRepository.findAll());
		System.out.println(registerlist.toString());
		List<Sales_reg_master> allData = registerlist
				  .stream()
				  .sorted(Comparator.comparing(Sales_reg_master::getId))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Sales_register_detailsDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Sales_register_detailsDTO> regis_list = new ModelMapper().map(allData,listType);
		
		return regis_list;
	}
	
	public List<Sales_register_detailsDTO> getReportNameList(String sales_report)
	{
	
		List<Sales_reg_master> registerlist=new ArrayList<Sales_reg_master>();
		registerlist.addAll(sales_reg_masterRepository.getReportName_List(sales_report));
		
		List<Sales_reg_master> allData = registerlist
				  .stream()
				  .filter(c -> c.getSales_report().equals(sales_report) )
				  .sorted(Comparator.comparing(Sales_reg_master::getSales_report))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Sales_register_detailsDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Sales_register_detailsDTO> regis_name_list = new ModelMapper().map(allData,listType);
		
		return regis_name_list;
	}
	
	
	public List getSalesInvoices(String sales_report,String reportname)
	{
		List sInvList=new ArrayList<>();
		sInvList.addAll(sales_reg_masterRepository.getSalesInvoices(sales_report,reportname));
		return sInvList;
	}
	
	public List getSalesCol(String sales_report,String reportname)
	{
		List regcol=new ArrayList<>();
		regcol.addAll(sales_reg_masterRepository.getSalesCol(sales_report,reportname));
		return regcol;
	}
	
}
