package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Solar_power_generation;

public interface Solar_power_generationRepository extends JpaRepository<Solar_power_generation, Long>{
	
	@Query("select COUNT(id) from Solar_power_generation")
	String countId();
	
	@Query(value="select * from solar_power_generation where modified_type='INSERTED' and company_id=:compid ", nativeQuery=true)
	List<Map<String, Object>> getSolarPowerGeneration(@Param("compid") String compid);
	
	@Query(value="select * from solar_power_generation where modified_type='INSERTED' AND b_unit=:bunit AND solar_date>=:fromdate AND solar_date <=:todate ", nativeQuery=true)
	List<Map<String, Object>> getSolarPorGenReport(@Param("bunit") String bunit,@Param("fromdate") String frpmdate,@Param("todate") String todate);
	
	@Query(value="SELECT s.solar_date AS solar_date,s.remarks AS remarks,(s.no_one/s.total)*100 AS no_one,(s.no_two/s.total)*100 AS no_two,(s.no_three/s.total)*100 AS no_three,(s.no_four/s.total)*100 AS no_four,(s.no_five/s.total)*100 AS no_five,(s.no_six/s.total)*100 AS no_six,(s.no_seven/s.total)*100 AS no_seven,(s.no_eight/s.total)*100 AS no_eight,(s.no_nine/s.total)*100 AS no_nine,(s.no_ten/s.total)*100 AS no_ten,(s.no_eleven/s.total)*100 AS no_eleven FROM solar_power_generation s WHERE s.modified_type='INSERTED' AND s.b_unit=:bunit AND s.solar_date>=:fromdate AND s.solar_date <=:todate ", nativeQuery=true)
	List<Map<String, Object>> getInverterSolarPowerGeneration(@Param("bunit") String bunit,@Param("fromdate") String frpmdate,@Param("todate") String todate);
	
	@Query(value="SELECT total,remarks FROM solar_power_generation s WHERE s.modified_type='INSERTED' AND s.solar_date=:solar_date ", nativeQuery=true)
	List<Map<String, Object>> getAllSolarData(@Param("solar_date") String solar_date);
	
	@Query("select COUNT(id) from Solar_power_generation d where d.solar_date=:solar_date and d.modified_type='INSERTED'")
	int countdate(@Param("solar_date") String solar_date);
}
