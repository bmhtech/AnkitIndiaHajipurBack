package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Stock_reports;
import com.AnkitIndia.jwtauthentication.repository.Stock_reportsRepository;


@Service
public class Stock_reportsService_Imp implements Stock_reportsService{

	@Autowired
	Stock_reportsRepository stock_reportsRepository;
	
	@Transactional
	public List<Stock_reports> stocksReport(String reportname)
	{
		List<Stock_reports> reports=stock_reportsRepository.getStockReports(reportname);
		return reports;
	}
}
