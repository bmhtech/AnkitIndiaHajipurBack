package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_uom;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Custom_uom_master;

import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.CustomUomMasterDTO;

import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class CustomUomMasterService_Imp implements CustomUomMasterService {
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Transactional
	public Custom_uom_master save(Custom_uom_master custom) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="CUM";
		if(customUomMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(customUomMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		custom.setCustomuom_id(gen_sno);
		custom.setModified_type("INSERTED");
		custom.setInserted_by(userRepository.getUserDetails(custom.getUsername()).getName());
		custom.setInserted_on(ldt);
		custom.setUpdated_by("NA");
		custom.setUpdated_on(ldt);
		custom.setDeleted_by("NA");
		custom.setDeleted_on(ldt);
		
		return customUomMasterRepository.save(custom);
	}
	
	public List<Custom_uom_master> findAll()
	{
		List<Custom_uom_master> custList=new ArrayList<Custom_uom_master>();
		custList.addAll(customUomMasterRepository.findAll());
		
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Custom_uom_master::getCustomuom_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<CustomUomMasterDTO> getCustomUOMNCList()
	{
		List<Custom_uom_master> custList=customUomMasterRepository.findAll();
		
		custList.forEach((e)->{
			e.setDescription(e.getDescription().toUpperCase());
		});

				
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<CustomUomMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<CustomUomMasterDTO> customUomList= new ModelMapper().map(allData,listType);
		
		return customUomList;
	}
	
	public List<CustomUomMasterDTO> getStandardCustomUOM(String company)
	{
		List<Custom_uom_master> custList=new ArrayList<Custom_uom_master>();
		custList.addAll(customUomMasterRepository.findAll());
				
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
			  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<CustomUomMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<CustomUomMasterDTO> scustomUomList= new ModelMapper().map(allData,listType);
		
		return scustomUomList;
	}
	//Delete
	public List<CustomUomMasterDTO> getStandardCustomUOM()
	{
		List<Custom_uom_master> custList=new ArrayList<Custom_uom_master>();
		custList.addAll(customUomMasterRepository.findAll());
				
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<CustomUomMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<CustomUomMasterDTO> scustomUomList= new ModelMapper().map(allData,listType);
		
		return scustomUomList;
	}
	
	public List<CustomUomMasterDTO> getWeighmentCustomUOM()
	{
		List<Custom_uom_master> custList=new ArrayList<Custom_uom_master>();
		custList.addAll(customUomMasterRepository.findAll());
				
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<CustomUomMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<CustomUomMasterDTO> wcustomUomList= new ModelMapper().map(allData,listType);
		
		return wcustomUomList;
	}
	
	public List<CustomUomMasterDTO> getCustomUOMs(String uomtype)
	{
		List<Custom_uom_master> custList=customUomMasterRepository.findAll();
				
		custList.forEach((e)->{
			e.setDescription(e.getDescription().toUpperCase());
		});
		
		List<Custom_uom_master> allData = custList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCustomuom_catg().equals(uomtype))
			  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<CustomUomMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<CustomUomMasterDTO> UomList= new ModelMapper().map(allData,listType);
		
		return UomList;
	}
	
	public List<Map<String,Object>> getUomList()
	{
		return customUomMasterRepository.getUomList();
	}
	
	public Custom_uom_master getCustomUomsbyname(String uomname) 
	{
		Custom_uom_master custuom= customUomMasterRepository.getname(uomname);
		return custuom;
	}
	public CustomUomMasterDTO getUomByIGroup(String code)
	{
		Custom_uom_master modelList=customUomMasterRepository.getUomByIGroup(code);
		
		Type listType = new TypeToken<CustomUomMasterDTO>() {}.getType();

		CustomUomMasterDTO customUomName = new ModelMapper().map(modelList,listType);
		
		return customUomName;
	}
	
	public CustomUomMasterDTO getCustomUomById(String cuomid){
		Custom_uom_master modelList=customUomMasterRepository.getCustomUomById(cuomid);
		
		Type listType = new TypeToken<CustomUomMasterDTO>() {}.getType();

		CustomUomMasterDTO customUomName = new ModelMapper().map(modelList,listType);
		
		return customUomName;
	}
	
	public Custom_uom_master findOne(long id)
	{
		Optional<Custom_uom_master> op=this.customUomMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Custom_uom_master update(Custom_uom_master custom,long id)
	{
		Optional<Custom_uom_master> op =customUomMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		custom.setCustomuom_id(op.get().getCustomuom_id());
		custom.setModified_type("UPDATED");
		custom.setInserted_by(op.get().getInserted_by());
		custom.setInserted_on(op.get().getInserted_on());
		custom.setUpdated_by(userRepository.getUserDetails(custom.getUsername()).getName());
		custom.setUpdated_on(ldt);
		custom.setDeleted_by("NA");
		custom.setDeleted_on(ldt);
		custom.setResponse("NA");
		
		//System.out.println("Decimal Value: "+custom.getDecimalv());
		
		if(op.isPresent())
		{
			custom.setId(id);
		}
		return customUomMasterRepository.save(custom);
	}
	
	@Transactional
	public Custom_uom_master deleteCustom_uom(Custom_uom_master custom,long id)
	{
		Optional<Custom_uom_master> op = customUomMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Custom_uom_master custMaster=op.get();

		custMaster.setInserted_by(op.get().getInserted_by());
		custMaster.setInserted_on(op.get().getInserted_on());
		custMaster.setUpdated_by(op.get().getUpdated_by());
		custMaster.setUpdated_on(op.get().getUpdated_on());
		custMaster.setDeleted_by(userRepository.getUserDetails(custom.getUsername()).getName());
		custMaster.setDeleted_on(ldt);
		custMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			custMaster.setId(id);
		}
		return customUomMasterRepository.save(custMaster);
	}
	
	public SequenceIdDTO getCustomUOMSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=customUomMasterRepository.getCustomUOMSequenceId(fprefix,company);
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

	public List<Custom_uom_master> findCustom_uom(String searchtext)
	{
		List<Custom_uom_master> custList=new ArrayList<Custom_uom_master>();
		custList.addAll(customUomMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Custom_uom_master> allData = custList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Custom_uom_master> allData = custList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getCustomuom_code()+c.getCustomuom_catg()+c.getDescription()+c.getRemarks()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Custom_uom_master::getDescription))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	@Transactional
	public Custom_uom_master exportuom(long id) 
	{
		Optional<Custom_uom_master> op=this.customUomMasterRepository.findById(id);
		System.out.println("over here ");
		String output,finalresult;
		Tallyrequest_uom tally=new Tallyrequest_uom();
		try  
		{
			output=tally.SendToTally(op.get().getDescription(), op.get().getDecimalv());
			
			System.out.println(output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
		     customUomMasterRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		
	//	Custom_uom_master custMaster=op.get();
	
		Optional<Custom_uom_master> op1=this.customUomMasterRepository.findById(id);
		System.out.println(op.get());
		
		return op1.get();
	}

}
