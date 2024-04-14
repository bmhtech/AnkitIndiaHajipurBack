package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Transporter_group;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transporter_groupDTO;

public interface Transporter_groupService {
	
	public SequenceIdDTO getTgrpSequenceId(String prefix,String company);
	
	public Transporter_group save(Transporter_group transporter_group);
	
	public List<Transporter_group> findAll();
	
	public List<Transporter_groupDTO> getTransporterGroupList();
	
	public Transporter_groupDTO getTransParentGroup(String code);
	
	public Transporter_group findOne(long id);
	
	public Transporter_group update(Transporter_group transporter_group,long id);
	
	public Transporter_group deleteTransporterGrps(Transporter_group transporter_group,long id);
	
	public MessageStatusDTO chkTransGroupStatus(String grpname);
	
	public List<Transporter_group> findTransporterGrps(String searchtext,String company);
	
	public StatusDTO chkTransporterGrpCodeStatus(String code);
}
