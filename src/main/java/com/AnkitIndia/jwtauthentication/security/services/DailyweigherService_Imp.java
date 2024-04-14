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
import com.AnkitIndia.jwtauthentication.model.Seives_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailyweigherRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
@Service
public class DailyweigherService_Imp implements DailyweigherService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DailyweigherRepository dailyweigherRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	Item_masterRepository item_masterRepository;
	
	public List<Map<String,Object>> getDailyweigherList()
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(dailyweigherRepository.getDailyweigherList());
		return list;
	}
	
	@Transactional
	public Dailyweigher save(Dailyweigher dailyweigher) {
		    LocalDateTime ldt = LocalDateTime.now();
		    int slno=0;
		    String sno=dailyweigherRepository.countId();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("DWR",slno);
			
			dailyweigher.setDwg_id(gen_sno);
			
			if(Utility.isNullOrEmpty(dailyweigher.getB_unit())) {
				dailyweigher.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(dailyweigher.getB_unit()).getBusinessunit_name());
			}else{dailyweigher.setB_unit_name("NA");};
			
			dailyweigher.setModified_type("INSERTED");
			dailyweigher.setInserted_by(userRepository.getUserDetails(dailyweigher.getUsername()).getName());
			dailyweigher.setInserted_on(ldt);
			dailyweigher.setUpdated_by("NA");
			dailyweigher.setUpdated_on(ldt);
			dailyweigher.setDeleted_by("NA");
			dailyweigher.setDeleted_on(ldt);
		
			Set<Dailyweigher_Dtls> dailyweigher_Dtls=new HashSet<Dailyweigher_Dtls>();
			dailyweigher_Dtls.addAll(dailyweigher.getDailyweigher_Dtls());
			for(Dailyweigher_Dtls dwd:dailyweigher_Dtls) 
			{
				dwd.setDwg_id(gen_sno);
				
				if(Utility.isNullOrEmpty(dwd.getItem_code())) {
					dwd.setItem_name(item_masterRepository.getItemDetailsThruItemId(dwd.getItem_code()).getItem_name());
				}
				else
				{
					dwd.setItem_name("NA");
				};
				
				
				if(Utility.isNullOrEmpty(dwd.getPacking_item())) {
					dwd.setPacking_item_name(item_masterRepository.getItemDetailsThruItemId(dwd.getPacking_item()).getItem_name());
				}else{dwd.setPacking_item_name("NA");};
				
				dwd.setCompany_id(dailyweigher.getCompany_id());
				dwd.setFin_year(dailyweigher.getFin_year());
				dwd.setModified_type("INSERTED");
				dwd.setInserted_by(dailyweigher.getInserted_by());
				dwd.setInserted_on(ldt);
				dwd.setUpdated_by("NA");
				dwd.setUpdated_on(ldt);
				dwd.setDeleted_by("NA");
				dwd.setDeleted_on(ldt);
				dwd.setUsername(dailyweigher.getUsername());
			}
			dailyweigher.setDailyweigher_Dtls(dailyweigher_Dtls);
			
		return dailyweigherRepository.save(dailyweigher);
	}
	

	public Dailyweigher findOne(long id)
	{
		Optional<Dailyweigher> op=this.dailyweigherRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> retriveDailyweigherDetails(String dwg_id)
	{
		List<Map<String, Object>> dwdtls=new ArrayList<Map<String, Object>>();
		dwdtls.addAll(dailyweigherRepository.retriveDailyweigherDetails(dwg_id));
		return dwdtls;
	}
	
	@Transactional
	public Dailyweigher update(Dailyweigher dailyweigher,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Dailyweigher> op = dailyweigherRepository.findById(id);
		
		if(Utility.isNullOrEmpty(dailyweigher.getB_unit())) {
			dailyweigher.setB_unit_name(companyBusinessUnitMasterRepository.businessunitbyid(dailyweigher.getB_unit()).getBusinessunit_name());
		}else{dailyweigher.setB_unit_name("NA");};
		
		dailyweigher.setDwg_id(op.get().getDwg_id());
		dailyweigher.setModified_type("INSERTED");
		dailyweigher.setInserted_by(op.get().getInserted_by());
		dailyweigher.setInserted_on(op.get().getInserted_on());
		dailyweigher.setUpdated_by(userRepository.getUserDetails(dailyweigher.getUsername()).getName());
		dailyweigher.setUpdated_on(ldt);
		dailyweigher.setDeleted_by(op.get().getDeleted_by());
		dailyweigher.setDeleted_on(op.get().getDeleted_on());
		
		//Dynamic
		dailyweigherRepository.updateDailyweigherDtls(dailyweigher.getDwg_id());
		
		Set<Dailyweigher_Dtls> dailyweigher_Dtls=new HashSet<Dailyweigher_Dtls>();
		dailyweigher_Dtls.addAll(dailyweigher.getDailyweigher_Dtls());
		for(Dailyweigher_Dtls dwd:dailyweigher_Dtls) 
		{
			dwd.setDwg_id(op.get().getDwg_id());
			
			if(Utility.isNullOrEmpty(dwd.getItem_code())) {
				dwd.setItem_name(item_masterRepository.getItemDetailsThruItemId(dwd.getItem_code()).getItem_name());
			}
			else
			{
				dwd.setItem_name("NA");
			};
			
			
			if(Utility.isNullOrEmpty(dwd.getPacking_item())) {
				dwd.setPacking_item_name(item_masterRepository.getItemDetailsThruItemId(dwd.getPacking_item()).getItem_name());
			}else{dwd.setPacking_item_name("NA");};
			dwd.setCompany_id(dailyweigher.getCompany_id());
			dwd.setFin_year(dailyweigher.getFin_year());
			dwd.setUsername(dailyweigher.getUsername());
			dwd.setModified_type("INSERTED");
			dwd.setInserted_by(dailyweigher.getInserted_by());
			dwd.setInserted_on(dailyweigher.getInserted_on());
			dwd.setUpdated_by(dailyweigher.getUpdated_by());
			dwd.setUpdated_on(dailyweigher.getUpdated_on());
			dwd.setDeleted_by(dailyweigher.getDeleted_by());
			dwd.setDeleted_on(dailyweigher.getDeleted_on());
		}
	
		dailyweigher.setDailyweigher_Dtls(dailyweigher_Dtls);
		
		if(op.isPresent())
		{
			dailyweigher.setId(id);
		}
		return dailyweigherRepository.save(dailyweigher);
	}
	
	@Transactional
	public Dailyweigher delete(Dailyweigher dailyweigher,long id) 
	{

		Optional<Dailyweigher> op = dailyweigherRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Dailyweigher dwg=op.get();
		
		dwg.setModified_type("DELETED");
		dwg.setInserted_by(op.get().getInserted_by());
		dwg.setInserted_on(op.get().getInserted_on());
		dwg.setUpdated_by(op.get().getUpdated_by());
		dwg.setUpdated_on(op.get().getUpdated_on());
		dwg.setDeleted_by(userRepository.getUserDetails(dwg.getUsername()).getName());
		dwg.setDeleted_on(ldt);
		
		dailyweigherRepository.deleteDailyweigherDtls(op.get().getDwg_id());
		
		
		if(op.isPresent())
		{
			dwg.setId(id);
		}
		
			return dailyweigherRepository.save(dwg);
		
	}
	
	public List<Map<String,Object>> getDailyWeigherReport(String wdate,String machine)
	{
		return dailyweigherRepository.DailyWeigherReport(wdate,machine);
	}
}
