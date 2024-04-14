package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.JobWorkItemAllocation;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_itemtag;
import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;
import com.AnkitIndia.jwtauthentication.responseDTO.ResponseDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;


public interface Jw_grn_itemtagService {
	
	public List<Map<String,Object>> getJwGRN();
	
	public List<Map<String,Object>> getJwGRNunique();
	
	public List<Map<String,Object>> getJobItemList();
	
	public List<Map<String,Object>> getJobItemTagMaster();

	public Jw_grn_itemtag save(Jw_grn_itemtag jw_grn_itemtag);
	
	public StatusDTO checkdeleteJobItemTagMaster(String jw_grn_tag);
	
	public Jw_grn_itemtag delete(Jw_grn_itemtag jw_grn_itemtag,long id);

	public ResponseDTO checkjw_itemallocation(String party,String job_item,double qty,String jw_grn_tag);
	
	public Jw_grn_itemtag findOne(long id);
	
	public List<Map<String,Object>> getJwGrnPartytagDetails(String jw_grn_tag);
	
	public List<Map<String,Object>> getJwGrnPartywitemDetails(String jw_grn_tag,String party);
	
	public Jw_grn_itemtag update(Jw_grn_itemtag jw_grn_itemtag,long id);
}
