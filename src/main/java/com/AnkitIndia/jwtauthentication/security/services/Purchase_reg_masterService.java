package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Purchase_reg_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Purchase_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;



public interface Purchase_reg_masterService {
	public Purchase_reg_master save(Purchase_reg_master purchase_reg_master);
	public List<Purchase_register_detailsDTO> getPurchaseRegisterList();
	public List<Purchase_register_detailsDTO> getPurReportNameList(String purchase_report);
	public List getPurReportDynamic(String purchase_report,String reportname);
	public List getPurReportCol(String purchase_report,String reportname);
}
