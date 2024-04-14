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
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.ExcelImport.ExcelHelper;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.State_master;
import com.AnkitIndia.jwtauthentication.repository.Country_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.State_masterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;


@Service
public class State_masterService_Imp implements State_masterService{
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	Country_masterRepository country_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public State_master save(State_master state_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="ST";
		if(state_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(state_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		state_master.setCountry_code(country_masterRepository.getCountry(state_master.getCountry_name()).getCountry_code());
		state_master.setState_code(gen_sno);
		state_master.setModified_type("INSERTED");
		state_master.setInserted_by(userRepository.getUserDetails(state_master.getUsername()).getName());
		state_master.setInserted_on(ldt);
		state_master.setUpdated_by("NA");
		state_master.setUpdated_on(ldt);
		state_master.setDeleted_by("NA");
		state_master.setDeleted_on(ldt);
		
		return state_masterRepository.save(state_master);
	}
	
	public List<State_master> findAll()
	{
		List<State_master> stateList=new ArrayList<State_master>();
		stateList.addAll(state_masterRepository.getStates());
				
		List<State_master> allData = stateList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(State_master::getState_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<State_masterDTO> statelist(){
		
		List<State_master> stateList=new ArrayList<State_master>();
		stateList.addAll(state_masterRepository.findAll());
				
		List<State_master> allData = stateList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(State_master::getState_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<State_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<State_masterDTO> statesList = new ModelMapper().map(allData,listType);
		
		return statesList;
	}
	
	public List<State_masterDTO> statelistByCountry(String country_name){
		List<State_master> stateList=state_masterRepository.getStates();

		stateList.forEach((e)->{
			e.setState_name(e.getState_name().toUpperCase());
			
		});
		//String country="";
		//country_name=country_name.toUpperCase();
		
		List<State_master> allData = stateList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCountry_name().equals(country_name.toUpperCase()))
			  
			  .sorted(Comparator.comparing(State_master::getState_name))
			  .collect(Collectors.toList());
		
		
		// Create Conversion Type
		Type listType = new TypeToken<List<State_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<State_masterDTO> statesList = new ModelMapper().map(allData,listType);
		return statesList;
	}
	
	public List<State_master> statelistByCountryUserprofile(){
		List<State_master> statename = state_masterRepository.findAll();
	return statename;
	}
	
	public State_master findOne(long id)
	{
		Optional<State_master> op=this.state_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public State_master update(State_master state_master,long id) 
	{
		Optional<State_master> op = state_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		state_master.setModified_type("INSERTED");
		state_master.setInserted_by(op.get().getInserted_by());
		state_master.setInserted_on(op.get().getInserted_on());
		state_master.setUpdated_by(userRepository.getUserDetails(state_master.getUsername()).getName());
		state_master.setUpdated_on(ldt);
		state_master.setDeleted_by("NA");
		state_master.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			state_master.setId(id);
		}
		return state_masterRepository.save(state_master);
	}
	
	
	  public void importexcel(MultipartFile file) 
	  {
		    try 
		    {
		    	System.out.println("here catch file ");
		      List<State_master> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
		      state_masterRepository.saveAll(tutorials);
		    }
		    catch (IOException e) 
		    {
		      throw new RuntimeException("fail to store excel data: " + e.getMessage());
		    }
	 }
}