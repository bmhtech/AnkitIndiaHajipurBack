package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Servicemaster;

public interface ServicemasterRepository extends JpaRepository<Servicemaster, Long>{
	
	@Query("Select count(id) from Servicemaster")
	String countservicemaster();
	

	@Query(value="select id, service_no, service_acc_code, service_type, service_item_type from servicemaster where modified_type='INSERTED' and fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getServiceMasterList(@Param("finyear") String finyear);
	
	@Query(value="select * from service_masterdtls where service_no = :code and modified_type = 'INSERTED' ", nativeQuery = true)
	List<Map<String, Object>> getServiceMasterdtlsList(@Param("code") String code);

	@Query(value= "select service_no,description from servicemaster  where modified_type = 'INSERTED' and service_subtype =:servicetype",nativeQuery=true)
	List<Map<String, Object>> getServiceList(@Param("servicetype") String servicetype);
	
	@Query(value= "select service_no,service_name from service_masterdtls  where modified_type = 'INSERTED' and service_no =:serviceno",nativeQuery=true)
	List<Map<String, Object>> getServiceDtlsList(@Param("serviceno") String serviceno);
	
	@Query(value= "select service_acc_code from servicemaster  where modified_type = 'INSERTED' and service_no =:serviceno",nativeQuery=true)
	Map<String, Object> getDescCode(@Param("serviceno") String serviceno);
	
	@Query( "select s from Servicemaster s where s.service_no = :service_no and s.modified_type = 'INSERTED'")
	Servicemaster getDescNameByCode(@Param("service_no") String service_no);

	@Query(value = "{call checkServiceMasterUsage(:service_no)}", nativeQuery = true)
	String checkServiceMasterUsage(@Param("service_no") String service_no);
	
}
