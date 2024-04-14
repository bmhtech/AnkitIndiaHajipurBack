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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.HubMaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GodownMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class GodownMasterService_Imp implements GodownMasterService{

	@Autowired
	GodownMasterRepository godownMasterReository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<GodownMaster> findAll()
	{
		List<GodownMaster> checklist=new ArrayList<GodownMaster>();
		checklist.addAll(godownMasterReository.findcheckList());
		return checklist;
	}
	
	@Transactional
	public GodownMaster save(GodownMaster godownMaster) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(godownMasterReository.countId() != null ) {
			slno=Long.parseLong(godownMasterReository.countId());
		}
		String prefix="GMR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		//System.out.println("bunit::"+godownMaster.getBusiness_unit());
		godownMaster.setGodownid(gen_sno);
		godownMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(godownMaster.getBusiness_unit()).getBusinessunit_name());
		godownMaster.setCompany_id(godownMaster.getCompany_id());
		godownMaster.setFin_year(godownMaster.getFin_year());
		godownMaster.setModified_type("INSERTED");
		godownMaster.setInserted_on(ldt);
		godownMaster.setInserted_by(userRepository.getUserDetails(godownMaster.getUsername()).getName());
		godownMaster.setUpdated_by(godownMaster.getUpdated_by());
		godownMaster.setUpdated_on(ldt);
		godownMaster.setDeleted_by("NA");
		godownMaster.setDeleted_on(ldt);
		
		return godownMasterReository.save(godownMaster);
	}
	
	@Transactional
	public GodownMaster update(GodownMaster godownMaster,long id)
	{
		Optional<GodownMaster> op =godownMasterReository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		godownMaster.setGodownid(op.get().getGodownid());
		godownMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(godownMaster.getBusiness_unit()).getBusinessunit_name());
		godownMaster.setCompany_id(godownMaster.getCompany_id());
		godownMaster.setFin_year(godownMaster.getFin_year());
		godownMaster.setModified_type("INSERTED");
		godownMaster.setInserted_on(ldt);
		godownMaster.setInserted_by(userRepository.getUserDetails(godownMaster.getUsername()).getName());
		godownMaster.setUpdated_by(godownMaster.getUpdated_by());
		godownMaster.setUpdated_on(ldt);
		godownMaster.setDeleted_by("NA");
		godownMaster.setDeleted_on(ldt);
		
		return godownMasterReository.save(godownMaster);
	}
	
	@Transactional
	public GodownMaster deleteGodownMaster(GodownMaster godownMaster,long id)
	{
		Optional<GodownMaster> op = godownMasterReository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		GodownMaster godown=op.get();
		
		godown.setInserted_by(op.get().getInserted_by());
		godown.setInserted_on(op.get().getInserted_on());
		godown.setUpdated_by(op.get().getUpdated_by());
		godown.setUpdated_on(op.get().getUpdated_on());
		godown.setDeleted_by(userRepository.getUserDetails(godown.getUsername()).getName());
		godown.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			godown.setId(id);
		}
		godown.setModified_type("DELETED");
			return godownMasterReository.save(godown);
		
	}
	
	public List<GodownMaster> findGodownMaster(String searchtext)
	{
		List<GodownMaster> godownList=new ArrayList<GodownMaster>();
		godownList.addAll(godownMasterReository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<GodownMaster> allData = godownList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(GodownMaster::getGodownname))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<GodownMaster> allData = godownList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getGodownname()+c.getBusiness_unit()+c.getGodowntype()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(GodownMaster::getGodownname))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public GodownMaster findOne(long id)
	{
		Optional<GodownMaster> op=this.godownMasterReository.findById(id);
		return op.get();
	}

	public List<GodownMaster> getGodownMasterList(String company)
	{
			List<GodownMaster> godownnames=new ArrayList<GodownMaster>();
			godownnames.addAll(godownMasterReository.getGodownMasterList(company));
			
			return godownnames;
					
		}
}
