package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_shipment_dtls;

public interface Return_approval_shipment_dtlsRepository extends JpaRepository<Return_approval_shipment_dtls, Long>{

	
	@Query( "select c from Return_approval_shipment_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	Return_approval_shipment_dtls getReturnApprovalSD(@Param("salesreturnid") String salesreturnid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_shipment_dtls w SET w.modified_type =:mstatus WHERE w.salesreturnid = :salesreturnid")
    int updateReturn_approval_shipment_dtls(@Param("salesreturnid") String salesreturnid,@Param("mstatus") String mstatus);
}
