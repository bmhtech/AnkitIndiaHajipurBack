package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_transaction_spl;

public interface Production_transaction_splRepository extends JpaRepository<Production_transaction_spl, Long>{
	
	@Query("select max(substring(prod_trans_code , 15, length(prod_trans_code))) from Production_transaction_spl where business_unit =:businessunit and shop_floor =:sfloor and prod_trans_code like %:code% and company_id =:company ")
	String getPTSequenceId(@Param("businessunit") String businessunit,@Param("sfloor") String sfloor,@Param("code") String code,@Param("company") String company);
	
	@Query("select COUNT(id) from Production_transaction_spl")
	String countId();
	
	@Query("select p from Production_transaction_spl p where p.modified_type ='INSERTED' ORDER BY p.prod_trans_id DESC")
	List<Production_transaction_spl> findAllProTrans();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Production_transaction_spl p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Production_transaction_spl p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int prodSpclPostingUndo(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);

}
