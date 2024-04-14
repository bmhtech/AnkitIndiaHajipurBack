package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_wgmnt;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_doc_attchDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_wgmnt_dtlsDTO;

public interface Wm_loading_wgmntService {
	
	public Wm_loading_wgmnt save(Wm_loading_wgmnt wm_loading_wgmnt);
	
	public List<Wm_loading_wgmnt> findAll();
	
	public Wm_loading_wgmnt findOne(long id);
	
	public List<Wm_loading_wgmnt_dtlsDTO> wmLoadingDtlsRetriveList(String code);
	
	
}
