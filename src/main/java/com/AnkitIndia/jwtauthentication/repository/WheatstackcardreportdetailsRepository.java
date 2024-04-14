package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreportdetails;

public interface WheatstackcardreportdetailsRepository extends JpaRepository<Wheatstackcardreportdetails, Long> {
	
	
	@Query("select  w from Wheatstackcardreportdetails w where w.modified_type = 'INSERTED' and w.wheatstackid =:wheatstackid ORDER BY w.date ASC")
	List<Wheatstackcardreportdetails>getwheatstackdetails(@Param("wheatstackid") String wheatstackid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatstackcardreportdetails d SET d.modified_type ='UPDATED' WHERE d.wheatstackid =:wheatstackid  and d.modified_type='INSERTED'")
    int updateprevious(@Param("wheatstackid") String wheatstackid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatstackcardreportdetails d SET d.modified_type ='DELETED' WHERE d.wheatstackid =:wheatstackid  and d.modified_type='INSERTED'")
    int deleteprevious(@Param("wheatstackid") String wheatstackid);
	
}
