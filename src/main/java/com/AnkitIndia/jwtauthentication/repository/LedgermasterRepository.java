package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Transporter_group;

public interface LedgermasterRepository extends JpaRepository<Ledgermaster, Long>{
	
	@Query( "select l from Ledgermaster l where l.ledgerid like :subGrpcode%")
	List<Ledgermaster> getLedgerList(@Param("subGrpcode") String subGrpcode);
	
	//@Query( "select l from Ledgermaster l where l.subgroupcode ='IB' ")
	@Query( "select l from Ledgermaster l ")
	List<Ledgermaster> getLedger();
	
	@Query(value= "select * from ledgermaster where modified_type='INSERTED' ", nativeQuery=true)
	List<Map<String,Object>> getLedgerNew();
	
	@Query( "select l from Ledgermaster l where l.subgroupcode ='CC001' ")
	List<Ledgermaster> getDutiesTaxesLedger();
	
	@Query( "select l from Ledgermaster l where l.ledgerid like 'BA001%' ")
	List<Ledgermaster> getBankLedger();

	@Query( "select l from Ledgermaster l where l.maincontrol_acc ='Yes'")
	List<Ledgermaster> getControlLedgers();
	
	@Query( "select l from Ledgermaster l where l.ledgerid =:ledgerid")
	Ledgermaster getLedgers(@Param("ledgerid") String ledgerid);
	
	@Query("select l from Ledgermaster l ")
    List<Ledgermaster> getLedgers();
	
	@Query("select l from Ledgermaster l where  l.ledgername =:ledgername ")		
	Ledgermaster getLedgerDetailsThrName(@Param("ledgername") String ledgername);
	
	@Query("select max(substring(uniqucode , 4, length(uniqucode))) from Ledgermaster where uniqucode like %:prefix% ")
	String countLedgerId(@Param("prefix") String prefix);

	//If use a native query then use table name ********************************************************************
	@Query(value = "SELECT MAX(SUBSTR(ledgerid,6,5)) from ledgermaster where ledgerid like :subgroupcode% and length(ledgerid)='10'",nativeQuery = true)
	String getMaxLedger5(@Param("subgroupcode") String subgroupcode);
	
	@Query(value = "SELECT MAX(SUBSTRING(ledgerid,3,5)) from ledgermaster where ledgerid like :subgroupcode% and length(ledgerid)='7'",nativeQuery = true)
	String getMaxLedger(@Param("subgroupcode") String subgroupcode);	
	//***************************************************************************************************************
	
	@Query("select l from Ledgermaster l where l.ledgerid =:ledgerid and l.modified_type='INSERTED'")		
	Ledgermaster getLedgerDetails(@Param("ledgerid") String ledgerid);
	
	@Query("select l from Ledgermaster l where (l.ledgerid like 'CB001%' or l.ledgerid like 'CB002%') order by l.ledgername asc")
	List<Ledgermaster> getLedgerWithBACH();
	
	@Query("select l from Ledgermaster l where (l.ledgerid not like 'BA%' or l.ledgerid not like 'CH%') order by l.ledgername asc")
	List<Ledgermaster> getLedgerWithoutBACH();	
	
	@Procedure(name = "Ledgermaster.saveLedgerMaster")
	int saveLedgerMaster(@Param("subgrps") String subgrps,@Param("subgrplength") int subgrplength,@Param("ledgerid") String ledgerid,@Param("balance") double balance,@Param("defsubgrp") String defsubgrp,
			@Param("uniqueledcode") String uniqueledcode,@Param("ledgername") String ledgername,@Param("uniqucode") String uniqucode,@Param("finyear") String finyear,@Param("aliasname") String aliasname,
			@Param("billbybill") char billbybill,@Param("inventoryval") char inventoryval,@Param("costcenter") char costcenter,@Param("gstval") String gstval,@Param("gstrate") String gstrate,
			@Param("mailingname") String mailingname,@Param("defaultcredit") String defaultcredit,@Param("address") String address,@Param("placecode") String placecode,@Param("panno") String panno,
			@Param("contactperson") String contactperson,@Param("mobileno") String mobileno,@Param("emailid") String emailid,@Param("drcr") String drcr,@Param("altermobileno") String altermobileno,
			@Param("alteremailid") String alteremailid,@Param("dlno") String dlno,@Param("gstn") String gstn,@Param("cst") String cst,@Param("partytype") String partytype,
			@Param("gross") String gross,@Param("caldatefor") String caldatefor,@Param("tinno") String tinno,@Param("statename") String statename,@Param("statecode") String statecode,
			@Param("ldt") LocalDateTime ldt,@Param("username") String username,@Param("acnumber") String acnumber,@Param("bankbranchname") String bankbranchname,@Param("ifsccode") String ifsccode,
			@Param("gsttype") String gsttype,@Param("gstpaymenttype") String gstpaymenttype,@Param("mainsubgroupcode") String mainsubgroupcode,@Param("maincontrol_acc") String maincontrol_acc);
	
