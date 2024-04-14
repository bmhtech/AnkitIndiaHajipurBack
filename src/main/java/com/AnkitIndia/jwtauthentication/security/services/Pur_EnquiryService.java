package com.AnkitIndia.jwtauthentication.security.services;
import java.util.List;



import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_BPartner_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_Service_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

	public interface Pur_EnquiryService {
		
		public SequenceIdDTO getEnqSequenceId(String prefix,String orderdate,String type);

		public Pur_Enquiry save(Pur_Enquiry pqc);
		
		public Pur_Enquiry update(Pur_Enquiry iMaster,Long id);
		
		public List<Pur_Enquiry> findAll();
		
		public Pur_Enquiry findOne(long id);
		
		 public List<Pur_Enquiry> enqNo();
		 
		 public List<Pur_EnquiryDTO> getPurEnquiryDDCList(String ccc);
		 
		 public List<Pur_EnquiryDTO> getPurEnquiryDDCSuppList(String reftype,String sid,String itemtype);
		 
		 public List<Pur_EnquiryDTO> getPurEnquiryInformal(String reftype);
		 
		 public Pur_EnquiryDTO getPurEnquiryDetails(String enqid);
		 
		 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryCNQUPList(String enq_id);
		 
		 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnqItemDtlsList();
		 
		 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryItemDtlsList(String enq_id);
		 
		 public List<Pur_Enquiry_BPartner_DetailsDTO> getPurEnquiryBPDetails(String enq_id);
		 
		 public List<Pur_Enquiry_DocDTO> getPurEnquiryDocList(String enq_id);
		 
		//public Pur_Enquiry findOne(Long id);
		
		//public Pur_Enquiry update(Pur_Enquiry pqc,Long id);
		 
	}