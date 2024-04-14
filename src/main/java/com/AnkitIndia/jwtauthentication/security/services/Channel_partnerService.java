package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Channel_partner;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;

public interface Channel_partnerService {
	
	public Channel_partner saveChannelPartner(Channel_partner chPartner);
	
	public Channel_partner update(Channel_partner chPartner,long id);
	
	public List<Channel_partner> findAllcPartners();
	
	public Channel_partner findOne(long id);
	
	public List<Channel_partner_detailsDTO> channelPRetriveList(String cp_id);
	
	public List<Channel_partnerDTO> ChannelPartnerNCList();

}
