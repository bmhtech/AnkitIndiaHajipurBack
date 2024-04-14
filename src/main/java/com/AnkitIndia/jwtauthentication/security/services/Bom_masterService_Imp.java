package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
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

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Bom_input_details;
import com.AnkitIndia.jwtauthentication.model.Bom_master;
import com.AnkitIndia.jwtauthentication.model.Bom_output_details;
import com.AnkitIndia.jwtauthentication.model.Bom_resource_cost;
import com.AnkitIndia.jwtauthentication.model.Production_transaction;
import com.AnkitIndia.jwtauthentication.repository.Bom_input_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Bom_output_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Bom_resource_costRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planningRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_listDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_output_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_resource_costDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class Bom_masterService_Imp implements Bom_masterService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository;
	
	@Autowired
	Bom_input_detailsRepository bom_input_detailsRepository;
	
	@Autowired
	Bom_output_detailsRepository bom_output_detailsRepository;
	
	@Autowired
	Bom_resource_costRepository bom_resource_costRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Production_planningRepository production_planningRepository;
	
	
	public SequenceIdDTO getBMSequenceId(String prefix,String bunit,String sfloor,String company) {
		Long slno=0L;String fprefix="",buprefix="",sfprefix="";
		
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(bunit).getBusinessunit_name();
		sfprefix=shop_floor_masterRepository.getShopFloorDtls(sfloor).getShop_floor_name();
		//String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		
		fprefix=prefix+"/"+buprefix.substring(0,3)+"/"+sfprefix.substring(0,3).toUpperCase()+"/";
		
		System.err.println("Code: > "+fprefix);
		String gen_sno=bom_masterRepository.getBMSequenceId(fprefix,bunit,sfloor,company);
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
	public Bom_master save(Bom_master bom_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(bom_masterRepository.countId() != null ) {
			slno=Long.parseLong(bom_masterRepository.countId());
		}
		String prefix="BM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		
		/*End checking transaction no for unique */
		
		if(bom_master.getBusiness_unit().compareTo("0") !=0 && bom_master.getBusiness_unit().compareTo("") !=0 && bom_master.getBusiness_unit() !=null) {
			bom_master.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(bom_master.getBusiness_unit()).getBusinessunit_name());
		}else {bom_master.setBusiness_unitname("None");}
		
		if(bom_master.getShop_floor().compareTo("0") !=0 && bom_master.getShop_floor().compareTo("") !=0 && bom_master.getShop_floor() !=null) {
			bom_master.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(bom_master.getShop_floor()).getShop_floor_name());
		}else {bom_master.setShop_floorname("None");}
		
		bom_master.setProduction_id(gen_sno);
		bom_master.setModified_type("INSERTED");
		bom_master.setInserted_by(userRepository.getUserDetails(bom_master.getUsername()).getName());
		bom_master.setInserted_on(ldt);
		bom_master.setUpdated_by("NA");
		bom_master.setUpdated_on(ldt);
		bom_master.setDeleted_by("NA");
		bom_master.setDeleted_on(ldt);
		
		Set<Bom_input_details> bInput=new HashSet<Bom_input_details>();
		bInput.addAll(bom_master.getBom_input_details());
		for(Bom_input_details bInput_details:bInput) 
		{
			bInput_details.setProduction_code(bom_master.getProduction_code());
			bInput_details.setProduction_id(gen_sno);
			if(bInput_details.getItem().compareTo("")!=0 && bInput_details.getItem().compareTo("0")!=0) {
				bInput_details.setItem_name(item_masterRepository.itemNameById(bInput_details.getItem()).getItem_name());
			}
			if(bInput_details.getPacking().compareTo("")!=0 && bInput_details.getPacking().compareTo("0")!=0) {
				bInput_details.setPacking_name(item_masterRepository.itemNameById(bInput_details.getPacking()).getItem_name());
			}
			bInput_details.setCompany_id(bom_master.getCompany_id());
			bInput_details.setFin_year(bom_master.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(bom_master.getInserted_by());
			bInput_details.setInserted_on(bom_master.getInserted_on());
			bInput_details.setUpdated_by(bom_master.getUpdated_by());
			bInput_details.setUpdated_on(bom_master.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);
		}
		bom_master.setBom_input_details(bInput);
		
		Set<Bom_output_details> bOutput=new HashSet<Bom_output_details>();
		bOutput.addAll(bom_master.getBom_output_details());
		for(Bom_output_details bOutput_details:bOutput) 
		{
			bOutput_details.setProduction_code(bom_master.getProduction_code());
			bOutput_details.setProduction_id(gen_sno);
			if(bOutput_details.getItem().compareTo("")!=0 && bOutput_details.getItem().compareTo("0")!=0) {
				bOutput_details.setItem_name(item_masterRepository.itemNameById(bOutput_details.getItem()).getItem_name());
			}
			if(bOutput_details.getPacking().compareTo("")!=0 && bOutput_details.getPacking().compareTo("0")!=0) {
				bOutput_details.setPacking_name(item_masterRepository.itemNameById(bOutput_details.getPacking()).getItem_name());
			}
			bOutput_details.setCompany_id(bom_master.getCompany_id());
			bOutput_details.setFin_year(bom_master.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(bom_master.getInserted_by());
			bOutput_details.setInserted_on(bom_master.getInserted_on());
			bOutput_details.setUpdated_by(bom_master.getUpdated_by());
			bOutput_details.setUpdated_on(bom_master.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
		}
		bom_master.setBom_output_details(bOutput);
		
		Set<Bom_resource_cost> bReCost=new HashSet<Bom_resource_cost>();
		bReCost.addAll(bom_master.getBom_resource_cost());
		for(Bom_resource_cost bCost:bReCost) 
		{
			bCost.setProduction_code(bom_master.getProduction_code());
			bCost.setProduction_id(gen_sno);
			bCost.setCompany_id(bom_master.getCompany_id());
			bCost.setFin_year(bom_master.getFin_year());
			bCost.setModified_type("INSERTED");
			bCost.setInserted_by(bom_master.getInserted_by());
			bCost.setInserted_on(bom_master.getInserted_on());
			bCost.setUpdated_by(bom_master.getUpdated_by());
			bCost.setUpdated_on(bom_master.getUpdated_on());
			bCost.setDeleted_by("NA");
			bCost.setDeleted_on(ldt);
		}
		bom_master.setBom_resource_cost(bReCost);
		
		return bom_masterRepository.save(bom_master);
	}
	
	public List<Bom_master> findAllBom()
	{
		List<Bom_master> processList=new ArrayList<Bom_master>();
		processList.addAll(bom_masterRepository.findAllBom());
		
		return processList;
	}
	public List<Bom_listDTO> findAllBomList()
	{
		List<Bom_master> processList=new ArrayList<Bom_master>();
		List<Bom_listDTO> processList1=new ArrayList<Bom_listDTO>();
		
		processList.addAll(bom_masterRepository.findAllBom());
		
		processList.forEach(e->{
			Bom_listDTO bomlist = new Bom_listDTO();
			bomlist.setId(e.getId());
			bomlist.setProduction_id(e.getProduction_id());
			bomlist.setProduction_code(e.getProduction_code());
			bomlist.setBusiness_unitname(e.getBusiness_unitname());
			bomlist.setShop_floorname(e.getShop_floorname());
			bomlist.setProd_desc(e.getProd_desc());
			bomlist.setEntry_mode(e.getEntry_mode());
			bomlist.setStatus(production_planningRepository.checkBom(e.getProduction_id()));
			processList1.add(bomlist);
		});
		return processList1;
	}
	
	public Bom_master findOne(long id)
	{
		Optional<Bom_master> op=this.bom_masterRepository.findById(id);
		return op.get();
	}

	public List<Bom_masterDTO> getBoms()
	{
		 List<Bom_master> modelList=  bom_masterRepository.findAllBom();
		 
		 Type listType=new TypeToken<List<Bom_masterDTO>>() {}.getType();
		 
		 List<Bom_masterDTO> shopF=new ModelMapper().map(modelList, listType);
		 
		 return shopF;
	}
	
	public List<Bom_masterDTO> getBomThruProcess(String bunit,String sfloor,String process,String company)
	{
		 System.err.println("Got val: "+bunit+","+sfloor+","+process+","+company);
		 List<Bom_master> modelList=bom_masterRepository.getBomThruProcess(bunit,sfloor,process,company);
		 
		 Type listType=new TypeToken<List<Bom_masterDTO>>() {}.getType();
		 
		 List<Bom_masterDTO> bomDtls=new ModelMapper().map(modelList, listType);
		 
		 return bomDtls;
	}
	
	public Bom_masterDTO getBomDetails(String bomid,String company)
	{
		 Bom_master modelList=bom_masterRepository.getBomDetails(bomid,company);
		 
		 Type listType=new TypeToken<Bom_masterDTO>() {}.getType();
		 
		 Bom_masterDTO bomDtls=new ModelMapper().map(modelList, listType);
		 
		 return bomDtls;
	}
	
	public List<Bom_input_detailsDTO> getBomInputDetails(String prodid)
	{
		List<Bom_input_details> modelList=new ArrayList<Bom_input_details>();
		modelList.addAll(bom_input_detailsRepository.getBomInputDetails(prodid));
		Type listType=new TypeToken<List<Bom_input_detailsDTO>>() {}.getType();
		
		List<Bom_input_detailsDTO> inputDtls=new ModelMapper().map(modelList,listType);
		
		return inputDtls;
	}
	
	public List<Map<String,Object>> getBomInputDetailsNew(String prodid)
	{
		List<Map<String,Object>> bomInput =new ArrayList<Map<String,Object>>();
		bomInput.addAll(bom_input_detailsRepository.getBomInputDetailsnew(prodid));
		
		return bomInput;
	}
	
	public Bom_input_detailsDTO getBomInputDtls(String prodid,String itemid,String packingid)
	{
		Bom_input_details modelList=bom_input_detailsRepository.getBomInputDtls(prodid,itemid,packingid);
		System.out.println("list data:"+modelList);
		
		Type listType=new TypeToken<Bom_input_detailsDTO>() {}.getType();
		Bom_input_detailsDTO inputDtls=new ModelMapper().map(modelList,listType);
		
		return inputDtls;
	}
	
	public List<Bom_output_detailsDTO> getBomOutputDetails(String prodid)
	{
		List<Bom_output_details> modelList=new ArrayList<Bom_output_details>();
		modelList.addAll(bom_output_detailsRepository.getBom_output_details(prodid));
		Type listType=new TypeToken<List<Bom_output_detailsDTO>>() {}.getType();
		
		List<Bom_output_detailsDTO> outputDtls=new ModelMapper().map(modelList,listType);
		
		return outputDtls;
	}
	
	/*public Bom_output_detailsDTO getBomOutputDtls(String prodid,String itemid)
	{
		Bom_output_details modelList=bom_output_detailsRepository.getBomOutputDtls(prodid,itemid);
		Type listType=new TypeToken<Bom_output_detailsDTO>() {}.getType();
		
		Bom_output_detailsDTO outputDtls=new ModelMapper().map(modelList,listType);
		
		return outputDtls;
	}*/
	
	public Bom_output_detailsDTO getBomOutputDtls(String prodid,String itemid,String packingid)
	{
		Bom_output_details modelList=bom_output_detailsRepository.getBomOutputDtls(prodid,itemid,packingid);
		Type listType=new TypeToken<Bom_output_detailsDTO>() {}.getType();
		
		Bom_output_detailsDTO outputDtls=new ModelMapper().map(modelList,listType);
		
		return outputDtls;
	}
	
	public List<Bom_resource_costDTO> getBomResourceCost(String prodid)
	{
		List<Bom_resource_cost> modelList=new ArrayList<Bom_resource_cost>();
		modelList.addAll(bom_resource_costRepository.getBom_resource_cost(prodid));
		Type listType=new TypeToken<List<Bom_resource_costDTO>>() {}.getType();
		
		List<Bom_resource_costDTO> reCostDtls=new ModelMapper().map(modelList,listType);
		
		return reCostDtls;
	}
	
	@Transactional
	public Bom_master update(Bom_master bom_master,long id)
	{
		Optional<Bom_master> op = bom_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(bom_master.getBusiness_unit().compareTo("0") !=0 && bom_master.getBusiness_unit().compareTo("") !=0 && bom_master.getBusiness_unit() !=null) {
			bom_master.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(bom_master.getBusiness_unit()).getBusinessunit_name());
		}else {bom_master.setBusiness_unitname("None");}
		
		if(bom_master.getShop_floor().compareTo("0") !=0 && bom_master.getShop_floor().compareTo("") !=0 && bom_master.getShop_floor() !=null) {
			bom_master.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(bom_master.getShop_floor()).getShop_floor_name());
		}else {bom_master.setShop_floorname("None");}
		
		bom_master.setModified_type("INSERTED");
		bom_master.setInserted_by(op.get().getInserted_by());
		bom_master.setInserted_on(op.get().getInserted_on());
		bom_master.setUpdated_by(userRepository.getUserDetails(bom_master.getUsername()).getName());
		bom_master.setUpdated_on(ldt);
		bom_master.setDeleted_by("NA");
		bom_master.setDeleted_on(ldt);
		
		//Update
		bom_input_detailsRepository.updateBom_input_details(op.get().getProduction_id());
		
		Set<Bom_input_details> bInput=new HashSet<Bom_input_details>();
		bInput.addAll(bom_master.getBom_input_details());
		for(Bom_input_details bInput_details:bInput) 
		{
			bInput_details.setProduction_code(bom_master.getProduction_code());
			bInput_details.setProduction_id(op.get().getProduction_id());
			if(bInput_details.getItem().compareTo("")!=0 && bInput_details.getItem().compareTo("0")!=0) {
				bInput_details.setItem_name(item_masterRepository.itemNameById(bInput_details.getItem()).getItem_name());
			}
			if(bInput_details.getPacking().compareTo("")!=0 && bInput_details.getPacking().compareTo("0")!=0) {
				bInput_details.setPacking_name(item_masterRepository.itemNameById(bInput_details.getPacking()).getItem_name());
			}
			bInput_details.setCompany_id(bom_master.getCompany_id());
			bInput_details.setFin_year(bom_master.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(bom_master.getInserted_by());
			bInput_details.setInserted_on(bom_master.getInserted_on());
			bInput_details.setUpdated_by(bom_master.getUpdated_by());
			bInput_details.setUpdated_on(bom_master.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);
		}
		bom_master.setBom_input_details(bInput);
		
		//Update
		bom_output_detailsRepository.updateBom_output_details(op.get().getProduction_id());
		
		Set<Bom_output_details> bOutput=new HashSet<Bom_output_details>();
		bOutput.addAll(bom_master.getBom_output_details());
		for(Bom_output_details bOutput_details:bOutput) 
		{
			bOutput_details.setProduction_code(bom_master.getProduction_code());
			bOutput_details.setProduction_id(op.get().getProduction_id());
			if(bOutput_details.getItem().compareTo("")!=0 && bOutput_details.getItem().compareTo("0")!=0) {
				bOutput_details.setItem_name(item_masterRepository.itemNameById(bOutput_details.getItem()).getItem_name());
			}
			if(bOutput_details.getPacking().compareTo("")!=0 && bOutput_details.getPacking().compareTo("0")!=0) {
				bOutput_details.setPacking_name(item_masterRepository.itemNameById(bOutput_details.getPacking()).getItem_name());
			}
			bOutput_details.setCompany_id(bom_master.getCompany_id());
			bOutput_details.setFin_year(bom_master.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(bom_master.getInserted_by());
			bOutput_details.setInserted_on(bom_master.getInserted_on());
			bOutput_details.setUpdated_by(bom_master.getUpdated_by());
			bOutput_details.setUpdated_on(bom_master.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
		}
		bom_master.setBom_output_details(bOutput);
		
		//Update
		bom_resource_costRepository.updateBom_resource_cost(op.get().getProduction_id());
		
		Set<Bom_resource_cost> bReCost=new HashSet<Bom_resource_cost>();
		bReCost.addAll(bom_master.getBom_resource_cost());
		for(Bom_resource_cost bCost:bReCost) 
		{
			bCost.setProduction_code(bom_master.getProduction_code());
			bCost.setProduction_id(op.get().getProduction_id());
			bCost.setCompany_id(bom_master.getCompany_id());
			bCost.setFin_year(bom_master.getFin_year());
			bCost.setModified_type("INSERTED");
			bCost.setInserted_by(bom_master.getInserted_by());
			bCost.setInserted_on(bom_master.getInserted_on());
			bCost.setUpdated_by(bom_master.getUpdated_by());
			bCost.setUpdated_on(bom_master.getUpdated_on());
			bCost.setDeleted_by("NA");
			bCost.setDeleted_on(ldt);
		}
		bom_master.setBom_resource_cost(bReCost);
		
		if(op.isPresent())
		{
			bom_master.setId(id);
		}
		return bom_masterRepository.save(bom_master);
	}
	
	@Transactional
	public Bom_master deleteBomMaster(Bom_master bommaster,long id)
		
	 	{
			Optional<Bom_master> op = bom_masterRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Bom_master bom=op.get();
			
			bom.setModified_type("DELETED");
			bom.setInserted_by(op.get().getInserted_by());
			bom.setInserted_on(op.get().getInserted_on());
			bom.setUpdated_by(op.get().getUpdated_by());
			bom.setUpdated_on(op.get().getUpdated_on());
			bom.setDeleted_by(userRepository.getUserDetails(bom.getUsername()).getName());
			bom.setDeleted_on(ldt);
			
			bom_input_detailsRepository.bomInputUpdate(op.get().getProduction_id());
			bom_output_detailsRepository.bomOutputUpdate(op.get().getProduction_id());
			bom_resource_costRepository.bomResourceCostUpdate(op.get().getProduction_id());
			
			
			return bom_masterRepository.save(bom);	
	 	}
	
	
}
