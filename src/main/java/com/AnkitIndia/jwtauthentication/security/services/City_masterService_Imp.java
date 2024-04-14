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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.City_master;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.repository.Area_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.City_masterDTO;

@Service
public class City_masterService_Imp implements City_masterService{
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Area_masterRepository  area_masterRepository;
	
	@Transactional
	public City_master save(City_master city_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="CT";
		if(city_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(city_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);

		if(Utility.isNullOrEmpty(city_master.getDist_code())) {
			city_master.setDist_name(district_masterRepository.getDistrictDtls(city_master.getDist_code()).getDist_name());
		}else {city_master.setDist_name("None");}
		
		if(Utility.isNullOrEmpty(city_master.getState_code())) {
			city_master.setState_name(state_masterRepository.getState(city_master.getState_code()).getState_name());
		}else {city_master.setState_name("None");}
		
		city_master.setCity_code(gen_sno);
		city_master.setModified_type("INSERTED");
		city_master.setInserted_by(userRepository.getUserDetails(city_master.getUsername()).getName());
		city_master.setInserted_on(ldt);
		city_master.setUpdated_by("NA");
		city_master.setUpdated_on(ldt);
		city_master.setDeleted_by("NA");
		city_master.setDeleted_on(ldt);
		
		return city_masterRepository.save(city_master);
	}
	
	public List<City_master> findAll()
	{
		List<City_master> cityList=new ArrayList<City_master>();
		cityList.addAll(city_masterRepository.findAll());
				
		List<City_master> allData = cityList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(City_master::getCity_code).reversed()).limit(50)
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public City_master findOne(long id)
	{
		Optional<City_master> op=this.city_masterRepository.findById(id);
		return op.get();
	}
	
	public List<City_masterDTO> citylist()
	{
		List<City_master> cityList=city_masterRepository.findAll();
		cityList.forEach((e->{
			e.setCity_name(e.getCity_name().toUpperCase());
		}));
		List<City_master> allData = cityList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(City_master::getCity_name))
			  .collect(Collectors.toList());
			
		// Create Conversion Type
		Type listType = new TypeToken<List<City_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<City_masterDTO> cityMasterList= new ModelMapper().map(allData,listType);
			
		return cityMasterList;
	}
	
	public List<City_masterDTO> citylistByDistrict(String dist_name)
	{
		List<City_master> cityList=new ArrayList<City_master>();
		cityList.addAll(city_masterRepository.findAll());
		System.err.println("Code: "+dist_name);	
		List<City_master> allData = cityList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getDist_name().toLowerCase().trim().equals(dist_name.toLowerCase().trim()))
			  .sorted(Comparator.comparing(City_master::getCity_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<City_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<City_masterDTO> cityMasterList= new ModelMapper().map(allData,listType);
		return cityMasterList;
	}
	
	public List<City_masterDTO> getCityListThruDistrict(String distid)
	{
		List<City_master> cityList= city_masterRepository.findAll();
		cityList.forEach((e)->{
			e.setCity_name(e.getCity_name().toUpperCase());
		});				
		List<City_master> allData = cityList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getDist_code().equals(distid))
			  .sorted(Comparator.comparing(City_master::getCity_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<City_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<City_masterDTO> cityMasterList= new ModelMapper().map(allData,listType);
		return cityMasterList;
	}
	
	@Transactional
	public City_master update(City_master city_master,long id)
	{
		Optional<City_master> op = city_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(city_master.getDist_code())) {
			city_master.setDist_name(district_masterRepository.getDistrictDtls(city_master.getDist_code()).getDist_name());
		}else {city_master.setDist_name("None");}
		
		if(Utility.isNullOrEmpty(city_master.getState_code())) {
			city_master.setState_name(state_masterRepository.getState(city_master.getState_code()).getState_name());
		}else {city_master.setState_name("None");}
		
		city_master.setCity_code(op.get().getCity_code());
		city_master.setModified_type("UPDATED");
		city_master.setInserted_by(op.get().getInserted_by());
		city_master.setInserted_on(op.get().getInserted_on());
		city_master.setUpdated_by(userRepository.getUserDetails(city_master.getUsername()).getName());
		city_master.setUpdated_on(ldt);
		city_master.setDeleted_by("NA");
		city_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			city_master.setId(id);
		}
		return city_masterRepository.save(city_master);
	}
	
	@Transactional
	public City_master deleteCity(City_master city_master,long id)
	{
		Optional<City_master> op = city_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		City_master cMaster=op.get();
		
		cMaster.setInserted_by(op.get().getInserted_by());
		cMaster.setInserted_on(op.get().getInserted_on());
		cMaster.setUpdated_by(op.get().getUpdated_by());
		cMaster.setUpdated_on(op.get().getUpdated_on());
		cMaster.setDeleted_by(userRepository.getUserDetails(city_master.getUsername()).getName());
		cMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			cMaster.setId(id);
		}
		
		if(area_masterRepository.getAreaDtlsThruCity(op.get().getCity_code()).isPresent()) {
			return city_master;
		}else {
			cMaster.setModified_type("DELETED");
			return city_masterRepository.save(cMaster);
		}
	}
	
	public List<City_master> findCities(String searchtext)
	{
		List<City_master> cityList=new ArrayList<City_master>();
		cityList.addAll(city_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<City_master> allData = cityList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(City_master::getCity_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<City_master> allData = cityList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getCity_name()+c.getDist_name()+c.getState_name()+c.getCountry_name()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(City_master::getCity_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
}
