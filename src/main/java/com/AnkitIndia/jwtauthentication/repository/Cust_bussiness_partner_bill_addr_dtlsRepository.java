package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_bill_addr_dtls;

public interface Cust_bussiness_partner_bill_addr_dtlsRepository extends JpaRepository<Cust_bussiness_partner_bill_addr_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_bill_addr_dtls w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_bill_addr_dtlsupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
	@Query( "select c from Cust_bussiness_partner_bill_addr_dtls c where c.cp_Id = :code and c.modified_type = 'INSERTED'")
	List<Cust_bussiness_partner_bill_addr_dtls> custBillAddDtlsRetriveList(@Param("code") String code);

}
