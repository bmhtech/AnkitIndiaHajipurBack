package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Sale_invoice_einvoice_gen;


public interface Sale_invoice_einvoice_genRepository extends JpaRepository<Sale_invoice_einvoice_gen, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_invoice_einvoice_gen SET cancel_status=1,cancel_message=:message,cancel_on=:ldt,cancel_by=:username WHERE invoice_id =:invid and modified_type='INSERTED'")
    int updateEinvoiceGenCancel(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("message") String message);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_invoice_einvoice_gen SET eway_bill_no=:ewaybill,eway_bill_date=:ewaybilldate,eway_valid_upto=:ewaybillvaliddate,distance=:distance,waybill_create_on=:ldt,waybill_create_by=:username WHERE invoice_id =:invid and modified_type='INSERTED'")
    int updateEwaybillGen(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("ewaybill") String ewaybill,@Param("ewaybilldate") String ewaybilldate,@Param("ewaybillvaliddate") String ewaybillvaliddate,@Param("distance") String distance);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_invoice_einvoice_gen SET waybill_cancel_message=:message,waybill_cancel_on=:ldt,waybill_cancel_by=:username WHERE invoice_id =:invid and modified_type='INSERTED'")
    int updateEwaybillGenCancel(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("message") String message);
	
	@Query(value = "select checkIrn(:#{#irn})", nativeQuery = true)
	int checkIrn(@Param("irn") String irn);
	
	@Query("select b from Sale_invoice_einvoice_gen b where b.modified_type = 'INSERTED' and b.invoice_id =:invid")
	Sale_invoice_einvoice_gen gettallyEinvoice (@Param("invid") String invid);
}
