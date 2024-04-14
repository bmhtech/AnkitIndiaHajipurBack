package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_ProductionReg;
import com.AnkitIndia.Exporttotally.Tallyrequest_SalesInvoice;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Bom_input_details;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_popup_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_output_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_output_popup_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls;
import com.AnkitIndia.jwtauthentication.repository.Bom_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_planning_shift_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_input_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_input_popup_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_output_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Production_transaction_output_popup_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_output_detailsDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Production_transactionService_Imp implements Production_transactionService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Production_transactionRepository production_transactionRepository;
	
	@Autowired
	Production_transaction_input_detailsRepository production_transaction_input_detailsRepository;
	
	@Autowired
	Production_transaction_output_detailsRepository production_transaction_output_detailsRepository;
	
	@Autowired
	Production_transaction_input_popup_detailsRepository production_transaction_input_popup_detailsRepository;
	
	@Autowired
	Production_transaction_output_popup_detailsRepository production_transaction_output_popup_detailsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Bom_masterRepository bom_masterRepository; 
	
	@Autowired
	Production_planning_shift_detailsRepository production_planning_shift_detailsRepository; 
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	public SequenceIdDTO getPTSequenceId(String businessunit,String sfloor,String prefix,String company) {
		Long slno=0L;String fprefix="",code="",buprefix="",sfprefix="";
		code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(businessunit).getBusinessunit_name();
		sfprefix=shop_floor_masterRepository.getShopFloorDtls(sfloor).getShop_floor_name();
		
		fprefix=prefix+"/"+code+"/"+buprefix.substring(0,3)+"/"+sfprefix.substring(0,3).toUpperCase()+"/";;
		
		System.err.println("Code: > "+fprefix);
		String gen_sno=production_transactionRepository.getPTSequenceId(businessunit,sfloor,fprefix,company);
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
	public Production_transaction save(Production_transaction pTransaction) throws JsonParseException, JsonMappingException, IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(production_transactionRepository.countId() != null ) {
			slno=Long.parseLong(production_transactionRepository.countId());
		}
		String prefix="PT";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		
		/*End checking transaction no for unique */
		
		if(pTransaction.getBusiness_unit().compareTo("0") !=0 && pTransaction.getBusiness_unit().compareTo("") !=0 && pTransaction.getBusiness_unit() !=null) {
			pTransaction.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pTransaction.getBusiness_unit()).getBusinessunit_name());
		}else {pTransaction.setBusiness_unitname("None");}
		
		if(pTransaction.getShop_floor().compareTo("0") !=0 && pTransaction.getShop_floor().compareTo("") !=0 && pTransaction.getShop_floor() !=null) {
			pTransaction.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pTransaction.getShop_floor()).getShop_floor_name());
		}else {pTransaction.setShop_floorname("None");}
		
		/*if(pTransaction.getProd_process().compareTo("0") !=0 && pTransaction.getProd_process().compareTo("") !=0 && pTransaction.getProd_process() !=null) {
			pTransaction.setProd_processname(process_masterRepository.getProcessDetails(pTransaction.getProd_process()).getProcess_desc());
		}else {pTransaction.setProd_processname("None");}*/
		
		if(pTransaction.getProd_desc().compareTo("0") !=0 && pTransaction.getProd_desc().compareTo("") !=0 && pTransaction.getProd_desc() !=null) {
			pTransaction.setProd_description(bom_masterRepository.getBomDetails(pTransaction.getProd_desc(),pTransaction.getCompany_id()).getProd_desc());
		}else {pTransaction.setProd_description("None");}
		
		production_planning_shift_detailsRepository.productionPlanShiftDetailsUpdate(pTransaction.getProd_process());
		
		pTransaction.setProd_trans_id(gen_sno);
		pTransaction.setModified_type("INSERTED");
		pTransaction.setInserted_by(userRepository.getUserDetails(pTransaction.getUsername()).getName());
		pTransaction.setInserted_on(ldt);
		pTransaction.setUpdated_by("NA");
		pTransaction.setUpdated_on(ldt);
		pTransaction.setDeleted_by("NA");
		pTransaction.setDeleted_on(ldt);
		
		long x=0,y=0;
		
		Set<Production_transaction_input_details> bInput=new HashSet<Production_transaction_input_details>();
		bInput.addAll(pTransaction.getProduction_transaction_input_details());
		for(Production_transaction_input_details bInput_details:bInput) 
		{
			System.err.println("Got Input:> "+bInput_details.getInput_qty());
			
			if(bInput_details.getItem().compareTo("0") !=0 && bInput_details.getItem().compareTo("") !=0 && bInput_details.getItem() !=null) {
				bInput_details.setItem_name(item_masterRepository.itemNameById(bInput_details.getItem()).getItem_name());
			}else {bInput_details.setItem_name("None");}
			
			if(bInput_details.getPacking().compareTo("0") !=0 && bInput_details.getPacking().compareTo("") !=0 && bInput_details.getPacking() !=null) {
				bInput_details.setPacking_name(item_masterRepository.itemNameById(bInput_details.getPacking()).getItem_name());
			}else {bInput_details.setPacking_name("None");}
			
			
			bInput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bInput_details.setProd_trans_id(gen_sno);
			bInput_details.setCompany_id(pTransaction.getCompany_id());
			bInput_details.setFin_year(pTransaction.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(pTransaction.getInserted_by());
			bInput_details.setInserted_on(pTransaction.getInserted_on());
			bInput_details.setUpdated_by(pTransaction.getUpdated_by());
			bInput_details.setUpdated_on(pTransaction.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);
			
			x++;
			bInput_details.setProd_popupid(gen_sno+Long.toString(x));
			
			if(bInput_details.getInput_qty().compareTo("")==0) {
				bInput_details.setInput_qty("{\"prod_input_details\":[{\"checkbox\":false,\"sl_no\":1,\"shifttime\":\"\",\"itemqty\":\"\",\"packingqty\":\"\"}]}");
			}
			String inptData=bInput_details.getInput_qty().replace("{\"prod_input_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			Set<Production_transaction_input_popup_details> pInput_popup_details=new HashSet<Production_transaction_input_popup_details>();
			
			@SuppressWarnings("unchecked")
			Production_transaction_input_popup_details[] pDetails= new ObjectMapper().readValue(inptData, Production_transaction_input_popup_details[].class);
			Set<Production_transaction_input_popup_details> set = new HashSet<>(Arrays.asList(pDetails));
			pInput_popup_details.addAll(set);
			
			for(Production_transaction_input_popup_details popup_details:pInput_popup_details) 
			{
				popup_details.setProd_trans_code(pTransaction.getProd_trans_code());
				popup_details.setProd_trans_id(gen_sno);
				popup_details.setProd_popupid(bInput_details.getProd_popupid());
				
				popup_details.setCompany_id(bInput_details.getCompany_id());
				popup_details.setFin_year(bInput_details.getFin_year());
				popup_details.setModified_type("INSERTED");
				popup_details.setInserted_by(bInput_details.getInserted_by());
				popup_details.setInserted_on(bInput_details.getInserted_on());
				popup_details.setUpdated_by(bInput_details.getUpdated_by());
				popup_details.setUpdated_on(bInput_details.getUpdated_on());
				popup_details.setDeleted_by("NA");
				popup_details.setDeleted_on(ldt);
			}
			bInput_details.setProduction_transaction_input_popup_details(pInput_popup_details);
		}
		pTransaction.setProduction_transaction_input_details(bInput);
		
		Set<Production_transaction_output_details> bOutput=new HashSet<Production_transaction_output_details>();
		bOutput.addAll(pTransaction.getProduction_transaction_output_details());
		for(Production_transaction_output_details bOutput_details:bOutput) 
		{
			System.err.println("Got Output:> "+bOutput_details.getOutput_qty());
			
			if(bOutput_details.getItem().compareTo("0") !=0 && bOutput_details.getItem().compareTo("") !=0 && bOutput_details.getItem() !=null) {
				bOutput_details.setItem_name(item_masterRepository.itemNameById(bOutput_details.getItem()).getItem_name());
			}else {bOutput_details.setItem_name("None");}
			
			if(bOutput_details.getPacking().compareTo("0") !=0 && bOutput_details.getPacking().compareTo("") !=0 && bOutput_details.getPacking() !=null) {
				bOutput_details.setPacking_name(item_masterRepository.itemNameById(bOutput_details.getPacking()).getItem_name());
			}else {bOutput_details.setPacking_name("None");}
			
			bOutput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bOutput_details.setProd_trans_id(gen_sno);
			bOutput_details.setCompany_id(pTransaction.getCompany_id());
			bOutput_details.setFin_year(pTransaction.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(pTransaction.getInserted_by());
			bOutput_details.setInserted_on(pTransaction.getInserted_on());
			bOutput_details.setUpdated_by(pTransaction.getUpdated_by());
			bOutput_details.setUpdated_on(pTransaction.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
			
			y++;
			bOutput_details.setProd_popupid(gen_sno+Long.toString(y));
			
			if(bOutput_details.getOutput_qty().compareTo("")==0) {
				bOutput_details.setOutput_qty("{\"prod_output_details\":[{\"checkbox\":false,\"sl_no\":1,\"shifttime\":\"\",\"itemqty\":\"\",\"packingqty\":\"\"}]}");
			}
			
			String inputData=bOutput_details.getOutput_qty().replace("{\"prod_output_details\":", "");
			inputData=inputData.substring(0, inputData.length()-1);
			
			Set<Production_transaction_output_popup_details> pOutput_popup_details=new HashSet<Production_transaction_output_popup_details>();
			
			@SuppressWarnings("unchecked")
			Production_transaction_output_popup_details[] popupDetails= new ObjectMapper().readValue(inputData, Production_transaction_output_popup_details[].class);
			Set<Production_transaction_output_popup_details> popupSet = new HashSet<>(Arrays.asList(popupDetails));
			pOutput_popup_details.addAll(popupSet);
			
			for(Production_transaction_output_popup_details popup_details:pOutput_popup_details) 
			{
				popup_details.setProd_trans_code(pTransaction.getProd_trans_code());
				popup_details.setProd_trans_id(gen_sno);
				popup_details.setProd_popupid(bOutput_details.getProd_popupid());
				
				popup_details.setCompany_id(bOutput_details.getCompany_id());
				popup_details.setFin_year(bOutput_details.getFin_year());
				popup_details.setModified_type("INSERTED");
				popup_details.setInserted_by(bOutput_details.getInserted_by());
				popup_details.setInserted_on(bOutput_details.getInserted_on());
				popup_details.setUpdated_by(bOutput_details.getUpdated_by());
				popup_details.setUpdated_on(bOutput_details.getUpdated_on());
				popup_details.setDeleted_by("NA");
				popup_details.setDeleted_on(ldt);
			}
			bOutput_details.setProduction_transaction_output_popup_details(pOutput_popup_details);
			
		}
		pTransaction.setProduction_transaction_output_details(bOutput);
		
		return production_transactionRepository.save(pTransaction);
	}
	
	public List<Production_transaction> findAllProTrans(){
		List<Production_transaction> proList=new ArrayList<Production_transaction>();
		proList.addAll(production_transactionRepository.findAllProTrans());
		
		return proList;
	}
	
	public List<Map<String, Object>> findAllProdTransfast(String date)
	{
		return production_transactionRepository.findAllProdTransfast(date);
	}
	
	public Production_transaction findOne(long id)
	{
		Optional<Production_transaction> op=this.production_transactionRepository.findById(id);
		return op.get();
	}
	
	public List<Production_transaction_input_detailsDTO> getProdTranInputDetails(String prodid)
	{
		List<Production_transaction_input_details> modelList=new ArrayList<Production_transaction_input_details>();
		modelList.addAll(production_transaction_input_detailsRepository.getProdTranInputDetails(prodid));
		Type listType=new TypeToken<List<Production_transaction_input_detailsDTO>>() {}.getType();
		
		List<Production_transaction_input_detailsDTO> inputDtls=new ModelMapper().map(modelList,listType);
		
		return inputDtls;
	}
	
	public List<Production_transaction_output_detailsDTO> getProdTranOutputDetails(String prodid)
	{
		List<Production_transaction_output_details> modelList=new ArrayList<Production_transaction_output_details>();
		modelList.addAll(production_transaction_output_detailsRepository.getProdTranOutputDetails(prodid));
		Type listType=new TypeToken<List<Production_transaction_output_detailsDTO>>() {}.getType();
		
		List<Production_transaction_output_detailsDTO> outDtls=new ModelMapper().map(modelList,listType);
		
		return outDtls;
	}
	
	@Transactional
	public Production_transaction update(Production_transaction pTransaction,long id) throws JsonParseException, JsonMappingException, IOException{
		Optional<Production_transaction> op = production_transactionRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(pTransaction.getBusiness_unit().compareTo("0") !=0 && pTransaction.getBusiness_unit().compareTo("") !=0 && pTransaction.getBusiness_unit() !=null) {
			pTransaction.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pTransaction.getBusiness_unit()).getBusinessunit_name());
		}else {pTransaction.setBusiness_unitname("None");}
		
		if(pTransaction.getShop_floor().compareTo("0") !=0 && pTransaction.getShop_floor().compareTo("") !=0 && pTransaction.getShop_floor() !=null) {
			pTransaction.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(pTransaction.getShop_floor()).getShop_floor_name());
		}else {pTransaction.setShop_floorname("None");}
		
		/*if(pTransaction.getProd_process().compareTo("0") !=0 && pTransaction.getProd_process().compareTo("") !=0 && pTransaction.getProd_process() !=null) {
			pTransaction.setProd_processname(process_masterRepository.getProcessDetails(pTransaction.getProd_process()).getProcess_desc());
		}else {pTransaction.setProd_processname("None");}*/
		
		if(pTransaction.getProd_desc().compareTo("0") !=0 && pTransaction.getProd_desc().compareTo("") !=0 && pTransaction.getProd_desc() !=null) {
			pTransaction.setProd_description(bom_masterRepository.getBomDetails(pTransaction.getProd_desc(),pTransaction.getCompany_id()).getProd_desc());
		}else {pTransaction.setProd_description("None");}
		
		pTransaction.setProd_trans_id(op.get().getProd_trans_id());
		pTransaction.setModified_type("INSERTED");
		pTransaction.setInserted_by(op.get().getInserted_by());
		pTransaction.setInserted_on(op.get().getInserted_on());
		pTransaction.setUpdated_by(userRepository.getUserDetails(pTransaction.getUsername()).getName());
		pTransaction.setUpdated_on(ldt);
		pTransaction.setDeleted_by("NA");
		pTransaction.setDeleted_on(ldt);
		
		long x=0,y=0;
		
		//Update
		production_transaction_input_detailsRepository.updateProduction_transaction_input_details(op.get().getProd_trans_id());
		
		Set<Production_transaction_input_details> bInput=new HashSet<Production_transaction_input_details>();
		bInput.addAll(pTransaction.getProduction_transaction_input_details());
		for(Production_transaction_input_details bInput_details:bInput) 
		{
			if(bInput_details.getItem().compareTo("0") !=0 && bInput_details.getItem().compareTo("") !=0 && bInput_details.getItem() !=null) {
				bInput_details.setItem_name(item_masterRepository.itemNameById(bInput_details.getItem()).getItem_name());
			}else {bInput_details.setItem_name("None");}
			
			if(bInput_details.getPacking().compareTo("0") !=0 && bInput_details.getPacking().compareTo("") !=0 && bInput_details.getPacking() !=null) {
				bInput_details.setPacking_name(item_masterRepository.itemNameById(bInput_details.getPacking()).getItem_name());
			}else {bInput_details.setPacking_name("None");}
			
			bInput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bInput_details.setProd_trans_id(op.get().getProd_trans_id());
			bInput_details.setCompany_id(pTransaction.getCompany_id());
			bInput_details.setFin_year(pTransaction.getFin_year());
			bInput_details.setModified_type("INSERTED");
			bInput_details.setInserted_by(pTransaction.getInserted_by());
			bInput_details.setInserted_on(pTransaction.getInserted_on());
			bInput_details.setUpdated_by(pTransaction.getUpdated_by());
			bInput_details.setUpdated_on(pTransaction.getUpdated_on());
			bInput_details.setDeleted_by("NA");
			bInput_details.setDeleted_on(ldt);
			
			/******** For input Popup *******/
			//Update
			production_transaction_input_popup_detailsRepository.updateProduction_transaction_input_popup_details(op.get().getProd_trans_id());
			
			x++;
			bInput_details.setProd_popupid(op.get().getProd_trans_id()+Long.toString(x));
			
			if(bInput_details.getInput_qty().compareTo("")==0) {
				bInput_details.setInput_qty("{\"prod_input_details\":[{\"checkbox\":false,\"sl_no\":1,\"shifttime\":\"\",\"itemqty\":\"\",\"packingqty\":\"\"}]}");
			}
			
			String inptData=bInput_details.getInput_qty().replace("{\"prod_input_details\":", "");
			inptData=inptData.substring(0, inptData.length()-1);
			
			Set<Production_transaction_input_popup_details> pInput_popup_details=new HashSet<Production_transaction_input_popup_details>();
			
			@SuppressWarnings("unchecked")
			Production_transaction_input_popup_details[] pDetails= new ObjectMapper().readValue(inptData, Production_transaction_input_popup_details[].class);
			Set<Production_transaction_input_popup_details> set = new HashSet<>(Arrays.asList(pDetails));
			pInput_popup_details.addAll(set);
			
			for(Production_transaction_input_popup_details popup_details:pInput_popup_details) 
			{
				popup_details.setProd_trans_code(pTransaction.getProd_trans_code());
				popup_details.setProd_trans_id(op.get().getProd_trans_id());
				popup_details.setProd_popupid(bInput_details.getProd_popupid());
				
				popup_details.setCompany_id(bInput_details.getCompany_id());
				popup_details.setFin_year(bInput_details.getFin_year());
				popup_details.setModified_type("INSERTED");
				popup_details.setInserted_by(bInput_details.getInserted_by());
				popup_details.setInserted_on(bInput_details.getInserted_on());
				popup_details.setUpdated_by(bInput_details.getUpdated_by());
				popup_details.setUpdated_on(bInput_details.getUpdated_on());
				popup_details.setDeleted_by("NA");
				popup_details.setDeleted_on(ldt);
			}
			bInput_details.setProduction_transaction_input_popup_details(pInput_popup_details);
			/******** For input Popup *******/
		}
		pTransaction.setProduction_transaction_input_details(bInput);
		
		//Update
		production_transaction_output_detailsRepository.updateProduction_transaction_output_details(op.get().getProd_trans_id());
		
		Set<Production_transaction_output_details> bOutput=new HashSet<Production_transaction_output_details>();
		bOutput.addAll(pTransaction.getProduction_transaction_output_details());
		for(Production_transaction_output_details bOutput_details:bOutput) 
		{
			if(bOutput_details.getItem().compareTo("0") !=0 && bOutput_details.getItem().compareTo("") !=0 && bOutput_details.getItem() !=null) {
				bOutput_details.setItem_name(item_masterRepository.itemNameById(bOutput_details.getItem()).getItem_name());
			}else {bOutput_details.setItem_name("None");}
			
			if(bOutput_details.getPacking().compareTo("0") !=0 && bOutput_details.getPacking().compareTo("") !=0 && bOutput_details.getPacking() !=null) {
				bOutput_details.setPacking_name(item_masterRepository.itemNameById(bOutput_details.getPacking()).getItem_name());
			}else {bOutput_details.setPacking_name("None");}
			
			bOutput_details.setProd_trans_code(pTransaction.getProd_trans_code());
			bOutput_details.setProd_trans_id(op.get().getProd_trans_id());
			bOutput_details.setCompany_id(pTransaction.getCompany_id());
			bOutput_details.setFin_year(pTransaction.getFin_year());
			bOutput_details.setModified_type("INSERTED");
			bOutput_details.setInserted_by(pTransaction.getInserted_by());
			bOutput_details.setInserted_on(pTransaction.getInserted_on());
			bOutput_details.setUpdated_by(pTransaction.getUpdated_by());
			bOutput_details.setUpdated_on(pTransaction.getUpdated_on());
			bOutput_details.setDeleted_by("NA");
			bOutput_details.setDeleted_on(ldt);
			
			/******** For output Popup *******/
			//Update
			production_transaction_output_popup_detailsRepository.updateProduction_transaction_output_popup_details(op.get().getProd_trans_id());
			
			y++;
			bOutput_details.setProd_popupid(op.get().getProd_trans_id()+Long.toString(y));
			
			if(bOutput_details.getOutput_qty().compareTo("")==0) {
				bOutput_details.setOutput_qty("{\"prod_output_details\":[{\"checkbox\":false,\"sl_no\":1,\"shifttime\":\"\",\"itemqty\":\"\",\"packingqty\":\"\"}]}");
			}
			
			String inputData=bOutput_details.getOutput_qty().replace("{\"prod_output_details\":", "");
			inputData=inputData.substring(0, inputData.length()-1);
			
			Set<Production_transaction_output_popup_details> pOutput_popup_details=new HashSet<Production_transaction_output_popup_details>();
			
			@SuppressWarnings("unchecked")
			Production_transaction_output_popup_details[] popupDetails= new ObjectMapper().readValue(inputData, Production_transaction_output_popup_details[].class);
			Set<Production_transaction_output_popup_details> popupSet = new HashSet<>(Arrays.asList(popupDetails));
			pOutput_popup_details.addAll(popupSet);
			
			for(Production_transaction_output_popup_details popup_details:pOutput_popup_details) 
			{
				popup_details.setProd_trans_code(pTransaction.getProd_trans_code());
				popup_details.setProd_trans_id(op.get().getProd_trans_id());
				popup_details.setProd_popupid(bOutput_details.getProd_popupid());
				
				popup_details.setCompany_id(bOutput_details.getCompany_id());
				popup_details.setFin_year(bOutput_details.getFin_year());
				popup_details.setModified_type("INSERTED");
				popup_details.setInserted_by(bOutput_details.getInserted_by());
				popup_details.setInserted_on(bOutput_details.getInserted_on());
				popup_details.setUpdated_by(bOutput_details.getUpdated_by());
				popup_details.setUpdated_on(bOutput_details.getUpdated_on());
				popup_details.setDeleted_by("NA");
				popup_details.setDeleted_on(ldt);
			}
			bOutput_details.setProduction_transaction_output_popup_details(pOutput_popup_details);
			/******** For output Popup *******/
		}
		pTransaction.setProduction_transaction_output_details(bOutput);
		
		if(op.isPresent())
		{
			pTransaction.setId(id);
		}
		return production_transactionRepository.save(pTransaction);
	}
	
	public List<Production_transaction_input_popup_details> getInputPopup(long id,String item)
	{
		List<Production_transaction_input_popup_details> modelList=new ArrayList<Production_transaction_input_popup_details>();
		
		Optional<Production_transaction> op = production_transactionRepository.findById(id);
		Production_transaction_input_details inputdata=production_transaction_input_detailsRepository.getpopupDetails(op.get().getProd_trans_id(),item);
		modelList.addAll(production_transaction_input_popup_detailsRepository.getinputpopupdetails(inputdata.getProd_popupid()));
		return modelList;
	}
	
	public List<Production_transaction_output_popup_details> getOutputPopup(long id,String item)
	{
		List<Production_transaction_output_popup_details> modelList=new ArrayList<Production_transaction_output_popup_details>();
		
		Optional<Production_transaction> op = production_transactionRepository.findById(id);
		Production_transaction_output_details outputdata=production_transaction_output_detailsRepository.getpopupDetails(op.get().getProd_trans_id(),item);
		modelList.addAll(production_transaction_output_popup_detailsRepository.getoutputpopupdetails(outputdata.getProd_popupid()));
		return modelList;
	}
	
	 @Transactional
		public Production_transaction deleteProdTransReg(Production_transaction ptrans_reg,Long id) throws JsonParseException, JsonMappingException, IOException
	 	{
			Optional<Production_transaction> op = production_transactionRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Production_transaction ptr=op.get();
			
			ptr.setModified_type("DELETED");
			ptr.setInserted_by(op.get().getInserted_by());
			ptr.setInserted_on(op.get().getInserted_on());
			ptr.setUpdated_by(op.get().getUpdated_by());
			ptr.setUpdated_on(op.get().getUpdated_on());
			ptr.setDeleted_by(userRepository.getUserDetails(ptr.getUsername()).getName());
			ptr.setDeleted_on(ldt);
			
			production_transaction_input_detailsRepository.production_transaction_input_detailsUpdate(op.get().getProd_trans_id());
			production_transaction_input_popup_detailsRepository.production_transaction_input_popup_detailsUpdate(op.get().getProd_trans_id());
			production_transaction_output_detailsRepository.production_transaction_output_detailsUpdate(op.get().getProd_trans_id());
			production_transaction_output_popup_detailsRepository.production_transaction_output_popup_detailsUpdate(op.get().getProd_trans_id());
			
			
			return production_transactionRepository.save(ptr);	
	 	}
	 
	 public List<Map<String, Object>> getStoreconsumptiontransaction(String shop_floor)
	 {
		 
		 List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		 data.addAll(production_transactionRepository.getstoreconsumptiontransaction(shop_floor));
		 
		 return data;
	 }
	 
	 public List<Map<String, Object>> searchProductionTransaction(String business_unit1,String shop_floor1,String fromdate, String todate)
	 {
		 List<Map<String, Object>> searchprodtrans =new ArrayList<Map<String, Object>>();
		 
		 ArrayList<String> shopmultiple=new ArrayList<String>();
		 String multishop[]=shop_floor1.split(",");
		 for(int i=0;i<multishop.length;i++)
		 {
			shopmultiple.add(multishop[i]);
		 }
		 
		 searchprodtrans.addAll(production_transactionRepository.getsearchdata(business_unit1,shopmultiple,fromdate,todate));
		 return searchprodtrans;
	 }
	 
	
	@Transactional
	public Production_transaction accountpostingproductionreg(long id) 
	{
		Optional<Production_transaction> op=this.production_transactionRepository.findById(id);//static details 
		
		String date=op.get().getProd_trans_date(),voucherno=op.get().getProd_trans_code();
		
        List<Production_transaction_output_details> outputdetails= production_transaction_output_detailsRepository.getProdTranOutputDetails(op.get().getProd_trans_id());
		
		
		String output_item_name_ledger[]=new String[outputdetails.size()];
        String output_item_name[]=new String[outputdetails.size()];
        String output_item_rate[]=new String[outputdetails.size()];
        String output_production_uom[]=new String[outputdetails.size()];
        String output_production_qty[]=new String[outputdetails.size()];
        String output_packing_uom[]=new String[outputdetails.size()];
        String output_packing_qty[]=new String[outputdetails.size()];
        
        for(int i=0;i<outputdetails.size();i++) 
        {
        	//String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(outputdetails.get(i).getItem()).getItem_group();
			//Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
        	//item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
        	
        	output_item_name_ledger[i]=outputdetails.get(i).getItem_name();
        	output_item_name[i]=outputdetails.get(i).getItem_name();
        	output_item_rate[i]="2500";
        	output_production_uom[i]=customUomMasterRepository.getCustomUomById(outputdetails.get(i).getProduction_uom()).getDescription();
        	output_production_qty[i]=String.valueOf(outputdetails.get(i).getProduction_qty());
			output_packing_uom[i]=outputdetails.get(i).getPacking_uom();
			output_packing_qty[i]=String.valueOf(outputdetails.get(i).getPacking_qty());
        }
        
         List<Production_transaction_input_details> inputdetails= production_transaction_input_detailsRepository.getProdTranInputDetails(op.get().getProd_trans_id());
		
		
		String input_item_name_ledger[]=new String[inputdetails.size()];
        String input_item_name[]=new String[inputdetails.size()];
        String input_item_rate[]=new String[inputdetails.size()];
        String input_production_uom[]=new String[inputdetails.size()];
        String input_production_qty[]=new String[inputdetails.size()];
        String input_packing_uom[]=new String[inputdetails.size()];
        String input_packing_qty[]=new String[inputdetails.size()];
        
        for(int i=0;i<inputdetails.size();i++) 
        {
        	//String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(outputdetails.get(i).getItem()).getItem_group();
			//Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
        	//item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
        	
        	input_item_name_ledger[i]=inputdetails.get(i).getItem_name();
        	input_item_name[i]=inputdetails.get(i).getItem_name();
        	input_item_rate[i]="2500";
        	input_production_uom[i]=customUomMasterRepository.getCustomUomById(inputdetails.get(i).getProduction_uom()).getDescription();
        	input_production_qty[i]=String.valueOf(inputdetails.get(i).getProduction_qty());
        	input_packing_uom[i]=inputdetails.get(i).getPacking_uom();
        	input_packing_qty[i]=String.valueOf(inputdetails.get(i).getPacking_qty());
        }
		
        String output;
        Tallyrequest_ProductionReg tally=new Tallyrequest_ProductionReg();
        
		try  
		{
			date=date.replace("-", "");
			/*output = tally.SendToTally(date,voucherno,output_item_name_ledger,output_item_name,output_item_rate,output_production_uom,output_production_qty,output_packing_uom,output_packing_qty,
					input_item_name_ledger,input_item_name,input_item_rate,input_production_uom,input_production_qty,input_packing_uom,input_packing_qty);
			
			String [] splitoutput = output.split("\\|\\|");
			
			production_transactionRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1])); */
			
		}
		catch(Exception e)
		{
		
			//System.out.println(e);
		}
        
		Optional<Production_transaction> op1=this.production_transactionRepository.findById(id);//static details 
	
		return op1.get();
	}
		
	 
}
