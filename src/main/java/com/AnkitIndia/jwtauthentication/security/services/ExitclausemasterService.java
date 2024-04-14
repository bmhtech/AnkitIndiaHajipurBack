package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Exitclausemaster;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface ExitclausemasterService {
	
	public SequenceIdDTO getESequenceId(String fin_year);
	
	public Exitclausemaster save(Exitclausemaster exitclausemaster);
	
	public List<Map<String, Object>> getExitClauseMasterList(String fin_year);

	public Exitclausemaster findOne(long id);
	
	public Exitclausemaster update(Exitclausemaster exitclausemaster, long id);
	
	public Exitclausemaster delete(Exitclausemaster exitclausemaster, long id);
	
}
