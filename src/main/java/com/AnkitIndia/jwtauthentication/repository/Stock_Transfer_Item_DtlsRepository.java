package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

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
	
	@Query(value="SELECT p.*,str.rest_bag AS st_rest_bag,str.rest_wt AS st_rest_wt,str.stock_item_status AS sti_status FROM stock_transfer_item_dtls p LEFT JOIN stock_grn_stock_transfer_rest_wt str ON str.order_id=p.order_id AND p.item_code=str.item_code AND p.packing=str.packing WHERE p.modified_type = 'INSERTED' AND p.order_id =:order_id AND str.stock_item_status='Open' GROUP BY p.order_id,p.item_code,p.packing ", nativeQuery=true)
	List<Map<String, Object>> getStockTransItemDltsArmy(@Param("order_id") String order_id);
	
	@Query(value="select * from stock_transfer_item_dtls s where s.modified_type = 'INSERTED' and s.order_id = :order_id", nativeQuery = true)
	List<Map<String, Object>> getStkTransOrderItemDlts(@Param("order_id") String order_id);
}
