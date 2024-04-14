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
import com.AnkitIndia.jwtauthentication.model.HubMaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.HubMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTOC;

@Service
public class HubMasterService_Imp implements HubMasterService{
	@Autowired
	HubMasterRepository hubMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<HubMaster> findAll()
	{
		List<HubMaster> checklist=new ArrayList<HubMaster>();
		checklist.addAll(hubMasterRepository.findcheckList());
		return checklist;
	}
	
	@Transactional
	public HubMaster save(HubMaster hubMaster) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(hubMasterRepository.countId() != null ) {
			slno=Long.parseLong(hubMasterRepository.countId());
		}
		String prefix="HMR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		hubMaster.setHubid(gen_sno);
		hubMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(hubMaster.getBusiness_unit()).getBusinessunit_name());
		hubMaster.setCompany_id(hubMaster.getCompany_id());
		hubMaster.setFin_year(hubMaster.getFin_year());
		hubMaster.setModified_type("INSERTED");
		hubMaster.setInserted_on(ldt);
		hubMaster.setInserted_by(userRepository.getUserDetails(hubMaster.getUsername()).getName());
		hubMaster.setUpdated_by(hubMaster.getUpdated_by());
		hubMaster.setUpdated_on(ldt);
		hubMaster.setDeleted_by("NA");
		hubMaster.setDeleted_on(ldt);
		
		return hubMasterRepository.save(hubMaster);
	}
	
	@Transactional
	public HubMaster update(HubMaster hubMaster,long id)
	{
		Optional<HubMaster> op =hubMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		hubMaster.setHubid(op.get().getHubid());
		hubMaster.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(hubMaster.getBusiness_unit()).getBusinessunit_name());
		hubMaster.setCompany_id(hubMaster.getCompany_id());
		hubMaster.setFin_year(hubMaster.getFin_year());
		hubMaster.setModified_type("INSERTED");
		hubMaster.setInserted_on(ldt);
		hubMaster.setInserted_by(userRepository.getUserDetails(hubMaster.getUsername()).getName());
		hubMaster.setUpdated_by(hubMaster.getUpdated_by());
		hubMaster.setUpdated_on(ldt);
		hubMaster.setDeleted_by("NA");
		hubMaster.setDeleted_on(ldt);
		
		return hubMasterRepository.save(hubMaster);
	}
	
	@Transactional
	public HubMaster deleteHubMaster(HubMaster hubMaster,long id)
	{
		Optional<HubMaster> op = hubMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		HubMaster hub=op.get();
		
		hub.setInserted_by(op.get().getInserted_by());
		hub.setInserted_on(op.get().getInserted_on());
		hub.setUpdated_by(op.get().getUpdated_by());
		hub.setUpdated_on(op.get().getUpdated_on());
		hub.setDeleted_by(userRepository.getUserDetails(hub.getUsername()).getName());
		hub.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			hub.setId(id);
		}
		hub.setModified_type("DELETED");
			return hubMasterRepository.save(hub);
		
	}
	
	public List<HubMaster> findHubMaster(String searchtext)
	{
		List<HubMaster> hubList=new ArrayList<HubMaster>();
		hubList.addAll(hubMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<HubMaster> allData = hubList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(HubMaster::getHubname))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<HubMaster> allData = hubList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getHubname()+c.getBusiness_unit()+c.getHubtype()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(HubMaster::getHubname))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public HubMaster findOne(long id)
	{
		Optional<HubMaster> op=this.hubMasterRepository.findById(id);
		return op.get();
	}
	
	public List<HubMaster> getHubMasterList(String company)
	{
			List<HubMaster> hubnames=new ArrayList<HubMaster>();
			hubnames.addAll(hubMasterRepository.getHubMaster(company));
			
			return hubnames;
					
		}
}
