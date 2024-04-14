package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setup_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Qc_rules_setupService {

	public Qc_rules_setup save(Qc_rules_setup qc_rules_setup);
	
	public Qc_rules_setup update(Qc_rules_setup qc_rules_setup,long id);
	
	public List<Qc_rules_setup>  findAll();
	
	public Qc_rules_setup findOne(long id);
	
	public List<Qc_rules_setup_dtlsDTO> qcRulesRetriveList(String q_id);
	
	public Qc_rules_setupDTO getQCRuleSetupDtls(String q_id);
	
	public List<Qc_rules_setupDTO> getQcrulesNc(String company);

	public SequenceIdDTO getQcRulesSequenceId(String prefix,String company);
	
	public Qc_rules_setup deleteQcRulesSetup(Qc_rules_setup qcMaster,long id);
	
	public List<Qc_rules_setup> findQcRulesSetup(String searchtext);
	
	public StatusDTO checkQualityCheckUsage(String qc_id);
	
}
