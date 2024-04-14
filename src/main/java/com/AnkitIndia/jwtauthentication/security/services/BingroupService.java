package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Bingroup;

public interface BingroupService {

	public List<Bingroup> getBingrouplist();
	
	public Bingroup save(Bingroup bingroup);
	
	public Bingroup update(Bingroup bingroup,long id);
	
	public Bingroup delete(Bingroup bingroup,long id);
	
	public Bingroup findOne(long id);
}
