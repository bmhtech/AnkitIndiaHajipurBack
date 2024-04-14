package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_note_weight_dtl;

public interface Pur_return_note_weight_dtlRepository extends JpaRepository<Pur_return_note_weight_dtl, Long>{

	
	@Query( "select c from Pur_return_note_weight_dtl c where c.modified_type = 'INSERTED' AND c.purreturnnoteid = :purreturnnoteid")
	Pur_return_note_weight_dtl getPur_return_note_weight_dtl(@Param("purreturnnoteid") String purreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_note_weight_dtl w SET w.modified_type ='UPDATED' WHERE w.purreturnnoteid = :purreturnnoteid")
    int updatePur_return_note_weight_dtl(@Param("purreturnnoteid") String purreturnnoteid);
}
