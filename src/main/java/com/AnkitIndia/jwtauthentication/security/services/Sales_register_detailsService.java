package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
//import com.AnkitIndia.jwtauthentication.model.Sales_register_details;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;
public interface Sales_register_detailsService {
public List<Sales_register_detailsDTO> getSalesRegisterdto(String sales_report);

}
