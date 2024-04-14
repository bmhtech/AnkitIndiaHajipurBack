package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Gateinoutregister;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GateinoutregisterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class GateinoutregisterService_Imp implements GateinoutregisterService{

	@Autowired
	GateinoutregisterRepository gateinoutregisterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<Gateinoutregister> getGateinoutregister(String currDate,String finyear)
	{
		List<Gateinoutregister> daliylist = new ArrayList<Gateinoutregister>();
		daliylist.addAll(gateinoutregisterRepository.getGateinoutregisterList(currDate,finyear));
		
		return daliylist;
	}
	
	@Transactional
	public Gateinoutregister save(Gateinoutregister gateinoutregister) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gateinoutregisterRepository.countId() != null ) {
			slno=Long.parseLong(gateinoutregisterRepository.countId());
		}
		String prefix="GIOR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		gateinoutregister.setGateinoutregisterid(gen_sno);
		gateinoutregister.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(gateinoutregister.getBusiness_unit()).getBusinessunit_name());

		gateinoutregister.setCompany_id(gateinoutregister.getCompany_id());
		gateinoutregister.setFin_year(gateinoutregister.getFin_year());
		gateinoutregister.setModified_type("INSERTED");
		gateinoutregister.setInserted_on(ldt);
		gateinoutregister.setInserted_by(userRepository.getUserDetails(gateinoutregister.getUsername()).getName());
		gateinoutregister.setUpdated_by(gateinoutregister.getUpdated_by());
		gateinoutregister.setUpdated_on(ldt);
		gateinoutregister.setDeleted_by("NA");
		gateinoutregister.setDeleted_on(ldt);
		
		
		return gateinoutregisterRepository.save(gateinoutregister);	
	}
	
	@Transactional
	public Gateinoutregister update(Gateinoutregister gateinoutregister,long id) 
	{

		Optional<Gateinoutregister> op =gateinoutregisterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		gateinoutregister.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(gateinoutregister.getBusiness_unit()).getBusinessunit_name());
		
		gateinoutregister.setGateinoutregisterid(op.get().getGateinoutregisterid());
		gateinoutregister.setCompany_id(gateinoutregister.getCompany_id());
		gateinoutregister.setFin_year(gateinoutregister.getFin_year());
		gateinoutregister.setModified_type("INSERTED");
		gateinoutregister.setInserted_on(ldt);
		gateinoutregister.setInserted_by(userRepository.getUserDetails(gateinoutregister.getUsername()).getName());
		gateinoutregister.setUpdated_by(gateinoutregister.getUpdated_by());
		gateinoutregister.setUpdated_on(ldt);
		gateinoutregister.setDeleted_by("NA");
		gateinoutregister.setDeleted_on(ldt);
		
	
		
		return gateinoutregisterRepository.save(gateinoutregister);
	
	}
	
	@Transactional
	public Gateinoutregister delete(Gateinoutregister gateinoutregister,long id) 
	{

		Optional<Gateinoutregister> op = gateinoutregisterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Gateinoutregister gateinout=op.get();
		
		gateinout.setModified_type("DELETED");
		gateinout.setInserted_by(op.get().getInserted_by());
		gateinout.setInserted_on(op.get().getInserted_on());
		gateinout.setUpdated_by(op.get().getUpdated_by());
		gateinout.setUpdated_on(op.get().getUpdated_on());
		gateinout.setDeleted_by(userRepository.getUserDetails(gateinout.getUsername()).getName());
		gateinout.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			gateinout.setId(id);
		}
		
			return gateinoutregisterRepository.save(gateinout);
		
	
	}
	
	public List<Gateinoutregister> searchgateinoutRegister(String fromdate, String todate,String finyear)
	{
		List<Gateinoutregister> searchdaily =new ArrayList<Gateinoutregister>();
		String tablename="gateinoutregister",order_date="reg_date";
		searchdaily.addAll(gateinoutregisterRepository.searchgateinoutRegister(tablename,order_date,fromdate,todate,finyear));
		
		List<Gateinoutregister> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Gateinoutregister::getReg_date))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Gateinoutregister findOne(long id) 
	{
		Optional<Gateinoutregister> op=gateinoutregisterRepository.findById(id);
		return op.get();
	}
	

}
