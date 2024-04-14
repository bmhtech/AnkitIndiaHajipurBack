package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_master_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Acc_pay_term_masterService {
	public Acc_pay_term_master save(Acc_pay_term_master payterm);
	public List<Acc_pay_term_master> findAll();
	public List<Acc_pay_term_master> getAccPayTermsMaster();
	public Acc_pay_term_master findOne(long id);
	public Acc_pay_term_master update(Acc_pay_term_master payterm,long id);
	
	public List<Acc_pay_term_master> getPayTermList();
	
	
	public List<Acc_pay_term_masterDTO> getAccPaytermNc();
	
	public List<Acc_pay_term_masterDTO> getPayTermNCList();
	
	public List<Map<String,Object>> getPayTermNCListFast();
	
	public List<Acc_pay_term_master_detailsDTO> payTermRetriveList(String p_id);
	
	public SequenceIdDTO getPayTermSequenceId(String prefix,String company);
	
	public List<Acc_pay_term_master> findAccPayTerm(String searchtext);
	
	public Acc_pay_term_master deleteAccPayTerm(Acc_pay_term_master acc_pay_term_master,long id);
	
	public StatusDTO checkPayTermUsage(String payment_id);
}
