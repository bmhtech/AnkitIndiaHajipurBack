package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Accounts_category_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;
import com.AnkitIndia.jwtauthentication.repository.Accounts_category_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Accounts_category_masterService_Imp implements Accounts_category_masterService {

	
	@Autowired
	Accounts_category_masterRepository accounts_category_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Accounts_type_masterRepository accounts_type_masterRepository;
	
	@Autowired
	Accounts_group_masterRepository accounts_group_masterRepository;
	
	public List<Map<String, Object>> getAccountsCategoryList()
	{
		return accounts_category_masterRepository.getAccountsCategoryList();
	}
	
	public SequenceIdDTO getAccountCatagorySequenceId(String fin_year) 
	{
		int slno=0;
		String sno=accounts_category_masterRepository.countId();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="AMC-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Accounts_category_master save(Accounts_category_master accounts_category_master) 
	{
		

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(accounts_category_masterRepository.countId() != null ) {
			slno=Long.parseLong(accounts_category_masterRepository.countId());
		}
		String prefix="AMC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
        String t_sno=accounts_category_masterRepository.countId();
        int tslno=0;
		if(t_sno != null )
		{
			tslno=Integer.parseInt(t_sno);
		}
		
		String fin_yearspit[]=accounts_category_master.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefixc="AMC-"+final_fyear+"-";
		String gen_snoc=UniqueIDTransaction.uniqueId4(prefixc,tslno);
		
		if(Utility.isNullOrEmpty(accounts_category_master.getAccts_type()))
		{
		//	accounts_category_master.setAccts_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_category_master.getAccts_type()).getAccts_name());
		}
		else 
		{
			accounts_category_master.setAccts_type_name("NA");
		}
		
		accounts_category_master.setAccts_catagory_id(gen_sno);
		accounts_category_master.setAccts_catagory_code(gen_snoc);
		accounts_category_master.setModified_type("INSERTED");
		accounts_category_master.setInserted_by(userRepository.getUserDetails(accounts_category_master.getUsername()).getName());
		accounts_category_master.setInserted_on(ldt);
		accounts_category_master.setUpdated_by("NA");
		accounts_category_master.setUpdated_on(ldt);
		accounts_category_master.setDeleted_by("NA");
		accounts_category_master.setDeleted_on(ldt);
		
		return  accounts_category_masterRepository.save(accounts_category_master);
	}
	
	public Accounts_category_master findOne(long id) 
	{
		Optional<Accounts_category_master> op=this.accounts_category_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Accounts_category_master update(Accounts_category_master accounts_category_master,long id) 
	{

		
		Optional<Accounts_category_master> op =accounts_category_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		if(Utility.isNullOrEmpty(accounts_category_master.getAccts_type()))
		{
			//accounts_category_master.setAccts_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_category_master.getAccts_type()).getAccts_name());
		}
		else 
		{
			accounts_category_master.setAccts_type_name("NA");
		}
		accounts_category_master.setAccts_catagory_id(op.get().getAccts_catagory_id());
		accounts_category_master.setAccts_catagory_code(op.get().getAccts_catagory_code());
		accounts_category_master.setCompany_id(accounts_category_master.getCompany_id());
		accounts_category_master.setFin_year(accounts_category_master.getFin_year());
		accounts_category_master.setModified_type("INSERTED");
		accounts_category_master.setInserted_on(op.get().getInserted_on());
		accounts_category_master.setInserted_by(op.get().getInserted_by());
		accounts_category_master.setUpdated_by(userRepository.getUserDetails(accounts_category_master.getUsername()).getName());
		accounts_category_master.setUpdated_on(ldt);
		accounts_category_master.setDeleted_by("NA");
		accounts_category_master.setDeleted_on(ldt);
		
		return accounts_category_masterRepository.save(accounts_category_master);
		
	}
	
	public StatusDTO checkAccCatagoryUsage(String code)
	 {
		StatusDTO result = new StatusDTO();
		/*boolean catagory=false;
		if(accounts_group_masterRepository.checkAccCatagoryUsage(code))
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
		}*/
		result.setStatus("No");
		return result;
	 }
	
	@Transactional
	public Accounts_category_master deleteAccCatagoryMaster(Accounts_category_master accounts_category_master,long id)
	{
		Optional<Accounts_category_master> op = accounts_category_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Accounts_category_master acc=op.get();
		
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
		return accounts_category_masterRepository.save(acc);
		
	}
	
}
