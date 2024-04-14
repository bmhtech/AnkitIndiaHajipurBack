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
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Wm_charges_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_charges_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_charges_masterDTO;
import com.AnkitIndia.jwtauthentication.security.services.Wm_charges_masterService;

@Service
public class Wm_charges_masterService_Imp implements Wm_charges_masterService{
	
	@Autowired
	Wm_charges_masterRepository wm_charges_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Wm_unload_wgmntRepository wm_unload_wgmntRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Transactional
	public Wm_charges_master save(Wm_charges_master wm_charges_master) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		String prefix="WCM";
		long slno=0;
		if(wm_charges_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(wm_charges_masterRepository.countId(prefix).get());
		}

		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		wm_charges_master.setWm_charge_id(gen_sno);	
		wm_charges_master.setModified_type("INSERTED");
		wm_charges_master.setInserted_by(userRepository.getUserDetails(wm_charges_master.getUsername()).getName());
		wm_charges_master.setInserted_on(ldt);
		wm_charges_master.setUpdated_by("NA");
		wm_charges_master.setUpdated_on(ldt);
		wm_charges_master.setDeleted_by("NA");
		wm_charges_master.setDeleted_on(ldt);
	
		return wm_charges_masterRepository.save(wm_charges_master);
	}
	
	public Wm_charges_master findOne(Long id) {
		 Optional<Wm_charges_master> op=this.wm_charges_masterRepository.findById(id);
		 return op.get();
	}
	
	@Transactional
	public Wm_charges_master update(Wm_charges_master pMaster,long id)
	{
		Optional<Wm_charges_master> op = wm_charges_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		pMaster.setWm_charge_id(op.get().getWm_charge_id());	
		pMaster.setModified_type("UPDATED");
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			pMaster.setId(id);
		}
		return wm_charges_masterRepository.save(pMaster);
	}
	
	public List<Wm_charges_master> findAll()
	{
		List<Wm_charges_master> wnChargeList=new ArrayList<Wm_charges_master>();
		wnChargeList.addAll(wm_charges_masterRepository.findAll());
				
		List<Wm_charges_master> allData = wnChargeList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Wm_charges_master::getWm_charge_id).reversed())
			  .collect(Collectors.toList());
		
		for(int i=0;i<allData.size();i++) {
			allData.get(i).setBu_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(allData.get(i).getBu_unit()).getBusinessunit_name());
		}
		
		return allData;
	
	}
	
	public Wm_charges_masterDTO getWeighmentChargeMasters(String wm_charge_id)
	{
		Wm_charges_master modelList=wm_charges_masterRepository.getWeighmentChargeMasters(wm_charge_id);
		
		Type listType=new TypeToken<Wm_charges_masterDTO>() {}.getType();
		
		Wm_charges_masterDTO wmcharge=new ModelMapper().map(modelList,listType);
		
		return wmcharge;
	}
	
	public List<Wm_charges_masterDTO> getWeighmentCharges()
	{
		
		List<Wm_charges_master> wnChargeList=new ArrayList<Wm_charges_master>();
		wnChargeList.addAll(wm_charges_masterRepository.findAll());
				
		List<Wm_charges_master> allData = wnChargeList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Wm_charges_master::getWm_charge_desc))
			  .collect(Collectors.toList());
		
		for(int i=0;i<allData.size();i++) {
			allData.get(i).setBu_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(allData.get(i).getBu_unit()).getBusinessunit_name());
		}
		
		
		Type listType=new TypeToken<List<Wm_charges_masterDTO>>() {}.getType();
		
		List<Wm_charges_masterDTO> wmcharge=new ModelMapper().map(allData,listType);
		
		return wmcharge;
	}
	
	public Wm_charges_masterDTO getWeighmentChargeThruVtype(String vehicletype)
	{
		Wm_charges_master modelList=wm_charges_masterRepository.getWeighmentChargeThruVtype(vehicletype);
		Type listType=new TypeToken<Wm_charges_masterDTO>() {}.getType();
		
		if(modelList == null) {
			Wm_charges_masterDTO def=new Wm_charges_masterDTO(0L,"0","0","0","0","0",0.0,"0",0.0,"0","0","0");
			
			Wm_charges_masterDTO wmcharge = new ModelMapper().map(def,listType);
			return wmcharge;
		}
		else {
			Wm_charges_masterDTO wmcharge = new ModelMapper().map(modelList,listType);
			return wmcharge;
		}
	}
	
	public SequenceIdDTO getWnChargesSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=wm_charges_masterRepository.getWnChargesSequenceId(fprefix,company);
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
	
	public List<Wm_charges_master> findWmCharges(String searchtext)
	{
		List<Wm_charges_master> wmChargeList=new ArrayList<Wm_charges_master>();
		wmChargeList.addAll(wm_charges_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Wm_charges_master> allData = wmChargeList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Wm_charges_master::getWm_charge_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Wm_charges_master> allData = wmChargeList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getWm_charge_desc()+c.getVehicle_type()+c.getBu_unit()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Wm_charges_master::getWm_charge_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	
	@Transactional
	public Wm_charges_master deleteWmCharges(Wm_charges_master wm_charges_master,long id)
	{
		Optional<Wm_charges_master> op = wm_charges_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Wm_charges_master wMaster=op.get();
		
		wMaster.setInserted_by(op.get().getInserted_by());
		wMaster.setInserted_on(op.get().getInserted_on());
		wMaster.setUpdated_by(op.get().getUpdated_by());
		wMaster.setUpdated_on(op.get().getUpdated_on());
		wMaster.setDeleted_by(userRepository.getUserDetails(wm_charges_master.getUsername()).getName());
		wMaster.setDeleted_on(ldt);
		wMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			wMaster.setId(id);
		}
			return wm_charges_masterRepository.save(wMaster);
	}
	
	public StatusDTO checkWmChgsMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false;
		
		if(wm_charges_masterRepository.checkWmChgsMasterUsage(code))//need to change Query 
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
}
