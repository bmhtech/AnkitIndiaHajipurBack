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
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreport;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreportdetails;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GodownMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WheatstackcardreportRepository;
import com.AnkitIndia.jwtauthentication.repository.WheatstackcardreportdetailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;

@Service
public class WheatstackcardreportService_Imp implements WheatstackcardreportService{
	
	
	@Autowired
	WheatstackcardreportRepository wheatstackcardreportRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WheatstackcardreportdetailsRepository wheatstackcardreportdetailsRepository;
	
	@Autowired
	GodownMasterRepository godownMasterRepository;
	
	public Wheatstackcardreport save(Wheatstackcardreport wheatstackcardreport) 
	{
        LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wheatstackcardreportRepository.countId() != null ) {
			slno=Long.parseLong(wheatstackcardreportRepository.countId());
		}
		String prefix="WSC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		wheatstackcardreport.setWheatstackid(gen_sno);
		wheatstackcardreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(wheatstackcardreport.getBusiness_unit()).getBusinessunit_name());
		wheatstackcardreport.setGodownname(godownMasterRepository.getGodownNameById(wheatstackcardreport.getGodowncode()).getGodownname());
		
		wheatstackcardreport.setCompany_id(wheatstackcardreport.getCompany_id());
		wheatstackcardreport.setFin_year(wheatstackcardreport.getFin_year());
		wheatstackcardreport.setModified_type("INSERTED");
		wheatstackcardreport.setInserted_on(ldt);
		wheatstackcardreport.setInserted_by(userRepository.getUserDetails(wheatstackcardreport.getUsername()).getName());
		wheatstackcardreport.setUpdated_by(wheatstackcardreport.getUpdated_by());
		wheatstackcardreport.setUpdated_on(ldt);
		wheatstackcardreport.setDeleted_by("NA");
		wheatstackcardreport.setDeleted_on(ldt);
		
		Set<Wheatstackcardreportdetails> wheatstackcardreportdetails=new HashSet<Wheatstackcardreportdetails>();
		wheatstackcardreportdetails.addAll(wheatstackcardreport.getWheatstackcardreportdetails());
		for(Wheatstackcardreportdetails wrDetails:wheatstackcardreportdetails) 
		{
			wrDetails.setWheatstackid(gen_sno);
			wrDetails.setSavedstatus("1");
			wrDetails.setCompany_id(wheatstackcardreport.getCompany_id());
			wrDetails.setFin_year(wheatstackcardreport.getFin_year());
			wrDetails.setModified_type("INSERTED");
			wrDetails.setInserted_by(wheatstackcardreport.getInserted_by());
			wrDetails.setInserted_on(ldt);
			wrDetails.setUpdated_by("NA");
			wrDetails.setUpdated_on(ldt);
			wrDetails.setDeleted_by("NA");
			wrDetails.setDeleted_on(ldt);
		}
		wheatstackcardreport.setWheatstackcardreportdetails(wheatstackcardreportdetails);
		
		
	   return wheatstackcardreportRepository.save(wheatstackcardreport);	
	}
	@Transactional
	public Wheatstackcardreport update(Wheatstackcardreport wheatstackcardreport,long id) 
	{


		Optional<Wheatstackcardreport> op = wheatstackcardreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		wheatstackcardreport.setWheatstackid(op.get().getWheatstackid());
		wheatstackcardreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(wheatstackcardreport.getBusiness_unit()).getBusinessunit_name());
		wheatstackcardreport.setGodownname(godownMasterRepository.getGodownNameById(wheatstackcardreport.getGodowncode()).getGodownname());
		
		wheatstackcardreport.setCompany_id(wheatstackcardreport.getCompany_id());
		wheatstackcardreport.setFin_year(wheatstackcardreport.getFin_year());
		wheatstackcardreport.setModified_type("INSERTED");
		wheatstackcardreport.setInserted_on(ldt);
		wheatstackcardreport.setInserted_by(userRepository.getUserDetails(wheatstackcardreport.getUsername()).getName());
		wheatstackcardreport.setUpdated_by(wheatstackcardreport.getUpdated_by());
		wheatstackcardreport.setUpdated_on(ldt);
		wheatstackcardreport.setDeleted_by("NA");
		wheatstackcardreport.setDeleted_on(ldt);
		
		
		wheatstackcardreportdetailsRepository.updateprevious(op.get().getWheatstackid());
		
		Set<Wheatstackcardreportdetails> wheatstackcardreportdetails=new HashSet<Wheatstackcardreportdetails>();
		wheatstackcardreportdetails.addAll(wheatstackcardreport.getWheatstackcardreportdetails());
		for(Wheatstackcardreportdetails wrDetails:wheatstackcardreportdetails) 
		{
			wrDetails.setWheatstackid(op.get().getWheatstackid());
			wrDetails.setSavedstatus("1");
			wrDetails.setCompany_id(wheatstackcardreport.getCompany_id());
			wrDetails.setFin_year(wheatstackcardreport.getFin_year());
			wrDetails.setModified_type("INSERTED");
			wrDetails.setInserted_by(wheatstackcardreport.getInserted_by());
			wrDetails.setInserted_on(ldt);
			wrDetails.setUpdated_by("NA");
			wrDetails.setUpdated_on(ldt);
			wrDetails.setDeleted_by("NA");
			wrDetails.setDeleted_on(ldt);
		}
		wheatstackcardreport.setWheatstackcardreportdetails(wheatstackcardreportdetails);
		
		
	   return wheatstackcardreportRepository.save(wheatstackcardreport);	
	}
	@Transactional
	public Wheatstackcardreport delete(Wheatstackcardreport wheatstackcardreport,long id) 
	{
        Optional<Wheatstackcardreport> op = wheatstackcardreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		wheatstackcardreport.setWheatstackid(op.get().getWheatstackid());
		wheatstackcardreport.setCompany_id(wheatstackcardreport.getCompany_id());
		wheatstackcardreport.setFin_year(wheatstackcardreport.getFin_year());
		wheatstackcardreport.setModified_type("DELETED");
		wheatstackcardreport.setInserted_on(ldt);
		wheatstackcardreport.setInserted_by(userRepository.getUserDetails(wheatstackcardreport.getUsername()).getName());
		wheatstackcardreport.setUpdated_by(wheatstackcardreport.getUpdated_by());
		wheatstackcardreport.setUpdated_on(ldt);
		wheatstackcardreport.setDeleted_by("NA");
		wheatstackcardreport.setDeleted_on(ldt);
		
		
		wheatstackcardreportdetailsRepository.deleteprevious(op.get().getWheatstackid());
		
		 return wheatstackcardreportRepository.save(wheatstackcardreport);	
	}
	
	public List<WheatstackcardreportDTO> getwheatstackcardlist(String finyear)
	{
		
		List<Object[]> wheatlist=new ArrayList<Object[]>();
		wheatlist.addAll(wheatstackcardreportRepository.getwheatstack(finyear));
		
		
		 List<WheatstackcardreportDTO> list = new ArrayList<WheatstackcardreportDTO>();     
		
		    for(final Object o : wheatlist)
		    {
		        Object[] obj = (Object[]) o;
		        list.add(new WheatstackcardreportDTO(Long.parseLong(obj[0].toString()),obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString()));
		    }
		    return list;
	}
	
	public Wheatstackcardreport retrievewheatstackcard(long id) 
	{
		Optional<Wheatstackcardreport> op = wheatstackcardreportRepository.findById(id);
		
		return op.get();
	}
	
	public List<Wheatstackcardreportdetails> retrievewheatstackcard_details(String wheatstackid)
	{
		 List<Wheatstackcardreportdetails> details=new ArrayList<Wheatstackcardreportdetails>();
		 details.addAll(wheatstackcardreportdetailsRepository.getwheatstackdetails(wheatstackid));
		 
		 return details;
	}

}
