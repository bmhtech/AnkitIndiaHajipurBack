package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.Requisition;
import com.AnkitIndia.jwtauthentication.model.Requisition_Item_Dtls;

import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.RequisitionRepository;
import com.AnkitIndia.jwtauthentication.repository.Requisition_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.RequisitionListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;


@Service
public class RequisitionService_Imp implements RequisitionService{

	@Autowired
	RequisitionRepository requisitionRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Requisition_Item_DtlsRepository requisition_Item_DtlsRepository;
	
	public SequenceIdDTO  getrequisitionnumber(String finyear) 
	{
		int slno=0;
		
		String sno=requisitionRepository.countrequisition();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=finyear.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="REQ-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
        Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		
		return genCode;
	}
	
	@Transactional
	public Requisition save(Requisition requisition) 
	{
		
		LocalDateTime ldt = LocalDateTime.now();
		
        int slno=0;
		String sno=requisitionRepository.countrequisition();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=requisition.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		
		String prefix="REQ-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		if(Utility.isNullOrEmpty(requisition.getApprovedby())) {
			requisition.setApprovedbyname(employeeMasterRepository.getEmployee(requisition.getApprovedby()).getEmp_name());
		}else {requisition.setApprovedbyname("NA");}
		
		if(Utility.isNullOrEmpty(requisition.getReject())) {
			
		}else {requisition.setReject("NA");}
		
		
		
		
		requisition.setRequisitionno(gen_sno);
		requisition.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(requisition.getBusiness_unit()).getBusinessunit_name());
		requisition.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(requisition.getShop_floor()).getShop_floor_name());
		requisition.setRequestedbyname(employeeMasterRepository.getEmployee(requisition.getRequestedby()).getEmp_name());
		requisition.setModified_type("INSERTED");
		requisition.setInserted_by(userRepository.getUserDetails(requisition.getUsername()).getName());
		requisition.setInserted_on(ldt);
		requisition.setUpdated_by("NA");
		requisition.setUpdated_on(ldt);
		requisition.setDeleted_by("NA");
		requisition.setDeleted_on(ldt);
		
		
		Set<Requisition_Item_Dtls> details = new HashSet<Requisition_Item_Dtls>();
		details.addAll(requisition.getRequisition_Item_Dtls());
		for(Requisition_Item_Dtls itemdetails : details)
		{
			itemdetails.setRequisitionno(gen_sno);
			itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem_code()).getItem_name());
			itemdetails.setPacking_item(item_masterRepository.itemNameById(itemdetails.getPacking()).getItem_name());
			itemdetails.setCompany_id(requisition.getCompany_id());
			itemdetails.setFin_year(requisition.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(requisition.getInserted_by());
			itemdetails.setInserted_on(requisition.getInserted_on());
			itemdetails.setUpdated_by("NA");
			itemdetails.setUpdated_on(ldt);
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
			
		}
		
		requisition.setRequisition_Item_Dtls(details);
		
		return requisitionRepository.save(requisition);
	}
	
	@Transactional
	public Requisition update(Requisition requisition,long id) 
	{

		Optional<Requisition> op =requisitionRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		requisition.setRequisitionno(op.get().getRequisitionno());
		requisition.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(requisition.getBusiness_unit()).getBusinessunit_name());
		requisition.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(requisition.getShop_floor()).getShop_floor_name());
		requisition.setRequestedbyname(employeeMasterRepository.getEmployee(requisition.getRequestedby()).getEmp_name());
		if(Utility.isNullOrEmpty(requisition.getApprovedby())) {
			requisition.setApprovedbyname(employeeMasterRepository.getEmployee(requisition.getApprovedby()).getEmp_name());
		}else {requisition.setApprovedbyname("NA");}
		
        if(Utility.isNullOrEmpty(requisition.getReject())) {
			
		}else {requisition.setReject("NA");}
		
		requisition.setModified_type("INSERTED");
		requisition.setInserted_by(op.get().getInserted_by());
		requisition.setInserted_on(op.get().getInserted_on());
		requisition.setUpdated_by(userRepository.getUserDetails(requisition.getUsername()).getName());
		requisition.setUpdated_on(ldt);
		requisition.setDeleted_by("NA");
		requisition.setDeleted_on(ldt);
		
		requisition_Item_DtlsRepository.update(requisition.getRequisitionno());
		
		Set<Requisition_Item_Dtls> details = new HashSet<Requisition_Item_Dtls>();
		details.addAll(requisition.getRequisition_Item_Dtls());
		for(Requisition_Item_Dtls itemdetails : details)
		{
			itemdetails.setRequisitionno(op.get().getRequisitionno());
			itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem_code()).getItem_name());
			itemdetails.setPacking_item(item_masterRepository.itemNameById(itemdetails.getPacking()).getItem_name());
			itemdetails.setCompany_id(requisition.getCompany_id());
			itemdetails.setFin_year(requisition.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(op.get().getInserted_by());
			itemdetails.setInserted_on(op.get().getInserted_on());
			itemdetails.setUpdated_by(userRepository.getUserDetails(requisition.getUsername()).getName());
			itemdetails.setUpdated_on(ldt);
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
			
		}
		
		if(op.isPresent())
		{
			requisition.setId(id);
		}
		requisition.setRequisition_Item_Dtls(details);
		
		return requisitionRepository.save(requisition);
	
		
	}
	@Transactional
	public Requisition setreject(Requisition requisition,long id)
	{

		Optional<Requisition> op =requisitionRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		requisition.setRequisitionno(op.get().getRequisitionno());
		requisition.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(requisition.getBusiness_unit()).getBusinessunit_name());
		requisition.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(requisition.getShop_floor()).getShop_floor_name());
		requisition.setRequestedbyname(employeeMasterRepository.getEmployee(requisition.getRequestedby()).getEmp_name());
		if(Utility.isNullOrEmpty(requisition.getApprovedby())) {
			requisition.setApprovedbyname(employeeMasterRepository.getEmployee(requisition.getApprovedby()).getEmp_name());
		}else {requisition.setApprovedbyname("NA");}
		
        if(Utility.isNullOrEmpty(requisition.getReject())) {
			
		}else {requisition.setReject("NA");}
		
		requisition.setModified_type("INSERTED");
		requisition.setInserted_by(op.get().getInserted_by());
		requisition.setInserted_on(op.get().getInserted_on());
		requisition.setUpdated_by(userRepository.getUserDetails(requisition.getUsername()).getName());
		requisition.setUpdated_on(ldt);
		requisition.setDeleted_by("NA");
		requisition.setDeleted_on(ldt);
		requisition.setRejecton(ldt);
		requisition_Item_DtlsRepository.update(requisition.getRequisitionno());
		
		Set<Requisition_Item_Dtls> details = new HashSet<Requisition_Item_Dtls>();
		details.addAll(requisition.getRequisition_Item_Dtls());
		for(Requisition_Item_Dtls itemdetails : details)
		{
			itemdetails.setRequisitionno(op.get().getRequisitionno());
			itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem_code()).getItem_name());
			itemdetails.setPacking_item(item_masterRepository.itemNameById(itemdetails.getPacking()).getItem_name());
			itemdetails.setCompany_id(requisition.getCompany_id());
			itemdetails.setFin_year(requisition.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(op.get().getInserted_by());
			itemdetails.setInserted_on(op.get().getInserted_on());
			itemdetails.setUpdated_by(userRepository.getUserDetails(requisition.getUsername()).getName());
			itemdetails.setUpdated_on(ldt);
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
			
		}
		
		if(op.isPresent())
		{
			requisition.setId(id);
		}
		requisition.setRequisition_Item_Dtls(details);
		
		return requisitionRepository.save(requisition);
	
		
	}
	
	@Transactional
	public Requisition deleteRequisition(Requisition requisition,long id) 
	{
		Optional<Requisition> op =requisitionRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Requisition requisitionde=op.get();
		
		requisitionde.setModified_type("DELETED");
		requisitionde.setInserted_by(op.get().getInserted_by());
		requisitionde.setInserted_on(op.get().getInserted_on());
		requisitionde.setUpdated_by(op.get().getUpdated_by());
		requisitionde.setUpdated_on(op.get().getUpdated_on());
		requisitionde.setDeleted_by(userRepository.getUserDetails(requisition.getUsername()).getName());
		requisitionde.setDeleted_on(ldt);
		
		requisition_Item_DtlsRepository.deleterequisition(requisitionde.getRequisitionno());
		
		if(op.isPresent())
		{
			requisitionde.setId(id);
		}
		return requisitionRepository.save(requisitionde);
		
	}
	
	public List<RequisitionListDTO>  listRequisition()
	{
		
		List<RequisitionListDTO> newlist = new ArrayList<RequisitionListDTO>();
		List<Object[]> requisitionlist=new ArrayList<Object[]>();
		requisitionlist.addAll(requisitionRepository.getdetails());
		
		for(final Object o : requisitionlist)
		{
		        Object[] obj = (Object[]) o;
		        boolean status=false;
		        String response="";
		        if(obj[7].toString().compareTo("0")==0) 
		        {
		        	status=false;
		        	if(obj[8].toString().compareToIgnoreCase("NA")==0) 
			        {
			        	
			        }
		        	else 
		        	{
		        		response="Rejected";
		        	}
		        }
		        else 
		        {
		        	status=true;
		        	response="Approved";
		        }
		       
		        
		        
		        
		        
		        newlist.add(new RequisitionListDTO( 
		        		Long.parseLong(obj[0].toString()),	
		        		obj[1].toString(),
		        		obj[2].toString(),
		        		obj[3].toString(),
		        		obj[4].toString(),
		        		obj[5].toString(),
		        		obj[6].toString(),
		        		status,
		        		obj[8].toString(),
		        		response
		        		));

		}
		return newlist;
	}
	
	public Requisition  getrequisitionnumber(long id) 
	{
		Optional<Requisition> op =requisitionRepository.findById(id);
		return op.get();
	}
	
	public List<Requisition_Item_Dtls>  getRequisitionitemdetails(String requisitionno)
	{
		List<Requisition_Item_Dtls> itemdetails= new ArrayList<Requisition_Item_Dtls>();
		itemdetails.addAll(requisition_Item_DtlsRepository.getitemdetails(requisitionno));
		
		return itemdetails;
	}	
	
	
	@Transactional
	 public StatusDTO  requisitionapprove(long id,String username) 
	 {
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("username :: " + username);
		 Employee_master employee=employeeMasterRepository.getEmployeebyuser(username);
		 requisitionRepository.changeapprove(id,employee.getEmp_id(),employee.getEmp_name(),ldt);
		 
		 StatusDTO res =new StatusDTO();
		 res.setStatus("YES");
		 
		 return res;
		 
	 }
	
	public List<Requisition>  getRequisitionnumberapprove(String shopfloor) 
	{
		List<Requisition> requisitionList =new ArrayList<Requisition>();
		requisitionList.addAll(requisitionRepository.getRequisitionnumberapprove(shopfloor));
		return requisitionList;
	}
	
	public Requisition  getRequisition(String requisitionno) 
	{
		Requisition req = new Requisition();
		req=requisitionRepository.getRequisition(requisitionno);
		return req;
	}
}
