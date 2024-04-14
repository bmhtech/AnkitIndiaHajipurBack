package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;

public interface Production_planning_periodic_date_detailsRepository extends JpaRepository<Production_planning_periodic_date_details, Long>{
	
	@Query( "select p from Production_planning_periodic_date_details p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED' and p.ppd_id =:proddateid ORDER BY p.sl_no")
	List<Production_planning_periodic_date_details> getProduction_planning_periodic_date_details(@Param("prod_plan_id") String prod_plan_id,@Param("proddateid") String proddateid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_periodic_date_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id ")
    int updateProduction_planning_periodic_date_details(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_periodic_date_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id =:prod_plan_id and p.fromdate ='00-00-0000' and p.todate ='00-00-0000' and p.shift1 ='' ")
    int updateProduction_planning_periodic_date_details_static(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_periodic_date_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id =:prod_plan_id and p.ppd_id =:ppdid ")
    int updateProduction_planning_periodic_date_details_static(@Param("prod_plan_id") String prod_plan_id,@Param("ppdid") String ppdid);
	
	@Query( "select p from Production_planning_periodic_date_details p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED' and p.ppd_id =:proddateid ORDER BY p.sl_no")
	List<Production_planning_periodic_date_details> getProdPlanPerDateDtlsShiftNo(@Param("prod_plan_id") String prod_plan_id,@Param("proddateid") String proddateid);
	
	@Query( "select p from Production_planning_periodic_date_details p where p.ppds_id =:ppds_id and p.modified_type ='INSERTED'")
	Production_planning_periodic_date_details getshiftNoDetails(@Param("ppds_id") String ppds_id);
	

	@Query( "select p from Production_planning_periodic_date_details p where p.ppds_id =:ppds_id and p.ppd_id =:ppd_id and  p.modified_type ='INSERTED'")
	Production_planning_periodic_date_details getshiftNoDetailsnew(@Param("ppds_id") String ppds_id,@Param("ppd_id") String ppd_id);
	
}
