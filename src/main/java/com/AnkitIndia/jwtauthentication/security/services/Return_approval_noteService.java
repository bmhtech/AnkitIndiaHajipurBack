package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Return_approval_trans_infoDTO;


public interface Return_approval_noteService {
	
	public SalesSequenceIdDTO getRANSequenceId(String fin_year,String return_type);
	
	public Return_approval_note save(Return_approval_note return_approval_note);
	
	public List<Return_approval_note> getReturnApprovalNote(String company,String finyear);
	
	public List<Return_approval_noteDTO> getReturnAppNoteThruUnAdv(String bunit,String uldate,String company,String finyear,String party);
	
	public List<Map<String,Object>> getReturnAppNoteThruUnAdvjobwork(String advice_date,String bunit,String party);
	
	public List<Map<String,Object>> getReturnAppNoteThruWejobwork(String date,String bunit,String party);
	
	
	public List<Return_approval_noteDTO> getReturnAppNoteThruWe(String invtype,String bunit,String party,String srdate,String company,String finyear);
	
	public List<Return_approval_noteDTO> getRtnAppNoteLowRate(String bunit,String party,String invdate,String company,String finyear,String invoicetype);
	
	public Return_approval_note findOne(Long id);
	
	public Return_approval_noteDTO getReturnApprovalDtls(String salesreturnid);
	
	public List<Return_approval_broker_dtlsDTO> getReturnApprovalBD(String salesreturnid);
	
	public List<Return_approval_docsDTO> getReturnApprovalD(String salesreturnid);
	
	public List<Return_approval_item_dtlsDTO> getReturnApprovalID(String salesreturnid);
	
	public List<Return_approval_party_dtlsDTO> getReturnApprovalPD(String salesreturnid);
	
	public Return_approval_shipment_dtlsDTO getReturnApprovalSD(String salesreturnid);
	
	public Return_approval_trans_infoDTO getReturnApprovalTI(String salesreturnid);
	
	public List<Invoice_Return_approval_trans_infoDTO> getReturnAppTransInfo(String salesreturnid);
	
	public Return_approval_weight_dtlDTO getReturnApprovalWD(String salesreturnid);
	
	public  Return_approval_note update(Return_approval_note return_approval_note,long id);
	
	public  List<Return_approval_note> getcreditnoteapproval(long id);
	
	public  List<Return_approval_item_dtls> getRtnAppNoteLowRateitemdetals(long id);
	
	public StatusDTO salesReturnApprovalNoteUsage(String salesreturnid);
	
	public Return_approval_note deleteSalesReturnApprovalNotes(Return_approval_note returnApprovalNote,Long id);
	
	public List<Return_approval_item_dtlsDTO> getMultipleReturnApprovalitemlist(String returnid);
	
	public List<Return_approval_noteDTO> getRtnAppNoteLowRateUpdate(Long id);
	
	public List<Return_approval_item_dtlsDTO> getMultipleReturnApprovalitemlistUpdate(Long id);
	
	public  List<Return_approval_note> getreturnapprovalnote_salesreturnupdate(long id);
	
	public  List<Return_approval_item_dtls> getRtnAppNoteLowRateitemdetals_returnapp(String salesreturnid,long id);
	
	public  List<Map<String, Object>> getReturnApprovalNoteList(String company,String currentdate);
	
	//public  List<Map<String, Object>> searchReturnApprovalNote(String fromdate,String todate,String party1);
	
	public  List<Map<String, Object>> searchReturnApprovalNote(String fromdate,String todate);
	
	public  List<Map<String, Object>> retriveReturnAppJobwork(String salesreturnid);
	
	public  List<Map<String, Object>> retriveReturnAppJobworkPrice(String salesreturnid);
	
	public  List<Map<String, Object>> getSalesAllTransactionData(String returnbasedon,String customer,String returndate,String bunit);
	
}
