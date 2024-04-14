package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_good_reciept_trans_info;



public interface Pur_good_receipt_driver_dtlsRepository extends JpaRepository<Pur_good_receipt_driver_dtls, Long> {

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_driver_dtls w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatedriverdetails(@Param("grn_id") String grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_driver_dtls w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int deletedriverdetails(@Param("grn_id") String grn_id);
	
	
	@Query( "select p from Pur_good_receipt_driver_dtls p where p.grn_id = :code and p.modified_type ='INSERTED'" )
	Pur_good_receipt_driver_dtls grndriverdetails(@Param("code") String code);
	
}
