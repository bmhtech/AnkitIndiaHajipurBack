package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Item_DtlsDTO;

public interface Sales_Order_Item_DtlsRepository extends JpaRepository<Sales_Order_Item_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Item_Dtls w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Item_Dtlsupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query( "select s from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.slno")
	List<Sales_Order_Item_Dtls> getSalesOrderItemDtls(@Param("order_id") String order_id);
	
	@Query( "select s from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id and s.item_code =:itemid and s.packing =:packingid ORDER BY s.slno")
	Sales_Order_Item_Dtls getSalesOrderItemDtls(@Param("order_id") String order_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Query("select s from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.slno")
	List<Sales_Order_Item_Dtls> getSalesOrdItemDtls(@Param("order_id") String order_id);
	
	
	@Query("select s from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.slno")
	List<Sales_Order_Item_Dtls> getSalesOrdItemDtlsRefraction(@Param("order_id") String order_id);
	
	@Query(value="SELECT s.order_id AS order_id,s.job_item AS job_item,s.job_packing AS job_packing,s.job_item_name AS job_item_name,s.job_packing_name AS job_packing_name,s.job_hsn AS job_hsn,(w.rest_item_qty/(SELECT i.capacity FROM item_master_pack_mat_tag i WHERE i.item_id =w.job_item AND i.item_code =w.job_packing AND i.modified_type = 'INSERTED')) AS pack_qty,s.pack_uom AS pack_uom,s.price_based_on AS price_based_on,w.rest_item_qty AS item_qty,s.item_uom AS item_uom,s.tolerance AS tolerance FROM sales_order_item_dtls_for_jobwork s,loading_sales_order_jobworks_w_tolerance w WHERE s.`modified_type`='INSERTED' AND s.order_id =:order_id  AND s.job_item = w.job_item AND s.job_packing = w.job_packing AND s.`order_id`=w.`order_id`",nativeQuery=true)
	List<Map<String,Object>> getSalesOrdItemDtls_for_jobwork(@Param("order_id") String order_id);
	
	@Query( "select (SUM(s.amount) + SUM(s.tax_amt) - SUM(s.discount_amt)) AS netamt from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	double getSalesOrdItemAmt(@Param("order_id") String order_id);
	
	@Query( "select sum(total_amt) AS total_amt from Sales_Order_Item_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	double getnumtowordsaleorder(@Param("order_id") String order_id);
	
	@Query(value= "SELECT i.item_code as item_id,i.item_name as item_name,i.price as price,i.price_based_on as price_based_on FROM sales_order_item_dtls i WHERE i.modified_type='INSERTED' AND i.order_id =:orderid", nativeQuery=true)
	List<Map<String, Object>> getSalesOrdItemDtlsnew(@Param("orderid") String orderid);
	
	@Query(value= "SELECT CASE WHEN COUNT(s.id)>0 THEN TRUE ELSE FALSE END FROM sales_order s, sales_order_item_dtls si WHERE s.id=:id AND s.order_id=si.order_id AND si.item_code=:itemid AND si.packing=:packingid", nativeQuery=true)
	long itemRateExist(@Param("id") long id,
						  @Param("itemid") String itemid,
						  @Param("packingid") String packingid);
	
}
