package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Broker_master_doc;

public interface Broker_master_docRepository extends JpaRepository<Broker_master_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Broker_master_doc w SET w.modified_type =:mstatus WHERE w.broker_Id = :broker_Id")
    int broker_master_docupdate(@Param("broker_Id") String broker_Id,@Param("mstatus") String mstatus);

}
