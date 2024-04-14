package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Info;

public interface Sales_Order_Trans_InfoRepository extends JpaRepository<Sales_Order_Trans_Info, Long> {
	
	@Query( "select s from Sales_Order_Trans_Info s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	Sales_Order_Trans_Info getSalesOrdTransInfo(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Trans_Info w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int Sales_Order_Trans_Infoupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);

}
