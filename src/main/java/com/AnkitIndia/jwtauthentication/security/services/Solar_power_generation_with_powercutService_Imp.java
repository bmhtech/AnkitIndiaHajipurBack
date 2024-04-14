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
import com.AnkitIndia.jwtauthentication.model.Solar_power_generation_with_powercut;
import com.AnkitIndia.jwtauthentication.model.Solar_power_generation_with_powercut_dtls;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailyweigherRepository;
import com.AnkitIndia.jwtauthentication.repository.Solar_power_generation_with_powercutRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Solar_power_generation_with_powercutService_Imp implements Solar_power_generation_with_powercutService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Solar_power_generation_with_powercutRepository solar_power_generation_with_powercutRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public List<Map<String,Object>> solarPowerGenerationWithPowerCutList(String compid)
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(solar_power_generation_with_powercutRepository.solarPowerGenerationWithPowerCutList(compid));
		return list;
	}
	
	@Transactional
	public Solar_power_generation_with_powercut save(Solar_power_generation_with_powercut spgp) {
		    LocalDateTime ldt = LocalDateTime.now();
		    int slno=0;
		    String sno=solar_power_generation_with_powercutRepository.countId();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("SPC",slno);
			
			spgp.setSolar_powercut_id(gen_sno);
			
			if(Utility.isNullOrEmpty(spgp.getB_unit())) {
				spgp.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(spgp.getB_unit()).getBusinessunit_name());
			}else{spgp.setB_unit_name("NA");};
			
			spgp.setModified_type("INSERTED");
			spgp.setInserted_by(userRepository.getUserDetails(spgp.getUsername()).getName());
			spgp.setInserted_on(ldt);
			spgp.setUpdated_by("NA");
			spgp.setUpdated_on(ldt);
			spgp.setDeleted_by("NA");
			spgp.setDeleted_on(ldt);
		
			Set<Solar_power_generation_with_powercut_dtls> power_Dtls=new HashSet<Solar_power_generation_with_powercut_dtls>();
			power_Dtls.addAll(spgp.getSolar_power_generation_with_powercut_dtls());
			for(Solar_power_generation_with_powercut_dtls spgcd:power_Dtls) 
			{
				spgcd.setSolar_powercut_id(gen_sno);
				
				spgcd.setCompany_id(spgp.getCompany_id());
				spgcd.setFin_year(spgp.getFin_year());
				spgcd.setModified_type("INSERTED");
				spgcd.setInserted_by(spgp.getInserted_by());
				spgcd.setInserted_on(ldt);
				spgcd.setUpdated_by("NA");
				spgcd.setUpdated_on(ldt);
				spgcd.setDeleted_by("NA");
				spgcd.setDeleted_on(ldt);
				spgcd.setUsername(spgp.getUsername());
			}
			spgp.setSolar_power_generation_with_powercut_dtls(power_Dtls);
			
		return solar_power_generation_with_powercutRepository.save(spgp);
	}
	

	public Solar_power_generation_with_powercut findOne(long id)
	{
		Optional<Solar_power_generation_with_powercut> op=this.solar_power_generation_with_powercutRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> retriveSolarPowercutDetails(String powerid)
	{
		List<Map<String, Object>> dwdtls=new ArrayList<Map<String, Object>>();
		dwdtls.addAll(solar_power_generation_with_powercutRepository.retriveSolarPowercutDetails(powerid));
		return dwdtls;
	}
	
	@Transactional
	public Solar_power_generation_with_powercut update(Solar_power_generation_with_powercut spgp,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Solar_power_generation_with_powercut> op = solar_power_generation_with_powercutRepository.findById(id);
		
		if(Utility.isNullOrEmpty(spgp.getB_unit())) {
			spgp.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(spgp.getB_unit()).getBusinessunit_name());
		}else{spgp.setB_unit_name("NA");};
		
		spgp.setSolar_powercut_id(op.get().getSolar_powercut_id());
		spgp.setModified_type("INSERTED");
		spgp.setInserted_by(op.get().getInserted_by());
		spgp.setInserted_on(op.get().getInserted_on());
		spgp.setUpdated_by(userRepository.getUserDetails(spgp.getUsername()).getName());
		spgp.setUpdated_on(ldt);
		spgp.setDeleted_by(op.get().getDeleted_by());
		spgp.setDeleted_on(op.get().getDeleted_on());
		
		//Dynamic
		solar_power_generation_with_powercutRepository.updateSolarPwrCutDtls(spgp.getSolar_powercut_id());
		
		Set<Solar_power_generation_with_powercut_dtls> power_Dtls=new HashSet<Solar_power_generation_with_powercut_dtls>();
		power_Dtls.addAll(spgp.getSolar_power_generation_with_powercut_dtls());
		for(Solar_power_generation_with_powercut_dtls spgpd:power_Dtls) 
		{
			spgpd.setSolar_powercut_id(op.get().getSolar_powercut_id());
			
			spgpd.setCompany_id(spgp.getCompany_id());
			spgpd.setFin_year(spgp.getFin_year());
			spgpd.setUsername(spgp.getUsername());
			spgpd.setModified_type("INSERTED");
			spgpd.setInserted_by(spgp.getInserted_by());
			spgpd.setInserted_on(spgp.getInserted_on());
			spgpd.setUpdated_by(spgp.getUpdated_by());
			spgpd.setUpdated_on(spgp.getUpdated_on());
			spgpd.setDeleted_by(spgp.getDeleted_by());
			spgpd.setDeleted_on(spgp.getDeleted_on());
		}
	
		spgp.setSolar_power_generation_with_powercut_dtls(power_Dtls);
		
		if(op.isPresent())
		{
			spgp.setId(id);
		}
		return solar_power_generation_with_powercutRepository.save(spgp);
	}
	
	@Transactional
	public Solar_power_generation_with_powercut delete(Solar_power_generation_with_powercut powercut,long id) 
	{

		Optional<Solar_power_generation_with_powercut> op = solar_power_generation_with_powercutRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Solar_power_generation_with_powercut spgpd=op.get();
		
		spgpd.setModified_type("DELETED");
		spgpd.setInserted_by(op.get().getInserted_by());
		spgpd.setInserted_on(op.get().getInserted_on());
		spgpd.setUpdated_by(op.get().getUpdated_by());
		spgpd.setUpdated_on(op.get().getUpdated_on());
		spgpd.setDeleted_by(userRepository.getUserDetails(spgpd.getUsername()).getName());
		spgpd.setDeleted_on(ldt);
		
		solar_power_generation_with_powercutRepository.deleteSolarPowerCut(op.get().getSolar_powercut_id());
		
		
		if(op.isPresent())
		{
			spgpd.setId(id);
		}
		
			return solar_power_generation_with_powercutRepository.save(spgpd);
		
	}
	
	public List<Map<String, Object>> getSolarPowerWithPowerCutList(String bunit,String fromdate,String todate)
	{
		System.out.println("bunit::"+bunit+"//"+fromdate+"//"+todate);
		List<Map<String, Object>> dwdtls=new ArrayList<Map<String, Object>>();
		dwdtls.addAll(solar_power_generation_with_powercutRepository.getSolarPowerWithPowerCutList(bunit,fromdate,todate));
		return dwdtls;
	}
	
	public Status_table checkSolarPowerCut(String solardate) 
	{
		Status_table res=new Status_table();
		int count=solar_power_generation_with_powercutRepository.countdate(solardate);
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
