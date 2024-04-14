package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supplier_group;

public interface Supplier_groupRepository extends JpaRepository<Supplier_group, Long>{
	
	
	@Query("SELECT MAX(SUBSTRING(bp_group_id , 3 , length(bp_group_id))) FROM Supplier_group where bp_group_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(bp_group_code , 8, length(bp_group_code))) from Supplier_group where bp_group_code like %:code% and company_id =:company ")
	String getSgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select i from Supplier_group i where i.modified_type != 'DELETED' ORDER BY i.bp_group_id DESC ")
	List<Supplier_group> findAllSupplierGroups();
	
	@Query("select i from Supplier_group i where i.bp_grp_active = :sts and i.modified_type != 'DELETED'")
	List<Supplier_group> getSupplierGroupList(@Param("sts") Boolean sts);
	
	@Query("select i from Supplier_group i where i.bp_group_id = :code and i.modified_type != 'DELETED' ")
	Supplier_group getSuppParentGroup(@Param("code") String code);
	
	@Query("select COUNT(i.id) from Supplier_group i ")
	String supplierGroupId();
	
	@Query("select i.bp_grp_name from Supplier_group i where i.modified_type != 'DELETED' and i.bp_grp_name =:grpname ")
	String chkSuppGroupStatus(@Param("grpname") String grpname);
	
	@Query("select i from Supplier_group i where i.modified_type != 'DELETED' and i.bp_group_code =:code")
	Optional<Supplier_group> chkSupplierGrpCodeStatus(@Param("code") String code);
	
	@Query( "select DISTINCT g.parent_group from Supplier_group g where g.bp_group_id !=:code and g.parent_group =:code and g.modified_type != 'DELETED'")
	Optional<String> chkSuppGroup(@Param("code") String code);
}
