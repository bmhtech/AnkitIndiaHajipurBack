package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;

public interface Trans_bussiness_partnerRepository extends JpaRepository<Trans_bussiness_partner, Long> {
	
	@Query("SELECT MAX(SUBSTRING(bp_Id ,4  , length(bp_Id))) FROM Trans_bussiness_partner where bp_Id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(bp_code , 8, length(bp_code))) from Trans_bussiness_partner where bp_code like %:code% and company_id =:company ")
	String getTransSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query( "select t from Trans_bussiness_partner t where t.modified_type = 'INSERTED' ORDER BY t.bp_Id DESC ")
	List<Trans_bussiness_partner> findAllTransporters();
	
	@Query("SELECT t FROM Trans_bussiness_partner t WHERE CONCAT(t.bp_code, t.bp_name, t.bp_type, t.sub_group_type, t.group_type_name, t.alt_name) LIKE %?1% and t.modified_type = 'INSERTED' ORDER BY t.bp_Id DESC")
    public List<Trans_bussiness_partner> findTransporters(String keyword);
	
	@Query( "select t from Trans_bussiness_partner t where t.modified_type = 'INSERTED' and t.bp_active =:status ")
	List<Trans_bussiness_partner> getTransporterMNCList(@Param("status") boolean status);
	
	@Query(value="select bp_id,bp_name from trans_bussiness_partner where modified_type = 'INSERTED' and bp_active =:status ", nativeQuery = true)
	List<Map<String, Object>> getTransporterMNCListFast(@Param("status") boolean status);
	
	@Query( "select t from Trans_bussiness_partner t where t.modified_type = 'INSERTED' and t.bp_Id =:transid ")
	List<Trans_bussiness_partner> getTransporterThruCustomer(@Param("transid") String transid);
	
	@Query( "select t from Trans_bussiness_partner t where t.modified_type = 'INSERTED' and t.bp_Id =:transid ")
	List<Trans_bussiness_partner> getTransporterThruSupplier(@Param("transid") String transid);
	
	@Query( "select t from Trans_bussiness_partner t where t.modified_type = 'INSERTED' and t.group_type =:tgid ")
	List<Trans_bussiness_partner> getTransporterThruGroup(@Param("tgid") String tgid);
	
	@Query( "select t from Trans_bussiness_partner t where t.bp_code = :code and t.modified_type = 'INSERTED'")
	Trans_bussiness_partner bpNameByCode(@Param("code") String code);
	
	@Query( "select t from Trans_bussiness_partner t where t.bp_Id = :code and t.modified_type = 'INSERTED'")
	Trans_bussiness_partner bpNameById(@Param("code") String code);
	
	
	@Query("select t.bp_name from Trans_bussiness_partner t where t.modified_type = 'INSERTED' and t.bp_name =:tname")
	String chkTransNameStatus(@Param("tname") String tname);
	
	@Query("select i from Trans_bussiness_partner i where i.modified_type = 'INSERTED' and i.bp_code =:code")
	Optional<Trans_bussiness_partner> chkTransBpCodeStatus(@Param("code") String code);
	
	@Query("SELECT DISTINCT t.group_type FROM Trans_bussiness_partner t where t.group_type =:group_id and t.modified_type = 'INSERTED'")
    Optional<String> getTransDtlsThruGroup(@Param("group_id") String group_id);
	
	@Query(value = "{call checkTransporterMasterUsage(:code)}", nativeQuery = true)
	String checkTransporterMasterUsage(@Param("code") String code);
	
	@Query(value="select id,bp_id,bp_name,bp_code,group_type_name from trans_bussiness_partner where modified_type = 'INSERTED' and bp_active =:status ", nativeQuery = true)
	List<Map<String, Object>> getTransporterBussinessPartnerFast(@Param("status") boolean status);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Trans_bussiness_partner p SET p.export=:export,p.response=:response WHERE p.id=:id and p.modified_type ='INSERTED'" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	
	
}