package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.jwtauthentication.model.System_settings;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.System_settingsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class System_settingsService_Imp implements System_settingsService {

	@Autowired
	System_settingsRepository system_settingsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Transactional
	public System_settings save(System_settings settings)
	{
		LocalDateTime ldt = LocalDateTime.now();
		System_settings system_settings=new System_settings();
		if(system_settingsRepository.getSystemSettingsByComp(settings.getCompany_id()).isPresent()) {
			system_settings.setStatus("Duplicate Company !!! Can't Save !!!");
			return system_settings;
		}else {
			if(Utility.isNullOrEmpty(settings.getCompany_id())) {
				settings.setCompany_name(companyMasterRepository.getCompanyDetails(settings.getCompany_id()).getCompany_name());
			}
			if(settings.isCode_generator()==true) {
				settings.setGenerator_status("Yes");
			}else {settings.setGenerator_status("No");}
			
			if(settings.isVehicle_doc()==true) {
				settings.setVehicle_status("Yes");
			}else {settings.setVehicle_status("No");}
			
			settings.setModified_type("INSERTED");
			settings.setInserted_by(userRepository.getUserDetails(settings.getUsername()).getName());
			settings.setInserted_on(ldt);
			settings.setUpdated_by("NA");
			settings.setUpdated_on(ldt);
			settings.setDeleted_by("NA");
			settings.setDeleted_on(ldt);
			settings.setStatus("Data save successfully!!!");
			return system_settingsRepository.save(settings);
		}
	}
	
	public List<System_settings> getSystemSettings(String company)
	{
		List<System_settings> systemList = new ArrayList<System_settings>();
		systemList.addAll(system_settingsRepository.findAll());
		
		List<System_settings> allsys = systemList
				.parallelStream()
				.filter(c-> c.getCompany_id() .equals(company) && c.getModified_type() .equals("UPDATED"))
				.collect(Collectors.toList());
				
		return allsys;
	}
	
	public System_settings findOne(Long id) {
		Optional<System_settings> op=this.system_settingsRepository.findById(id);
		return op.get();
	}
	
	public System_settings getSystemSettingsByComp(String comp) {
		System_settings settings=new System_settings();
		if(system_settingsRepository.getSystemSettingsByComp(comp).isPresent()) {
			settings=system_settingsRepository.getSystemSettingsByComp(comp).get();
		}
		return settings;
	}
	
	@Transactional
	public System_settings update(System_settings settings,long id)
	{
		Optional<System_settings> op = system_settingsRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(settings.getCompany_id())) {
			settings.setCompany_name(companyMasterRepository.getCompanyDetails(settings.getCompany_id()).getCompany_name());
		}
		if(settings.isCode_generator()==true) {
			settings.setGenerator_status("Yes");
		}else {settings.setGenerator_status("No");}
		
		if(settings.isVehicle_doc()==true) {
			settings.setVehicle_status("Yes");
		}else {settings.setVehicle_status("No");}
		
		settings.setModified_type("UPDATED");
		settings.setInserted_by(op.get().getInserted_by());
		settings.setInserted_on(op.get().getInserted_on());
		settings.setUpdated_by(userRepository.getUserDetails(settings.getUsername()).getName());
		settings.setUpdated_on(ldt);
		settings.setDeleted_by("NA");
		settings.setDeleted_on(ldt);
		settings.setStatus("Data updated successfully!!!");
		
		if(op.isPresent()) {
			settings.setId(id);
		}
		return system_settingsRepository.save(settings);
	}
}
