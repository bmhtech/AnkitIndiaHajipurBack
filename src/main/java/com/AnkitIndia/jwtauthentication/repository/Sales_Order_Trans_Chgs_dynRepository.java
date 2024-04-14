package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Chgs_dyn;

public interface Sales_Order_Trans_Chgs_dynRepository extends JpaRepository<Sales_Order_Trans_Chgs_dyn, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Trans_Chgs_dyn w SET w.modified_type ='UPDATED' WHERE w.order_id = :order_id")
    int sales_Order_Trans_Chgs_dynupdate(@Param("order_id") String order_id);
	
	@Query(value="select * from sales_order_trans_chgs_dyn p where p.order_id =:order_id and p.modified_type = 'INSERTED' ORDER BY p.slno", nativeQuery=true)
	List<Map<String,Object>> getSalesOrdTransChgsDynList(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Trans_Chgs_dyn w SET w.modified_type ='DELETED' WHERE w.order_id = :order_id")
    int salesOrderTransChgsdynUpdate(@Param("order_id") String order_id);
	
	@Query(value="select t.transporter_name as bp_Id,CASE WHEN (SELECT bp_name FROM trans_bussiness_partner WHERE bp_id=t.transporter_name) IS NULL THEN '' ELSE (SELECT bp_name FROM trans_bussiness_partner WHERE bp_id=t.transporter_name) END AS bp_name from sales_order_trans_chgs_dyn t where t.order_id =:order_id and t.modified_type = 'INSERTED'", nativeQuery=true)
	List<Map<String,Object>> transporterNameChgsList(@Param("order_id") String order_id);
	
	
	@Query("Select  t from Sales_Order_Trans_Chgs_dyn t WHERE t.modified_type = 'INSERTED'  and t.transporter_name=:transporter_Id and t.order_id=:referance_id ")
	Sales_Order_Trans_Chgs_dyn getsaleordercharges(@Param("transporter_Id") String transporter_Id,@Param("referance_id") String referance_id);
	
	  
	@Query(value="Select  * from sales_order_trans_chgs_dyn t WHERE t.modified_type = 'INSERTED'  and t.charge_code=:chargeid and t.order_id=:referance_id ", nativeQuery=true)
	List<Map<String,Object>> gettransdetails(@Param("referance_id") String referance_id,@Param("chargeid") String chargeid);
	
	
}
