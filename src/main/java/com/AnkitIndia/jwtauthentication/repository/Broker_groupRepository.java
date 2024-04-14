package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_group;

public interface Broker_groupRepository extends JpaRepository<Broker_group, Long>{
	
	@Query("SELECT MAX(SUBSTRING(group_id , 3 , length(group_id))) FROM Broker_group where group_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(group_code , 8, length(group_code))) from Broker_group where group_code like %:code% and company_id =:company ")
	String getBgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select b from Broker_group b where b.modified_type != 'DELETED'and b.company_id =:company ORDER BY b.group_id DESC")
	List<Broker_group> findAllBrokerGroups( @Param("company") String company);
	
	@Query("select b from Broker_group b where b.bg_active = :sts and b.modified_type != 'DELETED' and b.company_id =:company")
	List<Broker_group> getBrokerGroupList(@Param("sts") Boolean sts, @Param("company")String company);
	

	@Query("select b from Broker_group b where b.bg_active = :sts and b.modified_type != 'DELETED'")
	List<Broker_group> getBrokerGroupList(@Param("sts") Boolean st);

	
	@Query("select b from Broker_group b where b.group_id = :code and b.company_id =:company")
	Broker_group getBroParentGroup(@Param("code") String code, @Param("company")String company);
	
	@Query("select i.group_name from Broker_group i where i.modified_type != 'DELETED' and i.group_name =:grpname and i.company_id =:company")
	String chkBrokerGroupStatus(@Param("grpname") String grpname, @Param("company") String company);
	
	@Query("select i from Broker_group i where i.modified_type != 'DELETED' and i.group_code =:code and i.company_id =:company")
	Optional<Broker_group> chkBrokerGrpCodeStatus(@Param("code") String code, @Param("company") String company);
	
	@Query( "select DISTINCT g.parent_group from Broker_group g where g.group_id !=:code and g.parent_group =:code and g.modified_type != 'DELETED'")
	Optional<String> chkBrokerGroup(@Param("code") String code);

}
