package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Order;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_TerminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Terminations_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_docsDTO;

public interface Stock_Indent_Order_Service {
	
	public SequenceIdDTO getSTISequenceId(String idate);
	
	public Stock_Indent_Order save(Stock_Indent_Order stock_Indent_Order);
	
	public List<Stock_Indent_Order> findAll();
	
	public Stock_Indent_Order findOne(long id);
	
	public List<Stock_Indent_Item_DetailsDTO> getStkIndentOrderDetailsList(String indent_id);
	
	public List<Stock_Indent_docsDTO> getStkIndentDocsList(String indent_id);
	
	public List<Stock_Indent_OrderDTO> getStkIndOrder();
	
	public List<Stock_Indent_Terminations_dynDTO> getStkIndentTermDyn(String indent_id);
	
	public Stock_Indent_TerminationsDTO getStkIndTer(String indent_id);

	public Stock_Indent_OrderDTO getStkIndODR(String indent_id);
}