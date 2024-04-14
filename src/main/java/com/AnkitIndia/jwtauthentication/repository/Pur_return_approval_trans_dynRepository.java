package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_trans_dyn;

public interface Pur_return_approval_trans_dynRepository extends JpaRepository<Pur_return_approval_trans_dyn, Long>{

	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_trans_dyn w SET w.modified_type ='UPDATED' WHERE w.purreturnid = :purreturnid")
    int updatePur_return_approval_trans_dyn(@Param("purreturnid") String purreturnid);
}
