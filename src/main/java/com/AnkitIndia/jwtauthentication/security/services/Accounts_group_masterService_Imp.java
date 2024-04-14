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

import com.AnkitIndia.Exporttotally.Tallyrequest_AccountGroup;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;

import com.AnkitIndia.jwtauthentication.repository.Accounts_category_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_ledger_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Accounts_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Accounts_group_masterService_Imp implements Accounts_group_masterService{

	@Autowired
	Accounts_group_masterRepository accounts_group_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired 
	Accounts_type_masterRepository accounts_type_masterRepository;
	
	@Autowired
	Accounts_ledger_masterRepository accounts_ledger_masterRepository;
	
	@Autowired
	Accounts_category_masterRepository accounts_category_masterRepository;
	
	public SequenceIdDTO getSeqNoForAccGrp(String fin_year,String company) {
		
		int slno=0;
		String sno=accounts_group_masterRepository.countId();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="AMG-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
		
	}
	
	public List<Map<String,Object>> accGroupList(String company,String catagory) {
			
			List <Map<String,Object>> accGrList = new ArrayList<>();
			
			accGrList.addAll(accounts_group_masterRepository.accGroupList(true,company,catagory));
			
			return accGrList;
		}

	public List<Map<String,Object>> accountGroupList() {
		
		List <Map<String,Object>> accGrList = new ArrayList<>();
		
		accGrList.addAll(accounts_group_masterRepository.accountGroupList());
		
		return accGrList;
	}
	
	public List<Map<String,Object>> accountParentGroupList() {
			
			List <Map<String,Object>> accGrList = new ArrayList<>();
			
			accGrList.addAll(accounts_group_masterRepository.accountParentGroupList());
			
			return accGrList;
		}
	
	public List<Map<String,Object>> accountTypeList() {
		
		List <Map<String,Object>> acctypeList = new ArrayList<>();
		
		acctypeList.addAll(accounts_type_masterRepository.getAccountsTypeList());
		
		return acctypeList;
	}
	
	public List<Map<String,Object>> accountCatagoryList() {
		
		List <Map<String,Object>> acccatagoryList = new ArrayList<>();
		
		acccatagoryList.addAll(accounts_category_masterRepository.getAccountsCatagoryList());
		
		return acccatagoryList;
	}
	
	@Transactional
	public Accounts_group_master save(Accounts_group_master accounts_group_master) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(accounts_group_masterRepository.countId() != null ) {
			slno=Long.parseLong(accounts_group_masterRepository.countId());
		}
		String prefix="AGM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		if(accounts_group_master.getParent_group().compareTo("") ==0)
		{
			accounts_group_master.setParent_group(gen_sno);
			//accounts_group_master.setParent_group_name(accounts_group_master.getAccts_grp_name());
		}
		else {
			accounts_group_master.setParent_group(accounts_group_master.getParent_group());
			
			if(accounts_group_master.getParent_group().compareTo("NA") ==0)
			{
				accounts_group_master.setParent_group_name(accounts_group_master.getAccts_grp_name());
				accounts_group_master.setParent_group_name("NA");
			}
			else 
			{
				accounts_group_master.setParent_group_name(accounts_group_masterRepository.parentGroupNameByCode(accounts_group_master.getParent_group()).getAccts_grp_name());
			}
		}
		
		if(Utility.isNullOrEmpty(accounts_group_master.getAccts_id()))
		{
			accounts_group_master.setAcc_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_group_master.getAccts_id()).getAcc_type_name());
		}
		else 
		{
			accounts_group_master.setAcc_type_name("NA");
		}
		
	
		
		accounts_group_master.setAccts_group_id(gen_sno);
		accounts_group_master.setCompany_id(accounts_group_master.getCompany_id());
		accounts_group_master.setFin_year(accounts_group_master.getFin_year());
		accounts_group_master.setModified_type("INSERTED");
		accounts_group_master.setInserted_on(ldt);
		accounts_group_master.setInserted_by(userRepository.getUserDetails(accounts_group_master.getUsername()).getName());
		accounts_group_master.setUpdated_by("NA");
		accounts_group_master.setUpdated_on(ldt);
		accounts_group_master.setDeleted_by("NA");
		accounts_group_master.setDeleted_on(ldt);
		
		return accounts_group_masterRepository.save(accounts_group_master);
	}
	
	public Accounts_group_master findOne(long id)
	{
		Optional<Accounts_group_master> op=this.accounts_group_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Accounts_group_master postingaccountsgroup(long id) 
	{
		Optional<Accounts_group_master> op=this.accounts_group_masterRepository.findById(id);
		String accts_grp_name=op.get().getAccts_grp_name();
		String output;
		Tallyrequest_AccountGroup tally=new Tallyrequest_AccountGroup();
		try  
		{
			
			if(accts_grp_name.contains("&"))
			{
				accts_grp_name=accts_grp_name.replaceAll("&","&amp;");
				
			}
			
			
			output = tally.SendToTally(accts_grp_name,op.get().getAcc_type_name());
	        
			
		
			String [] splitoutput = output.split("\\|\\|");
		
			accounts_group_masterRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
		}
		catch(Exception e)
		{
		
			//System.out.println(e);
		}
		Optional<Accounts_group_master> op1=accounts_group_masterRepository.findById(id);//static details 
		
		return op1.get();
	}
	
	@Transactional
	public Accounts_group_master update(Accounts_group_master accounts_group_master,long id)
	{
		
		Optional<Accounts_group_master> op =accounts_group_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(accounts_group_master.getParent_group().compareTo("") ==0)
		{
			accounts_group_master.setParent_group(op.get().getAccts_group_id());
			//accounts_group_master.setParent_group_name(accounts_group_master.getAccts_grp_name());
		}
		else {
			accounts_group_master.setParent_group(accounts_group_master.getParent_group());
			
			if(accounts_group_master.getParent_group().compareTo("NA") ==0)
			{
				//accounts_group_master.setParent_group_name(accounts_group_master.getAccts_grp_name());
				accounts_group_master.setParent_group_name("NA");
			}
			else 
			{
				accounts_group_master.setParent_group_name(accounts_group_masterRepository.parentGroupNameByCode(accounts_group_master.getParent_group()).getAccts_grp_name());
			}
		}
		
		if(Utility.isNullOrEmpty(accounts_group_master.getAccts_id()))
		{
			accounts_group_master.setAcc_type_name(accounts_type_masterRepository.fetchbytypeid(accounts_group_master.getAccts_id()).getAcc_type_name());
		}
		else 
		{
			accounts_group_master.setAcc_type_name("NA");
		}
		
		
		
		accounts_group_master.setAccts_group_id(op.get().getAccts_group_id());
		accounts_group_master.setCompany_id(accounts_group_master.getCompany_id());
		accounts_group_master.setFin_year(accounts_group_master.getFin_year());
		accounts_group_master.setModified_type("INSERTED");
		accounts_group_master.setInserted_on(op.get().getInserted_on());
		accounts_group_master.setInserted_by(op.get().getInserted_by());
		accounts_group_master.setUpdated_by(userRepository.getUserDetails(accounts_group_master.getUsername()).getName());
		accounts_group_master.setUpdated_on(ldt);
		accounts_group_master.setDeleted_by("NA");
		accounts_group_master.setDeleted_on(ldt);
		
		return accounts_group_masterRepository.save(accounts_group_master);
	}
	
	public StatusDTO checkAccGroupUsage(String code)
	 {
		StatusDTO result = new StatusDTO();
		boolean ledger=false;
		
		if(accounts_ledger_masterRepository.checkAccGroupUsage(code))
		{
			ledger=true;
		}
		
		if(ledger == true)
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
	public Accounts_group_master deleteAccGrpMaster(Accounts_group_master accounts_group_master,long id)
	{
		Optional<Accounts_group_master> op = accounts_group_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Accounts_group_master acc=op.get();
		
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
		return accounts_group_masterRepository.save(acc);
		
	}
	
}
