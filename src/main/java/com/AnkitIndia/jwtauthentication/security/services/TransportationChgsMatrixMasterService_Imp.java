package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
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

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;
import com.AnkitIndia.jwtauthentication.model.Transportation_chgs_matrix_details;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.TransportationChgsMatrixMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Transportation_chgs_matrix_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TransportationChgsMatrixMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transportation_chgs_matrix_detailsDTO;

@Service
public class TransportationChgsMatrixMasterService_Imp implements TransportationChgsMatrixMasterService{
	@Autowired
	TransportationChgsMatrixMasterRepository transportationChgsMatrixMasterRepository;
	
	@Autowired
	Transportation_chgs_matrix_detailsRepository transportation_chgs_matrix_detailsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	public SequenceIdDTO getTCMId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=transportationChgsMatrixMasterRepository.getTCMSequenceId(fprefix,company);
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
	public TransportationChgsMatrixMaster save(TransportationChgsMatrixMaster transportation) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		String prefix="TPM";
		long slno=0;
		if(transportationChgsMatrixMasterRepository.countId(prefix).isPresent() ) {
			slno=Long.parseLong(transportationChgsMatrixMasterRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(transportation.getBusinessunit_code().compareTo("0") !=0 && transportation.getBusinessunit_code().compareTo("") !=0 && transportation.getBusinessunit_code() !=null) {
			transportation.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(transportation.getBusinessunit_code()).getBusinessunit_name());
		}else {transportation.setBusinessunit_name("None");}
		
		transportation.setTcm_id(gen_sno);
		transportation.setModified_type("INSERTED");
		transportation.setInserted_by(userRepository.getUserDetails(transportation.getUsername()).getName());
		transportation.setInserted_on(ldt);
		transportation.setUpdated_by("NA");
		transportation.setUpdated_on(ldt);
		transportation.setDeleted_by("NA");
		transportation.setDeleted_on(ldt);
		
		Set<Transportation_chgs_matrix_details> transChgSet=new HashSet<Transportation_chgs_matrix_details>();
		transChgSet.addAll(transportation.getTransportation_chgs_matrix_details());
		for(Transportation_chgs_matrix_details aanmdts : transChgSet)
		{
			aanmdts.setTcm_id(gen_sno);
			aanmdts.setTcm_code(transportation.getTcm_code());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(transportation.getInserted_by());
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
		}
		
		return transportationChgsMatrixMasterRepository.save(transportation);
	}
	
	public List<TransportationChgsMatrixMaster> findAll()
	{
		List<TransportationChgsMatrixMaster> distList=new ArrayList<TransportationChgsMatrixMaster>();
		distList.addAll(transportationChgsMatrixMasterRepository.findAll());
				
		List<TransportationChgsMatrixMaster> allData = distList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(TransportationChgsMatrixMaster::getTcm_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<TransportationChgsMatrixMaster> getTransportationCMNCList()
	{
		List<TransportationChgsMatrixMaster> distList=new ArrayList<TransportationChgsMatrixMaster>();
		distList.addAll(transportationChgsMatrixMasterRepository.findAll());
				
		List<TransportationChgsMatrixMaster> allData = distList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(TransportationChgsMatrixMaster::getTcm_description))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public TransportationChgsMatrixMaster findOne(long id)
	{
		Optional<TransportationChgsMatrixMaster> op=this.transportationChgsMatrixMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public TransportationChgsMatrixMaster update(TransportationChgsMatrixMaster transportation,long id)
	{
		Optional<TransportationChgsMatrixMaster> op = transportationChgsMatrixMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(transportation.getBusinessunit_code().compareTo("0") !=0 && transportation.getBusinessunit_code().compareTo("") !=0 && transportation.getBusinessunit_code() !=null) {
			transportation.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(transportation.getBusinessunit_code()).getBusinessunit_name());
		}else {transportation.setBusinessunit_name("None");}
		
		transportation.setTcm_id(op.get().getTcm_id());
		transportation.setModified_type("INSERTED");
		transportation.setInserted_by(op.get().getInserted_by());
		transportation.setInserted_on(op.get().getInserted_on());
		transportation.setUpdated_by(userRepository.getUserDetails(transportation.getUsername()).getName());
		transportation.setUpdated_on(ldt);
		transportation.setDeleted_by("NA");
		transportation.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			transportation.setId(id);
		}
		
		transportation_chgs_matrix_detailsRepository.Transportation_chgs_matrix_detailsUpdate(op.get().getTcm_id(),"UPDATED");
		
		Set<Transportation_chgs_matrix_details> transChgSet=new HashSet<Transportation_chgs_matrix_details>();
		transChgSet.addAll(transportation.getTransportation_chgs_matrix_details());
		for(Transportation_chgs_matrix_details aanmdts : transChgSet)
		{
			aanmdts.setTcm_id(op.get().getTcm_id());
			aanmdts.setTcm_code(transportation.getTcm_code());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setFin_year(transportation.getFin_year());
			aanmdts.setCompany_id(transportation.getCompany_id());
			aanmdts.setInserted_by(transportation.getInserted_by());
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
		}
		return transportationChgsMatrixMasterRepository.save(transportation);
	}
	
	public List<TransportationChgsMatrixMasterDTO> getTransChgNameCode() {
		
		List<TransportationChgsMatrixMaster> distList=new ArrayList<TransportationChgsMatrixMaster>();
		distList.addAll(transportationChgsMatrixMasterRepository.findAll());
				
		List<TransportationChgsMatrixMaster> allData = distList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.isTcm_active()==true)
			  .sorted(Comparator.comparing(TransportationChgsMatrixMaster::getTcm_description))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<TransportationChgsMatrixMasterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<TransportationChgsMatrixMasterDTO> tgList = new ModelMapper().map(allData,listType);
		
		return tgList;
	}
	
