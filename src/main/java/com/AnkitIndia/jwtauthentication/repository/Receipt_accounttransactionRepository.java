package com.AnkitIndia.jwtauthentication.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AnkitIndia.jwtauthentication.model.Receipt_accounttransaction;

@Repository
public interface Receipt_accounttransactionRepository extends JpaRepository<Receipt_accounttransaction, Long>{
	
	/*@Query("SELECT MAX(id) FROM Receipt_accounttransaction ")
	String countRcptId();*/
	
	@Query("select MAX(SUBSTRING(uniquevoucherno , 13, length(uniquevoucherno))) from Receipt_accounttransaction where uniquevoucherno like %:vcode% and fin_year =:finyear ")
	String countRcptId(@Param("vcode") String vcode,@Param("finyear") String finyear);
	
	@Query("select COUNT(id) from Receipt_accounttransaction where entrydate =:entrydate")
	String countRcptAcc(@Param("entrydate") String entrydate);
	
	@Query("SELECT COUNT(id) FROM Receipt_accounttransaction where voucherno like %:vcode% and branchcode =:branch ")
	String countReceiptId(@Param("vcode") String vcode,@Param("branch") String branch);
	
	@Query("SELECT MAX(SUBSTRING(voucherno,13,length(voucherno))) FROM Receipt_accounttransaction where voucherno like %:vcode% and branchcode =:branch and fin_year =:finyear")
	String getMaxVoucherId(@Param("vcode") String vcode,@Param("branch") String branch,@Param("finyear") String finyear);
	
	@Query("select r from Receipt_accounttransaction r where r.modified_type ='INSERTED'")
    List<Receipt_accounttransaction> getReceiptAccDetails();
	
	@Procedure(name = "Receipt_accounttransaction.saveReceiptAccount")
	int saveReceiptAccount(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("amount") double amount,@Param("drawnon") String drawnon,@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,
			@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,
			@Param("narration_dtls") String narration_dtls,@Param("uniquevoucher") String uniquevoucher,@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,
			@Param("pbillid") String pbillid,@Param("pbillno") String pbillno);
	
	@Procedure(name = "Receipt_accounttransaction.callRcptAccInsertPlus")
	int callRcptAccInsertPlus(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("uniquevoucher") String uniquevoucher,
			@Param("ledgerid") String ledgerid,@Param("amount") double amount,@Param("narration") String narration,
			@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,
			@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,@Param("gstcal") String gstcal,
			@Param("percentage") double percentage,@Param("pbillid") String pbillid,@Param("pbillno") String pbillno);
	
	
	//@Query(value = "select * from Receipt_accounttransaction where voucherno =:voucherno",nativeQuery = true)
	//Receipt_accounttransaction getReceiptTraThrVno(@Param("voucherno") String voucherno,@Param("ledgerid") String ledgerid);
	
}
