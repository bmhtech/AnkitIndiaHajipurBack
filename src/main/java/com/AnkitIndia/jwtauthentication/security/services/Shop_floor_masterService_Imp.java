package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;

import com.AnkitIndia.jwtauthentication.model.Shop_floor_master;

import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Shop_floor_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Shop_floor_masterService_Imp implements Shop_floor_masterService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	public SequenceIdDTO getSFSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=shop_floor_masterRepository.getSFSequenceId(fprefix,company);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	
	@Transactional
	public Shop_floor_master save(Shop_floor_master sFloor_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(shop_floor_masterRepository.countId() != null ) {
			slno=Long.parseLong(shop_floor_masterRepository.countId());
		}
		String prefix="SF";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		long nslno=0;
		String gen_snonew=shop_floor_masterRepository.getSFSequenceId(sFloor_master.getShop_floor_code().substring(0,6),sFloor_master.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(sFloor_master.getShop_floor_code().substring(0,6),nslno);
		sFloor_master.setShop_floor_code(gen_tslno);
		/*End checking transaction no for unique */
		
		if(sFloor_master.getBusiness_unit().compareTo("0") !=0 && sFloor_master.getBusiness_unit().compareTo("") !=0 && sFloor_master.getBusiness_unit() !=null) {
			sFloor_master.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sFloor_master.getBusiness_unit()).getBusinessunit_name());
		}else {sFloor_master.setBusiness_unitname("None");}
		
		sFloor_master.setShop_floor_id(gen_sno);
		sFloor_master.setModified_type("INSERTED");
		sFloor_master.setInserted_by(userRepository.getUserDetails(sFloor_master.getUsername()).getName());
		sFloor_master.setInserted_on(ldt);
		sFloor_master.setUpdated_by("NA");
		sFloor_master.setUpdated_on(ldt);
		sFloor_master.setDeleted_by("NA");
		sFloor_master.setDeleted_on(ldt);
		
		return shop_floor_masterRepository.save(sFloor_master);
	}
	
	public List<Shop_floor_master> findAllShopFloor()
	{
		
		List<Shop_floor_master> shopFloorList=new ArrayList<Shop_floor_master>();
		shopFloorList.addAll(shop_floor_masterRepository.findAllShopFloors());
				
		List<Shop_floor_master> allData = shopFloorList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Shop_floor_master::getShop_floor_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
		
	}
	
	public Shop_floor_master findOne(long id)
	{
		Optional<Shop_floor_master> op=this.shop_floor_masterRepository.findById(id);
		return op.get();
	}

	public List<Shop_floor_masterDTO> getShopFloors()
	{
		
		List<Shop_floor_master> shopList=new ArrayList<Shop_floor_master>();
		shopList.addAll(shop_floor_masterRepository.findAll());
				
		List<Shop_floor_master> allData = shopList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Shop_floor_master::getShop_floor_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Shop_floor_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Shop_floor_masterDTO> shopF= new ModelMapper().map(allData,listType);
		
		return shopF;
		 
	}
	
	public List<Shop_floor_masterDTO> getShopFloorThruBU(String bunit)
	{
		List<Shop_floor_master> shopList=new ArrayList<Shop_floor_master>();
		shopList.addAll(shop_floor_masterRepository.findAll());
				
		List<Shop_floor_master> allData = shopList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getBusiness_unit().equals(bunit))//process_type='Special'
			  .sorted(Comparator.comparing(Shop_floor_master::getShop_floor_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Shop_floor_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Shop_floor_masterDTO> shopF= new ModelMapper().map(allData,listType);
		
		return shopF;
		
	}
	
	public  List<Map<String,Object>> getShopFloorThruBUregular(String bunit)
	{
		return  process_masterRepository.getShopFloorThruBUregular(bunit);
	}
	
	public  List<Map<String,Object>> getShopFloorspecialThruBU(String bunit)
	{
		return  process_masterRepository.getShopFloorspecialThruBU(bunit);
	}
	
	@Transactional
	public Shop_floor_master update(Shop_floor_master sFloor_master,long id)
	{
		Optional<Shop_floor_master> op = shop_floor_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(sFloor_master.getBusiness_unit().compareTo("0") !=0 && sFloor_master.getBusiness_unit().compareTo("") !=0 && sFloor_master.getBusiness_unit() !=null) {
			sFloor_master.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sFloor_master.getBusiness_unit()).getBusinessunit_name());
		}else {sFloor_master.setBusiness_unitname("None");}
		
		sFloor_master.setShop_floor_id(op.get().getShop_floor_id());
		sFloor_master.setModified_type("INSERTED");
		sFloor_master.setInserted_by(op.get().getInserted_by());
		sFloor_master.setInserted_on(op.get().getInserted_on());
		sFloor_master.setUpdated_by(userRepository.getUserDetails(sFloor_master.getUsername()).getName());
		sFloor_master.setUpdated_on(ldt);
		sFloor_master.setDeleted_by("NA");
		sFloor_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			sFloor_master.setId(id);
		}
		return shop_floor_masterRepository.save(sFloor_master);
	}
	
	@Transactional
	public Shop_floor_master deleteShop_floor(Shop_floor_master sFloor_master,long id)
	{
		Optional<Shop_floor_master> op = shop_floor_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Shop_floor_master shopMaster=op.get();

		shopMaster.setInserted_by(op.get().getInserted_by());
		shopMaster.setInserted_on(op.get().getInserted_on());
		shopMaster.setUpdated_by(op.get().getUpdated_by());
		shopMaster.setUpdated_on(op.get().getUpdated_on());
		shopMaster.setDeleted_by(userRepository.getUserDetails(sFloor_master.getUsername()).getName());
		shopMaster.setDeleted_on(ldt);
		shopMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			shopMaster.setId(id);
		}
		
		
		return shop_floor_masterRepository.save(shopMaster);
	}
	
	public List<Shop_floor_master> findShop_floor(String searchtext)
	{
		List<Shop_floor_master> invList=new ArrayList<Shop_floor_master>();
		invList.addAll(shop_floor_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Shop_floor_master> allData = invList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Shop_floor_master::getShop_floor_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Shop_floor_master> allData = invList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && (c.getShop_floor_name()+c.getBusiness_unitname()+c.getShop_floor_code()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Shop_floor_master::getShop_floor_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO checkShopFloorUsage(String shopfloorid)
	 {
		StatusDTO result = new StatusDTO();
		
		if(shop_floor_masterRepository.checkShopFloorUsage(shopfloorid))
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		
		return result;
	 }
}
