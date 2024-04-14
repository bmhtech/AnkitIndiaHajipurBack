package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_stock;

public interface Item_stockRepository extends JpaRepository<Item_stock, Long>{

	@Query("Select count(id) from Item_stock")
	String countItemStock();

	@Query(value="select id,stockid,entrypersonname,entrydate from item_stock where modified_type='INSERTED' ", nativeQuery=true)
	List<Map<String, Object>> getStocklist();
	
	@Query(value="select itemname,itemcode,packingname,packingcode,item_type from itemlist", nativeQuery=true)
	List<Map<String, Object>> getAllItemFromStockView();
	
	@Query(value="select slno,itemname,itemcode,packingname,packingcode,itemtype,openitembal,openpackingbal,openingdate,openingfinyear from item_stock_dtls where modified_type='INSERTED' and stockid=:code order by slno", nativeQuery=true)
	List<Map<String, Object>> retriveStockDetails(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Item_stock_dtls u SET u.modified_type ='UPDATED' WHERE u.stockid = :stockid and u.modified_type ='INSERTED'")
	  int updateStockdtls(@Param("stockid") String stockid);

	
	
}
