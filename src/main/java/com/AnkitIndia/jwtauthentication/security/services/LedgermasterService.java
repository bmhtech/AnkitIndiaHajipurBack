package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.responseDTO.LedgermasterDTO;

public interface LedgermasterService {
	public List<LedgermasterDTO> getLedgerBySGrp(String sGrpcode);
	
	public List<LedgermasterDTO> getLedger();
	
	public List<Map<String,Object>> getLedgerNew();
	
	public List<LedgermasterDTO> getLedgerWithBACH();
	
	public List<LedgermasterDTO> getDutiesTaxesLedger();
	
	public List<LedgermasterDTO> getControlLedgers();
	
	public List<LedgermasterDTO> getBankLedger();
	
	public LedgermasterDTO getLedgerName(String ledgerid);
	
	public List<Ledgermaster> getAccountPostingFor();
	
}
