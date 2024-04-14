package com.AnkitIndia.jwtauthentication.security.services;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.AnkitIndia.jwtauthentication.payload.Response;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.repository.Company_business_unit_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.GroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Parent_controlRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.model.Business_unit;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_details;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.repository.Branch_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Business_unitRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.AnkitIndia.Master.Exception.FileStorageException;
import com.AnkitIndia.Utility.Utility;

import java.io.File;
import java.io.FileOutputStream;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTOC;

@Service
public class CompanyBusinessUnitMasterService_Imp implements CompanyBusinessUnitMasterService {
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	Branch_masterRepository branch_masterRepository;

	@Autowired
	GroupmasterRepository groupmasterRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	Parent_controlRepository parent_controlRepository;

	@Autowired
	Company_business_unit_detailsRepository company_business_unit_detailsRepository;
	
	@Autowired
    CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Post_office_masterRepository post_office_masterRepository;
	
	@Autowired
	Business_unitRepository business_unitRepository;
		
	@Value("${docPdf.upload-dir}")
	private String docPdf;
	
	@Transactional
	public Response fileUpload(@RequestParam("file") MultipartFile file,String fileName,String path,String folder) throws IOException
	{
		File filePath = new File(path+"/CBULicenceDoc");
		boolean value = filePath.mkdir();
	    if(value) {
	      System.out.println("The new directory is created.");
	      File filePath2 = new File(filePath+"/"+folder);
	      boolean value2 = filePath2.mkdir();
	      if(value2) {
	    	  System.out.println("The new directory is created.");
	      }else {
		      System.out.println("The directory already exists.");
		  }
	      filePath=filePath2;
	    }
	    else {
	      System.out.println("The directory already exists.");
	      File filePath2 = new File(filePath+"/"+folder);
	      boolean value2 = filePath2.mkdir();
	      if(value2) {
	    	  System.out.println("The new directory is created.");
	      }else {
		      System.out.println("The directory already exists.");
		  }
	      filePath=filePath2;
	    }
	    System.err.println("File Path: "+filePath);
    	File convertFile = new File(filePath+"/" + fileName.trim());
    	
		convertFile.createNewFile();
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
		try (FileOutputStream fout = new FileOutputStream(convertFile))
		{
			fout.write(file.getBytes());
			
		}
		catch (Exception exe)
		{
			 throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", exe);
		}
		return new Response(file.getOriginalFilename(), fileDownloadUri,file.getContentType(), file.getSize());
	}
	
