package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Store_issue_note;
import com.AnkitIndia.jwtauthentication.model.Store_issue_note_details;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_stockRepository;
import com.AnkitIndia.jwtauthentication.repository.Store_issue_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Store_issue_note_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Store_issue_noteService_Imp implements Store_issue_noteService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Store_issue_noteRepository store_issue_noteRepository;
	
	@Autowired
	Store_issue_note_detailsRepository store_issue_note_detailsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	public List<Map<String,Object>> getstoreIssuelist()
	{
		return store_issue_noteRepository.getstoreIssuelist();
	}
	
	 @Transactional
		public Store_issue_note save(Store_issue_note store_issue_note) 
		{
			LocalDateTime ldt = LocalDateTime.now();
			
			 int slno=0;
			 
			    String sno=store_issue_noteRepository.countId();
				
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				if(sno != null )
				{
					slno=Integer.parseInt(sno);
				}
				
				
				String prefix="SIN";
				String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
				
				store_issue_note.setStore_issue_id(gen_sno);
				store_issue_note.setModified_type("INSERTED");
				store_issue_note.setFin_year(store_issue_note.getFin_year());
				store_issue_note.setInserted_by(userRepository.getUserDetails(store_issue_note.getUsername()).getName());
				store_issue_note.setInserted_on(ldt);
				store_issue_note.setUpdated_by("NA");
				store_issue_note.setUpdated_on(ldt);
				store_issue_note.setDeleted_by("NA");
				store_issue_note.setDeleted_on(ldt);
			
			
			Set<Store_issue_note_details> details = new HashSet<Store_issue_note_details>();
			details.addAll(store_issue_note.getStore_issue_note_details());
			for(Store_issue_note_details itemdetails : details)
			{
				itemdetails.setStore_issue_id(gen_sno);
				if(Utility.isNullOrEmpty(itemdetails.getItem())) {
					itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem()).getItem_name());
				}else{itemdetails.setItem_name("0");};
				itemdetails.setFin_year(store_issue_note.getFin_year());
				itemdetails.setModified_type("INSERTED");
				itemdetails.setInserted_by(userRepository.getUserDetails(store_issue_note.getUsername()).getName());
				itemdetails.setInserted_on(ldt);
				itemdetails.setUpdated_by("NA");
				itemdetails.setUpdated_on(ldt);
				itemdetails.setDeleted_by("NA");
				itemdetails.setDeleted_on(ldt);
				
			}
			
			store_issue_note.setStore_issue_note_details(details);
			
			return store_issue_noteRepository.save(store_issue_note);
		}
	 
	public Store_issue_note retriveStoreIssueNote(long id) 
		{
			Optional<Store_issue_note> op =store_issue_noteRepository.findById(id);
			return op.get();
		}
	
	
	public List<Map<String,Object>> retriveStoreIssueNoteDetails(String issueno)
	{
		return store_issue_note_detailsRepository.retriveStoreIssueNoteDetails(issueno);
	}
	
	@Transactional
	public Store_issue_note update(Store_issue_note store_issue_note,long id) 
	{

		Optional<Store_issue_note> op =store_issue_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		store_issue_note.setStore_issue_id(op.get().getStore_issue_id());
		store_issue_note.setModified_type("INSERTED");
		store_issue_note.setInserted_by(op.get().getInserted_by());
		store_issue_note.setInserted_on(op.get().getInserted_on());
		store_issue_note.setUpdated_by(userRepository.getUserDetails(store_issue_note.getUsername()).getName());
		store_issue_note.setUpdated_on(ldt);
		store_issue_note.setDeleted_by("NA");
		store_issue_note.setDeleted_on(ldt);
		
		store_issue_note_detailsRepository.updateStoreIssueDetails(store_issue_note.getStore_issue_id(),"UPDATED");
		
		Set<Store_issue_note_details> details = new HashSet<Store_issue_note_details>();
		details.addAll(store_issue_note.getStore_issue_note_details());
		for(Store_issue_note_details itemdetails : details)
		{
			itemdetails.setStore_issue_id(op.get().getStore_issue_id());
			if(Utility.isNullOrEmpty(itemdetails.getItem())) {
				itemdetails.setItem_name(item_masterRepository.itemNameById(itemdetails.getItem()).getItem_name());
			}else{itemdetails.setItem_name("0");};
			itemdetails.setCompany_id(store_issue_note.getCompany_id());
			itemdetails.setFin_year(store_issue_note.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(op.get().getInserted_by());
			itemdetails.setInserted_on(op.get().getInserted_on());
			itemdetails.setUpdated_by(userRepository.getUserDetails(store_issue_note.getUsername()).getName());
			itemdetails.setUpdated_on(ldt);
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
		}
		
		if(op.isPresent())
		{
			store_issue_note.setId(id);
		}
		store_issue_note.setStore_issue_note_details(details);
		
		return store_issue_noteRepository.save(store_issue_note);
	
		
	}
	
	@Transactional
	public Store_issue_note deleteStoreIssueNote(Store_issue_note store_issue_note,long id) 
	{
		Optional<Store_issue_note> op =store_issue_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Store_issue_note issue=op.get();
		
		issue.setModified_type("DELETED");
		issue.setInserted_by(op.get().getInserted_by());
		issue.setInserted_on(op.get().getInserted_on());
		issue.setUpdated_by(op.get().getUpdated_by());
		issue.setUpdated_on(op.get().getUpdated_on());
		issue.setDeleted_by(userRepository.getUserDetails(issue.getUsername()).getName());
		issue.setDeleted_on(ldt);
		
		store_issue_note_detailsRepository.updateStoreIssueDetails(issue.getStore_issue_id(),"DELETED");
		
		if(op.isPresent())
		{
			issue.setId(id);
		}
		return store_issue_noteRepository.save(issue);
		
	}
	
	public Map<String,Object> getPoStoreQty(String item,String clasitem,String warehouse)
	{
		return store_issue_noteRepository.getPoStoreQty(item,clasitem,warehouse);
	}
	
	public List<Map<String,Object>> getItemThruGrn()
	{
		return store_issue_noteRepository.getItemThruGrn();
	}
	
	public List<Map<String,Object>> retriveClassifiedItemThruGrn(String item_id)
	{
		return store_issue_noteRepository.retriveClassifiedItemThruGrn(item_id);
	}
	
	public List<Map<String,Object>> getWarehouseFromPoStoreItem()
	{
		return store_issue_noteRepository.getWarehouseFromPoStoreItem();
	}
	
	
}
