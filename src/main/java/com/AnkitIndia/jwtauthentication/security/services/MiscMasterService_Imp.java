package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Misc_master;

import com.AnkitIndia.jwtauthentication.repository.MiscMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MiscMasterDTO;


@Service
public class MiscMasterService_Imp implements MiscMasterService{
	
	@Autowired
	MiscMasterRepository miscMasterRepository;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public Misc_master save(Misc_master misc)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="MIM";
		if(miscMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(miscMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		misc.setMastertype_code(gen_sno);
		misc.setModified_type("INSERTED");
		misc.setInserted_by(userRepository.getUserDetails(misc.getUsername()).getName());
		misc.setInserted_on(ldt);
		misc.setUpdated_by("NA");
		misc.setUpdated_on(ldt);
		misc.setDeleted_by("NA");
		misc.setDeleted_on(ldt);
		
		return miscMasterRepository.save(misc);
	}
	
	public List<Misc_master> findAll()
	{
		List<Misc_master> miscList=new ArrayList<Misc_master>();
		miscList.addAll(miscMasterRepository.findAll());
		
		List<Misc_master> allData = miscList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Misc_master::getMastertype_code).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Misc_master> getMiscNCList()
	{
		List<Misc_master> miscList=new ArrayList<Misc_master>();
		miscList.addAll(miscMasterRepository.findAll());
		
		List<Misc_master> allData = miscList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Misc_master::getMastertype_name))
			  .collect(Collectors.toList());
		
		return allData;
	}

	public Misc_master findOne(long id)
	{
		Optional<Misc_master> op=this.miscMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Misc_master update(Misc_master misc,long id)
	{
		Optional<Misc_master> op = miscMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		misc.setMastertype_code(op.get().getMastertype_code());
		misc.setModified_type("UPDATED");
		misc.setInserted_by(op.get().getInserted_by());
		misc.setInserted_on(op.get().getInserted_on());
		misc.setUpdated_by(userRepository.getUserDetails(misc.getUsername()).getName());
		misc.setUpdated_on(ldt);
		misc.setDeleted_by("NA");
		misc.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			misc.setId(id);
		}
		return miscMasterRepository.save(misc);
	}
	
	@Transactional
	public Misc_master deleteMisc_master(Misc_master misc,long id)
	{
		Optional<Misc_master> op = miscMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Misc_master miscMaster=op.get();

		miscMaster.setInserted_by(op.get().getInserted_by());
		miscMaster.setInserted_on(op.get().getInserted_on());
		miscMaster.setUpdated_by(op.get().getUpdated_by());
		miscMaster.setUpdated_on(op.get().getUpdated_on());
		miscMaster.setDeleted_by(userRepository.getUserDetails(misc.getUsername()).getName());
		miscMaster.setDeleted_on(ldt);
		miscMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			miscMaster.setId(id);
		}
		
		return miscMasterRepository.save(miscMaster);
	}
	
	public List<MiscMasterDTO> getMiscNameCode() {
		
		List<Misc_master> miscList=new ArrayList<Misc_master>();
		miscList.addAll(miscMasterRepository.findAll());
				
		List<Misc_master> allData = miscList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Misc_master::getMastertype_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<MiscMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<MiscMasterDTO> misc1List= new ModelMapper().map(allData,listType);
		
		return misc1List;
	}
	
	public List<MiscMasterDTO> getMiscList() {
		
		List<Misc_master> miscList=new ArrayList<Misc_master>();
		miscList.addAll(miscMasterRepository.findAll());
				
		List<Misc_master> allData = miscList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Misc_master::getMastertype_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<MiscMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<MiscMasterDTO> misc1List= new ModelMapper().map(allData,listType);
		
		return misc1List;
	}
	
	public List<Misc_master> findMisc_master(String searchtext)
	{
		List<Misc_master> miscList=new ArrayList<Misc_master>();
		miscList.addAll(miscMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Misc_master> allData = miscList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Misc_master::getMastertype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Misc_master> allData = miscList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getMastertype_name()+c.getBusinessunit_name()+c.getMastertype_remarks()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Misc_master::getMastertype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

}
