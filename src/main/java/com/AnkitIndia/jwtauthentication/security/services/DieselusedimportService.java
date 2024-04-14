package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;

public interface DieselusedimportService {

	public List<Dieselusedimport> getDieselusedimportlist(String currDate,String finyear);

	public Dieselusedimport save(Dieselusedimport dieselusedimport);
	
	public Dieselusedimport update(Dieselusedimport dieselusedimport,long id);
	
	public Dieselusedimport delete(Dieselusedimport dieselusedimport,long id);
	
	public List<Dieselusedimport> searchDieselusedimportReport(String fromdate, String todate,String finyear);
	
	public Dieselusedimport findOne(long id);
}
