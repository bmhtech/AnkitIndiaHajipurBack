package com.AnkitIndia.Deletation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
@Repository
public class Miscellaneous_Master_Deletation {

	@Autowired
	private CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	private Pur_OrderRepository pur_OrderRepository;
	
	public StatusDTO checkingmiscelaneous(String parent_id,String parentModel) 
	{
		StatusDTO status_del =new StatusDTO();
		
		System.out.println("parent_id::"+parent_id+"parentModel::"+parentModel);
		if(parentModel.compareToIgnoreCase("Bussiness_Unit")==0) 
		{
			boolean purchase=false,sales=false;
			
			
			if(pur_OrderRepository.checkBussinessUnitPurchaseOrderUsage(parent_id) ) 
			{
				purchase=true;
			}
			if(companyBusinessUnitMasterRepository.checkBussinessUnitSalesOrderUsage(parent_id)) 
			{
				sales=true;
			}
			if(purchase == true || sales == true)
			{
				status_del.setStatus("Yes");
			}
			else
			{
				status_del.setStatus("No");
			}
			
			
		}
		
	System.out.println("HELLO HERE "+status_del.getStatus());
		
		return status_del;
	}
	
	
	
}
