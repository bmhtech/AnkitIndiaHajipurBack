package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;
import com.AnkitIndia.jwtauthentication.model.OtherPartyMaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherItemMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherPartyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
@Service
public class OtherPartyMasterService_Imp implements OtherPartyMasterService{

	@Autowired
	OtherPartyMasterRepository otherPartyMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Transactional
	public OtherPartyMaster save(OtherPartyMaster otherParty) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(otherPartyMasterRepository.countId() != null ) {
			slno=Long.parseLong(otherPartyMasterRepository.countId());
		}
		String prefix="OPM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		//System.out.println("bunit::"+godownMaster.getBusiness_unit());
		otherParty.setNopartyid(gen_sno);
		otherParty.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherParty.getBusiness_unit()).getBusinessunit_name());
		otherParty.setCompany_id(otherParty.getCompany_id());
		otherParty.setFin_year(otherParty.getFin_year());
		otherParty.setModified_type("INSERTED");
		otherParty.setInserted_on(ldt);
		otherParty.setInserted_by(userRepository.getUserDetails(otherParty.getUsername()).getName());
		otherParty.setUpdated_by("NA");
		otherParty.setUpdated_on(ldt);
		otherParty.setDeleted_by("NA");
		otherParty.setDeleted_on(ldt);
		
		return otherPartyMasterRepository.save(otherParty);
	}
	
	@Transactional
	public OtherPartyMaster update(OtherPartyMaster otherparty,long id)
	{
		Optional<OtherPartyMaster> op =otherPartyMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		otherparty.setNopartyid(op.get().getNopartyid());
		otherparty.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherparty.getBusiness_unit()).getBusinessunit_name());
		otherparty.setCompany_id(otherparty.getCompany_id());
		otherparty.setFin_year(otherparty.getFin_year());
		otherparty.setModified_type("INSERTED");
		otherparty.setInserted_on(op.get().getInserted_on());
		otherparty.setInserted_by(op.get().getInserted_by());
		otherparty.setUpdated_by(userRepository.getUserDetails(otherparty.getUsername()).getName());
		otherparty.setUpdated_on(ldt);
		otherparty.setDeleted_by("NA");
		otherparty.setDeleted_on(ldt);
		
		return otherPartyMasterRepository.save(otherparty);
	}
	
	@Transactional
	public OtherPartyMaster delete(OtherPartyMaster otherparty,long id)
	{
		Optional<OtherPartyMaster> op = otherPartyMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		OtherPartyMaster party=op.get();
		
		party.setInserted_by(op.get().getInserted_by());
		party.setInserted_on(op.get().getInserted_on());
		party.setUpdated_by(op.get().getUpdated_by());
		party.setUpdated_on(op.get().getUpdated_on());
		party.setDeleted_by(userRepository.getUserDetails(party.getUsername()).getName());
		party.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			party.setId(id);
		}
		party.setModified_type("DELETED");
			return otherPartyMasterRepository.save(party);
		
	}
	
	public List<OtherPartyMaster> findOtherPartyMaster(String searchtext)
	{
		List<OtherPartyMaster> partylist=new ArrayList<OtherPartyMaster>();
		partylist.addAll(otherPartyMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<OtherPartyMaster> allData = partylist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(OtherPartyMaster::getNopartyname))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<OtherPartyMaster> allData = partylist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getNopartyname()+c.getBusiness_unit()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(OtherPartyMaster::getNopartyname))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public OtherPartyMaster findOne(long id)
	{
		Optional<OtherPartyMaster> op=this.otherPartyMasterRepository.findById(id);
		return op.get();
	}

	public List<Map<String,Object>> getOtherPartyList(String company)
	{
		return otherPartyMasterRepository.getOtherPartyList(company);
	}
	
	public List<Map<String,Object>> getOtherPartyMasterList(String company)
	{
		return otherPartyMasterRepository.getOtherPartyMasterList(company);
	}
}
