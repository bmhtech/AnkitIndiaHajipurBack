package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_payment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_trans_dtlsDTO;

public interface Sales_credit_noteService {
	
	public SalesSequenceIdDTO getCNSequenceId(String fin_year,String cntype,String cnotetype);
	
	public Sales_credit_note save(Sales_credit_note Sales_credit_note);
	
	public List<Sales_credit_note> findAll();
	
	public Sales_credit_note findOne(Long id);
	
	public Sales_credit_note retriveSalesCreditNoteposting(long id);
	
	public Sales_credit_note_bp_dtlsDTO getSalesCreditNoteBPD(String creditnoteid);
	
	public List<Sales_credit_note_broker_dtlsDTO> getSalesCreditNoteBD(String creditnoteid);
	
	public List<Sales_credit_note_docsDTO> getSalesCreditNoteD(String creditnoteid);
	
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteID(String creditnoteid);
	//getSalesCreditNoteIDposting
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteIDposting(String creditnoteid);
	
	public Sales_credit_note_payment_dtlsDTO getSalesCreditNotePD(String creditnoteid);
	
	public Sales_credit_note_shipment_dtlsDTO getSalesCreditNoteSD(String creditnoteid);
	
	public Sales_credit_note_tax_infoDTO getSalesCreditNoteTI(String creditnoteid);
	
	public List<Sales_credit_note_trans_dtlsDTO> getSalesCreditNoteTD(String creditnoteid);
	
	public  Sales_credit_note update(Sales_credit_note Sales_credit_note,long id);
	
	public Sales_credit_note deleteCreditNotes(Sales_credit_note creditNote,Long id);
	
	public List<Credit_item_groupwise_taxsumm> getcreditnotetaxcodes(String creditnoteid);
	
	public Sales_credit_note accountpostingCreditNote(long id);
	
	public Sales_credit_note accountpostingundo(long id);
	
	public List<Map<String, Object>> geteinvoicestatus_creditnote(long id,String invoiceno);
	
	public List<Map<String, Object>> getSalesCreditNoteFast(String compid);
	
	public List<Map<String, Object>> getSalesCreditNoteJobwork(String creditnoteid);
	
	public List<Map<String, Object>> getSalesCreditNoteJobworkPrice(String creditnoteid);
	
	public List<Map<String, Object>> getSalesCreditNoteReport(String fromdate,String todate,String invoicetype);
	
	public Map<String, Object> creditnoteeinvoicedetails(String creditnoteid);
	
	public List<Map<String,Object>> searchSalesCreditNote(String orderno,String fromdate, String todate,String party1,String finyear);
	
}
