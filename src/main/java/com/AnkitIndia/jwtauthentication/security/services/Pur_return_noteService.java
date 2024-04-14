package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Pur_return_noteService {
	
	public SequenceIdDTO getPRNSequenceId(String prdate);
	
	public Pur_return_note save(Pur_return_note pur_return_note);
	
	public Pur_return_note findOne(Long id);
	
	public Pur_return_note update(Pur_return_note pur_return_note,long id);
	
	public List<Pur_return_note> findAllRtnNotes(String company,String finyear);
	
	public Pur_return_noteDTO getPurRtnNoteDtls(String purreturnnoteid);
	
	public List<Pur_return_noteDTO> getPurReturnNotes(String bunit,String invdate,String company,String finyear);
	
	public List<Pur_return_note_item_dtlsDTO> getPurRtnNoteItemDtls(String purreturnnoteid);
	
	public List<Pur_return_note_broker_dtlsDTO> getPurRtnNoteBrokerDtls(String purreturnnoteid);
	
	public List<Pur_return_note_docsDTO> getPurRtnNoteDocs(String purreturnnoteid);
	
	public List<Pur_return_note_trans_dynDTO> getPurRtnNoteTransDyn(String purreturnnoteid);
	
	public Pur_return_note_trans_infoDTO getPurRtnNoteTransInfo(String purreturnnoteid);
	
	public Pur_return_note_weight_dtlDTO getPurRtnNoteWeDtls(String purreturnnoteid);
	
	public Pur_return_note_shipment_dtlsDTO  getPurRtnNoteShipDtls(String purreturnnoteid);
	
	public Pur_return_note_supplier_dtlsDTO  getPurRtnNoteSuppDtls(String purreturnnoteid);

	
}
