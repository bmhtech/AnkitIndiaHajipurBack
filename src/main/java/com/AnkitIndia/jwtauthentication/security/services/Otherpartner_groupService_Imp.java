package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Otherpartner_group;
import com.AnkitIndia.jwtauthentication.repository.Otherpartner_groupRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Otherpartner_groupDTO;

@Service
public class Otherpartner_groupService_Imp implements Otherpartner_groupService{

	@Autowired
	Otherpartner_groupRepository otherpartner_groupRepository;
	
	@Transactional
	public Otherpartner_group save(Otherpartner_group otherpartner_group)
	{
		
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(otherpartner_groupRepository.countId() != null ) {
			slno=Long.parseLong(otherpartner_groupRepository.countId());
		}
		String prefix="OP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		otherpartner_group.setOp_id(gen_sno);
		otherpartner_group.setCompany_id("xxxxx");
		otherpartner_group.setFin_year("2019-2020");
		otherpartner_group.setModified_type("INSERTED");
		otherpartner_group.setInserted_by("xxxx");
		otherpartner_group.setInserted_on(ldt);
		otherpartner_group.setUpdated_by("NA");
		otherpartner_group.setUpdated_on(ldt);
		otherpartner_group.setDeleted_by("NA");
		otherpartner_group.setDeleted_on(ldt);
		
			return otherpartner_groupRepository.save(otherpartner_group);	
	}
	
	public List<Otherpartner_group> findAll()
	{
		return otherpartner_groupRepository.findAll();
	}
	
	public Otherpartner_group findOne(long id)
	{
		Optional<Otherpartner_group> op=this.otherpartner_groupRepository.findById(id);
		return op.get();
	}
	
	public List<Otherpartner_groupDTO> getOtherpartnerGroupList()
	{
		List<Otherpartner_group> modelList= otherpartner_groupRepository.getOtherpartnerGroupList(true);
		
		Type listType=new TypeToken<List<Otherpartner_groupDTO>>() {}.getType();
		
		List<Otherpartner_groupDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
}
