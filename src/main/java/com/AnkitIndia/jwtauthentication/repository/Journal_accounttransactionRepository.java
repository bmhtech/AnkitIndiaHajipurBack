package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AnkitIndia.jwtauthentication.model.Journal_accounttransaction;

@Repository
public interface Journal_accounttransactionRepository extends JpaRepository<Journal_accounttransaction, Long>{

	@Query("SELECT COUNT(id) FROM Journal_accounttransaction")
	String countJournalId();
	
	@Query("select COUNT(id) from Journal_accounttransaction where entrydate =:entrydate")
	String countJournalAcc(@Param("entrydate") String entrydate);
	
	@Query("SELECT COUNT(id) FROM Journal_accounttransaction where voucherno like %:vcode% and branchcode =:branch ")
	String countJournalId(@Param("vcode") String vcode,@Param("branch") String branch);
	
	@Query(value="SELECT MAX(SUBSTR(voucherno,13,5)) FROM journal_accounttransaction where voucherno like %:vcode% and branchcode =:branch and fin_year =:finyear", nativeQuery = true)
	String getMaxVoucherId(@Param("vcode") String vcode,@Param("branch") String branch,@Param("finyear") String finyear);
	
	@Query("select r from Journal_accounttransaction r where r.modified_type ='INSERTED'")
    List<Journal_accounttransaction> getJournalAccDetails();
	
	@Procedure(name = "Journal_accounttransaction.saveJournalAccount")
	int saveJournalAccount(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("amount") double amount,@Param("drawnon") String drawnon,@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,
			@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,
			@Param("narration_dtls") String narration_dtls,@Param("uniquevoucher") String uniquevoucher,@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,
			@Param("invoiceid") String invoiceid,@Param("invoiceno") String invoiceno);
	
	@Procedure(name = "Journal_accounttransaction.updateJournalAccount")
	int updateJournalAccount(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("amount") double amount,@Param("drawnon") String drawnon,@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,
			@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,
			@Param("narration_dtls") String narration_dtls,@Param("uniquevoucher") String uniquevoucher,@Param("subgrps") String subgrps,@Param("groupcode") String groupcode);
	
	@Procedure(name = "Journal_accounttransaction.callJournalAccInsertPlus")
	int callJournalAccInsertPlus(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("uniquevoucherno") String uniquevoucherno,
			@Param("ledgerid") String ledgerid,@Param("amount") double amount,@Param("narration") String narration,
			@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,
			@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,@Param("gstcal") String gstcal,
			@Param("percentage") double percentage,@Param("invoiceid") String invoiceid,@Param("invoiceno") String invoiceno);
	
	@Procedure(name = "Journal_accounttransaction.updateJournalAccInsertPlus")
	int updateJournalAccInsertPlus(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("uniquevoucherno") String uniquevoucherno,
			@Param("ledgerid") String ledgerid,@Param("amount") double amount,@Param("narration") String narration,
			@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("modified_type") String modified_type,@Param("fin_year") String fin_year,
			@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,@Param("gstcal") String gstcal,
			@Param("percentage") double percentage);
	
	@Procedure(name = "Journal_accounttransaction.saveOutstandingledger")
	int saveOutstandingledger(@Param("branch") String branch,@Param("ledgerid") String ledgerid,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("invoice_no") String invoice_no,@Param("referencedate") String referencedate,@Param("billamount") double billamount,
			@Param("duesamount") double duesamount,@Param("vouchertype") String vouchertype,@Param("fin_year") String fin_year,
			@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,
			@Param("party") String party,@Param("partyname") String partyname,@Param("unitname") String unitname);
	
	@Procedure(name = "Journal_accounttransaction.updateOutstandingledger")
	int updateOutstandingledger(@Param("branch") String branch,@Param("ledgerid") String ledgerid,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("billamount") double billamount,
			@Param("duesamount") double duesamount,@Param("vouchertype") String vouchertype,@Param("fin_year") String fin_year,
			@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,
			@Param("party") String party,@Param("partyname") String partyname,@Param("unitname") String unitname);
	
	@Modifying
	@Query("DELETE FROM Journal_accounttransaction j WHERE j.voucherno =:voucherno and j.branchcode =:branchcode ")
	void deleteJournal_account(@Param("voucherno") String voucherno,@Param("branchcode") String branchcode);
	
}
