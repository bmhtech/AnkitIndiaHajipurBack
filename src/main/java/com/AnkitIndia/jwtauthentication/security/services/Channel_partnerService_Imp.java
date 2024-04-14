package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Channel_partner;
import com.AnkitIndia.jwtauthentication.model.Channel_partner_details;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.repository.Channel_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Channel_partner_detailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;

@Service
@Repository
public class Channel_partnerService_Imp implements Channel_partnerService {
	
	@Autowired
	Channel_partnerRepository chPartnerRepository;
	
	@Transactional
	public Channel_partner saveChannelPartner(Channel_partner cPartner)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(chPartnerRepository.countId() != null ) {
			slno=Long.parseLong(chPartnerRepository.countId());
		}
		String prefix="CP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		cPartner.setChannel_code(gen_sno);
		cPartner.setChannel_id(gen_sno);
		cPartner.setFin_year("2019-2020");
		cPartner.setModified_type("INSERTED");
		cPartner.setInserted_by("xxxx");
		cPartner.setInserted_on(ldt);
		cPartner.setUpdated_by("NA");
		cPartner.setUpdated_on(ldt);
		cPartner.setDeleted_by("NA");
		cPartner.setDeleted_on(ldt);
		
		
		//Dynamic
		Set<Channel_partner_details> chnlSet = new HashSet<Channel_partner_details>();
		chnlSet.addAll(cPartner.getChannel_partner_details());
		for(Channel_partner_details cpDts : chnlSet)
		{
			cpDts.setChannel_id(gen_sno);
			cpDts.setChannel_code(gen_sno);
			cpDts.setFin_year("2019-2020");
			cpDts.setModified_type("INSERTED");
			cpDts.setInserted_by("xxxx");
			cpDts.setInserted_on(ldt);
			cpDts.setUpdated_by("NA");
			cpDts.setUpdated_on(ldt);
			cpDts.setDeleted_by("NA");
			cpDts.setDeleted_on(ldt);
		
		}
		cPartner.setChannel_partner_details(chnlSet);
		
		return chPartnerRepository.save(cPartner);
	}
	@Autowired
	Channel_partner_detailsRepository channel_partner_detailsRepository;
	
	@Transactional
	public Channel_partner update(Channel_partner cPartner,long id)
	{
		Optional<Channel_partner> op = chPartnerRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		cPartner.setChannel_id(cPartner.getChannel_id());
		cPartner.setFin_year("2019-2020");
		cPartner.setModified_type("INSERTED");
		cPartner.setInserted_by("xxxx");
		cPartner.setInserted_on(ldt);
		cPartner.setUpdated_by("NA");
		cPartner.setUpdated_on(ldt);
		cPartner.setDeleted_by("NA");
		cPartner.setDeleted_on(ldt);
		cPartner.setCompany_id("2020-2021");
		
		if(op.isPresent())
		{
			cPartner.setId(id);
		}
		
		//Dynamic
			channel_partner_detailsRepository.updateChannel_partner_details(cPartner.getChannel_id());
				Set<Channel_partner_details> chnlSet = new HashSet<Channel_partner_details>();
				chnlSet.addAll(cPartner.getChannel_partner_details());
				for(Channel_partner_details cpDts : chnlSet)
				{
					cpDts.setChannel_id(cPartner.getChannel_id());
					cpDts.setFin_year("2019-2020");
					cpDts.setModified_type("INSERTED");
					cpDts.setInserted_by("xxxx");
					cpDts.setInserted_on(ldt);
					cpDts.setUpdated_by("NA");
					cpDts.setUpdated_on(ldt);
					cpDts.setDeleted_by("NA");
					cpDts.setDeleted_on(ldt);
				
				}
				cPartner.setChannel_partner_details(chnlSet);
				

		return chPartnerRepository.save(cPartner);
		
	}
	
	public List<Channel_partner> findAllcPartners()
	{
		return chPartnerRepository.findAll();
	}
	
	public Channel_partner findOne(long id)
	{
		Optional<Channel_partner> op=this.chPartnerRepository.findById(id);
		return op.get();
	}
	
	public List<Channel_partnerDTO> ChannelPartnerNCList()
	{
		List<Channel_partner> modelList= chPartnerRepository.channelPartnerNameList(true);
		Type listType=new TypeToken<List<Channel_partnerDTO>>() {}.getType();
		
		List<Channel_partnerDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public List<Channel_partner_detailsDTO> channelPRetriveList(String cp_id)
	{
		List<Channel_partner> modelList=new ArrayList<Channel_partner>();
		
		modelList.addAll(chPartnerRepository.channelPRetriveList(cp_id));
			
		Type listType=new TypeToken<List<Channel_partner_detailsDTO>>() {}.getType();
		
		List<Channel_partner_detailsDTO> channel=new ModelMapper().map(modelList,listType);
		
		return channel;
	}
}
