package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Dailyweigher;
import com.AnkitIndia.jwtauthentication.model.Dailyweigher_Dtls;
import com.AnkitIndia.jwtauthentication.model.Solar_power_generation;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailyweigherRepository;
import com.AnkitIndia.jwtauthentication.repository.Solar_power_generationRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Solar_power_generationService_Imp implements Solar_power_generationService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Solar_power_generationRepository solar_power_generationRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<Map<String,Object>> getSolarPowerGeneration(String compid)
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(solar_power_generationRepository.getSolarPowerGeneration(compid));
		return list;
	}
	
	@Transactional
	public Solar_power_generation save(Solar_power_generation spg) {
		    LocalDateTime ldt = LocalDateTime.now();
		    int slno=0;
		    String sno=solar_power_generationRepository.countId();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("SPG",slno);
			
			spg.setSolar_power_id(gen_sno);
			
			if(Utility.isNullOrEmpty(spg.getB_unit())) {
				spg.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(spg.getB_unit()).getBusinessunit_name());
			}else{spg.setB_unit_name("NA");};
			
			spg.setModified_type("INSERTED");
			spg.setInserted_by(userRepository.getUserDetails(spg.getUsername()).getName());
			spg.setInserted_on(ldt);
			spg.setUpdated_by("NA");
			spg.setUpdated_on(ldt);
			spg.setDeleted_by("NA");
			spg.setDeleted_on(ldt);
		
		return solar_power_generationRepository.save(spg);
	}
	

	public Solar_power_generation findOne(long id)
	{
		Optional<Solar_power_generation> op=this.solar_power_generationRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Solar_power_generation update(Solar_power_generation spg,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Solar_power_generation> op = solar_power_generationRepository.findById(id);
		
		if(Utility.isNullOrEmpty(spg.getB_unit())) {
			spg.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(spg.getB_unit()).getBusinessunit_name());
		}else{spg.setB_unit_name("NA");};
		
		spg.setSolar_power_id(op.get().getSolar_power_id());
		spg.setModified_type("INSERTED");
		spg.setInserted_by(op.get().getInserted_by());
		spg.setInserted_on(op.get().getInserted_on());
		spg.setUpdated_by(userRepository.getUserDetails(spg.getUsername()).getName());
		spg.setUpdated_on(ldt);
		spg.setDeleted_by(op.get().getDeleted_by());
		spg.setDeleted_on(op.get().getDeleted_on());
		
		
		if(op.isPresent())
		{
			spg.setId(id);
		}
		return solar_power_generationRepository.save(spg);
	}
	
	@Transactional
	public Solar_power_generation delete(Solar_power_generation solar_power_generation,long id) 
	{
		Optional<Solar_power_generation> op = solar_power_generationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Solar_power_generation spg=op.get();
		
		spg.setModified_type("DELETED");
		spg.setInserted_by(op.get().getInserted_by());
		spg.setInserted_on(op.get().getInserted_on());
		spg.setUpdated_by(op.get().getUpdated_by());
		spg.setUpdated_on(op.get().getUpdated_on());
		spg.setDeleted_by(userRepository.getUserDetails(spg.getUsername()).getName());
		spg.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			spg.setId(id);
		}
		
			return solar_power_generationRepository.save(spg);
	}
	
	public List<Map<String, Object>> getSolarPorGenReport(String bunit,String fromdate,String todate)
	{
		List<Map<String, Object>> solar=new ArrayList<Map<String, Object>>();
		solar.addAll(solar_power_generationRepository.getSolarPorGenReport(bunit,fromdate,todate));
		return solar;
	}
	
	public List<Map<String, Object>> getInverterSolarPowerGeneration(String bunit,String fromdate,String todate)
	{
		List<Map<String, Object>> solar=new ArrayList<Map<String, Object>>();
		solar.addAll(solar_power_generationRepository.getInverterSolarPowerGeneration(bunit,fromdate,todate));
		return solar;
	}
	
	public List<Map<String, Object>> getAllSolarData(String solar_date)
	{
		List<Map<String, Object>> solar=new ArrayList<Map<String, Object>>();
		solar.addAll(solar_power_generationRepository.getAllSolarData(solar_date));
		return solar;
	}
	
	public Status_table checkSolarPowerDate(String solardate) 
	{
		Status_table res=new Status_table();
		int count=solar_power_generationRepository.countdate(solardate);
		System.out.println("count :: " + count);
		if(count>0) 
		{
			res.setStatus("YES");
		}
		else 
		{
			res.setStatus("NO");
		}
		return res;
	}
	
	
}
