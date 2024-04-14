package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface BinMasterTest {
	
	public Bin_master save(Bin_master bin);
	
	public Bin_master update(Bin_master bin,long id);
	
	public List<Bin_master> findAll();
	
	public Bin_master findOne(long id);
	
	public void delete(Bin_master bin);
	
	public SequenceIdDTO getBinSequenceId(String prefix,String company);
	
	public Bin_master deleteBin(Bin_master bin,long id);
	
	public List<Bin_master> findBin_master(String searchtext);

}
