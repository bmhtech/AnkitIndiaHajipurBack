package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Return_approval_broker_dtls;

public interface Return_approval_broker_dtlsRepository extends JpaRepository<Return_approval_broker_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_broker_dtls w SET w.modified_type =:mstatus WHERE w.salesreturnid = :salesreturnid")
    int updateReturn_approval_broker_dtls(@Param("salesreturnid") String salesreturnid,@Param("mstatus") String mstatus);
	
	@Query( "select c from Return_approval_broker_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	List<Return_approval_broker_dtls> getReturnApprovalBD(@Param("salesreturnid") String salesreturnid);

}
