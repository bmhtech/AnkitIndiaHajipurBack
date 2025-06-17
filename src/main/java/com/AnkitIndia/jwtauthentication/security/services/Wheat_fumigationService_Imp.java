package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
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
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation;
import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation_details;
import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation_details_allocate;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheat_fumigationRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheat_fumigation_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheat_fumigation_details_allocateRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Wheat_fumigationService_Imp implements Wheat_fumigationService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Wheat_fumigationRepository wheat_fumigationRepository;
	
	@Autowired
	Wheat_fumigation_detailsRepository wheat_fumigation_detailsRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	Wheat_fumigation_details_allocateRepository wheat_fumigation_details_allocateRepository;
	
	public SequenceIdDTO getFumigationSequenceId(String company)
	{
		int slno=0;String prefix="WFM";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		String sno=wheat_fumigationRepository.countId();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		prefix=prefix+"/"+code+"/";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public List<Map<String,Object>> getWheatFumigationList(String finyear)
	{
		return wheat_fumigationRepository.getWheatFumigationList(finyear);
	}
	
	@Transactional
	public Wheat_fumigation save(Wheat_fumigation fumi) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wheat_fumigationRepository.countId() != null ) {
			slno=Long.parseLong(wheat_fumigationRepository.countId());
		}
		String prefix="WFM";
		String prefix1="";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		String code=companyMasterRepository.getCompanyDetails(fumi.getCompany_id()).getComp_prefix();
		
		prefix1=prefix+"/"+code+"/";
		String gen_sno1=UniqueIDTransaction.uniqueId4(prefix1,slno);
		
		fumi.setFumigation_id(gen_sno);
		fumi.setFumigation_no(gen_sno1);
		
		if(Utility.isNullOrEmpty(fumi.getBusiness_unit())) {
			fumi.setBusinessunit_name(companyBusinessUnitMasterRepository.businessunitbyid(fumi.getBusiness_unit()).getBusinessunit_name());
		}else {fumi.setBusinessunit_name("Na");}
		
		fumi.setCompany_id(fumi.getCompany_id());
		fumi.setFin_year(fumi.getFin_year());
		fumi.setUsername(fumi.getUsername());
		fumi.setModified_type("INSERTED");
		fumi.setInserted_on(ldt);
		fumi.setInserted_by(userRepository.getUserDetails(fumi.getUsername()).getName());
		fumi.setUpdated_by("NA");
		fumi.setUpdated_on(ldt);
		fumi.setDeleted_by("NA");
		fumi.setDeleted_on(ldt);
		
		Set<Wheat_fumigation_details> wheat_fumigation_details=new HashSet<Wheat_fumigation_details>();
		wheat_fumigation_details.addAll(fumi.getWheat_fumigation_details());
		for(Wheat_fumigation_details wfd:wheat_fumigation_details) 
		{
			wfd.setFumigation_id(gen_sno);
			wfd.setFumigation_no(gen_sno1);
			
			if(wfd.getWarehouse().compareTo("0") !=0 && wfd.getWarehouse().compareTo("") !=0 && wfd.getWarehouse() !=null) {
				wfd.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(wfd.getWarehouse()).getWarehouse_name());
			}else {wfd.setWarehouse_name("NA");}
			
			if(Utility.isNullOrEmpty(wfd.getPcmw_sign())) {
				wfd.setPcmw_sign_name(employeeRepository.getEmployee(wfd.getPcmw_sign()).getEmp_name());
			}else {wfd.setPcmw_sign_name("None");}
			
			if(Utility.isNullOrEmpty(wfd.getSupervisor_sign())) {
				wfd.setSupervisor_sign_name(employeeRepository.getEmployee(wfd.getSupervisor_sign()).getEmp_name());
			}else {wfd.setSupervisor_sign_name("None");}
			
			if(Utility.isNullOrEmpty(wfd.getLab_sign())) {
				wfd.setLab_sign_name(employeeRepository.getEmployee(wfd.getLab_sign()).getEmp_name());
			}else {wfd.setLab_sign_name("None");}
			
			wfd.setCompany_id(fumi.getCompany_id());
			wfd.setFin_year(fumi.getFin_year());
			wfd.setUsername(fumi.getUsername());
			wfd.setModified_type("INSERTED");
			wfd.setInserted_by(fumi.getInserted_by());
			wfd.setInserted_on(ldt);
			wfd.setUpdated_by("NA");
			wfd.setUpdated_on(ldt);
			wfd.setDeleted_by("NA");
			wfd.setDeleted_on(ldt);
			wfd.setStack_use("No");
			wfd.setAllocate_status("NA");
		}
		fumi.setWheat_fumigation_details(wheat_fumigation_details);
		
		return wheat_fumigationRepository.save(fumi);
	}
	
	@Transactional
	public Wheat_fumigation update(Wheat_fumigation fumi,long id)
	{
		Optional<Wheat_fumigation> op =wheat_fumigationRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		fumi.setFumigation_id(op.get().getFumigation_id());
		fumi.setFumigation_no(op.get().getFumigation_no());
		
		if(Utility.isNullOrEmpty(fumi.getBusiness_unit())) {
			fumi.setBusinessunit_name(companyBusinessUnitMasterRepository.businessunitbyid(fumi.getBusiness_unit()).getBusinessunit_name());
		}else {fumi.setBusinessunit_name("Na");}

		fumi.setCompany_id(fumi.getCompany_id());
		fumi.setFin_year(fumi.getFin_year());
		fumi.setUsername(fumi.getUsername());
		fumi.setModified_type("INSERTED");
		fumi.setInserted_on(op.get().getInserted_on());
		fumi.setInserted_by(op.get().getInserted_by());
		fumi.setUpdated_by(userRepository.getUserDetails(fumi.getUsername()).getName());
		fumi.setUpdated_on(ldt);
		fumi.setDeleted_by("NA");
		fumi.setDeleted_on(ldt);
		
		//Update
		wheat_fumigation_detailsRepository.updateWheat_fumigation_details(op.get().getFumigation_id());
		
		Set<Wheat_fumigation_details> wheat_fumigation_details=new HashSet<Wheat_fumigation_details>();
		wheat_fumigation_details.addAll(fumi.getWheat_fumigation_details());
		for(Wheat_fumigation_details wfd:wheat_fumigation_details) 
		{
			wfd.setFumigation_id(op.get().getFumigation_id());
			wfd.setFumigation_no(op.get().getFumigation_no());
			
			if(wfd.getWarehouse().compareTo("0") !=0 && wfd.getWarehouse().compareTo("") !=0 && wfd.getWarehouse() !=null) {
				wfd.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(wfd.getWarehouse()).getWarehouse_name());
			}else {wfd.setWarehouse_name("NA");}
			
			if(Utility.isNullOrEmpty(wfd.getPcmw_sign())) {
				wfd.setPcmw_sign_name(employeeRepository.getEmployee(wfd.getPcmw_sign()).getEmp_name());
			}else {wfd.setPcmw_sign_name("None");}
			
			if(Utility.isNullOrEmpty(wfd.getSupervisor_sign())) {
				wfd.setSupervisor_sign_name(employeeRepository.getEmployee(wfd.getSupervisor_sign()).getEmp_name());
			}else {wfd.setSupervisor_sign_name("None");}
			
			if(Utility.isNullOrEmpty(wfd.getLab_sign())) {
				wfd.setLab_sign_name(employeeRepository.getEmployee(wfd.getLab_sign()).getEmp_name());
			}else {wfd.setLab_sign_name("None");}
			
			wfd.setCompany_id(fumi.getCompany_id());
			wfd.setFin_year(fumi.getFin_year());
			wfd.setUsername(fumi.getUsername());
			wfd.setModified_type("INSERTED");
			wfd.setInserted_by(fumi.getInserted_by());
			wfd.setInserted_on(fumi.getInserted_on());
			wfd.setUpdated_by(fumi.getUpdated_by());
			wfd.setUpdated_on(fumi.getUpdated_on());
			wfd.setDeleted_by("NA");
			wfd.setDeleted_on(ldt);
			wfd.setStack_use("No");
			wfd.setAllocate_status("NA");
		}
		fumi.setWheat_fumigation_details(wheat_fumigation_details);
		
		return wheat_fumigationRepository.save(fumi);
	}
	
	@Transactional
	public Wheat_fumigation delete(Wheat_fumigation wheat_fumigation,long id)
	{
		Optional<Wheat_fumigation> op = wheat_fumigationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Wheat_fumigation fumi=op.get();
		
		fumi.setInserted_by(op.get().getInserted_by());
		fumi.setInserted_on(op.get().getInserted_on());
		fumi.setUpdated_by(op.get().getUpdated_by());
		fumi.setUpdated_on(op.get().getUpdated_on());
		fumi.setDeleted_by(userRepository.getUserDetails(fumi.getUsername()).getName());
		fumi.setDeleted_on(ldt);
		if(op.isPresent())
		{
			fumi.setId(id);
		}
		fumi.setModified_type("DELETED");
		
		wheat_fumigation_detailsRepository.wheatFumigationDetailsUpdate(op.get().getFumigation_id());
		
	   return wheat_fumigationRepository.save(fumi);
		
	}
	
	public Wheat_fumigation findOne(long id)
	{
		Optional<Wheat_fumigation> op=this.wheat_fumigationRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String,Object>> getWheatFumigationDetails(String requi_id)
	{
		return wheat_fumigation_detailsRepository.getWheatFumigationDetails(requi_id); 
	}
	
	public List<Map<String,Object>> getAllWheatFumiDtlsList(String type)
	{
		if(type.compareToIgnoreCase("fumigated stack")==0)
		{
			return wheat_fumigation_detailsRepository.getAllWheatFumiDtlsUnusedList();
		}
		else if(type.compareToIgnoreCase("stack open after fumigation")==0)
		{
			return wheat_fumigation_detailsRepository.getAllWheatFumiDtlsUsedList();
		}
		else
		{
			return wheat_fumigation_detailsRepository.getAllWheatFumiDtlsAllList();
		}
		
	}

	@Transactional
	 public StatusDTO updateWheatFumiDetails(long id,String fumigation_id,String allocate_date,String company,
			 String finyear,String user,String action,String allocate_st,String pcmw_sign_name,String supervisor_sign_name,
			 String lab_sign_name,String manpower,String degassing_date,String degassing_time,String wheat_fumi_qc)
	 {
		StatusDTO result = new StatusDTO();
		String stack_use="";
		if(action.compareToIgnoreCase("Save")==0)
		{stack_use="Yes";}
		else {stack_use="No";}
		//allocate_date=null;}
		
		wheat_fumigation_detailsRepository.updateFumigationDetails(id,fumigation_id,allocate_date,stack_use,action,allocate_st,wheat_fumi_qc);
		
		LocalDateTime ldt = LocalDateTime.now();
		Wheat_fumigation_details_allocate allocate=new Wheat_fumigation_details_allocate();
		
		allocate.setDetails_table_id(id);
		allocate.setFumigation_id(fumigation_id);
		allocate.setAllocate_date(allocate_date);
		allocate.setCompany_id(company);
		allocate.setFin_year(finyear);
		allocate.setUsername(user);
		allocate.setUpdated_by(userRepository.getUserDetails(user).getName());
		allocate.setUpdated_on(ldt);
		allocate.setModified_type(action);
		allocate.setStatus(stack_use);
		allocate.setAllocate_status(allocate_st);
		allocate.setPcmw_sign_name(pcmw_sign_name);
		allocate.setSupervisor_sign_name(supervisor_sign_name);
		allocate.setLab_sign_name(lab_sign_name);
		allocate.setManpower(manpower);
		allocate.setDegassing_date(degassing_date);	
		allocate.setDegassing_time(degassing_time);
		allocate.setWheat_fumi_qc(wheat_fumi_qc);
		
		wheat_fumigation_details_allocateRepository.save(allocate);
		
		result.setStatus("Yes");
		
		return result;
	 }
	
}
