package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Supplier_group;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supplier_groupDTO;

public interface Supplier_groupService {
	
	public SequenceIdDTO getSgrpSequenceId(String prefix,String company);
	
	public Supplier_group save(Supplier_group Supplier_group);
	
	public List<Supplier_group> findAll();
	
	public Supplier_group findOne(long id);
	
	public Supplier_group update(Supplier_group supplier_group,long id);
	
	public Supplier_group deleteSupplierGrp(Supplier_group supplier_group,long id);
	
	public List<Supplier_groupDTO> getSupplierGroupNCList();
	
	public Supplier_groupDTO getSuppParentGroup(String code);
	
	public List<Map<String,Object>> getsupplierByGroup(String code);
	
	public Supplier_groupDTO supplierGroupId();
	
	public MessageStatusDTO chkSuppGroupStatus(String grpname);
	
	public List<Supplier_group> findSupplierGrps(String searchtext,String company);
	
	public StatusDTO chkSupplierGrpCodeStatus(String code);
	
}
