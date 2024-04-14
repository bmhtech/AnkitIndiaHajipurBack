package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master_statutory;

public interface Broker_master_statutoryRepository extends JpaRepository<Broker_master_statutory, Long>{

	@Query( "select b from Broker_master_statutory b where b.broker_Id = :code and b.modified_type ='INSERTED'" )
	Broker_master_statutory brokerStatutoryRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_statutory w SET w.modified_type =:mstatus WHERE w.broker_Id = :broker_Id")
    int broker_master_statutoryupdate(@Param("broker_Id") String broker_Id,@Param("mstatus") String mstatus);
}
