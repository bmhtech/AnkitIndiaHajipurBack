package com.AnkitIndia.jwtauthentication.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_planning_shift_details;

public interface Production_planning_shift_detailsRepository extends JpaRepository<Production_planning_shift_details, Long>{

	@Query("select COUNT(id) from Production_planning_shift_details where business_unit =:business_unit and fromdate =:fromdate and todate =:todate")
	String countShifts(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.shop_floor =:shopfl and p.shift_used='No' and p.business_unit =:businessunit and p.shift > 0 ORDER BY p.shift")
	//@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.shop_floor =:shopfl and p.business_unit =:businessunit and p.shift > 0 ORDER BY p.shift")
	//@Query(value ="select * from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.shop_floor =:shopfl and p.business_unit =:businessunit ", nativeQuery = true)
	List<Production_planning_shift_details> getProdPlanShiftDtls(@Param("businessunit") String businessunit,@Param("shopfl") String shopfl);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.pp_shiftid =:pp_shiftid and p.todate =:tdate ")
	List<Production_planning_shift_details> getProdPlanPeriodicShiftDtls(@Param("pp_shiftid") String pp_shiftid,@Param("tdate") String tdate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.pp_shiftid =:pp_shiftid and p.fromdate =:tdate ")
	//@Query(value ="select * from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.pp_shiftid =:pp_shiftid and p.fromdate =:tdate ", nativeQuery = true)
	List<Production_planning_shift_details> getProdPlanDailyShiftDtls(@Param("pp_shiftid") String pp_shiftid,@Param("tdate") String tdate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.pp_shiftid =:shiftid")
	Production_planning_shift_details getProdPlanShiftDetails(@Param("shiftid") String shiftid);
	
	@Query( "select p from Production_planning_shift_details p where p.prod_plan_id =:prodid and p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl and p.fromdate =:startdate and p.todate =:enddate ORDER BY p.shift")
	List<Production_planning_shift_details> getProduction_planning_shift_details(@Param("prodid") String prodid,@Param("process") String process,@Param("shopfl") String shopfl,@Param("startdate") String startdate,@Param("enddate") String enddate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl and p.fromdate =:startdate and p.todate =:enddate ORDER BY p.shift")
	List<Production_planning_shift_details> getProduction_planning_shift_details(@Param("process") String process,@Param("shopfl") String shopfl,@Param("startdate") String startdate,@Param("enddate") String enddate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl and p.fromdate =:startdate ORDER BY p.shift")
	List<Production_planning_shift_details> getProduction_planning_shift_details(@Param("process") String process,@Param("shopfl") String shopfl,@Param("startdate") String startdate);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl")
	List<Production_planning_shift_details> getProduction_planning_shift_details(@Param("process") String process,@Param("shopfl") String shopfl);
	
	@Query( "select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl and function('date_format', p.fromdate, '%Y, %m, %d') >=  :sdate ")
	Production_planning_shift_details findShift( @Param("process") String process,@Param("shopfl") String shopfl,@Param("sdate") String sdate);
	
	//@Query("select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl AND :sdate BETWEEN DATE_FORMAT(STR_TO_DATE(p.fromdate,'%d-%m-%Y'),'%Y-%m-%d') AND DATE_FORMAT(STR_TO_DATE(p.todate,'%d-%m-%Y'),'%Y-%m-%d') ")
	@Query("select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.process =:process and p.shop_floor =:shopfl AND DATE_FORMAT(STR_TO_DATE(p.fromdate,'%d-%m-%Y'),'%Y-%m-%d') !=:sdate AND :sdate BETWEEN DATE_FORMAT(STR_TO_DATE(p.fromdate,'%d-%m-%Y'),'%Y-%m-%d') AND DATE_FORMAT(STR_TO_DATE(p.todate,'%d-%m-%Y'),'%Y-%m-%d') ")
	List<Production_planning_shift_details> findShifts( @Param("process") String process,@Param("shopfl") String shopfl,@Param("sdate") String sdate);
	
	//@Query("select DISTINCT(p.pp_shiftid) from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.process =:process and p.pp_shiftid =:shiftid ")
	@Query("select p from Production_planning_shift_details p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.process =:process and p.pp_shiftid =:shiftid ")
	Production_planning_shift_details getShiftDetails( @Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("process") String process,@Param("shiftid") String shiftid);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shift_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id ")
    int updateProduction_planning_shift_details(@Param("prod_plan_id") String prod_plan_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shift_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.shift='0' ")
    int updateProduction_planning_shift_details_static(@Param("prod_plan_id") String prod_plan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_planning_shift_details p SET p.modified_type ='UPDATED' WHERE p.prod_plan_id = :prod_plan_id and p.ppd_id =:ppd_id ")
    int updateProduction_planning_shift_details_static(@Param("prod_plan_id") String prod_plan_id,@Param("ppd_id") String ppd_id);
	
	 @Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Production_planning_shift_details pl where pl.modified_type = 'INSERTED' and  pl.fromdate=:fromdate ")
	 Boolean checkdate(@Param("fromdate") String fromdate);
	 
	 @Query(value="select * from production_planning_shift_details p where p.modified_type ='INSERTED' and  p.fromdate=:fromdate limit 1 ", 
			  nativeQuery = true)
	Production_planning_shift_details checkdateid(@Param("fromdate") String fromdate);
	 
	 @Modifying(clearAutomatically = true)
	    @Query("UPDATE Production_planning_shift_details b SET b.shift_used ='Yes' WHERE b.pp_shiftid = :pp_shiftid and b.modified_type ='INSERTED'")
	    int productionPlanShiftDetailsUpdate(@Param("pp_shiftid") String pp_shiftid);
	 

}
