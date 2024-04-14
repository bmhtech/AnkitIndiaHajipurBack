package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Prodsummary;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface ProdsummaryService 
{
	public Prodsummary save(Prodsummary prodsummary);
	
	public List<Map<String,Object>> Productionsummaryitemdetails(String proddate);
	
	public SequenceIdDTO  getProductionsummurynumber(String finyear);
	
	public List<Map<String,Object>> getProductionSummurylist();
	
	public Prodsummary update(Prodsummary prodsummary,long id);
	
	public Prodsummary delete(Prodsummary prodsummary,long id);
	
	public Prodsummary findOne(long id);
	
	public List<Map<String,Object>> getProdSumDtls(String prod_sum_id);
	
	public StatusDTO Productionsummarydatecheck(String date);
	
	//public Prodsummary productionposting(long id);
	
	public StatusDTO ProdSummaryPosting(long id);
	
	public StatusDTO prodSummaryPostingUndo(long id,String username);  
	
}
