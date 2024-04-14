package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_group;

public interface Cust_groupRepository extends JpaRepository<Cust_group, Long>{
	
	@Query("SELECT MAX(SUBSTRING(cp_code , 3 , length(cp_code))) FROM Cust_group where cp_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(grp_code , 8, length(grp_code))) from Cust_group where grp_code like %:code% and company_id =:company ")
	String getCgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select c from Cust_group c where c.modified_type != 'DELETED' ORDER BY c.id DESC")
	List<Cust_group> findAllCustGroups();
	
	@Query("select bus_part_name from Cust_group where bus_part_active = :sts")
	List<Cust_group> getCustomerGroupList(@Param("sts") Boolean sts);
	
	@Query("select c from Cust_group c where c.bus_part_active = :sts and c.modified_type != 'DELETED'")
	List<Cust_group> getCustGroupList(@Param("sts") Boolean sts);
	
	@Query("select i from Cust_group i where i.cp_code = :code and i.modified_type != 'DELETED'")
	Cust_group getCustParentGroup(@Param("code") String code);
	
	@Query("select i.grp_name from Cust_group i where i.modified_type != 'DELETED' and i.grp_name =:grpname ")
	String chkCustGroupStatus(@Param("grpname") String grpname);
	
	@Query("select i from Cust_group i where i.modified_type != 'DELETED' and i.grp_code =:code")
	Optional<Cust_group> chkCustGrpCodeStatus(@Param("code") String code);
	
	@Query( "select DISTINCT g.parent_group from Cust_group g where g.cp_code !=:code and g.parent_group =:code and g.modified_type != 'DELETED'")
	Optional<String> chkCustGroup(@Param("code") String code);

}
