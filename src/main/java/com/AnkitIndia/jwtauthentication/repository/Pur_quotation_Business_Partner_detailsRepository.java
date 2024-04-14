package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_quotation_Business_Partner_details;

public interface Pur_quotation_Business_Partner_detailsRepository extends JpaRepository<Pur_quotation_Business_Partner_details, Long>{

	@Query( "select p from Pur_quotation_Business_Partner_details p where p.quotation_id = :code and p.modified_type ='INSERTED' " )
	Pur_quotation_Business_Partner_details purQBPRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_quotation_Business_Partner_details w SET w.modified_type ='UPDATED' WHERE w.quotation_id = :quotation_id")
    int pur_quotation_Business_Partner_detailsupdate(@Param("quotation_id") String quotation_id);
}
