package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning_special_date_details;

public interface Production_planning_special_date_detailsRepository extends JpaRepository<Production_planning_special_date_details, Long>{

	@Query( "select p from Production_planning_special_date_details_static p where p.prod_plan_id =:prod_plan_id and p.modified_type ='INSERTED' and p.ppsplid =:proddateid ORDER BY p.sl_no")
	List<Production_planning_special_date_details> getProduction_planning_special_date_details(@Param("prod_plan_id") String prod_plan_id,@Param("proddateid") String proddateid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_date_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id ")
    int updateProduction_planning_special_date_details(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_date_details_static p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.fromdate='' ")
    int updateProduction_planning_special_date_details_static(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_special_date_details_static p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.ppsplid =:ppsid ")
    int updateProduction_planning_special_date_details_static(@Param("prod_plan_id") String prod_plan_id,@Param("ppsid") String ppsid);
	
}
