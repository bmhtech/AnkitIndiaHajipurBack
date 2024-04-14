package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;


public interface OtherItemMasterRepository extends JpaRepository<OtherItemMaster, Long>{
	
	@Query("select COUNT(id) from OtherItemMaster")
	String countId();
	
	@Query(value="select * from other_item_master g where g.modified_type='INSERTED' and g.company_id =:company_id",nativeQuery=true)
	List<Map<String,Object>> getOtherItemList(@Param("company_id") String company_id);
	
	@Query(value="select noitemid,noitemname from other_item_master g where g.modified_type='INSERTED' and g.company_id =:company_id",nativeQuery=true)
	List<Map<String,Object>> getOtherItemMasterList(@Param("company_id") String company_id);
	
	@Query( "select b from OtherItemMaster b where b.noitemid = :Id and b.modified_type = 'INSERTED'")
	OtherItemMaster otherItemName(@Param("Id") String Id);
	
}
