package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Accounts_category_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;



public interface Accounts_category_masterRepository extends JpaRepository<Accounts_category_master, Long>{

	
	@Query(value="select id, accts_catagory_code,accts_catagory_name,acc_type_name,accts_catagory_id from accounts_category_master where modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getAccountsCategoryList();
	
	@Query("select COUNT(id) from Accounts_category_master")
	String countId();
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Accounts_category_master pl where pl.modified_type = 'INSERTED' and pl.accts_type=:code")
	Boolean checkAccTypeUsage(@Param("code") String code);
	
	@Query(value="select id, accts_catagory_id, accts_catagory_name from accounts_category_master where modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getAccountsCatagoryList();
	
	@Query("select c from Accounts_category_master c where c.modified_type='INSERTED' and c.accts_catagory_id=:accts_catagory_id")
	Accounts_category_master getAccCatagoryNameByCode(@Param("accts_catagory_id") String accts_catagory_id);
}
