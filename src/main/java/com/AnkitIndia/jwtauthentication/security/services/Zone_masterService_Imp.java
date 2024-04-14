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
import com.AnkitIndia.jwtauthentication.model.Zone_master;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Zone_masterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Zone_masterService_Imp implements Zone_masterService {
	
	@Autowired
	Zone_masterRepository  zone_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Zone_master save(Zone_master zone_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		String prefix="ZN";
		if(zone_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(zone_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		zone_master.setZone_id(gen_sno);
		zone_master.setModified_type("INSERTED");
		zone_master.setInserted_by(userRepository.getUserDetails(zone_master.getUsername()).getName());
		zone_master.setInserted_on(ldt);
		zone_master.setUpdated_by("NA");
		zone_master.setUpdated_on(ldt);
		zone_master.setDeleted_by("NA");
		zone_master.setDeleted_on(ldt);
		
		return zone_masterRepository.save(zone_master);
	}
	
	@Transactional
	public Zone_master deleteZone(Zone_master zone_master,long id)
	{
		Optional<Zone_master> op = zone_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Zone_master zoneMaster=op.get();

		zoneMaster.setInserted_by(op.get().getInserted_by());
		zoneMaster.setInserted_on(op.get().getInserted_on());
		zoneMaster.setUpdated_by(op.get().getUpdated_by());
		zoneMaster.setUpdated_on(op.get().getUpdated_on());
		zoneMaster.setDeleted_by(userRepository.getUserDetails(zone_master.getUsername()).getName());
		zoneMaster.setDeleted_on(ldt);
		zoneMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			zoneMaster.setId(id);
		}
		return zone_masterRepository.save(zoneMaster);
	}
	
	@Transactional
	public Zone_master updateZone(Zone_master zone_master,long id)
	{
		Optional<Zone_master> op = zone_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		zone_master.setZone_id(op.get().getZone_id());
		zone_master.setModified_type("UPDATED");
		zone_master.setInserted_by(op.get().getInserted_by());
		zone_master.setInserted_on(op.get().getInserted_on());
		zone_master.setUpdated_by(userRepository.getUserDetails(zone_master.getUsername()).getName());
		zone_master.setUpdated_on(ldt);
		zone_master.setDeleted_by("NA");
		zone_master.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			zone_master.setId(id);
		}
		return zone_masterRepository.save(zone_master);
	}
	
	public List<Zone_master> findAll()
	{
		List<Zone_master> ccList=new ArrayList<Zone_master>();
		ccList.addAll(zone_masterRepository.findAll());
				
		List<Zone_master> allData = ccList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Zone_master::getZone_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Zone_master findOne(long id)
	{
		Optional<Zone_master> op=this.zone_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Zone_master> findZone(String searchtext)
	{
		List<Zone_master>zoneList=new ArrayList<Zone_master>();
		zoneList.addAll(zone_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Zone_master> allData = zoneList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Zone_master::getZone_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Zone_master> allData = zoneList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getZone_name()+c.getDescription()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(Zone_master::getZone_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO checkZoneMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false;
		
		if(zone_masterRepository.checkZoneMasterUsage(code))//need to change Query 
		{
			purchase=true;
		}
		if(purchase == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		return result;
	 }
}
