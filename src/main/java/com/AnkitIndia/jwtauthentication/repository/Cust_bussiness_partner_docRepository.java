package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_doc;

public interface Cust_bussiness_partner_docRepository extends JpaRepository<Cust_bussiness_partner_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_doc w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_docupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
	@Query( "select c from Cust_bussiness_partner_doc c where c.cp_Id = :code and c.modified_type = 'INSERTED' ")
	List<Cust_bussiness_partner_doc> custDocRetriveList(@Param("code") String code);

}
