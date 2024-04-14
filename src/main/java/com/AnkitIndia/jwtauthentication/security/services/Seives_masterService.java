package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_master;

public interface Seives_masterService {
	
	public List<Seives_master> getSeiveslist();
	
	public Seives_master save(Seives_master seives_master);
	
	public Seives_master update(Seives_master seives_master,long id);
	
	public Seives_master delete(Seives_master seives_master,long id);
	
	public Seives_master findOne(long id);
	
	public List<Seives_Dtls> retriveSeivesDetails(String seivesid);

}
