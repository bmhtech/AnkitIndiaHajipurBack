package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.ShopFloorAccess;

public interface ShopFloorAccessService {

	
	public List<ShopFloorAccess> getAccesslist(String fyear);
	
	public ShopFloorAccess save(ShopFloorAccess shopFloorAccess);
	
	public ShopFloorAccess update(ShopFloorAccess shopFloorAccess,long id);
	
	public ShopFloorAccess findOne(long id);
	
	public ShopFloorAccess deleteFloorAccess(ShopFloorAccess shopFloorAccess,long id);
}
