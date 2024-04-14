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
import com.AnkitIndia.jwtauthentication.model.Dailypowerreport;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailypowerreportRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;


@Service
public class DailypowerreportService_Imp implements DailypowerreportService{
	
	@Autowired
	DailypowerreportRepository dailypowerreportRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<Dailypowerreport> getdailypowerreportlist(String currDate,String finyear)
	{
		List<Dailypowerreport> daliylist = new ArrayList<Dailypowerreport>();
		daliylist.addAll(dailypowerreportRepository.getdailylist(currDate,finyear));
		
		return daliylist;
	}
	
	@Transactional
	public Dailypowerreport save(Dailypowerreport dailypowerreport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dailypowerreportRepository.countId() != null ) {
			slno=Long.parseLong(dailypowerreportRepository.countId());
		}
		String prefix="DPR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		dailypowerreport.setDailyreportid(gen_sno);
		dailypowerreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailypowerreport.getBusiness_unit()).getBusinessunit_name());

		dailypowerreport.setCompany_id(dailypowerreport.getCompany_id());
		dailypowerreport.setFin_year(dailypowerreport.getFin_year());
		dailypowerreport.setModified_type("INSERTED");
		dailypowerreport.setInserted_on(ldt);
		dailypowerreport.setInserted_by(userRepository.getUserDetails(dailypowerreport.getUsername()).getName());
		dailypowerreport.setUpdated_by(dailypowerreport.getUpdated_by());
		dailypowerreport.setUpdated_on(ldt);
		dailypowerreport.setDeleted_by("NA");
		dailypowerreport.setDeleted_on(ldt);
		
		return dailypowerreportRepository.save(dailypowerreport);	
	}
	
	@Transactional
	public Dailypowerreport update(Dailypowerreport dailypowerreport,long id) 
	{

		Optional<Dailypowerreport> op =dailypowerreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		dailypowerreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailypowerreport.getBusiness_unit()).getBusinessunit_name());

		dailypowerreport.setCompany_id(dailypowerreport.getCompany_id());
		dailypowerreport.setFin_year(dailypowerreport.getFin_year());
		dailypowerreport.setModified_type("INSERTED");
		dailypowerreport.setInserted_on(ldt);
		dailypowerreport.setInserted_by(userRepository.getUserDetails(dailypowerreport.getUsername()).getName());
		dailypowerreport.setUpdated_by(dailypowerreport.getUpdated_by());
		dailypowerreport.setUpdated_on(ldt);
		dailypowerreport.setDeleted_by("NA");
		dailypowerreport.setDeleted_on(ldt);
		
		return dailypowerreportRepository.save(dailypowerreport);
	
	}
	
	@Transactional
	public Dailypowerreport delete(Dailypowerreport dailypowerreport,long id) 
	{
		Optional<Dailypowerreport> op = dailypowerreportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Dailypowerreport dailypower=op.get();
		
		dailypower.setModified_type("DELETED");
		dailypower.setInserted_by(op.get().getInserted_by());
		dailypower.setInserted_on(op.get().getInserted_on());
		dailypower.setUpdated_by(op.get().getUpdated_by());
		dailypower.setUpdated_on(op.get().getUpdated_on());
		dailypower.setDeleted_by(userRepository.getUserDetails(dailypower.getUsername()).getName());
		dailypower.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			dailypower.setId(id);
		}
		
			return dailypowerreportRepository.save(dailypower);
	}
	
	public List<Dailypowerreport> searchdailypowerreport(String fromdate, String todate,String finyear)
	{
		List<Dailypowerreport> searchdaily =new ArrayList<Dailypowerreport>();
		String tablename="dailypowerreport",order_date="date";
		searchdaily.addAll(dailypowerreportRepository.getsearchdailypower(tablename,order_date,fromdate,todate,finyear));
		
		List<Dailypowerreport> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Dailypowerreport::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Dailypowerreport findOne(long id) 
	{
		Optional<Dailypowerreport> op=dailypowerreportRepository.findById(id);
		return op.get();
	}
	
}
