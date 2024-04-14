package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Solar_power_generation_with_powercut;

public interface Solar_power_generation_with_powercutRepository extends JpaRepository<Solar_power_generation_with_powercut, Long>{
	
	@Query("select COUNT(id) from Solar_power_generation_with_powercut")
	String countId();
	
	@Query(value="select * from solar_power_generation_with_powercut where modified_type='INSERTED' and company_id=:compid ", nativeQuery=true)
	List<Map<String, Object>> solarPowerGenerationWithPowerCutList(@Param("compid") String compid);
	
	@Query(value="select * from solar_power_generation_with_powercut_dtls where modified_type='INSERTED' and solar_powercut_id=:powerid order by slno", nativeQuery=true)
	List<Map<String, Object>> retriveSolarPowercutDetails(@Param("powerid") String powerid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Solar_power_generation_with_powercut_dtls u SET u.modified_type ='UPDATED' WHERE u.solar_powercut_id = :solar_powercut_id and u.modified_type ='INSERTED'")
	int updateSolarPwrCutDtls(@Param("solar_powercut_id") String solar_powercut_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Solar_power_generation_with_powercut_dtls d SET d.modified_type ='DELETED' WHERE d.solar_powercut_id =:solar_powercut_id  and d.modified_type='INSERTED'")
    int deleteSolarPowerCut(@Param("solar_powercut_id") String solar_powercut_id);
	
	@Query(value="SELECT s.solar_date AS solar_date,IF(@lastid1 = s.generation,'', @lastid1 \\:= s.generation) AS generation,d.from_time as from_time,d.to_time as to_time,d.total_time as total_time,d.power_triping as power_triping,IF(@lastid2 = s.weather_condition,'', @lastid2 \\:= s.weather_condition) AS weather_condition FROM solar_power_generation_with_powercut s,solar_power_generation_with_powercut_dtls d WHERE s.modified_type='INSERTED' AND d.modified_type='INSERTED' AND s.solar_powercut_id=d.solar_powercut_id AND s.solar_date>=:fromdate AND s.solar_date <=:todate and s.b_unit=:bunit order by s.id,d.slno", nativeQuery=true)
	List<Map<String, Object>> getSolarPowerWithPowerCutList(@Param("bunit") String bunit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query("select COUNT(id) from Solar_power_generation_with_powercut d where d.solar_date=:solar_date and d.modified_type='INSERTED'")
	int countdate(@Param("solar_date") String solar_date);
	
}
