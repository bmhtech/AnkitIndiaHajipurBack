package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master;

public interface Broker_masterRepository extends JpaRepository<Broker_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(broker_Id , 3 , length(broker_Id))) FROM Broker_master where broker_Id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(broker_code , 11, length(broker_code))) from Broker_master where broker_code like %:code% and company_id =:company and broker_type =:wtype")
	String getBrokerSequenceId(@Param("code") String code,@Param("company") String company,@Param("wtype") String wtype);
	
	@Query( "select b from Broker_master b where b.modified_type ='INSERTED' and b.broker_active =:status ")
	List<Broker_master> brokerMNCList(@Param("status") boolean status);
	
	@Query( "select b from Broker_master b where b.modified_type ='INSERTED' ")
	List<Broker_master> getbrokerMsNameList();
	
	@Query( "select b from Broker_master b where b.modified_type ='INSERTED' ORDER BY b.broker_Id DESC")
	List<Broker_master> findAllBrokers();
	
	@Query("SELECT b FROM Broker_master b WHERE CONCAT(b.broker_code, b.name, b.broker_type, b.sub_group_type, b.group_type_name, b.alt_name) LIKE %?1% and b.modified_type ='INSERTED' ORDER BY b.broker_Id DESC")
    public List<Broker_master> findBrokers(String keyword);
	
	@Query( "select b from Broker_master b where b.modified_type ='INSERTED' and b.broker_Id =:code ")
	Broker_master brokerNameByCode(@Param("code") String code);
	
	@Query( "select b from Broker_master b where b.modified_type ='INSERTED' and b.id =:id ")
	Broker_master getBrokerMaster(@Param("id") Long id);
	
	
	@Query( "select b from Broker_master_doc b where b.broker_Id =:code and b.modified_type ='INSERTED'")
	List<Broker_master> brokerDocRetriveList(@Param("code") String code);
	
	@Query("select b.name from Broker_master b where b.modified_type = 'INSERTED' and b.name =:bname")
	String chkBrokerNameStatus(@Param("bname") String bname);
	
	@Query("select i from Broker_master i where i.modified_type = 'INSERTED' and i.broker_code =:code")
	Optional<Broker_master> chkBrokerMasterCodeStatus(@Param("code") String code);
	
	@Query("SELECT DISTINCT b.group_type FROM Trans_bussiness_partner b where b.group_type =:group_id and b.modified_type = 'INSERTED'")
    Optional<String> getBrokerDtlsThruGroup(@Param("group_id") String group_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_vdr w SET w.modified_type =:mstatus WHERE w.vdr_code_name = :bp_id")
    int suppliertobrokerupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query(value = "{call checkBrokerMasterUsage(:code)}", nativeQuery = true)
	String checkBrokerMasterUsage(@Param("code") String code);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Broker_master p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Broker_master p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	@Query( value="select id as id,broker_id as broker_Id,name as name,broker_code as broker_code,broker_type as broker_type,group_type_name as group_type_name,trans_curr as trans_curr,username as username,export as export from broker_master where modified_type = 'INSERTED' and broker_active =1 and company_id =:company_id ",nativeQuery=true)
	List<Map<String,Object>> getBrokersFastApi(@Param("company_id") String company_id);
	
	@Query( value="select * from broker_master where modified_type ='INSERTED' and broker_active =:status ",nativeQuery=true)
	List<Map<String,Object>> brokerNameListFast(@Param("status") boolean status);
}
