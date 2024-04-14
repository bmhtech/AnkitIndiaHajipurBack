package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Bom_master;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.ShopFloorAccess;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ShopFloorAccessRepository;
import com.AnkitIndia.jwtauthentication.repository.Shop_floor_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class ShopFloorAccessService_Imp implements ShopFloorAccessService{
	
	@Autowired
	ShopFloorAccessRepository shopFloorAccessRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;

	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Shop_floor_masterRepository shop_floor_masterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	public List<ShopFloorAccess> getAccesslist(String fyear)
	{
		 List<ShopFloorAccess> modelList=  shopFloorAccessRepository.getAccesslist(fyear);
		 
		 return modelList;
	}
	
	@Transactional
	public ShopFloorAccess save(ShopFloorAccess shopFloorAccess)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(shopFloorAccessRepository.countId() != null ) {
			slno=Long.parseLong(shopFloorAccessRepository.countId());
		}
		String prefix="BM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		if(shopFloorAccess.getBusiness_unit().compareTo("0") !=0 && shopFloorAccess.getBusiness_unit().compareTo("") !=0 && shopFloorAccess.getBusiness_unit() !=null) {
			shopFloorAccess.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(shopFloorAccess.getBusiness_unit()).getBusinessunit_name());
		}else {shopFloorAccess.setBusiness_unitname("None");}
		
		/*if(shopFloorAccess.getShop_floor().compareTo("0") !=0 && shopFloorAccess.getShop_floor().compareTo("") !=0 && shopFloorAccess.getShop_floor() !=null) {
			shopFloorAccess.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(shopFloorAccess.getShop_floor()).getShop_floor_name());
		}else {shopFloorAccess.setShop_floorname("None");}*/
		
		if(shopFloorAccess.getOperator_name().compareTo("0") !=0 && shopFloorAccess.getOperator_name().compareTo("") !=0 && shopFloorAccess.getOperator_name() !=null) {
			shopFloorAccess.setOperatorname(employeeMasterRepository.getEmployee(shopFloorAccess.getOperator_name()).getEmp_name());
		}else {shopFloorAccess.setOperatorname("None");}
		
		shopFloorAccess.setAccessid(gen_sno);
		shopFloorAccess.setModified_type("INSERTED");
		shopFloorAccess.setInserted_by(userRepository.getUserDetails(shopFloorAccess.getUsername()).getName());
		shopFloorAccess.setInserted_on(ldt);
		shopFloorAccess.setUpdated_by("NA");
		shopFloorAccess.setUpdated_on(ldt);
		shopFloorAccess.setDeleted_by("NA");
		shopFloorAccess.setDeleted_on(ldt);
		
		return shopFloorAccessRepository.save(shopFloorAccess);
	}
	
	@Transactional
	public ShopFloorAccess update(ShopFloorAccess shopFloorAccess,long id) 
	{

		Optional<ShopFloorAccess> op =shopFloorAccessRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		if(shopFloorAccess.getBusiness_unit().compareTo("0") !=0 && shopFloorAccess.getBusiness_unit().compareTo("") !=0 && shopFloorAccess.getBusiness_unit() !=null) {
			shopFloorAccess.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(shopFloorAccess.getBusiness_unit()).getBusinessunit_name());
		}else {shopFloorAccess.setBusiness_unitname("None");}
		
	/*	if(shopFloorAccess.getShop_floor().compareTo("0") !=0 && shopFloorAccess.getShop_floor().compareTo("") !=0 && shopFloorAccess.getShop_floor() !=null) {
			shopFloorAccess.setShop_floorname(shop_floor_masterRepository.getShopFloorDtls(shopFloorAccess.getShop_floor()).getShop_floor_name());
		}else {shopFloorAccess.setShop_floorname("None");}*/
		
		if(shopFloorAccess.getOperator_name().compareTo("0") !=0 && shopFloorAccess.getOperator_name().compareTo("") !=0 && shopFloorAccess.getOperator_name() !=null) {
			shopFloorAccess.setOperatorname(employeeMasterRepository.getEmployee(shopFloorAccess.getOperator_name()).getEmp_name());
		}else {shopFloorAccess.setOperatorname("None");}
		
		shopFloorAccess.setAccessid(op.get().getAccessid());
		shopFloorAccess.setCompany_id(shopFloorAccess.getCompany_id());
		shopFloorAccess.setFin_year(shopFloorAccess.getFin_year());
		shopFloorAccess.setModified_type("INSERTED");
		shopFloorAccess.setInserted_on(ldt);
		shopFloorAccess.setInserted_by(userRepository.getUserDetails(shopFloorAccess.getUsername()).getName());
		shopFloorAccess.setUpdated_by(shopFloorAccess.getUpdated_by());
		shopFloorAccess.setUpdated_on(ldt);
		shopFloorAccess.setDeleted_by("NA");
		shopFloorAccess.setDeleted_on(ldt);
		
		return shopFloorAccessRepository.save(shopFloorAccess);
	}
	
	@Transactional
	public ShopFloorAccess deleteFloorAccess(ShopFloorAccess shopFloorAccess,long id) 
	{
		Optional<ShopFloorAccess> op = shopFloorAccessRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		ShopFloorAccess access=op.get();
		
		access.setModified_type("DELETED");
		access.setInserted_by(op.get().getInserted_by());
		access.setInserted_on(op.get().getInserted_on());
		access.setUpdated_by(op.get().getUpdated_by());
		access.setUpdated_on(op.get().getUpdated_on());
		access.setDeleted_by(userRepository.getUserDetails(access.getUsername()).getName());
		access.setDeleted_on(ldt);
		
		return shopFloorAccessRepository.save(access);
	}
	
	public ShopFloorAccess findOne(long id)
	{
		Optional<ShopFloorAccess> op=this.shopFloorAccessRepository.findById(id);
		return op.get();
	}
	
}
