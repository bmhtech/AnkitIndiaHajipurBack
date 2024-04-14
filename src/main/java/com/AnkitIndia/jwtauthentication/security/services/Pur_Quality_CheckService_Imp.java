package com.AnkitIndia.jwtauthentication.security.services;


import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_time_service;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details_QcDetails;
import com.AnkitIndia.jwtauthentication.model.Ratechart;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Quality_CheckRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quality_Check_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_docDTO;

@Service
public class Pur_Quality_CheckService_Imp implements Pur_Quality_CheckService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_Quality_CheckRepository pur_Quality_CheckRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Transactional
	public Pur_Quality_Check save(Pur_Quality_Check pqc) {
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		
		if(pur_Quality_CheckRepository.countId() != null)
		{
			slno=Long.parseLong(pur_Quality_CheckRepository.countId());
		}
		String prefix="PQC";
		
		String gen_sno=UniqueID.uniqueid(prefix, slno);
		
		if(Utility.isNullOrEmpty(pqc.getSupplier_name())) {
			pqc.setSupplier(supp_bussiness_partnerRepository.getSupplierName(pqc.getSupplier_name()).getBp_name());
		}else {pqc.setSupplier("None");}
		
		if(Utility.isNullOrEmpty(pqc.getBusiness_unit())) {
			pqc.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(pqc.getBusiness_unit()).getBusinessunit_name());
		}else {pqc.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(pqc.getVehicle_id())) {
			pqc.setVehicle_name(vehicleMasterRepository.getVehicleDetails(pqc.getVehicle_id()).getVehicle_no());
		}else {pqc.setVehicle_name("None");}
		
		pqc.setQuality_check_id(gen_sno);
		pqc.setCompany_id(pqc.getCompany_id());
		pqc.setFin_year(pqc.getFin_year());
		pqc.setModified_type("INSERTED");
		pqc.setInserted_by(userRepository.getUserDetails(pqc.getUsername()).getName());
		pqc.setInserted_on(ldt);
		pqc.setUpdated_by("NA");
		pqc.setUpdated_on(ldt);
		pqc.setDeleted_by("NA");
		pqc.setDeleted_on(ldt);
		
		if(pqc.getPer_obs_status().compareToIgnoreCase("Done")==0)	
		{
			wm_unload_adviceRepository.updateUnloadQcStatus(pqc.getReferenceid());
		}
		
		Set<Pur_Quality_Check_Details> aacNormsSet=new HashSet<Pur_Quality_Check_Details>();
		aacNormsSet.addAll(pqc.getPur_Quality_Check_Details());
		for(Pur_Quality_Check_Details aanmdts:aacNormsSet)
		{
			if(Utility.isNullOrEmpty(aanmdts.getItem_code())) {
				aanmdts.setItem_name(item_masterRepository.itemNameById(aanmdts.getItem_code()).getItem_name());
			}else {aanmdts.setItem_name("None");}
			
			if(Utility.isNullOrEmpty(aanmdts.getPacking())) {
				aanmdts.setPacking_name(item_masterRepository.itemNameById(aanmdts.getPacking()).getItem_name());
			}else {aanmdts.setPacking_name("None");}
			
			
			aanmdts.setQuality_check_id(gen_sno);
			aanmdts.setQcno(pqc.getQcno());
			aanmdts.setCompany_id(pqc.getCompany_id());
			aanmdts.setFin_year(pqc.getFin_year());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(userRepository.getUserDetails(pqc.getUsername()).getName());
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
			
			Set<Pur_Quality_Check_Details_QcDetails> qcdetails=new HashSet<Pur_Quality_Check_Details_QcDetails>();
			qcdetails.addAll(aanmdts.getPur_Quality_Check_Details_QcDetails());
			for(Pur_Quality_Check_Details_QcDetails qcDetails:qcdetails) 
			{
				if(Utility.isNullOrEmpty(aanmdts.getItem_code())) {
					qcDetails.setItem_name(item_masterRepository.itemNameById(aanmdts.getItem_code()).getItem_name());
				}else {qcDetails.setItem_name("None");}
				
				if(Utility.isNullOrEmpty(aanmdts.getPacking())) {
					qcDetails.setPacking_name(item_masterRepository.itemNameById(aanmdts.getPacking()).getItem_name());
				}else {qcDetails.setPacking_name("None");}
				
				qcDetails.setQuality_check_id(gen_sno);
				qcDetails.setItem_code(aanmdts.getItem_code());
				qcDetails.setPacking(aanmdts.getPacking());
				qcDetails.setCompany_id(pqc.getCompany_id());
				qcDetails.setFin_year(pqc.getFin_year());
				qcDetails.setModified_type("INSERTED");
				qcDetails.setInserted_by(pqc.getInserted_by());
				qcDetails.setInserted_on(pqc.getInserted_on());
				qcDetails.setUpdated_by(pqc.getUpdated_by());
				qcDetails.setUpdated_on(pqc.getUpdated_on());
				qcDetails.setDeleted_by("NA");
				qcDetails.setDeleted_on(ldt);
			}
			aanmdts.setPur_Quality_Check_Details_QcDetails(qcdetails);
			
			
		}
		
		pqc.setPur_Quality_Check_Details(aacNormsSet);
		
		
		return pur_Quality_CheckRepository.save(pqc);
	}
	
	public List<Pur_Quality_Check> findAll()
	{
		return pur_Quality_CheckRepository.findAll();
	}
	
	
	public Pur_Quality_Check findOne(Long id)
	{
		Optional<Pur_Quality_Check> purQualityCheckOp=pur_Quality_CheckRepository.findById(id);
		return purQualityCheckOp.get();
	}
	
	@Transactional
	public Pur_Quality_Check update(Pur_Quality_Check pqc,Long id)
	{
		
		Optional<Pur_Quality_Check> op = pur_Quality_CheckRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(pqc.getSupplier_name())) {
			pqc.setSupplier(supp_bussiness_partnerRepository.getSupplierName(pqc.getSupplier_name()).getBp_name());
		}else {pqc.setSupplier("None");}
		
		if(Utility.isNullOrEmpty(pqc.getBusiness_unit())) {
			pqc.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(pqc.getBusiness_unit()).getBusinessunit_name());
		}else {pqc.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(pqc.getVehicle_id())) {
			pqc.setVehicle_name(vehicleMasterRepository.getVehicleDetails(pqc.getVehicle_id()).getVehicle_no());
		}else {pqc.setVehicle_name("None");}
		
		pqc.setQcno(op.get().getQcno());
		pqc.setQuality_check_id(op.get().getQuality_check_id());
		pqc.setModified_type("INSERTED");
		pqc.setInserted_by(op.get().getInserted_by());
		pqc.setInserted_on(op.get().getInserted_on());
		pqc.setUpdated_by(userRepository.getUserDetails(pqc.getUsername()).getName());
		pqc.setUpdated_on(ldt);
		pqc.setDeleted_by("NA");
		pqc.setDeleted_on(ldt);
		
		if(pqc.getPer_obs_status().compareToIgnoreCase("Done")==0)	
		{
			wm_unload_adviceRepository.updateUnloadQcStatus(pqc.getReferenceid());
		}
		
		//Update item
		pur_Quality_CheckRepository.updatePQCDetails(op.get().getQuality_check_id());
		
		Set<Pur_Quality_Check_Details> aacNormsSet=new HashSet<Pur_Quality_Check_Details>();
		aacNormsSet.addAll(pqc.getPur_Quality_Check_Details());
		for(Pur_Quality_Check_Details aanmdts:aacNormsSet) 
		{
			
			if(Utility.isNullOrEmpty(aanmdts.getItem_code())) {
				aanmdts.setItem_name(item_masterRepository.itemNameById(aanmdts.getItem_code()).getItem_name());
			}else {aanmdts.setItem_name("None");}
			
			if(Utility.isNullOrEmpty(aanmdts.getPacking())) {
				aanmdts.setPacking_name(item_masterRepository.itemNameById(aanmdts.getPacking()).getItem_name());
			}else {aanmdts.setPacking_name("None");}
			
			
			aanmdts.setQuality_check_id(op.get().getQuality_check_id());
			aanmdts.setQcno(op.get().getQcno());
			aanmdts.setCompany_id(pqc.getCompany_id());
			aanmdts.setFin_year(pqc.getFin_year());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(pqc.getInserted_by());
			aanmdts.setInserted_on(pqc.getInserted_on());
			aanmdts.setUpdated_by(userRepository.getUserDetails(pqc.getUsername()).getName());
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
			
			System.out.println("Item "+aanmdts.getItem_code()+"PAcking "+aanmdts.getPacking()+" ID "+op.get().getQuality_check_id());
			//pur_Quality_CheckRepository.updatePQCQualityDetails(aanmdts.getQc(),op.get().getQuality_check_id());
			pur_Quality_CheckRepository.updatePQCQualityDetailsupdate(aanmdts.getItem_code(),aanmdts.getPacking(),op.get().getQuality_check_id());
			
			Set<Pur_Quality_Check_Details_QcDetails> qcdetails=new HashSet<Pur_Quality_Check_Details_QcDetails>();
			qcdetails.addAll(aanmdts.getPur_Quality_Check_Details_QcDetails());
			for(Pur_Quality_Check_Details_QcDetails qcDetails:qcdetails) 
			{
				if(Utility.isNullOrEmpty(aanmdts.getItem_code())) {
					qcDetails.setItem_name(item_masterRepository.itemNameById(aanmdts.getItem_code()).getItem_name());
				}else {qcDetails.setItem_name("None");}
				
				if(Utility.isNullOrEmpty(aanmdts.getPacking())) {
					qcDetails.setPacking_name(item_masterRepository.itemNameById(aanmdts.getPacking()).getItem_name());
				}else {qcDetails.setPacking_name("None");}
				
				qcDetails.setQuality_check_id(op.get().getQuality_check_id());
				qcDetails.setItem_code(aanmdts.getItem_code());
				qcDetails.setPacking(aanmdts.getPacking());
				qcDetails.setCompany_id(pqc.getCompany_id());
				qcDetails.setFin_year(pqc.getFin_year());
				qcDetails.setModified_type("INSERTED");
				qcDetails.setInserted_by(pqc.getInserted_by());
				qcDetails.setInserted_on(pqc.getInserted_on());
				qcDetails.setUpdated_by(pqc.getUpdated_by());
				qcDetails.setUpdated_on(pqc.getUpdated_on());
				qcDetails.setDeleted_by("NA");
				qcDetails.setDeleted_on(ldt);
			}
			aanmdts.setPur_Quality_Check_Details_QcDetails(qcdetails);
		}
		pqc.setPur_Quality_Check_Details(aacNormsSet);
		
	return pur_Quality_CheckRepository.save(pqc);
	}
	
	public List<Pur_Quality_Check_DetailsDTO> purQChkRetriveList(String code)
	{
		List<Pur_Quality_Check> modelList=new ArrayList<Pur_Quality_Check>();
		
		modelList.addAll(pur_Quality_CheckRepository.purQChkRetriveList(code));
			
		Type listType=new TypeToken<List<Pur_Quality_Check_DetailsDTO>>() {}.getType();
		
		List<Pur_Quality_Check_DetailsDTO> purQC=new ModelMapper().map(modelList,listType);
		
		return purQC;
	}
	
	public List<Pur_Quality_Check_Details> retriveQualityCheckDetails(String quality_check_id)
	{
		List<Pur_Quality_Check_Details> itemdetails= new ArrayList<Pur_Quality_Check_Details>();
		itemdetails.addAll(pur_Quality_CheckRepository.retriveQualityCheckDetails(quality_check_id));
		return itemdetails;
	}
	
	public List<Pur_Quality_Check_Details_QcDetails> retriveQualityCheckDetailsQcDetails(String qcno,String qcid)
	{
		//System.out.println("qcno:"+qcno+"//"+qcid);
		List<Pur_Quality_Check_Details_QcDetails> serdetails= new ArrayList<Pur_Quality_Check_Details_QcDetails>();
		serdetails.addAll(pur_Quality_CheckRepository.retriveQualityCheckDetailsQcDetails(qcno,qcid));
		return serdetails;
	}
	
	public List<Map<String,Object>> getQClist(String finyear)
	{
		List<Map<String,Object>> qclist = new ArrayList<Map<String,Object>>();
		
		qclist.addAll(pur_Quality_CheckRepository.getQClist(finyear));
		
		return qclist;
	}
	
	public List<Map<String,Object>> searchQC(String fromdate, String todate, String vehicle, String finyear)
	{
		List<Map<String, Object>> qclist=new ArrayList<Map<String, Object>>();
		if(vehicle.compareToIgnoreCase("All") == 0)
 		{
			qclist.addAll(pur_Quality_CheckRepository.searchQCwtDate(fromdate,todate,finyear));
 		}
		else if(fromdate.compareToIgnoreCase("wtfdate") == 0 && todate.compareToIgnoreCase("wttdate") == 0)
 		{
			qclist.addAll(pur_Quality_CheckRepository.searchQCwtVehicle(vehicle,finyear));
 		}
 		else
 		{
 			qclist.addAll(pur_Quality_CheckRepository.searchQC(fromdate,todate,vehicle,finyear));
 		}
		return qclist;
	}
	
	public StatusDTO checkQC(String advice_id)
	 {
		StatusDTO result = new StatusDTO();
		
		result.setStatus(wm_unload_adviceRepository.checkQC(advice_id));
		
		return result;
	 }
	
	@Transactional
	public Pur_Quality_Check delete(Pur_Quality_Check pqc,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Pur_Quality_Check> op = pur_Quality_CheckRepository.findById(id);
		
		Pur_Quality_Check qc=op.get();
		
		qc.setModified_type("DELETED");
		qc.setInserted_by(op.get().getInserted_by());
		qc.setInserted_on(op.get().getInserted_on());
		qc.setUpdated_by(op.get().getUpdated_by());
		qc.setUpdated_on(op.get().getUpdated_on());
		qc.setDeleted_by(userRepository.getUserDetails(pqc.getUsername()).getName());
		qc.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			qc.setId(id);
		}
		
		pur_Quality_CheckRepository.deletePQCDetails(op.get().getQuality_check_id());
		pur_Quality_CheckRepository.deletePQCQualityDetails(op.get().getQuality_check_id());
		
		// update unloading QC
		wm_unload_adviceRepository.updateUnloadAdviceQCStatus(op.get().getReferenceid());		
		return pur_Quality_CheckRepository.save(qc);
	}
	
}
