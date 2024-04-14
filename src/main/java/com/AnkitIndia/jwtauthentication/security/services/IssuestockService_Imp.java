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
import com.AnkitIndia.jwtauthentication.model.Issuestock;
import com.AnkitIndia.jwtauthentication.model.Issuestock_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Requisition;
import com.AnkitIndia.jwtauthentication.model.Requisition_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatstock_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.IssuestockItemDtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.IssuestockRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Requisition_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.RequisitionListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.RequistionRecordsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StocksRecordsDTO;

@Service
public class IssuestockService_Imp implements IssuestockService {
	
	@Autowired
	IssuestockRepository issuestockRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	IssuestockItemDtlsRepository issuestockItemDtlsRepository;
	
	@Autowired
	Requisition_Item_DtlsRepository requisition_Item_DtlsRepository;
	
	public List<Issuestock>  getIssueStocklist(String finyear)
	{
		List<Issuestock> newlist = new ArrayList<Issuestock>();
		newlist.addAll(issuestockRepository.getIssueStocklist(finyear));
		return newlist;
	}
	
	 public SequenceIdDTO  getissuestocknumber(String finyear) 
	 {
		    int slno=0;
		 
		    String sno=issuestockRepository.countissued();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=finyear.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="ISU-"+final_fyear+"-";
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			
	        Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
			
			SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
			
			genCode.setSequenceid(gen_sno);
			
			
			return genCode;
		 
	 }
	 
	 @Transactional
		public Issuestock save(Issuestock issuestock) 
		{
			LocalDateTime ldt = LocalDateTime.now();
			
			 int slno=0;
			 
			    String sno=issuestockRepository.countissued();
				
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				
				String fin_yearspit[]=issuestock.getFin_year().split("-");
				String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
				
				String prefix="ISU-"+final_fyear+"-";
				String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
				
			if(issuestock.getBusiness_unit().compareTo("0") !=0 && issuestock.getBusiness_unit().compareTo("") !=0 && issuestock.getBusiness_unit() !=null) {
				issuestock.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(issuestock.getBusiness_unit()).getBusinessunit_name());
			}else {issuestock.setBusiness_unitname("None");}
			
			if(issuestock.getIssueto().compareTo("0") !=0 && issuestock.getIssueto().compareTo("") !=0 && issuestock.getIssueto() !=null) {
				issuestock.setIssuetoname(shop_floor_masterRepository.getShopFloorDtls(issuestock.getIssueto()).getShop_floor_name());
			}else {issuestock.setIssuetoname("None");}
			
			if(Utility.isNullOrEmpty(issuestock.getRequestedby())) {
				issuestock.setRequestedbyname(employeeMasterRepository.getEmployee(issuestock.getRequestedby()).getEmp_name());
			}else {issuestock.setRequestedbyname("NA");}
			
			if(Utility.isNullOrEmpty(issuestock.getApprovedby())) {
				issuestock.setApprovedbyname(employeeMasterRepository.getEmployee(issuestock.getApprovedby()).getEmp_name());
			}else {issuestock.setApprovedbyname("NA");}
			
