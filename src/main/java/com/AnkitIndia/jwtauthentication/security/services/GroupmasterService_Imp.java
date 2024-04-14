package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Groupmaster;
import com.AnkitIndia.jwtauthentication.repository.GroupmasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.GroupmasterDTO;

@Service
public class GroupmasterService_Imp implements GroupmasterService{
	
	@Autowired
	GroupmasterRepository groupmasterRepository;
	
	/*
	@Transactional
	public Groupmaster save(Groupmaster groupmaster)
	{
		
LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(groupmasterRepository.countId() != null ) {
			slno=Long.parseLong(groupmasterRepository.countId());
		}
		String prefix="GRP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		groupmaster.setGroupcode(gen_sno);
		groupmaster.setInserted_by("xxxx");
		groupmaster.setCreated_on(ldt);
		return groupmasterRepository.save(groupmaster);
	}
	
	public List<Groupmaster> findAll()
	{
		return groupmasterRepository.findAll();
	}*/
	
	public List<GroupmasterDTO> getGroupmasterCList()
	{
		List<Groupmaster> modelList=groupmasterRepository.getGroup();
		
		Type listType=new TypeToken<List<GroupmasterDTO>>() {}.getType();
		
		List<GroupmasterDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public GroupmasterDTO getGroupmasterNByCList(String code)
	{
		Groupmaster modelList=groupmasterRepository.getGroupmasterNByCList(code);
		
		Type listType=new TypeToken<GroupmasterDTO>() {}.getType();
		
		GroupmasterDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	

}
