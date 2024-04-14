package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;

public interface Accounts_type_masterRepository extends JpaRepository<Accounts_type_master, Long>
{
	@Query("select COUNT(id) from Accounts_type_master")
	String countId();
	
	@Query(value="select id, acc_type_code, acc_type_name,accts_id from accounts_type_master where modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getAccountsTypeList();
	
	@Query("select t from Accounts_type_master t where t.modified_type='INSERTED' and t.accts_id=:accts_type")
	Accounts_type_master fetchbytypeid(@Param("accts_type") String accts_type);
	
	
	

}
