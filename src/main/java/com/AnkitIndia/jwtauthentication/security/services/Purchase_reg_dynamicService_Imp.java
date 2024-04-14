package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Purchase_reg_dynamic;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.repository.Purchase_reg_dynamicRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_reg_dynamicRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Purchase_reg_dynamicService_Imp implements Purchase_reg_dynamicService{

	@Autowired
	Purchase_reg_dynamicRepository purchase_reg_dynamicRepository;

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Purchase_reg_dynamic save(Purchase_reg_dynamic purchase_reg_dynamic) {
		System.out.println("sales_reg_dynamic.getTable_name()//"+purchase_reg_dynamic.getTable_name());
		LocalDateTime ldt = LocalDateTime.now();
		purchase_reg_dynamic.setPurchase_report(purchase_reg_dynamic.getPurchase_report());
		purchase_reg_dynamic.setReportfields(purchase_reg_dynamic.getReportfields());
		purchase_reg_dynamic.setReportname(purchase_reg_dynamic.getReportname());
		purchase_reg_dynamic.setTable_name(reorganizeTableName(purchase_reg_dynamic.getPurchase_report()));
		purchase_reg_dynamic.setStatic_report(purchase_reg_dynamic.getStatic_report());
		purchase_reg_dynamic.setDisplay_col(purchase_reg_dynamic.getDisplay_col());
		
		purchase_reg_dynamic.setModified_type("INSERTED");
		purchase_reg_dynamic.setInserted_by(userRepository.getUserDetails(purchase_reg_dynamic.getUsername()).getName());
		//purchase_reg_dynamic.setInserted_by("bb");
		purchase_reg_dynamic.setInserted_on(ldt);
		purchase_reg_dynamic.setUpdated_by("NA");
		purchase_reg_dynamic.setUpdated_on(ldt);
		purchase_reg_dynamic.setDeleted_by("NA");
		purchase_reg_dynamic.setDeleted_on(ldt);
		
		return purchase_reg_dynamicRepository.save(purchase_reg_dynamic);
	}
	
public String reorganizeTableName(String tableName) {
		
		String [] splitoutput=tableName.split(",");
		
		System.out.println("tableName/"+tableName);
		
		ArrayList<String> columnnamesplits =new ArrayList<String>();
		
		for(int j=0;j<splitoutput.length;j++) 
		{
			System.out.println(splitoutput[j]);
			String inside [] =splitoutput[j].split("\\.");
			
			
			System.out.println(" tuhin here look plz " + inside[0] + " / "+splitoutput[j]);
			columnnamesplits.add(inside[0]);
		}
		HashSet<String> unique = new HashSet<String>();
		for(int v=0;v<columnnamesplits.size();v++)
		{
			unique.add(columnnamesplits.get(v));
		}
		
		
		String semi_output="";
		 Iterator<String> i = unique.iterator();
		 
	        // Holds true till there is single element remaining
	        while (i.hasNext())
	        {
	        	semi_output +=i.next()+",";
	        	
	        }
	       String ultimatevalue= semi_output.substring(0, semi_output.length()-1);

		return ultimatevalue;
	}
}
