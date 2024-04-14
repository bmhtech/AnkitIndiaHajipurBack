package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary;

public interface Sales_Order_SummaryRepository extends JpaRepository<Sales_Order_Summary, Long>{
	
	@Query( "select s from Sales_Order_Summary s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	Sales_Order_Summary getSalesOrdSummary(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Summary w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Summaryupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);

}
