package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Broker_templt;
import com.AnkitIndia.jwtauthentication.repository.Broker_templtRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_templtDTO;

@Service
public class Broker_templtService_Imp implements Broker_templtService{

	@Autowired
	Broker_templtRepository broker_templtRepository;
	
	@Transactional
	public Broker_templt save(Broker_templt broker_templt)
	{
		
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(broker_templtRepository.countId() != null ) {
			slno=Long.parseLong(broker_templtRepository.countId());
		}
		String prefix="CP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		broker_templt.setTemplt_code(gen_sno);
		
		broker_templt.setFin_year("2019-2020");
		broker_templt.setModified_type("INSERTED");
		broker_templt.setInserted_by("xxxx");
		broker_templt.setInserted_on(ldt);
		broker_templt.setUpdated_by("NA");
		broker_templt.setUpdated_on(ldt);
		broker_templt.setDeleted_by("NA");
		broker_templt.setDeleted_on(ldt);
		
		return broker_templtRepository.save(broker_templt);
	}
	
	public List<Broker_templt> findAll()
	{
		
		  List<Broker_templt> arrli = broker_templtRepository.findAll();
		  System.out.println("listsize: "+arrli.size());
		return broker_templtRepository.findAll();
	}
}
