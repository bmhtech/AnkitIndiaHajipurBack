package com.AnkitIndia.jwtauthentication.security.services;

import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface JobOrderService {
	
	public SequenceIdDTO getOSequenceId(String fin_year);

}
