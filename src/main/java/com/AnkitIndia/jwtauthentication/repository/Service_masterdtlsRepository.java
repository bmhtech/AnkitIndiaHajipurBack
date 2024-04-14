package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Service_masterdtls;

public interface Service_masterdtlsRepository extends JpaRepository<Service_masterdtls, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Service_masterdtls u SET u.modified_type =:mstatus WHERE u.service_no = :service_no and u.modified_type ='INSERTED'")
	  int updateService_masterdtls(@Param("service_no") String service_no,@Param("mstatus") String mstatus);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Service_masterdtls d SET d.modified_type ='DELETED' WHERE d.service_no = :service_no")
    int service_masterdtlsUpdate(@Param("service_no") String service_no);
	
}
