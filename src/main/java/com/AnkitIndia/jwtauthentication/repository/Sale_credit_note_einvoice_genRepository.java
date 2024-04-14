package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sale_credit_note_einvoice_gen;

public interface Sale_credit_note_einvoice_genRepository extends JpaRepository<Sale_credit_note_einvoice_gen, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_credit_note_einvoice_gen SET cancel_status=1,cancel_message=:message,cancel_on=:ldt,cancel_by=:username WHERE creditnoteid =:invid and modified_type='INSERTED'")
    int updateEinvoiceGenCancel(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("message") String message);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_credit_note_einvoice_gen SET eway_bill_no=:ewaybill,eway_bill_date=:ewaybilldate,waybill_create_on=:ldt,waybill_create_by=:username WHERE creditnoteid =:invid and modified_type='INSERTED'")
    int updateEwaybillGen(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("ewaybill") String ewaybill,@Param("ewaybilldate") String ewaybilldate);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sale_credit_note_einvoice_gen SET waybill_cancel_message=:message,waybill_cancel_on=:ldt,waybill_cancel_by=:username WHERE creditnoteid =:invid and modified_type='INSERTED'")
    int updateEwaybillGenCancel(@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("invid") String invid,@Param("message") String message);
	
	@Query(value = "select checkCreditNoteIrn(:#{#irn})", nativeQuery = true)
	int checkCreditNoteIrn(@Param("irn") String irn);
	
	@Query("select b from Sale_credit_note_einvoice_gen b where b.modified_type = 'INSERTED' and b.creditnoteid =:invid")
	Sale_credit_note_einvoice_gen gettallyEinvoice (@Param("invid") String invid);
	
	@Query(value="SELECT * FROM sale_credit_note_einvoice_gen WHERE modified_type='INSERTED' And creditnoteid=:creditnoteid ",nativeQuery=true)
	Map<String,Object> creditnoteeinvoicedetails(@Param("creditnoteid") String creditnoteid);
	
}
