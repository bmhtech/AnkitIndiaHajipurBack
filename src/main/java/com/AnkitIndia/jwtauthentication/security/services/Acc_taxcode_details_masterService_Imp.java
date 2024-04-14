package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_taxcode_details_master;
import com.AnkitIndia.jwtauthentication.model.Acc_taxcode_details_master_dts;
import com.AnkitIndia.jwtauthentication.repository.Acc_taxcode_detailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_taxcode_details_masterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Acc_taxcode_details_masterService_Imp implements Acc_taxcode_details_masterService{
	@Autowired
	Acc_taxcode_detailsRepository acc_taxcode_detailsRepository;
	
	@Transactional
	public Acc_taxcode_details_master save(Acc_taxcode_details_master taxcodedetails)
	{
        LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_taxcode_detailsRepository.countId() != null ) {
			slno=Long.parseLong(acc_taxcode_detailsRepository.countId());
		}
		String prefix="APT";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		taxcodedetails.setTax_code(gen_sno);
		taxcodedetails.setModified_type("INSERTED");
		taxcodedetails.setInserted_by("xxxx");
		taxcodedetails.setInserted_on(ldt);
		taxcodedetails.setUpdated_by("NA");
		taxcodedetails.setUpdated_on(ldt);
		taxcodedetails.setDeleted_by("NA");
		taxcodedetails.setDeleted_on(ldt);
		
		Set<Acc_taxcode_details_master_dts> aactaxSet = new HashSet<Acc_taxcode_details_master_dts>();
		aactaxSet.addAll(taxcodedetails.getAcc_taxcode_details_master_dts());
		
		for(Acc_taxcode_details_master_dts atdts : aactaxSet)
		{
			//atdts.setAcc_taxcode_details_master(taxcodedetails);
			atdts.setTax_code(gen_sno);
			atdts.setModified_type("INSERTED");
			atdts.setInserted_by("xxxx");
			atdts.setInserted_on(ldt);
			atdts.setUpdated_by("NA");
			atdts.setUpdated_on(ldt);
			atdts.setDeleted_by("NA");
			atdts.setDeleted_on(ldt);
		
		}
		taxcodedetails.setAcc_taxcode_details_master_dts(aactaxSet);
		
		return acc_taxcode_detailsRepository.save(taxcodedetails);
	}
	public List<Acc_taxcode_details_master> findAll()
	{
		return acc_taxcode_detailsRepository.findAll();
	}
	
	public Acc_taxcode_details_master findOne(long id)
	{
		Optional<Acc_taxcode_details_master> op = this.acc_taxcode_detailsRepository.findById(id);
		return op.get();
	}
	
	public Acc_taxcode_details_master update(Acc_taxcode_details_master taxcodedetails,long id)
	{
		Optional<Acc_taxcode_details_master> op = acc_taxcode_detailsRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		taxcodedetails.setModified_type("UPDATED");
		taxcodedetails.setInserted_by("xxxx");
		taxcodedetails.setInserted_on(ldt);
		taxcodedetails.setUpdated_by("NA");
		taxcodedetails.setUpdated_on(ldt);
		taxcodedetails.setDeleted_by("NA");
		taxcodedetails.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			taxcodedetails.setId(id);
		}
		return acc_taxcode_detailsRepository.save(taxcodedetails);
	}
	
	
	public List<Acc_taxcode_details_masterDTO> getAccTaxcodeNc() {
		List<Acc_taxcode_details_master> modelList=acc_taxcode_detailsRepository.getAccTaxcodeNc();
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Acc_taxcode_details_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Acc_taxcode_details_masterDTO> taxcodeList = new ModelMapper().map(modelList,listType);
		
		return taxcodeList;	
	}
}