	@Procedure(name = "Ledgermaster.saveLedgerDrCrDetails")
	int saveLedgerDrCrDetails(@Param("uniqucode") String uniqucode,@Param("branchcode") String branchcode,@Param("voucher_type") String voucher_type,
			@Param("particulars") String particulars,@Param("voucher_date") String voucher_date,@Param("gross_amt") double gross_amt,
			@Param("pre_adjust") String pre_adjust,@Param("due_amt") double due_amt);
	
	
	@Procedure(name = "Ledgermaster.saveLedgerDrCr")
	int saveLedgerDrCr(@Param("branchcode") String branchcode,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("due") double due,@Param("drawnon") String drawnon,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("inserted_location") String inserted_location,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,@Param("narration_dtls") String narration_dtls
			,@Param("uniquevoucherno") String uniquevoucherno,@Param("drcr") String drcr,@Param("trnsamt") double trnsamt,@Param("spMinus") String spMinus,@Param("ledgerid2") String ledgerid2,@Param("dtldledgerid") String dtldledgerid
			,@Param("spMinusD") String spMinusD,@Param("dtlsamt") double dtlsamt,@Param("dtldledgerid2") String dtldledgerid2);
	
	@Procedure(name = "Ledgermaster.saveLedgerPcPb")
	int saveLedgerPcPb(@Param("branchcode") String branchcode,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("due") double due,@Param("drawnon") String drawnon,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("inserted_location") String inserted_location,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,@Param("narration_dtls") String narration_dtls
			,@Param("uniquevoucherno") String uniquevoucherno,@Param("drcr") String drcr,@Param("trnsamt") double trnsamt,@Param("spMinus") String spMinus,@Param("ledgerid2") String ledgerid2,@Param("dtldledgerid") String dtldledgerid
			,@Param("spMinusD") String spMinusD,@Param("dtlsamt") double dtlsamt,@Param("dtldledgerid2") String dtldledgerid2);
	
	@Procedure(name = "Ledgermaster.saveLedgerJr")
	int saveLedgerJr(@Param("branchcode") String branchcode,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("due") double due,@Param("drawnon") String drawnon,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("inserted_location") String inserted_location,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,@Param("narration_dtls") String narration_dtls
			,@Param("uniquevoucherno") String uniquevoucherno,@Param("drcr") String drcr,@Param("trnsamt") double trnsamt,@Param("spMinus") String spMinus,@Param("ledgerid2") String ledgerid2,@Param("dtldledgerid") String dtldledgerid
			,@Param("spMinusD") String spMinusD,@Param("dtlsamt") double dtlsamt,@Param("dtldledgerid2") String dtldledgerid2);
	
	@Procedure(name = "Ledgermaster.saveLedgerBillByBill")
	int saveLedgerBillByBill(@Param("branchcode") String branchcode,@Param("ledgerid") String ledgerid,@Param("voucherno") String voucherno,@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("billamount") double billamount,
			@Param("adjustmentamount") String adjustmentamount,@Param("duesamount") double duesamount,@Param("vouchertype") String vouchertype,@Param("entrybranchcode") String entrybranchcode,@Param("uniquevoucherno") String uniquevoucherno,@Param("out_uniquevoucherno") String out_uniquevoucherno,@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("fyear") String fyear);

	@Procedure(name = "Ledgermaster.saveLedgerRcRb")
	int saveLedgerRcRb(@Param("branchcode") String branchcode,@Param("entrydate") int entrydate,@Param("voucherno") String voucherno,@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("totalamount") Double totalamount,@Param("opamt") Double opamt,@Param("trnsamt") Double trnsamt,@Param("dtlsamt") Double dtlsamt,@Param("trnsdrcr") String trnsdrcr,@Param("dtlsdrcr") String dtlsdrcr,@Param("drawnon") String drawnon,@Param("inserted_on") String inserted_on,@Param("inserted_by") String inserted_by,
			@Param("inserted_location") String inserted_location,@Param("fyear") String fyear,@Param("status") String status,@Param("entrybranchcode") String entrybranchcode,@Param("uniquevoucherno") String uniquevoucherno,@Param("narration_name") String narration_name,@Param("drcr") String drcr,@Param("spMinus") String spMinus);
	
	
	@Query( "select l from Ledgermaster l where l.modified_type='INSERTED'")
	List<Ledgermaster> getLedgerList();
}
