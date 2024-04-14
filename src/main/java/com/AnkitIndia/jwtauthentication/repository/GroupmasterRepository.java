package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Groupmaster;

public interface GroupmasterRepository extends JpaRepository<Groupmaster, Long>{
	///////////////// New //////////////////////
	
	@Query("select COUNT(groupcode) from Groupmaster where groupcode =:groupcode and branchcode =:branchcode")
	String getNoOfGroups(@Param("groupcode") String groupcode,@Param("branchcode") String branchcode);
	
	@Query("select g from Groupmaster g where g.groupcode =:groupcode and g.branchcode =:branchcode")
	Groupmaster getGroups(@Param("branchcode") String branchcode,@Param("groupcode") String groupcode);
    
    @Query("select COUNT(id) from Groupmaster")
	String getMaxOfGroups();
    
    @Query("select max(substring(uniqucode , 4, length(uniqucode))) from Groupmaster where uniqucode like %:prefix% ")
	String getMaxOfGroups(@Param("prefix") String prefix);

    @Query("select g from Groupmaster g where g.uniqucode =:groupname and g.branchcode =:branchcode")
    Groupmaster getGroupDetails(@Param("groupname") String groupname,@Param("branchcode") String branchcode);
    
    @Query("select g from Groupmaster g where g.groupname =:groupname ")
    Groupmaster getGroupDetailsByGName(@Param("groupname") String groupname);
    
    @Query("select g from Groupmaster g ")
    List<Groupmaster> getGroupmasters();
    
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Groupmaster g SET g.subgroupserialno =:subgrpslno WHERE g.groupcode =:groupcode AND g.branchcode =:branchcode")
    int updateGroupmaster(@Param("subgrpslno") String subgrpslno,@Param("groupcode") String groupcode,@Param("branchcode") String branchcode);
    
	
	//////////////////// Old //////////////////////////////////////
	
	@Query( "select g from Groupmaster g")
	List<Groupmaster> getGroup();
	
	@Query( "select groupcode from Groupmaster where groupname = :code")
	String gname(@Param("code") String code);
	
	@Query( "select g from Groupmaster g where g.groupcode = :code")
	Groupmaster getGroupmasterNByCList(@Param("code") String code);
	
	@Query( "select g.groupname from Groupmaster g where g.groupcode = :code and g.status !='DELETED' ")
	String getGrpMasterNByCList(@Param("code") String code);
	
}
