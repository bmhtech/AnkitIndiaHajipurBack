package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dailypowerreport;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheat_issue_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatstock_Dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.HubMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheat_issue_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.WheatreceivingRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheatreceiving_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wheatstock_DtlsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;

@Service
public class WheatreceivingService_Imp implements WheatreceivingService{

	@Autowired
	WheatreceivingRepository wheatreceivingRepository;
	
	@Autowired
	 Wheatreceiving_DtlsRepository wheatreceiving_DtlsReository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HubMasterRepository hubMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	Wheat_issue_DtlsRepository wheat_issue_DtlsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Wheatstock_DtlsRepository wheatstock_DtlsRepository;
	
	
	public List<Wheatreceiving> getWheatReceivinglist(String currDate,String finyear)
	{
		List<Wheatreceiving> daliylist = new ArrayList<Wheatreceiving>();
		daliylist.addAll(wheatreceivingRepository.getWheatreceiving(currDate,finyear));
		
		return daliylist;
	}
	
	@Transactional
	public Wheatreceiving save(Wheatreceiving wheatreceiving) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wheatreceivingRepository.countId() != null ) {
			slno=Long.parseLong(wheatreceivingRepository.countId());
		}
		String prefix="WRR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		wheatreceiving.setWheatreceiveid(gen_sno);
		wheatreceiving.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(wheatreceiving.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(wheatreceiving.getCreatedby()))
		{
			wheatreceiving.setCreatedby_name(employeeMasterRepository.getEmployee(wheatreceiving.getCreatedby()).getEmp_name());
		}
		else 
		{
			wheatreceiving.setCreatedby_name("");
		}
		wheatreceiving.setCompany_id(wheatreceiving.getCompany_id());
		wheatreceiving.setFin_year(wheatreceiving.getFin_year());
		wheatreceiving.setModified_type("INSERTED");
		wheatreceiving.setInserted_on(ldt);
		wheatreceiving.setInserted_by(userRepository.getUserDetails(wheatreceiving.getUsername()).getName());
		wheatreceiving.setUpdated_by(wheatreceiving.getUpdated_by());
		wheatreceiving.setUpdated_on(ldt);
		wheatreceiving.setDeleted_by("NA");
		wheatreceiving.setDeleted_on(ldt);
		
		Set<Wheatstock_Dtls> wheatstock_Dtls=new HashSet<Wheatstock_Dtls>();
		wheatstock_Dtls.addAll(wheatreceiving.getWheatstock_Dtls());
		for(Wheatstock_Dtls wsDetails:wheatstock_Dtls) 
		{
			wsDetails.setWheatreceiveid(gen_sno);
			if(Utility.isNullOrEmpty(wsDetails.getWheat_grade()))
			{
				wsDetails.setWheat_grade_name(item_masterRepository.getItemDetailsThruItemId(wsDetails.getWheat_grade()).getItem_name());
			}
			else 
			{
				wsDetails.setWheat_grade_name("");
			}
			wsDetails.setCompany_id(wheatreceiving.getCompany_id());
			wsDetails.setFin_year(wheatreceiving.getFin_year());
			wsDetails.setModified_type("INSERTED");
			wsDetails.setInserted_by(wheatreceiving.getInserted_by());
			wsDetails.setInserted_on(ldt);
			wsDetails.setUpdated_by("NA");
			wsDetails.setUpdated_on(ldt);
			wsDetails.setDeleted_by("NA");
			wsDetails.setDeleted_on(ldt);
			wsDetails.setDate(wheatreceiving.getDate());
			
			wsDetails.setUsername(wheatreceiving.getUsername());
		}
		wheatreceiving.setWheatstock_Dtls(wheatstock_Dtls);
		
		
		
		Set<Wheatreceiving_Dtls> wheatreceiving_Dtls=new HashSet<Wheatreceiving_Dtls>();
		wheatreceiving_Dtls.addAll(wheatreceiving.getWheatreceiving_Dtls());
		for(Wheatreceiving_Dtls wrDetails:wheatreceiving_Dtls) 
		{
			wrDetails.setWheatreceiveid(gen_sno);
			if(Utility.isNullOrEmpty(wrDetails.getHub()))
			{
				wrDetails.setHub_name(hubMasterRepository.getHubNameById(wrDetails.getHub()).getHubname());
			}
			else 
			{
				wrDetails.setHub_name("");
			}
			if(Utility.isNullOrEmpty(wrDetails.getGrade()))
			{
				wrDetails.setGradename(item_masterRepository.getItemDetailsThruItemId(wrDetails.getGrade()).getItem_name());
			}
			else 
			{
				wrDetails.setGradename("");
			}
			wrDetails.setCompany_id(wheatreceiving.getCompany_id());
			wrDetails.setFin_year(wheatreceiving.getFin_year());
			wrDetails.setModified_type("INSERTED");
			wrDetails.setInserted_by(wheatreceiving.getInserted_by());
			wrDetails.setInserted_on(ldt);
			wrDetails.setUpdated_by("NA");
			wrDetails.setUpdated_on(ldt);
			wrDetails.setDeleted_by("NA");
			wrDetails.setDeleted_on(ldt);
			wrDetails.setDate(wheatreceiving.getDate());
			wrDetails.setUsername(wheatreceiving.getUsername());
		}
		wheatreceiving.setWheatreceiving_Dtls(wheatreceiving_Dtls);
		
		
		Set<Wheat_issue_Dtls> wheat_issue_Dtls=new HashSet<Wheat_issue_Dtls>();
		wheat_issue_Dtls.addAll(wheatreceiving.getWheat_issue_Dtls());
		for(Wheat_issue_Dtls wIDetails:wheat_issue_Dtls) 
		{
			wIDetails.setWheatreceiveid(gen_sno);
			wIDetails.setCompany_id(wheatreceiving.getCompany_id());
			if(Utility.isNullOrEmpty(wIDetails.getIssue_grade()))
			{
				wIDetails.setIssue_grade_name(item_masterRepository.getItemDetailsThruItemId(wIDetails.getIssue_grade()).getItem_name());
			}
			else 
			{
				wIDetails.setIssue_grade_name("");
			}
			wIDetails.setFin_year(wheatreceiving.getFin_year());
			wIDetails.setModified_type("INSERTED");
			wIDetails.setInserted_by(wheatreceiving.getInserted_by());
			wIDetails.setInserted_on(ldt);
			wIDetails.setUpdated_by("NA");
			wIDetails.setUpdated_on(ldt);
			wIDetails.setDeleted_by("NA");
			wIDetails.setDeleted_on(ldt);
			wIDetails.setUsername(wheatreceiving.getUsername());
		}
		wheatreceiving.setWheat_issue_Dtls(wheat_issue_Dtls);
		
		return wheatreceivingRepository.save(wheatreceiving);	
	}
	
	@Transactional
	public Wheatreceiving update(Wheatreceiving wheatreceiving,long id) 
	{

		Optional<Wheatreceiving> op =wheatreceivingRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		wheatreceiving.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(wheatreceiving.getBusiness_unit()).getBusinessunit_name());
		if(Utility.isNullOrEmpty(wheatreceiving.getCreatedby()))
		{
			wheatreceiving.setCreatedby_name(employeeMasterRepository.getEmployee(wheatreceiving.getCreatedby()).getEmp_name());
		}
		else 
		{
			wheatreceiving.setCreatedby_name("");
		}
		wheatreceiving.setWheatreceiveid(op.get().getWheatreceiveid());
		wheatreceiving.setCompany_id(wheatreceiving.getCompany_id());
		wheatreceiving.setFin_year(wheatreceiving.getFin_year());
		wheatreceiving.setModified_type("INSERTED");
		wheatreceiving.setInserted_on(ldt);
		wheatreceiving.setInserted_by(userRepository.getUserDetails(wheatreceiving.getUsername()).getName());
		wheatreceiving.setUpdated_by(wheatreceiving.getUpdated_by());
		wheatreceiving.setUpdated_on(ldt);
		wheatreceiving.setDeleted_by("NA");
		wheatreceiving.setDeleted_on(ldt);
		
		wheatstock_DtlsRepository.revertWheatstock_Dtls(op.get().getWheatreceiveid());
		
		Set<Wheatstock_Dtls> wheatstock_Dtls=new HashSet<Wheatstock_Dtls>();
		wheatstock_Dtls.addAll(wheatreceiving.getWheatstock_Dtls());
		for(Wheatstock_Dtls wsDetails:wheatstock_Dtls) 
		{
			wsDetails.setWheatreceiveid(op.get().getWheatreceiveid());
			if(Utility.isNullOrEmpty(wsDetails.getWheat_grade()))
			{
				wsDetails.setWheat_grade_name(item_masterRepository.getItemDetailsThruItemId(wsDetails.getWheat_grade()).getItem_name());
			}
			else 
			{
				wsDetails.setWheat_grade_name("");
			}
			wsDetails.setCompany_id(wheatreceiving.getCompany_id());
			wsDetails.setFin_year(wheatreceiving.getFin_year());
			wsDetails.setModified_type("INSERTED");
			wsDetails.setInserted_by("NA");
			wsDetails.setInserted_on(ldt);
			wsDetails.setUpdated_by(userRepository.getUserDetails(wheatreceiving.getUsername()).getName());
			wsDetails.setUpdated_on(ldt);
			wsDetails.setDeleted_by("NA");
			wsDetails.setDeleted_on(ldt);
			wsDetails.setUsername(wheatreceiving.getUsername());
			wsDetails.setDate(wheatreceiving.getDate());
		}
		wheatreceiving.setWheatstock_Dtls(wheatstock_Dtls);
		
		
		wheatreceiving_DtlsReository.revertWheatreceiving_Dtls(op.get().getWheatreceiveid());
		
		Set<Wheatreceiving_Dtls> wheatreceiving_Dtls=new HashSet<Wheatreceiving_Dtls>();
		wheatreceiving_Dtls.addAll(wheatreceiving.getWheatreceiving_Dtls());
		for(Wheatreceiving_Dtls wrDetails:wheatreceiving_Dtls) 
		{
			
			
			wrDetails.setWheatreceiveid(op.get().getWheatreceiveid());
			if(Utility.isNullOrEmpty(wrDetails.getHub()))
			{
				wrDetails.setHub_name(hubMasterRepository.getHubNameById(wrDetails.getHub()).getHubname());
			}
			else 
			{
				wrDetails.setHub_name("");
			}
			if(Utility.isNullOrEmpty(wrDetails.getGrade()))
			{
				wrDetails.setGradename(item_masterRepository.getItemDetailsThruItemId(wrDetails.getGrade()).getItem_name());
			}
			else 
			{
				wrDetails.setGradename("");
			}
			wrDetails.setCompany_id(wheatreceiving.getCompany_id());
			wrDetails.setFin_year(wheatreceiving.getFin_year());
			wrDetails.setModified_type("INSERTED");
			wrDetails.setInserted_by(wheatreceiving.getInserted_by());
			wrDetails.setInserted_on(ldt);
			wrDetails.setUpdated_by("NA");
			wrDetails.setUpdated_on(ldt);
			wrDetails.setDeleted_by("NA");
			wrDetails.setDeleted_on(ldt);
			wrDetails.setDate(wheatreceiving.getDate());
			wrDetails.setUsername(wheatreceiving.getUsername());
		}
		wheatreceiving.setWheatreceiving_Dtls(wheatreceiving_Dtls);
		
		wheat_issue_DtlsRepository.revertWheatreceiving_Issue_Dtls(op.get().getWheatreceiveid());
		
		Set<Wheat_issue_Dtls> wheat_issue_Dtls=new HashSet<Wheat_issue_Dtls>();
		wheat_issue_Dtls.addAll(wheatreceiving.getWheat_issue_Dtls());
		for(Wheat_issue_Dtls wIDetails:wheat_issue_Dtls) 
		{
			
			
			wIDetails.setWheatreceiveid(op.get().getWheatreceiveid());
			if(Utility.isNullOrEmpty(wIDetails.getIssue_grade()))
			{
				wIDetails.setIssue_grade_name(item_masterRepository.getItemDetailsThruItemId(wIDetails.getIssue_grade()).getItem_name());
			}
			else 
			{
				wIDetails.setIssue_grade_name("");
			}
			wIDetails.setCompany_id(wheatreceiving.getCompany_id());
			wIDetails.setFin_year(wheatreceiving.getFin_year());
			wIDetails.setModified_type("INSERTED");
			wIDetails.setInserted_by(wheatreceiving.getInserted_by());
			wIDetails.setInserted_on(ldt);
			wIDetails.setUpdated_by("NA");
			wIDetails.setUpdated_on(ldt);
			wIDetails.setDeleted_by("NA");
			wIDetails.setDeleted_on(ldt);
			wIDetails.setUsername(wheatreceiving.getUsername());
		}
		wheatreceiving.setWheat_issue_Dtls(wheat_issue_Dtls);
		
		return wheatreceivingRepository.save(wheatreceiving);
	
	}
	
	@Transactional
	public Wheatreceiving delete(Wheatreceiving wheatreceiving,long id) 
	{

		Optional<Wheatreceiving> op = wheatreceivingRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Wheatreceiving wheat=op.get();
		
		wheat.setModified_type("DELETED");
		wheat.setInserted_by(op.get().getInserted_by());
		wheat.setInserted_on(op.get().getInserted_on());
		wheat.setUpdated_by(op.get().getUpdated_by());
		wheat.setUpdated_on(op.get().getUpdated_on());
		wheat.setDeleted_by(userRepository.getUserDetails(wheat.getUsername()).getName());
		wheat.setDeleted_on(ldt);
		
		wheatstock_DtlsRepository.revertWheatstock_Dtls(op.get().getWheatreceiveid());
		
		wheatreceiving_DtlsReository.deleteWheatreceiving_Dtls(op.get().getWheatreceiveid());
		
		wheat_issue_DtlsRepository.deleteWheatreceiving_Issue_Dtls(op.get().getWheatreceiveid());
		
		if(op.isPresent())
		{
			wheat.setId(id);
		}
		
			return wheatreceivingRepository.save(wheat);
		
	
	}
	
	public List<Wheatstock_Dtls> retrivewheatstock_Dtls(String wheatreceiveid)
	{
		List<Wheatstock_Dtls> details= new ArrayList<Wheatstock_Dtls>();
        details.addAll(wheatstock_DtlsRepository.getStockDetails(wheatreceiveid));
		
		return details;
	}
	public Wheatstock_Dtls getopeningstockwheatrecieve(String itemId,String date,String finyear) 
	{
		Wheatstock_Dtls stock = new Wheatstock_Dtls();
		
		List<Object[]> wheatlist=new ArrayList<Object[]>();
		wheatlist.addAll(wheatstock_DtlsRepository.closingstockitem(date,itemId));
	  
	    
	  
	    for(final Object o : wheatlist)
	    {
	    	 Object[] obj = (Object[]) o;
	    	 
		     
		      
		        stock.setClosingbags(obj[0].toString());
		        System.out.println(stock.getClosingbags());
				stock.setClosingloose(obj[1].toString());
				stock.setClosingqty(obj[2].toString());
	    	
	    }
		
		return stock;
	}

	public List<Wheatreceiving> searchWheatreceiving(String fromdate, String todate,String finyear)
	{
		List<Wheatreceiving> searchdaily =new ArrayList<Wheatreceiving>();
		String tablename="wheatreceiving",order_date="date";
		searchdaily.addAll(wheatreceivingRepository.getsearchWheatReceiving(tablename,order_date,fromdate,todate,finyear));
		
		List<Wheatreceiving> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Wheatreceiving::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Wheatreceiving findOne(long id) 
	{
		Optional<Wheatreceiving> op=wheatreceivingRepository.findById(id);
		return op.get();
	}
	
	public List<Wheatreceiving_Dtls> retriveWheatDetails(String dailystockid)
	{
		List<Wheatreceiving_Dtls> details= new ArrayList<Wheatreceiving_Dtls>();
		details.addAll(wheatreceiving_DtlsReository.getdetails(dailystockid));
		
		return details;
	}
	
	public List<Wheat_issue_Dtls> retriveWheatIssueDetails(String dailystockid)
	{
		List<Wheat_issue_Dtls> details= new ArrayList<Wheat_issue_Dtls>();
		details.addAll(wheat_issue_DtlsRepository.getIssueDetails(dailystockid));
		
		return details;
	}
}
