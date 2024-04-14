package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;

import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls_static;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Production_planning_shop_floor_dtls_staticService {

	public Production_planning_shop_floor_dtls_static save(Production_planning_shop_floor_dtls_static pDtls) throws JsonParseException, JsonMappingException, IOException;
	
	public Production_planning_shop_floor_dtls_static update(Production_planning_shop_floor_dtls_static pDtls,String prodid,int slno) throws JsonParseException, JsonMappingException, IOException;
	
}
