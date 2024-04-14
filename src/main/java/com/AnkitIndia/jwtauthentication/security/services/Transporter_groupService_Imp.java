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
import com.AnkitIndia.jwtauthentication.model.Transporter_group;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Transporter_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transporter_groupDTO;

@Service
public class Transporter_groupService_Imp implements Transporter_groupService{

	@Autowired
	Transporter_groupRepository transporter_groupRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public SequenceIdDTO getTgrpSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=transporter_groupRepository.getTgrpSequenceId(fprefix,company);
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
	public Transporter_group save(Transporter_group transporter_group)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="TG";
		if(transporter_groupRepository.countId(prefix).isPresent() ) {
			slno=Long.parseLong(transporter_groupRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(transporter_group.getParent_group().compareTo("0")==0) {
			transporter_group.setParent_group(gen_sno);
		}else{}
		transporter_group.setBp_trans_id(gen_sno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=transporter_group.getBp_trans_code().substring(0,7);
		String gen_snonew=transporter_groupRepository.getTgrpSequenceId(tprefix,transporter_group.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix,nslno);
		transporter_group.setBp_trans_code(gen_tslno);
		/*End checking transaction no for unique */
		
		transporter_group.setModified_type("INSERTED");
		transporter_group.setInserted_by(userRepository.getUserDetails(transporter_group.getUsername()).getName());
		transporter_group.setInserted_on(ldt);
		transporter_group.setUpdated_by("NA");
		transporter_group.setUpdated_on(ldt);
		transporter_group.setDeleted_by("NA");
		transporter_group.setDeleted_on(ldt);
		
		return transporter_groupRepository.save(transporter_group);	
	}
	
	public List<Transporter_group> findAll()
	{
		List<Transporter_group> tgList=new ArrayList<Transporter_group>();
		tgList.addAll(transporter_groupRepository.findAll());
				
		List<Transporter_group> allData = tgList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Transporter_group::getBp_trans_id).reversed())
			  .collect(Collectors.toList());
		
		allData.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(transporter_groupRepository.getTransParentGroup(ig.getParent_group()).getBp_grp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getControl_acc())) {
			//	ig.setControl_acc(ledgermasterRepository.getLedgers(ig.getControl_acc()).getLedgername());
			}else {ig.setControl_acc("None");}
		});
		
		return allData;
	}
	
	public Transporter_group findOne(long id) {
		Optional<Transporter_group> op=this.transporter_groupRepository.findById(id);
		return op.get();
	}
	
	public List<Transporter_groupDTO> getTransporterGroupList()
	{
		List<Transporter_group> modelList=transporter_groupRepository.getTransporterGroupList(true);
		
		Type listType= new TypeToken<List<Transporter_groupDTO>>() {}.getType();
		
		List<Transporter_groupDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public Transporter_groupDTO getTransParentGroup(String code)
	{
		Transporter_group modelList=transporter_groupRepository.getTransParentGroup(transporter_groupRepository.getTransParentGroup(code).getParent_group());
		
		Type listType=new TypeToken<Transporter_groupDTO>(){}.getType();
		
		Transporter_groupDTO pGroup=new ModelMapper().map(modelList, listType);
		
		return pGroup;
	}
	
	@Transactional
	public Transporter_group update(Transporter_group transporter_group,long id)
	{
		Optional<Transporter_group> op = transporter_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		transporter_group.setBp_trans_id(op.get().getBp_trans_id());
		if(transporter_group.getParent_group().compareTo("0")==0) {
			transporter_group.setParent_group(transporter_group.getBp_trans_id());
		}
		else
		{
		}
		
		transporter_group.setModified_type("UPDATED");
		transporter_group.setInserted_by(op.get().getInserted_by());
		transporter_group.setInserted_on(op.get().getInserted_on());
		transporter_group.setUpdated_by(userRepository.getUserDetails(transporter_group.getUsername()).getName());
		transporter_group.setUpdated_on(ldt);
		transporter_group.setDeleted_by("NA");
		transporter_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			transporter_group.setId(id);
		}
		return transporter_groupRepository.save(transporter_group);
	}
	
	@Transactional
	public Transporter_group deleteTransporterGrps(Transporter_group trans_group,long id)
	{
		Optional<Transporter_group> op = transporter_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Transporter_group transporter_group=op.get();
		
		transporter_group.setInserted_by(op.get().getInserted_by());
		transporter_group.setInserted_on(op.get().getInserted_on());
		transporter_group.setUpdated_by(op.get().getUpdated_by());
		transporter_group.setUpdated_on(op.get().getUpdated_on());
		transporter_group.setDeleted_by(userRepository.getUserDetails(trans_group.getUsername()).getName());
		transporter_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			transporter_group.setId(id);
		}
		
		if(transporter_groupRepository.chkTransGroup(op.get().getBp_trans_id()).isPresent() ||
				trans_bussiness_partnerRepository.getTransDtlsThruGroup(op.get().getBp_trans_id()).isPresent()) {
			return trans_group;
		}else {
			transporter_group.setModified_type("DELETED");
			
			return transporter_groupRepository.save(transporter_group);
		}
	}
	
	public MessageStatusDTO chkTransGroupStatus(String grpname) {
		String result=transporter_groupRepository.chkTransGroupStatus(grpname);
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public List<Transporter_group> findTransporterGrps(String searchtext,String company)
	{
		List<Transporter_group> transGrpsList=new ArrayList<Transporter_group>();
		transGrpsList.addAll(transporter_groupRepository.findAll());
		
		transGrpsList.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(transporter_groupRepository.getTransParentGroup(ig.getParent_group()).getBp_grp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getControl_acc())) {
				ig.setControl_acc(ledgermasterRepository.getLedgers(ig.getControl_acc()).getLedgername());
			}else {ig.setControl_acc("None");}
		});
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Transporter_group> allData = transGrpsList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Transporter_group::getBp_grp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Transporter_group> allData = transGrpsList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getBp_trans_code()+c.getBp_grp_name()+c.getParent_group()+c.getControl_acc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Transporter_group::getBp_grp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO chkTransporterGrpCodeStatus(String code){
		String result="";
		
		if(transporter_groupRepository.chkTransporterGrpCodeStatus(code).isPresent()) {
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
