package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Creditnote_accounttransaction;

public interface Creditnote_accounttransactionRepository extends JpaRepository<Creditnote_accounttransaction, Long>{
	
	@Query(value="SELECT MAX(SUBSTR(voucherno,13,5)) FROM creditnote_accounttransaction where voucherno like %:vcode% and branchcode =:branch and fin_year =:finyear", nativeQuery = true)
	String getMaxVoucherId(@Param("vcode") String vcode,@Param("branch") String branch,@Param("finyear") String finyear);
	
	@Procedure(name = "Creditnote_accounttransaction.saveCreditnoteAccount")
	int saveCreditnoteAccount(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("amount") double amount,@Param("drawnon") String drawnon,@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,
			@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,
			@Param("narration_dtls") String narration_dtls,@Param("uniquevoucher") String uniquevoucher,@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,
			@Param("creditnoteid") String creditnoteid,@Param("creditnoteno") String creditnoteno);
	
	@Procedure(name = "Creditnote_accounttransaction.callCreditnoteAccInsertPlus")
	int callCreditnoteAccInsertPlus(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("uniquevoucher") String uniquevoucher,
			@Param("ledgerid") String ledgerid,@Param("amount") double amount,@Param("narration") String narration,
			@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,
			@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,@Param("gstcal") String gstcal,
			@Param("percentage") double percentage,@Param("creditnoteid") String creditnoteid,@Param("creditnoteno") String creditnoteno);
	
	@Procedure(name = "Creditnote_accounttransaction.saveOutstandingledger")
	int saveOutstandingledger(@Param("branch") String branch,@Param("ledgerid") String ledgerid,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("invoice_no") String invoice_no,@Param("referencedate") String referencedate,@Param("billamount") double billamount,
			@Param("duesamount") double duesamount,@Param("vouchertype") String vouchertype,@Param("fin_year") String fin_year,
			@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,
			@Param("party") String party,@Param("partyname") String partyname,@Param("unitname") String unitname);
	
}
