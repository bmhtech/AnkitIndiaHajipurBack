package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Tag_advice_with_po;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_poDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_po_Pagination_DTO;



public interface Tag_advice_with_poService {
	
	public SequenceIdDTO getTagAdvPOSequenceId(String prefix);
	
	public List<Map<String, Object>> getTagAdviceWithPoList(String currDate,String finyear);

	public Tag_advice_with_po save (Tag_advice_with_po tWith_po);
	
	public List<Tag_advice_with_poDTO> getTagAdvWithPO(String company);
	
	public Tag_advice_with_po findOne(long id);
	
	public Tag_advice_with_po deleteTagAdvWithPO (Tag_advice_with_po tagadvice,Long id);
	 
	public StatusDTO checkTagAdvicePoUsage(String adviceno);
	
	public StatusDTO checkTagAdvicePoUsageingrn(String pur_orderno);
	
	
	
	public Page<Tag_advice_with_po_Pagination_DTO> gettaggedadvice_pagination(int page,int size);
	
	public List<Tag_advice_with_po_Pagination_DTO> searchtaggedadvice(String orderno,String pono,String fromdate, String todate,String bus_partner1,String finyear);
	
	public List<Map<String,Object>> searchtaggedadviceFast(String orderno,String pono,String fromdate, String todate,String bus_partner1,String finyear);
	
	
}
