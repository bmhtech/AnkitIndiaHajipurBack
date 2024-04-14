package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Custom_uom_master;

public interface CustomUomMasterRepository extends JpaRepository<Custom_uom_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(customuom_id , 4 , length(customuom_id))) FROM Custom_uom_master where customuom_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select c from Custom_uom_master c where c.modified_type != 'DELETED'")
	List<Custom_uom_master> getCustomUOMNCList();
	
	@Query("select c from Custom_uom_master c where c.customuom_id= :code ")
	Custom_uom_master getUomByIGroup(@Param("code") String code);
	
	@Query("select c from Custom_uom_master c where c.customuom_id= :cuomid ")
	Custom_uom_master getCustomUomById(@Param("cuomid") String cuomid);
	
	@Query("select c from Custom_uom_master c where c.modified_type != 'DELETED' and c.company_id =:company ")
	List<Custom_uom_master> getStandardCustomUOM(@Param("company") String company);
	//Delete
	@Query("select c from Custom_uom_master c where c.modified_type != 'DELETED' ")
	List<Custom_uom_master> getStandardCustomUOM();
	
	@Query("select c from Custom_uom_master c where c.modified_type != 'DELETED' and c.customuom_catg ='WUOM' ")
	List<Custom_uom_master> getWeighmentCustomUOM();

	@Query("select max(substring(customuom_code , 8, length(customuom_code))) from Custom_uom_master where customuom_code like %:code% and company_id =:company ")
	String getCustomUOMSequenceId(@Param("code") String code,@Param("company") String company);

	@Query("select c from Custom_uom_master c where c.modified_type != 'DELETED' and c.customuom_catg =:uomtype")
	List<Custom_uom_master> getCustomUOMs(@Param("uomtype") String uomtype);
	
	@Query(value="select * from custom_uom_master c where c.modified_type != 'DELETED'", nativeQuery = true)
	List<Map<String,Object>> getUomList();
	
	@Query("select c from Custom_uom_master c where c.modified_type = 'INSERTED' and c.description =:uomname")
	Custom_uom_master getname(@Param("uomname") String uomname);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Custom_uom_master p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
}
