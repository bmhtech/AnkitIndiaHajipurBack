package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_Peri_Quality_Check;


public interface Pur_Peri_Quality_CheckService {

	public Pur_Peri_Quality_Check save(Pur_Peri_Quality_Check pPeripheral);
	
	public List<Pur_Peri_Quality_Check> findAllPeripheral();
	
	public Pur_Peri_Quality_Check findOne(long id);
	
}
