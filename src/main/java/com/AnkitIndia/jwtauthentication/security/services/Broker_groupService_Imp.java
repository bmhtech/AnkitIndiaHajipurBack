package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_group;
import com.AnkitIndia.jwtauthentication.repository.Broker_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Broker_groupService_Imp implements Broker_groupService{
	@Autowired
	Broker_groupRepository broker_groupRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;

	public SequenceIdDTO getBgrpSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=broker_groupRepository.getBgrpSequenceId(fprefix,company);
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
	public Broker_group save(Broker_group broker_group)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="BG";
		if(broker_groupRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(broker_groupRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(broker_group.getParent_group().compareTo("0")==0) {
			broker_group.setParent_group(gen_sno);
		}
		else
		{
		}
		broker_group.setGroup_id(gen_sno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=broker_group.getGroup_code().substring(0,7);
		String gen_snonew=broker_groupRepository.getBgrpSequenceId(tprefix,broker_group.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix,nslno);
		broker_group.setGroup_code(gen_tslno);
		/*End checking transaction no for unique */
		
		broker_group.setModified_type("INSERTED");
		broker_group.setInserted_by(userRepository.getUserDetails(broker_group.getUsername()).getName());
		broker_group.setInserted_on(ldt);
		broker_group.setUpdated_by("NA");
		broker_group.setUpdated_on(ldt);
		broker_group.setDeleted_by("NA");
		broker_group.setDeleted_on(ldt);
		
		return broker_groupRepository.save(broker_group);
	}
	
	public List<Broker_group> findAll(String company)
	{
		List<Broker_group> brokerGrpList=new ArrayList<Broker_group>();
		brokerGrpList.addAll(broker_groupRepository.findAll());
		//System.out.println(brokerGrpList.size());
		brokerGrpList.forEach((bg)->{
			//System.out.println("bg.getParent_group()" + bg.getParent_group());
			if(Utility.isNullOrEmpty(bg.getParent_group())) {
				bg.setParent_group(broker_groupRepository.getBroParentGroup(bg.getParent_group(),company).getGroup_name());
				//System.out.println("1 "+broker_groupRepository.getBroParentGroup(bg.getParent_group(),company).getGroup_name());
			}else {bg.setParent_group("None");}
			//System.out.println("bg.getControl_acc() "+bg.getControl_acc());
			if(Utility.isNullOrEmpty(bg.getControl_acc())) {
				//bg.setControl_acc(ledgermasterRepository.getLedgerDetails(bg.getControl_acc()).getLedgername());  // end on 10062022 getting error for blank ledger 
				//System.out.println("2 :: " +ledgermasterRepository.getLedgerDetails(bg.getControl_acc()).getLedgername());
			}else {bg.setControl_acc("None");}
			
			//System.out.println("bg.getCompany_id() "+bg.getCompany_id());
			//System.out.println("bg.getModified_type() "+bg.getModified_type());
		});
		
		List<Broker_group> allData = brokerGrpList
				  .parallelStream()
				  .filter(c -> c.getCompany_id().equals(company) && c.getModified_type().equals("INSERTED") || c.getModified_type().equals("UPDATED"))
				  .sorted(Comparator.comparing(Broker_group::getGroup_id).reversed())
				  .collect(Collectors.toList());
		//System.out.println("allData :: "+allData);
		return allData;
	}
	
	public Broker_group findOne(long id)
	{
		Optional<Broker_group> op=this.broker_groupRepository.findById(id);
		return op.get();
	}
	
	public List<Broker_groupDTO> getBrokerGroupList(String company)
	 {
		 List<Broker_group> modelList=  broker_groupRepository.getBrokerGroupList(true, company);
		 
		 Type listType=new TypeToken<List<Broker_groupDTO>>() {}.getType();
		 
		 List<Broker_groupDTO> itemNameList=new ModelMapper().map(modelList, listType);
		 
		 return itemNameList;
	 }
	

	public List<Broker_groupDTO> getBrokerGroupList()
	 {
		 List<Broker_group> modelList=  broker_groupRepository.getBrokerGroupList(true);

		 
		 Type listType=new TypeToken<List<Broker_groupDTO>>() {}.getType();
		 
		 List<Broker_groupDTO> itemNameList=new ModelMapper().map(modelList, listType);
		 
		 return itemNameList;
	 }
	
	public Broker_groupDTO getBroParentGroup(String code, String company)
	{
		Broker_group modelList=broker_groupRepository.getBroParentGroup(broker_groupRepository.getBroParentGroup(code,company).getParent_group(),company);
		
		Type listType=new TypeToken<Broker_groupDTO>() {}.getType();
		
		Broker_groupDTO pGroup=new ModelMapper().map(modelList, listType);
		
		return pGroup;
	}
	
	@Transactional
	public Broker_group update(Broker_group broker_group,long id)
	{
		Optional<Broker_group> op = broker_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		broker_group.setGroup_id(op.get().getGroup_id());
		
		if(broker_group.getParent_group().compareTo("0")==0) {
			broker_group.setParent_group(broker_group.getGroup_id());
		}
		else
		{
		}
		
		broker_group.setModified_type("UPDATED");
		broker_group.setInserted_by(op.get().getInserted_by());
		broker_group.setInserted_on(op.get().getInserted_on());
		broker_group.setUpdated_by(userRepository.getUserDetails(broker_group.getUsername()).getName());
		broker_group.setUpdated_on(ldt);
		broker_group.setDeleted_by("NA");
		broker_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			broker_group.setId(id);
		}
		return broker_groupRepository.save(broker_group);
	}
	
	@Transactional
	public Broker_group deleteBrokerGrp(Broker_group bro_group,long id)
	{
		Optional<Broker_group> op = broker_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Broker_group broker_group=op.get();
		
		broker_group.setDeleted_by(userRepository.getUserDetails(bro_group.getUsername()).getName());
		broker_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			broker_group.setId(id);
		}
		
		if(broker_groupRepository.chkBrokerGroup(op.get().getGroup_id()).isPresent() ||
				broker_masterRepository.getBrokerDtlsThruGroup(op.get().getGroup_id()).isPresent()) {
			return bro_group;
		}else {
			broker_group.setModified_type("DELETED");
			
			return broker_groupRepository.save(broker_group);
		}
	}
	
	public MessageStatusDTO chkBrokerGroupStatus(String grpname, String company) 
	{
		String result=broker_groupRepository.chkBrokerGroupStatus(grpname, company);
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public List<Broker_group> findBrokerGrp(String searchtext,String company)
	{
		List<Broker_group> bgList=new ArrayList<Broker_group>();
		bgList.addAll(broker_groupRepository.findAll());
		
		bgList.forEach((bg)->{
			if(Utility.isNullOrEmpty(bg.getParent_group())) {
				bg.setParent_group(broker_groupRepository.getBroParentGroup(bg.getParent_group(),company).getGroup_name());
			}else {bg.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(bg.getControl_acc())) {
				bg.setControl_acc(ledgermasterRepository.getLedgerDetails(bg.getControl_acc()).getLedgername());
			}else {bg.setControl_acc("None");}
		});
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Broker_group> allData = bgList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Broker_group::getGroup_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Broker_group> allData = bgList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getGroup_code()+c.getGroup_name()+c.getParent_group()+c.getControl_acc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Broker_group::getGroup_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO chkBrokerGrpCodeStatus(String code, String company)
	{
		String result="";
		
		if(broker_groupRepository.chkBrokerGrpCodeStatus(code,company).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
}
