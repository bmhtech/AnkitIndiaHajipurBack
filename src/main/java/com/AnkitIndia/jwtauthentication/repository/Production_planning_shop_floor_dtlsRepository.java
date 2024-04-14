package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls;

public interface Production_planning_shop_floor_dtlsRepository extends JpaRepository<Production_planning_shop_floor_dtls, Long>{
	
	@Query( "select p from Production_planning_shop_floor_dtls p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED' ORDER BY p.sl_no")
	List<Production_planning_shop_floor_dtls> getProduction_planning_shop_floor_dtls(@Param("prod_plan_id") String prod_plan_id);
	
	@Query( "select p from Production_planning_shop_floor_dtls p where p.business_unit =:bunit and p.shop_floor =:sfloor and p.process_date like %:pdate% and p.modified_type ='INSERTED' and p.company_id =:company")
	List<Production_planning_shop_floor_dtls> getProcessThruProPlan(@Param("pdate") String pdate,@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query("select p from Production_planning_shop_floor_dtls p where p.business_unit =:bunit and p.shop_floor =:sfloor and p.process =:process and p.process_date like %:pdate% and p.modified_type ='INSERTED' and p.company_id =:company")
	Production_planning_shop_floor_dtls getProcessThruProdPlan(@Param("pdate") String pdate,@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("process") String process,@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shop_floor_dtls p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id")
    int updateProduction_planning_shop_floor_dtls(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shop_floor_dtls p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.process ='' and p.production= '' and p.shop_floor='' ")
    int updateProduction_planning_shop_floor_dtls_static(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shop_floor_dtls p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id =:prod_plan_id and p.sl_no =:slno ")
    int updateProduction_planning_shop_floor_dtls_static(@Param("prod_plan_id") String prod_plan_id,@Param("slno") int slno);
	
	@Query("select p from Production_planning_shop_floor_dtls p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED' and p.sl_no =:slno")
	Production_planning_shop_floor_dtls getProduction_planning_regular_dtls(@Param("prod_plan_id") String prod_plan_id,@Param("slno") int slno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shop_floor_dtls p SET p.modified_type ='DELETED' WHERE p.prod_plan_id = :prod_plan_id")
    int delete(@Param("prod_plan_id") String prod_plan_id);
	
}
