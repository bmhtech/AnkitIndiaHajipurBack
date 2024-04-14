package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_debit_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_account_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_bp_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_broker_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_musterroll_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Pur_debit_noteService {
	
	public SequenceIdDTO getDNSequenceId(String dndate,String dntype);
	
	public Pur_debit_note save(Pur_debit_note pur_debit_note);
	
	public Pur_debit_note findOne(Long id);
	
	public Pur_debit_note update(Pur_debit_note pur_debit_note,long id);
	
	public List<Pur_debit_note> findAllDNotes(String company,String finyear);
	
	public List<Pur_debit_note_item_detailsDTO> getPurDebitID(String debitnoteid);
	
	public List<Pur_debit_note_musterroll_detailsDTO> getPurDebitMD(String debitnoteid);
	
	public List<Pur_debit_note_broker_detailsDTO> getPurDebitBD(String debitnoteid);
	
	public List<Pur_debit_note_docsDTO> getPurDebitD(String debitnoteid);
	 
	public Pur_debit_note_tax_infoDTO getPurDebitTI(String debitnoteid);
	 
	public Pur_debit_note_bp_detailsDTO getPurDebitBPD(String debitnoteid);
	 
	public Pur_debit_note_account_infoDTO getPurDebitAI(String debitnoteid);
	 

}
