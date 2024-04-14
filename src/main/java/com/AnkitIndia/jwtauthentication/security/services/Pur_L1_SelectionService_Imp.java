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
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_BPartner_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Service_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection;
import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection_Dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.repository.Pur_EnquiryRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_L1_SelectionRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_L1_Selection_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
@Service
public class Pur_L1_SelectionService_Imp implements Pur_L1_SelectionService {

	@Autowired
	Pur_L1_SelectionRepository pur_L1_SelectionRepository;
	
	@Transactional
	public Pur_L1_Selection save(Pur_L1_Selection plsl)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_L1_SelectionRepository.countId() != null ) {
			slno=Long.parseLong(pur_L1_SelectionRepository.countId());
		}
		String prefix="PLS";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		plsl.setLsel_id(gen_sno);
		
		//System.err.println("Company : "+pur_L1_SelectionRepository.getCompNameToCompCode(plsl.getCompany_id()));
		
		//plsl.setCompany_id(pur_L1_SelectionRepository.getCompNameToCompCode(plsl.getCompany_id()));
		plsl.setCompany_id("xxxx");
		plsl.setFin_year("2019-2020");
		plsl.setModified_type("INSERTED");
		plsl.setInserted_by("xxxx");
		plsl.setInserted_on(ldt);
		plsl.setUpdated_by("NA");
		plsl.setUpdated_on(ldt);
		plsl.setDeleted_by("NA");
		plsl.setDeleted_on(ldt);
		
		
		Set<Pur_L1_Selection_Dtls> plselSet = new HashSet<Pur_L1_Selection_Dtls>();
		plselSet.addAll(plsl.getPur_L1_Selection_Dtls());
		for(Pur_L1_Selection_Dtls plsldtls : plselSet)
		{
			plsldtls.setLsel_id(gen_sno);
			//plsldtls.setCompany_id(pur_L1_SelectionRepository.getCompNameToCompCode(plsldtls.getCompany_id()));
			plsldtls.setCompany_id("xxxx");
			plsldtls.setFin_year("2019-2020");
			plsldtls.setModified_type("INSERTED");
			plsldtls.setInserted_by("xxxx");
			plsldtls.setInserted_on(ldt);
			plsldtls.setUpdated_by("NA");
			plsldtls.setUpdated_on(ldt);
			plsldtls.setDeleted_by("NA");
			plsldtls.setDeleted_on(ldt);
		
		}
		plsl.setPur_L1_Selection_Dtls(plselSet);
		
		return pur_L1_SelectionRepository.save(plsl);
	}

	public List<Pur_L1_Selection> findAll()
	{
		return pur_L1_SelectionRepository.findAll();
	}
	
	public Pur_L1_Selection findOne(long id)
	{
		Optional<Pur_L1_Selection> op=this.pur_L1_SelectionRepository.findById(id);
		return op.get();
	}
	
	 public List<Pur_L1_Selection_DtlsDTO> l1DtlsRetriveList(String code)
		{
			List<Pur_L1_Selection> modelList=new ArrayList<Pur_L1_Selection>();
			
			modelList.addAll(pur_L1_SelectionRepository.l1DtlsRetriveList(code));
				
			Type listType=new TypeToken<List<Pur_L1_Selection_DtlsDTO>>() {}.getType();
			
			List<Pur_L1_Selection_DtlsDTO> l1Dtls=new ModelMapper().map(modelList,listType);
			
			return l1Dtls;
		}
}