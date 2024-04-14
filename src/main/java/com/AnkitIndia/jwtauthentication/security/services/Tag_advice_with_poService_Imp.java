package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Tag_advice_with_po;
import com.AnkitIndia.jwtauthentication.model.Tag_advice_withpo_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Tag_advice_with_poRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_poDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tag_advice_with_po_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;

@Service
@Repository
public class Tag_advice_with_poService_Imp implements Tag_advice_with_poService{

	@Autowired
	Tag_advice_with_poRepository tag_advice_with_poRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Wm_unload_advice_broker_dtlsRepository wm_unload_advice_broker_dtlsRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getTagAdvPOSequenceId(String prefix)
	{
		int slno=0;
	//	String sno=tag_advice_with_poRepository.countTagAdvOrder();
		String sno=tag_advice_with_poRepository.countId();
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
			
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Tag_advice_with_po save(Tag_advice_with_po tWith_po)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(tag_advice_with_poRepository.countId() != null ) {
			slno=Long.parseLong(tag_advice_with_poRepository.countId());
		}
		String prefix="ATPO";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		tWith_po.setAdv_po_tag_id(gen_sno);
		
		if(tWith_po.getAdvice_id().compareTo("0") != 0 && tWith_po.getAdvice_id().compareTo("") != 0 && tWith_po.getAdvice_id() != null) {
			tWith_po.setAdvice_no(wm_unload_adviceRepository.getUnloadDetails(tWith_po.getAdvice_id()).getUnadviceno());
		}else {tWith_po.setAdvice_no("None");}
		
		if(tWith_po.getItem_subtype().compareTo("0") != 0 && tWith_po.getItem_subtype().compareTo("") != 0 && tWith_po.getItem_subtype() != null) {
			tWith_po.setItem_subtype_name(item_type_masterRepository.getItemType(tWith_po.getItem_subtype()).getItem_name());
		}else {tWith_po.setItem_subtype_name("None");}
		
		if(tWith_po.getBusi_partner().compareTo("0") !=0 && tWith_po.getBusi_partner().compareTo("") !=0 && tWith_po.getBusi_partner() !=null) {
			tWith_po.setBusi_partnername(supp_bussiness_partnerRepository.getSupplierName(tWith_po.getBusi_partner()).getBp_name());
		}else {tWith_po.setBusi_partnername("None");}
		
		if(tWith_po.getBusiness_unit().compareTo("0") !=0 && tWith_po.getBusiness_unit().compareTo("") !=0 && tWith_po.getBusiness_unit() !=null) {
			tWith_po.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(tWith_po.getBusiness_unit()).getBusinessunit_name());
		}else {tWith_po.setBusiness_unitname("None");}
		
		if(tWith_po.getVehicle_id().compareTo("0") !=0 && tWith_po.getVehicle_id().compareTo("") !=0 && tWith_po.getVehicle_id() !=null) {
			tWith_po.setVeh_no(vehicleMasterRepository.getVehicleDetails(tWith_po.getVehicle_id()).getVehicle_no());
		}else {tWith_po.setVeh_no("None");}
		
		System.out.println("getTransporter_code/"+tWith_po.getTransporter_code());
		
