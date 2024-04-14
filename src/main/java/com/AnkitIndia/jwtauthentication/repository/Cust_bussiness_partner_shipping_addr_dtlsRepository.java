package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_shipping_addr_dtls;

public interface Cust_bussiness_partner_shipping_addr_dtlsRepository extends JpaRepository<Cust_bussiness_partner_shipping_addr_dtls, Long>  
{
	@Query( value="select * from cust_bussiness_partner_shipping_addr_dtls c where c.cp_Id = :cp_id and c.modified_type = 'INSERTED' order by c.slno", nativeQuery=true)
	List<Map<String, Object>> custShipAddDtlsRetriveList(@Param("cp_id") String cp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_shipping_addr_dtls w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_shipping_addr_dtlsupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
}