	@Transactional
	public  Company_business_unit_master save(Company_business_unit_master companyBusiness,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="CBU";
		if(companyBusinessUnitMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(companyBusinessUnitMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		companyBusiness.setBusinessunit_id(gen_sno);
		companyBusiness.setModified_type("INSERTED");
		companyBusiness.setInserted_by(userRepository.getUserDetails(companyBusiness.getUsername()).getName());
		companyBusiness.setInserted_on(ldt);
		companyBusiness.setUpdated_by("NA");
		companyBusiness.setUpdated_on(ldt);
		companyBusiness.setDeleted_by("NA");
		companyBusiness.setDeleted_on(ldt);
		
		if(Utility.isNullOrEmpty(companyBusiness.getState_code())) {
			companyBusiness.setState_name(state_masterRepository.getState(companyBusiness.getState_code()).getState_name());
		}else {companyBusiness.setState_name("None");}
		
		if(Utility.isNullOrEmpty(companyBusiness.getDist_code())) {
			companyBusiness.setDist_name(district_masterRepository.getDistrictDtls(companyBusiness.getDist_code()).getDist_name());
		}else {companyBusiness.setDist_name("None");}
		
		/*if(Utility.isNullOrEmpty(companyBusiness.getCity_code())) {
			companyBusiness.setCity_name(city_masterRepository.getCityDtls(companyBusiness.getCity_code()).getCity_name());
		}else {companyBusiness.setCity_name("None");}
		
		if(Utility.isNullOrEmpty(companyBusiness.getPostid())) {
			companyBusiness.setPost_office(post_office_masterRepository.getPincodeThruPO(companyBusiness.getPostid()).get().getPost_office());
		}else {companyBusiness.setPost_office("None");}*/
		
		Set<Company_business_unit_details> aacNormsSet=new HashSet<Company_business_unit_details>();
		aacNormsSet.addAll(companyBusiness.getCompanyBusinessUnitDetails());
		int i=0;
		String path=docPdf;
		for(Company_business_unit_details aanmdts:aacNormsSet)
		{
			if(files.length > 0) {
				try {
					fileUpload(files[i],companyBusiness.getBusinessunit_id().trim()+"_"+(i+1)+".pdf",path,companyBusiness.getBusinessunit_name().trim());
				}catch (IOException e) {System.out.println(e);}
				i++;
			}
			
			aanmdts.setBusinessunit_id(gen_sno);
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(userRepository.getUserDetails(companyBusiness.getUsername()).getName());
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
		}
		companyBusiness.setCompanyBusinessUnitDetails(aacNormsSet);
		
		/*
		//Group,Sub Group,Parent Control Save
		
		String uniqucode="";
		int grpunicount=0;String gprefix="GRP";
		if(groupmasterRepository.getMaxOfGroups(gprefix) != null ) {
			grpunicount=Integer.parseInt(groupmasterRepository.getMaxOfGroups(gprefix));
		}
		uniqucode=prefix+Utility.serialNoGenarate(grpunicount);
		
		String subuniqucode="";
		int subunicount=0;String subprefix="SUB";
		if(subgroupmasterRepository.getMaxOfSubGroups(subprefix) != null ) {
			subunicount=Integer.parseInt(subgroupmasterRepository.getMaxOfSubGroups(subprefix));
		}
		subuniqucode=subprefix+Utility.serialNoGenarate(subunicount);
		
		Branch_master branchMaster=new Branch_master(gen_sno,companyBusiness.getBusinessunit_name(),gen_sno,companyBusiness.getBusinessunit_name(),
				companyBusiness.getState_code(),companyBusiness.getCity_name(),Long.toString(companyBusiness.getMobile_no()),companyBusiness.getGstin_no(),
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
			subGrp.setParent_srno("0");
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
			subGrp.setParent_srno("0");
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
		
		//int s=companyBusinessUnitMasterRepository.saveGrpSubParent(gen_sno,Utility.GroupmasterInsert(gen_sno, companyBusiness.getUsername(), companyBusiness.getFin_year(), grpunicount));
		
		return companyBusinessUnitMasterRepository.save(companyBusiness);
	}
	
	public List<Company_business_unit_master> findAll()
	{
		List<Company_business_unit_master> cbuList=new ArrayList<Company_business_unit_master>();
		cbuList.addAll(companyBusinessUnitMasterRepository.findAll());
				
		List<Company_business_unit_master> allData = cbuList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Company_business_unit_master::getBusinessunit_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	 
	public Company_business_unit_master findOne(Long id)
	{
		Optional<Company_business_unit_master> op=this.companyBusinessUnitMasterRepository.findById(id);
		return op.get();
	}
	 
	@Transactional
	public Company_business_unit_master update(Company_business_unit_master companyBusiness,MultipartFile files[]) throws IOException
	 { 
		 Optional<Company_business_unit_master> op =companyBusinessUnitMasterRepository.findById(companyBusiness.getId());
		 LocalDateTime ldt = LocalDateTime.now();
		 
		 companyBusiness.setBusinessunit_id(op.get().getBusinessunit_id());
		 companyBusiness.setModified_type("INSERTED");
		 companyBusiness.setInserted_by(op.get().getInserted_by());
		 companyBusiness.setInserted_on(op.get().getInserted_on());
		 companyBusiness.setUpdated_by(userRepository.getUserDetails(companyBusiness.getUsername()).getName());
		 companyBusiness.setUpdated_on(ldt);
		 companyBusiness.setDeleted_by("NA");
		 companyBusiness.setDeleted_on(ldt);
		 
		 if(Utility.isNullOrEmpty(companyBusiness.getState_code())) {
			 companyBusiness.setState_name(state_masterRepository.getState(companyBusiness.getState_code()).getState_name());
		 }else {companyBusiness.setState_name("None");}
		
		 if(Utility.isNullOrEmpty(companyBusiness.getDist_code())) {
		     companyBusiness.setDist_name(district_masterRepository.getDistrictDtls(companyBusiness.getDist_code()).getDist_name());
		 }else {companyBusiness.setDist_name("None");}
		
		/* if(Utility.isNullOrEmpty(companyBusiness.getCity_code())) {
			 companyBusiness.setCity_name(city_masterRepository.getCityDtls(companyBusiness.getCity_code()).getCity_name());
		 }else {companyBusiness.setCity_name("None");}
		 
		 if(Utility.isNullOrEmpty(companyBusiness.getPostid())) {
			 companyBusiness.setPost_office(post_office_masterRepository.getPincodeThruPO(companyBusiness.getPostid()).get().getPost_office());
		 }else {companyBusiness.setPost_office("None");}*/
		 	
		 if(op.isPresent())
		 {
			 companyBusiness.setId(companyBusiness.getId());
		 }
		 
		 	company_business_unit_detailsRepository.company_business_unit_detailsupdate(companyBusiness.getBusinessunit_id(),"UPDATED");
		 	
		 	Set<Company_business_unit_details> aacNormsSet=new HashSet<Company_business_unit_details>();
			aacNormsSet.addAll(companyBusiness.getCompanyBusinessUnitDetails());
			int i=0;
			String path=docPdf;
			for(Company_business_unit_details aanmdts:aacNormsSet)
			{
				if(files.length > 0) {
					try {
						fileUpload(files[i],companyBusiness.getBusinessunit_id().trim()+"_"+(i+1)+".pdf",path,companyBusiness.getBusinessunit_name().trim());
					}catch (IOException e) {System.out.println(e);}
					i++;
				}
				aanmdts.setBusinessunit_id(companyBusiness.getBusinessunit_id());
				aanmdts.setModified_type("INSERTED");
				aanmdts.setInserted_by(op.get().getInserted_by());
				aanmdts.setInserted_on(op.get().getInserted_on());
				aanmdts.setUpdated_by(userRepository.getUserDetails(companyBusiness.getUsername()).getName());
				aanmdts.setUpdated_on(ldt);
				aanmdts.setDeleted_by("NA");
				aanmdts.setDeleted_on(ldt);
			}
			companyBusiness.setCompanyBusinessUnitDetails(aacNormsSet);
		 
		 return companyBusinessUnitMasterRepository.save(companyBusiness);
	 }
	 
	public List<Business_unit> getcompanyBUnameList(String company)
	 {
			List<Business_unit> cbuList=business_unitRepository.findAll();
			cbuList.forEach((e->{
				e.setBusinessunit_name(e.getBusinessunit_name().toUpperCase());
			}));
			
			List<Business_unit> allData = cbuList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company) )
				  .sorted(Comparator.comparing(Business_unit::getBusinessunit_name))
				  .collect(Collectors.toList());
			
			return allData;
	 }
	
