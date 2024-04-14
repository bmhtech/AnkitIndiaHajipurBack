package com.AnkitIndia.jwtauthentication.security.services;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDGenerator;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Uniqueid;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_EnquiryRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Enquiry_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Enquiry_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Enquiry_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.UniqueidRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
@Repository
public class Sales_EnquiryService_Imp implements Sales_EnquiryService {
	
	@Autowired
	Sales_EnquiryRepository sales_EnquiryRepository;
	
	@Autowired
	UniqueidRepository uniqueidRepository;
	
	@Autowired
	Sales_Enquiry_Item_DtlsRepository sales_Enquiry_Item_DtlsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Sales_Enquiry_Party_DtlsRepository sales_Enquiry_Party_DtlsRepository;
	
	@Autowired 
	Sales_Enquiry_DocsRepository sales_Enquiry_DocsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SalesSequenceIdDTO getSalesEnqSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		String sno=sales_EnquiryRepository.countEnquiryOrder(orderdate,type);
		if(type.compareTo("Formal")==0) {type="FOR";}
		if(type.compareTo("Informal")==0) {type="INFOR";}
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Sales_Enquiry save(Sales_Enquiry sales_Enquiry)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(sales_EnquiryRepository.countId() != null ) {
			slno=Long.parseLong(sales_EnquiryRepository.countId());
		}
		String prefix="SE";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sales_Enquiry.setEnquiry_id(gen_sno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+sales_Enquiry.getEnquiry_no());
		long nslno=0;String type="",tprefix="ENQ";
		String tsno=sales_EnquiryRepository.countEnquiryOrder(sales_Enquiry.getEnq_date(),sales_Enquiry.getEnq_type());
		if(sales_Enquiry.getEnq_type().compareTo("Formal")==0) {type="FOR";}
		if(sales_Enquiry.getEnq_type().compareTo("Informal")==0) {type="INFOR";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = sales_Enquiry.getEnq_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		sales_Enquiry.setEnquiry_no(gen_tslno);
		System.err.println("Last:>>>"+sales_Enquiry.getEnquiry_no());
		/*End checking transaction no for unique */
		
		//SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");
		//System.err.println("1st Date: "+DATE_FORMAT1.format(sales_Enquiry.getEnq_date()));
		//System.err.println("Got Date: "+Utility.convertDate(DATE_FORMAT.format(sales_Enquiry.getEnq_date())));
		//sales_Enquiry.setEnq_date();
		
		sales_Enquiry.setModified_type("INSERTED");
		sales_Enquiry.setInserted_by(userRepository.getUserDetails(sales_Enquiry.getUsername()).getName());
		sales_Enquiry.setInserted_on(ldt);
		sales_Enquiry.setUpdated_by("NA");
		sales_Enquiry.setUpdated_on(ldt);
		sales_Enquiry.setDeleted_by("NA");
		sales_Enquiry.setDeleted_on(ldt);
	
		//Dynamic
		Set<Sales_Enquiry_Item_Dtls> itemSet = new HashSet<Sales_Enquiry_Item_Dtls>();
		itemSet.addAll(sales_Enquiry.getSales_Enquiry_Item_Dtls());
		for(Sales_Enquiry_Item_Dtls itemDts : itemSet)
		{
			itemDts.setEnquiry_id(gen_sno);
			itemDts.setEnquiry_no(sales_Enquiry.getEnquiry_no());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(itemDts.getPacking_item().compareTo("")!=0 && itemDts.getPacking_item().compareTo("0")!=0) {
				itemDts.setPacking_item_name(item_masterRepository.itemNameById(itemDts.getPacking_item()).getItem_name());
			}
			itemDts.setCompany_id(sales_Enquiry.getCompany_id());
			itemDts.setFin_year(sales_Enquiry.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(sales_Enquiry.getInserted_by());
			itemDts.setInserted_on(sales_Enquiry.getInserted_on());
			itemDts.setUpdated_by("NA");
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		
		}
		sales_Enquiry.setSales_Enquiry_Item_Dtls(itemSet);

		//Dynamic
		Set<Sales_Enquiry_Party_Dtls> partySet = new HashSet<Sales_Enquiry_Party_Dtls>();
		partySet.addAll(sales_Enquiry.getSales_Enquiry_Party_Dtls());
		for(Sales_Enquiry_Party_Dtls party : partySet)
		{
			party.setEnquiry_id(gen_sno);
			party.setEnquiry_no(sales_Enquiry.getEnquiry_no());
			party.setP_name(cust_bussiness_partnerRepository.getCustomer(party.getP_code()).getCp_name());
			party.setCompany_id(sales_Enquiry.getCompany_id());
			party.setFin_year(sales_Enquiry.getFin_year());
			party.setModified_type("INSERTED");
			party.setInserted_by(sales_Enquiry.getInserted_by());
			party.setInserted_on(sales_Enquiry.getInserted_on());
			party.setUpdated_by("NA");
			party.setUpdated_on(ldt);
			party.setDeleted_by("NA");
			party.setDeleted_on(ldt);
		}
		sales_Enquiry.setSales_Enquiry_Party_Dtls(partySet);
		
		//Dynamic
		Set<Sales_Enquiry_Docs> docSet = new HashSet<Sales_Enquiry_Docs>();
		docSet.addAll(sales_Enquiry.getSales_Enquiry_Docs());
		for(Sales_Enquiry_Docs docDts : docSet)
		{
			docDts.setEnquiry_id(gen_sno);
			docDts.setEnquiry_no(sales_Enquiry.getEnquiry_no());
			docDts.setCompany_id(sales_Enquiry.getCompany_id());
			docDts.setFin_year(sales_Enquiry.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(sales_Enquiry.getInserted_by());
			docDts.setInserted_on(sales_Enquiry.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		
		}
		sales_Enquiry.setSales_Enquiry_Docs(docSet);
		
		return sales_EnquiryRepository.save(sales_Enquiry);
	}
	
	@Transactional
	public Sales_Enquiry update(Sales_Enquiry iMaster,Long id)
	{
		Optional<Sales_Enquiry> op = sales_EnquiryRepository.findById(id);
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
		
		sales_Enquiry_Item_DtlsRepository.sales_Enquiry_Item_Dtlsupdate(iMaster.getEnquiry_id());
		Set<Sales_Enquiry_Item_Dtls> itemSet = new HashSet<Sales_Enquiry_Item_Dtls>();
		itemSet.addAll(iMaster.getSales_Enquiry_Item_Dtls());
		for(Sales_Enquiry_Item_Dtls itemDts : itemSet)
		{
			itemDts.setEnquiry_id(iMaster.getEnquiry_id());
			itemDts.setEnquiry_no(iMaster.getEnquiry_no());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(itemDts.getPacking_item().compareTo("")!=0 && itemDts.getPacking_item().compareTo("0")!=0) {
				itemDts.setPacking_item_name(item_masterRepository.itemNameById(itemDts.getPacking_item()).getItem_name());
			}
			itemDts.setCompany_id(iMaster.getCompany_id());
			itemDts.setFin_year(iMaster.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(iMaster.getInserted_by());
			itemDts.setInserted_on(iMaster.getInserted_on());
			itemDts.setUpdated_by(iMaster.getUpdated_by());
			itemDts.setUpdated_on(iMaster.getUpdated_on());
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		}
		iMaster.setSales_Enquiry_Item_Dtls(itemSet);

		//Dynamic
		sales_Enquiry_Party_DtlsRepository.sales_Enquiry_Party_Dtlsupdate(iMaster.getEnquiry_id());
		Set<Sales_Enquiry_Party_Dtls> partySet = new HashSet<Sales_Enquiry_Party_Dtls>();
		partySet.addAll(iMaster.getSales_Enquiry_Party_Dtls());
		for(Sales_Enquiry_Party_Dtls party : partySet)
		{
			party.setEnquiry_id(iMaster.getEnquiry_id());
			party.setEnquiry_no(iMaster.getEnquiry_no());
			party.setP_name(cust_bussiness_partnerRepository.getCustomer(party.getP_code()).getCp_name());
			party.setCompany_id(iMaster.getCompany_id());
			party.setFin_year(iMaster.getFin_year());
			party.setModified_type("INSERTED");
			party.setInserted_by(iMaster.getInserted_by());
			party.setInserted_on(iMaster.getInserted_on());
			party.setUpdated_by(iMaster.getUpdated_by());
			party.setUpdated_on(iMaster.getUpdated_on());
			party.setDeleted_by("NA");
			party.setDeleted_on(ldt);
		}
		iMaster.setSales_Enquiry_Party_Dtls(partySet);
		
		//Dynamic
		sales_Enquiry_DocsRepository.sales_Enquiry_Docsupdate(iMaster.getEnquiry_id());
		Set<Sales_Enquiry_Docs> docSet = new HashSet<Sales_Enquiry_Docs>();
		docSet.addAll(iMaster.getSales_Enquiry_Docs());
		for(Sales_Enquiry_Docs docDts : docSet)
		{
			docDts.setEnquiry_id(iMaster.getEnquiry_id());
			docDts.setEnquiry_no(iMaster.getEnquiry_no());
			docDts.setCompany_id(iMaster.getCompany_id());
			docDts.setFin_year(iMaster.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(iMaster.getInserted_by());
			docDts.setInserted_on(iMaster.getInserted_on());
			docDts.setUpdated_by(iMaster.getUpdated_by());
			docDts.setUpdated_on(iMaster.getUpdated_on());
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		
		}
		iMaster.setSales_Enquiry_Docs(docSet);
		
		return sales_EnquiryRepository.save(iMaster);
	}
	
	public Sales_Enquiry findOne(long id) {
		Optional<Sales_Enquiry> op=this.sales_EnquiryRepository.findById(id);
		return op.get();
	}
	
	public List<Sales_Enquiry> findAll()
	{
		List<Sales_Enquiry> enqList=new ArrayList<Sales_Enquiry>();
		enqList.addAll(sales_EnquiryRepository.findSalesEnquirys());
		for(int i=0;i<enqList.size();i++) {
			if(enqList.get(i).getBusinessunit().compareTo("0") !=0 && enqList.get(i).getBusinessunit() !=null) {
				enqList.get(i).setBusinessunit(companyBusinessUnitMasterRepository.CompanyBUAddress(enqList.get(i).getBusinessunit()).getBusinessunit_name());
			}
			else {
				enqList.get(i).setBusinessunit("None");
			}
			if(enqList.get(i).getSales_person().compareTo("0") !=0 && enqList.get(i).getSales_person().compareTo("") !=0 && enqList.get(i).getSales_person() !=null) {
				enqList.get(i).setSales_person(employeeMasterRepository.getEmployee(enqList.get(i).getSales_person()).getEmp_name());
			}else {enqList.get(i).setSales_person("None");}
		}
		return enqList;
	}
	
	@Transactional
	public SequenceIdDTO getSequenceId(String prefix)
	{
		Long slno=0L;
		if(uniqueidRepository.getSequenceId() != null ) {
			slno=Long.parseLong(uniqueidRepository.getSequenceId());
		}
		Long k=(long) String.valueOf(slno).length();
		UniqueIDGenerator u = new UniqueIDGenerator(); 
		String x=u.getUniqueID();
		
		Uniqueid uniqueid=new Uniqueid();
		uniqueid.setSid((slno+1));
		uniqueid.setUid(x +(k+1));
		System.err.println("Got Val ID:"+uniqueid.getId() +"Sid: "+uniqueid.getSid()+"Uid: "+uniqueid.getUid());
		uniqueidRepository.save(uniqueid);
		
		String gen_sno=UniqueIDTransaction.uniqueid(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}

	public List<Sales_EnquiryDTO> salesEnquiryList() {
		
		List<Sales_Enquiry> modelList=sales_EnquiryRepository.salesEnquiryList();
		
		Type listType = new TypeToken<List<Sales_EnquiryDTO>>() {}.getType();
		
		List<Sales_EnquiryDTO> salesEnq = new ModelMapper().map(modelList,listType);
		
		return salesEnq;
	}
	
	public List<Sales_EnquiryDTO> getSalesEnquiriesOpen(String qdate) {
		
		List<Sales_Enquiry> modelList=sales_EnquiryRepository.getSalesEnquiriesOpen(qdate);
		
		Type listType = new TypeToken<List<Sales_EnquiryDTO>>() {}.getType();
		
		List<Sales_EnquiryDTO> salesEnqOpen = new ModelMapper().map(modelList,listType);
		
		return salesEnqOpen;
	}
	
	public Sales_EnquiryDTO salesEnquiryByEnqId(String enquiry_id) {
		
		Sales_Enquiry modelList=sales_EnquiryRepository.salesEnquiryByEnqId(enquiry_id);
		
		Type listType = new TypeToken<Sales_EnquiryDTO>() {}.getType();
		
		Sales_EnquiryDTO salesEnq = new ModelMapper().map(modelList,listType);
		
		return salesEnq;
	}
	
	public List<Sales_Enquiry_Item_DtlsDTO> getSalesEnqItemDtls(String code)
	{
		List<Sales_Enquiry_Item_Dtls> modelList=sales_Enquiry_Item_DtlsRepository.getSalesEnqItemDtls(code);
		
		Type listType=new TypeToken<List<Sales_Enquiry_Item_DtlsDTO>>() {}.getType();
		
		List<Sales_Enquiry_Item_DtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public Sales_EnquiryDTO salesEnqPersonList(String code)
	 {
		Sales_Enquiry modelList=sales_EnquiryRepository.salesEnqPersonList(code);
		 Type listType = new TypeToken<Sales_EnquiryDTO>() {}.getType();

		 Sales_EnquiryDTO salesEnqPerson= new ModelMapper().map(modelList,listType);
			
		return salesEnqPerson;
	 }
	
	public List<Sales_Enquiry_Party_DtlsDTO> getSalesEnqPartyDtls(String code)
	{
		List<Sales_Enquiry_Party_Dtls> modelList=sales_Enquiry_Party_DtlsRepository.getSalesEnqPartyDtls(code);
		
		Type listType=new TypeToken<List<Sales_Enquiry_Party_DtlsDTO>>() {}.getType();
		
		List<Sales_Enquiry_Party_DtlsDTO> salesPartyList=new ModelMapper().map(modelList,listType);
		
		return salesPartyList;
	}
	
	public List<Sales_Enquiry_DocsDTO> getSalesEnqDoc(String enquiry_id)
	{
		List<Sales_Enquiry_Docs> modelList=sales_Enquiry_DocsRepository.getSalesEnqDoc(enquiry_id);
		
			
		Type listType=new TypeToken<List<Sales_Enquiry_DocsDTO>>() {}.getType();
		
		List<Sales_Enquiry_DocsDTO> salesPartyList=new ModelMapper().map(modelList,listType);
		
		return salesPartyList;
	}
	
}
