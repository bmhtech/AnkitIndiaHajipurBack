package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;


@Transactional

public interface Sales_reg_masterRepository extends JpaRepository<Sales_reg_master, Long>{

	@Query("select b from Sales_reg_master b where b.sales_report =:sales_report")
	List<Sales_reg_master> getReportName_List(@Param("sales_report") String sales_report);
	
	
	@Query(value = "{call GetCustomerDetail(:sales_invoice,:reportname)}", nativeQuery = true)
	List getSalesInvoices(@Param("sales_invoice") String sales_invoice,@Param("reportname") String reportname);
	
	@Query(value = "select a.reportfields from Sales_reg_master a where a.sales_report =:sales_invoice and a.reportname=:reportname", nativeQuery = true)
	List getSalesCol(@Param("sales_invoice") String sales_invoice,@Param("reportname") String reportname);
	
	
}
