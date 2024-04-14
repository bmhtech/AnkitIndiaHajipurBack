package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_bill_addr_dtls;

public interface Supp_bussiness_partner_bill_addr_dtlsRepository extends JpaRepository<Supp_bussiness_partner_bill_addr_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_bill_addr_dtls w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_bill_addr_dtlsUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	
	
	@Query("SELECT b FROM Supp_bussiness_partner_bill_addr_dtls b where b.bp_Id =:bp_id and b.modified_type = 'INSERTED'")
	List<Supp_bussiness_partner_bill_addr_dtls> getSuppBPBillAddrDtls(@Param("bp_id") String bp_id);

}
