package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Acc_taxcode_details_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_taxcode_details_masterDTO;

public interface Acc_taxcode_details_masterService {
	public Acc_taxcode_details_master save(Acc_taxcode_details_master taxcodedetails);
	public List<Acc_taxcode_details_master> findAll();
	public Acc_taxcode_details_master findOne(long id);
	public Acc_taxcode_details_master update(Acc_taxcode_details_master taxcodedetails,long id);
	
	public List<Acc_taxcode_details_masterDTO> getAccTaxcodeNc();
	
}
