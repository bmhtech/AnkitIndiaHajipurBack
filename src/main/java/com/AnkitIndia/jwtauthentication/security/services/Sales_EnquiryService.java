package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_stat_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Sales_EnquiryService {
	
	public SalesSequenceIdDTO getSalesEnqSequenceId(String prefix,String orderdate,String type);
	
	public Sales_Enquiry save(Sales_Enquiry sales_Enquiry);
	
	public Sales_Enquiry update(Sales_Enquiry iMaster,Long id);
	
	public List<Sales_Enquiry> findAll();
	
	public SequenceIdDTO getSequenceId(String prefix);

	public List<Sales_EnquiryDTO> salesEnquiryList();
	
	public List<Sales_EnquiryDTO> getSalesEnquiriesOpen(String qdate);
	
	public Sales_EnquiryDTO salesEnquiryByEnqId(String enquiry_id);
	
	public List<Sales_Enquiry_Item_DtlsDTO> getSalesEnqItemDtls(String enquiry_id);
	
	public Sales_EnquiryDTO salesEnqPersonList(String enquiry_id);
	
	public List<Sales_Enquiry_Party_DtlsDTO> getSalesEnqPartyDtls(String enquiry_id);
	
	public List<Sales_Enquiry_DocsDTO> getSalesEnqDoc(String enquiry_id);
	
	public Sales_Enquiry findOne(long id);
}
