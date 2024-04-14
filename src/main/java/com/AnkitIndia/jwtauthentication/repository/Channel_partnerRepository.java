package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Channel_partner;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;

public interface Channel_partnerRepository extends JpaRepository<Channel_partner, Long> {
	
	@Query("select COUNT(id) from Channel_partner")
	String countId();
	
	@Query( "select c from Channel_partner c where c.channel_active = :sts and c.modified_type != 'DELETED'")
	List<Channel_partner> channelPartnerNameList(@Param("sts") Boolean sts);

	@Query( "select c from Channel_partner_details c where c.channel_id = :cp_id")
	List<Channel_partner> channelPRetriveList(@Param("cp_id") String cp_id);
}
