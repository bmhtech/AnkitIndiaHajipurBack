package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Sales_return_note_trans_infoDTO;


public interface Sales_return_noteService {
	
	public Sales_return_note save(Sales_return_note sales_return_note);
	
	public SalesSequenceIdDTO getSRSequenceId(String fin_year,String inv_type);
	
	public List<Sales_return_noteDTO> getSalesRtnNote(String bunit,String party,String invdate,String company,String finyear,String invoicetype);
	
	public List<Map<String,Object>> getSalesRtnNoteJw(String date,String bunit,String party_id);
	
	public List<Map<String,Object>> getSalesRtnNoteJwdetails(String salereturn);
	
	public List<Map<String,Object>> getsalereturnjobworkprice(String salereturn);
	
	
	public Sales_return_noteDTO getSalesReturnNoteDtls(String salesreturnnoteid);

	public List<Map<String, Object>> getSalesReturnNote(String company,String currentdate);
	
	public Sales_return_note findOne(Long id);
	
	public List<Sales_return_note_broker_dtlsDTO> getSalesReturnNoteBD(String salesreturnnoteid);
	
	public List<Sales_return_note_docsDTO> getSalesReturnNoteD(String salesreturnnoteid);
	
	public List<Sales_return_note_item_dtlsDTO> getSalesReturnNoteID(String salesreturnnoteid);
	
	public List<Map<String,Object>> getSalesReturnNoteIDjobwork(String salesreturnnoteid);
	
	public List<Sales_return_note_party_dtlsDTO> getSalesReturnNotePD(String salesreturnnoteid);
	
	public Sales_return_note_shipment_dtlsDTO getSalesReturnNoteSD(String salesreturnnoteid);
	
	public Sales_return_note_trans_infoDTO getSalesReturnNoteTI(String salesreturnnoteid);
	
	public List<Invoice_Sales_return_note_trans_infoDTO> getSalesRtnNoteTransInfo(String salesreturnnoteid);
	
	public List<Invoice_Sales_return_note_trans_infoDTO> getMultipleSalesRtnNoteTransInfo(String salesreturnnoteid);
	
	public Sales_return_note_weight_dtlDTO getSalesReturnNoteWD(String salesreturnnoteid);
	
	public  Sales_return_note update(Sales_return_note sales_return_note,long id);
	
	public StatusDTO salesReturnNoteUsage(String salesreturnnoteid);
	
	public Sales_return_note deleteSalesReturnNotes(Sales_return_note salesReturnNote,Long id);
	
	public  List<Sales_return_note> getcreditnotepopupsalesreturn(long id);
	
	public  List<Sales_return_note_item_dtls> getsales_creditnote(String salesreturnid,long id);
	
	public List<Sales_return_note_item_dtlsDTO> getMultipleSalesReturnNoteitemlist(String salesreturnnoteid);
	
	public List<Sales_return_note_item_dtlsDTO> getMultipleSalesReturnNoteitemlistUpdate(Long id);
	
	public List<Sales_return_noteDTO> getSalesRtnNoteUpdate(Long id);
	
	public  List<Map<String, Object>> searchSalesReturnNote(String fromdate,String todate,String party1);
	
	public List<Map<String, Object>> retriveSalesReturnNoteJobwork(String salesreturnnoteid);
}
