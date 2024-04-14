package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.model.Item_master;

public interface Item_Service_masterRepository extends JpaRepository<Item_Service_master, Long>{

	@Query("select COUNT(id) from Item_Service_master where company_id=:company")
	String countId(@Param("company") String company);

	@Query(value="select id,service_id,service_name,gst_code,gst_name,description,sac_code,ac_ledger_name from item_service_master where modified_type ='INSERTED' and company_id=:company",nativeQuery=true)
	List<Map<String, Object>> getItemServiceList(@Param("company") String company);
	
	@Query("select i.service_name from Item_Service_master i where i.modified_type != 'DELETED' and i.service_name =:cname ")
	String chkItemServiceNameStatic(@Param("cname") String cname);
	
	@Query(value= "SELECT d.gst_code FROM item_service_master d WHERE d.modified_type='INSERTED' AND d.service_id=:name", nativeQuery=true)
	String getTaxCode(@Param("name") String name);
	
	@Query( "select i from Item_Service_master i where i.service_id = :code and i.modified_type = 'INSERTED'")
	Item_Service_master serviceNameById(@Param("code") String code);
	
}
