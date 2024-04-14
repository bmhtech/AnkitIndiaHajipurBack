package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Issuestock;
import com.AnkitIndia.jwtauthentication.model.Issuestock_Item_Dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.RequistionRecordsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StocksRecordsDTO;

public interface IssuestockService {
	
	 public List<Issuestock>  getIssueStocklist(String finyear);
	
	 public SequenceIdDTO  getissuestocknumber(String finyear);

	 public Issuestock save(Issuestock issuestock);
	 
	 public Issuestock  retriveIssueStock(long id);
		
	 public List<Issuestock_Item_Dtls> getIssueItemDetails(String issueno);
	
	 public Issuestock update(Issuestock issuestock,long id);
	
	 public Issuestock deleteIssueStock(Issuestock issuestock,long id);
	
	 public StocksRecordsDTO getaayogstocks(String itemcode);
	
	 public RequistionRecordsDTO getrequistionstockrecordbyitem(String itemcode,String requisitionno,String packingcode);
		
}
