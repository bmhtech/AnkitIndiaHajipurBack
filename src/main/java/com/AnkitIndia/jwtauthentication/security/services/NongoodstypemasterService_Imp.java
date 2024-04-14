package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Nongoodstypemaster;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodstypemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class NongoodstypemasterService_Imp implements NongoodstypemasterService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	NongoodstypemasterRepository nongoodstypemasterRepository;
	
	
	public List<Map<String,Object>> getServiceTypeList()
	{
		List<Map<String, Object>> getServiceTypeList = new ArrayList<Map<String, Object>>();
		getServiceTypeList.addAll(nongoodstypemasterRepository.getServiceTypeList());
		return getServiceTypeList;
	}
	
	@Transactional
	public Nongoodstypemaster save(Nongoodstypemaster nongoodstypemaster) {
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=nongoodstypemasterRepository.counttype();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=nongoodstypemaster.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="NST-"+final_fyear+"-";
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			nongoodstypemaster.setTypeserviceno(gen_sno);
			
		nongoodstypemaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(nongoodstypemaster.getBusiness_unit()).getBusinessunit_name());
		nongoodstypemaster.setTypename(nongoodstypemaster.getTypename());
		nongoodstypemaster.setModified_type("INSERTED");
		nongoodstypemaster.setInserted_by(userRepository.getUserDetails(nongoodstypemaster.getUsername()).getName());
		nongoodstypemaster.setInserted_on(ldt);
		nongoodstypemaster.setUpdated_by("NA");
		nongoodstypemaster.setUpdated_on(ldt);
		nongoodstypemaster.setDeleted_by("NA");
		nongoodstypemaster.setDeleted_on(ldt);
		
		return nongoodstypemasterRepository.save(nongoodstypemaster);
		
	}
	
	public List<Map<String, Object>> getNonGoodsTypeMasterList(String fin_year){
		List<Map<String, Object>> NonGoodsTypeMasterList = new ArrayList<Map<String, Object>>();
		NonGoodsTypeMasterList.addAll(nongoodstypemasterRepository.getNonGoodsTypeMaster(fin_year));
		return NonGoodsTypeMasterList;
	}
	
	public Nongoodstypemaster findOne(long id)
	{
		Optional<Nongoodstypemaster> data = this.nongoodstypemasterRepository.findById(id);
		return data.get();
	}
	
	@Transactional
	public Nongoodstypemaster update(Nongoodstypemaster nongoodstypemaster, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Nongoodstypemaster> op = nongoodstypemasterRepository.findById(id);
		
		nongoodstypemaster.setTypeserviceno(op.get().getTypeserviceno());
		nongoodstypemaster.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(nongoodstypemaster.getBusiness_unit()).getBusinessunit_name());
		nongoodstypemaster.setTypename(nongoodstypemaster.getTypename());
		nongoodstypemaster.setModified_type("INSERTED");
		nongoodstypemaster.setInserted_by(op.get().getInserted_by());
		nongoodstypemaster.setInserted_on(op.get().getInserted_on());
		nongoodstypemaster.setUpdated_by(userRepository.getUserDetails(nongoodstypemaster.getUsername()).getName());
		nongoodstypemaster.setUpdated_on(ldt);
		nongoodstypemaster.setDeleted_by("NA");
		nongoodstypemaster.setDeleted_on(op.get().getDeleted_on());
		
		return nongoodstypemasterRepository.save(nongoodstypemaster);
	}
	
	@Transactional
	public Nongoodstypemaster delete(Nongoodstypemaster nongoodstypemaster, long id) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Nongoodstypemaster> op = nongoodstypemasterRepository.findById(id);
		
		Nongoodstypemaster ngtm=op.get();
		
		ngtm.setModified_type("DELETED");
		ngtm.setInserted_by(op.get().getInserted_by());
		ngtm.setInserted_on(op.get().getInserted_on());
		ngtm.setUpdated_by(op.get().getUpdated_by());
		ngtm.setUpdated_on(op.get().getUpdated_on());
		ngtm.setDeleted_by(userRepository.getUserDetails(nongoodstypemaster.getUsername()).getName());
		ngtm.setDeleted_on(ldt);
		
		return nongoodstypemasterRepository.save(ngtm);
	}
	
}
