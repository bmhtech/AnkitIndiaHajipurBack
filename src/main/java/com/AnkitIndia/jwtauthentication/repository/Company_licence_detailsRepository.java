package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_licence_details;

public interface Company_licence_detailsRepository extends JpaRepository<Company_licence_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Company_licence_details w SET w.modified_type =:mstatus WHERE w.company_id = :company_id")
    int updateCompany_licence_details(@Param("company_id") String company_id,@Param("mstatus") String mstatus);

}
