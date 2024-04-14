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
import com.AnkitIndia.jwtauthentication.model.Acc_cost_centre_master;
import com.AnkitIndia.jwtauthentication.model.Channel_partner;
import com.AnkitIndia.jwtauthentication.repository.Acc_cost_centreRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_centre_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partnerDTO;

@Service
public class Acc_cost_centre_masterService_Imp implements Acc_cost_centre_masterService {
	@Autowired
	Acc_cost_centreRepository acc_cost_centreRepository;
	
	@Transactional
	public Acc_cost_centre_master save(Acc_cost_centre_master costcenter)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_cost_centreRepository.countId() != null ) {
			slno=Long.parseLong(acc_cost_centreRepository.countId());
		}
		String prefix="ACC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		costcenter.setCostcentre_Id(gen_sno);
		costcenter.setModified_type("INSERTED");
		costcenter.setInserted_by("xxxx");
		costcenter.setInserted_on(ldt);
		costcenter.setUpdated_by("NA");
		costcenter.setUpdated_on(ldt);
		costcenter.setDeleted_by("NA");
		costcenter.setDeleted_on(ldt);
		
		return acc_cost_centreRepository.save(costcenter);
	}
	
	public List<Acc_cost_centre_master> findAll()
	{
		return acc_cost_centreRepository.findAll();
	}
	
	public Acc_cost_centre_master findOne(long id)
	{
		Optional<Acc_cost_centre_master> op=this.acc_cost_centreRepository.findById(id);
		return op.get();
	}
	
	public Acc_cost_centre_master update(Acc_cost_centre_master costcenter,long id)
	{
		Optional<Acc_cost_centre_master> op = acc_cost_centreRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		costcenter.setModified_type("UPDATED");
		costcenter.setInserted_by("xxxx");
		costcenter.setInserted_on(ldt);
		costcenter.setUpdated_by("NA");
		costcenter.setUpdated_on(ldt);
		costcenter.setDeleted_by("NA");
		costcenter.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			costcenter.setId(id);
		}
		return acc_cost_centreRepository.save(costcenter);
	}
	
	public List<Acc_cost_centre_masterDTO> getAccCostCentreN()
	{
		List<Acc_cost_centre_master> modelList= acc_cost_centreRepository.getAccCostCentreN(true);
		Type listType=new TypeToken<List<Acc_cost_centre_masterDTO>>() {}.getType();
		
		List<Acc_cost_centre_masterDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}

}
