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
import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.model.Bingroup;
import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Binreportdetails;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailystockfinishgoodRepository;
import com.AnkitIndia.jwtauthentication.repository.Dailystockfinishgood_DtlsReository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;


@Service
public class DailystockfinishgoodService_Imp implements DailystockfinishgoodService{
	
	@Autowired
	DailystockfinishgoodRepository dailystockfinishgoodRepository;
	
	@Autowired
	Dailystockfinishgood_DtlsReository dailystockfinishgood_DtlsReository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Transactional
	public Dailystockfinishgood save(Dailystockfinishgood dailystockfinishgood) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dailystockfinishgoodRepository.countId() != null ) {
			slno=Long.parseLong(dailystockfinishgoodRepository.countId());
		}
		String prefix="DSF";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		dailystockfinishgood.setDailystockid(gen_sno);
		dailystockfinishgood.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailystockfinishgood.getBusiness_unit()).getBusinessunit_name());
		
		dailystockfinishgood.setCreatedby_name(employeeMasterRepository.getEmployee(dailystockfinishgood.getCreatedby()).getEmp_name());
		if(Utility.isNullOrEmpty(dailystockfinishgood.getApprovedby()))
		{
		dailystockfinishgood.setApprovedby_name(employeeMasterRepository.getEmployee(dailystockfinishgood.getApprovedby()).getEmp_name());
		}
		else 
		{
			dailystockfinishgood.setApprovedby_name("");
		}
		dailystockfinishgood.setCompany_id(dailystockfinishgood.getCompany_id());
		dailystockfinishgood.setFin_year(dailystockfinishgood.getFin_year());
		dailystockfinishgood.setModified_type("INSERTED");
		dailystockfinishgood.setInserted_on(ldt);
		dailystockfinishgood.setInserted_by(userRepository.getUserDetails(dailystockfinishgood.getUsername()).getName());
		dailystockfinishgood.setUpdated_by(dailystockfinishgood.getUpdated_by());
		dailystockfinishgood.setUpdated_on(ldt);
		dailystockfinishgood.setDeleted_by("NA");
		dailystockfinishgood.setDeleted_on(ldt);
		
		
	
		
		
		Set<Dailystockfinishgood_Dtls> dailystockfinishgood_Dtlss=new HashSet<Dailystockfinishgood_Dtls>();
		dailystockfinishgood_Dtlss.addAll(dailystockfinishgood.getDailystockfinishgood_Dtls());
		for(Dailystockfinishgood_Dtls gpDetails:dailystockfinishgood_Dtlss) 
		{
			gpDetails.setDailystockid(gen_sno);
			System.out.println(" item code :: "+gpDetails.getItem_code());
			gpDetails.setDate(dailystockfinishgood.getDate());
			gpDetails.setItemname(item_masterRepository.getItemDetailsThruItemId(gpDetails.getItem_code()).getItem_name());
			gpDetails.setCompany_id(dailystockfinishgood.getCompany_id());
			gpDetails.setFin_year(dailystockfinishgood.getFin_year());
			gpDetails.setModified_type("INSERTED");
			gpDetails.setInserted_by(dailystockfinishgood.getInserted_by());
			gpDetails.setInserted_on(ldt);
			gpDetails.setUpdated_by("NA");
			gpDetails.setUpdated_on(ldt);
			gpDetails.setDeleted_by("NA");
			gpDetails.setDeleted_on(ldt);
		}
		dailystockfinishgood.setDailystockfinishgood_Dtls(dailystockfinishgood_Dtlss);
		
		
		return dailystockfinishgoodRepository.save(dailystockfinishgood);	
	}
	
	@Transactional
	public Dailystockfinishgood update(Dailystockfinishgood dailystockfinishgood,long id) 
	{

		Optional<Dailystockfinishgood> op =dailystockfinishgoodRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		dailystockfinishgood.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailystockfinishgood.getBusiness_unit()).getBusinessunit_name());
		dailystockfinishgood.setCreatedby_name(employeeMasterRepository.getEmployee(dailystockfinishgood.getCreatedby()).getEmp_name());
		
		if(Utility.isNullOrEmpty(dailystockfinishgood.getApprovedby()))
		{
		dailystockfinishgood.setApprovedby_name(employeeMasterRepository.getEmployee(dailystockfinishgood.getApprovedby()).getEmp_name());
		}
		else 
		{
			dailystockfinishgood.setApprovedby_name("");
		}
		dailystockfinishgood.setDailystockid(op.get().getDailystockid());
		dailystockfinishgood.setCompany_id(dailystockfinishgood.getCompany_id());
		dailystockfinishgood.setFin_year(dailystockfinishgood.getFin_year());
		dailystockfinishgood.setModified_type("INSERTED");
		dailystockfinishgood.setInserted_on(ldt);
		dailystockfinishgood.setInserted_by(userRepository.getUserDetails(dailystockfinishgood.getUsername()).getName());
		dailystockfinishgood.setUpdated_by(dailystockfinishgood.getUpdated_by());
		dailystockfinishgood.setUpdated_on(ldt);
		dailystockfinishgood.setDeleted_by("NA");
		dailystockfinishgood.setDeleted_on(ldt);
		
	
		dailystockfinishgood_DtlsReository.revertDailystockfinishgood_DtlsReository(op.get().getDailystockid());
		
		Set<Dailystockfinishgood_Dtls> dailystockfinishgood_Dtlss=new HashSet<Dailystockfinishgood_Dtls>();
		dailystockfinishgood_Dtlss.addAll(dailystockfinishgood.getDailystockfinishgood_Dtls());
		for(Dailystockfinishgood_Dtls gpDetails:dailystockfinishgood_Dtlss) 
		{
			
			
			gpDetails.setDailystockid(op.get().getDailystockid());
			gpDetails.setDate(dailystockfinishgood.getDate());
			gpDetails.setItemname(item_masterRepository.getItemDetailsThruItemId(gpDetails.getItem_code()).getItem_name());
			gpDetails.setCompany_id(dailystockfinishgood.getCompany_id());
			gpDetails.setFin_year(dailystockfinishgood.getFin_year());
			gpDetails.setModified_type("INSERTED");
			gpDetails.setInserted_by(dailystockfinishgood.getInserted_by());
			gpDetails.setInserted_on(ldt);
			gpDetails.setUpdated_by("NA");
			gpDetails.setUpdated_on(ldt);
			gpDetails.setDeleted_by("NA");
			gpDetails.setDeleted_on(ldt);
		}
		dailystockfinishgood.setDailystockfinishgood_Dtls(dailystockfinishgood_Dtlss);
		
		return dailystockfinishgoodRepository.save(dailystockfinishgood);
	
	}
	
	@Transactional
	public Dailystockfinishgood delete(Dailystockfinishgood dailystockfinishgood,long id) 
	{

		Optional<Dailystockfinishgood> op = dailystockfinishgoodRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Dailystockfinishgood dailystock=op.get();
		
		dailystock.setModified_type("DELETED");
		dailystock.setInserted_by(op.get().getInserted_by());
		dailystock.setInserted_on(op.get().getInserted_on());
		dailystock.setUpdated_by(op.get().getUpdated_by());
		dailystock.setUpdated_on(op.get().getUpdated_on());
		dailystock.setDeleted_by(userRepository.getUserDetails(dailystock.getUsername()).getName());
		dailystock.setDeleted_on(ldt);
		
		dailystockfinishgood_DtlsReository.revertdeleteDailystockfinishgood_DtlsReository(op.get().getDailystockid());
		
		if(op.isPresent())
		{
			dailystock.setId(id);
		}
		
			return dailystockfinishgoodRepository.save(dailystock);
		
	
	}
	public List<Dailystockfinishgood> getdailystockfinishedgoodslist(String currDate,String finyear)
	{
		System.out.println(currDate + " // " + finyear);
		List<Dailystockfinishgood> daliylist = new ArrayList<Dailystockfinishgood>();
		daliylist.addAll(dailystockfinishgoodRepository.getdailylist(currDate,finyear));
		
		
		//System.out.println(daliylist.get(0));
		return daliylist;
	}

	public List<Dailystockfinishgood> searchdailystockfinishedgoods(String fromdate, String todate,String finyear)
	{
		List<Dailystockfinishgood> searchdaily =new ArrayList<Dailystockfinishgood>();
		String tablename="dailystockfinishgood",order_date="date";
		searchdaily.addAll(dailystockfinishgoodRepository.getsearchdatapower(tablename,order_date,fromdate,todate,finyear));
		
		List<Dailystockfinishgood> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Dailystockfinishgood::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Dailystockfinishgood findOne(long id) 
	{
		Optional<Dailystockfinishgood> op=dailystockfinishgoodRepository.findById(id);
		return op.get();
	}
	
	public List<Dailystockfinishgood_Dtls> getdailystockfinishedgoodsdtlslist(String dailystockid)
	{
		List<Dailystockfinishgood_Dtls> details= new ArrayList<Dailystockfinishgood_Dtls>();
		details.addAll(dailystockfinishgood_DtlsReository.getdetails(dailystockid));
		
		/*List<Dailystockfinishgood_Dtls> allData = details
				  .stream()
				  .sorted(Comparator.comparing(Dailystockfinishgood_Dtls::getSlno))
				  .collect(Collectors.toList());*/
		
		return details;
	}
	
/*	public Dailystockfinishgood_Dtls getdailystockfinishedgoodopeningstock(String itemId,String date,String finyear)
	{
		Dailystockfinishgood details=dailystockfinishgoodRepository.getdetails(date,finyear);
		System.out.println("data::"+details.getDailystockid()+"//"+itemId);
		Dailystockfinishgood_Dtls itemdetails=dailystockfinishgood_DtlsReository.getitemperiddetails(details.getDailystockid(),itemId);
		
		return itemdetails;
	}*/
	public Dailystockfinishgood_Dtls getdailystockfinishedgoodopeningstock(String itemId,String date,String finyear)
	{
		//boolean check=dailystockfinishgood_DtlsReository.checkItem(date,itemId);
		Dailystockfinishgood_Dtls itemdetails=new Dailystockfinishgood_Dtls();
		itemdetails.setClosingstock(dailystockfinishgood_DtlsReository.closingstockitem(date,itemId));
		/*if(check==true)
		{
			itemdetails=dailystockfinishgood_DtlsReository.getitemperidDateDetails(date,itemId);
		}
		else
		{
			itemdetails.setClosingstock("0");
		}
		*/
		 //closingstockitem
		
		return itemdetails;
	}
	
	public Status_table getcheckdate(String date,String finyear) 
	{
		Status_table res=new Status_table();
		int count=dailystockfinishgoodRepository.countdate(date,finyear);
		System.out.println("count :: " + count);
		if(count>0) 
		{
			res.setStatus("YES");
		}
		else 
		{
			res.setStatus("NO");
		}
		
		return res;
		
	}
	
	public List<Dailystockfinishgood_Dtls> getItemThruSalesThruBUanddDate(String bunit,String compid,String date,String finyear)
	{
		List<Dailystockfinishgood_Dtls> reportdetails = new ArrayList<Dailystockfinishgood_Dtls>();
		 
		 boolean status=dailystockfinishgoodRepository.checkDailyStockFG();
		 
		 if(status == true) 
		 {
			 System.out.println("FINISHGOODS::"+bunit+"//"+date+"//"+finyear);	 
			 Dailystockfinishgood lastdata=dailystockfinishgoodRepository.getlastrowdata(bunit,date,finyear);//DSF00004
		System.out.println("id::"+lastdata.getDailystockid());	 
			 List<Dailystockfinishgood_Dtls> getdetailsdata=dailystockfinishgood_DtlsReository.getdetails(lastdata.getDailystockid());
			 for(int i=0;i<getdetailsdata.size();i++) 
			 {
				 System.out.println(getdetailsdata.size()+"//"+getdetailsdata.get(i).getItemname()+"///"+getdetailsdata.get(i).getClosingstock());
				 Dailystockfinishgood_Dtls details=new Dailystockfinishgood_Dtls();
				 
				 details.setDailystockid(getdetailsdata.get(i).getDailystockid());
				 details.setSlno(getdetailsdata.get(i).getSlno());
				 details.setItem_code(getdetailsdata.get(i).getItem_code());
				// details.setOpeningstock(getdetailsdata.get(i).getOpeningstock()); 
				 details.setOpeningstock(getdetailsdata.get(i).getClosingstock()); //changes on 28-01-2023
				 details.setProduction("0");
				 details.setConversion("0");
				 details.setSale("0");
				 details.setClosingstock("0");
				 
				 reportdetails.add(details);
				 
			 }
			
		 }
		 return reportdetails;
	}
	
}
