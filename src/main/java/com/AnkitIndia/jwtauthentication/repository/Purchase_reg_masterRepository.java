package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Purchase_reg_master;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;

public interface Purchase_reg_masterRepository extends JpaRepository<Purchase_reg_master, Long>{

	
	@Query("select b from Purchase_reg_master b where b.purchase_report =:purchase_report")
	List<Purchase_reg_master> getPurReportNameList(@Param("purchase_report") String purchase_report);
	
	@Query(value = "{call GetPurReprotDyn(:purchase_report,:reportname)}", nativeQuery = true)
	List getPurReportDynamic(@Param("purchase_report") String purchase_report,@Param("reportname") String reportname);
	
	@Query(value="select r.reportfields from Purchase_reg_master r where r.purchase_report=:purchase_report and r.reportname=:reportname",nativeQuery=true)
	List getPurReportCol(@Param("purchase_report") String purchase_report,@Param("reportname") String reportname);
}
