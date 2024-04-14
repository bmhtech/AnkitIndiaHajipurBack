package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;


public interface Channel_customer_masterRepository extends JpaRepository<Channel_customer_master, Long> {
	
	
	@Query("SELECT MAX(SUBSTRING(channel_custid , 4 , length(channel_custid))) FROM Channel_customer_master where channel_custid like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	
	@Query( "select c from Channel_customer_master c where c.modified_type != 'DELETED' and c.channel_custid =:channelid ")
	Channel_customer_master getChannelCustId(@Param("channelid") String channelid);
	
	@Query( "select c from Channel_customer_master c where c.modified_type != 'DELETED' ")
	List<Channel_customer_master> getChannelCustDesc();
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkChennelCustomerMasterUsage(@Param("code") String code);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Channel_customer_master c SET c.terminate=1 WHERE c.id=:id" )
	int updateChannelTerminate(@Param("id") long id);
	

	@Query( value="select * from channel_customer_master c where c.modified_type != 'DELETED' and c.terminate = 0", nativeQuery=true)
	 List<Map<String, Object>> getChannelCustFastApi();
	
	@Query( value="select * from channel_customer_master c where c.modified_type != 'DELETED' and c.terminate = 0 and c.channeltype='Sale'", nativeQuery=true)
	 List<Map<String, Object>> getChannelCustForSales();
	
	@Query( "select c from Channel_customer_master c where c.modified_type != 'DELETED' and c.terminate = 0 ")
	List<Channel_customer_master> getChannelCustDescNew();
}
