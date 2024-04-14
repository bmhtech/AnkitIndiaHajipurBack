package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Docs;

public interface Sales_Order_DocsRepository extends JpaRepository<Sales_Order_Docs, Long> {
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Docs w SET w.modified_type =:mstatus WHERE w.order_id =:order_id")
    int updateSales_Order_Docs(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query("select s from Sales_Order_Docs s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.id")
	List<Sales_Order_Docs> getSalesOrdDocs(@Param("order_id") String order_id);

}
