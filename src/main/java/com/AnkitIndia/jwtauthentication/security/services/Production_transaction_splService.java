package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Production_transaction_spl_output_detailsDTO;

public interface Production_transaction_splService {
	
	public SequenceIdDTO getPTSSequenceId(String businessunit,String sfloor,String prefix,String company);
	
	public Production_transaction_spl save(Production_transaction_spl pTransaction);
	
	public List<Production_transaction_spl> findAllProTrans();
	
	public Production_transaction_spl findOne(long id);
	
	public Production_transaction_spl update(Production_transaction_spl pTransaction,long id);
	
	public List<Production_transaction_spl_input_detailsDTO> getProdTranInputDetails(String prodid);
	
	public List<Production_transaction_spl_output_detailsDTO> getProdTranOutputDetails(String prodid);
	
	
    public List<Map<String, Object>> getspecialProdInputReport(String business_unit,String shop_floor,String fromdate,String todate);
	 
	public List<Map<String, Object>> getspecialProdOutputReport(String business_unit,String shop_floor,String fromdate,String todate);
	
	public StatusDTO Prodtransaction_spl_Posting(String prodid,long id);
	
	public StatusDTO prodtransaction_spl_Posting_Undo(long id,String username); 
	
}