	public List<Map<String,Object>> getTransChargeCode(String trans_id,String transfrom,String transto,String type)
	{
		TransportationChgsMatrixMaster trans = new TransportationChgsMatrixMaster();
		if(type.compareToIgnoreCase("Sales")==0)
		{	
			trans=transportationChgsMatrixMasterRepository.findactivematrixsale(); // one transchargecode can be applied for sale
		}
		else
		{
		    trans=transportationChgsMatrixMasterRepository.findactivematrixpurchase(); // one transchargecode can be applied for purchase
		}
		return transportationChgsMatrixMasterRepository.getTansChargeCode(trans_id,transfrom,transto,trans.getTcm_id());
	}
	
	@Transactional
	public TransportationChgsMatrixMaster deleteTCMM(TransportationChgsMatrixMaster transportationChgsMatrixMaster,long id)
	{
		Optional<TransportationChgsMatrixMaster> op = transportationChgsMatrixMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		TransportationChgsMatrixMaster tMaster=op.get();
		
		tMaster.setTcm_id(op.get().getTcm_id());
		tMaster.setInserted_by(op.get().getInserted_by());
		tMaster.setInserted_on(op.get().getInserted_on());
		tMaster.setUpdated_by(op.get().getUpdated_by());
		tMaster.setUpdated_on(op.get().getUpdated_on());
		tMaster.setDeleted_by(userRepository.getUserDetails(transportationChgsMatrixMaster.getUsername()).getName());
		tMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			tMaster.setId(id);
		}
		tMaster.setModified_type("DELETED");
		
       transportation_chgs_matrix_detailsRepository.Transportation_chgs_matrix_detailsUpdate(op.get().getTcm_id(),"DELETED");
		
		return transportationChgsMatrixMasterRepository.save(tMaster);
	}
	
	public List<TransportationChgsMatrixMaster> findTransportationChgsMatrixMaster(String searchtext)
	{
		List<TransportationChgsMatrixMaster> distList=new ArrayList<TransportationChgsMatrixMaster>();
		distList.addAll(transportationChgsMatrixMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<TransportationChgsMatrixMaster> allData = distList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(TransportationChgsMatrixMaster::getTcm_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<TransportationChgsMatrixMaster> allData = distList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getTcm_code() +c.getTcm_description()+c.getBusinessunit_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(TransportationChgsMatrixMaster::getTcm_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public List<Transportation_chgs_matrix_detailsDTO> transChrgsMatRetriveList(String t_id)
	{
		List<TransportationChgsMatrixMaster> modelList=new ArrayList<TransportationChgsMatrixMaster>();
		
		modelList.addAll(transportationChgsMatrixMasterRepository.transChrgsMatRetriveList(t_id));
			
		Type listType=new TypeToken<List<Transportation_chgs_matrix_detailsDTO>>() {}.getType();
		
		List<Transportation_chgs_matrix_detailsDTO> transMat=new ModelMapper().map(modelList,listType);
		
		return transMat;
	}
}
