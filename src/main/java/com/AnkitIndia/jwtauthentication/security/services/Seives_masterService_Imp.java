package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Seives_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Seives_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Seives_masterService_Imp implements Seives_masterService{

	@Autowired
	Seives_masterRepository seives_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Seives_DtlsRepository seives_DtlsRepository;
	
	
	public List<Seives_master> getSeiveslist()
	{
		List<Seives_master> list = new ArrayList<Seives_master>();
		list.addAll(seives_masterRepository.getSeivesMasterlist());
		return list;
	}
		
	@Transactional
	public Seives_master save(Seives_master seives_master) 
	{

		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(seives_masterRepository.countId() != null ) {
			slno=Long.parseLong(seives_masterRepository.countId());
		}
		String prefix="SEI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		seives_master.setSeivesid(gen_sno);
		seives_master.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(seives_master.getBusiness_unit()).getBusinessunit_name());
		//System.out.println("item data::"+granulation.getItemid());
		
		if(Utility.isNullOrEmpty(seives_master.getItemid()))
		{
			seives_master.setItem_name(item_masterRepository.getItemDetailsThruItemId(seives_master.getItemid()).getItem_name());
		}
		else 
		{
			seives_master.setItem_name("");
		}

		seives_master.setCompany_id(seives_master.getCompany_id());
		seives_master.setFin_year(seives_master.getFin_year());
		seives_master.setModified_type("INSERTED");
		seives_master.setInserted_on(ldt);
		seives_master.setInserted_by(userRepository.getUserDetails(seives_master.getUsername()).getName());
		seives_master.setUpdated_by(seives_master.getUpdated_by());
		seives_master.setUpdated_on(ldt);
		seives_master.setDeleted_by("NA");
		seives_master.setDeleted_on(ldt);
		
		
		Set<Seives_Dtls> seives_master_Dtls=new HashSet<Seives_Dtls>();
		seives_master_Dtls.addAll(seives_master.getSeives_Dtls());
		for(Seives_Dtls sDetails:seives_master_Dtls) 
		{
			System.out.println("item data::"+sDetails.getSeives_name());
			sDetails.setSeivesid(gen_sno);
			sDetails.setItemid(seives_master.getItemid());
			sDetails.setCompany_id(seives_master.getCompany_id());
			sDetails.setFin_year(seives_master.getFin_year());
			sDetails.setModified_type("INSERTED");
			sDetails.setInserted_by(seives_master.getInserted_by());
			sDetails.setInserted_on(ldt);
			sDetails.setUpdated_by("NA");
			sDetails.setUpdated_on(ldt);
			sDetails.setDeleted_by("NA");
			sDetails.setDeleted_on(ldt);
			sDetails.setUsername(seives_master.getUsername());
		}
		seives_master.setSeives_Dtls(seives_master_Dtls);
		
		return seives_masterRepository.save(seives_master);	
	}
	
	@Transactional
	public Seives_master update(Seives_master seives_master,long id) 
	{

		Optional<Seives_master> op =seives_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		seives_master.setBusiness_unitname(companyBusinessUnitMasterRepository.businessunitbyid(seives_master.getBusiness_unit()).getBusinessunit_name());
		//System.out.println("item data::"+granulation.getItemid());
		
		if(Utility.isNullOrEmpty(seives_master.getItemid()))
		{
			seives_master.setItem_name(item_masterRepository.getItemDetailsThruItemId(seives_master.getItemid()).getItem_name());
		}
		else 
		{
			seives_master.setItem_name("");
		}
		
		seives_master.setSeivesid(op.get().getSeivesid());
		seives_master.setCompany_id(seives_master.getCompany_id());
		seives_master.setFin_year(seives_master.getFin_year());
		seives_master.setModified_type("INSERTED");
		seives_master.setInserted_on(ldt);
		seives_master.setInserted_by(userRepository.getUserDetails(seives_master.getUsername()).getName());
		seives_master.setUpdated_by(seives_master.getUpdated_by());
		seives_master.setUpdated_on(ldt);
		seives_master.setDeleted_by("NA");
		seives_master.setDeleted_on(ldt);
		
	
		seives_DtlsRepository.revertSeives_Dtls(op.get().getSeivesid());
		
		Set<Seives_Dtls> seives_master_Dtls=new HashSet<Seives_Dtls>();
		seives_master_Dtls.addAll(seives_master.getSeives_Dtls());
		for(Seives_Dtls sDetails:seives_master_Dtls) 
		{
			
			
			sDetails.setSeivesid(op.get().getSeivesid());
			sDetails.setItemid(seives_master.getItemid());
			sDetails.setCompany_id(seives_master.getCompany_id());
			sDetails.setFin_year(seives_master.getFin_year());
			sDetails.setModified_type("INSERTED");
			sDetails.setInserted_by(seives_master.getInserted_by());
			sDetails.setInserted_on(ldt);
			sDetails.setUpdated_by(userRepository.getUserDetails(seives_master.getUsername()).getName());
			sDetails.setUpdated_on(ldt);
			sDetails.setDeleted_by("NA");
			sDetails.setDeleted_on(ldt);
			sDetails.setUsername(seives_master.getUsername());
		}
		seives_master.setSeives_Dtls(seives_master_Dtls);
		
		
		return seives_masterRepository.save(seives_master);
	
	}
	
	@Transactional
	public Seives_master delete(Seives_master seives_master,long id) 
	{

		Optional<Seives_master> op = seives_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Seives_master seives=op.get();
		
		seives.setModified_type("DELETED");
		seives.setInserted_by(op.get().getInserted_by());
		seives.setInserted_on(op.get().getInserted_on());
		seives.setUpdated_by(op.get().getUpdated_by());
		seives.setUpdated_on(op.get().getUpdated_on());
		seives.setDeleted_by(userRepository.getUserDetails(seives.getUsername()).getName());
		seives.setDeleted_on(ldt);
		
		seives_DtlsRepository.deleteSeives_Dtls(op.get().getSeivesid());
		
		
		if(op.isPresent())
		{
			seives.setId(id);
		}
		
			return seives_masterRepository.save(seives);
		
	}
	
	public Seives_master findOne(long id) 
	{
		Optional<Seives_master> op=seives_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Seives_Dtls> retriveSeivesDetails(String seivesid)
	{
		List<Seives_Dtls> details= new ArrayList<Seives_Dtls>();
		details.addAll(seives_DtlsRepository.getSeivesDetailsThruId(seivesid));
		return details;
	}
}
