package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.model.Bingroup;
import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Binreportdetails;

import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.BingroupRepository;
import com.AnkitIndia.jwtauthentication.repository.BinreportRepository;
import com.AnkitIndia.jwtauthentication.repository.BinreportdetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.BinreportdetailspopupDTO;


@Service
public class BinreportService_Imp implements BinreportService{
	
	@Autowired
	BinreportRepository binreportRepository;

	@Autowired
	BinreportdetailsRepository binreportdetailsRepository;
	
	@Autowired
	BingroupRepository bingroupRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	
	
	@Transactional
	public Binreport save(Binreport binreport)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="BIN";
		
		if(binreportRepository.countId() != null ) 
		{
			slno=Long.parseLong(binreportRepository.countId());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		binreport.setBinreportid(gen_sno);
		
		if(Utility.isNullOrEmpty(binreport.getApprovedby()))
		{
			binreport.setApprovedbyname(employeeMasterRepository.getEmployee(binreport.getApprovedby()).getEmp_name());
		}
		else 
		{
			binreport.setApprovedbyname("");
		}
		binreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(binreport.getBusiness_unit()).getBusinessunit_name());
		binreport.setModified_type("INSERTED");
		binreport.setInserted_by(userRepository.getUserDetails(binreport.getUsername()).getName());
		binreport.setInserted_on(ldt);
		binreport.setUpdated_by("NA");
		binreport.setUpdated_on(ldt);
		binreport.setDeleted_by("NA");
		binreport.setDeleted_on(ldt);
		
		
		Set<Binreportdetails> binreportdetails = new HashSet<Binreportdetails>();
		binreportdetails.addAll(binreport.getBinreportdetails());
		for(Binreportdetails details : binreportdetails)
		{
			details.setBinreportid(gen_sno);
			details.setBingroupname(bingroupRepository.binGroupName(details.getBingroupid()).getBingroupname());
			details.setBinname(binMasterRepository.getBinDesc(details.getBinid()).getBin_description());
			
			details.setCompany_id(binreport.getCompany_id());
			details.setFin_year(binreport.getFin_year());
			details.setModified_type("INSERTED");
			details.setInserted_by(binreport.getInserted_by());
			details.setInserted_on(binreport.getInserted_on());
			details.setUpdated_by("NA");
			details.setUpdated_on(ldt);
			details.setDeleted_by("NA");
			details.setDeleted_on(ldt);
		
		}
		
		binreport.setBinreportdetails(binreportdetails);
		
