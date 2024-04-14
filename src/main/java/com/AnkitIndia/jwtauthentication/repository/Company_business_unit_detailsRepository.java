package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_business_unit_details;

public interface Company_business_unit_detailsRepository extends JpaRepository<Company_business_unit_details, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Company_business_unit_details w SET w.modified_type =:mstatus WHERE w.businessunit_id = :businessunit_id")
    int company_business_unit_detailsupdate(@Param("businessunit_id") String businessunit_id,@Param("mstatus") String mstatus);
	
}
