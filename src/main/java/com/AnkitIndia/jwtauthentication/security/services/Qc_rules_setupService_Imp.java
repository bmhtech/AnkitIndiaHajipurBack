package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup;
import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup_dtls;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Qc_rules_setupRepository;
import com.AnkitIndia.jwtauthentication.repository.Qc_rules_setup_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setup_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Qc_rules_setupService_Imp implements Qc_rules_setupService{
	
	@Autowired
	Qc_rules_setupRepository qc_rules_setupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Qc_rules_setup_dtlsRepository qc_rules_setup_dtlsRepository;
	
	@Transactional
	public Qc_rules_setup save(Qc_rules_setup qc_rules_setup)
	{
		LocalDateTime ldt = LocalDateTime.now();
		String prefix= "QCR";
		long slno=0;
		if(qc_rules_setupRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(qc_rules_setupRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix, slno);
		
		qc_rules_setup.setQc_id(gen_sno);
		qc_rules_setup.setModified_type("INSERTED");
		qc_rules_setup.setInserted_by(userRepository.getUserDetails(qc_rules_setup.getUsername()).getName());
		qc_rules_setup.setInserted_on(ldt);
		qc_rules_setup.setUpdated_by("NA");
		qc_rules_setup.setUpdated_on(ldt);
		qc_rules_setup.setDeleted_by("NA");
		qc_rules_setup.setDeleted_on(ldt);

		
		Set<Qc_rules_setup_dtls> aacNormsSet = new HashSet<Qc_rules_setup_dtls>();
		aacNormsSet.addAll(qc_rules_setup.getQc_rules_setup_dtls());
		for(Qc_rules_setup_dtls aanmdts:aacNormsSet)
		{
			aanmdts.setQc_id(gen_sno);
			aanmdts.setQc_code(qc_rules_setup.getQc_code());
			aanmdts.setCompany_id(qc_rules_setup.getCompany_id());
			aanmdts.setFin_year(qc_rules_setup.getFin_year());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(qc_rules_setup.getInserted_by());
			aanmdts.setInserted_on(ldt);
			aanmdts.setUpdated_by("NA");
			aanmdts.setUpdated_on(ldt);
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
		}
		qc_rules_setup.setQc_rules_setup_dtls(aacNormsSet);
		
		return qc_rules_setupRepository.save(qc_rules_setup);
	}
	
	@Transactional
	public Qc_rules_setup update(Qc_rules_setup qc_rules_setup,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<Qc_rules_setup> op = qc_rules_setupRepository.findById(id);
		
		qc_rules_setup.setQc_id(op.get().getQc_id());
		qc_rules_setup.setModified_type("INSERTED");
		qc_rules_setup.setInserted_by(op.get().getInserted_by());
		qc_rules_setup.setInserted_on(op.get().getInserted_on());
		qc_rules_setup.setUpdated_by(userRepository.getUserDetails(qc_rules_setup.getUsername()).getName());
		qc_rules_setup.setUpdated_on(ldt);
		qc_rules_setup.setDeleted_by("NA");
		qc_rules_setup.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			qc_rules_setup.setId(id);
		}
		
		qc_rules_setup_dtlsRepository.updateQc_rules_setup_dtls(op.get().getQc_id(),"UPDATED");
		Set<Qc_rules_setup_dtls> aacNormsSet = new HashSet<Qc_rules_setup_dtls>();
		aacNormsSet.addAll(qc_rules_setup.getQc_rules_setup_dtls());
		for(Qc_rules_setup_dtls aanmdts:aacNormsSet)
		{
			aanmdts.setQc_id(op.get().getQc_id());
			aanmdts.setQc_code(qc_rules_setup.getQc_code());
			aanmdts.setCompany_id(qc_rules_setup.getCompany_id());
			aanmdts.setFin_year(qc_rules_setup.getFin_year());
			aanmdts.setModified_type("INSERTED");
			aanmdts.setInserted_by(qc_rules_setup.getInserted_by());
			aanmdts.setInserted_on(qc_rules_setup.getInserted_on());
			aanmdts.setUpdated_by(qc_rules_setup.getUpdated_by());
			aanmdts.setUpdated_on(qc_rules_setup.getUpdated_on());
			aanmdts.setDeleted_by("NA");
			aanmdts.setDeleted_on(ldt);
		}
		qc_rules_setup.setQc_rules_setup_dtls(aacNormsSet);
		
		return qc_rules_setupRepository.save(qc_rules_setup);
	}
	
	public List<Qc_rules_setup>  findAll()
	{
		List<Qc_rules_setup> purList=new ArrayList<Qc_rules_setup>();
		purList.addAll(qc_rules_setupRepository.findAll());
		
		List<Qc_rules_setup> allData1 =purList.stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Qc_rules_setup::getQc_id).reversed())
				  .collect(Collectors.toList());
		return allData1;
	}
	
	public Qc_rules_setup findOne(long id)
	{
		Optional<Qc_rules_setup> op=this.qc_rules_setupRepository.findById(id);
		return op.get();
	}

	public List<Qc_rules_setupDTO> getQcrulesNc(String company) {
		
		List<Qc_rules_setup> purList=new ArrayList<Qc_rules_setup>();
		purList.addAll(qc_rules_setupRepository.getAllQC(company));
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Qc_rules_setupDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Qc_rules_setupDTO> qcList = new ModelMapper().map(purList,listType);
		//System.out.println("qcList::"+qcList.size());
		return qcList;	
	}
	public List<Qc_rules_setup_dtlsDTO> qcRulesRetriveList(String q_id)
	{
		List<Qc_rules_setup> modelList=new ArrayList<Qc_rules_setup>();
		
		modelList.addAll(qc_rules_setupRepository.qcRulesRetriveListnew(q_id));
			
		Type listType=new TypeToken<List<Qc_rules_setup_dtlsDTO>>() {}.getType();
		
		List<Qc_rules_setup_dtlsDTO> qcR=new ModelMapper().map(modelList,listType);
		
		return qcR;
	}
	
	public Qc_rules_setupDTO getQCRuleSetupDtls(String q_id)
	{
		Qc_rules_setup modelList=qc_rules_setupRepository.getQcrulesDetails(q_id);
		
		Type listType=new TypeToken<Qc_rules_setupDTO>() {}.getType();
		
		Qc_rules_setupDTO qcRDtls=new ModelMapper().map(modelList,listType);
		
		return qcRDtls;
	}
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public SequenceIdDTO getQcRulesSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=qc_rules_setupRepository.getQcRulesSequenceId(fprefix,company);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	
	
	@Transactional
	public Qc_rules_setup deleteQcRulesSetup(Qc_rules_setup qcMaster,long id)
	{
		Optional<Qc_rules_setup> op = qc_rules_setupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Qc_rules_setup QcRulespurposeMaster=op.get();

		QcRulespurposeMaster.setInserted_by(op.get().getInserted_by());
		QcRulespurposeMaster.setInserted_on(op.get().getInserted_on());
		QcRulespurposeMaster.setUpdated_by(op.get().getUpdated_by());
		QcRulespurposeMaster.setUpdated_on(op.get().getUpdated_on());
		QcRulespurposeMaster.setDeleted_by(userRepository.getUserDetails(qcMaster.getUsername()).getName());
		QcRulespurposeMaster.setDeleted_on(ldt);
		QcRulespurposeMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			QcRulespurposeMaster.setId(id);
		}
		
		QcRulespurposeMaster.setModified_type("DELETED");
		qc_rules_setup_dtlsRepository.updateQc_rules_setup_dtls(op.get().getQc_id(),"DELETED");

		return qc_rules_setupRepository.save(QcRulespurposeMaster);
	}
	
	
	public List<Qc_rules_setup> findQcRulesSetup(String searchtext)
	{
		List<Qc_rules_setup> cityList=new ArrayList<Qc_rules_setup>();
		cityList.addAll(qc_rules_setupRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Qc_rules_setup> allData = cityList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Qc_rules_setup::getQc_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Qc_rules_setup> allData = cityList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && (c.getQc_name()+c.getRemarks()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Qc_rules_setup::getQc_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

	public StatusDTO checkQualityCheckUsage(String qc_id)
	 {
		StatusDTO result = new StatusDTO();
		boolean purchase=false,sales=false,qualityChk=false;
		
		if(qc_rules_setupRepository.checkQCPurchaseUsage(qc_id))
		{
			purchase=true;
		}
		
		if(qc_rules_setupRepository.checkQCSalesUsage(qc_id))
		{
			sales=true;
		}
		
		if(qc_rules_setupRepository.checkQCPurchaseQualityUsage(qc_id))
		{
			qualityChk=true;
		}
		
		if(purchase == true || sales == true || qualityChk == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		
		return result;
	 }
	
}
