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
import com.AnkitIndia.jwtauthentication.model.Exitclausemaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ExitclausemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class ExitclausemasterService_Imp implements ExitclausemasterService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	ExitclausemasterRepository exitclausemasterRepository;
	
	public SequenceIdDTO getESequenceId(String fin_year) 
	{
		int slno=0;
		String sno=exitclausemasterRepository.countexitclause();
		
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
		
		String prefix="ECM-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Exitclausemaster save(Exitclausemaster exitclausemaster) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=exitclausemasterRepository.countexitclause();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=exitclausemaster.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="ECM-"+final_fyear+"-";
			String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
			exitclausemaster.setExitclauseno(gen_sno);
			
			exitclausemaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(exitclausemaster.getBusiness_unit()).getBusinessunit_name());
			exitclausemaster.setExitclausename(exitclausemaster.getExitclausename());
			exitclausemaster.setModified_type("INSERTED");
			exitclausemaster.setInserted_by(userRepository.getUserDetails(exitclausemaster.getUsername()).getName());
			exitclausemaster.setInserted_on(ldt);
			exitclausemaster.setUpdated_by("NA");
			exitclausemaster.setUpdated_on(ldt);
			exitclausemaster.setDeleted_by("NA");
			exitclausemaster.setDeleted_on(ldt);
		
		return exitclausemasterRepository.save(exitclausemaster);
	}
	
	public List<Map<String, Object>> getExitClauseMasterList(String fin_year){
		List<Map<String, Object>> ExitClauseMasterList = new ArrayList<Map<String, Object>>();
		ExitClauseMasterList.addAll(exitclausemasterRepository.getExitClauseMaster(fin_year));
		return ExitClauseMasterList;
	}
	
	public Exitclausemaster findOne(long id)
	{
		Optional<Exitclausemaster> data = this.exitclausemasterRepository.findById(id);
		return data.get();
	}
	
	@Transactional
	public Exitclausemaster update(Exitclausemaster exitclausemaster, long id) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Exitclausemaster> op = exitclausemasterRepository.findById(id);
		
		exitclausemaster.setExitclauseno(op.get().getExitclauseno());
		exitclausemaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(exitclausemaster.getBusiness_unit()).getBusinessunit_name());
		exitclausemaster.setExitclausename(exitclausemaster.getExitclausename());
		exitclausemaster.setModified_type("INSERTED");
		exitclausemaster.setInserted_by(op.get().getInserted_by());
		exitclausemaster.setInserted_on(op.get().getInserted_on());
		exitclausemaster.setUpdated_by(userRepository.getUserDetails(exitclausemaster.getUsername()).getName());
		exitclausemaster.setUpdated_on(ldt);
		exitclausemaster.setDeleted_by("NA");
		exitclausemaster.setDeleted_on(op.get().getDeleted_on());
		
		return exitclausemasterRepository.save(exitclausemaster);
	}
	
	@Transactional
	public Exitclausemaster delete(Exitclausemaster exitclausemaster, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Exitclausemaster> op = exitclausemasterRepository.findById(id);
		
		Exitclausemaster ecm=op.get();
		
		ecm.setModified_type("DELETED");
		ecm.setInserted_by(op.get().getInserted_by());
		ecm.setInserted_on(op.get().getInserted_on());
		ecm.setUpdated_by(op.get().getUpdated_by());
		ecm.setUpdated_on(op.get().getUpdated_on());
		ecm.setDeleted_by(userRepository.getUserDetails(exitclausemaster.getUsername()).getName());
		ecm.setDeleted_on(ldt);
		
		return exitclausemasterRepository.save(ecm);
	}
	
}
