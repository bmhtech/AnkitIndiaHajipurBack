package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Store_issue_note;

public interface Store_issue_noteService {

	public List<Map<String,Object>> getstoreIssuelist();
	
	public Store_issue_note save(Store_issue_note store_issue_note);
	 
	public Store_issue_note  retriveStoreIssueNote(long id);
		
	public List<Map<String,Object>> retriveStoreIssueNoteDetails(String store_issue_id);
	
	public Store_issue_note update(Store_issue_note store_issue_note,long id);
	
	public Store_issue_note deleteStoreIssueNote(Store_issue_note store_issue_note,long id);
	
	public Map<String,Object> getPoStoreQty(String item,String clasitem,String warehouse);
	
	public List<Map<String,Object>> getItemThruGrn();
	
	public List<Map<String,Object>> retriveClassifiedItemThruGrn(String item_id);
	
	public List<Map<String,Object>> getWarehouseFromPoStoreItem();
	
	
}
