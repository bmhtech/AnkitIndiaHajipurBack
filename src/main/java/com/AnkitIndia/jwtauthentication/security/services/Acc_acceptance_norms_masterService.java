package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master;
import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master_dts;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_master_dtsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_addr_dtlsDTO;


public interface Acc_acceptance_norms_masterService {
	
	public Acc_acceptance_norms_master save(Acc_acceptance_norms_master acceptance);
	public List<Acc_acceptance_norms_master> findAll();
	public Acc_acceptance_norms_master findOne(long id);
	public Acc_acceptance_norms_master update(Acc_acceptance_norms_master acceptance,long id);
	
	public List<Acc_acceptance_norms_masterDTO> getAccNormsNameCode();
	public List<Acc_acceptance_norms_master_dtsDTO> getAccNormsDetails(String code);
	
	public List<Acc_acceptance_norms_master_dtsDTO> accNormsRetriveList(String a_id);

}
