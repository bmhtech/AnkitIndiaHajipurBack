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
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.MisclabreportfgRepository;
import com.AnkitIndia.jwtauthentication.repository.Misclabreportfg_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class MisclabreportfgService_Imp implements MisclabreportfgService{
	
	@Autowired
	MisclabreportfgRepository misclabreportfgRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Misclabreportfg_DtlsRepository misclabreportfg_DtlsReository;
	
	public List<Misclabreportfg> getLabReportlist(String currDate,String finyear)
	{
		List<Misclabreportfg> lablist = new ArrayList<Misclabreportfg>();
		lablist.addAll(misclabreportfgRepository.getLabReportList(currDate,finyear));
		
		
		return lablist;
	}
		
	@Transactional
	public Misclabreportfg save(Misclabreportfg misclabreportfg) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(misclabreportfgRepository.countId() != null ) {
			slno=Long.parseLong(misclabreportfgRepository.countId());
		}
		String prefix="LFR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		misclabreportfg.setMisclabreportfgid(gen_sno);
		misclabreportfg.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(misclabreportfg.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(misclabreportfg.getApprovedby()))
		{
			misclabreportfg.setApprovedby_name(employeeMasterRepository.getEmployee(misclabreportfg.getApprovedby()).getEmp_name());
		}
		else 
		{
			misclabreportfg.setApprovedby_name("");
		}

		misclabreportfg.setCompany_id(misclabreportfg.getCompany_id());
		misclabreportfg.setFin_year(misclabreportfg.getFin_year());
		misclabreportfg.setModified_type("INSERTED");
		misclabreportfg.setInserted_on(ldt);
		misclabreportfg.setInserted_by(userRepository.getUserDetails(misclabreportfg.getUsername()).getName());
		misclabreportfg.setUpdated_by(misclabreportfg.getUpdated_by());
		misclabreportfg.setUpdated_on(ldt);
		misclabreportfg.setDeleted_by("NA");
		misclabreportfg.setDeleted_on(ldt);
		
		Set<Misclabreportfg_Dtls> misclabreportfg_Dtls=new HashSet<Misclabreportfg_Dtls>();
		misclabreportfg_Dtls.addAll(misclabreportfg.getMisclabreportfg_Dtls());
		for(Misclabreportfg_Dtls lDetails:misclabreportfg_Dtls) 
		{
			lDetails.setMisclabreportfgid(gen_sno);
			System.out.println("set data::"+lDetails.getItemid());
			lDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(lDetails.getItemid()).getItem_name());
			lDetails.setCompany_id(misclabreportfg.getCompany_id());
			lDetails.setFin_year(misclabreportfg.getFin_year());
			lDetails.setModified_type("INSERTED");
			lDetails.setInserted_by(misclabreportfg.getInserted_by());
			lDetails.setInserted_on(ldt);
			lDetails.setUpdated_by("NA");
			lDetails.setUpdated_on(ldt);
			lDetails.setDeleted_by("NA");
			lDetails.setDeleted_on(ldt);
			lDetails.setUsername(misclabreportfg.getUsername());
		}
		misclabreportfg.setMisclabreportfg_Dtls(misclabreportfg_Dtls);
		
		return misclabreportfgRepository.save(misclabreportfg);	
	}
	
	@Transactional
	public Misclabreportfg update(Misclabreportfg misclabreportfg,long id) 
	{

		Optional<Misclabreportfg> op =misclabreportfgRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		misclabreportfg.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(misclabreportfg.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(misclabreportfg.getApprovedby()))
		{
			misclabreportfg.setApprovedby_name(employeeMasterRepository.getEmployee(misclabreportfg.getApprovedby()).getEmp_name());
		}
		else 
		{
			misclabreportfg.setApprovedby_name("");
		}
		
		misclabreportfg.setMisclabreportfgid(op.get().getMisclabreportfgid());
		misclabreportfg.setCompany_id(misclabreportfg.getCompany_id());
		misclabreportfg.setFin_year(misclabreportfg.getFin_year());
		misclabreportfg.setModified_type("INSERTED");
		misclabreportfg.setInserted_on(ldt);
		misclabreportfg.setInserted_by(userRepository.getUserDetails(misclabreportfg.getUsername()).getName());
		misclabreportfg.setUpdated_by(misclabreportfg.getUpdated_by());
		misclabreportfg.setUpdated_on(ldt);
		misclabreportfg.setDeleted_by("NA");
		misclabreportfg.setDeleted_on(ldt);
		
	
		misclabreportfg_DtlsReository.revertMisclabreportfg_DtlsRepository(op.get().getMisclabreportfgid());
		
		Set<Misclabreportfg_Dtls> misclabreportfg_Dtls=new HashSet<Misclabreportfg_Dtls>();
		misclabreportfg_Dtls.addAll(misclabreportfg.getMisclabreportfg_Dtls());
		for(Misclabreportfg_Dtls lDetails:misclabreportfg_Dtls) 
		{
			
			
			lDetails.setMisclabreportfgid(op.get().getMisclabreportfgid());
			lDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(lDetails.getItemid()).getItem_name());
			lDetails.setCompany_id(misclabreportfg.getCompany_id());
			lDetails.setFin_year(misclabreportfg.getFin_year());
			lDetails.setModified_type("INSERTED");
			lDetails.setInserted_by(misclabreportfg.getInserted_by());
			lDetails.setInserted_on(ldt);
			lDetails.setUpdated_by("NA");
			lDetails.setUpdated_on(ldt);
			lDetails.setDeleted_by("NA");
			lDetails.setDeleted_on(ldt);
			lDetails.setUsername(misclabreportfg.getUsername());
		}
		misclabreportfg.setMisclabreportfg_Dtls(misclabreportfg_Dtls);
		
		
		return misclabreportfgRepository.save(misclabreportfg);
	
	}
	
	@Transactional
	public Misclabreportfg delete(Misclabreportfg misclabreportfg,long id) 
	{

		Optional<Misclabreportfg> op = misclabreportfgRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Misclabreportfg lab=op.get();
		
		lab.setModified_type("DELETED");
		lab.setInserted_by(op.get().getInserted_by());
		lab.setInserted_on(op.get().getInserted_on());
		lab.setUpdated_by(op.get().getUpdated_by());
		lab.setUpdated_on(op.get().getUpdated_on());
		lab.setDeleted_by(userRepository.getUserDetails(lab.getUsername()).getName());
		lab.setDeleted_on(ldt);
		
		misclabreportfg_DtlsReository.deleteMisclabreportfg_Dtls(op.get().getMisclabreportfgid());
		
		
		if(op.isPresent())
		{
			lab.setId(id);
		}
		
			return misclabreportfgRepository.save(lab);
		
	
	}
	

	public List<Misclabreportfg> searcLabReport(String fromdate, String todate,String finyear)
	{
		List<Misclabreportfg> searchdaily =new ArrayList<Misclabreportfg>();
		String tablename="misclabreportfg",order_date="date";
		searchdaily.addAll(misclabreportfgRepository.searchLabReport(tablename,order_date,fromdate,todate,finyear));
		
		List<Misclabreportfg> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Misclabreportfg::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Misclabreportfg findOne(long id) 
	{
		Optional<Misclabreportfg> op=misclabreportfgRepository.findById(id);
		return op.get();
	}
	
	public List<Misclabreportfg_Dtls> retriveLabReportDetails(String misclabreportfgid)
	{
		List<Misclabreportfg_Dtls> details= new ArrayList<Misclabreportfg_Dtls>();
		details.addAll(misclabreportfg_DtlsReository.getdetails(misclabreportfgid));
		
		return details;
	}
	
	
	 
}
