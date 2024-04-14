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

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls_One;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DailyproductionRepository;
import com.AnkitIndia.jwtauthentication.repository.Dailyproduction_DtlsReository;
import com.AnkitIndia.jwtauthentication.repository.Dailyproduction_Dtls_OneRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;

@Service
public class DailyproductionService_Imp implements DailyproductionService{
	
	
	@Autowired
	DailyproductionRepository dailyproductionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Dailyproduction_DtlsReository dailyproduction_DtlsReository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	Dailyproduction_Dtls_OneRepository dailyproduction_Dtls_OneReository;
	
	public List<Dailyproduction> getDailyproductionList(String currDate,String finyear)
	{
		List<Dailyproduction> daliylist = new ArrayList<Dailyproduction>();
		daliylist.addAll(dailyproductionRepository.getDailyproductionList(currDate,finyear));
		
		
		return daliylist;
	}
		
	@Transactional
	public Dailyproduction save(Dailyproduction dailyproduction) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(dailyproductionRepository.countId() != null ) {
			slno=Long.parseLong(dailyproductionRepository.countId());
		}
		String prefix="DPR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		dailyproduction.setDailyproductionid(gen_sno);
		dailyproduction.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailyproduction.getBusiness_unit()).getBusinessunit_name());
		dailyproduction.setShopfloor_name(shop_floor_masterRepository.getShopFloorDtls(dailyproduction.getShopfloor()).getShop_floor_name());

		dailyproduction.setCompany_id(dailyproduction.getCompany_id());
		dailyproduction.setFin_year(dailyproduction.getFin_year());
		dailyproduction.setModified_type("INSERTED");
		dailyproduction.setInserted_on(ldt);
		dailyproduction.setInserted_by(userRepository.getUserDetails(dailyproduction.getUsername()).getName());
		dailyproduction.setUpdated_by(dailyproduction.getUpdated_by());
		dailyproduction.setUpdated_on(ldt);
		dailyproduction.setDeleted_by("NA");
		dailyproduction.setDeleted_on(ldt);
		
		Set<Dailyproduction_Dtls> dailyproduction_Dtls=new HashSet<Dailyproduction_Dtls>();
		dailyproduction_Dtls.addAll(dailyproduction.getDailyproduction_Dtls());
		for(Dailyproduction_Dtls pDetails:dailyproduction_Dtls) 
		{
			pDetails.setDailyproductionid(gen_sno);
			System.out.println("set data::"+pDetails.getItem_code());
			pDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(pDetails.getItem_code()).getItem_name());
			pDetails.setCompany_id(dailyproduction.getCompany_id());
			pDetails.setFin_year(dailyproduction.getFin_year());
			pDetails.setModified_type("INSERTED");
			pDetails.setInserted_by(dailyproduction.getInserted_by());
			pDetails.setInserted_on(ldt);
			pDetails.setUpdated_by("NA");
			pDetails.setUpdated_on(ldt);
			pDetails.setDeleted_by("NA");
			pDetails.setDeleted_on(ldt);
			pDetails.setUsername(dailyproduction.getUsername());
		}
		dailyproduction.setDailyproduction_Dtls(dailyproduction_Dtls);
		
		
		Set<Dailyproduction_Dtls_One> dailyproduction_Dtls1=new HashSet<Dailyproduction_Dtls_One>();
		dailyproduction_Dtls1.addAll(dailyproduction.getDailyproduction_Dtls_One());
		for(Dailyproduction_Dtls_One p1Details:dailyproduction_Dtls1) 
		{
			p1Details.setDailyproductionid(gen_sno);
			p1Details.setCompany_id(dailyproduction.getCompany_id());
			p1Details.setFin_year(dailyproduction.getFin_year());
			p1Details.setModified_type("INSERTED");
			p1Details.setInserted_by(dailyproduction.getInserted_by());
			p1Details.setInserted_on(ldt);
			p1Details.setUpdated_by("NA");
			p1Details.setUpdated_on(ldt);
			p1Details.setDeleted_by("NA");
			p1Details.setDeleted_on(ldt);
			p1Details.setUsername(dailyproduction.getUsername());
		}
		dailyproduction.setDailyproduction_Dtls_One(dailyproduction_Dtls1);
		
		return dailyproductionRepository.save(dailyproduction);	
	}
	
	@Transactional
	public Dailyproduction update(Dailyproduction dailyproduction,long id) 
	{

		Optional<Dailyproduction> op =dailyproductionRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		dailyproduction.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(dailyproduction.getBusiness_unit()).getBusinessunit_name());
		dailyproduction.setShopfloor_name(shop_floor_masterRepository.getShopFloorDtls(dailyproduction.getShopfloor()).getShop_floor_name());
		
		dailyproduction.setDailyproductionid(op.get().getDailyproductionid());
		dailyproduction.setCompany_id(dailyproduction.getCompany_id());
		dailyproduction.setFin_year(dailyproduction.getFin_year());
		dailyproduction.setModified_type("INSERTED");
		dailyproduction.setInserted_on(ldt);
		dailyproduction.setInserted_by(userRepository.getUserDetails(dailyproduction.getUsername()).getName());
		dailyproduction.setUpdated_by(dailyproduction.getUpdated_by());
		dailyproduction.setUpdated_on(ldt);
		dailyproduction.setDeleted_by("NA");
		dailyproduction.setDeleted_on(ldt);
		
	
		dailyproduction_DtlsReository.revertDailyproduction_Dtls(op.get().getDailyproductionid());
		
		Set<Dailyproduction_Dtls> dailyproduction_Dtls=new HashSet<Dailyproduction_Dtls>();
		dailyproduction_Dtls.addAll(dailyproduction.getDailyproduction_Dtls());
		for(Dailyproduction_Dtls pDetails:dailyproduction_Dtls) 
		{
			
			
			pDetails.setDailyproductionid(op.get().getDailyproductionid());
			pDetails.setItem_name(item_masterRepository.getItemDetailsThruItemId(pDetails.getItem_code()).getItem_name());
			pDetails.setCompany_id(dailyproduction.getCompany_id());
			pDetails.setFin_year(dailyproduction.getFin_year());
			pDetails.setModified_type("INSERTED");
			pDetails.setInserted_by(dailyproduction.getInserted_by());
			pDetails.setInserted_on(ldt);
			pDetails.setUpdated_by("NA");
			pDetails.setUpdated_on(ldt);
			pDetails.setDeleted_by("NA");
			pDetails.setDeleted_on(ldt);
			pDetails.setUsername(dailyproduction.getUsername());
		}
		dailyproduction.setDailyproduction_Dtls(dailyproduction_Dtls);
		
		dailyproduction_Dtls_OneReository.revertDailyproduction_Dtls1(op.get().getDailyproductionid());
		
		Set<Dailyproduction_Dtls_One> dailyproduction_Dtls1=new HashSet<Dailyproduction_Dtls_One>();
		dailyproduction_Dtls1.addAll(dailyproduction.getDailyproduction_Dtls_One());
		for(Dailyproduction_Dtls_One p1Details:dailyproduction_Dtls1) 
		{
			
			
			p1Details.setDailyproductionid(op.get().getDailyproductionid());
			p1Details.setCompany_id(dailyproduction.getCompany_id());
			p1Details.setFin_year(dailyproduction.getFin_year());
			p1Details.setModified_type("INSERTED");
			p1Details.setInserted_by(dailyproduction.getInserted_by());
			p1Details.setInserted_on(ldt);
			p1Details.setUpdated_by("NA");
			p1Details.setUpdated_on(ldt);
			p1Details.setDeleted_by("NA");
			p1Details.setDeleted_on(ldt);
			p1Details.setUsername(dailyproduction.getUsername());
		}
		dailyproduction.setDailyproduction_Dtls_One(dailyproduction_Dtls1);
		
		return dailyproductionRepository.save(dailyproduction);
	
	}
	
	@Transactional
	public Dailyproduction delete(Dailyproduction dailyproduction,long id) 
	{

		Optional<Dailyproduction> op = dailyproductionRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Dailyproduction production=op.get();
		
		production.setModified_type("DELETED");
		production.setInserted_by(op.get().getInserted_by());
		production.setInserted_on(op.get().getInserted_on());
		production.setUpdated_by(op.get().getUpdated_by());
		production.setUpdated_on(op.get().getUpdated_on());
		production.setDeleted_by(userRepository.getUserDetails(production.getUsername()).getName());
		production.setDeleted_on(ldt);
		
		dailyproduction_DtlsReository.deleteDailyproduction_Dtls(op.get().getDailyproductionid());
		
		dailyproduction_Dtls_OneReository.deleteDailyproduction_Dtls1(op.get().getDailyproductionid());
		
		
		if(op.isPresent())
		{
			production.setId(id);
		}
		
			return dailyproductionRepository.save(production);
		
	
	}
	

	public List<Dailyproduction> searchDailyProduction(String fromdate, String todate,String finyear)
	{
		List<Dailyproduction> searchdaily =new ArrayList<Dailyproduction>();
		String tablename="dailyproduction",order_date="date";
		searchdaily.addAll(dailyproductionRepository.searchDailyProduction(tablename,order_date,fromdate,todate,finyear));
		
		List<Dailyproduction> allData = searchdaily
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Dailyproduction::getDate))
				  .collect(Collectors.toList());
		
		return allData;
		
		
	}
	
	public Dailyproduction_Dtls productionreportitembydata(String itemId, String date) 
	{
		Dailyproduction dailyproduction=dailyproductionRepository.getdetailsbydate(date);
		
		Dailyproduction_Dtls details= new Dailyproduction_Dtls();
		boolean check =dailyproduction_DtlsReository.checkexistproductionrep(itemId);
		if(check == true) 
		{
			details=dailyproduction_DtlsReository.getlastrowdetails(itemId,dailyproduction.getDailyproductionid());
		}
		else 
		{

			details.setSix_bag("0");
			details.setSix_percen("0");
			details.setEight_bag("0");
			details.setEight_percen("0");
			details.setTen_bag("0");
			details.setTen_percen("0");
			details.setTwelve_bag("0");
			details.setTwelve_percen("0");
			details.setForteen_bag("0");
			details.setForteen_percen("0");
			details.setSixteen_bag("0");
			details.setSixteen_percen("0");
			details.setEighteen_bag("0");
			details.setEighteen_percen("0");
			details.setTwenty_bag("0");
			details.setTwenty_percen("0");
			details.setTwentytwo_bag("0");
			details.setTwentytwo_percen("0");
			details.setZero_bag("0");
			details.setZero_percen("0");
			details.setTwo_bag("0");
			details.setTwo_percen("0");
			details.setFour_bag("0");
			details.setFour_percen("0");
			details.setTotal("0");
			details.setTotal_percen("0");
		}
		
		
		
		return details;
	}
	public Dailyproduction findOne(long id) 
	{
		Optional<Dailyproduction> op=dailyproductionRepository.findById(id);
		return op.get();
	}
	
	public List<Dailyproduction_Dtls> retriveProductionDetails(String dailyproductionid)
	{
		List<Dailyproduction_Dtls> details= new ArrayList<Dailyproduction_Dtls>();
		details.addAll(dailyproduction_DtlsReository.getdetails(dailyproductionid));
		
		return details;
	}
	
	public List<Dailyproduction_Dtls_One> retriveProductionDetails1(String dailyproductionid)
	{
		List<Dailyproduction_Dtls_One> details1= new ArrayList<Dailyproduction_Dtls_One>();
		details1.addAll(dailyproduction_Dtls_OneReository.getdetails1(dailyproductionid));
		
		return details1;
	}

	public List<Item_masterDTO> getItemCodeByTypeNew(String type) {
		
		
		List<Item_master> modelList=new ArrayList<Item_master>();
		Item_master setitemname=new Item_master();
		setitemname.setItem_id("0");
		setitemname.setItem_name("Choose an option");
		modelList.add(setitemname);
		modelList.addAll(item_masterRepository.getItemCodeByTypeNew(type));
		
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> itemCodeType= new ModelMapper().map(modelList,listType);
		return itemCodeType;
	 }
	 
	public Item_master_pack_mat_tag getItemCapacity(String item_code,String finyear) {
		//System.out.println("item::"+item_code+"//"+finyear);
		//Item_master_pack_mat_tag modelList=item_masterRepository.getItemCapacity(item_code,finyear);
		String capacity=item_masterRepository.getItemCapacity(item_code);
		
		Item_master_pack_mat_tag modelList=new Item_master_pack_mat_tag();
		modelList.setCapacity(capacity);
		
		return modelList;
	 }
	
}
