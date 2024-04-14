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
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Weigherreding;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.MillbreakdownreportRepository;
import com.AnkitIndia.jwtauthentication.repository.Millbreakdownreport_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WeigherredingRepository;

@Service
public class WeigherredingService_Imp implements WeigherredingService{

	@Autowired
	WeigherredingRepository weigherredingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	public List<Weigherreding> getWeigherReadingList(String currDate,String finyear)
	{
		List<Weigherreding> weigherlist = new ArrayList<Weigherreding>();
		weigherlist.addAll(weigherredingRepository.getWeigherRedingList(currDate,finyear));
		
		return weigherlist;
	}
		
	@Transactional
	public Weigherreding save(Weigherreding weigherreding) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(weigherredingRepository.countId() != null ) {
			slno=Long.parseLong(weigherredingRepository.countId());
		}
		String prefix="WRR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		weigherreding.setWeigherredingid(gen_sno);
		weigherreding.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(weigherreding.getBusiness_unit()).getBusinessunit_name());

		weigherreding.setCompany_id(weigherreding.getCompany_id());
		weigherreding.setFin_year(weigherreding.getFin_year());
		weigherreding.setModified_type("INSERTED");
		weigherreding.setInserted_on(ldt);
		weigherreding.setInserted_by(userRepository.getUserDetails(weigherreding.getUsername()).getName());
		weigherreding.setUpdated_by(weigherreding.getUpdated_by());
		weigherreding.setUpdated_on(ldt);
		weigherreding.setDeleted_by("NA");
		weigherreding.setDeleted_on(ldt);
		
		return weigherredingRepository.save(weigherreding);	
	}
	
	@Transactional
	public Weigherreding update(Weigherreding weigherreding,long id) 
	{

		Optional<Weigherreding> op =weigherredingRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		weigherreding.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(weigherreding.getBusiness_unit()).getBusinessunit_name());
		
		weigherreding.setWeigherredingid(op.get().getWeigherredingid());
		weigherreding.setCompany_id(weigherreding.getCompany_id());
		weigherreding.setFin_year(weigherreding.getFin_year());
		weigherreding.setModified_type("INSERTED");
		weigherreding.setInserted_on(ldt);
		weigherreding.setInserted_by(userRepository.getUserDetails(weigherreding.getUsername()).getName());
		weigherreding.setUpdated_by(weigherreding.getUpdated_by());
		weigherreding.setUpdated_on(ldt);
		weigherreding.setDeleted_by("NA");
		weigherreding.setDeleted_on(ldt);
		
	
		return weigherredingRepository.save(weigherreding);
	
	}
	
	@Transactional
	public Weigherreding delete(Weigherreding weigherreding,long id) 
	{

		Optional<Weigherreding> op = weigherredingRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Weigherreding weigher=op.get();
		
		weigher.setModified_type("DELETED");
		weigher.setInserted_by(op.get().getInserted_by());
		weigher.setInserted_on(op.get().getInserted_on());
		weigher.setUpdated_by(op.get().getUpdated_by());
		weigher.setUpdated_on(op.get().getUpdated_on());
		weigher.setDeleted_by(userRepository.getUserDetails(weigher.getUsername()).getName());
		weigher.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			weigher.setId(id);
		}
		
			return weigherredingRepository.save(weigher);
	}
	
	public List<Weigherreding> searcWeigherReading(String fromdate, String todate,String finyear)
	{
		List<Weigherreding> searchdaily =new ArrayList<Weigherreding>();
		String tablename="weigherreding",order_date="date";
		searchdaily.addAll(weigherredingRepository.searchWeigherReding(tablename,order_date,fromdate,todate,finyear));
		
		List<Weigherreding> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Weigherreding::getDate))
				  .collect(Collectors.toList());
		return allData;
	}
	
	public Weigherreding findOne(long id) 
	{
		Optional<Weigherreding> op=weigherredingRepository.findById(id);
		return op.get();
	}
}
