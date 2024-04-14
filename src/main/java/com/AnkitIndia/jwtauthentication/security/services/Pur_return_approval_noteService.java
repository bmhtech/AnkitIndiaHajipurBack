package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Pur_return_approval_noteService {
	
	public SequenceIdDTO getPRANSequenceId(String rtntype,String rtndate);
	
	public Pur_return_approval_note save(Pur_return_approval_note pur_return_approval_note);
	
	public Pur_return_approval_note findOne(Long id);
	
	public List<Pur_return_approval_note> getPurReturnApprovalNote(String company,String finyear);
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteForLA(String bunit,String supplier,String tdate,String company,String finyear);
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteThruWe(String bunit,String supplier,String tdate,String company,String finyear);
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteLowRate(String bunit,String supplier,String tdate,String company,String finyear);
	
	public Pur_return_approval_noteDTO getPurRtnAppNoteDtls(String purreturnid);
	
	public List<Pur_return_approval_docsDTO> getPurReturnApprovalDoc(String purreturnid);
	
	public List<Pur_return_approval_item_dtlsDTO> getPurReturnApprovalID(String purreturnid);
	
	public List<Pur_return_approval_broker_dtlsDTO> getPurReturnApprovalBD(String purreturnid);
	
	public List<Pur_return_approval_trans_dynDTO> getPurReturnApprovalTD(String purreturnid);
	
	public Pur_return_approval_weight_dtlDTO getPurReturnApprovalWD(String purreturnid);
	
	public Pur_return_approval_trans_infoDTO getPurReturnApprovalTI(String purreturnid);

	public Pur_return_approval_shipment_dtlsDTO getPurReturnApprovalSD(String purreturnid);

	public Pur_return_approval_supplier_dtlsDTO getPurReturnApprovalSuD(String purreturnid);
	
	public  Pur_return_approval_note update(Pur_return_approval_note pur_return_approval_note,long id);
	
	public List<Map<String,Object>> getReturnApprovalPopupData(String date,String bunit,String supplier,String returnbasedon,String finyear,String compid);
	
	public List<Map<String,Object>> getgrnlistbypurorder(String pur_orderid);
	
	public List<Map<String,Object>> getgrnitemlist(String grnlist);
	
}
