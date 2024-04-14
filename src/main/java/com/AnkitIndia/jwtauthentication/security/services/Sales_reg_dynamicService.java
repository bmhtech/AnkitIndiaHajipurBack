package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Sales_dynamic_sort;
import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_dynamic_sortDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.sales_reg_dynamicDTO;

public interface Sales_reg_dynamicService {
	
	public Sales_reg_dynamic save(Sales_reg_dynamic sales_reg_dynamic);
	public List<sales_reg_dynamicDTO> getSalesRegDynamicList();
	public Sales_reg_dynamic findOne(long id);
	public List<sales_reg_dynamicDTO> getSalesReportNameList();
	
	public List<Sales_reg_dynamic> getColumn(String reportname);
	public List<Sales_reg_dynamic> getRows(String reportname,String fromdate,String todate);
	public List<Sales_dynamic_sortDTO> salesDynamicSort(long reportname);
	public List<sales_reg_dynamicDTO> reportTypeDropdownList(String reporttype);
	public List<Sales_dynamic_sort> salessortde(String backend);
	public Sales_reg_dynamic update(Sales_reg_dynamic sales_reg_dynamic,long id);
	public Sales_reg_dynamic update2(Sales_reg_dynamic sales_reg_dynamic,long id);
	//public Sales_reg_dynamic deleteSalesRegDynamic(Sales_reg_dynamic sales_reg_dynamic,long id);
	public StatusDTO deleteSalesRegDynamic(long id);
	public List<Sales_reg_dynamic> getSalesRegDuplicateCheck();
	


}
