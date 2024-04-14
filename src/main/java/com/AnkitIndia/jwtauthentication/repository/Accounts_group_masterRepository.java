package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;

public interface Accounts_group_masterRepository extends JpaRepository<Accounts_group_master, Long>{
	
	@Query("select COUNT(id) from Accounts_group_master")
	String countId();
	
	@Query("select max(substring(accts_group_code , 8, length(accts_group_code))) from Accounts_group_master where accts_group_code like %:code% and company_id =:company ")
	String getIgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value="select * from accounts_group_master g where g.modified_type = 'INSERTED' and g.accts_grp_active =:status and g.company_id =:company and g.accts_catagory =:catagory ORDER BY g.accts_group_id", nativeQuery=true)
	List<Map<String,Object>> accGroupList(@Param("status") boolean status,@Param("company") String company,@Param("catagory") String catagory);

	@Query(value="select id,accts_group_code,accts_grp_name,parent_group_name,accts_group_id,acc_type_name from accounts_group_master where modified_type = 'INSERTED'", nativeQuery=true)
	List<Map<String,Object>> accountGroupList();
	
	@Query(value="select accts_group_code,accts_grp_name,accts_group_id from accounts_group_master where modified_type = 'INSERTED' and accts_grp_active=1", nativeQuery=true)
	List<Map<String,Object>> accountParentGroupList();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Accounts_group_master g SET g.export=:export , g.response=:response WHERE g.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Query("select t from Accounts_group_master t where t.modified_type='INSERTED' and t.accts_group_id=:accts_group_id")
	Accounts_group_master parentGroupNameByCode(@Param("accts_group_id") String accts_group_id);
	
/*	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM accounts_group_master pl where pl.modified_type = 'INSERTED' and pl.accts_catagory=:code")
	Boolean checkAccCatagoryUsage(@Param("code") String code);*/
}
