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
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherItemMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
@Service
public class OtherItemMasterService_Imp implements OtherItemMasterService{

	@Autowired
	OtherItemMasterRepository otherItemMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	
	@Transactional
	public OtherItemMaster save(OtherItemMaster otherItem) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(otherItemMasterRepository.countId() != null ) {
			slno=Long.parseLong(otherItemMasterRepository.countId());
		}
		String prefix="OIM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		//System.out.println("bunit::"+godownMaster.getBusiness_unit());
		otherItem.setNoitemid(gen_sno);
		otherItem.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherItem.getBusiness_unit()).getBusinessunit_name());
		otherItem.setCompany_id(otherItem.getCompany_id());
		otherItem.setFin_year(otherItem.getFin_year());
		otherItem.setModified_type("INSERTED");
		otherItem.setInserted_on(ldt);
		otherItem.setInserted_by(userRepository.getUserDetails(otherItem.getUsername()).getName());
		otherItem.setUpdated_by("NA");
		otherItem.setUpdated_on(ldt);
		otherItem.setDeleted_by("NA");
		otherItem.setDeleted_on(ldt);
		
		return otherItemMasterRepository.save(otherItem);
	}
	
	@Transactional
	public OtherItemMaster update(OtherItemMaster otherItem,long id)
	{
		Optional<OtherItemMaster> op =otherItemMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		otherItem.setNoitemid(op.get().getNoitemid());
		otherItem.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherItem.getBusiness_unit()).getBusinessunit_name());
		otherItem.setCompany_id(otherItem.getCompany_id());
		otherItem.setFin_year(otherItem.getFin_year());
		otherItem.setModified_type("INSERTED");
		otherItem.setInserted_on(op.get().getInserted_on());
		otherItem.setInserted_by(op.get().getInserted_by());
		otherItem.setUpdated_by(userRepository.getUserDetails(otherItem.getUsername()).getName());
		otherItem.setUpdated_on(ldt);
		otherItem.setDeleted_by("NA");
		otherItem.setDeleted_on(ldt);
		
		return otherItemMasterRepository.save(otherItem);
	}
	
	@Transactional
	public OtherItemMaster delete(OtherItemMaster otherItemMaster,long id)
	{
		Optional<OtherItemMaster> op = otherItemMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		OtherItemMaster item=op.get();
		
		item.setInserted_by(op.get().getInserted_by());
		item.setInserted_on(op.get().getInserted_on());
		item.setUpdated_by(op.get().getUpdated_by());
		item.setUpdated_on(op.get().getUpdated_on());
		item.setDeleted_by(userRepository.getUserDetails(item.getUsername()).getName());
		item.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			item.setId(id);
		}
		item.setModified_type("DELETED");
			return otherItemMasterRepository.save(item);
		
	}
	
	public List<OtherItemMaster> findOtherItemMaster(String searchtext)
	{
		List<OtherItemMaster> itemlist=new ArrayList<OtherItemMaster>();
		itemlist.addAll(otherItemMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<OtherItemMaster> allData = itemlist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(OtherItemMaster::getNoitemname))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<OtherItemMaster> allData = itemlist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getNoitemname()+c.getBusiness_unit()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(OtherItemMaster::getNoitemname))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public OtherItemMaster findOne(long id)
	{
		Optional<OtherItemMaster> op=this.otherItemMasterRepository.findById(id);
		return op.get();
	}

	public List<Map<String,Object>> getOtherItemList(String company)
	{
		return otherItemMasterRepository.getOtherItemList(company);
	}
	
	public List<Map<String,Object>> getOtherItemMasterList(String company)
	{
		return otherItemMasterRepository.getOtherItemMasterList(company);
	}
}
