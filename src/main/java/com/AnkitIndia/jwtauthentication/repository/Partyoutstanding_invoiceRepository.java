package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Partyoutstanding_invoice;

public interface Partyoutstanding_invoiceRepository extends JpaRepository<Partyoutstanding_invoice, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Partyoutstanding_invoice p SET p.adjustmentamount =p.adjustmentamount+:payableamt,p.duesamount = p.duesamount-:payableamt  WHERE p.invoice_id =:invoice_id and p.party =:party " )
    int updatePartyOutstanding(@Param("party") String party,@Param("invoice_id") String invoice_id,@Param("payableamt") double payableamt);

}
