package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;

public interface Supp_bussiness_partner_addr_dtlsRepository extends JpaRepository<Supp_bussiness_partner_addr_dtls, Long> {

	@Query( "select a from Supp_bussiness_partner_addr_dtls a where a.bp_Id =:sbp_id and a.contact_person = :cp and a.modified_type ='INSERTED'")
	Supp_bussiness_partner_addr_dtls getSupplierContDetails(@Param("sbp_id") String sbp_id,@Param("cp") String cp);
	
	@Query("SELECT sa FROM Supp_bussiness_partner_addr_dtls sa where sa.bp_Id =:bp_id and sa.contact_person =:CP and sa.modified_type ='INSERTED'")
	Supp_bussiness_partner_addr_dtls getSuppphoneByIdName(@Param("bp_id") String bp_id,@Param("CP") String CP);
	
	@Query("SELECT sa FROM Supp_bussiness_partner_addr_dtls sa where sa.bp_Id =:bp_id and sa.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_addr_dtls> getSuppContById(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_addr_dtls w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_addr_dtlsUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query("SELECT sa FROM Supp_bussiness_partner_addr_dtls sa where sa.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_addr_dtls> getSupplierContact();
	
}
