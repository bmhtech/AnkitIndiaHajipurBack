package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Item_Dtls;

public interface Stock_Transfer_Item_DtlsRepository extends JpaRepository<Stock_Transfer_Item_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Item_Dtls s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_Transfer_Item_Dtls(@Param("order_id") String order_id);

	@Query("select p from Stock_Transfer_Item_Dtls p where p.modified_type = 'INSERTED' and p.order_id = :order_id ")
	List<Stock_Transfer_Item_Dtls> getStockTransItemDlts(@Param("order_id") String order_id);
}
