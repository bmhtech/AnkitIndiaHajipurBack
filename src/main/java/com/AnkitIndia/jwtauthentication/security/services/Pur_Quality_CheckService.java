package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details_QcDetails;
import com.AnkitIndia.jwtauthentication.model.Ratechart;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quality_Check_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Pur_Quality_CheckService {

	public List<Map<String,Object>> getQClist(String finyear);
	
	public Pur_Quality_Check save(Pur_Quality_Check pqc);
	
	public List<Pur_Quality_Check> findAll();
	
	public Pur_Quality_Check findOne(Long id);
	
	public List<Pur_Quality_Check_Details> retriveQualityCheckDetails(String quality_check_id);
	
	public List<Pur_Quality_Check_Details_QcDetails> retriveQualityCheckDetailsQcDetails(String qcno,String qcid);
	
	public Pur_Quality_Check update(Pur_Quality_Check pqc,Long id);
	
	public Pur_Quality_Check delete(Pur_Quality_Check pqc,long id);
	
	public List<Pur_Quality_Check_DetailsDTO> purQChkRetriveList(String code);
	
	public List<Map<String,Object>> searchQC(String fromdate, String todate, String vehicle, String finyear);
	
	public StatusDTO checkQC(String advice_id);
}
