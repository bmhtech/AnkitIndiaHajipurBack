package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Gate_Pass;

public interface Gate_PassService {
	
	public Gate_Pass saveGatePass (Gate_Pass gate_Pass);
	
	public List<Gate_Pass> findAllGPass();

}
