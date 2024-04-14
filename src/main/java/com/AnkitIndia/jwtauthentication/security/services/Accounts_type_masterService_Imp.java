package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;
import com.AnkitIndia.jwtauthentication.repository.Accounts_category_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Accounts_type_masterService_Imp implements Accounts_type_masterService
{
	@Autowired
	Accounts_type_masterRepository accounts_type_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Accounts_group_masterRepository accounts_group_masterRepository;
	
	@Autowired
	Accounts_category_masterRepository accounts_category_masterRepository;
	
	public SequenceIdDTO getATSequenceId(String fin_year) 
	{
		int slno=0;
		String sno=accounts_type_masterRepository.countId();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="AMT-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Accounts_type_master saveAccountsType(Accounts_type_master accounts_type_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(accounts_type_masterRepository.countId() != null ) {
			slno=Long.parseLong(accounts_type_masterRepository.countId());
		}
		String prefix="AMT";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		accounts_type_master.setAccts_id(gen_sno);
		accounts_type_master.setModified_type("INSERTED");
		accounts_type_master.setInserted_by(userRepository.getUserDetails(accounts_type_master.getUsername()).getName());
		accounts_type_master.setInserted_on(ldt);
		accounts_type_master.setUpdated_by("NA");
		accounts_type_master.setUpdated_on(ldt);
		accounts_type_master.setDeleted_by("NA");
		accounts_type_master.setDeleted_on(ldt);
		
		return  accounts_type_masterRepository.save(accounts_type_master);
	}
	
	public List<Map<String, Object>> getAccountsTypeList()
	{
	
		return accounts_type_masterRepository.getAccountsTypeList();
	}
	
	public Accounts_type_master findOne(long id) 
	{
		Optional<Accounts_type_master> op=this.accounts_type_masterRepository.findById(id);
		return op.get();
	}
	
	public StatusDTO checkAccTypeUsage(String code)
	 {
		StatusDTO result = new StatusDTO();
		boolean catagory=false;
		
		/*if(accounts_group_masterRepository.checkAccTypeUsage(code))
		{
			group=true;
		}*/
		
		if(accounts_category_masterRepository.checkAccTypeUsage(code))
		{
			catagory=true;
		}
		
		if(catagory == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		
		return result;
	 }
	
	@Transactional
	public Accounts_type_master deleteaccountstype(Accounts_type_master accounts_type_master,long id)
	{
		Optional<Accounts_type_master> op = accounts_type_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Accounts_type_master acc=op.get();
		
		acc.setInserted_by(op.get().getInserted_by());
		acc.setInserted_on(op.get().getInserted_on());
		acc.setUpdated_by(op.get().getUpdated_by());
		acc.setUpdated_on(op.get().getUpdated_on());
		acc.setDeleted_by(userRepository.getUserDetails(acc.getUsername()).getName());
		acc.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			acc.setId(id);
		}
		acc.setModified_type("DELETED");
		
		return accounts_type_masterRepository.save(acc);
		
	}
}
