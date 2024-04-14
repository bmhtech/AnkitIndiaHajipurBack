package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master_add;


public interface Broker_master_addRepository extends JpaRepository<Broker_master_add, Long>{
	
	/*@Query( "select b from Broker_master_add b where b.modified_type ='INSERTED' and b.broker_Id =:code ")
	Broker_master getBrokerMasterAddr(@Param("code") String code);*/
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_add w SET w.modified_type =:mstatus WHERE w.broker_Id = :broker_Id")
    int broker_master_addupdate(@Param("broker_Id") String broker_Id,@Param("mstatus") String mstatus);
	
	@Query( "select b from Broker_master_add b where b.modified_type ='INSERTED' and b.broker_Id =:code ")
	List<Broker_master_add> getBrokerMasterAddr(@Param("code") String code);
	
	@Query( "select b from Broker_master_add b where b.modified_type ='INSERTED' and b.broker_Id =:code ")
	Broker_master_add getBrokerMasterAddress(@Param("code") String code);

}
