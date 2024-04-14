package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.System_settings;

public interface System_settingsService {

	public System_settings save(System_settings settings);
	
	public List<System_settings> getSystemSettings(String company);
	
	public System_settings findOne(Long id);
	
	public System_settings getSystemSettingsByComp(String comp);
	
	public System_settings update(System_settings settings,long id);
}
