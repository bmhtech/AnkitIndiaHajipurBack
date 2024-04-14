package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Nongoodsservice;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_time_service;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface NongoodssrnService {
	
	public SequenceIdDTO getSRNNo(String finyear);
	
	public List<Map<String,Object>> getSRNlist(String finyear);
	
	
	public List<Map<String,Object>> getNonServiceOrderAllList(String ordertype,String bunit,String party,String srndate);
	
	public List<Nongoodsservice_item_details> getServiceItemList(String serviceid);
	
	public List<Map<String,Object>> getDescAccordingBillPeriodList(String nongoodsserviceid,String serviceno);
	
	public Nongoodssrn save(Nongoodssrn nongoodssrn);
	
	public Nongoodssrn update(Nongoodssrn nongoodssrn,long id);
	
	public Nongoodssrn delete(Nongoodssrn nongoodssrn,long id);
	
	public Nongoodssrn findOne(long id);
	
	public List<Nongoodssrn_item_details> retriveNongoodsSrnItem(String srnid);
	
	public List<Nonservicesrn_desc_details> getSrnItemDetailsSerList(String srnid,String services);
	
	public List<Nongoodssrn_time_service> retriveNongoodsSrnTime(String srnid);
	
	public List<Nongoodssrn_docs> retriveNongoodsSrnDocs(String srnid);

}
