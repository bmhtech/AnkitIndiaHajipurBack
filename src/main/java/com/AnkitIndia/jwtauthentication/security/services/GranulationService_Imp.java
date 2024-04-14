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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Granulation;
import com.AnkitIndia.jwtauthentication.model.Granulation_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GranulationRepository;
import com.AnkitIndia.jwtauthentication.repository.Granulation_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Seives_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class GranulationService_Imp implements GranulationService{

	@Autowired
	GranulationRepository granulationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Granulation_DtlsRepository granulation_DtlsReository;
	
	@Autowired
	Seives_DtlsRepository seives_DtlsRepository;
	
	public List<Granulation> getGranulationlist(String currDate,String finyear)
	{
		List<Granulation> list = new ArrayList<Granulation>();
		list.addAll(granulationRepository.getGranulationlist(currDate,finyear));
		
		
		return list;
	}
		
	public List<Seives_Dtls> getSeiveslistByitemid(String itemid)
	{
		List<Seives_Dtls> list = new ArrayList<Seives_Dtls>();
		list.addAll(seives_DtlsRepository.getSeivesDetailsThruItemId(itemid));
		
		
		return list;
	}
	
	@Transactional
	public Granulation save(Granulation granulation) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(granulationRepository.countId() != null ) {
			slno=Long.parseLong(granulationRepository.countId());
		}
		String prefix="GLR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		granulation.setGranulationreportid(gen_sno);
		granulation.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(granulation.getBusiness_unit()).getBusinessunit_name());
		System.out.println("item data::"+granulation.getItemid());
		if(Utility.isNullOrEmpty(granulation.getItemid()))
		{
			granulation.setItem_name(item_masterRepository.getItemDetailsThruItemId(granulation.getItemid()).getItem_name());
		}
		else 
		{
			granulation.setItem_name("");
		}
		if(Utility.isNullOrEmpty(granulation.getApprovedby()))
		{
			granulation.setApprovedby_name(employeeMasterRepository.getEmployee(granulation.getApprovedby()).getEmp_name());
		}
		else 
		{
			granulation.setApprovedby_name("");
		}

		granulation.setCompany_id(granulation.getCompany_id());
		granulation.setFin_year(granulation.getFin_year());
		granulation.setModified_type("INSERTED");
		granulation.setInserted_on(ldt);
		granulation.setInserted_by(userRepository.getUserDetails(granulation.getUsername()).getName());
		granulation.setUpdated_by(granulation.getUpdated_by());
		granulation.setUpdated_on(ldt);
		granulation.setDeleted_by("NA");
		granulation.setDeleted_on(ldt);
		
		Set<Granulation_Dtls> granulation_Dtls=new HashSet<Granulation_Dtls>();
		granulation_Dtls.addAll(granulation.getGranulation_Dtls());
		for(Granulation_Dtls gDetails:granulation_Dtls) 
		{
			gDetails.setGranulationreportid(gen_sno);
			gDetails.setItemid(granulation.getItemid());
			gDetails.setCompany_id(granulation.getCompany_id());
			gDetails.setFin_year(granulation.getFin_year());
			gDetails.setModified_type("INSERTED");
			gDetails.setInserted_by(granulation.getInserted_by());
			gDetails.setInserted_on(ldt);
			gDetails.setUpdated_by("NA");
			gDetails.setUpdated_on(ldt);
			gDetails.setDeleted_by("NA");
			gDetails.setDeleted_on(ldt);
			gDetails.setUsername(granulation.getUsername());
		}
		granulation.setGranulation_Dtls(granulation_Dtls);
		
		return granulationRepository.save(granulation);	
	}
	
	@Transactional
	public Granulation update(Granulation granulation,long id) 
	{

		Optional<Granulation> op =granulationRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		granulation.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(granulation.getBusiness_unit()).getBusinessunit_name());
		System.out.println("item data::"+granulation.getItemid());
		if(Utility.isNullOrEmpty(granulation.getItemid()))
		{
			granulation.setItem_name(item_masterRepository.getItemDetailsThruItemId(granulation.getItemid()).getItem_name());
		}
		else 
		{
			granulation.setItem_name("");
		}
		if(Utility.isNullOrEmpty(granulation.getApprovedby()))
		{
			granulation.setApprovedby_name(employeeMasterRepository.getEmployee(granulation.getApprovedby()).getEmp_name());
		}
		else 
		{
			granulation.setApprovedby_name("");
		}
		
		granulation.setGranulationreportid(op.get().getGranulationreportid());
		granulation.setCompany_id(granulation.getCompany_id());
		granulation.setFin_year(granulation.getFin_year());
		granulation.setModified_type("INSERTED");
		granulation.setInserted_on(ldt);
		granulation.setInserted_by(userRepository.getUserDetails(granulation.getUsername()).getName());
		granulation.setUpdated_by(granulation.getUpdated_by());
		granulation.setUpdated_on(ldt);
		granulation.setDeleted_by("NA");
		granulation.setDeleted_on(ldt);
		
	
		granulation_DtlsReository.revertGranulation_Dtls(op.get().getGranulationreportid());
		
		Set<Granulation_Dtls> granulation_Dtls=new HashSet<Granulation_Dtls>();
		granulation_Dtls.addAll(granulation.getGranulation_Dtls());
		for(Granulation_Dtls gDetails:granulation_Dtls) 
		{
			
			
			gDetails.setGranulationreportid(op.get().getGranulationreportid());
			gDetails.setItemid(granulation.getItemid());
			gDetails.setCompany_id(granulation.getCompany_id());
			gDetails.setFin_year(granulation.getFin_year());
			gDetails.setModified_type("INSERTED");
			gDetails.setInserted_by(granulation.getInserted_by());
			gDetails.setInserted_on(ldt);
			gDetails.setUpdated_by(userRepository.getUserDetails(granulation.getUsername()).getName());
			gDetails.setUpdated_on(ldt);
			gDetails.setDeleted_by("NA");
			gDetails.setDeleted_on(ldt);
			gDetails.setUsername(granulation.getUsername());
		}
		granulation.setGranulation_Dtls(granulation_Dtls);
		
		
		return granulationRepository.save(granulation);
	
	}
	
	@Transactional
	public Granulation delete(Granulation granulation,long id) 
	{

		Optional<Granulation> op = granulationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Granulation granul=op.get();
		
		granul.setModified_type("DELETED");
		granul.setInserted_by(op.get().getInserted_by());
		granul.setInserted_on(op.get().getInserted_on());
		granul.setUpdated_by(op.get().getUpdated_by());
		granul.setUpdated_on(op.get().getUpdated_on());
		granul.setDeleted_by(userRepository.getUserDetails(granul.getUsername()).getName());
		granul.setDeleted_on(ldt);
		
		granulation_DtlsReository.deleteGranulation_Dtls(op.get().getGranulationreportid());
		
		
		if(op.isPresent())
		{
			granul.setId(id);
		}
		
			return granulationRepository.save(granul);
		
	
	}
	

	public List<Granulation> searchGranulationReport(String fromdate, String todate,String finyear)
	{
		List<Granulation> searchdaily =new ArrayList<Granulation>();
		String tablename="granulation",order_date="date";
		searchdaily.addAll(granulationRepository.searchGranulationReport(tablename,order_date,fromdate,todate,finyear));
		
		List<Granulation> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Granulation::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Granulation findOne(long id) 
	{
		Optional<Granulation> op=granulationRepository.findById(id);
		return op.get();
	}
	
	public List<Granulation_Dtls> retriveGranulationDetails(String granulationreportid)
	{
		List<Granulation_Dtls> details= new ArrayList<Granulation_Dtls>();
		details.addAll(granulation_DtlsReository.getdetails(granulationreportid));
		
		return details;
	}
	
}
