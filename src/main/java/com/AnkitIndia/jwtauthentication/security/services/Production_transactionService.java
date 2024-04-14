package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Production_transaction;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_popup_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_output_popup_details;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_output_detailsDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Production_transactionService {
	
	public SequenceIdDTO getPTSequenceId(String businessunit,String sfloor,String prefix,String company);
	
	public Production_transaction save(Production_transaction pTransaction) throws JsonParseException, JsonMappingException, IOException;
	
	public List<Production_transaction> findAllProTrans();
	
	public List<Map<String, Object>> findAllProdTransfast(String date);
	
	public Production_transaction findOne(long id);
	
	public Production_transaction update(Production_transaction pTransaction,long id) throws JsonParseException, JsonMappingException, IOException;
	
	public List<Production_transaction_input_detailsDTO> getProdTranInputDetails(String prodid);
	
	public List<Production_transaction_output_detailsDTO> getProdTranOutputDetails(String prodid);
	
	public List<Production_transaction_input_popup_details> getInputPopup(long id,String item);
	
	public List<Production_transaction_output_popup_details> getOutputPopup(long id,String item);
	
	public Production_transaction deleteProdTransReg(Production_transaction PT,Long id) throws JsonParseException, JsonMappingException, IOException;
	
	public List<Map<String, Object>> getStoreconsumptiontransaction(String shop_floor);

	public List<Map<String, Object>> searchProductionTransaction(String business_unit1,String shop_floor1,String fromdate, String todate);

	public Production_transaction accountpostingproductionreg(long id);
}
