package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_item_dtls;

public interface Pur_return_approval_item_dtlsRepository extends JpaRepository<Pur_return_approval_item_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_item_dtls w SET w.modified_type ='UPDATED' WHERE w.purreturnid = :purreturnid")
    int updatePur_return_approval_item_dtls(@Param("purreturnid") String purreturnid);

	@Query( "select p from Pur_return_approval_item_dtls p where p.modified_type = 'INSERTED' AND p.purreturnid = :purreturnid")
	List<Pur_return_approval_item_dtls> getPurReturnApprovalID(@Param("purreturnid") String purreturnid);
}
