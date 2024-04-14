package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Production_planning;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_date_details_static;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls_static;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planningRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_date_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_dtls_staticRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Production_planning_special_dtls_staticService_Imp implements Production_planning_special_dtls_staticService{

	@Autowired
	Production_planning_special_dtls_staticRepository production_planning_special_dtls_staticRepository;
	
	@Autowired
	Production_planningRepository production_planningRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Production_planning_special_dtlsRepository production_planning_special_dtlsRepository;
	
	@Autowired
	Production_planning_special_date_detailsRepository production_planning_special_date_detailsRepository;
	
	@Transactional
	public Production_planning_special_dtls_static saveProdPlanSpl(Production_planning_special_dtls_static pSpecial) throws JsonParseException, JsonMappingException, IOException 
	{
		Production_planning op = production_planningRepository.findByProdPlanId(pSpecial.getProd_plan_id());
		LocalDateTime ldt = LocalDateTime.now();
		
		//For Special Process ********
		
		//Update
		production_planning_special_dtlsRepository.updateProduction_planning_special_dtls_static(pSpecial.getProd_plan_id());
				
			if(pSpecial.getProcess().compareTo("0") !=0 && pSpecial.getProcess().compareTo("") !=0 && pSpecial.getProcess() !=null) {
				pSpecial.setProcess_name(process_masterRepository.getProcessDetails(pSpecial.getProcess()).getProcess_desc());
			}else {pSpecial.setProcess_name("None");}
			
			if(pSpecial.getProduction().compareTo("0") !=0 && pSpecial.getProduction().compareTo("") !=0 && pSpecial.getProduction() !=null) {
				pSpecial.setProduction_name(bom_masterRepository.getBomDetails(pSpecial.getProduction()).getProd_desc());
			}else {pSpecial.setProduction_name("None");}
			
			pSpecial.setProd_plan_code(op.getProd_plan_code());
			pSpecial.setProd_plan_id(op.getProd_plan_id());
			pSpecial.setBusiness_unit(op.getBusiness_unit());
			pSpecial.setCompany_id(op.getCompany_id());
			pSpecial.setFin_year(op.getFin_year());
			pSpecial.setModified_type("INSERTED");
			pSpecial.setInserted_by(op.getInserted_by());
			pSpecial.setInserted_on(op.getInserted_on());
			pSpecial.setUpdated_by(op.getUpdated_by());
			pSpecial.setUpdated_on(op.getUpdated_on());
			pSpecial.setDeleted_by("NA");
			pSpecial.setDeleted_on(ldt);
			
			int z=pSpecial.getSl_no();
			pSpecial.setPps_id(op.getProd_plan_id()+Long.toString(z));
			
			//For special process popup date details ********
			
			//Update
			production_planning_special_date_detailsRepository.updateProduction_planning_special_date_details_static(op.getProd_plan_id());
			
			Set<Production_planning_special_date_details_static> pSpecial_date_details=new HashSet<Production_planning_special_date_details_static>();
			
			System.out.println("Got Process Date:>> "+pSpecial.getProcess_date());
			
			if(pSpecial.getProcess_date()==null || pSpecial.getProcess_date().compareTo("")==0) {
				pSpecial.setProcess_date("{\"special_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\"}]}");
			}
			
			String inptData=pSpecial.getProcess_date().replace("{\"special_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_special_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_special_date_details_static[] pSpecial_date_dtls= new ObjectMapper().readValue(inptData, Production_planning_special_date_details_static[].class);
			
			Set<Production_planning_special_date_details_static> set2 = new HashSet<>(Arrays.asList(pSpecial_date_dtls));
			pSpecial_date_details.addAll(set2);
			
			for(Production_planning_special_date_details_static pSplDateDtls:pSpecial_date_details) 
			{
				pSplDateDtls.setProd_plan_code(op.getProd_plan_code());
				pSplDateDtls.setProd_plan_id(op.getProd_plan_id());
				pSplDateDtls.setPpsplid(pSpecial.getPps_id());
				
				pSplDateDtls.setCompany_id(pSpecial.getCompany_id());
				pSplDateDtls.setFin_year(pSpecial.getFin_year());
				pSplDateDtls.setModified_type("INSERTED");
				pSplDateDtls.setInserted_by(pSpecial.getInserted_by());
				pSplDateDtls.setInserted_on(pSpecial.getInserted_on());
				pSplDateDtls.setUpdated_by(pSpecial.getUpdated_by());
				pSplDateDtls.setUpdated_on(pSpecial.getUpdated_on());
				pSplDateDtls.setDeleted_by("NA");
				pSplDateDtls.setDeleted_on(ldt);
			}
			pSpecial.setSpecial_date_details(pSpecial_date_details);
		
		return production_planning_special_dtls_staticRepository.save(pSpecial);
	}
	
	@Transactional
	public Production_planning_special_dtls_static update(Production_planning_special_dtls_static pSpecial,String prodid,int slno) throws JsonParseException, JsonMappingException, IOException 
	{
		
		//Update
		production_planning_special_dtlsRepository.updateProduction_planning_special_dtls_static(pSpecial.getProd_plan_id(),pSpecial.getPps_id());
		
		LocalDateTime ldt = LocalDateTime.now();
		Production_planning op = production_planningRepository.findByProdPlanId(pSpecial.getProd_plan_id());
		
		if(pSpecial.getProcess().compareTo("0") !=0 && pSpecial.getProcess().compareTo("") !=0 && pSpecial.getProcess() !=null) {
			pSpecial.setProcess_name(process_masterRepository.getProcessDetails(pSpecial.getProcess()).getProcess_desc());
		}else {pSpecial.setProcess_name("None");}
		
		if(pSpecial.getProduction().compareTo("0") !=0 && pSpecial.getProduction().compareTo("") !=0 && pSpecial.getProduction() !=null) {
			pSpecial.setProduction_name(bom_masterRepository.getBomDetails(pSpecial.getProduction()).getProd_desc());
		}else {pSpecial.setProduction_name("None");}
		
		pSpecial.setProd_plan_code(op.getProd_plan_code());
		pSpecial.setProd_plan_id(op.getProd_plan_id());
		pSpecial.setBusiness_unit(op.getBusiness_unit());
		pSpecial.setCompany_id(op.getCompany_id());
		pSpecial.setFin_year(op.getFin_year());
		pSpecial.setModified_type("INSERTED");
		pSpecial.setInserted_by(op.getInserted_by());
		pSpecial.setInserted_on(op.getInserted_on());
		pSpecial.setUpdated_by(op.getUpdated_by());
		pSpecial.setUpdated_on(op.getUpdated_on());
		pSpecial.setDeleted_by("NA");
		pSpecial.setDeleted_on(ldt);
		
		int z=pSpecial.getSl_no();
		pSpecial.setPps_id(op.getProd_plan_id()+Long.toString(z));
		
		//For special process popup date details ********
		
		//Update
		production_planning_special_date_detailsRepository.updateProduction_planning_special_date_details_static(pSpecial.getProd_plan_id(),pSpecial.getPps_id());
		
		Set<Production_planning_special_date_details_static> pSpecial_date_details=new HashSet<Production_planning_special_date_details_static>();
		
		if(pSpecial.getProcess_date().compareTo("")==0) {
			pSpecial.setProcess_date("{\"special_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\"}]}");
		}
		
		String inptData=pSpecial.getProcess_date().replace("{\"special_date_details\":", "");
		inptData=inptData.substring(0, inptData.length()-1);
		
		//Production_planning_special_date_details is class.
		//inptData must be mapped with the class's entity
		@SuppressWarnings("unchecked")
		Production_planning_special_date_details_static[] pSpecial_date_dtls= new ObjectMapper().readValue(inptData, Production_planning_special_date_details_static[].class);
		
		Set<Production_planning_special_date_details_static> set2 = new HashSet<>(Arrays.asList(pSpecial_date_dtls));
		pSpecial_date_details.addAll(set2);
		
		for(Production_planning_special_date_details_static pSplDateDtls:pSpecial_date_details) 
		{
			pSplDateDtls.setProd_plan_code(op.getProd_plan_code());
			pSplDateDtls.setProd_plan_id(op.getProd_plan_id());
			pSplDateDtls.setPpsplid(pSpecial.getPps_id());
			
			pSplDateDtls.setCompany_id(pSpecial.getCompany_id());
			pSplDateDtls.setFin_year(pSpecial.getFin_year());
			pSplDateDtls.setModified_type("INSERTED");
			pSplDateDtls.setInserted_by(pSpecial.getInserted_by());
			pSplDateDtls.setInserted_on(pSpecial.getInserted_on());
			pSplDateDtls.setUpdated_by(pSpecial.getUpdated_by());
			pSplDateDtls.setUpdated_on(pSpecial.getUpdated_on());
			pSplDateDtls.setDeleted_by("NA");
			pSplDateDtls.setDeleted_on(ldt);
		}
		pSpecial.setSpecial_date_details(pSpecial_date_details);
		
		return production_planning_special_dtls_staticRepository.save(pSpecial);
	}
}
