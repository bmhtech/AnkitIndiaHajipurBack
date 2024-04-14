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

import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleTypeMasterRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleTypeMasterDTO;

@Service
public class VehicleTypeMasterService_Imp implements VehicleTypeMasterService {

	@Autowired
	VehicleTypeMasterRepository vehicleTypeMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public SequenceIdDTO getVtypeSequenceId(String prefix) {
		System.err.println("Got: "+prefix);
		Long slno=0L;String fprefix="";
		fprefix=prefix;
		String gen_sno=vehicleTypeMasterRepository.getVtypeSequenceId(fprefix);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		System.err.println("New ID: "+gen_slno);
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	
	@Transactional
	public VehicleTypeMaster save(VehicleTypeMaster vehicletype) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="VTM";
		if(vehicleTypeMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(vehicleTypeMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=vehicletype.getVehtype_code();
		String gen_snonew=vehicleTypeMasterRepository.getVtypeSequenceId(tprefix.substring(0,2));
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,2),nslno);
		vehicletype.setVehtype_code(gen_tslno);
		/*End checking transaction no for unique */
		
		if(vehicletype.getBusinessunit_code().compareTo("0") !=0 && vehicletype.getBusinessunit_code().compareTo("") !=0 && vehicletype.getBusinessunit_code() !=null) {
			vehicletype.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(vehicletype.getBusinessunit_code()).getBusinessunit_name());
		}else {vehicletype.setBusinessunit_name("None");}
		
		vehicletype.setVehtype_id(gen_sno);
		vehicletype.setModified_type("INSERTED");
		vehicletype.setInserted_by(userRepository.getUserDetails(vehicletype.getUsername()).getName());
		vehicletype.setInserted_on(ldt);
		vehicletype.setUpdated_by("NA");
		vehicletype.setUpdated_on(ldt);
		vehicletype.setDeleted_by("NA");
		vehicletype.setDeleted_on(ldt);
		
		return vehicleTypeMasterRepository.save(vehicletype);
	}
	
	public List<VehicleTypeMaster> findAll()
	{
		List<VehicleTypeMaster> vehichletypeList=new ArrayList<VehicleTypeMaster>();
		vehichletypeList.addAll(vehicleTypeMasterRepository.findAll());
				
		List<VehicleTypeMaster> allData = vehichletypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(VehicleTypeMaster::getVehtype_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<VehicleTypeMaster> getVehicleTypeNCList()
	{
		List<VehicleTypeMaster> vehichletypeList=new ArrayList<VehicleTypeMaster>();
		vehichletypeList.addAll(vehicleTypeMasterRepository.getVehicleTypeNCList());
				
		List<VehicleTypeMaster> allData = vehichletypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(VehicleTypeMaster::getVehtype_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public VehicleTypeMaster findOne(long id)
	{
		Optional<VehicleTypeMaster> op=this.vehicleTypeMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public VehicleTypeMaster update(VehicleTypeMaster vehicletype,long id)
	{
		Optional<VehicleTypeMaster> op =vehicleTypeMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		vehicletype.setVehtype_id(op.get().getVehtype_id());
		vehicletype.setModified_type("UPDATED");
		vehicletype.setInserted_by(op.get().getInserted_by());
		vehicletype.setInserted_on(op.get().getInserted_on());
		vehicletype.setUpdated_by(userRepository.getUserDetails(vehicletype.getUsername()).getName());
		vehicletype.setUpdated_on(ldt);
		vehicletype.setDeleted_by("NA");
		vehicletype.setDeleted_on(ldt);
		
		if(vehicletype.getBusinessunit_code().compareTo("0") !=0 && vehicletype.getBusinessunit_code().compareTo("") !=0 && vehicletype.getBusinessunit_code() !=null) {
			vehicletype.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(vehicletype.getBusinessunit_code()).getBusinessunit_name());
		}else {vehicletype.setBusinessunit_name("None");}
		
		if(op.isPresent())
		{
			vehicletype.setId(id);
		}
		return vehicleTypeMasterRepository.save(vehicletype);
	}
	
	@Transactional
	public VehicleTypeMaster deleteVehicleType(VehicleTypeMaster vehicletype,long id)
	{
		Optional<VehicleTypeMaster> op = vehicleTypeMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		VehicleTypeMaster vehicleMaster=op.get();

		vehicleMaster.setInserted_by(op.get().getInserted_by());
		vehicleMaster.setInserted_on(op.get().getInserted_on());
		vehicleMaster.setUpdated_by(op.get().getUpdated_by());
		vehicleMaster.setUpdated_on(op.get().getUpdated_on());
		vehicleMaster.setDeleted_by(userRepository.getUserDetails(vehicletype.getUsername()).getName());
		vehicleMaster.setDeleted_on(ldt);
		vehicleMaster.setModified_type("DELETED");
		
		if(op.isPresent())
		{
			vehicleMaster.setId(id);
		}
		return vehicleTypeMasterRepository.save(vehicleMaster);
	}
	
	public List<VehicleTypeMasterDTO> getVehicleTNameCode() {
		
		List<VehicleTypeMaster> vehichletypeList=new ArrayList<VehicleTypeMaster>();
		vehichletypeList.addAll(vehicleTypeMasterRepository.findAll());
				
		List<VehicleTypeMaster> allData = vehichletypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(VehicleTypeMaster::getVehtype_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<VehicleTypeMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<VehicleTypeMasterDTO> vehicleList= new ModelMapper().map(allData,listType);
		
		return vehicleList;
	}
	
	public VehicleTypeMasterDTO getVehicleTypeNByCode(String code)
	{
		VehicleTypeMaster modelList=vehicleTypeMasterRepository.getVehicleTypeNByCode(code);
		
		Type listType = new TypeToken<VehicleTypeMasterDTO>() {}.getType();
		
		VehicleTypeMasterDTO vehicleName = new ModelMapper().map(modelList,listType);
		
		return vehicleName;
	}
	
	public List<VehicleTypeMaster> findVehicleType(String searchtext)
	{
		List<VehicleTypeMaster> vehicleList=new ArrayList<VehicleTypeMaster>();
		vehicleList.addAll(vehicleTypeMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<VehicleTypeMaster> allData = vehicleList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(VehicleTypeMaster::getVehtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<VehicleTypeMaster> allData = vehicleList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getVehtype_name()+c.getVehtype_remarks()+c.getBusinessunit_name()+c.getNoofwheels()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(VehicleTypeMaster::getVehtype_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
