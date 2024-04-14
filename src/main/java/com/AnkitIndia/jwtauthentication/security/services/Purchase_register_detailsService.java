package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import com.AnkitIndia.jwtauthentication.model.Purchase_register_details;
import com.AnkitIndia.jwtauthentication.responseDTO.Purchase_register_detailsDTO;

public interface Purchase_register_detailsService {
	
	public List<Purchase_register_detailsDTO> getPurchaseRegisterdt(String purchase_report);
}
