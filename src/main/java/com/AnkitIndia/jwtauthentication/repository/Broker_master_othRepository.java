package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master_oth;

public interface Broker_master_othRepository extends JpaRepository<Broker_master_oth, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_oth w SET w.modified_type =:mstatus WHERE w.broker_Id = :broker_Id")
    int broker_master_othupdate(@Param("broker_Id") String broker_Id,@Param("mstatus") String mstatus);
	
	@Query( "select b from Broker_master_oth b where b.broker_Id =:code and b.modified_type ='INSERTED'")
	List<Broker_master_oth> brokerOthPartnerRetriveList(@Param("code") String code);

}
