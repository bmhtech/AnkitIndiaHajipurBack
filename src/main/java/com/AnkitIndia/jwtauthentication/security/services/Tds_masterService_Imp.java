package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Tds_master;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Tds_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tds_masterDTO;

@Service
public class Tds_masterService_Imp implements Tds_masterService{

	@Autowired
	Tds_masterRepository tds_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LedgermasterRepository 	ledgermasterRepository;
	
	@Transactional
	public Tds_master save(Tds_master tds_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="TDSM";
		if(tds_masterRepository.countId(prefix).isPresent() ) {
			slno=Long.parseLong(tds_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		tds_master.setTds_id(gen_sno);
		
		
		
		if(Utility.isNullOrEmpty(tds_master.getTds_acc())) 
		{
			tds_master.setTds_accname(ledgermasterRepository.getLedgers(tds_master.getTds_acc()).getLedgername());
		}
		else
		{
			tds_master.setTds_accname("None");
		}
		
		tds_master.setModified_type("INSERTED");
		tds_master.setInserted_by(userRepository.getUserDetails(tds_master.getUsername()).getName());
		tds_master.setInserted_on(ldt);
		tds_master.setUpdated_by("NA");
		tds_master.setUpdated_on(ldt);
		tds_master.setDeleted_by("NA");
		tds_master.setDeleted_on(ldt);
		
		return  tds_masterRepository.save(tds_master);
	}
	
	@Transactional
	public Tds_master update(Tds_master tds_master,long id)
	{
		Optional<Tds_master> op = tds_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();

		tds_master.setTds_id(op.get().getTds_id());
		
		if(Utility.isNullOrEmpty(tds_master.getTds_acc())) 
		{
			tds_master.setTds_accname(ledgermasterRepository.getLedgers(tds_master.getTds_acc()).getLedgername());
		}
		else
		{
			tds_master.setTds_accname("None");
		}
		
		tds_master.setModified_type("UPDATED");
		tds_master.setInserted_by(op.get().getInserted_by());
		tds_master.setInserted_on(op.get().getInserted_on());
		tds_master.setUpdated_by(userRepository.getUserDetails(tds_master.getUsername()).getName());
		tds_master.setUpdated_on(ldt);
		tds_master.setDeleted_by("NA");
		tds_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			tds_master.setId(id);
		}
		return tds_masterRepository.save(tds_master);
	}
	
	@Transactional
	public Tds_master deleteTds(Tds_master tds,long id)
	{
		Optional<Tds_master> op = tds_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Tds_master tds_master=op.get();
		
		tds_master.setTds_id(op.get().getTds_id());
		tds_master.setModified_type("DELETED");
		tds_master.setInserted_by(op.get().getInserted_by());
		tds_master.setInserted_on(op.get().getInserted_on());
		tds_master.setUpdated_by(op.get().getUpdated_by());
		tds_master.setUpdated_on(op.get().getUpdated_on());
		tds_master.setDeleted_by(userRepository.getUserDetails(tds.getUsername()).getName());
		tds_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			tds_master.setId(id);
		}
		return tds_masterRepository.save(tds_master);
	}
	
	public List<Tds_master> findAll()
	{
		List<Tds_master> tdsList=new ArrayList<Tds_master>();
		tdsList.addAll(tds_masterRepository.findAll());
				
		List<Tds_master> allData = tdsList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Tds_master::getTds_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Tds_master findOne(long id)
	{
		Optional<Tds_master> op=this.tds_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Tds_masterDTO> getTdsMNCList(){
		
		List<Tds_master> modelList=tds_masterRepository.getTdsMNCList();
		
		Type listType=new TypeToken<List<Tds_masterDTO>>() {}.getType();
		
		List<Tds_masterDTO> tdsList=new ModelMapper().map(modelList, listType);
		
		return tdsList;
	}
	
	public Tds_masterDTO getTDSRate(String tdsid){
		
		Tds_master modelList=tds_masterRepository.getTDSRate(tdsid);
		
		Type listType=new TypeToken<Tds_masterDTO>() {}.getType();
		
		Tds_masterDTO tds=new ModelMapper().map(modelList, listType);
		
		return tds;
	}

	public List<Tds_master> findTds(String searchtext,String company)
	{
		List<Tds_master> iTypeList=new ArrayList<Tds_master>();
		iTypeList.addAll(tds_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Tds_master> allData = iTypeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Tds_master::getTds_type))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Tds_master> allData = iTypeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getTds_type()+c.getTds_section()+c.getTds_rate()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(Tds_master::getTds_type))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

	public StatusDTO checkTdsMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false;
		
		if(tds_masterRepository.checkTdsMasterUsage(code))//need to change Query 
		{
			purchase=true;
		}
		if(purchase == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		return result;
	 }
	
	public Tds_master gettdsdetails(String taxcode) 
	{
		return tds_masterRepository.getTDSRate(taxcode);
	}
	
	/*
	 * @Autowired
	 * 
	 * CompanyMasterRepository companyMasterRepository;
	 * 
	 * 
	 * public SequenceIdDTO getTdsSequenceId(String prefix,String company) { Long
	 * slno=0L;String fprefix=""; String
	 * code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
	 * fprefix=prefix+"/"+code+"/"; System.err.println("Code: > "+fprefix); String
	 * gen_sno=tds_masterRepository.getTdsSequenceId(fprefix,company);
	 * System.err.println("No: > "+gen_sno);
	 * 
	 * if(gen_sno != null ) { slno=Long.parseLong(gen_sno); }
	 * 
	 * String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
	 * 
	 * Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
	 * 
	 * SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
	 * 
	 * genCode.setSequenceid(gen_slno);
	 * 
	 * return genCode; }
	 */

}
