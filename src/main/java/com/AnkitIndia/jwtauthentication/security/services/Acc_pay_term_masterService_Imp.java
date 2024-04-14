package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master_details;
import com.AnkitIndia.jwtauthentication.repository.Acc_pay_termRepository;
import com.AnkitIndia.jwtauthentication.repository.Acc_pay_term_master_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_master_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Acc_pay_term_masterService_Imp implements Acc_pay_term_masterService{
	@Autowired
	Acc_pay_termRepository acc_pay_termRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Acc_pay_term_master_detailsRepository acc_pay_term_master_detailsRepository;
	
	@Transactional
	public Acc_pay_term_master save(Acc_pay_term_master payterm)
	{
        LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_pay_termRepository.countId() != null ) {
			slno=Long.parseLong(acc_pay_termRepository.countId());
		}
		String prefix="APT";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		payterm.setPayterm_id(gen_sno);
		payterm.setPayterm_code(gen_sno);
		payterm.setModified_type("INSERTED");
		payterm.setInserted_by(userRepository.getUserDetails(payterm.getUsername()).getName());
		payterm.setInserted_on(ldt);
		payterm.setUpdated_by("NA");
		payterm.setUpdated_on(ldt);
		payterm.setDeleted_by("NA");
		payterm.setDeleted_on(ldt);
		
		Set<Acc_pay_term_master_details> aacPaySet = new HashSet<Acc_pay_term_master_details>();
		
		aacPaySet.addAll(payterm.getAcc_pay_term_master_details());
		for(Acc_pay_term_master_details accpdts : aacPaySet)
		{
			accpdts.setPayterm_code(gen_sno);
			accpdts.setPayterm_id(gen_sno);
			accpdts.setModified_type("INSERTED");
			accpdts.setInserted_by(userRepository.getUserDetails(payterm.getUsername()).getName());
			accpdts.setInserted_on(ldt);
			accpdts.setUpdated_by("NA");
			accpdts.setUpdated_on(ldt);
			accpdts.setDeleted_by("NA");
			accpdts.setDeleted_on(ldt);
		
		}
		payterm.setAcc_pay_term_master_details(aacPaySet); 
		 
		
		return acc_pay_termRepository.save(payterm);
	}
	public List<Acc_pay_term_master> findAll()
	{
		List<Acc_pay_term_master> accPayList=acc_pay_termRepository.findAll();
		
		accPayList.forEach((e)->{
			e.setPayterm_desc(e.getPayterm_desc().toUpperCase());
		});

		List<Acc_pay_term_master> allData1 =accPayList.stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_desc).reversed())
				  .collect(Collectors.toList());
		return allData1;
	}
	
	public List<Acc_pay_term_master> getAccPayTermsMaster()
	{
		List<Acc_pay_term_master> accPayList=acc_pay_termRepository.findAll();
		
		List<Acc_pay_term_master> allData1 =accPayList.stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_id).reversed())
				  .collect(Collectors.toList());
		return allData1;
	}
	
	public Acc_pay_term_master findOne(long id)
	{
		Optional<Acc_pay_term_master> op = this.acc_pay_termRepository.findById(id);
		return op.get();
	}
	
	
	@Transactional
	public Acc_pay_term_master update(Acc_pay_term_master payterm,long id)
	{
		Optional<Acc_pay_term_master> op = acc_pay_termRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		payterm.setPayterm_id(op.get().getPayterm_id());
		payterm.setPayterm_code(op.get().getPayterm_code());
		payterm.setModified_type("INSERTED");
		payterm.setInserted_by(op.get().getInserted_by());
		payterm.setInserted_on(op.get().getInserted_on());
		payterm.setUpdated_by(userRepository.getUserDetails(payterm.getUsername()).getName());
		payterm.setUpdated_on(ldt);
		payterm.setDeleted_by("NA");
		payterm.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			payterm.setId(id);
		}
		
		acc_pay_term_master_detailsRepository.updateAcc_pay_term_master_details(payterm.getPayterm_id(),"UPDATED");
		Set<Acc_pay_term_master_details> aacPaySet = new HashSet<Acc_pay_term_master_details>();
		
		aacPaySet.addAll(payterm.getAcc_pay_term_master_details());
		for(Acc_pay_term_master_details accpdts : aacPaySet)
		{
			accpdts.setPayterm_code(payterm.getPayterm_id());
			accpdts.setPayterm_id(payterm.getPayterm_id());
			accpdts.setModified_type("INSERTED");
			accpdts.setInserted_by(op.get().getInserted_by());
			accpdts.setInserted_on(op.get().getInserted_on());
			accpdts.setUpdated_by(payterm.getUpdated_by());
			accpdts.setUpdated_on(payterm.getUpdated_on());
			accpdts.setDeleted_by("NA");
			accpdts.setDeleted_on(ldt);
		
		}
		payterm.setAcc_pay_term_master_details(aacPaySet); 
		
		return acc_pay_termRepository.save(payterm);
	}
	
	public List<Acc_pay_term_master> getPayTermList()
	{
		return acc_pay_termRepository.getPayTermList();
	}
	
	public List<Acc_pay_term_masterDTO> getAccPaytermNc() {
		List<Acc_pay_term_master> accPayList=new ArrayList<Acc_pay_term_master>();
		accPayList.addAll(acc_pay_termRepository.findAll());
		
		List<Acc_pay_term_master> allData1 =accPayList.stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_desc))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Acc_pay_term_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Acc_pay_term_masterDTO> paytermList = new ModelMapper().map(allData1,listType);
		
		return paytermList;	
	}
	
	public List<Acc_pay_term_masterDTO> getPayTermNCList(){
		
		List<Acc_pay_term_master> accPayList=acc_pay_termRepository.findAll();
		accPayList.forEach((e->{
			e.setPayterm_desc(e.getPayterm_desc().toUpperCase());
		}));		
		List<Acc_pay_term_master> allData1 =accPayList.stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.isPayterm_active() == true)
				  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_desc))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Acc_pay_term_masterDTO>>() {}.getType();
		
		List<Acc_pay_term_masterDTO> payTermList=new ModelMapper().map(allData1, listType);
		
		return payTermList;
	}
	
	public List<Map<String,Object>> getPayTermNCListFast(){
		
		List<Map<String,Object>> accPayList=new ArrayList<Map<String,Object>>();
				
		accPayList.addAll(acc_pay_termRepository.getPayTermNCListFast());
				
		return accPayList;
	}
	
	public List<Acc_pay_term_master_detailsDTO> payTermRetriveList(String p_id)
	{
		List<Acc_pay_term_master> modelList=new ArrayList<Acc_pay_term_master>();
		
		modelList.addAll(acc_pay_termRepository.payTermRetriveList(p_id));
			
		Type listType=new TypeToken<List<Acc_pay_term_master_detailsDTO>>() {}.getType();
		
		List<Acc_pay_term_master_detailsDTO> payTerm=new ModelMapper().map(modelList,listType);
		
		return payTerm;
	}
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public SequenceIdDTO getPayTermSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=acc_pay_termRepository.getPayTermSequenceId(fprefix,company);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	
	
	@Transactional
	public Acc_pay_term_master deleteAccPayTerm(Acc_pay_term_master acc_pay_term_master,long id)
	{
		Optional<Acc_pay_term_master> op = acc_pay_termRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Acc_pay_term_master aptmaster=op.get();

		aptmaster.setInserted_by(op.get().getInserted_by());
		aptmaster.setInserted_on(op.get().getInserted_on());
		aptmaster.setUpdated_by(op.get().getUpdated_by());
		aptmaster.setUpdated_on(op.get().getUpdated_on());
		aptmaster.setDeleted_by(userRepository.getUserDetails(acc_pay_term_master.getUsername()).getName());
		aptmaster.setDeleted_on(ldt);
		aptmaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			aptmaster.setId(id);
		}
		
		aptmaster.setModified_type("DELETED");
		acc_pay_term_master_detailsRepository.updateAcc_pay_term_master_details(op.get().getPayterm_id(),"DELETED");

		return acc_pay_termRepository.save(aptmaster);
	}
	
	
	public List<Acc_pay_term_master> findAccPayTerm(String searchtext)
	{
		List<Acc_pay_term_master> cityList=new ArrayList<Acc_pay_term_master>();
		cityList.addAll(acc_pay_termRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Acc_pay_term_master> allData = cityList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Acc_pay_term_master> allData = cityList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && (c.getPayterm_desc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Acc_pay_term_master::getPayterm_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

	public StatusDTO checkPayTermUsage(String payment_id)
	 {
		StatusDTO result = new StatusDTO();
		boolean purchase=false,sales=false,customer=false,supplier=false,broker=false,transporter=false;
		
		if(acc_pay_termRepository.checkPayTermPurchaseUsage(payment_id))
		{
			purchase=true;
		}
		
		if(acc_pay_termRepository.checkPayTermSalesUsage(payment_id))
		{
			sales=true;
		}
		
		if(acc_pay_termRepository.checkPayTermSupplierUsage(payment_id))
		{
			supplier=true;
		}
		
		if(acc_pay_termRepository.checkPayTermTransporterUsage(payment_id))
		{
			transporter=true;
		}
		
		if(acc_pay_termRepository.checkPayTermCustomerUsage(payment_id))
		{
			customer=true;
		}
		
		if(acc_pay_termRepository.checkPayTermBrokerUsage(payment_id))
		{
			broker=true;
		}
		
		if(purchase == true || sales == true || supplier == true || transporter == true || customer == true || broker == true)
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
