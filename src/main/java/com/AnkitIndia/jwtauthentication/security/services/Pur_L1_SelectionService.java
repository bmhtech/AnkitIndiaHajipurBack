package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_L1_Selection_DtlsDTO;

public interface Pur_L1_SelectionService {

	public Pur_L1_Selection save(Pur_L1_Selection pqc);
	
	public List<Pur_L1_Selection> findAll();
	
	public Pur_L1_Selection findOne(long id);
	
	public List<Pur_L1_Selection_DtlsDTO> l1DtlsRetriveList(String code);
	
	//public Pur_L1_Selection update(Pur_L1_Selection pqc,Long id);
	
}