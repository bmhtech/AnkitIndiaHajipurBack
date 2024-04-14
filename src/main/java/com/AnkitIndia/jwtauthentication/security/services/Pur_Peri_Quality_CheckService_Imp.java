package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Peri_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Peri_Quality_Check_Details;
import com.AnkitIndia.jwtauthentication.repository.Pur_Peri_Quality_CheckRepository;

@Service
@Repository
public class Pur_Peri_Quality_CheckService_Imp implements Pur_Peri_Quality_CheckService {
	
	@Autowired
	Pur_Peri_Quality_CheckRepository pur_Peri_Quality_CheckRepository;
	
	@Transactional
	public Pur_Peri_Quality_Check save(Pur_Peri_Quality_Check pPeripheral)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_Peri_Quality_CheckRepository.countId() != null ) {
			slno=Long.parseLong(pur_Peri_Quality_CheckRepository.countId());
		}
		String prefix="PQC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		pPeripheral.setPeri_qc_id(gen_sno);
		pPeripheral.setModified_type("INSERTED");
		pPeripheral.setInserted_by("xxxx");
		pPeripheral.setInserted_on(ldt);
		pPeripheral.setUpdated_by("NA");
		pPeripheral.setUpdated_on(ldt);
		pPeripheral.setDeleted_by("NA");
		pPeripheral.setDeleted_on(ldt);
	
		Set<Pur_Peri_Quality_Check_Details> periDtls = new HashSet<Pur_Peri_Quality_Check_Details>();
		periDtls.addAll(pPeripheral.getPur_Peri_Quality_Check_Details());
		for(Pur_Peri_Quality_Check_Details pDts : periDtls)
		{
			pDts.setPeri_qc_id(gen_sno);
			pDts.setCompany_id("xxxx");
			pDts.setFin_year("2019-2020");
			pDts.setModified_type("INSERTED");
			pDts.setInserted_by("xxxx");
			pDts.setInserted_on(ldt);
			pDts.setUpdated_by("NA");
			pDts.setUpdated_on(ldt);
			pDts.setDeleted_by("NA");
			pDts.setDeleted_on(ldt);
		
		}
		pPeripheral.setPur_Peri_Quality_Check_Details(periDtls);
		
		
		return pur_Peri_Quality_CheckRepository.save(pPeripheral);
	}
	
	public List<Pur_Peri_Quality_Check> findAllPeripheral()
	{
		return pur_Peri_Quality_CheckRepository.findAll();
	}
	
	public Pur_Peri_Quality_Check findOne(long id)
	{
		Optional<Pur_Peri_Quality_Check> op=this.pur_Peri_Quality_CheckRepository.findById(id);
		return op.get();
	}
	
}
