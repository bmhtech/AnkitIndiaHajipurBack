package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Weigherreding;

public interface WeigherredingService {

	public List<Weigherreding> getWeigherReadingList(String currDate,String finyear);
	
	public Weigherreding save(Weigherreding weigherreding);
	
	public Weigherreding update(Weigherreding weigherreding,long id);
	
	public Weigherreding delete(Weigherreding weigherreding,long id);
	
	public List<Weigherreding> searcWeigherReading(String fromdate, String todate,String finyear);
	
	public Weigherreding findOne(long id);
}
