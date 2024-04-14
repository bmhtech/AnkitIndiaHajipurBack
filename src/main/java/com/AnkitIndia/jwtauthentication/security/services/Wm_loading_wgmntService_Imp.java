package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_wgmnt;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_wgmnt_dtls;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_doc_attchDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_wgmnt_dtlsDTO;

@Service
public class Wm_loading_wgmntService_Imp implements Wm_loading_wgmntService{
	
	
	@Autowired
	Wm_loading_wgmntRepository wm_loading_wgmntRepository;
	
	@Transactional
	public Wm_loading_wgmnt save(Wm_loading_wgmnt wm_loading_wgmnt) {
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wm_loading_wgmntRepository.countId() != null ) {
			slno=Long.parseLong(wm_loading_wgmntRepository.countId());
		}
		String prefix="WLW";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		wm_loading_wgmnt.setWgment_id(gen_sno);
		wm_loading_wgmnt.setCompany_id("xxxxx");
		wm_loading_wgmnt.setFin_year("2019-2020");
		wm_loading_wgmnt.setModified_type("INSERTED");
		wm_loading_wgmnt.setInserted_by("xxxx");
		wm_loading_wgmnt.setInserted_on(ldt);
		wm_loading_wgmnt.setUpdated_by("NA");
		wm_loading_wgmnt.setUpdated_on(ldt);
		wm_loading_wgmnt.setDeleted_by("NA");
		wm_loading_wgmnt.setDeleted_on(ldt);
		
		Set<Wm_loading_wgmnt_dtls> wgDtlsSet=new HashSet<Wm_loading_wgmnt_dtls>();
		wgDtlsSet.addAll(wm_loading_wgmnt.getWm_loading_wgmnt_dtls());
		for(Wm_loading_wgmnt_dtls wgDtls:wgDtlsSet) 
		{
			wgDtls.setWgment_id(gen_sno);
			wgDtls.setCompany_id("xxxxx");
			wgDtls.setFin_year("2019-2020");
			wgDtls.setModified_type("INSERTED");
			wgDtls.setInserted_by("xxxx");
			wgDtls.setInserted_on(ldt);
			wgDtls.setUpdated_by("NA");
			wgDtls.setUpdated_on(ldt);
			wgDtls.setDeleted_by("NA");
			wgDtls.setDeleted_on(ldt);
		}
		
		wm_loading_wgmnt.setWm_loading_wgmnt_dtls(wgDtlsSet);
		
		return wm_loading_wgmntRepository.save(wm_loading_wgmnt);
	   }
	
		
		public List<Wm_loading_wgmnt> findAll(){
				return wm_loading_wgmntRepository.findAll();
			}
		public Wm_loading_wgmnt findOne(long id)
		{
			Optional<Wm_loading_wgmnt> wlw=this.wm_loading_wgmntRepository.findById(id);
			return wlw.get();
		}
		
		public List<Wm_loading_wgmnt_dtlsDTO> wmLoadingDtlsRetriveList(String code)
		{
			List<Wm_loading_wgmnt> modelList=new ArrayList<Wm_loading_wgmnt>();
			
			modelList.addAll(wm_loading_wgmntRepository.wmLoadingDtlsRetriveList(code));
				
			Type listType=new TypeToken<List<Wm_loading_wgmnt_dtlsDTO>>() {}.getType();
			
			List<Wm_loading_wgmnt_dtlsDTO> loadingWm=new ModelMapper().map(modelList,listType);
			
			return loadingWm;
		}
		
		
}
