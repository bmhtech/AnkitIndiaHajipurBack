package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;

import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;

public interface Sales_reg_masterService {
	
	public Sales_reg_master save(Sales_reg_master sales_reg_master);
	
	public List<Sales_register_detailsDTO> getSalesRegisterList();
	public Sales_reg_master update(Sales_reg_master sales_reg_master,long id);
	public List<Sales_register_detailsDTO> getReportNameList(String sales_report);
	public Sales_reg_master findOne(long id);
	
	public List getSalesInvoices(String sales_report,String reportname);
	public List getSalesCol(String sales_report,String reportname);
	
	
}
