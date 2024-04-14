package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master_vdr;

public interface Broker_master_vdrRepository extends JpaRepository<Broker_master_vdr, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_vdr w SET w.modified_type =:mstatus WHERE w.broker_Id = :broker_Id")
    int broker_master_vdrupdate(@Param("broker_Id") String broker_Id,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_vdr w SET w.modified_type ='DELETED' WHERE w.vdr_code_name = :vdr_code_name AND w.modified_type ='INSERTED' ")
    int broker_master_vdrupdateSUPNAME(@Param("vdr_code_name") String vdr_code_name);
	
	@Query( "select b from Broker_master_vdr b where b.modified_type ='INSERTED' and b.broker_Id =:code ")
	List<Broker_master_vdr> getBrokerMasterVdr(@Param("code") String code);
	
	@Query(value="select * from broker_master_vdr b where b.modified_type ='INSERTED' and b.broker_Id =:code ",nativeQuery=true)
	List<Map<String, Object>>getBrokerMasterVdrnew(@Param("code") String code);
}
