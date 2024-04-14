package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;
import com.AnkitIndia.jwtauthentication.model.OtherPartyMaster;

public interface OtherPartyMasterRepository extends JpaRepository<OtherPartyMaster, Long>{
	
	@Query("select COUNT(id) from OtherPartyMaster")
	String countId();
	
	@Query(value="select * from other_party_master g where g.modified_type='INSERTED' and g.company_id =:company_id",nativeQuery=true)
	List<Map<String,Object>> getOtherPartyList(@Param("company_id") String company_id);
	
	@Query(value="select nopartyid,nopartyname from other_party_master g where g.modified_type='INSERTED' and g.company_id =:company_id",nativeQuery=true)
	List<Map<String,Object>> getOtherPartyMasterList(@Param("company_id") String company_id);
	
	@Query( "select b from OtherPartyMaster b where b.nopartyid = :Id and b.modified_type = 'INSERTED'")
	OtherPartyMaster otherPartyName(@Param("Id") String Id);
	
}
