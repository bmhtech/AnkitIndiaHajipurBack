package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Acc_pay_mode_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_mode_masterDTO;

public interface Acc_pay_mode_masterService {
	public Acc_pay_mode_master save(Acc_pay_mode_master paymode);
	public List<Acc_pay_mode_master> findAll();
	public Acc_pay_mode_master findOne(long id);
	public Acc_pay_mode_master update(Acc_pay_mode_master paymode,long id);
	
	
	public List<Acc_pay_mode_masterDTO> getAccPaymodeNc();
}
