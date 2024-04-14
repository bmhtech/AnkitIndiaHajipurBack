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
import com.AnkitIndia.jwtauthentication.model.Acc_cost_category_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_cost_categoryRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_category_masterDTO;

@Service
public class Acc_cost_category_masterService_Imp implements Acc_cost_category_masterService {
	@Autowired
	Acc_cost_categoryRepository acc_cost_categoryRepository;
	
	@Transactional
	public Acc_cost_category_master save(Acc_cost_category_master costcatagory)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_cost_categoryRepository.countId() != null ) {
			slno=Long.parseLong(acc_cost_categoryRepository.countId());
		}
		String prefix="ACS";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		costcatagory.setCostcat_Id(gen_sno);
		costcatagory.setModified_type("INSERTED");
		costcatagory.setInserted_by("xxxx");
		costcatagory.setInserted_on(ldt);
		costcatagory.setUpdated_by("NA");
		costcatagory.setUpdated_on(ldt);
		costcatagory.setDeleted_by("NA");
		costcatagory.setDeleted_on(ldt);
		
		return acc_cost_categoryRepository.save(costcatagory);
	}

	public List<Acc_cost_category_master> findAll()
	{
		return acc_cost_categoryRepository.findAll();
	}
	
	public Acc_cost_category_master findOne(long id)
	{
		Optional<Acc_cost_category_master> op = this.acc_cost_categoryRepository.findById(id);
		return op.get();
	}
	
	public Acc_cost_category_master update(Acc_cost_category_master costcatagory,long id)
	{
		Optional<Acc_cost_category_master> op = acc_cost_categoryRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		costcatagory.setModified_type("UPDATED");
		costcatagory.setInserted_by("xxxx");
		costcatagory.setInserted_on(ldt);
		costcatagory.setUpdated_by("NA");
		costcatagory.setUpdated_on(ldt);
		costcatagory.setDeleted_by("NA");
		costcatagory.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			costcatagory.setId(id);
		}
		return acc_cost_categoryRepository.save(costcatagory);
	}
	
	public List<Acc_cost_category_masterDTO > getAccCostCategoriCNList()
	{
		List<Acc_cost_category_master> modelList= acc_cost_categoryRepository.getAccCostCategoriCNList(true);
		Type listType=new TypeToken<List<Acc_cost_category_masterDTO>>() {}.getType();
		
		List<Acc_cost_category_masterDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	
	public Acc_cost_category_masterDTO getAccCostCatNListbyC(String grpCode)
	{
		System.out.println("sadk: "+grpCode);
		Acc_cost_category_master modelList=acc_cost_categoryRepository.getAccCostCatNListbyC(grpCode);
		Type listType=new TypeToken<Acc_cost_category_masterDTO>() {}.getType();
		
		Acc_cost_category_masterDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;

	}

}
