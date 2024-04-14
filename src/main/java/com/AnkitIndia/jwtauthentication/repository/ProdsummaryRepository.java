package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Prodsummary;

public interface ProdsummaryRepository extends JpaRepository<Prodsummary, Long>
{
	@Query("Select count(id) from Prodsummary")
	String countProd_summary();
	
	
	@Query(value="SELECT  d.`item`,d.`item_name`,d.`packing`,d.`packing_name`,d.`uom_basedon`,m.`capacity`,m.`empty_big_wt`,m.`empbagwt_based_on`,d.packing_qty,d.`production_qty`,'2500' AS rate,(d.`production_qty`*2500) AS amount,d.item_uom AS production_uom,d.packing_uom AS packing_uom FROM  production_transaction p,production_transaction_output_details d,`item_master` i,`item_master_pack_mat_tag` m WHERE p.modified_type='INSERTED' AND p.`modified_type`=d.`modified_type` AND p.`prod_trans_id`=d.`prod_trans_id` AND p.`prod_trans_date`=:proddate AND i.`modified_type`=p.`modified_type` AND m.`item_id`= d.`item` AND m.`item_code`=d.`packing` AND m.`modified_type`=p.`modified_type` AND i.`item_id`=d.`item` AND (i.sales_item='1' OR i.purchase_item='1') AND p.shop_floor!='SF00005'",nativeQuery=true)
	List<Map<String,Object>> productionregoutput(@Param("proddate") String proddate);
	
	@Query(value="SELECT  d.`item`,i.`item_name`,d.`packing`,m.`item_name` as packing_name,d.`uom_basedon`,m.`capacity`,m.`empty_big_wt`,m.`empbagwt_based_on`,d.packing_qty,d.`production_qty`,'2500' AS rate,(d.`production_qty`*2500) AS amount FROM `production_transaction_spl`p,`production_transaction_spl_output_details` d,`item_master` i,`item_master_pack_mat_tag` m WHERE p.modified_type='INSERTED' AND p.`modified_type`=d.`modified_type` AND p.`prod_trans_id`=d.`prod_trans_id` AND p.`prod_trans_date`=:proddate AND i.`modified_type`=p.`modified_type` AND m.`item_id`= d.`item` AND m.`item_code`=d.`packing` AND m.`modified_type`=p.`modified_type` AND i.`item_id`=d.`item` AND (i.sales_item='1' OR i.purchase_item='1')",nativeQuery=true)
	List<Map<String,Object>> productionspecialoutput(@Param("proddate") String proddate);
	
	@Query(value="SELECT id,prod_sum_id,date,export FROM  prodsummary WHERE modified_type='INSERTED' ORDER BY id",nativeQuery=true)
	List<Map<String,Object>> getProductionSummurylist();
	
	@Query(value="select case when count(id)>0 then 'Yes' else 'No' end as status from prodsummary WHERE modified_type='INSERTED' and  date=:proddate",nativeQuery=true)
	String Productionsummarydatecheck(@Param("proddate") String proddate);
	
	@Query("SELECT i FROM Prodsummary i where i.id =:id and i.modified_type='INSERTED'")
	Prodsummary getSummaryDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Prodsummary p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Prodsummary p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int prodSummaryPostingUndo(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
}
