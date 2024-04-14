package com.AnkitIndia.jwtauthentication.security.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;
import com.AnkitIndia.jwtauthentication.model.Powercutreport;

import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.PowercutreportRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.Powercutreport_Pagination_DTO;





@Service
public class PowercutreportService_Imp implements PowercutreportService{
	
	@Autowired
	PowercutreportRepository powercutreportRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public Powercutreport save(Powercutreport powercutreport) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(powercutreportRepository.countId() != null ) {
			slno=Long.parseLong(powercutreportRepository.countId());
		}
		String prefix="PCR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		powercutreport.setPowercutid(gen_sno);
		powercutreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(powercutreport.getBusiness_unit()).getBusinessunit_name());
		
		powercutreport.setCompany_id(powercutreport.getCompany_id());
		powercutreport.setFin_year(powercutreport.getFin_year());
		powercutreport.setModified_type("INSERTED");
		powercutreport.setInserted_on(ldt);
		powercutreport.setInserted_by(userRepository.getUserDetails(powercutreport.getUsername()).getName());
		powercutreport.setUpdated_by(powercutreport.getUpdated_by());
		powercutreport.setUpdated_on(ldt);
		powercutreport.setDeleted_by("NA");
		powercutreport.setDeleted_on(ldt);
		
		return powercutreportRepository.save(powercutreport);
	}
	
	
	public Page<Powercutreport_Pagination_DTO> getpowercutlist(int page,int size)
	{
	
	
	 	PageRequest pageRequest = PageRequest.of(page, size);
	    Page<Powercutreport> pageResult = powercutreportRepository.findAll(pageRequest);
	
	 
	    List<Powercutreport_Pagination_DTO> powercut = pageResult
	    	      .stream()
	    	      .map((Powercutreport powercutreport) -> new Powercutreport_Pagination_DTO(
	    	    		  powercutreport.getId(),
	    	    		  powercutreport.getPowercutid(),
	    	    		  powercutreport.getBusiness_unit(),
	    	    		  powercutreport.getBusiness_unitname(),
	    	    		  powercutreport.getPowercutdate(),
	    	    		  powercutreport.getPowercuttime(),
	    	    		  powercutreport.getPowerondate(),
	    	    		  powercutreport.getPowerontime(),
	    	    		  powercutreport.getDiffpower(),
	    	    		  powercutreport.getRemarks(),
	    	    		  powercutreport.getModified_type())).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
	    	      
	   for(int i=0;i<powercut.size();i++) 
	   {
		   System.out.println("new "+powercut.get(i));
	   }
	    return new PageImpl<>(powercut, pageRequest, pageResult.getTotalElements());
  
	}
	
	
	
	/*public List<Powercutreport_Pagination_DTO> searchpowercut(String fromdate, String todate,String finyear)
	{
		List<Powercutreport> searchpowercutr =new ArrayList<Powercutreport>();
		String tablename="powercutreport",order_date="powercutdate";
		searchpowercutr.addAll(powercutreportRepository.getsearchdatapower(tablename,order_date,fromdate,todate,finyear));
		//System.out.println("power data:"+searchpowercutr.get(0)+"//"+searchpowercutr.size());
		List<Powercutreport_Pagination_DTO> paginationlist = new ArrayList<Powercutreport_Pagination_DTO>();
		for(int i=0;i<searchpowercutr.size();i++) 
		{
			//System.out.println("searchpowercutr.get(i).getId() "+searchpowercutr.get(0));
			Powercutreport_Pagination_DTO power= new Powercutreport_Pagination_DTO(searchpowercutr.get(i).getId(),
					searchpowercutr.get(i).getPowercutid(),
					searchpowercutr.get(i).getBusiness_unit(),
					searchpowercutr.get(i).getPowercutdate(),
					searchpowercutr.get(i).getPowercuttime(),
					searchpowercutr.get(i).getPowerondate(),
					searchpowercutr.get(i).getPowerontime(),
					searchpowercutr.get(i).getDiffpower(),
					searchpowercutr.get(i).getRemarks(),
					searchpowercutr.get(i).getBusiness_unitname(),
					searchpowercutr.get(i).getModified_type()
					);
			paginationlist.add(power);
		}
		
		List<Powercutreport_Pagination_DTO> allData = paginationlist
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Powercutreport_Pagination_DTO::getPowercutdate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}*/
	
	public List<Powercutreport> searchpowercut(String fromdate, String todate,String finyear)
	{
		List<Powercutreport> searchpower =new ArrayList<Powercutreport>();
		String tablename="powercutreport",order_date="powercutdate";
		searchpower.addAll(powercutreportRepository.getsearchdatapower(tablename,order_date,fromdate,todate,finyear));
		
		List<Powercutreport> allData = searchpower
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Powercutreport::getPowercutdate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public List<Powercutreport> getpowercutDatalist(String currDate,String finyear)
	{
		List<Powercutreport> modelList=new ArrayList<Powercutreport>();
		//String tablename="powercutreport",order_date="powercutdate";
		modelList.addAll(powercutreportRepository.getPowerlist(currDate,finyear));
		
		return modelList;
	}

	public Powercutreport findOne(long id)
	{
		Optional<Powercutreport> op=powercutreportRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Powercutreport update(Powercutreport powercutreport,long id)
	{
		Optional<Powercutreport> op =powercutreportRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println("update:"+op.get().getPowercutid());
		powercutreport.setPowercutid(op.get().getPowercutid());
		powercutreport.setCompany_id(powercutreport.getCompany_id());
		powercutreport.setFin_year(powercutreport.getFin_year());
		powercutreport.setModified_type("INSERTED");
		powercutreport.setInserted_on(ldt);
		powercutreport.setInserted_by(userRepository.getUserDetails(powercutreport.getUsername()).getName());
		powercutreport.setUpdated_by(powercutreport.getUpdated_by());
		powercutreport.setUpdated_on(ldt);
		powercutreport.setDeleted_by("NA");
		powercutreport.setDeleted_on(ldt);
		powercutreport.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(powercutreport.getBusiness_unit()).getBusinessunit_name());
		
		return powercutreportRepository.save(powercutreport);
	}
	
	@Transactional
	public Powercutreport deletePowerCutReport(Powercutreport powercutreport,long id)
	{
		Optional<Powercutreport> op = powercutreportRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Powercutreport powercut=op.get();
		
		powercut.setInserted_by(op.get().getInserted_by());
		powercut.setInserted_on(op.get().getInserted_on());
		powercut.setUpdated_by(op.get().getUpdated_by());
		powercut.setUpdated_on(op.get().getUpdated_on());
		powercut.setDeleted_by(userRepository.getUserDetails(powercut.getUsername()).getName());
		powercut.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			powercut.setId(id);
		}
		powercut.setModified_type("DELETED");
			return powercutreportRepository.save(powercut);
		
	}
}
