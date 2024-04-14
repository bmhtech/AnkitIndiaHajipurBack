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

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Gatepassregister;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GatepassregisterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class GatepassregisterService_Imp implements GatepassregisterService{

	@Autowired
	GatepassregisterRepository gatepassregisterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	public List<Gatepassregister> getGatepassRegisterList(String currDate,String finyear)
	{
		List<Gatepassregister> daliylist = new ArrayList<Gatepassregister>();
		daliylist.addAll(gatepassregisterRepository.getGatepassRegisterList(currDate,finyear));
		
		return daliylist;
	}
	
	@Transactional
	public Gatepassregister save(Gatepassregister gatepassregister) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gatepassregisterRepository.countId() != null ) {
			slno=Long.parseLong(gatepassregisterRepository.countId());
		}
		String prefix="GPR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		gatepassregister.setGatepassregisterid(gen_sno);
		
		if(Utility.isNullOrEmpty(gatepassregister.getApprovedby()))
		{
			gatepassregister.setApprovedby_name(employeeMasterRepository.getEmployee(gatepassregister.getApprovedby()).getEmp_name());
		}
		else 
		{
			gatepassregister.setApprovedby_name("");
		}

		gatepassregister.setCompany_id(gatepassregister.getCompany_id());
		gatepassregister.setFin_year(gatepassregister.getFin_year());
		gatepassregister.setModified_type("INSERTED");
		gatepassregister.setInserted_on(ldt);
		gatepassregister.setInserted_by(userRepository.getUserDetails(gatepassregister.getUsername()).getName());
		gatepassregister.setUpdated_by(gatepassregister.getUpdated_by());
		gatepassregister.setUpdated_on(ldt);
		gatepassregister.setDeleted_by("NA");
		gatepassregister.setDeleted_on(ldt);
		
		
		return gatepassregisterRepository.save(gatepassregister);	
	}
	
	@Transactional
	public Gatepassregister update(Gatepassregister gatepassregister,long id) 
	{

		Optional<Gatepassregister> op =gatepassregisterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(gatepassregister.getApprovedby()))
		{
			gatepassregister.setApprovedby_name(employeeMasterRepository.getEmployee(gatepassregister.getApprovedby()).getEmp_name());
		}
		else 
		{
			gatepassregister.setApprovedby_name("");
		}
		gatepassregister.setGatepassregisterid(op.get().getGatepassregisterid());
		gatepassregister.setCompany_id(gatepassregister.getCompany_id());
		gatepassregister.setFin_year(gatepassregister.getFin_year());
		gatepassregister.setModified_type("INSERTED");
		gatepassregister.setInserted_on(ldt);
		gatepassregister.setInserted_by(userRepository.getUserDetails(gatepassregister.getUsername()).getName());
		gatepassregister.setUpdated_by(gatepassregister.getUpdated_by());
		gatepassregister.setUpdated_on(ldt);
		gatepassregister.setDeleted_by("NA");
		gatepassregister.setDeleted_on(ldt);
		
	
		
		return gatepassregisterRepository.save(gatepassregister);
	
	}
	
	@Transactional
	public Gatepassregister delete(Gatepassregister gatepassregister,long id) 
	{

		Optional<Gatepassregister> op = gatepassregisterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Gatepassregister gatepass=op.get();
		
		gatepass.setModified_type("DELETED");
		gatepass.setInserted_by(op.get().getInserted_by());
		gatepass.setInserted_on(op.get().getInserted_on());
		gatepass.setUpdated_by(op.get().getUpdated_by());
		gatepass.setUpdated_on(op.get().getUpdated_on());
		gatepass.setDeleted_by(userRepository.getUserDetails(gatepass.getUsername()).getName());
		gatepass.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			gatepass.setId(id);
		}
		
			return gatepassregisterRepository.save(gatepass);
		
	
	}
	
	public List<Gatepassregister> searchgatePassRegister(String fromdate, String todate,String finyear)
	{
		List<Gatepassregister> searchdaily =new ArrayList<Gatepassregister>();
		String tablename="gatepassregister",order_date="gp_date";
		searchdaily.addAll(gatepassregisterRepository.searchgatePassRegister(tablename,order_date,fromdate,todate,finyear));
		
		List<Gatepassregister> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Gatepassregister::getGp_date))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Gatepassregister findOne(long id) 
	{
		Optional<Gatepassregister> op=gatepassregisterRepository.findById(id);
		return op.get();
	}
}
