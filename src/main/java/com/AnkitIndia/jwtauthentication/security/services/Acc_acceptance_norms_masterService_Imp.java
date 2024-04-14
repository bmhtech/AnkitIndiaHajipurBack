package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master;
import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master_dts;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;
import com.AnkitIndia.jwtauthentication.repository.Acc_acceptance_normsRepository;
import com.AnkitIndia.jwtauthentication.repository.Acc_acceptance_norms_master_dtsRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_master_dtsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_addr_dtlsDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Acc_acceptance_norms_masterService_Imp implements Acc_acceptance_norms_masterService{
	@Autowired
	Acc_acceptance_normsRepository acc_acceptance_normsRepository;

	@Transactional
	public Acc_acceptance_norms_master save(Acc_acceptance_norms_master acceptance)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_acceptance_normsRepository.countId() != null ) {
			slno=Long.parseLong(acc_acceptance_normsRepository.countId());
		}
		String prefix="ACN";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		acceptance.setAnm_id(gen_sno);
		acceptance.setModified_type("INSERTED");
		acceptance.setInserted_by("xxxx");
		acceptance.setInserted_on(ldt);
		acceptance.setUpdated_by("NA");
		acceptance.setUpdated_on(ldt);
		acceptance.setDeleted_by("NA");
		acceptance.setDeleted_on(ldt);
		
		
		Set<Acc_acceptance_norms_master_dts> aacNormsSet = new HashSet<Acc_acceptance_norms_master_dts>();
		aacNormsSet.addAll(acceptance.getAcc_acceptance_norms_master_dts());
		for(Acc_acceptance_norms_master_dts aanmdts : aacNormsSet)
		{
			//aanmdts.setAcc_acceptance_norms_master(acceptance);
			aanmdts.setAnmd_code(gen_sno);
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by("xxxx");
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			//System.out.println("got test: "+aanmdts.getAnm_code());
			
		
		}
		acceptance.setAcc_acceptance_norms_master_dts(aacNormsSet);
		
		return acc_acceptance_normsRepository.save(acceptance);
	}
	
	public List<Acc_acceptance_norms_master> findAll()
	{
		return acc_acceptance_normsRepository.findAll();
	}
	
	public Acc_acceptance_norms_master findOne(long id)
	{
		Optional<Acc_acceptance_norms_master> op=this.acc_acceptance_normsRepository.findById(id);
		return op.get();
	}
	
	public Acc_acceptance_norms_master update(Acc_acceptance_norms_master acceptance,long id)
	{
		Optional<Acc_acceptance_norms_master> op = acc_acceptance_normsRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		acceptance.setModified_type("UPDATED");
		acceptance.setInserted_by("xxxx");
		acceptance.setInserted_on(ldt);
		acceptance.setUpdated_by("NA");
		acceptance.setUpdated_on(ldt);
		acceptance.setDeleted_by("NA");
		acceptance.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			acceptance.setId(id);
		}
		return acc_acceptance_normsRepository.save(acceptance);
	}
	
	public List<Acc_acceptance_norms_masterDTO> getAccNormsNameCode() {
		List<Acc_acceptance_norms_master> modelList=acc_acceptance_normsRepository.getAccNormsNameCode(true);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Acc_acceptance_norms_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Acc_acceptance_norms_masterDTO> accNormsList = new ModelMapper().map(modelList,listType);
		
		return accNormsList;	
	}
	
	@Autowired
	Acc_acceptance_norms_master_dtsRepository acc_acceptance_norms_master_dtsRepository;
	
	public List<Acc_acceptance_norms_master_dtsDTO> getAccNormsDetails(String code){

		List<Acc_acceptance_norms_master_dts> modelList=acc_acceptance_norms_master_dtsRepository.getAccNormsDetails(code);

		Type listType = new TypeToken<List<Acc_acceptance_norms_master_dtsDTO>>() {}.getType();

		List<Acc_acceptance_norms_master_dtsDTO> accNorms= new ModelMapper().map(modelList,listType);
		
		return accNorms;
	}
	
	public List<Acc_acceptance_norms_master_dtsDTO> accNormsRetriveList(String a_id)
	{
		List<Acc_acceptance_norms_master> modelList=new ArrayList<Acc_acceptance_norms_master>();
		
		modelList.addAll(acc_acceptance_normsRepository.accNormsRetriveList(a_id));
			
		Type listType=new TypeToken<List<Acc_acceptance_norms_master_dtsDTO>>() {}.getType();
		
		List<Acc_acceptance_norms_master_dtsDTO> accNorms=new ModelMapper().map(modelList,listType);
		
		return accNorms;
	}
}
