package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning_shop_floor_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls;
import com.AnkitIndia.jwtauthentication.model.Production_planning_special_dtls_static;

public interface Production_planning_special_dtlsRepository extends JpaRepository<Production_planning_special_dtls, Long>{
	
	@Query( "select p from Production_planning_special_dtls_static p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED'  ORDER BY p.sl_no")
	List<Production_planning_special_dtls> getProduction_planning_special_dtls(@Param("prod_plan_id") String prod_plan_id);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_dtls p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id")
    int updateProduction_planning_special_dtls(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_dtls_static p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.process='' and p.production='' and p.shop_floor='' ")
    int updateProduction_planning_special_dtls_static(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_dtls_static p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.pps_id =:ppsid ")
    int updateProduction_planning_special_dtls_static(@Param("prod_plan_id") String prod_plan_id,@Param("ppsid") String ppsid);
	
	@Query( "select p from Production_planning_special_dtls_static p where p.business_unit =:bunit and p.shop_floor =:sfloor and p.modified_type ='INSERTED' and p.company_id =:company")
	List<Production_planning_special_dtls> getProdPlanSplDtls(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query(value="select * from production_planning_special_dtls_static p where p.business_unit =:bunit and p.shop_floor =:sfloor and p.modified_type ='INSERTED' and p.pps_id =:process and p.company_id =:company",nativeQuery=true)
	Map<String,Object> getProdPlanSplProcessDtls(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("process") String process,@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_dtls_static p SET p.modified_type ='DELETED' WHERE p.prod_plan_id = :prod_plan_id")
    int delete(@Param("prod_plan_id") String prod_plan_id);
	
}
