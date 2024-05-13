package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_doc;

public interface Pur_good_receipt_docRepository extends JpaRepository<Pur_good_receipt_doc, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_doc w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_good_receipt_doc(@Param("grn_id") String grn_id);
	
	@Query( "select p from Pur_good_receipt_doc p where p.grn_id =:grnid and p.modified_type ='INSERTED'")
	List<Pur_good_receipt_doc> getPurGoodRcptDocList(@Param("grnid") String grnid);
	
	@Query(value= "SELECT p.* FROM pur_good_receipt_doc p WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED'",nativeQuery = true)
	List<Map<String, Object>> grnDocRetriveListFast(@Param("grnid") String grnid);
	
}
