package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_doc;

public interface Supp_bussiness_partner_docRepository extends JpaRepository<Supp_bussiness_partner_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_doc w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_docUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query("SELECT b FROM Supp_bussiness_partner_doc b where b.bp_Id =:bp_id and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_doc> getSuppBPDoc(@Param("bp_id") String bp_id);

}
