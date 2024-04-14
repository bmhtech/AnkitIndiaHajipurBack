package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;

public interface SubgroupmasterRepository extends JpaRepository<Subgroupmaster, Long> {
	
	////////////// New ///////////////////////
	
	@Query(value = "select MAX(RIGHT(subgroupcode,3)) from Subgroupmaster where subgroupcode like %:groupcode% and branchcode =:branchcode and subgroupcode !=:groupcode",nativeQuery = true)
	String getMaxSubGroup(@Param("groupcode") String groupcode,@Param("branchcode") String branchcode);
	
	@Query("select s from Subgroupmaster s where s.subgroupcode =:subgroupcode and s.branchcode =:branchcode ")
	Subgroupmaster getSubGroupDtls(@Param("subgroupcode") String subgroupcode,@Param("branchcode") String branchcode);
	
	@Query("select s from Subgroupmaster s where s.uniqucode =:uniqucode and s.branchcode =:branchcode ")
	Subgroupmaster getSubGroupDtlsByCodeBranch(@Param("uniqucode") String uniqucode,@Param("branchcode") String branchcode);
	
	@Query("select COUNT(parent_subgroupcode) from Subgroupmaster where parent_subgroupcode =:psubgroupcode and branchcode =:branchcode ")
	String getCountSubGroup(@Param("psubgroupcode") String psubgroupcode,@Param("branchcode") String branchcode);
	
	@Query("select s from Subgroupmaster s where s.subgroupcode =:subgroupcode and s.branchcode =:branchcode and s.parent_subgroupcode != :subgroupcode")
	Optional<Subgroupmaster> getParentSubGroup(@Param("subgroupcode") String subgroupcode,@Param("branchcode") String branchcode);
	
	
	/////////// Old ///////////////////////////
	
	@Query("select COUNT(id) from Subgroupmaster")
	String countId();

	@Query( "select s from Subgroupmaster s")
	List<Subgroupmaster> getSubgroupNameList();
	
	@Query( "select subgroupname from Subgroupmaster where subgroupcode like :gr%")
	List<Subgroupmaster> getSubgroupByGr(@Param("gr") String gr);
	
	@Query( "select subgroupcode from Subgroupmaster where subgroupname = :subGrName")
	String getSubgroupId(@Param("subGrName") String subGrName);
	
	@Query( "select s from Subgroupmaster s where s.subgroupcode = :subGrName")
	Subgroupmaster getSubGroupmasterNByC(@Param("subGrName") String subGrName);
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Query("select COUNT(id) from Subgroupmaster")
	String getMaxOfSubGroups();
	
	@Query("select max(substring(uniqucode , 4, length(uniqucode))) from Subgroupmaster where uniqucode like %:prefix% ")
	String getMaxOfSubGroups(@Param("prefix") String prefix);
	
	@Query("select max(substring(subgroupcode , 3, length(subgroupcode))) from Subgroupmaster where subgroupcode !=:groupcode and subgroupcode like %:groupcode% ")
	String getMaxSubGroup2(@Param("groupcode") String groupcode);
	
	@Query(value="select MAX(RIGHT(s.subgroupcode,3)) from subgroupmaster s where s.subgroupcode like %:groupcode% and s.subgroupcode !=:groupcode",nativeQuery=true)
	String getMaxSubGroup(@Param("groupcode") String groupcode);
	
	@Query("select s from Subgroupmaster s where s.subgroupcode =:subgroupcode")
	Subgroupmaster getSubGroupDtls(@Param("subgroupcode") String subgroupcode);
	
	@Query("select s from Subgroupmaster s where s.uniqucode =:uniqucode and s.status !='DELETED'")
	Subgroupmaster getSubGroupDtlsByCodeBranch(@Param("uniqucode") String uniqucode);
	
	@Query("select s from Subgroupmaster s where s.uniqucode =:uniqucode ")
	Subgroupmaster getSubGroupDtlsByCode(@Param("uniqucode") String uniqucode);
	
	@Query("select COUNT(parent_subgroupcode) from Subgroupmaster where parent_subgroupcode =:psubgroupcode ")
	String getCountSubGroup(@Param("psubgroupcode") String psubgroupcode);
	
	@Query(value="select * from subgroupmaster s where s.subgroupcode =:subgroupcode and s.parent_subgroupcode !=s.subgroupcode",nativeQuery=true)
	Optional<Subgroupmaster> getParentSubGroup(@Param("subgroupcode") String subgroupcode);
	
	@Query("select s from Subgroupmaster s where s.status !='DELETED' ")
	List<Subgroupmaster> getSubgroups();
	
	@Query("select s from Subgroupmaster s where s.status !='DELETED' and s.subgroupcode =:sgrId")
	Subgroupmaster getGrpThrSgp(@Param("sgrId") String sgrId);
	
	@Query("select s from Subgroupmaster s where s.status !='DELETED' and length(s.subgroupcode)=:len")
	List<Subgroupmaster> getGrpFrmSgpLen(@Param("len") int len);
	
	@Query( "select s.subgroupname from Subgroupmaster s where s.subgroupcode = :subGrName and s.status !='DELETED'")
	String getSubGrpMasterNByC(@Param("subGrName") String subGrName);
	
}
