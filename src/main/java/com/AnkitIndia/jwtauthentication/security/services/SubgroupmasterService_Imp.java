package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;
import com.AnkitIndia.jwtauthentication.repository.GroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SubgroupmasterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;


@Service
public class SubgroupmasterService_Imp implements SubgroupmasterService {
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	GroupmasterRepository groupmasterRepository;
	
	public List<SubgroupmasterDTO> getSubgroupNames(){
		//return subgroupmasterRepository.getSubgroupNameList();
		List<Subgroupmaster> modelList=subgroupmasterRepository.getSubgroupNameList();
		
		// Create Conversion Type
		Type listType = new TypeToken<List<SubgroupmasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<SubgroupmasterDTO> subGroupList = new ModelMapper().map(modelList,listType);
		
		return subGroupList;
	}
	
	public List<Subgroupmaster> getSubgroupByGr(String gr){
		
		String grCode=groupmasterRepository.gname(gr);
		
		return subgroupmasterRepository.getSubgroupByGr(grCode);
	}
	
	public SubgroupmasterDTO getSubGroupmasterNByC(String code)
	{
		System.out.println("modelList: "+code);
		Subgroupmaster modelList=subgroupmasterRepository.getSubGroupmasterNByC(code);
		
		System.out.println("modelList: "+modelList);
		
		Type listType = new TypeToken<SubgroupmasterDTO>() {}.getType();
		
		SubgroupmasterDTO subGroupList = new ModelMapper().map(modelList,listType);
		
		return subGroupList;
		
	}


}
