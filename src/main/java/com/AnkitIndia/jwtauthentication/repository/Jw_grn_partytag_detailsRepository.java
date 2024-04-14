package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Jw_grn_partytag_details;

public interface Jw_grn_partytag_detailsRepository extends JpaRepository<Jw_grn_partytag_details, Long>{

	@Query(value="select * from jw_grn_partytag_details where modified_type='INSERTED' AND jw_grn_tag=:jw_grn_tag order by slno", nativeQuery=true)
	List<Map<String, Object>> getJwGrnPartytagDetails(@Param("jw_grn_tag") String jw_grn_tag);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Jw_grn_partytag_details w SET w.modified_type =:mstatus WHERE w.jw_grn_tag = :jw_grn_tag AND w.modified_type ='INSERTED'")
    int updateJwGrnPartytagDetails(@Param("jw_grn_tag") String jw_grn_tag,@Param("mstatus") String mstatus);
}
