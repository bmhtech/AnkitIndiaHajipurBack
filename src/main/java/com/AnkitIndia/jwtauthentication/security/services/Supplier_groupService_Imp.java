package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import com.AnkitIndia.jwtauthentication.model.Supplier_group;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Supplier_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supplier_groupDTO;


@Service
public class Supplier_groupService_Imp implements Supplier_groupService{
	
	@Autowired
	Supplier_groupRepository supplier_groupRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;

	public SequenceIdDTO getSgrpSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=supplier_groupRepository.getSgrpSequenceId(fprefix,company);
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
	public Supplier_group save(Supplier_group supplier_group)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="SG";
		if(supplier_groupRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(supplier_groupRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(supplier_group.getParent_group().compareTo("0")==0) {
			supplier_group.setParent_group(gen_sno);
			supplier_group.setMain_group(gen_sno);
		}
		else
		{
			supplier_group.setMain_group("0");
		}
		supplier_group.setBp_group_id(gen_sno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=supplier_group.getBp_group_code();
		String gen_snonew=supplier_groupRepository.getSgrpSequenceId(tprefix.substring(0,7),supplier_group.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,7),nslno);
		supplier_group.setBp_group_code(gen_tslno);
		/*End checking transaction no for unique */
		
		supplier_group.setModified_type("INSERTED");
		supplier_group.setInserted_by(userRepository.getUserDetails(supplier_group.getUsername()).getName());
		supplier_group.setInserted_on(ldt);
		supplier_group.setUpdated_by("NA");
		supplier_group.setUpdated_on(ldt);
		supplier_group.setDeleted_by("NA");
		supplier_group.setDeleted_on(ldt);
		
		return supplier_groupRepository.save(supplier_group);
	}
	
	public List<Supplier_group> findAll()
	{
		List<Supplier_group> suppGrpList=new ArrayList<Supplier_group>();
		suppGrpList.addAll(supplier_groupRepository.findAll());
		 
		List<Supplier_group> allData = suppGrpList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Supplier_group::getBp_group_id).reversed())
				  .collect(Collectors.toList());
		
		allData.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(supplier_groupRepository.getSuppParentGroup(ig.getParent_group()).getBp_grp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getControl_acc())) {
				ig.setControl_acc(ledgermasterRepository.getLedgerDetails(ig.getControl_acc()).getLedgername());
			}else {ig.setControl_acc("None");}
		});
		
		return allData;
	}
	
	public Supplier_group findOne(long id) {
		Optional<Supplier_group> op=this.supplier_groupRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Supplier_group update(Supplier_group supplier_group,long id)
	{
		Optional<Supplier_group> op =supplier_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		supplier_group.setBp_group_id(op.get().getBp_group_id());
		
		if(supplier_group.getParent_group().compareTo("0")==0) {
			supplier_group.setParent_group(supplier_group.getBp_group_id());
			supplier_group.setMain_group(supplier_group.getBp_group_id());
		}
		else
		{
			supplier_group.setMain_group("0");
		}
		
		supplier_group.setModified_type("UPDATED");
		supplier_group.setInserted_by(op.get().getInserted_by());
		supplier_group.setInserted_on(op.get().getInserted_on());
		supplier_group.setUpdated_by(userRepository.getUserDetails(supplier_group.getUsername()).getName());
		supplier_group.setUpdated_on(ldt);
		supplier_group.setDeleted_by("NA");
		supplier_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			supplier_group.setId(id);
		}
		return supplier_groupRepository.save(supplier_group);
	}
	
	@Transactional
	public Supplier_group deleteSupplierGrp(Supplier_group suppgrp,long id)
	{
		Optional<Supplier_group> op = supplier_groupRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Supplier_group supplier_group=op.get();

		supplier_group.setInserted_by(op.get().getInserted_by());
		supplier_group.setInserted_on(op.get().getInserted_on());
		supplier_group.setUpdated_by(op.get().getUpdated_by());
		supplier_group.setUpdated_on(op.get().getUpdated_on());
		supplier_group.setDeleted_by(userRepository.getUserDetails(suppgrp.getUsername()).getName());
		supplier_group.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			supplier_group.setId(id);
		}
		if(supplier_groupRepository.chkSuppGroup(op.get().getBp_group_id()).isPresent() || 
				supp_bussiness_partnerRepository.getSuppDtlsThruGroup(op.get().getBp_group_id()).isPresent()) {
			return suppgrp;
		}else {
			supplier_group.setModified_type("DELETED");
			
			return supplier_groupRepository.save(supplier_group);
		}
	}
	
	public List<Supplier_groupDTO> getSupplierGroupNCList()
	{
		List<Supplier_group> modelList=supplier_groupRepository.getSupplierGroupList(true);
		modelList.forEach((e->{
			e.setBp_grp_name(e.getBp_grp_name().toUpperCase());
		}));
		
		List<Supplier_group> cPartners = modelList
				  .parallelStream()
				  .filter(c ->!c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Supplier_group::getBp_grp_name))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Supplier_groupDTO>>() {}.getType();
		
		List<Supplier_groupDTO> itemNameList=new ModelMapper().map(cPartners, listType);
		
		return itemNameList;
	}
	
	public Supplier_groupDTO getSuppParentGroup(String code)
	{
		System.out.println("code :: "+code);
		Supplier_group modelList=supplier_groupRepository.getSuppParentGroup(supplier_groupRepository.getSuppParentGroup(code).getParent_group());
		
		Type listType=new TypeToken<Supplier_groupDTO>() {}.getType();
		
		Supplier_groupDTO pGroup=new ModelMapper().map(modelList, listType);
		
		return pGroup;
	}
	
	
	public Supplier_groupDTO supplierGroupId()
	{
		String modelList=supplier_groupRepository.supplierGroupId();
		
		Type listType=new TypeToken<Supplier_groupDTO>() {}.getType();
		
		Supplier_groupDTO result=new ModelMapper().map(modelList, listType);
		
		return result;
	}
	
	public MessageStatusDTO chkSuppGroupStatus(String grpname) {
		String result=supplier_groupRepository.chkSuppGroupStatus(grpname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public List<Supplier_group> findSupplierGrps(String searchtext,String company)
	{
		List<Supplier_group> sGrpList=new ArrayList<Supplier_group>();
		sGrpList.addAll(supplier_groupRepository.findAll());
		
		sGrpList.forEach((ig)->{
			if(Utility.isNullOrEmpty(ig.getParent_group())) {
				ig.setParent_group(supplier_groupRepository.getSuppParentGroup(ig.getParent_group()).getBp_grp_name());
			}else {ig.setParent_group("None");}
			
			if(Utility.isNullOrEmpty(ig.getControl_acc())) {
				ig.setControl_acc(ledgermasterRepository.getLedgerDetails(ig.getControl_acc()).getLedgername());
			}else {ig.setControl_acc("None");}
		});
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Supplier_group> allData = sGrpList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Supplier_group::getBp_grp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Supplier_group> allData = sGrpList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getBp_group_code()+c.getBp_grp_name()+c.getParent_group()+c.getControl_acc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Supplier_group::getBp_grp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO chkSupplierGrpCodeStatus(String code){
		String result="";
		
		if(supplier_groupRepository.chkSupplierGrpCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
	public List<Map<String,Object>> getsupplierByGroup(String code)
	{
		
		List<Map<String,Object>> supllist=supp_bussiness_partnerRepository.getgroupbysupplier(code);
		return supllist;
				
	}

}
