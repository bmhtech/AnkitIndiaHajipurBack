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
import com.AnkitIndia.jwtauthentication.model.Accounts_ledger_master;
import com.AnkitIndia.jwtauthentication.repository.Accounts_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_ledger_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class Accounts_ledger_masterService_Imp implements Accounts_ledger_masterService
{

	@Autowired
	Accounts_ledger_masterRepository accounts_ledger_masterRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Accounts_group_masterRepository accounts_group_masterRepository;
	
	@Autowired
	Accounts_type_masterRepository accounts_type_masterRepository;
	
	public SequenceIdDTO getAccountLedgerSequenceId(String fin_year) 
	{
		

		int slno=0;
		String sno=accounts_ledger_masterRepository.countId();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="AML-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public List<Map<String, Object>> accountledgerlist()
	{
		return accounts_ledger_masterRepository.accountledgerlist();
	}
	
	public Accounts_ledger_master findOne(long id) 
	{
		Optional<Accounts_ledger_master> op=this.accounts_ledger_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
    public Accounts_ledger_master save(Accounts_ledger_master accounts_ledger_master) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(accounts_ledger_masterRepository.countId() != null ) {
			slno=Long.parseLong(accounts_ledger_masterRepository.countId());
		}
		String prefix="AMC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
        String t_sno=accounts_ledger_masterRepository.countId();
        int tslno=0;
		if(t_sno != null )
		{
			tslno=Integer.parseInt(t_sno);
		}
		
		String fin_yearspit[]=accounts_ledger_master.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefixc="AMC-"+final_fyear+"-";
		String gen_snoc=UniqueIDTransaction.uniqueId4(prefixc,tslno);
		
		if(Utility.isNullOrEmpty(accounts_ledger_master.getAccts_ledger_group()))
		{
			accounts_ledger_master.setAccts_ledger_group_name(accounts_group_masterRepository.parentGroupNameByCode(accounts_ledger_master.getAccts_ledger_group()).getAccts_grp_name());
		}
		else 
		{
			accounts_ledger_master.setAccts_ledger_group_name("NA");
		}
		
		if(Utility.isNullOrEmpty(accounts_ledger_master.getAccts_id()))
		{
			accounts_ledger_master.setAcc_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_ledger_master.getAccts_id()).getAcc_type_name());
		}
		else 
		{
			accounts_ledger_master.setAcc_type_name("NA");
		}
		
		accounts_ledger_master.setAccts_ledger_id(gen_sno);
		accounts_ledger_master.setAccts_ledger_code(gen_snoc);
		accounts_ledger_master.setModified_type("INSERTED");
		accounts_ledger_master.setInserted_by(userRepository.getUserDetails(accounts_ledger_master.getUsername()).getName());
		accounts_ledger_master.setInserted_on(ldt);
		accounts_ledger_master.setUpdated_by("NA");
		accounts_ledger_master.setUpdated_on(ldt);
		accounts_ledger_master.setDeleted_by("NA");
		accounts_ledger_master.setDeleted_on(ldt);
		
		return  accounts_ledger_masterRepository.save(accounts_ledger_master);
	}
	
	@Transactional
	public Accounts_ledger_master update(Accounts_ledger_master accounts_ledger_master,long id) 
	{
		Optional<Accounts_ledger_master> op =accounts_ledger_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		if(Utility.isNullOrEmpty(accounts_ledger_master.getAccts_ledger_group()))
		{
			accounts_ledger_master.setAccts_ledger_group_name(accounts_group_masterRepository.parentGroupNameByCode(accounts_ledger_master.getAccts_ledger_group()).getAccts_grp_name());
		}
		else 
		{
			accounts_ledger_master.setAccts_ledger_group_name("NA");
		}
		
		if(Utility.isNullOrEmpty(accounts_ledger_master.getAccts_id()))
		{
			accounts_ledger_master.setAcc_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_ledger_master.getAccts_id()).getAcc_type_name());
		}
		else 
		{
			accounts_ledger_master.setAcc_type_name("NA");
		}
		accounts_ledger_master.setAccts_ledger_id(op.get().getAccts_ledger_id());
		accounts_ledger_master.setAccts_ledger_code(op.get().getAccts_ledger_code());
		accounts_ledger_master.setCompany_id(accounts_ledger_master.getCompany_id());
		accounts_ledger_master.setFin_year(accounts_ledger_master.getFin_year());
		accounts_ledger_master.setModified_type("INSERTED");
		accounts_ledger_master.setInserted_on(op.get().getInserted_on());
		accounts_ledger_master.setInserted_by(op.get().getInserted_by());
		accounts_ledger_master.setUpdated_by(userRepository.getUserDetails(accounts_ledger_master.getUsername()).getName());
		accounts_ledger_master.setUpdated_on(ldt);
		accounts_ledger_master.setDeleted_by("NA");
		accounts_ledger_master.setDeleted_on(ldt);
		
		return accounts_ledger_masterRepository.save(accounts_ledger_master);
		
		
	}
	
	@Transactional
	public Accounts_ledger_master deleteAccLedger(Accounts_ledger_master accounts_ledger_master,long id)
	{
		Optional<Accounts_ledger_master> op = accounts_ledger_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Accounts_ledger_master acc=op.get();
		
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
		return accounts_ledger_masterRepository.save(acc);
		
	}
}
