package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls_for_jobwork;

public interface Return_approval_item_dtls_for_jobworkRepository extends JpaRepository<Return_approval_item_dtls_for_jobwork, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_item_dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.salesreturnid = :salesreturnid")
    int updateReturn_approval_item_dtls_for_jobwork(@Param("salesreturnid") String salesreturnid,@Param("mstatus") String mstatus);

	@Query( value="select * from return_approval_item_dtls_for_jobwork s where s.modified_type = 'INSERTED' and s.salesreturnid =:salesreturnid",nativeQuery=true)
	List<Map<String,Object>> retriveReturnAppJobwork(@Param("salesreturnid") String salesreturnid);
	 
}
