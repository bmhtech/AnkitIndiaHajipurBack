package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.model.Country_master;
import com.AnkitIndia.jwtauthentication.repository.Country_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Country_masterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Country_masterService_Imp implements Country_masterService{
	
	@Autowired
	Country_masterRepository country_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Country_master save(Country_master country_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="CNT";
		if(country_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(country_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		country_master.setCountry_code(gen_sno);
		country_master.setModified_type("INSERTED");
		country_master.setInserted_by(userRepository.getUserDetails(country_master.getUsername()).getName());
		country_master.setInserted_on(ldt);
		country_master.setUpdated_by("NA");
		country_master.setUpdated_on(ldt);
		country_master.setDeleted_by("NA");
		country_master.setDeleted_on(ldt);
		
		return country_masterRepository.save(country_master);
	}
	
	public List<Country_master> findAll()
	{
		List<Country_master> countryList=new ArrayList<Country_master>();
		countryList.addAll(country_masterRepository.findAll());
				
		List<Country_master> allData = countryList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Country_master::getCountry_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Country_masterDTO> countrylist(){
		
		List<Country_master> countryList=country_masterRepository.findAll();
		countryList.forEach((e->{
			e.setCountry_name(e.getCountry_name().toUpperCase());
		}));
		List<Country_master> allData = countryList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Country_master::getCountry_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Country_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Country_masterDTO> countrys = new ModelMapper().map(allData,listType);
		
		return countrys;
	}
	
	@Transactional
	public Country_master update(Country_master country,long id)
	{
		Optional<Country_master> op =country_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		country.setCompany_id(op.get().getCompany_id());
		country.setModified_type("INSERTED");
		country.setInserted_by(op.get().getInserted_by());
		country.setInserted_on(op.get().getInserted_on());
		country.setUpdated_by(userRepository.getUserDetails(country.getUsername()).getName());
		country.setUpdated_on(ldt);
		country.setDeleted_by("NA");
		country.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			country.setId(id);
		}
		
		return country_masterRepository.save(country);
	}
	
	@Transactional
	public Country_master deleteCompany(Country_master country,long id)
	{
		Optional<Country_master> op =country_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Country_master cMaster=op.get();
		
		cMaster.setCompany_id(op.get().getCompany_id());
		cMaster.setInserted_by(op.get().getInserted_by());
		cMaster.setInserted_on(op.get().getInserted_on());
		cMaster.setUpdated_by(op.get().getUpdated_by());
		cMaster.setUpdated_on(op.get().getUpdated_on());
		cMaster.setDeleted_by(userRepository.getUserDetails(country.getUsername()).getName());
		cMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			cMaster.setId(id);
		}
		
		cMaster.setModified_type("DELETED");
		
		return country_masterRepository.save(cMaster);
	}
}