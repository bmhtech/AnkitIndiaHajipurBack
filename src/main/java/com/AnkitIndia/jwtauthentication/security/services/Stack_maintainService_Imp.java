package com.AnkitIndia.jwtauthentication.security.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Stack_maintain;
import com.AnkitIndia.jwtauthentication.model.Stack_maintain_details;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Stack_maintainRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;

@Service
public class Stack_maintainService_Imp implements Stack_maintainService{

	@Autowired
	Stack_maintainRepository stack_maintainRepository;

	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository suppBussiness_partnerRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	public List<Map<String,Object>> stackMaintainList(String currdate)
	{
		return stack_maintainRepository.stackMaintainList(currdate);
	}
	
	public List<Map<String,Object>> getGrnList()
	{
		return pur_good_receiptRepository.getGrnList();
	}
	
	public List<Map<String,Object>> getItemListByGrnId(String grnid)
	{
		return pur_good_receiptRepository.getItemListByGrnId(grnid);
	}
	
	public List<Map<String,Object>> getPackingItemByGrn(String item,String grnid)
	{
		return pur_good_receiptRepository.getPackingItemByGrn(item,grnid);
	}
	
	@Transactional
	public Stack_maintain save(Stack_maintain stack) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stack_maintainRepository.countId() != null ) {
			slno=Long.parseLong(stack_maintainRepository.countId());
		}
		String prefix="STM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		stack.setStack_id(gen_sno);
		
		if(Utility.isNullOrEmpty(stack.getB_unit())) {
			stack.setB_unitname(companyBusinessUnitMasterRepository.businessunitbyid(stack.getB_unit()).getBusinessunit_name());
		}else {stack.setB_unitname("Na");}
		
		if(Utility.isNullOrEmpty(stack.getGrn_id())) {
			stack.setGrn_no(pur_good_receiptRepository.getPurGoodRcptDtls(stack.getGrn_id()).getGrn_no());
		}else {stack.setGrn_no("Na");}
		
		if(Utility.isNullOrEmpty(stack.getSupplier())) {
			stack.setSupplier_name(suppBussiness_partnerRepository.getSupplierName(stack.getSupplier()).getBp_name());
		}else {stack.setSupplier_name("None");}
		
		if(Utility.isNullOrEmpty(stack.getVehicle_id())) {
			stack.setVehicle_no(vehicleMasterRepository.getVehicleDetails(stack.getVehicle_id()).getVehicle_no());
		}else {stack.setVehicle_no("Na");}
		
		stack.setCompany_id(stack.getCompany_id());
		stack.setFin_year(stack.getFin_year());
		stack.setModified_type("INSERTED");
		stack.setInserted_on(ldt);
		stack.setInserted_by(userRepository.getUserDetails(stack.getUsername()).getName());
		stack.setUpdated_by("NA");
		stack.setUpdated_on(ldt);
		stack.setDeleted_by("NA");
		stack.setDeleted_on(ldt);
		stack.setUsername(stack.getUsername());
		
		pur_good_receiptRepository.updateStackMaintain(stack.getGrn_id(),"Yes");
		
		Set<Stack_maintain_details> std=new HashSet<Stack_maintain_details>();
		std.addAll(stack.getStack_maintain_details());
		for(Stack_maintain_details item:std) 
		{
			item.setStack_id(gen_sno);
			
			if(item.getItem_code().compareTo("")!=0 && item.getItem_code().compareTo("0")!=0) {
				item.setItem_name(item_masterRepository.itemNameById(item.getItem_code()).getItem_name());
			}else {item.setItem_name("Na");}
			
			if(item.getPacking().compareTo("")!=0 && item.getPacking().compareTo("0")!=0) {
				item.setPacking_name(item_masterRepository.itemNameById(item.getPacking()).getItem_name());
			}else {item.setPacking_name("Na");}
			
			if(item.getWarehouse().compareTo("")!=0 && item.getWarehouse().compareTo("0")!=0) {
				item.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(item.getWarehouse()).getWarehouse_name());
			}else {item.setWarehouse_name("None");}
			
			
			item.setCompany_id(stack.getCompany_id());
			item.setFin_year(stack.getFin_year());
			item.setModified_type("INSERTED");
			item.setInserted_by(stack.getInserted_by());
			item.setInserted_on(ldt);
			item.setUpdated_by("NA");
			item.setUpdated_on(ldt);
			item.setDeleted_by("NA");
			item.setDeleted_on(ldt);
			item.setUsername(stack.getUsername());
		}
		stack.setStack_maintain_details(std);
		
		return stack_maintainRepository.save(stack);
	}
	
	@Transactional
	public Stack_maintain update(Stack_maintain stack,long id)
	{
		Optional<Stack_maintain> op =stack_maintainRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		stack.setStack_id(op.get().getStack_id());
		
		if(Utility.isNullOrEmpty(stack.getB_unit())) {
			stack.setB_unitname(companyBusinessUnitMasterRepository.businessunitbyid(stack.getB_unit()).getBusinessunit_name());
		}else {stack.setB_unitname("Na");}
		
		if(Utility.isNullOrEmpty(stack.getGrn_id())) {
			stack.setGrn_no(pur_good_receiptRepository.getPurGoodRcptDtls(stack.getGrn_id()).getGrn_no());
		}else {stack.setGrn_no("Na");}
		
		if(Utility.isNullOrEmpty(stack.getSupplier())) {
			stack.setSupplier_name(suppBussiness_partnerRepository.getSupplierName(stack.getSupplier()).getBp_name());
		}else {stack.setSupplier_name("None");}
		
		if(Utility.isNullOrEmpty(stack.getVehicle_id())) {
			stack.setVehicle_no(vehicleMasterRepository.getVehicleDetails(stack.getVehicle_id()).getVehicle_no());
		}else {stack.setVehicle_no("Na");}

		stack.setCompany_id(stack.getCompany_id());
		stack.setFin_year(stack.getFin_year());
		stack.setModified_type("INSERTED");
		stack.setInserted_on(op.get().getInserted_on());
		stack.setInserted_by(op.get().getInserted_by());
		stack.setUpdated_by(userRepository.getUserDetails(stack.getUsername()).getName());
		stack.setUpdated_on(ldt);
		stack.setDeleted_by("NA");
		stack.setDeleted_on(ldt);
		stack.setUsername(stack.getUsername());
		//Update
		pur_good_receiptRepository.updateStackMaintain(stack.getGrn_id(),"Yes");
		stack_maintainRepository.updateStackMaintainDetails(op.get().getStack_id(),"UPDATED");
		
		Set<Stack_maintain_details> std=new HashSet<Stack_maintain_details>();
		std.addAll(stack.getStack_maintain_details());
		for(Stack_maintain_details item:std) 
		{
			item.setStack_id(op.get().getStack_id());
			
			if(item.getItem_code().compareTo("")!=0 && item.getItem_code().compareTo("0")!=0) {
				item.setItem_name(item_masterRepository.itemNameById(item.getItem_code()).getItem_name());
			}else {item.setItem_name("Na");}
			
			if(item.getPacking().compareTo("")!=0 && item.getPacking().compareTo("0")!=0) {
				item.setPacking_name(item_masterRepository.itemNameById(item.getPacking()).getItem_name());
			}else {item.setPacking_name("Na");}
			
			if(item.getWarehouse().compareTo("")!=0 && item.getWarehouse().compareTo("0")!=0) {
				item.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(item.getWarehouse()).getWarehouse_name());
			}else {item.setWarehouse_name("None");}
			
			item.setCompany_id(stack.getCompany_id());
			item.setFin_year(stack.getFin_year());
			item.setModified_type("INSERTED");
			item.setInserted_by(stack.getInserted_by());
			item.setInserted_on(stack.getInserted_on());
			item.setUpdated_by(stack.getUpdated_by());
			item.setUpdated_on(stack.getUpdated_on());
			item.setDeleted_by("NA");
			item.setDeleted_on(ldt);
			item.setUsername(stack.getUsername());
		}
		stack.setStack_maintain_details(std);
		
		return stack_maintainRepository.save(stack);
	}
	
	@Transactional
	public Stack_maintain delete(Stack_maintain stack_maintain,long id)
	{
		Optional<Stack_maintain> op = stack_maintainRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Stack_maintain stack=op.get();
		
		stack.setInserted_by(op.get().getInserted_by());
		stack.setInserted_on(op.get().getInserted_on());
		stack.setUpdated_by(op.get().getUpdated_by());
		stack.setUpdated_on(op.get().getUpdated_on());
		stack.setDeleted_by(userRepository.getUserDetails(stack.getUsername()).getName());
		stack.setDeleted_on(ldt);
		if(op.isPresent())
		{
			stack.setId(id);
		}
		stack.setModified_type("DELETED");
		pur_good_receiptRepository.updateStackMaintain(stack.getGrn_id(),"No");
		stack_maintainRepository.updateStackMaintainDetails(op.get().getStack_id(),"DELETED");
		
	   return stack_maintainRepository.save(stack);
		
	}
	
	public Stack_maintain findOne(long id)
	{
		Optional<Stack_maintain> op=this.stack_maintainRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String,Object>> stackItemRetriveList(String requi_id)
	{
		return stack_maintainRepository.stackItemRetriveList(requi_id); 
	}
	
	public List<Map<String,Object>> getGrnAllList()
	{
		return pur_good_receiptRepository.getGrnAllList(); 
	}
	
	public List<Stack_maintain> findStackMaintain(String searchtext)
	{
		List<Stack_maintain> deptlist=new ArrayList<Stack_maintain>();
		deptlist.addAll(stack_maintainRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Stack_maintain> allData = deptlist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Stack_maintain::getGrn_no))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Stack_maintain> allData = deptlist
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getGrn_no()+c.getSupplier_name()+c.getVehicle_no()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Stack_maintain::getGrn_no))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
}