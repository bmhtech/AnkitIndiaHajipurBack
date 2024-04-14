package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Invoice_type;
import com.AnkitIndia.jwtauthentication.responseDTO.Invoice_typeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Invoice_typeService {
public Invoice_type save(Invoice_type area_master);

public Invoice_type update(Invoice_type invoice_type,long id);
	
	public List<Invoice_type> findAll();
	
	public Invoice_type findOne(long id);
	
	public List<Invoice_typeDTO> getSalesInvTypes();
	
	public List<Map<String,Object>> getSalesInvTypesFast();
	
	public SequenceIdDTO getInvtypeSequenceId(String prefix,String company);
	
	public Invoice_type deleteInvoiceType(Invoice_type invoice_type,long id);
	
	public List<Invoice_type> findInvoice_type(String searchtext);

}
