package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Invoice_accountdetails;

public interface Invoice_accountdetailsRepository extends JpaRepository<Invoice_accountdetails, Long>{
	
	@Query("select i from Invoice_accountdetails i where i.modified_type = 'INSERTED' and i.business_unit =:bunit and i.particulars =:party and i.entrydate BETWEEN DATE_FORMAT(STR_TO_DATE(:fromdate,'%d-%m-%Y'),'%Y-%m-%d') and DATE_FORMAT(STR_TO_DATE(:todate,'%d-%m-%Y'),'%Y-%m-%d') ORDER BY i.entrydate") 
	List<Invoice_accountdetails> getSalesPaymentDetails(@Param("bunit") String bunit,@Param("party") String party,@Param("fromdate") String fromdate,@Param("todate") String todate);

	@Query("select i from Invoice_accountdetails i where i.modified_type = 'INSERTED' and i.business_unit =:bunit and i.particulars =:ledger and i.entrydate BETWEEN DATE_FORMAT(STR_TO_DATE(:fromdate,'%d-%m-%Y'),'%Y-%m-%d') and DATE_FORMAT(STR_TO_DATE(:todate,'%d-%m-%Y'),'%Y-%m-%d') ORDER BY i.entrydate") 
	List<Invoice_accountdetails> getControlAccPayDetails(@Param("bunit") String bunit,@Param("ledger") String ledger,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Invoice_accountdetails i SET i.modified_type =:mstatus WHERE i.voucherno =:invoice_id")
    int updateInvoice_accountdetails(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
}
