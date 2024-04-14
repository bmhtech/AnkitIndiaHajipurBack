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
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.MillbreakdownreportRepository;
import com.AnkitIndia.jwtauthentication.repository.Millbreakdownreport_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherparameterreportRepository;
import com.AnkitIndia.jwtauthentication.repository.Otherparameterreport_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class MillbreakdownreportService_Imp implements MillbreakdownreportService{

	@Autowired
	MillbreakdownreportRepository millbreakdownreportRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	Millbreakdownreport_DtlsRepository millbreakdownreport_DtlsRepository;
	
	public List<Millbreakdownreport> getMillBreakdownlist(String currDate,String finyear)
	{
		List<Millbreakdownreport> breakdownlist = new ArrayList<Millbreakdownreport>();
		breakdownlist.addAll(millbreakdownreportRepository.getMillBreakdownReportList(currDate,finyear));
		
		return breakdownlist;
	}
		
	@Transactional
	public Millbreakdownreport save(Millbreakdownreport millbreakdownreport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(millbreakdownreportRepository.countId() != null ) {
			slno=Long.parseLong(millbreakdownreportRepository.countId());
		}
		String prefix="MBR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		millbreakdownreport.setMillbreakdownid(gen_sno);
		millbreakdownreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(millbreakdownreport.getBusiness_unit()).getBusinessunit_name());

		millbreakdownreport.setCompany_id(millbreakdownreport.getCompany_id());
		millbreakdownreport.setFin_year(millbreakdownreport.getFin_year());
		millbreakdownreport.setModified_type("INSERTED");
		millbreakdownreport.setInserted_on(ldt);
		millbreakdownreport.setInserted_by(userRepository.getUserDetails(millbreakdownreport.getUsername()).getName());
		millbreakdownreport.setUpdated_by(millbreakdownreport.getUpdated_by());
		millbreakdownreport.setUpdated_on(ldt);
		millbreakdownreport.setDeleted_by("NA");
		millbreakdownreport.setDeleted_on(ldt);
		
		Set<Millbreakdownreport_Dtls> millbreakdownreport_Dtls=new HashSet<Millbreakdownreport_Dtls>();
		millbreakdownreport_Dtls.addAll(millbreakdownreport.getMillbreakdownreport_Dtls());
		for(Millbreakdownreport_Dtls mDetails:millbreakdownreport_Dtls) 
		{
			mDetails.setMillbreakdownid(gen_sno);
			mDetails.setCompany_id(millbreakdownreport.getCompany_id());
			mDetails.setFin_year(millbreakdownreport.getFin_year());
			mDetails.setModified_type("INSERTED");
			mDetails.setInserted_by(millbreakdownreport.getInserted_by());
			mDetails.setInserted_on(ldt);
			mDetails.setUpdated_by("NA");
			mDetails.setUpdated_on(ldt);
			mDetails.setDeleted_by("NA");
			mDetails.setDeleted_on(ldt);
			mDetails.setUsername(millbreakdownreport.getUsername());
		}
		millbreakdownreport.setMillbreakdownreport_Dtls(millbreakdownreport_Dtls);
		
		return millbreakdownreportRepository.save(millbreakdownreport);	
	}
	
	@Transactional
	public Millbreakdownreport update(Millbreakdownreport millbreakdownreport,long id) 
	{

		Optional<Millbreakdownreport> op =millbreakdownreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		millbreakdownreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(millbreakdownreport.getBusiness_unit()).getBusinessunit_name());
		
		millbreakdownreport.setMillbreakdownid(op.get().getMillbreakdownid());
		millbreakdownreport.setCompany_id(millbreakdownreport.getCompany_id());
		millbreakdownreport.setFin_year(millbreakdownreport.getFin_year());
		millbreakdownreport.setModified_type("INSERTED");
		millbreakdownreport.setInserted_on(ldt);
		millbreakdownreport.setInserted_by(userRepository.getUserDetails(millbreakdownreport.getUsername()).getName());
		millbreakdownreport.setUpdated_by(millbreakdownreport.getUpdated_by());
		millbreakdownreport.setUpdated_on(ldt);
		millbreakdownreport.setDeleted_by("NA");
		millbreakdownreport.setDeleted_on(ldt);
		
	
		millbreakdownreport_DtlsRepository.revertMillbreakdownreportDtls(op.get().getMillbreakdownid());
		
		Set<Millbreakdownreport_Dtls> millbreakdownreport_Dtls=new HashSet<Millbreakdownreport_Dtls>();
		millbreakdownreport_Dtls.addAll(millbreakdownreport.getMillbreakdownreport_Dtls());
		for(Millbreakdownreport_Dtls mDetails:millbreakdownreport_Dtls) 
		{
			
			
			mDetails.setMillbreakdownid(op.get().getMillbreakdownid());
			mDetails.setCompany_id(millbreakdownreport.getCompany_id());
			mDetails.setFin_year(millbreakdownreport.getFin_year());
			mDetails.setModified_type("INSERTED");
			mDetails.setInserted_by(userRepository.getUserDetails(millbreakdownreport.getUsername()).getName());
			mDetails.setInserted_on(ldt);
			mDetails.setUpdated_by(millbreakdownreport.getInserted_by());
			mDetails.setUpdated_on(ldt);
			mDetails.setDeleted_by("NA");
			mDetails.setDeleted_on(ldt);
			mDetails.setUsername(millbreakdownreport.getUsername());
		}
		millbreakdownreport.setMillbreakdownreport_Dtls(millbreakdownreport_Dtls);
		
		return millbreakdownreportRepository.save(millbreakdownreport);
	
	}
	
	@Transactional
	public Millbreakdownreport delete(Millbreakdownreport millbreakdownreport,long id) 
	{

		Optional<Millbreakdownreport> op = millbreakdownreportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Millbreakdownreport other=op.get();
		
		other.setModified_type("DELETED");
		other.setInserted_by(op.get().getInserted_by());
		other.setInserted_on(op.get().getInserted_on());
		other.setUpdated_by(op.get().getUpdated_by());
		other.setUpdated_on(op.get().getUpdated_on());
		other.setDeleted_by(userRepository.getUserDetails(other.getUsername()).getName());
		other.setDeleted_on(ldt);
		
		millbreakdownreport_DtlsRepository.deleteMillbreakdownreportDtls(op.get().getMillbreakdownid());
		
		
		if(op.isPresent())
		{
			other.setId(id);
		}
		
			return millbreakdownreportRepository.save(other);
	}
	
	public List<Millbreakdownreport> searcMillBreakdown(String fromdate, String todate,String finyear)
	{
		List<Millbreakdownreport> searchdaily =new ArrayList<Millbreakdownreport>();
		String tablename="millbreakdownreport",order_date="date";
		searchdaily.addAll(millbreakdownreportRepository.searchBreakdownReport(tablename,order_date,fromdate,todate,finyear));
		
		List<Millbreakdownreport> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Millbreakdownreport::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Millbreakdownreport findOne(long id) 
	{
		Optional<Millbreakdownreport> op=millbreakdownreportRepository.findById(id);
		return op.get();
	}
	
	public List<Millbreakdownreport_Dtls> retriveMillBreakdownDetails(String millbreakdownid)
	{
		List<Millbreakdownreport_Dtls> details= new ArrayList<Millbreakdownreport_Dtls>();
		details.addAll(millbreakdownreport_DtlsRepository.getdetails(millbreakdownid));
		
		return details;
	}
}
