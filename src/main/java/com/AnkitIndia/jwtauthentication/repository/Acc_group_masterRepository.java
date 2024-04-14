package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_group_master;

public interface Acc_group_masterRepository extends JpaRepository<Acc_group_master, Long> {
	@Query("select COUNT(id) from Acc_group_master")
	String countId();
	
	//@Query( "select o.businessunit_name from CompanyBusinessUnitMaster o where o.businessunit_code = :bucode" )
	//String getBCodeToBUnitName(String bucode);
	
	//@Query( "select o.businessunit_code from CompanyBusinessUnitMaster o where o.businessunit_name = :buname" )
	//String getBUnitNameToBCode(String buname);
	
	//@Query( "select o.group_name from Acc_group_master o where o.modified_type != 'DELETED'")
	//List<Acc_group_master> getGroupNameList();
	
	@Query( "select group_code from Acc_group_master")
	List<Acc_group_master> groupCode();
	
	@Query( "select group_name from Acc_group_master where group_code = :code")
	List<Acc_group_master> groupNameByCode(@Param("code") String code);
}