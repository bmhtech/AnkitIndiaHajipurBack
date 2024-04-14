package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Accounts_ledger_master;

public interface Accounts_ledger_masterRepository extends JpaRepository<Accounts_ledger_master, Long>{
	
	
	@Query("select COUNT(id) from Accounts_ledger_master")
	String countId();

	@Query(value="select * from accounts_ledger_master where modified_type ='INSERTED'  ",nativeQuery=true)
	List<Map<String, Object>> accountledgerlist();
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Accounts_ledger_master pl where pl.modified_type = 'INSERTED' and pl.accts_ledger_group=:code")
	Boolean checkAccGroupUsage(@Param("code") String code);
	
}
