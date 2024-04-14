package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_doc;

public interface Trans_bussiness_partner_docRepository extends JpaRepository<Trans_bussiness_partner_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_doc w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_docupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query( "select t from Trans_bussiness_partner_doc t where t.modified_type = 'INSERTED' and t.bp_Id =:bp_id")
	List<Trans_bussiness_partner_doc> getTransBPDocs(@Param("bp_id") String bp_id);

}
