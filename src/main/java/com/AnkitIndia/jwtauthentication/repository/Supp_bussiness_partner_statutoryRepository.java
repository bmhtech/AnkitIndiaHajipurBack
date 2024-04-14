package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;

public interface Supp_bussiness_partner_statutoryRepository extends JpaRepository<Supp_bussiness_partner_statutory, Long>{
	
	@Query("SELECT b FROM Supp_bussiness_partner_statutory b where b.bp_Id =:bp_id and b.modified_type ='INSERTED'")
	Supp_bussiness_partner_statutory getSuppBPStatuDtls(@Param("bp_id") String bp_id);
	
	@Query("SELECT b FROM Supp_bussiness_partner_statutory b where b.bp_Id =:bp_id and b.modified_type ='INSERTED'")
	Supp_bussiness_partner_statutory getSupplierStatDtls(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_statutory w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_statutoryUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query("select b from Supp_bussiness_partner_statutory b where b.bp_Id =:code and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_statutory> getSupplierStatutaries(@Param("code") String code);

	@Query("select i from Supp_bussiness_partner_statutory i where i.modified_type = 'INSERTED' and i.gst_no =:code")
	Optional<Supp_bussiness_partner_statutory> checkValidGstNo(@Param("code") String code);
	
	@Query("select i from Supp_bussiness_partner_statutory i where i.modified_type = 'INSERTED' and i.pan_no =:code")
	Optional<Supp_bussiness_partner_statutory> checkValidPANNo(@Param("code") String code);
	
}
