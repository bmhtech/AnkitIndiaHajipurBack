package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_time_service;

import java.util.List;
import java.util.Map;


public interface NongoodssrnRepository extends JpaRepository<Nongoodssrn, Long>{

	@Query("select COUNT(id) from Nongoodssrn where fin_year=:finyear")
	String countId(@Param("finyear") String finyear);
	
	@Query("select COUNT(id) from Nongoodssrn")
	String genId();
	
	@Query(value="select id,srnid,b_unitname,ordertype,party_name,srndate,b_unit from nongoodssrn where fin_year=:finyear and modified_type='INSERTED' ORDER BY id DESC",nativeQuery=true)
	List<Map<String,Object>> getSRNlist(@Param("finyear") String finyear);
	
	@Query( "select n from Nongoodssrn_time_service n where n.modified_type = 'INSERTED' AND n.srnid = :srnid ORDER BY slno ASC")
	List<Nongoodssrn_time_service> retriveNongoodsSrnTime(@Param("srnid") String srnid);
	
	@Query( "select n from Nongoodssrn_docs n where n.modified_type = 'INSERTED' AND n.srnid = :srnid")
	List<Nongoodssrn_docs> getDocDetails(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_time_service n SET n.modified_type ='UPDATED' WHERE n.srnid = :srnid")
    int updateTimeDetails(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_docs n SET n.modified_type ='UPDATED' WHERE n.srnid = :srnid")
    int updateDoc(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_time_service n SET n.modified_type ='DELETED' WHERE n.srnid =:srnid and n.modified_type='INSERTED'")
    int revertTimeDetails(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_docs n SET n.modified_type ='DELETED' WHERE n.srnid =:srnid and n.modified_type='INSERTED'")
    int revertDocuments(@Param("srnid") String srnid);
}
