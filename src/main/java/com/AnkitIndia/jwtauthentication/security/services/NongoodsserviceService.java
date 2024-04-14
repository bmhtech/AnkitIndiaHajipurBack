package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Nongoodsservice;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_bank_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_terms_con;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_time_service;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface NongoodsserviceService {

	public List<Map<String,Object>> getNonGoodsServicelist(String finyear);
	
	public SequenceIdDTO getServiceNo(boolean check,String finyear);
	
	public List<Map<String,Object>> getCustomerSupplierList(String bunit,boolean check);
	
	public List<Map<String,Object>> getServiceList(String servicetype);
	
	public List<Map<String,Object>> getServiceMasterDetails(String service);
	
	public Map<String,Object> getDescCode(String service);
			
	public Nongoodsservice save(Nongoodsservice nongoodsservice);
	
	public Nongoodsservice update(Nongoodsservice nongoodsservice,long id);
	
	public Nongoodsservice delete(Nongoodsservice nongoodsservice,long id);
	
	public Nongoodsservice findOne(long id);
	
	public List<Nongoodsservice_item_details> retriveNongoodsServiceItem(String nongoodsid);
	
	public List<Nonservice_desc_details> getItemDetailsSerList(String nongoodsid,String serviceno);
			
	public Nongoodsservice_terms_con retriveNongoodsServiceTermsCon(String nongoodsid);
	
	public List<Nongoodsservice_party_dtls> retriveNongoodsServiceParty(String nongoodsid);
	
	public Nongoodsservice_bank_dtls retriveNongoodsServiceBankDtls(String nongoodsid);
	
	public Nongoodsservice_summary retriveNongoodsServiceSummary(String nongoodsid);
	
	public List<Nongoodsservice_summary_dyn> retriveNongoodsServiceSummaryDyn(String nongoodsid);
	
	public List<Nongoodsservice_time_service> retriveNongoodsServiceTimeService(String nongoodsid);
	
	public List<Nongoodsservice_docs> retriveNongoodsServiceDocs(String nongoodsid);
	
	public Nongoodsservice_exit_clause retriveNongoodsServiceExitClause(String nongoodsid);
	
	public List<Nongoodsservice_exit_clause_dyn> retriveNongoodsServiceExitClauseDyn(String nongoodsid);
}
