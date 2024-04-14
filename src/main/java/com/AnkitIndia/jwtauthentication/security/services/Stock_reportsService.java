package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Stock_reports;

public interface Stock_reportsService {

	public List<Stock_reports> stocksReport(String reportname);
}
