package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Loading_point;
import com.AnkitIndia.jwtauthentication.responseDTO.Loading_pointDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Loading_pointService {
	
	public Loading_point save(Loading_point loading_point);
	
	public List<Loading_point> findAll();
	
	public List<Loading_pointDTO> getLoadingPointThruBU(String bunit);
	
	public List<Loading_pointDTO> getLoadingPointThruBUDiff(String bunit,String lpoint);
	
	public Loading_point findOne(Long id);

	public Loading_point update(Loading_point loading_point,long id);
	
	public SequenceIdDTO getLoadingPointSequenceId(String prefix,String company);
	
	public Loading_point deleteLoading_point(Loading_point loading_point,long id);
	
	public List<Loading_point> findLoading_point(String searchtext);
	
	public StatusDTO checkLoadingPointUsage(String code);
}
