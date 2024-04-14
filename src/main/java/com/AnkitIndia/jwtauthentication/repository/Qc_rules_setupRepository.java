package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup;

public interface Qc_rules_setupRepository extends JpaRepository<Qc_rules_setup, Long>   {
	
	@Query("SELECT MAX(SUBSTRING(qc_id , 4 , length(qc_id))) FROM Qc_rules_setup where qc_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select c from Qc_rules_setup c where c.modified_type = 'INSERTED' and c.company_id =:company ")
	List<Qc_rules_setup> getAllQC(@Param("company") String company);
	
	@Query( "select c from Qc_rules_setup c where c.qc_active = :sts and c.modified_type = 'INSERTED' and c.company_id =:company ")
	List<Qc_rules_setup> getQcrulesNc(@Param("sts") Boolean sts,@Param("company") String company);
	
	@Query( "select q from Qc_rules_setup_dtls q where q.qc_id = :q_id and q.modified_type ='INSERTED'")//qc_code
	List<Qc_rules_setup> qcRulesRetriveList(@Param("q_id") String q_id);
	
	@Query( "select q from Qc_rules_setup_dtls q where q.qc_code = :q_id AND  q.modified_type ='INSERTED'")//qc_code
	List<Qc_rules_setup> qcRulesRetriveListnew(@Param("q_id") String q_id);
	
	@Query( "select c from Qc_rules_setup c where c.qc_id = :qc_id and c.modified_type != 'DELETED' ")
	Qc_rules_setup getQcrulesDetails(@Param("qc_id") String qc_id);
	
	@Query("select max(substring(qc_code , 8, length(qc_code))) from Qc_rules_setup where qc_code like %:code% and company_id =:company ")
	String getQcRulesSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query( "select q from Qc_rules_setup_dtls q where q.qc_id = :q_id and q.modified_type ='INSERTED'")
    Optional<String> getqcRulesDtls(@Param("q_id") String q_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order_Item_Details pl where pl.modified_type != 'DELETED' and pl.qc_norms=:qc_id")
	Boolean checkQCPurchaseUsage(@Param("qc_id") String qc_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Order_Item_Dtls pl where pl.modified_type != 'DELETED' and pl.acc_norms=:qc_id")
	Boolean checkQCSalesUsage(@Param("qc_id") String qc_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Quality_Check_Details pl where pl.modified_type != 'DELETED' and pl.qc=:qc_id")
	Boolean checkQCPurchaseQualityUsage(@Param("qc_id") String qc_id);
	
	
}
