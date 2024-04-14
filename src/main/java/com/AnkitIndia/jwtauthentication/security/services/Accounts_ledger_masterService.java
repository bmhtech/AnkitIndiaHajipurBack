package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Accounts_ledger_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Accounts_ledger_masterService {
	
	
	public SequenceIdDTO getAccountLedgerSequenceId(String fin_year);
	
	public List<Map<String, Object>> accountledgerlist();
	
	public Accounts_ledger_master findOne(long id);
	
	public Accounts_ledger_master save(Accounts_ledger_master accounts_ledger_master);
	
	public Accounts_ledger_master update(Accounts_ledger_master accounts_ledger_master,long id);
	
	public Accounts_ledger_master deleteAccLedger(Accounts_ledger_master accounts_ledger_master,long id);
	
}
