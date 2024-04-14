package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_trans_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_trans_dyn;

public interface Pur_return_note_trans_dynRepository extends JpaRepository<Pur_return_note_trans_dyn, Long>{

	@Query( "select p from Pur_return_note_trans_dyn p where p.modified_type = 'INSERTED' AND p.purreturnnoteid = :purreturnnoteid")
	List<Pur_return_note_trans_dyn> getPur_return_note_trans_dyn(@Param("purreturnnoteid") String purreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_note_trans_dyn w SET w.modified_type ='UPDATED' WHERE w.purreturnnoteid =:purreturnnoteid")
    int updatePur_return_note_trans_dyn(@Param("purreturnnoteid") String purreturnnoteid);
	
	@Query( "select p from Pur_return_approval_trans_dyn p where p.modified_type = 'INSERTED' AND p.purreturnid = :purreturnid")
	List<Pur_return_approval_trans_dyn> getPurReturnApprovalTD(@Param("purreturnid") String purreturnid);
}
