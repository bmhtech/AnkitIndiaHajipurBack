package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_IndentDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Indent_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Indent_DocDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Indent_DtlsDTO;

public interface Pur_IndentService {
	
	public SequenceIdDTO getIndSequenceId(String prefix,String orderdate,String type);
	
	public Pur_Indent saveIndent(Pur_Indent pIndent);
	
	public Pur_Indent update(Pur_Indent iMaster,Long id);
	
	public List<Pur_Indent> findAll();
	
	public Pur_Indent findOne(long id);
	
	public List<Pur_Indent> getPurIndentCList();
	
	public List<Pur_Indent> getPurIndentCYList();
	
	public List<Pur_IndentDTO> getPurIndentDDCList(String ccc,String itemtype);
	
	public List<Pur_Indent_DetailsDTO> getPurIndentCNQUPList(String indent_no);
	
	public List<Pur_Indent_DetailsDTO> getPurIndentDetailsList(String indent_no);
	
	public List<Pur_Indent_DocDTO> indentOrderDocRetriveList(String indent_no);
	
	public Pur_IndentDTO getPurIndentDtls(String indentno);
	
	
	
}
