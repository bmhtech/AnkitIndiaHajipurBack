package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl_input_details;

public interface Production_transaction_spl_input_detailsRepository extends JpaRepository<Production_transaction_spl_input_details, Long>{
	
	@Query( "select b from Production_transaction_spl_input_details b where b.prod_trans_id =:prod_trans_id and b.modified_type ='INSERTED' ORDER BY b.sl_no")
	List<Production_transaction_spl_input_details> getProdTranInputDetails(@Param("prod_trans_id") String prod_trans_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_transaction_spl_input_details b SET b.modified_type ='UPDATED' WHERE b.prod_trans_id = :prod_trans_id")
    int updateProduction_transaction_spl_input_details(@Param("prod_trans_id") String prod_trans_id);
	
	@Query(value = "SELECT a.prod_trans_date AS prod_trans_date ,a.prod_trans_id AS prod_trans_id ,a.shop_floor AS shop_floor ,a.shop_floorname AS shop_floorname ,b.item AS item,c.item_name AS item_name ,SUM(b.item_qty) AS item_qty,SUM(b.packing_qty) AS packing_qty FROM production_transaction_spl a,production_transaction_spl_input_details b,item_master c WHERE a.prod_trans_id=b.prod_trans_id AND b.item=c.item_id AND a.modified_type='INSERTED' AND b.modified_type='INSERTED' AND a.prod_trans_date>=:fromdate AND a.prod_trans_date<=:todate AND a.shop_floor=:shop_floor GROUP BY b.item ORDER BY b.sl_no", nativeQuery=true)
	List<Map<String, Object>> getspecialProdInputReport(@Param("shop_floor") String shop_floor,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	
}
