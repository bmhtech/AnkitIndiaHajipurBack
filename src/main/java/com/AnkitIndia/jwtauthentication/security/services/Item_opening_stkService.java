package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Item_opening_stk;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_opening_stk_dtlsDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Item_opening_stkService {

	public Item_opening_stk save(Item_opening_stk iOpening_stk) throws JsonParseException, JsonMappingException, IOException;
	
	public List<Item_opening_stk_dtlsDTO> getItemOpeningStk();
	
}
