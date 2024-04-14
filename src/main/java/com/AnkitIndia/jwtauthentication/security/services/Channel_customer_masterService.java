package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_customer_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Channel_customer_masterService {
	
	public Channel_customer_master save(Channel_customer_master cMaster);
	public List<Channel_customer_master> findAll();
	public List<Channel_customer_masterDTO> getChannelCustDesc();
	
	public Channel_customer_master findOne(Long id);
	
	public StatusDTO terminatechannel(long id);
	
	public Channel_customer_master update(Channel_customer_master pMaster,long id);
	
	public Channel_customer_master deleteChannel_customer(Channel_customer_master cMaster,long id);
	
	public List<Channel_customer_master> findChannel_customer(String searchtext);
	
	public StatusDTO checkChennelCustomerMasterUsage(String code);
	
	public List<Map<String, Object>> getChannelCustFastApi();
	
	public List<Map<String, Object>> getChannelCustForSales();
	
}
