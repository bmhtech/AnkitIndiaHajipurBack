package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Bingroup;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_master;
import com.AnkitIndia.jwtauthentication.repository.BingroupRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Seives_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class BingroupService_Imp implements BingroupService{
	@Autowired
	BingroupRepository bingroupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	
	public List<Bingroup> getBingrouplist()
	{
		List<Bingroup> list = new ArrayList<Bingroup>();
		list.addAll(bingroupRepository.getBinGrouplist());
		return list;
	}
		
	@Transactional
	public Bingroup save(Bingroup bingroup) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(bingroupRepository.countId() != null ) {
			slno=Long.parseLong(bingroupRepository.countId());
		}
		String prefix="BGM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		bingroup.setBingroupid(gen_sno);
		bingroup.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(bingroup.getBusiness_unit()).getBusinessunit_name());

		bingroup.setCompany_id(bingroup.getCompany_id());
		bingroup.setFin_year(bingroup.getFin_year());
		bingroup.setModified_type("INSERTED");
		bingroup.setInserted_on(ldt);
		bingroup.setInserted_by(userRepository.getUserDetails(bingroup.getUsername()).getName());
		bingroup.setUpdated_by(bingroup.getUpdated_by());
		bingroup.setUpdated_on(ldt);
		bingroup.setDeleted_by("NA");
		bingroup.setDeleted_on(ldt);
		
		return bingroupRepository.save(bingroup);	
	}
	
	@Transactional
	public Bingroup update(Bingroup bingroup,long id) 
	{

		Optional<Bingroup> op =bingroupRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		bingroup.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(bingroup.getBusiness_unit()).getBusinessunit_name());
		
		bingroup.setBingroupid(op.get().getBingroupid());
		bingroup.setCompany_id(bingroup.getCompany_id());
		bingroup.setFin_year(bingroup.getFin_year());
		bingroup.setModified_type("INSERTED");
		bingroup.setInserted_on(ldt);
		bingroup.setInserted_by(userRepository.getUserDetails(bingroup.getUsername()).getName());
		bingroup.setUpdated_by(bingroup.getUpdated_by());
		bingroup.setUpdated_on(ldt);
		bingroup.setDeleted_by("NA");
		bingroup.setDeleted_on(ldt);
		
		return bingroupRepository.save(bingroup);
	
	}
	
	@Transactional
	public Bingroup delete(Bingroup bingroup,long id) 
	{
		Optional<Bingroup> op = bingroupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Bingroup bin=op.get();
		
		bin.setModified_type("DELETED");
		bin.setInserted_by(op.get().getInserted_by());
		bin.setInserted_on(op.get().getInserted_on());
		bin.setUpdated_by(op.get().getUpdated_by());
		bin.setUpdated_on(op.get().getUpdated_on());
		bin.setDeleted_by(userRepository.getUserDetails(bin.getUsername()).getName());
		bin.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			bin.setId(id);
		}
		
			return bingroupRepository.save(bin);
		
	}
	
	public Bingroup findOne(long id) 
	{
		Optional<Bingroup> op=bingroupRepository.findById(id);
		return op.get();
	}
	

}
