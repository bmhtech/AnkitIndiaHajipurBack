package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_note_trans_info;

public interface Pur_return_note_trans_infoRepository extends JpaRepository<Pur_return_note_trans_info, Long>{

	@Query( "select c from Pur_return_note_trans_info c where c.modified_type = 'INSERTED' AND c.purreturnnoteid = :purreturnnoteid")
	Pur_return_note_trans_info getPur_return_note_trans_info(@Param("purreturnnoteid") String purreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_note_trans_info w SET w.modified_type ='UPDATED' WHERE w.purreturnnoteid = :purreturnnoteid")
    int updatePur_return_note_trans_info(@Param("purreturnnoteid") String purreturnnoteid);
}
