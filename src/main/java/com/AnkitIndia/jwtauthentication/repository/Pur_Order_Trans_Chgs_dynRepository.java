package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Chgs_dyn;

public interface Pur_Order_Trans_Chgs_dynRepository extends JpaRepository<Pur_Order_Trans_Chgs_dyn, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Trans_Chgs_dyn w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Trans_Chgs_dynupdate(@Param("pur_orderid") String pur_orderid);
	
	@Query(value="select * from pur_order_trans_chgs_dyn p where p.pur_orderid =:pur_order_no and p.modified_type = 'INSERTED' ORDER BY p.slno", nativeQuery=true)
	List<Map<String,Object>> getPurOrderTransChgsdynList(@Param("pur_order_no") String pur_order_no);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Trans_Chgs_dyn w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int purOrderTransChgsdynUpdate(@Param("pur_orderid") String pur_orderid);
	
	@Query(value="select t.transporter_name as bp_Id,CASE WHEN (SELECT bp_name FROM trans_bussiness_partner WHERE bp_id=t.transporter_name) IS NULL THEN '' ELSE (SELECT bp_name FROM trans_bussiness_partner WHERE bp_id=t.transporter_name) END AS bp_name from pur_order_trans_chgs_dyn t where t.pur_orderid =:order_id and t.modified_type = 'INSERTED'", nativeQuery=true)
	List<Map<String,Object>> transporterNameChgsPurList(@Param("order_id") String order_id);
	
	
	@Query("Select  t from Pur_Order_Trans_Chgs_dyn t WHERE t.modified_type = 'INSERTED'  and t.transporter_name=:transporter_Id and t.pur_orderid=:referance_id")
	Pur_Order_Trans_Chgs_dyn getpurchaseordercharges(@Param("transporter_Id") String transporter_Id,@Param("referance_id") String referance_id);
}
