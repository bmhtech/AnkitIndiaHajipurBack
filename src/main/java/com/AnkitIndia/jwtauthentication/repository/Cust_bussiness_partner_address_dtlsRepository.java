package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address_dtls;

public interface Cust_bussiness_partner_address_dtlsRepository extends JpaRepository< Cust_bussiness_partner_address_dtls, Long> {

	@Query( "select c from Cust_bussiness_partner_address_dtls c where c.cp_Id =:code and c.contact_person = :name and c.company_id=:company and c.modified_type = 'INSERTED'")
	Cust_bussiness_partner_address_dtls custContactByName(@Param("code") String code,@Param("name") String name,@Param("company") String company);
	
	@Query( "select c from Cust_bussiness_partner_address_dtls c where c.cp_Id =:cbp_id and c.contact_person = :cp and c.modified_type = 'INSERTED'")
	Cust_bussiness_partner_address_dtls getCustContDetails(@Param("cbp_id") String cbp_id,@Param("cp") String cp);
	
	@Query( "select c from Cust_bussiness_partner_address_dtls c where c.cp_Id = :code and c.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner_address_dtls> custAddDtlsRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_address_dtls w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_address_dtlsupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
}
