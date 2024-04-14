package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Channel_partner_details;

public interface Channel_partner_detailsRepository extends JpaRepository<Channel_partner_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Channel_partner_details w SET w.modified_type ='UPDATED' WHERE w.channel_id = :channel_id")
    int updateChannel_partner_details(@Param("channel_id") String channel_id);

}
