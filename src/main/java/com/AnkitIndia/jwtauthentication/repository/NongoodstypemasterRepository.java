package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Nongoodstypemaster;

public interface NongoodstypemasterRepository extends JpaRepository<Nongoodstypemaster, Long>{
	
	@Query("Select count(id) from Nongoodstypemaster")
	String counttype();
	
	@Query(value="select id, business_unitname, typeserviceno, typename from nongoodstypemaster where modified_type='INSERTED' and fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getNonGoodsTypeMaster(@Param("finyear") String finyear);
	
	@Query(value="select typeserviceno,typename from nongoodstypemaster where modified_type='INSERTED' ", nativeQuery=true)
	List<Map<String, Object>> getServiceTypeList();
	
	@Query( "select n from Nongoodstypemaster n where n.typeserviceno = :typeserviceno and n.modified_type = 'INSERTED'")
	Nongoodstypemaster getNonGoodsServiceTypeName(@Param("typeserviceno") String typeserviceno);
}
