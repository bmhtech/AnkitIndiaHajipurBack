package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDGenerator;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent_Doc;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Uniqueid;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DepartmentMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_IndentRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Indent_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Indent_DocRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_IndentDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Indent_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Indent_DocDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Indent_DtlsDTO;

@Service
public class Pur_IndentService_Imp implements Pur_IndentService {
	
	@Autowired
	Pur_IndentRepository pur_IndentRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	DepartmentMasterRepository departmentMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Pur_Indent_DetailsRepository pur_Indent_DetailsRepository;
	
	@Autowired
	Pur_Indent_DocRepository pur_Indent_DocRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getIndSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		//String dt=Utility.convertDate(orderdate);
		String sno=pur_IndentRepository.countIndentOrder(orderdate,type);
		if(type.compareTo("Formal")==0) {type="FOR";}
		if(type.compareTo("In Formal")==0) {type="INFOR";}
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		/*if(uniqueidRepository.getSequenceId() != null ) {
			slno=Integer.parseInt(uniqueidRepository.getSequenceId());
		}*/
		/*int k=String.valueOf(slno).length();
		UniqueIDGenerator u = new UniqueIDGenerator(); 
		String x=u.getUniqueID();
		
		Uniqueid uniqueid=new Uniqueid();
		uniqueid.setSid((slno+1));
		uniqueid.setUid(x +(k+1));
		uniqueidRepository.save(uniqueid);*/
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Pur_Indent saveIndent(Pur_Indent pIndent)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_IndentRepository.countId() != null ) {
			slno=Long.parseLong(pur_IndentRepository.countId());
		}
		String prefix="IND";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		pIndent.setIndent_id(gen_sno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pIndent.getIndent_no());
		long nslno=0;String type="",tprefix="IND";
		String tsno=pur_IndentRepository.countIndentOrder(pIndent.getIndent_date(),pIndent.getIndent_type());
		if(pIndent.getIndent_type().compareTo("Formal")==0) {type="FOR";}
		if(pIndent.getIndent_type().compareTo("In Formal")==0) {type="INFOR";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = pIndent.getIndent_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pIndent.setIndent_no(gen_tslno);
		System.err.println("Last:>>>"+pIndent.getIndent_no());
		/*End checking transaction no for unique */
		
		pIndent.setModified_type("INSERTED");
		pIndent.setInserted_by(userRepository.getUserDetails(pIndent.getUsername()).getName());
		pIndent.setInserted_on(ldt);
		pIndent.setUpdated_by("NA");
		pIndent.setUpdated_on(ldt);
		pIndent.setDeleted_by("NA");
		pIndent.setDeleted_on(ldt);
		
		Set<Pur_Indent_Details> indentSet = new HashSet<Pur_Indent_Details>();
		indentSet.addAll(pIndent.getPur_Indent_Details());
		for(Pur_Indent_Details indentDts : indentSet)
		{
			indentDts.setIndent_id(gen_sno);
			indentDts.setIndent_no(pIndent.getIndent_no());
			indentDts.setItem_name(item_masterRepository.itemNameById(indentDts.getItem_code()).getItem_name());
			if(indentDts.getPacking_item().compareTo("")!=0 && indentDts.getPacking_item().compareTo("0")!=0) {
				indentDts.setPacking_item_name(item_masterRepository.itemNameById(indentDts.getPacking_item()).getItem_name());
			}
			indentDts.setCompany_id(pIndent.getCompany_id());
			indentDts.setFin_year(pIndent.getFin_year());
			indentDts.setModified_type("INSERTED");
			indentDts.setInserted_by(pIndent.getInserted_by());
			indentDts.setInserted_on(pIndent.getInserted_on());
			indentDts.setUpdated_by("NA");
			indentDts.setUpdated_on(ldt);
			indentDts.setDeleted_by("NA");
			indentDts.setDeleted_on(ldt);
		
		}
		pIndent.setPur_Indent_Details(indentSet);
		
		Set<Pur_Indent_Doc> docSet = new HashSet<Pur_Indent_Doc>();
		docSet.addAll(pIndent.getPur_Indent_docs());
		for(Pur_Indent_Doc docDts : docSet)
		{
			docDts.setIndent_id(gen_sno);
			docDts.setIndent_no(pIndent.getIndent_no());
			docDts.setCompany_id(pIndent.getCompany_id());
			docDts.setFin_year(pIndent.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(pIndent.getInserted_by());
			docDts.setInserted_on(pIndent.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		pIndent.setPur_Indent_docs(docSet);
		
		return pur_IndentRepository.save(pIndent);
	}
	
	@Transactional
	public Pur_Indent update(Pur_Indent iMaster,Long id) 
	{
		Optional<Pur_Indent> op = pur_IndentRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		pur_Indent_DetailsRepository.pur_Indent_Detailsupdate(iMaster.getIndent_id());
		
		Set<Pur_Indent_Details> indentSet = new HashSet<Pur_Indent_Details>();
		indentSet.addAll(iMaster.getPur_Indent_Details());
		for(Pur_Indent_Details indentDts : indentSet)
		{
			indentDts.setIndent_id(iMaster.getIndent_id());
			indentDts.setIndent_no(iMaster.getIndent_no());
			indentDts.setItem_name(item_masterRepository.itemNameById(indentDts.getItem_code()).getItem_name());
			if(indentDts.getPacking_item().compareTo("")!=0 && indentDts.getPacking_item().compareTo("0")!=0) {
				indentDts.setPacking_item_name(item_masterRepository.itemNameById(indentDts.getPacking_item()).getItem_name());
			}
			indentDts.setCompany_id(iMaster.getCompany_id());
			indentDts.setFin_year(iMaster.getFin_year());
			indentDts.setModified_type("INSERTED");
			indentDts.setInserted_by(op.get().getInserted_by());
			indentDts.setInserted_on(op.get().getInserted_on());
			indentDts.setUpdated_by(iMaster.getInserted_by());
			indentDts.setUpdated_on(iMaster.getInserted_on());
			indentDts.setDeleted_by("NA");
			indentDts.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Indent_Details(indentSet);
		
		pur_Indent_DocRepository.pur_Indent_Docupdate(iMaster.getIndent_id());
		Set<Pur_Indent_Doc> docSet = new HashSet<Pur_Indent_Doc>();
		docSet.addAll(iMaster.getPur_Indent_docs());
		for(Pur_Indent_Doc docDts : docSet)
		{
			docDts.setIndent_id(iMaster.getIndent_id());
			docDts.setIndent_no(iMaster.getIndent_no());
			docDts.setCompany_id(iMaster.getCompany_id());
			docDts.setFin_year(iMaster.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(op.get().getInserted_by());
			docDts.setInserted_on(op.get().getInserted_on());
			docDts.setUpdated_by(iMaster.getInserted_by());
			docDts.setUpdated_on(iMaster.getInserted_on());
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Indent_docs(docSet);
		
		return pur_IndentRepository.save(iMaster);
	}

	public List<Pur_Indent> findAll()
	{
		List<Pur_Indent> indList=new ArrayList<Pur_Indent>();
		indList.addAll(pur_IndentRepository.findPurIndents());
		
		for(int i=0;i<indList.size();i++) {
			
			if(indList.get(i).getSer_item_type().compareTo("0") != 0) {
				indList.get(i).setSer_item_type(item_type_masterRepository.getItemType(indList.get(i).getSer_item_type()).getItem_name());
			}
			else {
				indList.get(i).setSer_item_type("NO");
			}
			
			if(indList.get(i).getFullfillment_type().compareTo("pur001")==0) {
				indList.get(i).setFullfillment_type("Purchase Enquiry");
			}
			if(indList.get(i).getFullfillment_type().compareTo("pur002")==0) {
				indList.get(i).setFullfillment_type("Purchase Quotation");
			}
			if(indList.get(i).getFullfillment_type().compareTo("pur003")==0) {
				indList.get(i).setFullfillment_type("Purchase Order");
			}
			if(indList.get(i).getFullfillment_type().compareTo("pur007")==0) {
				indList.get(i).setFullfillment_type("GRN");
			}
		}
		return indList;
	}
	
	public Pur_Indent findOne(long id)
	{
		Optional<Pur_Indent> op=this.pur_IndentRepository.findById(id);
		return op.get();
	}
	
	public List<Pur_Indent> getPurIndentCList()
	{
		return pur_IndentRepository.getPurIndentCList();
	}
	
	public List<Pur_Indent> getPurIndentCYList()
	{
		return pur_IndentRepository.getPurIndentCYList();
	}
	
	public List<Pur_IndentDTO> getPurIndentDDCList(String ccc,String itemtype)
	{
		List<Pur_Indent> modelList =pur_IndentRepository.getPurIndentDDCList(ccc,itemtype);
		
		Type listType=new TypeToken<List<Pur_IndentDTO>>() {}.getType();
		
		List<Pur_IndentDTO> itemNameList=new ModelMapper().map(modelList,listType);
		for(int i=0;i<itemNameList.size();i++) {
			if(itemNameList.get(i).getDepartment()!=null && itemNameList.get(i).getDepartment().compareTo("")!=0 && itemNameList.get(i).getDepartment().compareTo("0")!=0) {
				itemNameList.get(i).setDepartment_name(departmentMasterRepository.getDeptName(itemNameList.get(i).getDepartment()).getDepartment_name());
			}
			else {
				itemNameList.get(i).setDepartment_name("None");
			}
		}
		return itemNameList;
	}
	
	public List<Pur_Indent_DetailsDTO> getPurIndentCNQUPList(String indent_no)
	{
		String[] arrOfStr=indent_no.split(",");
		
		List<Pur_Indent_Details> modelList=new ArrayList<Pur_Indent_Details>();
		
		for(int i=0;i<arrOfStr.length;i++)
		{
			modelList.addAll(pur_Indent_DetailsRepository.getPurIndentCNQUPList(arrOfStr[i]));
		}
			
		Type listType=new TypeToken<List<Pur_Indent_DetailsDTO>>() {}.getType();
		List<Pur_Indent_DetailsDTO> purIndList=new ModelMapper().map(modelList,listType);
		return purIndList;
	}
	
	public List<Pur_Indent_DetailsDTO> getPurIndentDetailsList(String indent_no)
	{
		List<Pur_Indent_Details> modelList=pur_Indent_DetailsRepository.getPurIndentDetailsList(indent_no);
		
		Type listType=new TypeToken<List<Pur_Indent_DetailsDTO>>() {}.getType();
		
		List<Pur_Indent_DetailsDTO> indList=new ModelMapper().map(modelList,listType);
		
		/*for(int i=0;i<indList.size();i++)
		{
			System.err.println("Packing UOM: "+indList.get(i).getPacking_uom()+" Item UOM: "+indList.get(i).getItem_uom());
			indList.get(i).setPacking_uom();
			getUomDesc
		}*/
		
		return indList;
	}
	
	public Pur_IndentDTO getPurIndentDtls(String indentno)
	{
		Pur_Indent modelList=pur_IndentRepository.getPurIndentDtls(indentno);
		
		Type listType=new TypeToken<Pur_IndentDTO>() {}.getType();
		
		Pur_IndentDTO indDtls=new ModelMapper().map(modelList,listType);
		
		return indDtls;
	}
	
	
	public List<Pur_Indent_DocDTO> indentOrderDocRetriveList(String indent_no)
	{
		List<Pur_Indent_Doc> modelList=pur_Indent_DocRepository.indentOrderDocRetriveList(indent_no);
		
		Type listType=new TypeToken<List<Pur_Indent_DocDTO>>() {}.getType();
		
		List<Pur_Indent_DocDTO> indDoc=new ModelMapper().map(modelList,listType);
		
		return indDoc;
	}
}