	public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList(String company)
	 {
			List<Company_business_unit_master> cbuList=companyBusinessUnitMasterRepository.findAll();
			cbuList.forEach((e->{
				e.setBusinessunit_name(e.getBusinessunit_name().toUpperCase());
			}));
			
			List<Company_business_unit_master> allData = cbuList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company) )
				  .sorted(Comparator.comparing(Company_business_unit_master::getBusinessunit_name))
				  .collect(Collectors.toList());
			
			
			// Create Conversion Type
			Type listType = new TypeToken<List<CompanyBusinessUnitMasterDTO>>() {}.getType();
			// Convert List of Entity objects to a List of DTOs objects 
			List<CompanyBusinessUnitMasterDTO> compBunitList = new ModelMapper().map(allData,listType);
			
			return compBunitList;
	 }
	//Delete
	public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList()
	 {
		 	//return companyBusinessUnitMasterRepository.getcompanyBUMNCList();
		 	List<Company_business_unit_master> modelList=companyBusinessUnitMasterRepository.getcompanyBUMNCList();
			
			// Create Conversion Type
			Type listType = new TypeToken<List<CompanyBusinessUnitMasterDTO>>() {}.getType();
			// Convert List of Entity objects to a List of DTOs objects 
			List<CompanyBusinessUnitMasterDTO> compBunitList = new ModelMapper().map(modelList,listType);
			
			return compBunitList;
	 }
	    	
    public CompanyBusinessUnitMasterDTO getBUnitNameByCode(String buCode){
    	
		//return companyBusinessUnitMasterRepository.buNameByCode(buCode);
    	Company_business_unit_master modelList=companyBusinessUnitMasterRepository.buNameByCode(buCode);
		
		// Create Conversion Type
		Type listType = new TypeToken<CompanyBusinessUnitMasterDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		CompanyBusinessUnitMasterDTO itemNameList= new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
    
    public CompanyBusinessUnitMasterDTO getCBUdetails(String buCode){
    	
    	Company_business_unit_master modelList=companyBusinessUnitMasterRepository.getCBUdetails(buCode);
		
		Type listType = new TypeToken<CompanyBusinessUnitMasterDTO>() {}.getType();
		
		CompanyBusinessUnitMasterDTO itemNameList= new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
    
    public CompanyBusinessUnitMasterDTO getCBUdetailsById(String buCode){
    	
    	Company_business_unit_master modelList=companyBusinessUnitMasterRepository.businessunitbyid(buCode);
		
		Type listType = new TypeToken<CompanyBusinessUnitMasterDTO>() {}.getType();
		
		CompanyBusinessUnitMasterDTO itemNameList= new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
    
    public CompanyBusinessUnitMasterDTO CompanyBUAddress(String Id){
		Company_business_unit_master modelList=companyBusinessUnitMasterRepository.CompanyBUAddress(Id);
		Type listType = new TypeToken<CompanyBusinessUnitMasterDTO>() {}.getType();
		CompanyBusinessUnitMasterDTO itemNameList= new ModelMapper().map(modelList,listType);
		String Add="";
		/*if(Utility.isNullOrEmpty(itemNameList.getWork_address()))
		{
			Add+=itemNameList.getWork_address()+",";
		}
		if(Utility.isNullOrEmpty(itemNameList.getCountry_name()))
		{
			Add+=itemNameList.getCountry_name()+",";
		}
		if(Utility.isNullOrEmpty(itemNameList.getState_name()))
		{
			Add+=itemNameList.getState_name()+",";
		}
		if(Utility.isNullOrEmpty(itemNameList.getCity_name()))
		{
			Add+=itemNameList.getCity_name()+",";
		}
		if(Utility.isNullOrEmpty(String.valueOf(itemNameList.getPin_code())))
		{
			Add+=itemNameList.getPin_code()+",";
		}
		Add=Add.substring(0,Add.length()-1);*/
		//String Add=itemNameList.getWork_address()+" , "+itemNameList.getCountry_name()+" , "+itemNameList.getState_name()+" , "+itemNameList.getCity_name()+" , "+itemNameList.getPin_code();
		itemNameList.setAdd(itemNameList.getWork_address());
		return itemNameList;
	}
    
    public List<CompanyBusinessUnitDetailsDTO> compBURetriveList(String b_id)
	{
		List<Company_business_unit_details> cbuList=new ArrayList<Company_business_unit_details>();
		cbuList.addAll(company_business_unit_detailsRepository.findAll());
				
		List<Company_business_unit_details> allData = cbuList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && c.getBusinessunit_id().equals(b_id))
			  .sorted(Comparator.comparing(Company_business_unit_details::getSln_no))
			  .collect(Collectors.toList());
			
		Type listType=new TypeToken<List<CompanyBusinessUnitDetailsDTO>>() {}.getType();
		List<CompanyBusinessUnitDetailsDTO> compBU=new ModelMapper().map(allData,listType);
		
		return compBU;
	}
    
    public List<CompanyBusinessUnitMasterDTO> getCompBusinessUnitDiff(String bunitid)
    {
	 	List<Company_business_unit_master> cbuList=new ArrayList<Company_business_unit_master>();
		cbuList.addAll(companyBusinessUnitMasterRepository.findAll());
				
		List<Company_business_unit_master> allData = cbuList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED") && !c.getBusinessunit_id().equals(bunitid))
			  .sorted(Comparator.comparing(Company_business_unit_master::getBusinessunit_name))
			  .collect(Collectors.toList());
	 	
	 	Type listType = new TypeToken<List<CompanyBusinessUnitMasterDTO>>() {}.getType();
		List<CompanyBusinessUnitMasterDTO> cBunitList = new ModelMapper().map(allData,listType);
		
		return cBunitList;
    }
    
    public SequenceIdDTO getCompanyBusiSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=companyBusinessUnitMasterRepository.getCompanyBusiSequenceId(fprefix,company);
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
    
    public StatusDTO getBusiUnitStateStatus(String bunit,String dbunit) {
			
    	String bustate=companyBusinessUnitMasterRepository.CompanyBUAddress(bunit).getState_name();
    	String dbustate=companyBusinessUnitMasterRepository.CompanyBUAddress(dbunit).getState_name();
    	Type listType = new TypeToken<StatusDTO>() {}.getType();
    	System.err.println("Got Value:- "+bustate+" "+dbustate);
    	if(bustate.compareTo(dbustate)==0) {
    		StatusDTO def=new StatusDTO("Same");
    		StatusDTO dstatus = new ModelMapper().map(def,listType);
			return dstatus;
		}
    	else {
    		StatusDTO def=new StatusDTO("Diff");
    		StatusDTO dstatus = new ModelMapper().map(def,listType);
			return dstatus;
    	}
	}
    
    @Transactional
	public Company_business_unit_master deleteCompanyBUMaster(Company_business_unit_master cbum,long id)
	{
		Optional<Company_business_unit_master> op = companyBusinessUnitMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Company_business_unit_master company_business_unit_master=op.get();

		company_business_unit_master.setInserted_by(op.get().getInserted_by());
		company_business_unit_master.setInserted_on(op.get().getInserted_on());
		company_business_unit_master.setUpdated_by(op.get().getUpdated_by());
		company_business_unit_master.setUpdated_on(op.get().getUpdated_on());
		company_business_unit_master.setDeleted_by(userRepository.getUserDetails(cbum.getUsername()).getName());
		company_business_unit_master.setDeleted_on(ldt);
		company_business_unit_master.setModified_type("DELETED");
		
		company_business_unit_detailsRepository.company_business_unit_detailsupdate(company_business_unit_master.getBusinessunit_id(),"DELETED");
		
		if(op.isPresent())
		{
			company_business_unit_master.setId(id);
		}
		
		return companyBusinessUnitMasterRepository.save(company_business_unit_master);
	}
    
    
    public List<Company_business_unit_master> findCompanyBUMaster(String searchtext)
	{
		List<Company_business_unit_master> CBUM=new ArrayList<Company_business_unit_master>();
		CBUM.addAll(companyBusinessUnitMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Company_business_unit_master> allData = CBUM
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Company_business_unit_master::getBusinessunit_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Company_business_unit_master> allData = CBUM
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getBusinessunit_name()+c.getBusinessunit_code()+c.getCompany_code()+c.getMailing_address()+c.getCountry_name()+c.getDist_name()+c.getState_name()+c.getCity_name()+c.getPin_code()+c.getOffice_contactno()+c.getWork_phoneno()+c.getMobile_no()+c.getEmail_id()+c.getWebsite_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Company_business_unit_master::getBusinessunit_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
    
    
    
    
    
    
    
    public StatusDTO checkMisleniousDeletation(String parent_id,String parentModel) 
	{
		StatusDTO status_del =new StatusDTO();
		boolean purchase=false,sales=false,unloadadvice=false,loadadvice=false,binmaster=false,item=false,transmaster=false,deptMaster=false;
		boolean transVehiclemaster=false,vehiclemaster=false,supplier=false,customer=false,broker=false,transporter=false,purpose=false,delvChallan=false;
		boolean chargeMatrixmaster=false;
		//System.out.println("parentModel:"+parentModel);
		if(parentModel.compareToIgnoreCase("Bussiness_Unit")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBussinessUnitPurchaseOrderUsage(parent_id)) 
			{
				purchase=true;
			}
			if(companyBusinessUnitMasterRepository.checkBussinessUnitSalesOrderUsage(parent_id)) 
			{
				sales=true;
			}
			if(purchase == true || sales == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("wareHouese")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkWareHouseUnloadAdviceUsage(parent_id)) 
			{
				unloadadvice=true;
			}
			if(companyBusinessUnitMasterRepository.checkWareHouseLoadAdviceUsage(parent_id)) 
			{
				loadadvice=true;
			}
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) 
			{
				binmaster=true;
			}
			
			if(unloadadvice == true || loadadvice == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("binMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("departmentMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkPurOrderItemDetailsUsage(parent_id))
			{
				deptMaster=true;
			}
			if(deptMaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("CustomUomMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkItemMasterUsage(parent_id))
			{
				item=true;
			}
			if(item == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("miscMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("taxTypeMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("vehicleTypeMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checTransVehicleDtlsUsage(parent_id)) 
			{
				transVehiclemaster=true;
			}
			if(companyBusinessUnitMasterRepository.checkVehicleMasterUsage(parent_id)) 
			{
				vehiclemaster=true;
			}
			if(transVehiclemaster == true || vehiclemaster ==true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("vehicleMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkTransMasterUsage(parent_id)) 
			{
				transmaster=true;
			}
			if(companyBusinessUnitMasterRepository.checkTransMasterUsage(parent_id)) 
			{
				unloadadvice=true;
			}
			if(companyBusinessUnitMasterRepository.checkTransMasterUsage(parent_id)) 
			{
				loadadvice=true;
			}
			if(transmaster == true || unloadadvice == true || loadadvice == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("transportationChargeMatrixMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("taxCodeMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("designationMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("districtMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkSuppMasterUsage(parent_id))
			{
				supplier=true;
			}
			if(companyBusinessUnitMasterRepository.checkCustMasterUsage(parent_id))
			{
				customer=true;
			}
			if(companyBusinessUnitMasterRepository.checkBrokerMasterUsage(parent_id))
			{
				broker=true;
			}
			if(companyBusinessUnitMasterRepository.checkTransporetrMasterUsage(parent_id))
			{
				transporter=true;
			}
			if(supplier == true || customer == true || broker == true ||transporter == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("areaMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkBinMasterUsage(parent_id)) //need to change repository
			{
				binmaster=true;
			}
			if(binmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("screenMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkChargematrixMasterUsage(parent_id)) 
			{
				chargeMatrixmaster=true;
			}
			if(chargeMatrixmaster == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("purposeMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkPurOrderitemDtlsMasterUsage(parent_id))
			{
				purpose=true;
			}
			if(purpose == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
		if(parentModel.compareToIgnoreCase("invoiceTypeMaster")==0) 
		{
			if(companyBusinessUnitMasterRepository.checkDeliveryChallanUsage(parent_id))
			{
				delvChallan=true;
			}
			if(delvChallan == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
		}
		
	//System.out.println("HELLO HERE "+status_del.getStatus());
		
		return status_del;
	}


	 public List<CompanyBusinessUnitMasterDTOC> getcompanyBUMNCListnew(String company)
	{
			
			List<Object[]> comapnynames=new ArrayList<Object[]>();
			comapnynames.addAll(companyBusinessUnitMasterRepository.getcomapanybuunit(company));
			
			CompanyBusinessUnitMasterDTOC defaultr= new CompanyBusinessUnitMasterDTOC("0", "Choose an Option");
			
			
			 List<CompanyBusinessUnitMasterDTOC> list = new ArrayList<CompanyBusinessUnitMasterDTOC>();     
			 list.add(defaultr);
			    for(final Object o : comapnynames)
			    {
			        Object[] obj = (Object[]) o;
			        list.add(new CompanyBusinessUnitMasterDTOC(obj[0].toString(), obj[1].toString()));
			    }
				  
			return list;
					
		}
	 
	 public List<Map<String,Object>> getcompanyBUMNCListFastApi(String company)
		{
		 List<Map<String,Object>> comapnynames=new ArrayList<Map<String,Object>>();
		 
		 comapnynames.addAll(companyBusinessUnitMasterRepository.getcompanyBUMNCListFastApi(company));
		 
		 return comapnynames;
		}
	 
	 public Map<String,Object> getCompanyBussinessUnitDetails(String company,String bunit)
		{
			return companyBusinessUnitMasterRepository.getCompanyBussinessUnitDetails(company,bunit);
		}
	 
}
