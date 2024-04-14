package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning;

public interface Production_planningRepository extends JpaRepository<Production_planning, Long>{
	
	@Query("select max(substring(prod_plan_code , 11, length(prod_plan_code))) from Production_planning where prod_plan_code like %:code% and business_unit=:bunit and company_id =:company ")
	String getPPSequenceId(@Param("code") String code,@Param("bunit") String bunit,@Param("company") String company);
	
	@Query("select COUNT(id) from Production_planning")
	String countId();
	
	@Query("select p from Production_planning p where p.modified_type ='INSERTED' ORDER BY p.prod_plan_id DESC")
	List<Production_planning> findAllProdPlanning();
	
	@Query("select p from Production_planning p where p.modified_type ='INSERTED' and p.prod_plan_id =:prod_plan_id")
	Production_planning findByProdPlanId(@Param("prod_plan_id") String prod_plan_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Production_planning_shift_details pl where pl.modified_type = 'INSERTED' and pl.production=:production")
	Boolean checkBom(@Param("production") String production);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Production_planning_shift_details pl where pl.modified_type = 'INSERTED' and pl.prod_plan_id=:production and pl.shift_used='Yes'")
	Boolean checkPlanShift(@Param("production") String production);
	
	@Query(value = "SELECT a.prod_trans_date AS prod_trans_date ,a.prod_trans_id AS prod_trans_id ,a.shop_floor AS shop_floor ,a.shop_floorname AS shop_floorname ,b.item AS item,c.item_name AS item_name ,SUM(b.item_qty) AS item_qty,SUM(b.packing_qty) AS packing_qty FROM production_transaction a, production_transaction_input_details b,item_master c WHERE a.prod_trans_id=b.prod_trans_id AND b.item=c.item_id AND a.modified_type='INSERTED' AND b.modified_type='INSERTED' AND a.prod_trans_date>=:fromdate AND a.prod_trans_date<=:todate AND a.shop_floor=:shop_floor GROUP BY b.item ORDER BY b.sl_no", nativeQuery=true)
	List<Map<String, Object>> getProdInputReport(@Param("shop_floor") String shop_floor,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "SELECT a.prod_trans_date AS prod_trans_date ,a.prod_trans_id AS prod_trans_id ,a.shop_floor AS shop_floor ,a.shop_floorname AS shop_floorname ,b.item AS item,c.item_name AS item_name ,SUM(b.item_qty) AS item_qty,SUM(b.packing_qty) AS packing_qty FROM production_transaction a, production_transaction_output_details b,item_master c WHERE a.prod_trans_id=b.prod_trans_id AND b.item=c.item_id AND a.modified_type='INSERTED' AND b.modified_type='INSERTED' AND a.prod_trans_date>=:fromdate AND a.prod_trans_date<=:todate AND a.shop_floor=:shop_floor GROUP BY b.item ORDER BY b.sl_no", nativeQuery=true)
	List<Map<String, Object>> getProdOutputReport(@Param("shop_floor") String shop_floor,@Param("fromdate") String fromdate,@Param("todate") String todate);
}
