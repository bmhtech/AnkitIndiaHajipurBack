package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Gst_input_output_ledger_dtls;
import com.AnkitIndia.jwtauthentication.model.Tax_type_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TaxTypeMasterDTO;


public interface TaxTypeMasterService {
	
	public SequenceIdDTO getTaxTypeSequenceId();
	
	public Tax_type_master save(Tax_type_master taxtype);
	
	public List<Tax_type_master> findAll();
	
	public List<Tax_type_master> getTaxTypeNCList();
	
	public Tax_type_master findOne(long id);
	
	public Tax_type_master update(Tax_type_master taxtype,long id);
	
	public List<TaxTypeMasterDTO> getTaxTypeNameCode();
	
	public Tax_type_master deleteTax_type(Tax_type_master taxtype,long id);
	
	public List<Tax_type_master> findTax_type(String searchtext);
	
	public Gst_input_output_ledger_dtls getgstdetailsoftaxtype(String taxtypecode);
	
	public List<Gst_input_output_ledger_dtls> retriveTaxTypeGst(String taxtypecode);
}
