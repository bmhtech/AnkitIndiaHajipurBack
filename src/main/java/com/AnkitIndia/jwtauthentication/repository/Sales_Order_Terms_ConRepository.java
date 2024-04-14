package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Terms_Con;

public interface Sales_Order_Terms_ConRepository extends JpaRepository<Sales_Order_Terms_Con, Long> {
	
	@Query( "select s from Sales_Order_Terms_Con s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	Sales_Order_Terms_Con getSalesOrdTermsCon(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Terms_Con w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Terms_Conupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query( value="select payment_mode from sales_order_terms_con s where s.modified_type = 'INSERTED' and s.order_id =:order_id ",nativeQuery=true)
	String getlastorder(@Param("order_id") String order_id);

}
