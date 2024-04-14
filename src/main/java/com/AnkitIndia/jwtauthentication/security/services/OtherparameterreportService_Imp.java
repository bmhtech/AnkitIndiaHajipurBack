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
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg;
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg_Dtls;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.MisclabreportfgRepository;
import com.AnkitIndia.jwtauthentication.repository.Misclabreportfg_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.OtherparameterreportRepository;
import com.AnkitIndia.jwtauthentication.repository.Otherparameterreport_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class OtherparameterreportService_Imp implements OtherparameterreportService{

	@Autowired
	OtherparameterreportRepository otherparameterreportRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Otherparameterreport_DtlsRepository otherparameterreport_DtlsRepository;
	
	public List<Otherparameterreport> getOtherParameterlist(String currDate,String finyear)
	{
		List<Otherparameterreport> lablist = new ArrayList<Otherparameterreport>();
		lablist.addAll(otherparameterreportRepository.getLOtherPartnerReportList(currDate,finyear));
		
		
		return lablist;
	}
		
	@Transactional
	public Otherparameterreport save(Otherparameterreport otherparameterreport) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(otherparameterreportRepository.countId() != null ) {
			slno=Long.parseLong(otherparameterreportRepository.countId());
		}
		String prefix="OPR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		otherparameterreport.setOtherparameterid(gen_sno);
		otherparameterreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherparameterreport.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(otherparameterreport.getApprovedby()))
		{
			otherparameterreport.setApprovedby_name(employeeMasterRepository.getEmployee(otherparameterreport.getApprovedby()).getEmp_name());
		}
		else 
		{
			otherparameterreport.setApprovedby_name("");
		}

		otherparameterreport.setCompany_id(otherparameterreport.getCompany_id());
		otherparameterreport.setFin_year(otherparameterreport.getFin_year());
		otherparameterreport.setModified_type("INSERTED");
		otherparameterreport.setInserted_on(ldt);
		otherparameterreport.setInserted_by(userRepository.getUserDetails(otherparameterreport.getUsername()).getName());
		otherparameterreport.setUpdated_by(otherparameterreport.getUpdated_by());
		otherparameterreport.setUpdated_on(ldt);
		otherparameterreport.setDeleted_by("NA");
		otherparameterreport.setDeleted_on(ldt);
		
		Set<Otherparameterreport_Dtls> otherparameterreport_Dtls=new HashSet<Otherparameterreport_Dtls>();
		otherparameterreport_Dtls.addAll(otherparameterreport.getOtherparameterreport_Dtls());
		for(Otherparameterreport_Dtls oDetails:otherparameterreport_Dtls) 
		{
			oDetails.setOtherparameterid(gen_sno);
			System.out.println("set data::"+oDetails.getItemid());
			oDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(oDetails.getItemid()).getItem_name());
			oDetails.setCompany_id(otherparameterreport.getCompany_id());
			oDetails.setFin_year(otherparameterreport.getFin_year());
			oDetails.setModified_type("INSERTED");
			oDetails.setInserted_by(otherparameterreport.getInserted_by());
			oDetails.setInserted_on(ldt);
			oDetails.setUpdated_by("NA");
			oDetails.setUpdated_on(ldt);
			oDetails.setDeleted_by("NA");
			oDetails.setDeleted_on(ldt);
			oDetails.setUsername(otherparameterreport.getUsername());
		}
		otherparameterreport.setOtherparameterreport_Dtls(otherparameterreport_Dtls);
		
		return otherparameterreportRepository.save(otherparameterreport);	
	}
	
	@Transactional
	public Otherparameterreport update(Otherparameterreport otherparameterreport,long id) 
	{

		Optional<Otherparameterreport> op =otherparameterreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		otherparameterreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(otherparameterreport.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(otherparameterreport.getApprovedby()))
		{
			otherparameterreport.setApprovedby_name(employeeMasterRepository.getEmployee(otherparameterreport.getApprovedby()).getEmp_name());
		}
		else 
		{
			otherparameterreport.setApprovedby_name("");
		}
		
		otherparameterreport.setOtherparameterid(op.get().getOtherparameterid());
		otherparameterreport.setCompany_id(otherparameterreport.getCompany_id());
		otherparameterreport.setFin_year(otherparameterreport.getFin_year());
		otherparameterreport.setModified_type("INSERTED");
		otherparameterreport.setInserted_on(ldt);
		otherparameterreport.setInserted_by(userRepository.getUserDetails(otherparameterreport.getUsername()).getName());
		otherparameterreport.setUpdated_by(otherparameterreport.getUpdated_by());
		otherparameterreport.setUpdated_on(ldt);
		otherparameterreport.setDeleted_by("NA");
		otherparameterreport.setDeleted_on(ldt);
		
	
		otherparameterreport_DtlsRepository.revertOtherParameterDtls(op.get().getOtherparameterid());
		
		Set<Otherparameterreport_Dtls> otherparameterreport_Dtls=new HashSet<Otherparameterreport_Dtls>();
		otherparameterreport_Dtls.addAll(otherparameterreport.getOtherparameterreport_Dtls());
		for(Otherparameterreport_Dtls oDetails:otherparameterreport_Dtls) 
		{
			
			
			oDetails.setOtherparameterid(op.get().getOtherparameterid());
			oDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(oDetails.getItemid()).getItem_name());
			oDetails.setCompany_id(otherparameterreport.getCompany_id());
			oDetails.setFin_year(otherparameterreport.getFin_year());
			oDetails.setModified_type("INSERTED");
			oDetails.setInserted_by(userRepository.getUserDetails(otherparameterreport.getUsername()).getName());
			oDetails.setInserted_on(ldt);
			oDetails.setUpdated_by(otherparameterreport.getInserted_by());
			oDetails.setUpdated_on(ldt);
			oDetails.setDeleted_by("NA");
			oDetails.setDeleted_on(ldt);
			oDetails.setUsername(otherparameterreport.getUsername());
		}
		otherparameterreport.setOtherparameterreport_Dtls(otherparameterreport_Dtls);
		
		
		return otherparameterreportRepository.save(otherparameterreport);
	
	}
	
	@Transactional
	public Otherparameterreport delete(Otherparameterreport otherparameterreport,long id) 
	{

		Optional<Otherparameterreport> op = otherparameterreportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Otherparameterreport other=op.get();
		
		other.setModified_type("DELETED");
		other.setInserted_by(op.get().getInserted_by());
		other.setInserted_on(op.get().getInserted_on());
		other.setUpdated_by(op.get().getUpdated_by());
		other.setUpdated_on(op.get().getUpdated_on());
		other.setDeleted_by(userRepository.getUserDetails(other.getUsername()).getName());
		other.setDeleted_on(ldt);
		
		otherparameterreport_DtlsRepository.deleteOtherParameterDtls(op.get().getOtherparameterid());
		
		
		if(op.isPresent())
		{
			other.setId(id);
		}
		
			return otherparameterreportRepository.save(other);
		
	
	}
	

	public List<Otherparameterreport> searcOtherparameter(String fromdate, String todate,String finyear)
	{
		List<Otherparameterreport> searchdaily =new ArrayList<Otherparameterreport>();
		String tablename="otherparameterreport",order_date="date";
		searchdaily.addAll(otherparameterreportRepository.searchOtherPartnerReport(tablename,order_date,fromdate,todate,finyear));
		
		List<Otherparameterreport> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Otherparameterreport::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Otherparameterreport findOne(long id) 
	{
		Optional<Otherparameterreport> op=otherparameterreportRepository.findById(id);
		return op.get();
	}
	
	public List<Otherparameterreport_Dtls> retriveOtherParameterDetails(String otherparameterreportid)
	{
		List<Otherparameterreport_Dtls> details= new ArrayList<Otherparameterreport_Dtls>();
		details.addAll(otherparameterreport_DtlsRepository.getdetails(otherparameterreportid));
		
		return details;
	}
}
