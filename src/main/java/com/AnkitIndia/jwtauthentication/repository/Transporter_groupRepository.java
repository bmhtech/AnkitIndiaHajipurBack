package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Transporter_group;

public interface Transporter_groupRepository extends JpaRepository<Transporter_group, Long>{

	@Query("SELECT MAX(SUBSTRING(bp_trans_id , 3 , length(bp_trans_id))) FROM Transporter_group where bp_trans_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(bp_trans_code , 8, length(bp_trans_code))) from Transporter_group where bp_trans_code like %:code% and company_id =:company ")
	String getTgrpSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("select t from Transporter_group t where t.modified_type != 'DELETED' ORDER BY t.bp_trans_id DESC")
	List<Transporter_group> findAllTransporterGroups();
	
	@Query("select t from Transporter_group t where t.bp_trans_active = :sts and t.modified_type != 'DELETED'")
	List<Transporter_group> getTransporterGroupList(@Param("sts") Boolean sts);
	
	@Query("select t from Transporter_group t where t.bp_trans_id = :code ")
	Transporter_group getTransParentGroup(@Param("code") String code);
	
	@Query("select i.bp_grp_name from Transporter_group i where i.modified_type != 'DELETED' and i.bp_grp_name =:grpname ")
	String chkTransGroupStatus(@Param("grpname") String grpname);
	
	@Query("select i from Transporter_group i where i.modified_type != 'DELETED' and i.bp_trans_code =:code")
	Optional<Transporter_group> chkTransporterGrpCodeStatus(@Param("code") String code);
	
	@Query( "select DISTINCT g.parent_group from Transporter_group g where g.bp_trans_id !=:code and g.parent_group =:code and g.modified_type != 'DELETED'")
	Optional<String> chkTransGroup(@Param("code") String code);
}
