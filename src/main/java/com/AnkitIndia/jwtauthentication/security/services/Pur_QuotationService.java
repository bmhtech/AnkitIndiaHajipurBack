package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_BrokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_ServiceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_quotation_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Pur_QuotationService {
	
	public SequenceIdDTO getQuotSequenceId(String prefix,String orderdate,String type);

	public Pur_Quotation save(Pur_Quotation pqc);
	
	public Pur_Quotation update(Pur_Quotation iMaster,Long id);
	
	public List<Pur_Quotation> findAll();
	
	 public List<Pur_Quotation_ServiceDTO> getPurQtyItemDtlsList();
	 
	 public List<Pur_QuotationDTO> getPurQtyDDCList(String ccc);
	 
	 public List<Pur_QuotationDTO> getPurQuotPrevList();
	 
	 public List<Pur_QuotationDTO> getPurQuotThruSuppList(String reftype,String suppid,String itemtype);
	 
	 public List<Pur_Quotation_ServiceDTO> getPurQtyCNQUPList(String quotation_no);
	
	 public Pur_Quotation findOne(long id);
	 
	 public Pur_QuotationDTO purQuotDetails(String quotationno);
	 
	 public List<Pur_Quotation_ServiceDTO> purQServRetriveList(String code);
	 
	 public Pur_quotation_Business_Partner_detailsDTO purQBPRetriveList(String code);
	 
	 public List<Pur_Quotation_DocDTO> purQDocRetriveList(String code);
	 
	 public List<Pur_Quotation_BrokerDTO> getPurQuotBrokerDtls(String quot_id);
	
	//public Pur_Quotation update(Pur_Quotation pqc,Long id);
}