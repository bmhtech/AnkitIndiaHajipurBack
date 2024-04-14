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

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DieselusedimportRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
@Service
public class DieselusedimportService_Imp implements DieselusedimportService{
	
	@Autowired
	DieselusedimportRepository dieselusedimportRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<Dieselusedimport> getDieselusedimportlist(String currDate,String finyear)
	{
		List<Dieselusedimport> daliylist = new ArrayList<Dieselusedimport>();
		daliylist.addAll(dieselusedimportRepository.getdailylist(currDate,finyear));
		
		return daliylist;
	}
	
	@Transactional
	public Dieselusedimport save(Dieselusedimport dieselusedimport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dieselusedimportRepository.countId() != null ) {
			slno=Long.parseLong(dieselusedimportRepository.countId());
		}
		String prefix="DUI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		dieselusedimport.setDieselusedimportid(gen_sno);
		dieselusedimport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dieselusedimport.getBusiness_unit()).getBusinessunit_name());

		dieselusedimport.setCompany_id(dieselusedimport.getCompany_id());
		dieselusedimport.setFin_year(dieselusedimport.getFin_year());
		dieselusedimport.setModified_type("INSERTED");
		dieselusedimport.setInserted_on(ldt);
		dieselusedimport.setInserted_by(userRepository.getUserDetails(dieselusedimport.getUsername()).getName());
		dieselusedimport.setUpdated_by(dieselusedimport.getUpdated_by());
		dieselusedimport.setUpdated_on(ldt);
		dieselusedimport.setDeleted_by("NA");
		dieselusedimport.setDeleted_on(ldt);
		
		return dieselusedimportRepository.save(dieselusedimport);	
	}
	
	@Transactional
	public Dieselusedimport update(Dieselusedimport dieselusedimport,long id) 
	{

		Optional<Dieselusedimport> op =dieselusedimportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		dieselusedimport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dieselusedimport.getBusiness_unit()).getBusinessunit_name());

		dieselusedimport.setCompany_id(dieselusedimport.getCompany_id());
		dieselusedimport.setFin_year(dieselusedimport.getFin_year());
		dieselusedimport.setModified_type("INSERTED");
		dieselusedimport.setInserted_on(ldt);
		dieselusedimport.setInserted_by(userRepository.getUserDetails(dieselusedimport.getUsername()).getName());
		dieselusedimport.setUpdated_by(dieselusedimport.getUpdated_by());
		dieselusedimport.setUpdated_on(ldt);
		dieselusedimport.setDeleted_by("NA");
		dieselusedimport.setDeleted_on(ldt);
		
		return dieselusedimportRepository.save(dieselusedimport);
	
	}
	
	@Transactional
	public Dieselusedimport delete(Dieselusedimport dieselusedimport,long id) 
	{
		Optional<Dieselusedimport> op = dieselusedimportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Dieselusedimport dieselused=op.get();
		
		dieselused.setModified_type("DELETED");
		dieselused.setInserted_by(op.get().getInserted_by());
		dieselused.setInserted_on(op.get().getInserted_on());
		dieselused.setUpdated_by(op.get().getUpdated_by());
		dieselused.setUpdated_on(op.get().getUpdated_on());
		dieselused.setDeleted_by(userRepository.getUserDetails(dieselused.getUsername()).getName());
		dieselused.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			dieselused.setId(id);
		}
		
			return dieselusedimportRepository.save(dieselused);
	}
	
	public List<Dieselusedimport> searchDieselusedimportReport(String fromdate, String todate,String finyear)
	{
		List<Dieselusedimport> searchdiesel =new ArrayList<Dieselusedimport>();
		String tablename="dieselusedimport",order_date="date";
		searchdiesel.addAll(dieselusedimportRepository.getsearchdieselusedimport(tablename,order_date,fromdate,todate,finyear));
		
		List<Dieselusedimport> allData = searchdiesel
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Dieselusedimport::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Dieselusedimport findOne(long id) 
	{
		Optional<Dieselusedimport> op=dieselusedimportRepository.findById(id);
		return op.get();
	}
	
}