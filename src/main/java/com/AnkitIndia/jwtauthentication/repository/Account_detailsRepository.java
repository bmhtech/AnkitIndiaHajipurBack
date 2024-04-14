package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Account_details;
import com.AnkitIndia.jwtauthentication.model.Invoice_accountdetails;

public interface Account_detailsRepository extends JpaRepository<Account_details, Long>{
	
	@Query( "select a from Account_details a where a.modified_type ='INSERTED' and a.voucherno =:voucherno and a.ledgerid !=:ledger ")
	List<Account_details> getAccount_details(@Param("voucherno") String voucherno,@Param("ledger") String ledger);

	@Query("select i from Account_details i where i.modified_type = 'INSERTED' and i.branchcode =:bunit and i.ledgerid =:ledger and i.entrydate BETWEEN DATE_FORMAT(STR_TO_DATE(:fromdate,'%d-%m-%Y'),'%Y-%m-%d') and DATE_FORMAT(STR_TO_DATE(:todate,'%d-%m-%Y'),'%Y-%m-%d') ORDER BY i.entrydate") 
	List<Account_details> getControlAccPayDetails(@Param("bunit") String bunit,@Param("ledger") String ledger,@Param("fromdate") String fromdate,@Param("todate") String todate);
}
