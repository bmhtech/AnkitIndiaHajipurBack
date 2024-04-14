package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
import com.AnkitIndia.jwtauthentication.model.Company_licence_details;
import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.repository.Branch_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Company_licence_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.GroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Parent_controlRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class CompanyMasterService_Imp implements CompanyMasterService {
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	Branch_masterRepository branch_masterRepository;

	@Autowired
	GroupmasterRepository groupmasterRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	Parent_controlRepository parent_controlRepository;
	
	@Autowired
	Company_licence_detailsRepository company_licence_detailsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Transactional
	public Company_master save(Company_master company) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="CM";
		if(companyMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(companyMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		company.setCompany_id(gen_sno);
		company.setModified_type("INSERTED");
		company.setInserted_by(userRepository.getUserDetails(company.getUsername()).getName());
		company.setInserted_on(ldt);
		company.setUpdated_by("NA");
		company.setUpdated_on(ldt);
		company.setDeleted_by("NA");
		company.setDeleted_on(ldt);
		
		Set<Company_licence_details> compLicSet = new HashSet<Company_licence_details>();
		compLicSet.addAll(company.getCompany_licence_details());
		for(Company_licence_details licDtls : compLicSet)
		{
			licDtls.setCompany_id(gen_sno);
			licDtls.setModified_type("INSERTED");
			licDtls.setInserted_by(company.getInserted_by());
			licDtls.setInserted_on(ldt);
			licDtls.setUpdated_by("NA");
			licDtls.setUpdated_on(ldt);
			licDtls.setDeleted_by("NA");
			licDtls.setDeleted_on(ldt);
		}
		company.setCompany_licence_details(compLicSet);
		/*
		//Group,Sub Group,Parent Control Save *******************************************************************
		String grpuniquecode="";
		int grpunicount=0;
		if(groupmasterRepository.getMaxOfGroups() != null ) {
			grpunicount=Integer.parseInt(groupmasterRepository.getMaxOfGroups());
		}
		grpuniquecode="GRP"+Utility.serialNoGenarate(grpunicount);
		
		String subuniquecode="";
		int subunicount=0;
		if(subgroupmasterRepository.getMaxOfSubGroups() != null ) {
			subunicount=Integer.parseInt(subgroupmasterRepository.getMaxOfSubGroups());
		}
		subuniquecode="SUB"+Utility.serialNoGenarate(subunicount);
		
		Branch_master branchMaster=new Branch_master(gen_sno,company.getCompany_name(),gen_sno,company.getCompany_name(),
				company.getState_code(),companyBusiness.getCity_name(),Long.toString(companyBusiness.getMobile_no()),companyBusiness.getGstin_no(),
				companyBusiness.getModified_type(),ldt,companyBusiness.getInserted_by(),ldt,"NA",companyBusiness.getFin_year(),companyBusiness.getUsername());
		
		branch_masterRepository.save(branchMaster);
		
		//Group Master Default Data *************************************
		
		List<Groupmaster> grps=Utility.GroupmasterInsert(gen_sno, companyBusiness.getUsername(), companyBusiness.getFin_year(), grpunicount);
		for(Groupmaster grpList:grps) {
			grpList.setClassy("");
			grpList.setStatus("INSERTED");
			grpList.setInserted_by(companyBusiness.getInserted_by());
			grpList.setInserted_on(ldt);
			grpList.setInserted_location("");
			grpList.setModified_by("NA");
			grpList.setModified_on(ldt);
			grpList.setModified_location("");
			grpList.setFin_year(companyBusiness.getFin_year());
			grpList.setUsername(companyBusiness.getUsername());
		}
		groupmasterRepository.saveAll(grps);
		
		List<Parent_control> pControls=Utility.groupParentControl(gen_sno, companyBusiness.getUsername(),  companyBusiness.getFin_year(), grpunicount);
		for(Parent_control pControl:pControls) {
			String code=groupmasterRepository.getGroups(pControl.getBranchcode(),pControl.getCode()).getUniqucode();
			pControl.setCategory_id(code);
			pControl.setStatus("INSERTED");
			pControl.setInserted_by(companyBusiness.getInserted_by());
			pControl.setInserted_on(ldt);
			pControl.setInserted_location("");
			pControl.setModified_by("NA");
			pControl.setModified_on(ldt);
			pControl.setModified_location("");
			pControl.setFin_year(companyBusiness.getFin_year());
			pControl.setUsername(companyBusiness.getUsername());
		}
		parent_controlRepository.saveAll(pControls);
		
		List<Subgroupmaster> subGrps=Utility.SubgroupmasterInsert(gen_sno, companyBusiness.getUsername(),  companyBusiness.getFin_year(), subunicount);
		for(Subgroupmaster subGrp:subGrps) {
			String code=groupmasterRepository.getGroups(subGrp.getBranchcode(),subGrp.getSubgroupcode()).getUniqucode();
			subGrp.setUniqucode(code);
			subGrp.setParent_srno("0");subGrp.setParent_subgroup("");subGrp.setParent_subgroupcode("");
			subGrp.setStatus("INSERTED");
			subGrp.setInserted_by(companyBusiness.getInserted_by());
			subGrp.setInserted_on(ldt);
			subGrp.setInserted_location("");
			subGrp.setModified_by("NA");
			subGrp.setModified_on(ldt);
			subGrp.setModified_location("");
			subGrp.setFin_year(companyBusiness.getFin_year());
			subGrp.setUsername(companyBusiness.getUsername());
		}
		subgroupmasterRepository.saveAll(subGrps);
		
		//Sub Group Master Default Data *************************************
		List<Subgroupmaster> subGrpMaster=Utility.getSubgroupmasterDataSet(gen_sno, companyBusiness.getUsername(), companyBusiness.getFin_year(), subunicount, ldt);
		for(Subgroupmaster subGrp:subGrpMaster) {
			//System.err.println("GOT CODE:: >>> "+subGrp.getBranchcode()+" , "+subGrp.getSubgroupcode());
			//String code=groupmasterRepository.getGroups(subGrp.getBranchcode(),subGrp.getSubgroupcode()).getUniqucode();
			subGrp.setParent_srno("0");subGrp.setParent_subgroup("");subGrp.setParent_subgroupcode("");
			subGrp.setStatus("INSERTED");
			subGrp.setInserted_by(companyBusiness.getInserted_by());
			subGrp.setInserted_on(ldt);
			subGrp.setInserted_location("");
			subGrp.setModified_by("NA");
			subGrp.setModified_on(ldt);
			subGrp.setModified_location("");
			subGrp.setFin_year(companyBusiness.getFin_year());
			subGrp.setUsername(companyBusiness.getUsername());
		}
		subgroupmasterRepository.saveAll(subGrpMaster);
		
		List<Parent_control> parentConts=Utility.subGroupParentControlDataSet(gen_sno, companyBusiness.getUsername(),  companyBusiness.getFin_year(), subunicount);
		for(Parent_control pControl:parentConts) {
			String code=subgroupmasterRepository.getSubGroupDtls(pControl.getCode(),pControl.getBranchcode()).getUniqucode();
			String parent=groupmasterRepository.getGroups(pControl.getBranchcode(), pControl.getCode().substring(0,2)).getUniqucode();
			
			pControl.setParent(parent);
			pControl.setCategory_id(code);
			pControl.setStatus("INSERTED");
			pControl.setInserted_by(companyBusiness.getInserted_by());
			pControl.setInserted_on(ldt);
			pControl.setInserted_location("");
			pControl.setModified_by("NA");
			pControl.setModified_on(ldt);
			pControl.setModified_location("");
			pControl.setFin_year(companyBusiness.getFin_year());
			pControl.setUsername(companyBusiness.getUsername());
		}
		parent_controlRepository.saveAll(parentConts);*/
		
		return companyMasterRepository.save(company);
	}
	
	public List<Company_master> findAll()
	{
		List<Company_master> compList=new ArrayList<Company_master>();
		compList.addAll(companyMasterRepository.findAll());
				
		List<Company_master> allData = compList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Company_master::getCompany_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Company_master> getCompanyNCCode()
	{
		List<Company_master> compList=new ArrayList<Company_master>();
		compList.addAll(companyMasterRepository.findAll());
				
		List<Company_master> allData = compList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Company_master::getCompany_name))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Company_master findOne(long id)
	{
		Optional<Company_master> op=this.companyMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Company_master update(Company_master company,long id)
	{
		Optional<Company_master> op =companyMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		company.setCompany_id(op.get().getCompany_id());
		company.setModified_type("INSERTED");
		company.setInserted_by(op.get().getInserted_by());
		company.setInserted_on(op.get().getInserted_on());
		company.setUpdated_by(userRepository.getUserDetails(company.getUsername()).getName());
		company.setUpdated_on(ldt);
		company.setDeleted_by("NA");
		company.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			company.setId(id);
		}
		
		company_licence_detailsRepository.updateCompany_licence_details(company.getCompany_id(),"UPDATED");
		
		Set<Company_licence_details> aacNormsSet = new HashSet<Company_licence_details>();
		aacNormsSet.addAll(company.getCompany_licence_details());
		for(Company_licence_details aanmdts : aacNormsSet)
		{
			aanmdts.setCompany_id(company.getCompany_id());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(company.getInserted_by());
			aanmdts.setInserted_on(company.getInserted_on());
			aanmdts.setUpdated_by(company.getUpdated_by());
			aanmdts.setUpdated_on(company.getUpdated_on());
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
		}
		company.setCompany_licence_details(aacNormsSet);
		
		return companyMasterRepository.save(company);
	}
	
	@Transactional
	public Company_master deleteCompany(Company_master company,long id)
	{
		Optional<Company_master> op =companyMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Company_master cMaster=op.get();
		
		cMaster.setCompany_id(op.get().getCompany_id());
		cMaster.setInserted_by(op.get().getInserted_by());
		cMaster.setInserted_on(op.get().getInserted_on());
		cMaster.setUpdated_by(op.get().getUpdated_by());
		cMaster.setUpdated_on(op.get().getUpdated_on());
		cMaster.setDeleted_by(userRepository.getUserDetails(company.getUsername()).getName());
		cMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			cMaster.setId(id);
		}

		if(companyBusinessUnitMasterRepository.getCBUDtlsThruCompany(cMaster.getCompany_id()).isPresent()) {
			return company;
		}else {
			cMaster.setModified_type("DELETED");
			company_licence_detailsRepository.updateCompany_licence_details(op.get().getCompany_id(),"DELETED");
			
			return companyMasterRepository.save(cMaster);
		}
	}
	
	public List<CompanyMasterDTO> getCompanyNameCode() {
		List<Company_master> compList=new ArrayList<Company_master>();
		compList.addAll(companyMasterRepository.findAll());
				
		List<Company_master> allData = compList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Company_master::getCompany_name))
			  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<CompanyMasterDTO>>() {}.getType();
		List<CompanyMasterDTO> companyList = new ModelMapper().map(allData,listType);
		
		return companyList;
	}
	
	public List<Company_licence_detailsDTO> compLiceRetriveList(String cp_id)
	{
		List<Company_licence_details> cLicences=new ArrayList<Company_licence_details>();
		cLicences.addAll(company_licence_detailsRepository.findAll());
				
		List<Company_licence_details> allData = cLicences
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(cp_id))
			  .sorted(Comparator.comparing(Company_licence_details::getSln_no))
			  .collect(Collectors.toList());
			
		Type listType=new TypeToken<List<Company_licence_detailsDTO>>() {}.getType();
		List<Company_licence_detailsDTO> compliceList=new ModelMapper().map(allData,listType);
		
		return compliceList;
	}
	
	public SequenceIdDTO getCompanySequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=companyMasterRepository.getCompanySequenceId(fprefix,company);
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
	
	public List<Company_master> findCompanys(String searchtext)
	{
		List<Company_master> companyList=new ArrayList<Company_master>();
		companyList.addAll(companyMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Company_master> allData = companyList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Company_master::getCompany_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Company_master> allData = companyList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getCompany_name()+c.getCompany_code()+c.getComp_mailing_name()+c.getCompany_type()+c.getMobile_no()+c.getEmail_id()+c.getWebsite_name()+c.getPin_code()+c.getGstin_no()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Company_master::getCompany_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	 public Company_master getCompanyDetails (String compid)
	 {
		 System.out.println(" compid :: " + compid);
		 Company_master compdetails= companyMasterRepository.getCompanyDetails(compid);
		
		 return compdetails;
	 }
	 
}
