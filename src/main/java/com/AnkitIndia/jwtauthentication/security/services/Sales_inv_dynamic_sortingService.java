package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_inv_dynamic_sortingDTO;

public interface Sales_inv_dynamic_sortingService {

	//public Sales_inv_dynamic_sorting save(Sales_inv_dynamic_sorting sales_inv_dynamic_sorting);
	
	public List<Sales_inv_dynamic_sortingDTO> getSalesSortDynList();
	
	//public Sales_inv_dynamic_sorting deleteSalesSortDynList(Sales_inv_dynamic_sorting sales_inv_dynamic_sorting,long id);
}
