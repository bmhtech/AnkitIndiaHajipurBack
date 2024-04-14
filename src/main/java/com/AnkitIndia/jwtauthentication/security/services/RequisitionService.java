package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Requisition;
import com.AnkitIndia.jwtauthentication.model.Requisition_Item_Dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.RequisitionListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface RequisitionService {

	
	public SequenceIdDTO  getrequisitionnumber(String finyear);
	
	public Requisition save(Requisition requisition);
	
	public Requisition update(Requisition requisition,long id);
	
	public Requisition deleteRequisition(Requisition requisition,long id);
	
	public List<RequisitionListDTO>  listRequisition();
	
	public Requisition  getrequisitionnumber(long id);
			
	public List<Requisition_Item_Dtls>  getRequisitionitemdetails(String requisitionno);	
	
	public StatusDTO  requisitionapprove(long id,String username);
	 
	public Requisition setreject(Requisition requisition,long id);
	
	public List<Requisition>  getRequisitionnumberapprove(String shopfloor);
	
	 public Requisition  getRequisition(String requisitionno);
	 
}
