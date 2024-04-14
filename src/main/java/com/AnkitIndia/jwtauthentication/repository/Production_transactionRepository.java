package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_transaction;

public interface Production_transactionRepository extends JpaRepository<Production_transaction, Long>{
	
	@Query("select max(substring(prod_trans_code , 15, length(prod_trans_code))) from Production_transaction where business_unit =:businessunit and shop_floor =:sfloor and prod_trans_code like %:code% and company_id =:company ")
	String getPTSequenceId(@Param("businessunit") String businessunit,@Param("sfloor") String sfloor,@Param("code") String code,@Param("company") String company);
	
	@Query("select COUNT(id) from Production_transaction")
	String countId();
	
	@Query("select p from Production_transaction p where p.modified_type ='INSERTED' ORDER BY p.prod_trans_id DESC")
	List<Production_transaction> findAllProTrans();
	
	@Query(value="SELECT * FROM production_transaction p WHERE p.modified_type ='INSERTED' AND p.prod_shift_date=:cdate ORDER BY p.prod_trans_id DESC",nativeQuery=true)
	List<Map<String, Object>> findAllProdTransfast(@Param("cdate") String cdate);
	
	@Query(value="select * from storeconsuptiontransaction where issueto=:sfloor",nativeQuery=true)
	List<Map<String, Object>> getstoreconsumptiontransaction(@Param("sfloor") String sfloor);
	
	@Query(value="SELECT p.id AS id, p.prod_trans_id AS prod_trans_id, p.prod_trans_date AS prod_trans_date, p.business_unitname AS business_unitname, p.shop_floorname AS shop_floorname, p.prod_process AS prod_process FROM production_transaction p WHERE p.`business_unit`=:business_unit1 AND p.shop_floor IN (:shop_floor1) AND p.`prod_trans_date`>=:fromdate AND p.`prod_trans_date`<=:todate",nativeQuery=true)
	List<Map<String, Object>> getsearchdata(@Param("business_unit1") String business_unit1,
			@Param("shop_floor1") List<String> shop_floor1,@Param("fromdate") String fromdate,@Param("todate") String todate);

	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Production_transaction p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
}
