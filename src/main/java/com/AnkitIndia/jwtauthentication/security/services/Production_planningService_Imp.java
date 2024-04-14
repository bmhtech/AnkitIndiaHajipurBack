package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Production_planning;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls_static;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planningRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_periodic_date_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shift_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shop_floor_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_date_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_special_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Delivery_challan_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planningDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_periodic_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_timeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shop_floor_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_up;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_listDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Production_planningService_Imp implements Production_planningService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Production_planningRepository production_planningRepository;
	
	@Autowired
	Production_planning_shop_floor_dtlsRepository production_planning_shop_floor_dtlsRepository;
	
	@Autowired
	Production_planning_special_dtlsRepository production_planning_special_dtlsRepository;
	
	@Autowired
	Production_planning_periodic_date_detailsRepository pDate_detailsRepository;
	
	@Autowired
	Production_planning_special_date_detailsRepository production_planning_special_date_detailsRepository;
	
	@Autowired
	Production_planning_shift_detailsRepository production_planning_shift_detailsRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Production_planning_periodic_date_detailsRepository production_planning_periodic_date_detailsRepository;
	
	public SequenceIdDTO getPPSequenceId(String prefix,String bunit,String company) {
		Long slno=0L;String fprefix="",buprefix="";
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(bunit).getBusinessunit_name();
		
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/"+buprefix.substring(0,3)+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=production_planningRepository.getPPSequenceId(fprefix,bunit,company);
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
	public Production_planning save(Production_planning planning) throws JsonParseException, JsonMappingException, IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(production_planningRepository.countId() != null ) {
			slno=Long.parseLong(production_planningRepository.countId());
		}
		String prefix="PP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		
		/*End checking transaction no for unique */
		
		if(planning.getBusiness_unit().compareTo("0") !=0 && planning.getBusiness_unit().compareTo("") !=0 && planning.getBusiness_unit() !=null) {
			planning.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(planning.getBusiness_unit()).getBusinessunit_name());
		}else {planning.setBusiness_unitname("None");}
		
		planning.setProd_plan_id(gen_sno);
		planning.setModified_type("INSERTED");
		planning.setInserted_by(userRepository.getUserDetails(planning.getUsername()).getName());
		planning.setInserted_on(ldt);
		planning.setUpdated_by("NA");
		planning.setUpdated_on(ldt);
		planning.setDeleted_by("NA");
		planning.setDeleted_on(ldt);
		
		long x=0,z=0;
		String buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(planning.getBusiness_unit()).getBusinessunit_name().substring(0,3);
		//For Regular Process ********
		
		Set<Production_planning_shop_floor_dtls> pFloor_dtls=new HashSet<Production_planning_shop_floor_dtls>();
		pFloor_dtls.addAll(planning.getProduction_planning_shop_floor_dtls());
		for(Production_planning_shop_floor_dtls pDtls:pFloor_dtls) 
		{
			if(pDtls.getProcess().compareTo("0") !=0 && pDtls.getProcess().compareTo("") !=0 && pDtls.getProcess() !=null) {
				pDtls.setProcess_name(process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_desc());
			}else {pDtls.setProcess_name("None");}
			
			if(pDtls.getProduction().compareTo("0") !=0 && pDtls.getProduction().compareTo("") !=0 && pDtls.getProduction() !=null) {
				pDtls.setProduction_name(bom_masterRepository.getBomDetails(pDtls.getProduction()).getProd_desc());
			}else {pDtls.setProduction_name("None");}
			
			pDtls.setProd_plan_code(planning.getProd_plan_code());
			pDtls.setProd_plan_id(gen_sno);
			pDtls.setBusiness_unit(planning.getBusiness_unit());
			pDtls.setCompany_id(planning.getCompany_id());
			pDtls.setFin_year(planning.getFin_year());
			pDtls.setModified_type("INSERTED");
			pDtls.setInserted_by(planning.getInserted_by());
			pDtls.setInserted_on(planning.getInserted_on());
			pDtls.setUpdated_by(planning.getUpdated_by());
			pDtls.setUpdated_on(planning.getUpdated_on());
			pDtls.setDeleted_by("NA");
			pDtls.setDeleted_on(ldt);
			
			x++;
			pDtls.setPpd_id(gen_sno+Long.toString(x));
			
			//For regular process popup date details ********
			long n=0;
			
			Set<Production_planning_periodic_date_details> pDate_details2=new HashSet<Production_planning_periodic_date_details>();
			
			if(pDtls.getProcess_date().compareTo("")==0) {
				pDtls.setProcess_date("{\"periodic_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\",\"todate\":\"\",\"shift1\":\"\"}]}");
			}
			
			String inptData=pDtls.getProcess_date().replace("{\"periodic_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_periodic_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_periodic_date_details[] pDate_details= new ObjectMapper().readValue(inptData, Production_planning_periodic_date_details[].class);
			// List<Production_planning_periodic_date_details> listObjects = Arrays.asList(pDate_details);
			
			Set<Production_planning_periodic_date_details> popupDateSet = new HashSet<>(Arrays.asList(pDate_details));
			
			/*for(Production_planning_periodic_date_details ppDateDetails:popupDateSet) 
			{
				System.err.println("Got Date 1st:> "+ppDateDetails.getFromdate()+" To: "+ppDateDetails.getTodate());
				if(ppDateDetails.getFromdate().compareTo("")!=0 && ppDateDetails.getTodate().compareTo("")!=0)
				{
					pDate_details2.add(ppDateDetails);
				}
			}*/
			
			pDate_details2.addAll(popupDateSet);
			for(Production_planning_periodic_date_details ppDatedtls:pDate_details2) 
			{
				ppDatedtls.setProd_plan_code(planning.getProd_plan_code());
				ppDatedtls.setProd_plan_id(gen_sno);
				ppDatedtls.setPpd_id(pDtls.getPpd_id());
				
				ppDatedtls.setCompany_id(pDtls.getCompany_id());
				ppDatedtls.setFin_year(pDtls.getFin_year());
				ppDatedtls.setModified_type("INSERTED");
				ppDatedtls.setInserted_by(pDtls.getInserted_by());
				ppDatedtls.setInserted_on(pDtls.getInserted_on());
				ppDatedtls.setUpdated_by(pDtls.getUpdated_by());
				ppDatedtls.setUpdated_on(pDtls.getUpdated_on());
				ppDatedtls.setDeleted_by("NA");
				ppDatedtls.setDeleted_on(ldt);
				ppDatedtls.setShift2("0");
				
				n++;
				ppDatedtls.setPpds_id("S"+gen_sno+Long.toString(n));
				System.out.println("from date "+ppDatedtls.getFromdate());
				if(ppDatedtls.getFromdate().compareTo("")==0) 
				{
					ppDatedtls.setFromdate("00-00-0000");
				}
				if(ppDatedtls.getTodate().compareTo("")==0) 
				{
					ppDatedtls.setTodate("00-00-0000");
				}
				String date[] = ppDatedtls.getFromdate().split("-");
				String date2[] = ppDatedtls.getTodate().split("-");
				
				String sprefix=buprefix+"-"+date[0]+date[1]+date[2].substring(2,4)+"-"+date2[0]+date2[1]+date2[2].substring(2,4)+"-";
				String sgen_sno="";
				//For regular process popup date shift details ********
				
				String shifts=ppDatedtls.getShift1();
				String noshifts[]=null;
				
				if(shifts.compareTo("")==0) {shifts="0,0";}
				noshifts=shifts.split(",");
				String shift_enddate="0",shift_endtime="0",shift_startdate="0",shift_starttime="0";
				
				Set<Production_planning_shift_details> pShift_details=new HashSet<Production_planning_shift_details>();
				
				for (int j=0;j<noshifts.length;j++) 
				{ 
					sgen_sno=UniqueIDTransaction.uniqueId4(sprefix,Integer.parseInt(noshifts[j])-1);
					/*Production_planning_shift_details shfts=new Production_planning_shift_details(ppDatedtls.getProd_plan_id(),
							ppDatedtls.getProd_plan_code(),
							ppDatedtls.getPpd_id(),ppDatedtls.getPpds_id(),
							sgen_sno,planning.getBusiness_unit(),pDtls.getShop_floor(),
							pDtls.getProcess(),pDtls.getProduction(),ppDatedtls.getFromdate(),ppDatedtls.getTodate(),
							Integer.parseInt(noshifts[j]),shift_enddate,shift_endtime,shift_startdate,shift_starttime,
							ppDatedtls.getCompany_id(),ppDatedtls.getFin_year(),ppDatedtls.getModified_type(),
							ppDatedtls.getInserted_on(),ppDatedtls.getInserted_by(),ppDatedtls.getUpdated_on(),
							ppDatedtls.getUpdated_by(),ppDatedtls.getDeleted_on(),ppDatedtls.getDeleted_by(),ppDatedtls);
					ppDatedtls.getInserted_on(),ppDatedtls.getInserted_by(),ppDatedtls.getUpdated_on(),
					ppDatedtls.getUpdated_by(),ppDatedtls.getDeleted_on(),ppDatedtls.getDeleted_by(),ppDatedtls);
					*/
					
					Production_planning_shift_details shfts=new Production_planning_shift_details();
					
					shfts.setProd_plan_id(ppDatedtls.getProd_plan_id());
					shfts.setProd_plan_code(ppDatedtls.getProd_plan_code());
					shfts.setPpd_id(ppDatedtls.getPpd_id());
					shfts.setPpds_id(ppDatedtls.getPpds_id());
					shfts.setPp_shiftid(sgen_sno);
					shfts.setBusiness_unit(planning.getBusiness_unit());
					shfts.setShop_floor(pDtls.getShop_floor());
					shfts.setProcess(pDtls.getProcess());
					shfts.setProduction(pDtls.getProduction());
					shfts.setFromdate(ppDatedtls.getFromdate());
					shfts.setTodate(ppDatedtls.getTodate());
					shfts.setShift(Integer.parseInt(noshifts[j]));
					shfts.setShift_startdate(shift_startdate);
					shfts.setShift_starttime(shift_starttime);
					shfts.setShift_endtime(shift_endtime);
					shfts.setShift_enddate(shift_enddate);
					shfts.setCompany_id(ppDatedtls.getCompany_id());
					shfts.setFin_year(ppDatedtls.getFin_year());
					shfts.setModified_type(ppDatedtls.getModified_type());
					shfts.setInserted_on(ppDatedtls.getInserted_on());
					shfts.setInserted_by(ppDatedtls.getInserted_by());
					shfts.setUpdated_on(ppDatedtls.getUpdated_on());
					shfts.setUpdated_by(ppDatedtls.getUpdated_by());
					shfts.setDeleted_by(ppDatedtls.getDeleted_by());
					shfts.setDeleted_on(ppDatedtls.getDeleted_on());
					shfts.setPDate_details(ppDatedtls);
					
					
					
					
					
					
					pShift_details.add(shfts);
				}
				ppDatedtls.setProduction_planning_shift_details(pShift_details);
				
			}
			pDtls.setPeriodic_date_details(pDate_details2);
			
		}
		planning.setProduction_planning_shop_floor_dtls(pFloor_dtls);
		
		//For Special Process ********
		
		Set<Production_planning_special_dtls> pSpecial_dtls=new HashSet<Production_planning_special_dtls>();
		pSpecial_dtls.addAll(planning.getProduction_planning_special_dtls());
		for(Production_planning_special_dtls pSpecial:pSpecial_dtls) 
		{
			if(pSpecial.getProcess().compareTo("0") !=0 && pSpecial.getProcess().compareTo("") !=0 && pSpecial.getProcess() !=null) {
				pSpecial.setProcess_name(process_masterRepository.getProcessDetails(pSpecial.getProcess()).getProcess_desc());
			}else {pSpecial.setProcess_name("None");}
			
			if(pSpecial.getProduction().compareTo("0") !=0 && pSpecial.getProduction().compareTo("") !=0 && pSpecial.getProduction() !=null) {
				pSpecial.setProduction_name(bom_masterRepository.getBomDetails(pSpecial.getProduction()).getProd_desc());
			}else {pSpecial.setProduction_name("None");}
			
			pSpecial.setProd_plan_code(planning.getProd_plan_code());
			pSpecial.setProd_plan_id(gen_sno);
			pSpecial.setBusiness_unit(planning.getBusiness_unit());
			pSpecial.setCompany_id(planning.getCompany_id());
			pSpecial.setFin_year(planning.getFin_year());
			pSpecial.setModified_type("INSERTED");
			pSpecial.setInserted_by(planning.getInserted_by());
			pSpecial.setInserted_on(planning.getInserted_on());
			pSpecial.setUpdated_by(planning.getUpdated_by());
			pSpecial.setUpdated_on(planning.getUpdated_on());
			pSpecial.setDeleted_by("NA");
			pSpecial.setDeleted_on(ldt);
			
			z++;
			pSpecial.setPps_id(gen_sno+Long.toString(z));
			
			//For special process popup date details ********
			
			Set<Production_planning_special_date_details> pSpecial_date_details=new HashSet<Production_planning_special_date_details>();
			
			if(pSpecial.getProcess_date().compareTo("")==0) {
				pSpecial.setProcess_date("{\"special_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\"}]}");
			}
			
			String inptData=pSpecial.getProcess_date().replace("{\"special_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_special_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_special_date_details[] pSpecial_date_dtls= new ObjectMapper().readValue(inptData, Production_planning_special_date_details[].class);
			
			Set<Production_planning_special_date_details> set2 = new HashSet<>(Arrays.asList(pSpecial_date_dtls));
			pSpecial_date_details.addAll(set2);
			
			for(Production_planning_special_date_details pSplDateDtls:pSpecial_date_details) 
			{
				pSplDateDtls.setProd_plan_code(planning.getProd_plan_code());
				pSplDateDtls.setProd_plan_id(gen_sno);
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
			
		}
		planning.setProduction_planning_special_dtls(pSpecial_dtls);
		
		return production_planningRepository.save(planning);
	}
	
	public List<Production_planningDTO> findAllProdPlanning()
	{
		List<Production_planning> processList=new ArrayList<Production_planning>();
		List<Production_planningDTO> processList1=new ArrayList<Production_planningDTO>();
		
		processList.addAll(production_planningRepository.findAllProdPlanning());
		processList.forEach(e->{
			System.out.println("planid:"+e.getProd_plan_id());
			Production_planningDTO pplan = new Production_planningDTO();
			pplan.setId(e.getId());
			pplan.setProd_plan_id(e.getProd_plan_id());
			pplan.setProd_plan_code(e.getProd_plan_code());
			pplan.setBusiness_unitname(e.getBusiness_unitname());
			pplan.setProd_plan_desc(e.getProd_plan_desc());
			pplan.setPred_from(e.getPred_from());
			pplan.setPred_to(e.getPred_to());
			pplan.setStatus(production_planningRepository.checkPlanShift(e.getProd_plan_id()));
			processList1.add(pplan);
		});
		return processList1;
	}
	
	public Production_planning findOne(long id)
	{
		Optional<Production_planning> op=this.production_planningRepository.findById(id);
		return op.get();
	}

	public List<Production_planningDTO> getProdPlanning()
	{
		 List<Production_planning> modelList=  production_planningRepository.findAllProdPlanning();
		 
		 Type listType=new TypeToken<List<Production_planningDTO>>() {}.getType();
		 
		 List<Production_planningDTO> pPlanning=new ModelMapper().map(modelList, listType);
		 
		 return pPlanning;
	}
	
	public List<Production_planning_shop_floor_dtlsDTO> getProdPlanFloorDtls(String prodid)
	{
		List<Production_planning_shop_floor_dtls> modelList=new ArrayList<Production_planning_shop_floor_dtls>();
		modelList.addAll(production_planning_shop_floor_dtlsRepository.getProduction_planning_shop_floor_dtls(prodid));
		Type listType=new TypeToken<List<Production_planning_shop_floor_dtlsDTO>>() {}.getType();
		
		List<Production_planning_shop_floor_dtlsDTO> floorDtls=new ModelMapper().map(modelList,listType);
		
		return floorDtls;
	}
	
	public List<Production_planning_special_dtlsDTO> getProdPlanSpecialDtls(String prodid)
	{
		List<Production_planning_special_dtls> modelList=new ArrayList<Production_planning_special_dtls>();
		modelList.addAll(production_planning_special_dtlsRepository.getProduction_planning_special_dtls(prodid));
		Type listType=new TypeToken<List<Production_planning_special_dtlsDTO>>() {}.getType();
		
		List<Production_planning_special_dtlsDTO> special_dtlsDTOs=new ModelMapper().map(modelList,listType);
		
		return special_dtlsDTOs;
	}
	
	public List<Production_planning_periodic_date_detailsDTO> getProdPlanPerDateDtls(String prodid,String proddateid)
	{
		List<Production_planning_periodic_date_details> modelList=new ArrayList<Production_planning_periodic_date_details>();
		modelList.addAll(pDate_detailsRepository.getProduction_planning_periodic_date_details(prodid,proddateid));
		Type listType=new TypeToken<List<Production_planning_periodic_date_detailsDTO>>() {}.getType();
		
		List<Production_planning_periodic_date_detailsDTO> pdatedtlsDTOs=new ModelMapper().map(modelList,listType);
		
		for(int i=0;i<pdatedtlsDTOs.size();i++) {
			String shifts[] =null;
			String shifts2[] =null;
			
			if(pdatedtlsDTOs.get(i).getShift1().compareTo("")!=0) {
				shifts2=pdatedtlsDTOs.get(i).getShift1().split(",");
				
				shifts = new String[shifts2.length];
				for(int j=0;j<shifts2.length;j++) {
					shifts[j]=shifts2[j];
				}
			}
			else {
				shifts = new String[1];
				shifts[0]="0";
			}
			pdatedtlsDTOs.get(i).setShift(shifts);
		}
		return pdatedtlsDTOs;
	}
	
	public List<Production_planning_periodic_date_details> getProdPlanPerDateDtlsShiftNo(String prodid,String proddateid)
	{
		List<Production_planning_periodic_date_details> modelList=new ArrayList<Production_planning_periodic_date_details>();
		modelList.addAll(pDate_detailsRepository.getProdPlanPerDateDtlsShiftNo(prodid,proddateid));
		
		return modelList;
	}
	public List<Production_planning_shift_detailsDTO> getProdPlanShiftDtls(String businessunit,String shopfl,String tdate)
	{
		List<Production_planning_shift_details> pDetails=new ArrayList<Production_planning_shift_details>();
		List<Production_planning_shift_details> pShift_details=production_planning_shift_detailsRepository.getProdPlanShiftDtls(businessunit,shopfl);
		//System.out.println("production/"+production_planning_shift_detailsRepository.getProdPlanShiftDtls(businessunit,shopfl));
		//System.out.println("businessunit/"+businessunit+"/shopfl/"+shopfl+"/tdate/"+tdate+"/pShift_details.size()/"+pShift_details.size());
		for(int x=0;x<pShift_details.size();x++) {
			//System.err.println("Got Dtls: "+pShift_details.get(x).getPp_shiftid()+" From Date: "+pShift_details.get(x).getFromdate()+" To Date: "+pShift_details.get(x).getTodate()+" Process: "+pShift_details.get(x).getProcess());
			//System.out.println("process"+pShift_details.get(x).getProcess());
			//System.out.println("process1"+process_masterRepository.getProcessDetails(pShift_details.get(x).getProcess()).getProcess_freq());
			String processfreq=process_masterRepository.getProcessDetails(pShift_details.get(x).getProcess()).getProcess_freq();
			if(processfreq.compareTo("Periodic")==0) {
				pDetails.addAll(production_planning_shift_detailsRepository.getProdPlanPeriodicShiftDtls(pShift_details.get(x).getPp_shiftid(),Utility.convertDateDDMMYYYY(tdate)));
			}else {
				//System.out.println("else/"+pShift_details.get(x).getPp_shiftid()+" / " +Utility.convertDateDDMMYYYY(tdate)+" / " + production_planning_shift_detailsRepository.getProdPlanDailyShiftDtls(pShift_details.get(x).getPp_shiftid(),Utility.convertDateDDMMYYYY(tdate)));
				pDetails.addAll(production_planning_shift_detailsRepository.getProdPlanDailyShiftDtls(pShift_details.get(x).getPp_shiftid(),Utility.convertDateDDMMYYYY(tdate)));
			}
			
		}
		
		Type listType=new TypeToken<List<Production_planning_shift_detailsDTO>>() {}.getType();
		List<Production_planning_shift_detailsDTO> pShiftsDTO=new ModelMapper().map(pDetails,listType);
		
		return pShiftsDTO;
	}
	
	public Production_planning_shift_detailsDTO getProdPlanShiftDetails(String shiftid)
	{
		Production_planning_shift_details pShift_details=production_planning_shift_detailsRepository.getProdPlanShiftDetails(shiftid);
		Type listType=new TypeToken<Production_planning_shift_detailsDTO>() {}.getType();
		Production_planning_shift_detailsDTO pShiftsDTO=new ModelMapper().map(pShift_details,listType);
		
		if(pShiftsDTO.getProduction().compareTo("0") !=0 && pShiftsDTO.getProduction().compareTo("") !=0 && pShiftsDTO.getProduction() !=null) {
			
			pShiftsDTO.setProduction_desc(bom_masterRepository.getBomDetails(pShiftsDTO.getProduction()).getProd_desc());
		}else {pShiftsDTO.setProduction_desc("None");}
		
		String processfreq=process_masterRepository.getProcessDetails(pShiftsDTO.getProcess()).getProcess_freq();
		
		if(processfreq.compareTo("Periodic")==0) {
			pShiftsDTO.setProduction_date(pShiftsDTO.getShift_endtime());
		}else {
			pShiftsDTO.setProduction_date(pShiftsDTO.getShift_startdate());
		}
		return pShiftsDTO;
	}
	
	public List<Production_planning_shiftDTO> getProdPlanShifts(String process,String shopfl,String startdate,String enddate)
	{
		int shifts[] =null;
		List<Production_planning_shift_details> pShift_details=production_planning_shift_detailsRepository.getProduction_planning_shift_details(process,shopfl,startdate,enddate);
		
		/*shifts = new int[pShift_details.size()];
		for(int i=0;i<pShift_details.size();i++) {
			shifts[i]=pShift_details.get(i).getShift();
		}*/
		
		Type listType=new TypeToken<List<Production_planning_shiftDTO>>() {}.getType();
		List<Production_planning_shiftDTO> pShiftDTO=new ModelMapper().map(pShift_details,listType);
		//pShiftDTO.setShift(shifts);
		
		for(int j=0;j<pShift_details.size();j++) {
			System.err.println("Got No: "+pShift_details.get(j).getShift());
			
			pShiftDTO.get(j).setShiftno(pShift_details.get(j).getShift());
		}
		
		return pShiftDTO;
	}
	
	public List<Production_planning_shiftDTO> getProdPlanShiftsFrom(String process,String shopfl,String startdate)
	{
		List<Production_planning_shift_details> pShift_details=production_planning_shift_detailsRepository.getProduction_planning_shift_details(process,shopfl,startdate);
		
		Type listType=new TypeToken<List<Production_planning_shiftDTO>>() {}.getType();
		List<Production_planning_shiftDTO> pShiftDTO=new ModelMapper().map(pShift_details,listType);
		
		for(int j=0;j<pShift_details.size();j++) {
			//System.out.println("shift"+pShift_details.get(j).getShift());
			pShiftDTO.get(j).setShiftno(pShift_details.get(j).getShift());
		}
		//System.out.println("shift size" +  pShiftDTO.size()); 
		return pShiftDTO;
	}
	
	public StatusDTO getProdPlanShiftStatus(String process,String shopfl,String sdate) {
		String result="NOTEXIST";
		System.out.println("Got data: "+Utility.convertDateYYYYMMDD(sdate));
		List<Production_planning_shift_details> pDetails=production_planning_shift_detailsRepository.findShifts(process,shopfl,Utility.convertDateYYYYMMDD(sdate));
		//.orElseThrow(()-> new RuntimeException("the data could not find please chec."))
		System.out.println("SIze: "+pDetails.size());	
		if(pDetails.size() > 0)
		{result="EXIST";}
		else{result="NOTEXIST";}
			
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO status = new ModelMapper().map(result,listType);
		status.setStatus(result);
		
		return status;
	}
	
	public List<Production_planning_shift_timeDTO> getProdTranShiftTime(String bunit,String sfloor,String process,String shiftid){
		
		List<Production_planning_shift_timeDTO> pTimeDTOs=new ArrayList<Production_planning_shift_timeDTO>();
		Production_planning_shift_details pDetails=production_planning_shift_detailsRepository.getShiftDetails(bunit,sfloor,process,shiftid);
		String shftdatetime=Utility.convertDateYYYYMMDD(pDetails.getShift_startdate())+"T"+pDetails.getShift_starttime();
		System.err.println("Got date Time: "+shftdatetime);
		
		int mntnce=0,tshfthrs=0,schedule=0;
		Process_master processMst=process_masterRepository.getProcessDetails(process);
		mntnce=Integer.parseInt(processMst.getShift_mntnce());
		tshfthrs=Integer.parseInt(processMst.getTot_shift_hrs());
		schedule=tshfthrs/mntnce;
		System.err.println("Got Val: "+mntnce+"  "+tshfthrs+"  "+schedule);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(shftdatetime, formatter);
		System.err.println("Got Val1 : "+dateTime);
		for(int j=0;j<schedule;j++) 
		{ 
			String stdatetime=formatter.format(dateTime), enddatetime=formatter.format(dateTime.plusHours(mntnce));
			System.err.println("Shift Time: "+stdatetime.substring(stdatetime.length()-5,stdatetime.length())+"-"+enddatetime.substring(enddatetime.length()-5,enddatetime.length()));
			System.err.println("Shift Time: "+stdatetime.substring(0,stdatetime.length()-6)+" "+stdatetime.substring(stdatetime.length()-5,stdatetime.length())+"-"+Utility.convertDateDDMMYYYY(enddatetime.substring(0,enddatetime.length()-6))+" "+enddatetime.substring(enddatetime.length()-5,enddatetime.length()));
			Production_planning_shift_timeDTO shfts=new Production_planning_shift_timeDTO(Utility.convertDateDDMMYYYY(stdatetime.substring(0,stdatetime.length()-6))+" "+stdatetime.substring(stdatetime.length()-5,stdatetime.length())+"-"+Utility.convertDateDDMMYYYY(enddatetime.substring(0,enddatetime.length()-6))+" "+enddatetime.substring(enddatetime.length()-5,enddatetime.length()));
			pTimeDTOs.add(shfts);
			dateTime=dateTime.plusHours(mntnce);
		}
		
		Type listType = new TypeToken<List<Production_planning_shift_timeDTO>>() {}.getType();
		List<Production_planning_shift_timeDTO> pShiftTimeDTOs = new ModelMapper().map(pTimeDTOs,listType);
		
		return pShiftTimeDTOs;
	}
	
	public List<Production_planning_shift_timeDTO> getProdTranShiftTimeShiftNo(String bunit,String sfloor,String process,String shiftid){
		
		Production_planning_shift_details pDetails=production_planning_shift_detailsRepository.getShiftDetails(bunit,sfloor,process,shiftid);
		List<Production_planning_periodic_date_details> prod=new ArrayList<Production_planning_periodic_date_details>();
		//prod.add(production_planning_periodic_date_detailsRepository.getshiftNoDetails(pDetails.getPpds_id()));
		prod.add(production_planning_periodic_date_detailsRepository.getshiftNoDetailsnew(pDetails.getPpds_id(),pDetails.getPpd_id()));
		
		List<Production_planning_shift_timeDTO> pShiftTimeDTOs=new ArrayList<Production_planning_shift_timeDTO>();
		for(int i=0;i<prod.size();i++)
        {
			Production_planning_shift_timeDTO pShiftTime1 = new  Production_planning_shift_timeDTO();
			pShiftTime1.setShifttime(prod.get(i).getShift1());
			
			pShiftTimeDTOs.add(pShiftTime1);
        }		
		
		return pShiftTimeDTOs;
		
	}

	
	public List<Production_planning_special_date_detailsDTO> getProdPlanSplDateDtls(String prodid,String proddateid)
	{
		List<Production_planning_special_date_details> modelList=new ArrayList<Production_planning_special_date_details>();
		modelList.addAll(production_planning_special_date_detailsRepository.getProduction_planning_special_date_details(prodid,proddateid));
		Type listType=new TypeToken<List<Production_planning_special_date_detailsDTO>>() {}.getType();
		
		List<Production_planning_special_date_detailsDTO> specialDtdtlsDTOs=new ModelMapper().map(modelList,listType);
		
		return specialDtdtlsDTOs;
	}
	
	public List<Production_planning_shop_floor_dtlsDTO> getProcessThruProPlan(String pdate,String bunit,String sfloor,String company)
	{
		List<Production_planning_shop_floor_dtls> modelList=new ArrayList<Production_planning_shop_floor_dtls>();
		modelList.addAll(production_planning_shop_floor_dtlsRepository.getProcessThruProPlan(Utility.convertDateFormat(pdate),bunit,sfloor,company));
		Type listType=new TypeToken<List<Production_planning_shop_floor_dtlsDTO>>() {}.getType();
		
		List<Production_planning_shop_floor_dtlsDTO> floorDtls=new ModelMapper().map(modelList,listType);
		
		return floorDtls;
	}
	
	public List<Production_planning_special_dtlsDTO> getProdPlanSplDtls(String bunit,String sfloor,String company)
	{
		List<Production_planning_special_dtls> modelList=new ArrayList<Production_planning_special_dtls>();
		modelList.addAll(production_planning_special_dtlsRepository.getProdPlanSplDtls(bunit,sfloor,company));
		Type listType=new TypeToken<List<Production_planning_special_dtlsDTO>>() {}.getType();
		
		List<Production_planning_special_dtlsDTO> special_dtlsDTOs=new ModelMapper().map(modelList,listType);
		
		return special_dtlsDTOs;
	}
	
	public Map<String,Object> getProdPlanSplProcessDtls(String bunit,String sfloor,String process,String company)
	{
		Map<String,Object> modelList=production_planning_special_dtlsRepository.getProdPlanSplProcessDtls(bunit,sfloor,process,company);
		
		
		return modelList;
	}
	
	public Production_planning_shop_floor_dtlsDTO getProcessThruProdPlan(String pdate,String bunit,String sfloor,String process,String company)
	{
		Production_planning_shop_floor_dtls modelList=production_planning_shop_floor_dtlsRepository.getProcessThruProdPlan(Utility.convertDateFormat(pdate),bunit,sfloor,process,company);
		
		Type listType=new TypeToken<Production_planning_shop_floor_dtlsDTO>() {}.getType();
		
		Production_planning_shop_floor_dtlsDTO floorDtls=new ModelMapper().map(modelList,listType);
		
		return floorDtls;
	}
	
	
	@Transactional
	public Production_planning update(Production_planning planning,long id) throws JsonParseException, JsonMappingException, IOException 
	{
		Optional<Production_planning> op = production_planningRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(planning.getBusiness_unit().compareTo("0") !=0 && planning.getBusiness_unit().compareTo("") !=0 && planning.getBusiness_unit() !=null) {
			planning.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(planning.getBusiness_unit()).getBusinessunit_name());
		}else {planning.setBusiness_unitname("None");}
		
		planning.setModified_type("INSERTED");
		planning.setInserted_by(op.get().getInserted_by());
		planning.setInserted_on(op.get().getInserted_on());
		planning.setUpdated_by(userRepository.getUserDetails(planning.getUsername()).getName());
		planning.setUpdated_on(ldt);
		planning.setDeleted_by("NA");
		planning.setDeleted_on(ldt);
		
		
		//Update
		production_planning_shop_floor_dtlsRepository.updateProduction_planning_shop_floor_dtls(op.get().getProd_plan_id());
		
		long x=0,z=0;
		
		//For Regular Process ********
		
		Set<Production_planning_shop_floor_dtls> pFloor_dtls=new HashSet<Production_planning_shop_floor_dtls>();
		pFloor_dtls.addAll(planning.getProduction_planning_shop_floor_dtls());
		for(Production_planning_shop_floor_dtls pDtls:pFloor_dtls) 
		{
			if(pDtls.getProcess().compareTo("0") !=0 && pDtls.getProcess().compareTo("") !=0 && pDtls.getProcess() !=null) {
				pDtls.setProcess_name(process_masterRepository.getProcessDetails(pDtls.getProcess()).getProcess_desc());
			}else {pDtls.setProcess_name("None");}
			
			if(pDtls.getProduction().compareTo("0") !=0 && pDtls.getProduction().compareTo("") !=0 && pDtls.getProduction() !=null) {
				pDtls.setProduction_name(bom_masterRepository.getBomDetails(pDtls.getProduction()).getProd_desc());
			}else {pDtls.setProduction_name("None");}
			
			pDtls.setProd_plan_code(planning.getProd_plan_code());
			pDtls.setProd_plan_id(op.get().getProd_plan_id());
			pDtls.setBusiness_unit(planning.getBusiness_unit());
			pDtls.setCompany_id(planning.getCompany_id());
			pDtls.setFin_year(planning.getFin_year());
			pDtls.setModified_type("INSERTED");
			pDtls.setInserted_by(planning.getInserted_by());
			pDtls.setInserted_on(planning.getInserted_on());
			pDtls.setUpdated_by(planning.getUpdated_by());
			pDtls.setUpdated_on(planning.getUpdated_on());
			pDtls.setDeleted_by("NA");
			pDtls.setDeleted_on(ldt);
			
			x++;
			pDtls.setPpd_id(op.get().getProd_plan_id()+Long.toString(x));
			
			//Update
			pDate_detailsRepository.updateProduction_planning_periodic_date_details(op.get().getProd_plan_id());
			
			long n=0;
			//For regular process popup date details ********
			
			Set<Production_planning_periodic_date_details> pDate_details2=new HashSet<Production_planning_periodic_date_details>();
			
			if(pDtls.getProcess_date().compareTo("")==0) {
				pDtls.setProcess_date("{\"periodic_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\",\"todate\":\"\",\"shift1\":\"\"}]}");
			}
			
			String inptData=pDtls.getProcess_date().replace("{\"periodic_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_periodic_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_periodic_date_details[] pDate_details= new ObjectMapper().readValue(inptData, Production_planning_periodic_date_details[].class);
			// List<Production_planning_periodic_date_details> listObjects = Arrays.asList(pDate_details);
			
			Set<Production_planning_periodic_date_details> popupDateSet = new HashSet<>(Arrays.asList(pDate_details));
			
			//for(Production_planning_periodic_date_details ppDateDetails:popupDateSet) 
			//{
				//System.err.println("Got Date 1st:> "+ppDateDetails.getFromdate()+" To: "+ppDateDetails.getTodate());
				//if(ppDateDetails.getFromdate().compareTo("")!=0 && ppDateDetails.getTodate().compareTo("")!=0)
				//{
					//pDate_details2.add(ppDateDetails);
				//}
			//}
			
			pDate_details2.addAll(popupDateSet);
			for(Production_planning_periodic_date_details ppDatedtls:pDate_details2) 
			{
				ppDatedtls.setProd_plan_code(planning.getProd_plan_code());
				ppDatedtls.setProd_plan_id(op.get().getProd_plan_id());
				ppDatedtls.setPpd_id(pDtls.getPpd_id());
				
				ppDatedtls.setCompany_id(pDtls.getCompany_id());
				ppDatedtls.setFin_year(pDtls.getFin_year());
				ppDatedtls.setModified_type("INSERTED");
				ppDatedtls.setInserted_by(pDtls.getInserted_by());
				ppDatedtls.setInserted_on(pDtls.getInserted_on());
				ppDatedtls.setUpdated_by(pDtls.getUpdated_by());
				ppDatedtls.setUpdated_on(pDtls.getUpdated_on());
				ppDatedtls.setDeleted_by("NA");
				ppDatedtls.setDeleted_on(ldt);
				
				n++;
				ppDatedtls.setPpds_id("S"+op.get().getProd_plan_id()+Long.toString(n));
				
				//For regular process popup date shift details ********
				
				//Update
				production_planning_shift_detailsRepository.updateProduction_planning_shift_details(op.get().getProd_plan_id());
				
				String shifts=ppDatedtls.getShift1();
				String noshifts[]=null;
				
				if(shifts.compareTo("")==0) {shifts="0,0";}
				noshifts=shifts.split(",");
				String shift_enddate="0",shift_endtime="0",shift_startdate="0",shift_starttime="0";
				
				Set<Production_planning_shift_details> pShift_details=new HashSet<Production_planning_shift_details>();
				
				for (int j=0;j<noshifts.length;j++) 
				{ 
					//Production_planning_shift_details shfts=new Production_planning_shift_details(ppDatedtls.getProd_plan_id(),
				/*	ppDatedtls.getProd_plan_code(),
					ppDatedtls.getPpd_id(),
					ppDatedtls.getPpds_id(),
					pDtls.getShop_floor(),
					pDtls.getProcess(),
					pDtls.getProduction(),
					ppDatedtls.getFromdate(),
					ppDatedtls.getTodate(),
					Integer.parseInt(noshifts[j]),
					ppDatedtls.getCompany_id(),
					ppDatedtls.getFin_year(),
					ppDatedtls.getModified_type(),
					ppDatedtls.getInserted_on(),
					ppDatedtls.getInserted_by(),
					ppDatedtls.getUpdated_on(),
					ppDatedtls.getUpdated_by(),
					ppDatedtls.getDeleted_on(),
					ppDatedtls.getDeleted_by(),
					ppDatedtls);
	           */   
					Production_planning_shift_details shfts=new Production_planning_shift_details();
					shfts.setProd_plan_id(ppDatedtls.getProd_plan_id());
					shfts.setProd_plan_code(ppDatedtls.getProd_plan_code());
					shfts.setPpd_id(ppDatedtls.getPpd_id());
					shfts.setPpds_id(ppDatedtls.getPpds_id());
					shfts.setPp_shiftid(pDtls.getShop_floor());
					shfts.setBusiness_unit(planning.getBusiness_unit());
					shfts.setShop_floor(pDtls.getShop_floor());
					shfts.setProcess(pDtls.getProcess());
					shfts.setProduction(pDtls.getProduction());
					shfts.setFromdate(ppDatedtls.getFromdate());
					shfts.setTodate(ppDatedtls.getTodate());
					shfts.setShift(Integer.parseInt(noshifts[j]));
					shfts.setShift_startdate(shift_startdate);
					shfts.setShift_starttime(shift_starttime);
					shfts.setShift_endtime(shift_endtime);
					shfts.setShift_enddate(shift_enddate);
					shfts.setCompany_id(ppDatedtls.getCompany_id());
					shfts.setFin_year(ppDatedtls.getFin_year());
					shfts.setModified_type(ppDatedtls.getModified_type());
					shfts.setInserted_on(ppDatedtls.getInserted_on());
					shfts.setInserted_by(ppDatedtls.getInserted_by());
					shfts.setUpdated_on(ppDatedtls.getUpdated_on());
					shfts.setUpdated_by(ppDatedtls.getUpdated_by());
					shfts.setDeleted_by(ppDatedtls.getDeleted_by());
					shfts.setDeleted_on(ppDatedtls.getDeleted_on());
					shfts.setPDate_details(ppDatedtls);
					
					
					
					
					
					pShift_details.add(shfts);
				}
				ppDatedtls.setProduction_planning_shift_details(pShift_details);
			}
			pDtls.setPeriodic_date_details(pDate_details2);
			
		}
		planning.setProduction_planning_shop_floor_dtls(pFloor_dtls);
		
		//For Special Process ********
		
		//Update
		production_planning_special_dtlsRepository.updateProduction_planning_special_dtls(op.get().getProd_plan_id());
		
		Set<Production_planning_special_dtls> pSpecial_dtls=new HashSet<Production_planning_special_dtls>();
		pSpecial_dtls.addAll(planning.getProduction_planning_special_dtls());
		for(Production_planning_special_dtls pSpecial:pSpecial_dtls) 
		{
			if(pSpecial.getProcess().compareTo("0") !=0 && pSpecial.getProcess().compareTo("") !=0 && pSpecial.getProcess() !=null) {
				pSpecial.setProcess_name(process_masterRepository.getProcessDetails(pSpecial.getProcess()).getProcess_desc());
			}else {pSpecial.setProcess_name("None");}
			
			if(pSpecial.getProduction().compareTo("0") !=0 && pSpecial.getProduction().compareTo("") !=0 && pSpecial.getProduction() !=null) {
				pSpecial.setProduction_name(bom_masterRepository.getBomDetails(pSpecial.getProduction()).getProd_desc());
			}else {pSpecial.setProduction_name("None");}
			
			pSpecial.setProd_plan_code(planning.getProd_plan_code());
			pSpecial.setProd_plan_id(op.get().getProd_plan_id());
			pSpecial.setBusiness_unit(planning.getBusiness_unit());
			pSpecial.setCompany_id(planning.getCompany_id());
			pSpecial.setFin_year(planning.getFin_year());
			pSpecial.setModified_type("INSERTED");
			pSpecial.setInserted_by(planning.getInserted_by());
			pSpecial.setInserted_on(planning.getInserted_on());
			pSpecial.setUpdated_by(planning.getUpdated_by());
			pSpecial.setUpdated_on(planning.getUpdated_on());
			pSpecial.setDeleted_by("NA");
			pSpecial.setDeleted_on(ldt);
			
			z++;
			pSpecial.setPps_id(op.get().getProd_plan_id()+Long.toString(z));
			
			//For special process popup date details ********
			
			//Update
			production_planning_special_date_detailsRepository.updateProduction_planning_special_date_details(op.get().getProd_plan_id());
			
			Set<Production_planning_special_date_details> pSpecial_date_details=new HashSet<Production_planning_special_date_details>();
			
			if(pSpecial.getProcess_date().compareTo("")==0) {
				pSpecial.setProcess_date("{\"special_date_details\":[{\"checkbox\":false,\"sl_no\":1,\"fromdate\":\"\"}]}");
			}
			
			String inptData=pSpecial.getProcess_date().replace("{\"special_date_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			//Production_planning_special_date_details is class.
			//inptData must be mapped with the class's entity
			@SuppressWarnings("unchecked")
			Production_planning_special_date_details[] pSpecial_date_dtls= new ObjectMapper().readValue(inptData, Production_planning_special_date_details[].class);
			
			Set<Production_planning_special_date_details> set2 = new HashSet<>(Arrays.asList(pSpecial_date_dtls));
			pSpecial_date_details.addAll(set2);
			
			for(Production_planning_special_date_details pSplDateDtls:pSpecial_date_details) 
			{
				pSplDateDtls.setProd_plan_code(planning.getProd_plan_code());
				pSplDateDtls.setProd_plan_id(op.get().getProd_plan_id());
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
			
		}
		planning.setProduction_planning_special_dtls(pSpecial_dtls);
		
		
		if(op.isPresent())
		{
			planning.setId(id);
		}
		return production_planningRepository.save(planning);
	}
	
	public Production_planning_up checkdaterangeupdate(String date) 
	{
		Production_planning_up res = new Production_planning_up();
		String dategroup []=date.split("-");
		
		String fromdate=dategroup[2]+"-"+dategroup[1]+"-"+dategroup[0];
		
		Boolean check=production_planning_shift_detailsRepository.checkdate(fromdate);
		if(check==true) 
		{
			String prodid=production_planning_shift_detailsRepository.checkdateid(fromdate).getProd_plan_id();
			Production_planning pro= production_planningRepository.findByProdPlanId(prodid);
			
			res.setId(pro.getId());
			res.setProd_plan_id(pro.getProd_plan_id());
		}
		else 
		{
			int i= 0;
			res.setId(Long.valueOf(i));
		}
		
		return res;
	}
	
	@Transactional
	 public Production_planning deleteProductionPlanning(Production_planning pPlanning,long id)
	{
		Optional<Production_planning> op = production_planningRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();

		pPlanning.setModified_type("DELETED");
		pPlanning.setInserted_by(op.get().getInserted_by());
		pPlanning.setInserted_on(op.get().getInserted_on());
		pPlanning.setUpdated_by(op.get().getUpdated_by());
		pPlanning.setUpdated_on(op.get().getUpdated_on());
		pPlanning.setDeleted_by(userRepository.getUserDetails(pPlanning.getUsername()).getName());
		pPlanning.setDeleted_on(ldt);
		
		
		if(op.isPresent()) {
			pPlanning.setId(id);
		}
		
		
		production_planning_shop_floor_dtlsRepository.delete(op.get().getProd_plan_id());
		production_planning_special_dtlsRepository.delete(op.get().getProd_plan_id());
		
		return production_planningRepository.save(pPlanning);
	}
	
	public List<Map<String, Object>> getProdInputReport(String business_unit,String shop_floor,String fromdate,String todate)
	{
		List<Map<String, Object>> inputReport=production_planningRepository.getProdInputReport(shop_floor,fromdate,todate);
		 
	     return inputReport;
	}
	public List<Map<String, Object>> getProdOutputReport(String business_unit,String shop_floor,String fromdate,String todate)
	{
		List<Map<String, Object>> outputReport=production_planningRepository.getProdOutputReport(shop_floor,fromdate,todate);
		 
	     return outputReport;
	}
}
