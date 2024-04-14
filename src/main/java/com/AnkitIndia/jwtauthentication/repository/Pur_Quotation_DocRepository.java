package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Quotation_Doc;

public interface Pur_Quotation_DocRepository extends JpaRepository<Pur_Quotation_Doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quotation_Doc w SET w.modified_type ='UPDATED' WHERE w.quotation_id = :quotation_id")
    int pur_Quotation_Docupdate(@Param("quotation_id") String quotation_id);

}
