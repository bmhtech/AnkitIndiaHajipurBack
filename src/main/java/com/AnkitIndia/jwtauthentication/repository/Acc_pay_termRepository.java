package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;

public interface Acc_pay_termRepository extends JpaRepository<Acc_pay_term_master, Long> {
	@Query("select COUNT(id) from Acc_pay_term_master")
	String countId();
	
	@Query( "select c from Acc_pay_term_master c where c.modified_type != 'DELETED' ")
	List<Acc_pay_term_master> getAccPaytermNc();
	
	@Query( "select payterm_desc from Acc_pay_term_master")
	List<Acc_pay_term_master> getPayTermList();
	
	@Query( "select p from Acc_pay_term_master p where p.modified_type != 'DELETED' and p.payterm_active =:status ")
	List<Acc_pay_term_master> getPayTermNCList(@Param("status") boolean status);
	
	@Query( "select p from Acc_pay_term_master_details p where p.payterm_code = :p_id and p.modified_type = 'INSERTED'")
	List<Acc_pay_term_master> payTermRetriveList(@Param("p_id") String p_id);
	
	@Query("select max(substring(payterm_code , 8, length(payterm_code))) from Acc_pay_term_master where payterm_code like %:code% and company_id =:company ")
	String getPayTermSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order_Terms_Con pl where pl.modified_type != 'DELETED' and pl.payment_terms=:ptid")
	Boolean checkPayTermPurchaseUsage(@Param("ptid") String ptid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Order_Terms_Con pl where pl.modified_type != 'DELETED' and pl.payment_term=:ptid")
	Boolean checkPayTermSalesUsage(@Param("ptid") String ptid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Supp_bussiness_partner_accont pl where pl.modified_type != 'DELETED' and pl.pay_term=:ptid")
	Boolean checkPayTermSupplierUsage(@Param("ptid") String ptid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Trans_bussiness_partner_accont pl where pl.modified_type != 'DELETED' and pl.pay_term=:ptid")
	Boolean checkPayTermTransporterUsage(@Param("ptid") String ptid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Cust_bussiness_partner_accont pl where pl.modified_type != 'DELETED' and pl.pay_term=:ptid")
	Boolean checkPayTermCustomerUsage(@Param("ptid") String ptid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Broker_master_account pl where pl.modified_type != 'DELETED' and pl.pay_term=:ptid")
	Boolean checkPayTermBrokerUsage(@Param("ptid") String ptid);
	
	@Query(value="select * from acc_pay_term_master_details p where p.modified_type = 'INSERTED'",nativeQuery=true)
	List<Map<String,Object>> getPayTermNCListFast();
	
}