package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Termasservice;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.TermasserviceRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class TermasserviceService_Imp implements TermasserviceService
{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	TermasserviceRepository termasserviceRepository;
	
	public SequenceIdDTO getTSequenceId(String fin_year) 
	{
			int slno=0;
			String sno=termasserviceRepository.counttermasservice();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="TAS-"+final_fyear+"-";
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			
			Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
			
			SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
			
			genCode.setSequenceid(gen_sno);
			
			return genCode;
	}
	
	@Transactional
	public Termasservice save(Termasservice termasservice)
	{
			LocalDateTime ldt = LocalDateTime.now();
			
			 int slno=0;
			 
			    String sno=termasserviceRepository.counttermasservice();
				
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				
				String fin_yearspit[]=termasservice.getFin_year().split("-");
				String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
				
				String prefix="ECM-"+final_fyear+"-";
				String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
				termasservice.setTermasserviceno(gen_sno);
				
				termasservice.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(termasservice.getBusiness_unit()).getBusinessunit_name());
				termasservice.setTermasservicename(termasservice.getTermasservicename());
				termasservice.setModified_type("INSERTED");
				termasservice.setInserted_by(userRepository.getUserDetails(termasservice.getUsername()).getName());
				termasservice.setInserted_on(ldt);
				termasservice.setUpdated_by("NA");
				termasservice.setUpdated_on(ldt);
				termasservice.setDeleted_by("NA");
				termasservice.setDeleted_on(ldt);
			
			return termasserviceRepository.save(termasservice);
	}
	
	public List<Map<String, Object>> getTermasServiceList(String fin_year){
		List<Map<String, Object>> TermasServiceList = new ArrayList<Map<String, Object>>();
		TermasServiceList.addAll(termasserviceRepository.getTermasService(fin_year));
		return TermasServiceList;
	}
	
	public Termasservice findOne(long id) 
	{
		Optional<Termasservice> data = this.termasserviceRepository.findById(id);
		return data.get();
	}
	
	@Transactional
	public Termasservice update(Termasservice termasservice, long id) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Termasservice> op = termasserviceRepository.findById(id);
		
		termasservice.setTermasserviceno(op.get().getTermasserviceno());
		termasservice.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(termasservice.getBusiness_unit()).getBusinessunit_name());
		termasservice.setTermasservicename(termasservice.getTermasservicename());
		termasservice.setModified_type("INSERTED");
		termasservice.setInserted_by(op.get().getInserted_by());
		termasservice.setInserted_on(op.get().getInserted_on());
		termasservice.setUpdated_by(userRepository.getUserDetails(termasservice.getUsername()).getName());
		termasservice.setUpdated_on(ldt);
		termasservice.setDeleted_by("NA");
		termasservice.setDeleted_on(op.get().getDeleted_on());
		
		return termasserviceRepository.save(termasservice);
	}
	
	@Transactional
	public Termasservice delete(Termasservice termasservice, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Termasservice> op = termasserviceRepository.findById(id);
		
		Termasservice tas=op.get();
		
		tas.setModified_type("DELETED");
		tas.setInserted_by(op.get().getInserted_by());
		tas.setInserted_on(op.get().getInserted_on());
		tas.setUpdated_by(op.get().getUpdated_by());
		tas.setUpdated_on(op.get().getUpdated_on());
		tas.setDeleted_by(userRepository.getUserDetails(termasservice.getUsername()).getName());
		tas.setDeleted_on(ldt);
		
		return termasserviceRepository.save(tas);
	}
	
	public List<Map<String, Object>> getTermAsService(){
		List<Map<String, Object>> getTermAsService = new ArrayList<Map<String, Object>>();
		getTermAsService.addAll(termasserviceRepository.getTermAsServiceList());
		return getTermAsService;
	}
}
