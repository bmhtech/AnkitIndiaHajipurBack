package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_transaction;

public interface Sales_transactionRepository extends JpaRepository<Sales_transaction, Long>{

	@Query( "select s from Sales_transaction s where s.modified_type ='INSERTED' and s.reference_id =:orderid and s.business_unit =:bunit and s.item_id =:itemid and s.packing_id =:packingid ")
	Sales_transaction getSalesStockDetails(@Param("orderid") String orderid,@Param("bunit") String bunit,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Query( "select s from Sales_transaction s where s.modified_type ='INSERTED' and s.reference_id =:orderid")
	List<Sales_transaction> getSalesStocks(@Param("orderid") String orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_transaction s SET s.sales_item_qty =:itemqty,s.sales_pack_qty =:packqty,s.load_item_qty =:litemqty,s.load_pack_qty =:lpackqty,s.modified_type =:tstatus WHERE s.reference_id =:orderid and s.business_unit =:bunit and s.item_id =:itemid and s.packing_id =:packingid")
    int updateSales_transactions(@Param("orderid") String orderid,@Param("bunit") String bunit,@Param("itemid") String itemid,@Param("packingid") String packingid,@Param("itemqty") double itemqty,@Param("packqty") double packqty,@Param("litemqty") double litemqty,@Param("lpackqty") double lpackqty,@Param("tstatus") String tstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_transaction s SET s.modified_type =:tstatus WHERE s.reference_id =:orderid and s.business_unit =:bunit and s.item_id =:itemid and s.packing_id =:packingid")
    int updateSalesTransactions(@Param("orderid") String orderid,@Param("bunit") String bunit,@Param("itemid") String itemid,@Param("packingid") String packingid,@Param("tstatus") String tstatus);
	
	@Modifying
	@Query("DELETE FROM Sales_transaction s WHERE s.reference_id =:orderid and s.business_unit =:bunit and s.item_id =:itemid and s.packing_id =:packingid")
	void deleteSales_transactions(@Param("orderid") String orderid,@Param("bunit") String bunit,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
}