			if(Utility.isNullOrEmpty(issuestock.getRequisitionno())) {
				
			}
			else 
			{
				issuestock.setRequisitionno("NA");
			}
			
			
			issuestock.setIssueno(gen_sno);
			issuestock.setApprovedon(ldt);
			issuestock.setModified_type("INSERTED");
			issuestock.setInserted_by(userRepository.getUserDetails(issuestock.getUsername()).getName());
			issuestock.setInserted_on(ldt);
			issuestock.setUpdated_by("NA");
			issuestock.setUpdated_on(ldt);
			issuestock.setDeleted_by("NA");
			issuestock.setDeleted_on(ldt);
			
			
			Set<Issuestock_Item_Dtls> details = new HashSet<Issuestock_Item_Dtls>();
			details.addAll(issuestock.getIssuestock_Item_Dtls());
			for(Issuestock_Item_Dtls itemdetails : details)
			{
				itemdetails.setIssueno(gen_sno);
				itemdetails.setRequisitionno(issuestock.getRequisitionno());
				
				itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem_code()).getItem_name());
				itemdetails.setPacking_item(item_masterRepository.itemNameById(itemdetails.getPacking()).getItem_name());
				itemdetails.setCompany_id(issuestock.getCompany_id());
				itemdetails.setFin_year(issuestock.getFin_year());
				itemdetails.setModified_type("INSERTED");
				itemdetails.setInserted_by(issuestock.getInserted_by());
				itemdetails.setInserted_on(issuestock.getInserted_on());
				itemdetails.setUpdated_by("NA");
				itemdetails.setUpdated_on(ldt);
				itemdetails.setDeleted_by("NA");
				itemdetails.setDeleted_on(ldt);
				
			}
			
			issuestock.setIssuestock_Item_Dtls(details);
			
			return issuestockRepository.save(issuestock);
		}
	 
	public Issuestock retriveIssueStock(long id) 
		{
			Optional<Issuestock> op =issuestockRepository.findById(id);
			return op.get();
		}
	
	public List<Issuestock_Item_Dtls>  getIssueItemDetails(String issueno)
	{
		List<Issuestock_Item_Dtls> itemdetails= new ArrayList<Issuestock_Item_Dtls>();
		itemdetails.addAll(issuestockItemDtlsRepository.getitemdetails(issueno));
		
		return itemdetails;
	}
	
	@Transactional
	public Issuestock update(Issuestock issuestock,long id) 
	{

		Optional<Issuestock> op =issuestockRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		issuestock.setIssueno(op.get().getIssueno());
		issuestock.setApprovedon(ldt);
		
		if(issuestock.getBusiness_unit().compareTo("0") !=0 && issuestock.getBusiness_unit().compareTo("") !=0 && issuestock.getBusiness_unit() !=null) {
			issuestock.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(issuestock.getBusiness_unit()).getBusinessunit_name());
		}else {issuestock.setBusiness_unitname("None");}
		
		if(issuestock.getIssueto().compareTo("0") !=0 && issuestock.getIssueto().compareTo("") !=0 && issuestock.getIssueto() !=null) {
			issuestock.setIssuetoname(shop_floor_masterRepository.getShopFloorDtls(issuestock.getIssueto()).getShop_floor_name());
		}else {issuestock.setIssuetoname("None");}
		
		if(Utility.isNullOrEmpty(issuestock.getRequestedby())) {
			issuestock.setRequestedbyname(employeeMasterRepository.getEmployee(issuestock.getRequestedby()).getEmp_name());
		}else {issuestock.setRequestedbyname("NA");}
		
		if(Utility.isNullOrEmpty(issuestock.getApprovedby())) {
			issuestock.setApprovedbyname(employeeMasterRepository.getEmployee(issuestock.getApprovedby()).getEmp_name());
		}else {issuestock.setApprovedbyname("NA");}
		
		if(Utility.isNullOrEmpty(issuestock.getRequisitionno())) {
			
		}
		else 
		{
			issuestock.setRequisitionno("NA");
		}
		issuestock.setModified_type("INSERTED");
		issuestock.setInserted_by(op.get().getInserted_by());
		issuestock.setInserted_on(op.get().getInserted_on());
		issuestock.setUpdated_by(userRepository.getUserDetails(issuestock.getUsername()).getName());
		issuestock.setUpdated_on(ldt);
		issuestock.setDeleted_by("NA");
		issuestock.setDeleted_on(ldt);
		
		issuestockItemDtlsRepository.updateIssueDetails(issuestock.getIssueno());
		
		Set<Issuestock_Item_Dtls> details = new HashSet<Issuestock_Item_Dtls>();
		details.addAll(issuestock.getIssuestock_Item_Dtls());
		for(Issuestock_Item_Dtls itemdetails : details)
		{
			itemdetails.setIssueno(op.get().getIssueno());
			itemdetails.setRequisitionno(op.get().getRequisitionno());
			itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem_code()).getItem_name());
			itemdetails.setPacking_item(item_masterRepository.itemNameById(itemdetails.getPacking()).getItem_name());
			itemdetails.setCompany_id(issuestock.getCompany_id());
			itemdetails.setFin_year(issuestock.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(op.get().getInserted_by());
			itemdetails.setInserted_on(op.get().getInserted_on());
			itemdetails.setUpdated_by(userRepository.getUserDetails(issuestock.getUsername()).getName());
			itemdetails.setUpdated_on(ldt);
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
			
		}
		
		if(op.isPresent())
		{
			issuestock.setId(id);
		}
		issuestock.setIssuestock_Item_Dtls(details);
		
		return issuestockRepository.save(issuestock);
	
		
	}
	
	@Transactional
	public Issuestock deleteIssueStock(Issuestock issuestock,long id) 
	{
		Optional<Issuestock> op =issuestockRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Issuestock issue=op.get();
		
		issue.setModified_type("DELETED");
		issue.setInserted_by(op.get().getInserted_by());
		issue.setInserted_on(op.get().getInserted_on());
		issue.setUpdated_by(op.get().getUpdated_by());
		issue.setUpdated_on(op.get().getUpdated_on());
		issue.setDeleted_by(userRepository.getUserDetails(issue.getUsername()).getName());
		issue.setDeleted_on(ldt);
		
		issuestockItemDtlsRepository.deleteIssueDetails(issue.getIssueno());
		
		if(op.isPresent())
		{
			issue.setId(id);
		}
		return issuestockRepository.save(issue);
		
	}
	
	
	public StocksRecordsDTO getaayogstocks(String itemcode) 
	{
		StocksRecordsDTO  stock =new StocksRecordsDTO();
		
		List<Object[]> stockrecords=new ArrayList<Object[]>();
		stockrecords.addAll(issuestockItemDtlsRepository.stockrecordsall(itemcode));
	  
	    
	  
	    for(final Object o : stockrecords)
	    {
	    	 Object[] obj = (Object[]) o;
	    	 stock.setItemname(obj[0].toString());
	    	 stock.setItem_code(obj[1].toString());
	    	 stock.setOpeningstock(Double.parseDouble(obj[2].toString()));
	    	 stock.setOpeningstock_packing(Double.parseDouble(obj[3].toString()));
	    	 stock.setPur_return(Double.parseDouble(obj[4].toString()));
	    	 stock.setPur_return_packing(Double.parseDouble(obj[5].toString()));
	    	 stock.setStocktransfer_pur(Double.parseDouble(obj[6].toString()));
	    	 stock.setStocktransfer_pur_packing(Double.parseDouble(obj[7].toString()));
	    	 stock.setProduction(Double.parseDouble(obj[8].toString()));
	    	 stock.setProduction_packing(Double.parseDouble(obj[9].toString()));
	    	 stock.setSale(Double.parseDouble(obj[10].toString()));
	    	 stock.setSale_packing(Double.parseDouble(obj[11].toString()));
	    	 stock.setSale_return(Double.parseDouble(obj[12].toString()));
	    	 stock.setSale_return_packing(Double.parseDouble(obj[13].toString()));
	    	 stock.setStocktransfer_sale(Double.parseDouble(obj[14].toString()));
	    	 stock.setStocktransfer_sale_packing(Double.parseDouble(obj[15].toString()));
	    	 stock.setClosingstock(Double.parseDouble(obj[16].toString()));
	    	 stock.setClosingstock_packing(Double.parseDouble(obj[17].toString()));  
		     
		      
		        
	    }
		
		
		return stock;
	}

	
	 public RequistionRecordsDTO getrequistionstockrecordbyitem(String itemcode,String requisitionno,String packingcode) 
	 {
		 RequistionRecordsDTO req = new RequistionRecordsDTO();
		 
		 
		 List<Object[]> stockrecords=new ArrayList<Object[]>();
		 stockrecords.addAll(issuestockItemDtlsRepository.getrequistionstockrecordbyitem(itemcode,packingcode,requisitionno));
		 
		 for(final Object o : stockrecords)
	     {
		 
			 Object[] obj = (Object[]) o;
			 
			 req.setIssuedpackingqty(Double.parseDouble(obj[0].toString()));
			 req.setIssueditemqty(Double.parseDouble(obj[1].toString()));
	     }
		 
		 return req;
	 }
}
