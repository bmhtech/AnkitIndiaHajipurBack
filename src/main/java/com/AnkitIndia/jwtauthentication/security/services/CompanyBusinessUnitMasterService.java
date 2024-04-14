package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Business_unit;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface CompanyBusinessUnitMasterService {

	 public Company_business_unit_master save(Company_business_unit_master companyBusiness,MultipartFile files[]) throws IOException;
	 
	 public List<Company_business_unit_master> findAll();
	 
	 public Company_business_unit_master findOne(Long id);
	 
	 public Company_business_unit_master update(Company_business_unit_master companyBusiness,MultipartFile files[]) throws IOException;
	 
	 public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList(String company);
	 //Delete
	 public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList();
	 
	 public List<CompanyBusinessUnitMasterDTOC> getcompanyBUMNCListnew(String company);
	 
	 public List<Map<String,Object>> getcompanyBUMNCListFastApi(String company);
	 
	 public CompanyBusinessUnitMasterDTO getBUnitNameByCode(String buCode);
	 
	 public CompanyBusinessUnitMasterDTO CompanyBUAddress(String Id);
	 
	 public List<CompanyBusinessUnitDetailsDTO> compBURetriveList(String b_id);
	 
	 public List<CompanyBusinessUnitMasterDTO> getCompBusinessUnitDiff(String bunitid);
	 
	 public SequenceIdDTO getCompanyBusiSequenceId(String prefix,String company);
	 
	 public StatusDTO getBusiUnitStateStatus(String bunit,String dbunit);
	 
	 public List<Company_business_unit_master> findCompanyBUMaster(String searchtext);
	 
	 public Company_business_unit_master deleteCompanyBUMaster(Company_business_unit_master cbum,long id);
	 
	 public List<Business_unit> getcompanyBUnameList(String company);
	 
	 public StatusDTO checkMisleniousDeletation(String parent_id,String parentModel);
	 
	 public CompanyBusinessUnitMasterDTO getCBUdetails(String bunit);
	 
	 public CompanyBusinessUnitMasterDTO getCBUdetailsById(String bunit);
	 
	 public Map<String,Object> getCompanyBussinessUnitDetails(String company,String bunit);
	 
}
