package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Sales_transport;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Sales_transportService {

	public SequenceIdDTO getTransJVCode(String prefix,String currDate);
	
	public Sales_transport save(Sales_transport sales_transport);
	
	public Sales_transport findOne(long id);
	
	public Sales_transport update(Sales_transport sales_transport,long id);
	
	//public List<Map<String, Object>> getSalesTransactionReport(String fromdate,String todate);
	
	public List<Map<String, Object>> getSalesTransactionReport(String fromdate,String todate,String challanno,String salestype,String finyear,String jvnum,String date_search_type);
	
	public List<Map<String, Object>> getSalesTransactionReportCheckbalance(String fromdate,String todate,String inv_typenew,String trans_code);
	
	public List<Map<String, Object>> getSalesTransactionReportList(String currentdate);
	
	public List<Map<String, Object>> getSalestransportappchgsList(String sales_tranport_id);
	
	public Map<String, Object> getSalesTransport(String id);
	
	public List<Map<String, Object>> getSalesTransportChgs(String transportId);
	
	public Sales_transport accountpostingsalestransport(long id); 
	
	public Sales_transport accountpostingsalestransportundo(long id); 
	
	public List<Map<String, Object>> getDuplicateRefTransport();
	
	public List<Map<String, Object>> searchTransportStatement(String fromdate,String todate,String invoicetype);
	
	public StatusDTO checkBulkSupply(String adviceid);
			
}
