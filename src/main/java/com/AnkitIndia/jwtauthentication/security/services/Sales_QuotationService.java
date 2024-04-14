package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Quotation_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Trans_InfoDTO;

public interface Sales_QuotationService {
	
	public SalesSequenceIdDTO getSalesQuotSequenceId(String prefix,String orderdate,String type);
	
	public Sales_Quotation save(Sales_Quotation quotation);
	
	public Sales_Quotation update(Sales_Quotation quotation,Long id);
	
	public List<Sales_Quotation> findAll();
	
	public List<Map<String,Object>> getSalesQuotationsList(String cur_date);
	
	public List<Map<String,Object>> searchSaleQuotation(String fromdate,String todate);
	
	public List<Sales_QuotationDTO> salesQuotationList();
	
	public List<Sales_QuotationDTO> salesQuotationFinalise();
	
	public List<Sales_QuotationDTO> salesQuotationPrevList();
	
	public Sales_QuotationDTO getSalesQuotDetails(String quot_id);
	
	public List<Sales_Quotation_Item_DtlsDTO> getSalesQuotItemDtls(String quot_id);
	
	public List<Sales_Quotation_Broker_DtlsDTO> getSalesQuotBrokerDtls(String quot_id);
	
	public List<Sales_Quotation_Summary_dynDTO> getSalesQuotSummaryDtls(String quot_id);
	
	public List<Sales_Quotation_Party_DtlsDTO> getSalesQuotPartyDtls(String quot_id);
	
	public Sales_Quotation_SummaryDTO getSalesQuotSummary(String quot_id);
	
	public Sales_Quotation_Terms_ConDTO getSalesQuotTermsCon(String quot_id);
	
	public Sales_Quotation_Trans_InfoDTO getSalesQuotTransInfo(String quot_id);
	
	public Sales_Quotation_Shipment_DtlsDTO getSalesQuotShipDtls(String quot_id);
	
	public Sales_Quotation findOne(long id);
	
	public List<Sales_Quotation_DocsDTO> getSalesQuotDoc(String quot_id);
	
	public StatusDTO checkSalesQuotationUsage(String quot_id);
	
	public StatusDTO SalesQuotationTerminate(long id,String username);
	
	public Sales_Quotation deleteSalesQuotation(Sales_Quotation salesquot,Long id);
	

}
