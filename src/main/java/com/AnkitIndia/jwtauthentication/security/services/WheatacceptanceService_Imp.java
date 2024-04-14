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
import com.AnkitIndia.jwtauthentication.model.Wheatacceptance;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WheatacceptanceRepository;

@Service
public class WheatacceptanceService_Imp implements WheatacceptanceService{

	@Autowired
	WheatacceptanceRepository wheatacceptanceRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	
	public List<Wheatacceptance> getWheatAcceptancelist(String currDate,String finyear)
	{
		List<Wheatacceptance> list = new ArrayList<Wheatacceptance>();
		list.addAll(wheatacceptanceRepository.getWheatAcceptancelist(currDate,finyear));
		
		
		return list;
	}
	
	@Transactional
	public Wheatacceptance save(Wheatacceptance wheatacceptance) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wheatacceptanceRepository.countId() != null ) {
			slno=Long.parseLong(wheatacceptanceRepository.countId());
		}
		String prefix="WAR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		wheatacceptance.setAcceptanceid(gen_sno);
		
		if(wheatacceptance.getVehicle_id().compareTo("0") !=0 && wheatacceptance.getVehicle_id().compareTo("") !=0 && wheatacceptance.getVehicle_id() !=null) {
			wheatacceptance.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wheatacceptance.getVehicle_id()).getVehicle_no());
		}else {wheatacceptance.setVehicle_no("None");}
		

		wheatacceptance.setCompany_id(wheatacceptance.getCompany_id());
		wheatacceptance.setFin_year(wheatacceptance.getFin_year());
		wheatacceptance.setModified_type("INSERTED");
		wheatacceptance.setInserted_on(ldt);
		wheatacceptance.setInserted_by(userRepository.getUserDetails(wheatacceptance.getUsername()).getName());
		wheatacceptance.setUpdated_by(wheatacceptance.getUpdated_by());
		wheatacceptance.setUpdated_on(ldt);
		wheatacceptance.setDeleted_by("NA");
		wheatacceptance.setDeleted_on(ldt);
		
		return wheatacceptanceRepository.save(wheatacceptance);	
	}
	
	@Transactional
	public Wheatacceptance update(Wheatacceptance wheatacceptance,long id) 
	{
		Optional<Wheatacceptance> op = wheatacceptanceRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(wheatacceptance.getVehicle_id().compareTo("0") !=0 && wheatacceptance.getVehicle_id().compareTo("") !=0 && wheatacceptance.getVehicle_id() !=null) {
			wheatacceptance.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wheatacceptance.getVehicle_id()).getVehicle_no());
		}else {wheatacceptance.setVehicle_no("None");}
		
		wheatacceptance.setAcceptanceid(op.get().getAcceptanceid());
		wheatacceptance.setCompany_id(wheatacceptance.getCompany_id());
		wheatacceptance.setFin_year(wheatacceptance.getFin_year());
		wheatacceptance.setModified_type("INSERTED");
		wheatacceptance.setInserted_on(ldt);
		wheatacceptance.setInserted_by(userRepository.getUserDetails(wheatacceptance.getUsername()).getName());
		wheatacceptance.setUpdated_by(wheatacceptance.getUpdated_by());
		wheatacceptance.setUpdated_on(ldt);
		wheatacceptance.setDeleted_by("NA");
		wheatacceptance.setDeleted_on(ldt);
		
		return wheatacceptanceRepository.save(wheatacceptance);
	}
	
	@Transactional
	public Wheatacceptance delete(Wheatacceptance wheatacceptance,long id) 
	{

		Optional<Wheatacceptance> op = wheatacceptanceRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Wheatacceptance acceptance=op.get();
		
		acceptance.setModified_type("DELETED");
		acceptance.setInserted_by(op.get().getInserted_by());
		acceptance.setInserted_on(op.get().getInserted_on());
		acceptance.setUpdated_by(op.get().getUpdated_by());
		acceptance.setUpdated_on(op.get().getUpdated_on());
		acceptance.setDeleted_by(userRepository.getUserDetails(acceptance.getUsername()).getName());
		acceptance.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			acceptance.setId(id);
		}
		
			return wheatacceptanceRepository.save(acceptance);
		
	
	}
	

	public List<Wheatacceptance> searchWheatAcceptance(String fromdate, String todate,String finyear)
	{
		List<Wheatacceptance> searchdaily =new ArrayList<Wheatacceptance>();
		String tablename="wheatacceptance",order_date="date";
		searchdaily.addAll(wheatacceptanceRepository.searchWheatAcceptance(tablename,order_date,fromdate,todate,finyear));
		
		List<Wheatacceptance> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Wheatacceptance::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Wheatacceptance findOne(long id) 
	{
		Optional<Wheatacceptance> op=wheatacceptanceRepository.findById(id);
		return op.get();
	}
	
	public List<Wheatacceptance> getWheatAcceptancePrintList(String acceptanceid)
	{
		List<Wheatacceptance> list = new ArrayList<Wheatacceptance>();
		list.addAll(wheatacceptanceRepository.getWheatAcceptancePrintList(acceptanceid));
		
		
		return list;
	}
	
}
