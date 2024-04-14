package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_supplier_dtls;

public interface Pur_return_approval_supplier_dtlsRepository extends JpaRepository<Pur_return_approval_supplier_dtls, Long>{

	
	@Query( "select c from Pur_return_approval_supplier_dtls c where c.modified_type = 'INSERTED' AND c.purreturnid = :purreturnid")
	Pur_return_approval_supplier_dtls getPurReturnApprovalWD(@Param("purreturnid") String purreturnid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_supplier_dtls w SET w.modified_type ='UPDATED' WHERE w.purreturnid = :purreturnid")
    int updatePur_return_approval_supplier_dtls(@Param("purreturnid") String purreturnid);
}
