package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_stock;
import com.AnkitIndia.jwtauthentication.model.Item_stock_dtls;
import com.AnkitIndia.jwtauthentication.model.Service_masterdtls;
import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_stockRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Item_stockService_Imp implements Item_stockService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_stockRepository item_stockRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	
	public List<Map<String,Object>> getStocklist()
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(item_stockRepository.getStocklist());
		return list;
	}
	
	public List<Map<String,Object>> getAllItemFromStockView()
	{
		List<Map<String,Object>> viewlist = new ArrayList<Map<String,Object>>();
		viewlist.addAll(item_stockRepository.getAllItemFromStockView());
		return viewlist;
	}
	
	@Transactional
	public Item_stock save(Item_stock stock) {
		    LocalDateTime ldt = LocalDateTime.now();
		    int slno=0;
		    String sno=item_stockRepository.countItemStock();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("IST-",slno);
			stock.setStockid(gen_sno);
			
			if(Utility.isNullOrEmpty(stock.getEntryperson())) {
				stock.setEntrypersonname(employeeMasterRepository.getEmployee(stock.getEntryperson()).getEmp_name());
			}else{stock.setEntrypersonname("0");};
			
			stock.setModified_type("INSERTED");
			stock.setInserted_by(userRepository.getUserDetails(stock.getUsername()).getName());
			stock.setInserted_on(ldt);
			stock.setUpdated_by("NA");
			stock.setUpdated_on(ldt);
			stock.setDeleted_by("NA");
			stock.setDeleted_on(ldt);
		
			Set<Item_stock_dtls> item_stock_dtls=new HashSet<Item_stock_dtls>();
			item_stock_dtls.addAll(stock.getItem_stock_dtls());
			for(Item_stock_dtls sDetails:item_stock_dtls) 
			{
				sDetails.setStockid(gen_sno);
				sDetails.setCompany_id(stock.getCompany_id());
				sDetails.setFin_year(stock.getFin_year());
				sDetails.setModified_type("INSERTED");
				sDetails.setInserted_by(stock.getInserted_by());
				sDetails.setInserted_on(ldt);
				sDetails.setUpdated_by("NA");
				sDetails.setUpdated_on(ldt);
				sDetails.setDeleted_by("NA");
				sDetails.setDeleted_on(ldt);
				sDetails.setUsername(stock.getUsername());
			}
			stock.setItem_stock_dtls(item_stock_dtls);
			
		return item_stockRepository.save(stock);
	}
	

	public Item_stock findOne(long id)
	{
		Optional<Item_stock> op=this.item_stockRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String, Object>> retriveStockDetails(String s_no)
	{
		List<Map<String, Object>> stockdtls=new ArrayList<Map<String, Object>>();
		stockdtls.addAll(item_stockRepository.retriveStockDetails(s_no));
		return stockdtls;
	}
	
	@Transactional
	public Item_stock update(Item_stock stock,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Item_stock> op = item_stockRepository.findById(id);
		
		if(Utility.isNullOrEmpty(stock.getEntryperson())) {
			stock.setEntrypersonname(employeeMasterRepository.getEmployee(stock.getEntryperson()).getEmp_name());
		}else{stock.setEntrypersonname("0");};
		
		stock.setStockid(op.get().getStockid());
		stock.setModified_type("INSERTED");
		stock.setInserted_by(op.get().getInserted_by());
		stock.setInserted_on(op.get().getInserted_on());
		stock.setUpdated_by(userRepository.getUserDetails(stock.getUsername()).getName());
		stock.setUpdated_on(ldt);
		stock.setDeleted_by(op.get().getDeleted_by());
		stock.setDeleted_on(op.get().getDeleted_on());
		
		//Dynamic
		item_stockRepository.updateStockdtls(stock.getStockid());
		Set<Item_stock_dtls> item_stock_dtls=new HashSet<Item_stock_dtls>();
		item_stock_dtls.addAll(stock.getItem_stock_dtls());
		for(Item_stock_dtls stockdtls:item_stock_dtls) 
		{
			stockdtls.setStockid(op.get().getStockid());
			stockdtls.setCompany_id(stock.getCompany_id());
			stockdtls.setFin_year(stock.getFin_year());
			stockdtls.setUsername(stock.getUsername());
			stockdtls.setModified_type("INSERTED");
			stockdtls.setInserted_by(stock.getInserted_by());
			stockdtls.setInserted_on(stock.getInserted_on());
			stockdtls.setUpdated_by(stock.getUpdated_by());
			stockdtls.setUpdated_on(stock.getUpdated_on());
			stockdtls.setDeleted_by(stock.getDeleted_by());
			stockdtls.setDeleted_on(stock.getDeleted_on());
		}
	
		stock.setItem_stock_dtls(item_stock_dtls);
		
		if(op.isPresent())
		{
			stock.setId(id);
		}
		return item_stockRepository.save(stock);
	}
	
}
