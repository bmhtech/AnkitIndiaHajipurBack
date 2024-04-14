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

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_group;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;

@Service
public class Cust_groupService_Imp implements Cust_groupService{
	
	@Autowired
	Cust_groupRepository cust_groupRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;

	public SequenceIdDTO getCgrpSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=cust_groupRepository.getCgrpSequenceId(fprefix,company);
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
	public Cust_group save(Cust_group cust_group)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="CG";
		if(cust_groupRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(cust_groupRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(cust_group.getParent_group().compareTo("0")==0) {
			cust_group.setParent_group(gen_sno);
		}
		else
		{
		}
		cust_group.setCp_code(gen_sno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=cust_group.getGrp_code();
		String gen_snonew=cust_groupRepository.getCgrpSequenceId(tprefix.substring(0,7),cust_group.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,7),nslno);
		cust_group.setGrp_code(gen_tslno);
		/*End checking transaction no for unique */
		
		cust_group.setModified_type("INSERTED");
		cust_group.setInserted_by(userRepository.getUserDetails(cust_group.getUsername()).getName());
		cust_group.setInserted_on(ldt);
		cust_group.setUpdated_by("NA");
		cust_group.setUpdated_on(ldt);
		cust_group.setDeleted_by("NA");
		cust_group.setDeleted_on(ldt);
		
		return cust_groupRepository.save(cust_group);
	}
	
	public List<Cust_group> findAll()
	{
		List<Cust_group> custGroupList=new ArrayList<Cust_group>();
		custGroupList=cust_groupRepository.findAll();
		
		List<Cust_group> allData = custGroupList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Cust_group::getCp_code).reversed())
				  .collect(Collectors.toList());
		
		allData.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(cust_groupRepository.getCustParentGroup(ig.getParent_group()).getGrp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getCtrl_acc())) {
		//		ig.setCtrl_acc(ledgermasterRepository.getLedgerDetails(ig.getCtrl_acc()).getLedgername());
			}else {ig.setCtrl_acc("None");}
		});
		
		return allData;
	}
	
	public Cust_group findOne(long id)
	{
		Optional<Cust_group> op=this.cust_groupRepository.findById(id);
		return op.get();
	}
	
	public List<Cust_group> getCustomerGroupList()
	{
		return cust_groupRepository.getCustomerGroupList(true);
	}
	
	public List<Cust_groupDTO> getCustGroupNCList()
	{
		List<Cust_group> modelList=cust_groupRepository.getCustGroupList(true);
		
		Type listType=new TypeToken<List<Cust_groupDTO>>() {}.getType();
		
		List<Cust_groupDTO> custNameList=new ModelMapper().map(modelList, listType);
		
		return custNameList;
	}
	
	public Cust_groupDTO getCustParentGroup(String code)
	{
		Cust_group modelList=cust_groupRepository.getCustParentGroup(cust_groupRepository.getCustParentGroup(code).getParent_group());
		Type listType=new TypeToken<Cust_groupDTO>() {}.getType();
		
		Cust_groupDTO pGroup=new ModelMapper().map(modelList, listType);
		
		return pGroup;
	}
	
	public Cust_groupDTO getCustomerControlAccounts(String custid) {
		Cust_bussiness_partner cPartner =cust_bussiness_partnerRepository.getCustomer(custid);
		Cust_group cust_group=cust_groupRepository.getCustParentGroup(cPartner.getGroup_type());
		
		Type listType=new TypeToken<Cust_groupDTO>() {}.getType();
		Cust_groupDTO pGroup=new ModelMapper().map(cust_group, listType);
		return pGroup;
	}
	
	@Transactional
	public Cust_group update(Cust_group cust_group,long id)
	{
		Optional<Cust_group> op = cust_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();

		cust_group.setCp_code(op.get().getCp_code());
		if(cust_group.getParent_group().compareTo("0")==0) {
			cust_group.setParent_group(cust_group.getCp_code());
		}
		else
		{
		}
		
		cust_group.setModified_type("UPDATED");
		cust_group.setInserted_by(op.get().getInserted_by());
		cust_group.setInserted_on(op.get().getInserted_on());
		cust_group.setUpdated_by(userRepository.getUserDetails(cust_group.getUsername()).getName());
		cust_group.setUpdated_on(ldt);
		cust_group.setDeleted_by("NA");
		cust_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			cust_group.setId(id);
		}
		return cust_groupRepository.save(cust_group);
	}
	
	@Transactional
	public Cust_group deleteCustGrp(Cust_group cust_group,long id)
	{
		Optional<Cust_group> op = cust_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Cust_group cMaster=op.get();
		
		cMaster.setDeleted_by(userRepository.getUserDetails(cust_group.getUsername()).getName());
		cMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			cMaster.setId(id);
		}
		
		if(cust_groupRepository.chkCustGroup(op.get().getCp_code()).isPresent() ||
				cust_bussiness_partnerRepository.getCustDtlsThruGroup(op.get().getCp_code()).isPresent()) {
			return cust_group;
		}else {
			cMaster.setModified_type("DELETED");
			
			return cust_groupRepository.save(cMaster);
		}
	}
	
	public MessageStatusDTO chkCustGroupStatus(String grpname)
	{
		String result=cust_groupRepository.chkCustGroupStatus(grpname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public List<Cust_group> findCustomerGrp(String searchtext,String company)
	{
		List<Cust_group> cGrpList=new ArrayList<Cust_group>();
		cGrpList.addAll(cust_groupRepository.findAll());
		
		cGrpList.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(cust_groupRepository.getCustParentGroup(ig.getParent_group()).getGrp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getCtrl_acc())) {
				ig.setCtrl_acc(ledgermasterRepository.getLedgerDetails(ig.getCtrl_acc()).getLedgername());
			}else {ig.setCtrl_acc("None");}
		});
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Cust_group> allData = cGrpList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Cust_group::getGrp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Cust_group> allData = cGrpList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getGrp_code()+c.getGrp_name()+c.getParent_group()+c.getCtrl_acc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Cust_group::getGrp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO chkCustGrpCodeStatus(String code){
		String result="";
		
		if(cust_groupRepository.chkCustGrpCodeStatus(code).isPresent()) {
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