		return binreportRepository.save(binreport);
	}
	
	@Transactional
	public Binreport update(Binreport binreport,long id) 
	{
		 Optional<Binreport> op = binreportRepository.findById(id);
		 LocalDateTime ldt = LocalDateTime.now();
		
		    binreport.setBinreportid(op.get().getBinreportid());
		    if(Utility.isNullOrEmpty(binreport.getApprovedby()))
			{
				binreport.setApprovedbyname(employeeMasterRepository.getEmployee(binreport.getApprovedby()).getEmp_name());
			}
			else 
			{
				binreport.setApprovedbyname("");
			}
		    binreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(binreport.getBusiness_unit()).getBusinessunit_name());
			binreport.setModified_type("INSERTED");
			binreport.setInserted_by(op.get().getInserted_by());
			binreport.setInserted_on(op.get().getInserted_on());
			binreport.setUpdated_by(userRepository.getUserDetails(binreport.getUsername()).getName());
			binreport.setUpdated_on(ldt);
			binreport.setDeleted_by("NA");
			binreport.setDeleted_on(ldt);
			
			binreportdetailsRepository.updateprevious(op.get().getBinreportid());
			
			Set<Binreportdetails> binreportdetails = new HashSet<Binreportdetails>();
			binreportdetails.addAll(binreport.getBinreportdetails());
			for(Binreportdetails details : binreportdetails)
			{
				details.setBinreportid(op.get().getBinreportid());
				details.setBingroupname(bingroupRepository.binGroupName(details.getBingroupid()).getBingroupname());
				details.setBinname(binMasterRepository.getBinDesc(details.getBinid()).getBin_description());
				
				details.setCompany_id(binreport.getCompany_id());
				details.setFin_year(binreport.getFin_year());
				details.setModified_type("INSERTED");
				details.setInserted_by(op.get().getInserted_by());
				details.setInserted_on(op.get().getInserted_on());
				details.setUpdated_by(binreport.getInserted_by());
				details.setUpdated_on(binreport.getInserted_on());
				details.setDeleted_by("NA");
				details.setDeleted_on(ldt);
			
			}
			
			binreport.setBinreportdetails(binreportdetails);
			
		 return binreportRepository.save(binreport);
	}
	
	@Transactional
	public Binreport delete(Binreport binreport,long id) 
	{
		 Optional<Binreport> op = binreportRepository.findById(id);
		 LocalDateTime ldt = LocalDateTime.now();
		 
		 binreport.setBinreportid(op.get().getBinreportid());
		 binreport.setModified_type("DELETED");
		 binreport.setInserted_by(op.get().getInserted_by());
		 binreport.setInserted_on(op.get().getInserted_on());
		 binreport.setUpdated_by(op.get().getUpdated_by());
		 binreport.setUpdated_on(op.get().getUpdated_on());
		 binreport.setDeleted_by(userRepository.getUserDetails(binreport.getUsername()).getName());
		 binreport.setDeleted_on(ldt);
		
		 binreportdetailsRepository.deleteprevious(op.get().getBinreportid());
		 
		 return binreportRepository.save(binreport);	
	}
	
	public List<Binreportdetails> getbinreportlist(String businessunit_id,String finyear)
	{
		 List<Binreportdetails> reportdetails = new ArrayList<Binreportdetails>();
		 
		 boolean status=binreportRepository.checkbinreport();
		 
		 if(status == true) 
		 {
			 
			 Binreport binlastdata=binreportRepository.getlastrowdata(finyear);
			 
			 List<Binreportdetails> getdetailsdata=binreportdetailsRepository.getdetailsrow(binlastdata.getBinreportid());
			 for(int i=0;i<getdetailsdata.size();i++) 
			 {
				 Binreportdetails details=new Binreportdetails();
				 
				 details.setBingroupid(getdetailsdata.get(i).getBingroupid());
				 details.setBingroupname(getdetailsdata.get(i).getBingroupname());
				 details.setBinid(getdetailsdata.get(i).getBinid());
				 details.setBinname(getdetailsdata.get(i).getBinname());
				 details.setDip("0");
				 details.setMt("0");
				 details.setPrevdip(getdetailsdata.get(i).getDip());
				 details.setPrevmt(getdetailsdata.get(i).getMt());
				 
				 reportdetails.add(details);
				 
			 }
			
		 }
		 else 
		 {
			 
			 List<Bingroup> bingroup=bingroupRepository.getBinGrouplist();
			 
			 for(int i=0;i<bingroup.size();i++) 
			 {
				 
				 List<Bin_master> bindetails=binMasterRepository.getbinlistbygroup(bingroup.get(i).getBingroupid());
				 for(int v=0;v<bindetails.size();v++) 
				 {
					 Binreportdetails details=new Binreportdetails();
					 
					 details.setBingroupid(bingroup.get(i).getBingroupid());
					 details.setBingroupname(bingroup.get(i).getBingroupname());
					 details.setBinid(bindetails.get(v).getBin_code());
					 details.setBinname(bindetails.get(v).getBin_description());
					 details.setDip("0");
					 details.setMt("0");
					 details.setPrevdip("0");
					 details.setPrevmt("0");
					 
					 reportdetails.add(details);
					 
				 }
			 }
			 
			 
		 }
		 
		 
		 return reportdetails;
	}
	
	public Binreport findOne(long id) 
	{
		Optional<Binreport> op=binreportRepository.findById(id);
		return op.get();
	}
	
	public List<Binreportdetails> retrivebillreportDetails(String binreportid)
	{
		 List<Binreportdetails> getdetailsdata=binreportdetailsRepository.getdetailsrow(binreportid);
		return getdetailsdata;
	}
	
	
	public List<BinreportdetailspopupDTO> retrivebillreportcolumnDetails(String binreportid)
	{
		 List<Object[]> getdetailsdata=new ArrayList<Object[]>();
		 getdetailsdata.addAll(binreportdetailsRepository.getdetailscolumn(binreportid));
			
			
		 List<BinreportdetailspopupDTO> list = new ArrayList<BinreportdetailspopupDTO>();     
			
		    for(final Object o : getdetailsdata)
		    {
		        Object[] obj = (Object[]) o;
		        list.add(new BinreportdetailspopupDTO(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString()));
		    }
		
		 return list;
	}

	public List<Binreport> getbillreportlist(String finyear)
	{
		List<Binreport> alllist=binreportRepository.getbillreportlist(finyear);
		return alllist;
	}
}
