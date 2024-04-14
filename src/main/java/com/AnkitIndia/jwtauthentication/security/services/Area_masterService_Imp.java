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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Area_master;


import com.AnkitIndia.jwtauthentication.repository.Area_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Area_masterDTO;


@Service
public class Area_masterService_Imp implements Area_masterService{

	@Autowired
	Area_masterRepository  area_masterRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public Area_master save(Area_master area_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="AR";
		if(area_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(area_masterRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(area_master.getState_code())) {
			area_master.setState_name(state_masterRepository.getState(area_master.getState_code()).getState_name());
		}else {area_master.setState_name("None");}
		
		if(Utility.isNullOrEmpty(area_master.getDist_code())) {
			area_master.setDist_name(district_masterRepository.getDistrictDtls(area_master.getDist_code()).getDist_name());
		}else {area_master.setDist_name("None");}
		
		/*if(Utility.isNullOrEmpty(area_master.getCity_code())) {
			area_master.setCity_name(city_masterRepository.getCityDtls(area_master.getCity_code()).getCity_name());
		}else {area_master.setCity_name("None");}*/
		
		area_master.setArea_id(gen_sno);
		area_master.setModified_type("INSERTED");
		area_master.setInserted_by(userRepository.getUserDetails(area_master.getUsername()).getName());
		area_master.setInserted_on(ldt);
		area_master.setUpdated_by("NA");
		area_master.setUpdated_on(ldt);
		area_master.setDeleted_by("NA");
		area_master.setDeleted_on(ldt);
		
		return area_masterRepository.save(area_master);
	}
	
	public List<Area_master> findAll()
	{
		List<Area_master> areaList=new ArrayList<Area_master>();
		areaList.addAll(area_masterRepository.findAll());
				
		List<Area_master> allData = areaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Area_master::getArea_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Area_master findOne(long id)
	{
		Optional<Area_master> op=this.area_masterRepository.findById(id);
		return op.get();
	}

	@Transactional
	public Area_master update(Area_master area_master,long id)
	{
		Optional<Area_master> op = area_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();

		if(Utility.isNullOrEmpty(area_master.getState_code())) {
			area_master.setState_name(state_masterRepository.getState(area_master.getState_code()).getState_name());
		}else {area_master.setState_name("None");}
		
		if(Utility.isNullOrEmpty(area_master.getDist_code())) {
			area_master.setDist_name(district_masterRepository.getDistrictDtls(area_master.getDist_code()).getDist_name());
		}else {area_master.setDist_name("None");}
		
		/*if(Utility.isNullOrEmpty(area_master.getCity_code())) {
			area_master.setCity_name(city_masterRepository.getCityDtls(area_master.getCity_code()).getCity_name());
		}else {area_master.setCity_name("None");}*/
		
		area_master.setArea_id(op.get().getArea_id());
		area_master.setModified_type("UPDATED");
		area_master.setInserted_by(op.get().getInserted_by());
		area_master.setInserted_on(ldt);
		area_master.setUpdated_by(userRepository.getUserDetails(area_master.getUsername()).getName());
		area_master.setUpdated_on(ldt);
		area_master.setDeleted_by("NA");
		area_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			area_master.setId(id);
		}
		return area_masterRepository.save(area_master);
	}
	
	@Transactional
	public Area_master deleteAreaMaster(Area_master area_master,long id)
	{
		Optional<Area_master> op = area_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Area_master aMaster=op.get();
		aMaster.setArea_id(op.get().getArea_id());
		aMaster.setInserted_by(op.get().getInserted_by());
		aMaster.setInserted_on(op.get().getInserted_on());
		aMaster.setUpdated_by(op.get().getUpdated_by());
		aMaster.setUpdated_on(op.get().getUpdated_on());
		aMaster.setDeleted_by(userRepository.getUserDetails(area_master.getUsername()).getName());
		aMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			aMaster.setId(id);
		}
		
		area_master.setModified_type("DELETED");
		return area_masterRepository.save(aMaster);
	}
	
	public List<Area_masterDTO> areaMasterList()
	{
		List<Area_master> areaList=new ArrayList<Area_master>();
		areaList.addAll(area_masterRepository.findAll());
				
		List<Area_master> allData = areaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Area_master::getArea_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Area_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Area_masterDTO> areas= new ModelMapper().map(allData,listType);
		
		return areas;
	}
	
	public List<Area_master> findAreas(String searchtext)
	{
		List<Area_master> areaList=new ArrayList<Area_master>();
		areaList.addAll(area_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Area_master> allData = areaList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Area_master::getArea_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Area_master> allData = areaList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getArea_name()+c.getDescription()+c.getCountry_name()+c.getState_name()+c.getDist_name()+c.getCity_name()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(Area_master::getArea_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
}