		//if(tWith_po.getTransporter_code().compareTo("0") !=0 && tWith_po.getTransporter_code().compareTo("") !=0 && tWith_po.getTransporter_code() !=null) {
		if((tWith_po.getTransporter_code() == null) || (tWith_po.getTransporter_code().compareTo("0") ==0 )||(tWith_po.getTransporter_code().compareTo("") ==0 ))   {
			tWith_po.setTransporter_name("None");
		}else {tWith_po.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(tWith_po.getTransporter_code()).getBp_name());}
		
		if(tWith_po.getPo_number().compareTo("0") !=0 && tWith_po.getPo_number().compareTo("") !=0 && tWith_po.getPo_number() !=null) {
			tWith_po.setPur_orderno(pur_OrderRepository.getPurOrdDetails(tWith_po.getPo_number()).getPur_order_no());
		}else {tWith_po.setPur_orderno("None");}
		
		//String purchasesubtype=pur_OrderRepository.getsubtype(tWith_po.getPo_number());
		//System.out.println("getPo_number :: " + tWith_po.getPo_number());
		List<Pur_Order> subtypelist=pur_OrderRepository.getsubtype(tWith_po.getPo_number());
		
		
		String purchasesubtype=subtypelist.get(0).getPur_ord_type();
		
		String materialtype=subtypelist.get(0).getSer_item_subtype_name();
		
		//System.out.println("purchasesubtype :: " + purchasesubtype);
		wm_unload_adviceRepository.updateTagAdvicePO(tWith_po.getAdvice_id(),tWith_po.getPo_number(),tWith_po.getAdv_po_tag_no(),tWith_po.getUla_date(),purchasesubtype);
		
		if(materialtype.compareToIgnoreCase("RAW MATERIALS") == 0) 
		{
			boolean weihmentreq=true;
			pur_OrderRepository.updateTagAdvStatusraw(tWith_po.getPo_number(),weihmentreq);
		}
		else 
		{
			pur_OrderRepository.updateTagAdvStatus(tWith_po.getPo_number());
		}
		
		
		tWith_po.setModified_type("INSERTED");
		tWith_po.setInserted_by("xxxx");
		tWith_po.setInserted_on(ldt);
		tWith_po.setUpdated_by("NA");
		tWith_po.setUpdated_on(ldt);
		tWith_po.setDeleted_by("NA");
		tWith_po.setDeleted_on(ldt);
		
		System.out.println(tWith_po.getAdvice_id() +" / " + tWith_po.getAdvice_no());
		
		Set<Tag_advice_withpo_broker_dtls> brokerSet = new HashSet<Tag_advice_withpo_broker_dtls>();
		brokerSet.addAll(tWith_po.getTag_advice_withpo_broker_dtls());
		for(Tag_advice_withpo_broker_dtls broker_dtls : brokerSet)
		{
			broker_dtls.setUnadviceid(tWith_po.getAdvice_id());
			broker_dtls.setUnadviceno(tWith_po.getAdvice_no());
			broker_dtls.setAdv_po_tag_id(tWith_po.getAdv_po_tag_id());
			broker_dtls.setAdv_po_tag_no(tWith_po.getAdv_po_tag_no());
			broker_dtls.setVen_name(broker_masterRepository.brokerNameByCode(broker_dtls.getVen_code_name()).getName());
		
			wm_unload_advice_broker_dtlsRepository.updatezerobroker(tWith_po.getAdvice_id());
			
			
			 Wm_unload_advice_broker_dtls wm_unload_advice_broker_dtls=new Wm_unload_advice_broker_dtls(); 
			 wm_unload_advice_broker_dtls.setBrokerage_acc(broker_dtls.getBrokerage_acc());
			 wm_unload_advice_broker_dtls.setBasis(broker_dtls.getBasis());
			 wm_unload_advice_broker_dtls.setSl_no(broker_dtls.getSl_no());
			 wm_unload_advice_broker_dtls.setRate(broker_dtls.getRate());
			 wm_unload_advice_broker_dtls.setTds_acc(broker_dtls.getTds_acc());
			 wm_unload_advice_broker_dtls.setTds_rate(broker_dtls.getTds_rate());
			 wm_unload_advice_broker_dtls.setVen_code_name(broker_dtls.getVen_code_name());
			 wm_unload_advice_broker_dtls.setVen_name(broker_masterRepository.brokerNameByCode(broker_dtls.getVen_code_name()).getName());
			 wm_unload_advice_broker_dtls.setUnadviceno(broker_dtls.getUnadviceno());
			 wm_unload_advice_broker_dtls.setUnadviceid(broker_dtls.getUnadviceid());
			 wm_unload_advice_broker_dtls.setModified_type("INSERTED");
			
			 wm_unload_advice_broker_dtls.setCompany_id(tWith_po.getCompany_id());
			 wm_unload_advice_broker_dtls.setFin_year(tWith_po.getFin_year());
			 
			 wm_unload_advice_broker_dtls.setInserted_by("xxxx");
			 wm_unload_advice_broker_dtls.setInserted_on(ldt);
			 wm_unload_advice_broker_dtls.setUpdated_by("NA");
			 wm_unload_advice_broker_dtls.setUpdated_on(ldt);
			 wm_unload_advice_broker_dtls.setDeleted_by("NA");
			 wm_unload_advice_broker_dtls.setDeleted_on(ldt);
			 
			 
			 broker_dtls.setWm_unload_advice_broker_dtls(wm_unload_advice_broker_dtls);
		
		}
		tWith_po.setTag_advice_withpo_broker_dtls(brokerSet);
		
		
		
		
		
		return tag_advice_with_poRepository.save(tWith_po);
	}
	
	public Tag_advice_with_po findOne(long id)
	{
		Optional<Tag_advice_with_po> op=this.tag_advice_with_poRepository.findById(id);
		return op.get();
	}
	
	
	public List<Tag_advice_with_poDTO> getTagAdvWithPO(String company)
	{
		List<Tag_advice_with_po> tafPolist=new ArrayList<Tag_advice_with_po>();
		tafPolist.addAll(tag_advice_with_poRepository.getTagAdvWithPO(company));
		
		Type listType=new TypeToken<List<Tag_advice_with_poDTO>>() {}.getType();
		List<Tag_advice_with_poDTO> tagList=new ModelMapper().map(tafPolist,listType);
		
		return tagList;
	}
	
	
	
	public Page<Tag_advice_with_po_Pagination_DTO> gettaggedadvice_pagination(int page,int size)
	{

		  
		//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
		PageRequest pageRequest = PageRequest.of(page, size,Sort.by("uladate").descending().and(Sort.by("purorderno")).descending().and(Sort.by("adviceno")).descending());
	    Page<Tag_advice_with_po> pageResult = tag_advice_with_poRepository.findAll(pageRequest);
	   
	    List<Tag_advice_with_po_Pagination_DTO> advList = pageResult
	    	      .stream()
	    	      .map((Tag_advice_with_po tag_advice) -> new Tag_advice_with_po_Pagination_DTO(tag_advice.getId(),
	    	    		  tag_advice.getAdv_po_tag_no(),
	    	    		  tag_advice.getAdvice_no(),
	    	    		  tag_advice.getUla_date(),
	    	    		  tag_advice.getItem_subtype_name(),
	    	    		  tag_advice.getBusi_partnername(),
	    	    		  tag_advice.getBusiness_unitname(),
	    	    		  tag_advice.getPur_orderno(),
	    	    		  tag_advice.getVeh_no(),
	    	    		  tag_advice.getModified_type())).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
	    	      
	    
	  
			
	   
	    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
  
	}
	
	public List<Map<String,Object>> searchtaggedadviceFast(String orderno,String pono,String fromdate, String todate,String bus_partner1,String finyear)
	{
		
		List<Map<String,Object>> tag_advice =new ArrayList<Map<String,Object>>();
		
		String tablename="tag_advice_with_po",party_name="busi_partner",order_no="advice_no",order_date="ula_date",po_no="pur_orderno";
		
		tag_advice.addAll(tag_advice_with_poRepository.getsearchdataFast(tablename,party_name,order_no,po_no,order_date,orderno,pono,fromdate,todate,bus_partner1,finyear));
		
		return tag_advice;
	}
	
	public List<Tag_advice_with_po_Pagination_DTO> searchtaggedadvice(String orderno,String pono,String fromdate, String todate,String bus_partner1,String finyear)
	{
		

		
		List<Tag_advice_with_po> tag_advice =new ArrayList<Tag_advice_with_po>();
	//	System.out.println("finyear :: "+finyear);
		String tablename="tag_advice_with_po",party_name="busi_partner",order_no="advice_no",order_date="ula_date",po_no="pur_orderno";
	//	System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" bus_partner1 "+bus_partner1+" finyear "+finyear);
	
		//
		tag_advice.addAll(tag_advice_with_poRepository.getsearchdata(tablename,party_name,order_no,po_no,order_date,orderno,pono,fromdate,todate,bus_partner1,finyear));
		
		//System.out.println(tag_advice.size());
		List<Tag_advice_with_po_Pagination_DTO> unAdvList = new ArrayList<Tag_advice_with_po_Pagination_DTO>();
		for(int i=0;i<tag_advice.size();i++) 
		{
		
			Tag_advice_with_po_Pagination_DTO sale= new Tag_advice_with_po_Pagination_DTO(
				  tag_advice.get(i).getId(),
				  tag_advice.get(i).getAdv_po_tag_no(),
   	    		  tag_advice.get(i).getAdvice_no(),
   	    		  tag_advice.get(i).getUla_date(),
   	    		  tag_advice.get(i).getItem_subtype_name(),
   	    		  tag_advice.get(i).getBusi_partnername(),
   	    		  tag_advice.get(i).getBusiness_unitname(),
   	    		  tag_advice.get(i).getPur_orderno(),
   	    		  tag_advice.get(i).getVeh_no(),
   	    		  tag_advice.get(i).getModified_type());
			
			unAdvList.add(sale);
		}
		
		
		List<Tag_advice_with_po_Pagination_DTO> allData = unAdvList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Tag_advice_with_po_Pagination_DTO::getUla_date).
						  thenComparingInt(
                        d -> d.getUla_date().length() + d.getPur_orderno().length() + d.getAdvice_no().length())
                .thenComparing(Tag_advice_with_po_Pagination_DTO::getPur_orderno).reversed()
                .thenComparing(Tag_advice_with_po_Pagination_DTO::getAdvice_no).reversed())
				  .collect(Collectors.toList());
		
		return allData;
		
	
		
		
		
		
	}
	
	@Transactional
	public Tag_advice_with_po deleteTagAdvWithPO (Tag_advice_with_po tagAdvice,Long id)
	{
		System.out.println("enter ");
		Optional<Tag_advice_with_po> op = tag_advice_with_poRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Tag_advice_with_po tag_po=op.get();
		
		
		tag_po.setInserted_by(op.get().getInserted_by());
		tag_po.setInserted_on(op.get().getInserted_on());
		tag_po.setUpdated_by("NA");
		tag_po.setUpdated_on(ldt);
		tag_po.setInserted_by(userRepository.getUserDetails(tagAdvice.getUsername()).getName());
		tag_po.setDeleted_on(ldt);
		tag_po.setModified_type("DELETED");

		tag_advice_with_poRepository.tag_advice_withpo_broker_dtlsupdate(op.get().getAdv_po_tag_id());
		
		//unlod starts here
		
			wm_unload_adviceRepository.updateTagAdvicePODeleteTime(op.get().getAdvice_id()); //Unload advice Static revert
			wm_unload_adviceRepository.updateUnAdvItemDetails(op.get().getAdvice_id()); //Unload advice Iten  revert
		
		
				pur_OrderRepository.updateTagAdvStatusrevert(op.get().getPo_number()); //Purchase Order Static revert
			
		
		//pur ord ends here
		
		if(op.isPresent()) {
			tag_po.setId(id);
		}
		return  tag_advice_with_poRepository.save(tag_po);
	}

	public StatusDTO checkTagAdvicePoUsage(String adviceno)
	{
		//String result = tag_advice_with_poRepository.checkTagAdvicePoUsage(adviceno);
		//SELECT CASE WHEN COUNT(id)>0 THEN 'Yes' ELSE 'NO' END AS res FROM pur_good_receipt WHERE modified_type='INSERTED' AND  referance_id =(SELECT unadviceid FROM wm_unload_advice WHERE  modified_type='INSERTED' AND unadviceno='UA-FLO-100423-0005')
		
        // String result=tag_advice_with_poRepository.checkTagAdvicePoUsagresponse(adviceno);
        		 
        StatusDTO res =new StatusDTO();	 
		res.setStatus(tag_advice_with_poRepository.checkTagAdvicePoUsagresponse(adviceno));
 
		return res;
	}
	public StatusDTO checkTagAdvicePoUsageingrn(String pur_orderno) 
	{
		
		String po_id=pur_OrderRepository.getPurOrdDetailsbyno(pur_orderno).getPur_orderid();
		System.out.println("po_id :: " + po_id);
		int checkusage=tag_advice_with_poRepository.checkTagAdvicePoUsageingrn(po_id);
		
		StatusDTO statusDTO= new StatusDTO();
		System.out.println(" checkusage "+ checkusage);
		if(checkusage==1) 
		{
			statusDTO.setStatus("Yes");	
		}
		else 
		{
			statusDTO.setStatus("No");
		}
		
		
		return statusDTO;
	}
	
	 public List<Map<String,Object>> getTagAdviceWithPoList(String currDate,String finyear)
		{
		 	List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 	
			modelList.addAll(tag_advice_with_poRepository.getTagAdviceWithPoList(currDate,finyear));

			return modelList;
		}
	
}
