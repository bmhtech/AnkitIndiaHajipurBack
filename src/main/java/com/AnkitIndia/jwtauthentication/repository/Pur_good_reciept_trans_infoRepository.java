package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_reciept_trans_info;

public interface Pur_good_reciept_trans_infoRepository extends JpaRepository<Pur_good_reciept_trans_info, Long>{

	@Query( "select p from Pur_good_reciept_trans_info p where p.grn_id = :code and p.modified_type ='INSERTED'" )
	Pur_good_reciept_trans_info grnTransInfoRetriveList(@Param("code") String code);
	
	@Query(value= "SELECT p.* FROM pur_good_reciept_trans_info p WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED'",nativeQuery = true)
	Map<String, Object> grnTransInfoRetriveListFast(@Param("grnid") String grnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_reciept_trans_info w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_good_reciept_trans_info(@Param("grn_id") String grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_reciept_trans_info w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_good_reciept_trans_infoUpdate(@Param("grn_id") String grn_id);
}
