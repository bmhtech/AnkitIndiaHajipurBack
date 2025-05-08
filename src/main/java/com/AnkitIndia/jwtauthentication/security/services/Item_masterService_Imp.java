package com.AnkitIndia.jwtauthentication.security.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Item_maintain;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_alternative_dtls;
import com.AnkitIndia.jwtauthentication.model.Item_master_classification;
import com.AnkitIndia.jwtauthentication.model.Item_master_inv_data1;
import com.AnkitIndia.jwtauthentication.model.Item_master_inv_data2;
import com.AnkitIndia.jwtauthentication.model.Item_master_other_info;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Item_master_qc_details;
import com.AnkitIndia.jwtauthentication.model.Item_master_size_weight;
import com.AnkitIndia.jwtauthentication.model.Item_master_stat_info;
import com.AnkitIndia.jwtauthentication.model.Item_master_stock_details;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ItemRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_catagory_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_maintainRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_alternative_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_classificationRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_inv_data1Repository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_inv_data2Repository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_other_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_pack_mat_tagRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_qc_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_size_weightRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_stat_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_opening_stk_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Process_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Qc_rules_setupRepository;
import com.AnkitIndia.jwtauthentication.repository.System_settingsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_alternative_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data1DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data2DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_other_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_stat_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Paging_SortingDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_pack_mat_tagDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_qc_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_masterstatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import com.AnkitIndia.Exporttotally.Tallyrequest_ItemMaster;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import java.util.stream.Collectors;

@Service
@Repository
public class Item_masterService_Imp implements Item_masterService {
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired

	Item_master_stat_infoRepository item_master_stat_infoRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	Item_master_pack_mat_tagRepository item_master_pack_mat_tagRepository;
	
	@Autowired
	Item_group_masterRepository item_group_masterRepository;
	
	@Autowired
	Item_catagory_masterRepository itemCatRepository;
	
	@Autowired
	Item_master_inv_data1Repository item_master_inv_data1Repository;
	
	@Autowired
	Item_master_other_infoRepository item_master_other_infoRepository;
	
	@Autowired
	Item_master_inv_data2Repository item_master_inv_data2Repository;
	
	@Autowired
	Qc_rules_setupRepository qc_rules_setupRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Item_master_qc_detailsRepository item_master_qc_detailsRepository;
	
	@Autowired
	Item_master_alternative_dtlsRepository item_master_alternative_dtlsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Item_maintainRepository item_maintainRepository;
	
	@Autowired
	Item_opening_stk_dtlsRepository item_opening_stk_dtlsRepository;
	
	@Autowired
	System_settingsRepository system_settingsRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	Process_masterRepository process_masterRepository;
	
	@Autowired
	Item_master_classificationRepository item_master_classificationRepository;
	
	@Autowired
	Item_master_size_weightRepository item_master_size_weightRepository;
	
	public SequenceIdDTO getItemSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		Optional<String> gen_sno=item_masterRepository.getItemSequenceId(fprefix,company);
		
		if(gen_sno.isPresent()) {
			slno=Long.parseLong(gen_sno.get());
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	
	public List<Item_maintain> getItemOpeningStock(String bunit,String itype,String tdate){
		List<Item_maintain> itemStkList=new ArrayList<Item_maintain>();
		itemStkList.addAll(item_maintainRepository.getItemMaintains(bunit,itype));

		return itemStkList;
	}
	
	public List<Item_masterDTO> getItemOpening(String bunit,String itype,String tdate){
		
		List<Item_master> itemList=new ArrayList<Item_master>();
		itemList.addAll(item_masterRepository.findAll());
		
		List<Item_master> allItem = itemList
				  .parallelStream()
				  .filter(c -> c.getItem_type().equals(itype) && c.getItem_unit().contains(bunit) 
						  && c.getModified_type().contains("INSERTED")) // && c.isStk_status()==false
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> items = new ModelMapper().map(allItem,listType);
		return items;
	}
	
	@Transactional
	public Item_master saveItem(Item_master item_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		if(system_settingsRepository.getSystemSettingsByComp(item_master.getCompany_id()).get().isCode_generator()) {
			/*Start checking transaction no for unique */
			long nslno=0;String tprefix=item_master.getItem_code();
			Optional<String> gen_snonew=item_masterRepository.getItemSequenceId(tprefix.substring(0,7),item_master.getCompany_id());
			if(gen_snonew.isPresent()) {
				nslno=Long.parseLong(gen_snonew.get());
			}
			String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,7),nslno);
			item_master.setItem_code(gen_tslno);
			/*End checking transaction no for unique */
		}else {
			if(chkItemCodeStatus(item_master.getItem_code()).getStatus().compareTo("EXIST")==0) {
				
			}else {
				
			}
		}
		
		long slno=0;String prefix="IM";
		if(item_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(item_masterRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		item_master.setItem_id(gen_sno);
		item_master.setGroup_main_id(item_masterRepository.getMainGroup(item_master.getItem_group()));
		
	//	item_master.setSub_group_name(ledgermasterRepository.getLedgerDetails(item_master.getSub_group()).getLedgername());
		
		if(Utility.isNullOrEmpty(item_master.getItem_group())) {
			item_master.setItem_group_name(item_group_masterRepository.getItemGroupUom(item_master.getItem_group()).getGroup_name());
		}else {item_master.setItem_group_name("None");}
		
		if(Utility.isNullOrEmpty(item_master.getMstock_unit())) {
			item_master.setMstock_unit_name(customUomMasterRepository.getUomByIGroup(item_master.getMstock_unit()).getDescription());
		}else {item_master.setMstock_unit_name("None");}
		
		if(Utility.isNullOrEmpty(item_master.getItem_category())) {
			item_master.setItem_category_name(itemCatRepository.catagoryNameByCode(item_master.getItem_category()).getCatagory_name());
		}else {item_master.setItem_category_name("None");}
		
		if(Utility.isNullOrEmpty(item_master.getItem_unit())) {}else {item_master.setItem_unit("");}
		
		item_master.setModified_type("INSERTED");
		item_master.setInserted_by(userRepository.getUserDetails(item_master.getUsername()).getName());
		item_master.setInserted_on(ldt);
		item_master.setUpdated_by("NA");
		item_master.setUpdated_on(ldt);
		item_master.setDeleted_by("NA");
		item_master.setDeleted_on(ldt);
		item_master.setInsert_status(false);
		item_master.setStk_status(false);
	
		Set<Item_master_stat_info> itemSet = new HashSet<Item_master_stat_info>();
		itemSet.addAll(item_master.getItem_master_stat_infos());
		for(Item_master_stat_info itemDts : itemSet)
		{
			itemDts.setItem_id(gen_sno);
			itemDts.setItem_code(item_master.getItem_code());
			itemDts.setCompany_id(item_master.getCompany_id());
			itemDts.setFin_year(item_master.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(item_master.getInserted_by());
			itemDts.setInserted_on(ldt);
			itemDts.setUpdated_by("NA");
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		}
		item_master.setItem_master_stat_infos(itemSet);
		
		Set<Item_master_inv_data1> data1Set = new HashSet<Item_master_inv_data1>();
		data1Set.add(item_master.getItem_master_inv_data1());
		for(Item_master_inv_data1 inv_data1 : data1Set)
		{
			inv_data1.setItem_id(gen_sno);
			inv_data1.setItem_code(item_master.getItem_code());
			inv_data1.setCompany_id(item_master.getCompany_id());
			inv_data1.setFin_year(item_master.getFin_year());
			inv_data1.setModified_type("INSERTED");
			inv_data1.setInserted_by(item_master.getInserted_by());
			inv_data1.setInserted_on(ldt);
			inv_data1.setUpdated_by("NA");
			inv_data1.setUpdated_on(ldt);
			inv_data1.setDeleted_by("NA");
			inv_data1.setDeleted_on(ldt);
		}
		if(!data1Set.isEmpty()) {
			item_master.setItem_master_inv_data1(data1Set.iterator().next());
		}
		
		Set<Item_master_inv_data2> data2Set = new HashSet<Item_master_inv_data2>();
		data2Set.add(item_master.getItem_master_inv_data2());
		for(Item_master_inv_data2 inv_data2 : data2Set)
		{
			inv_data2.setItem_id(gen_sno);
			inv_data2.setItem_code(item_master.getItem_code());
			inv_data2.setCompany_id(item_master.getCompany_id());
			inv_data2.setFin_year(item_master.getFin_year());
			inv_data2.setModified_type("INSERTED");
			inv_data2.setInserted_by(item_master.getInserted_by());
			inv_data2.setInserted_on(ldt);
			inv_data2.setUpdated_by("NA");
			inv_data2.setUpdated_on(ldt);
			inv_data2.setDeleted_by("NA");
			inv_data2.setDeleted_on(ldt);
		}
		if(!data2Set.isEmpty())	{
			item_master.setItem_master_inv_data2(data2Set.iterator().next());
		}
		
		Set<Item_master_other_info> infoSet = new HashSet<Item_master_other_info>();
		infoSet.add(item_master.getItem_master_other_info());
		for(Item_master_other_info info : infoSet)
		{
			info.setItem_id(gen_sno);
			info.setItem_code(item_master.getItem_code());
			info.setCompany_id(item_master.getCompany_id());
			info.setFin_year(item_master.getFin_year());
			info.setModified_type("INSERTED");
			info.setInserted_by(item_master.getInserted_by());
			info.setInserted_on(ldt);
			info.setUpdated_by("NA");
			info.setUpdated_on(ldt);
			info.setDeleted_by("NA");
			info.setDeleted_on(ldt);
		}
		if(!infoSet.isEmpty())	{
			item_master.setItem_master_other_info(infoSet.iterator().next());
		}
		
		
		Set<Item_master_stock_details> itemstock = new HashSet<Item_master_stock_details>();
		itemstock.addAll(item_master.getItem_master_stock_details());
		for(Item_master_stock_details stock : itemstock)
		{
			stock.setItem_id(gen_sno);
			stock.setItem_code(item_master.getItem_code());
			stock.setCompany_id(item_master.getCompany_id());
			stock.setFin_year(item_master.getFin_year());
			stock.setModified_type("INSERTED");
			stock.setInserted_by(item_master.getInserted_by());
			stock.setInserted_on(ldt);
			stock.setUpdated_by("NA");
			stock.setUpdated_on(ldt);
			stock.setDeleted_by("NA");
			stock.setDeleted_on(ldt);
		}
		
		item_master.setItem_master_stock_details(itemstock);
		
		
		
		
		Set<Item_master_qc_details> qcSet = new HashSet<Item_master_qc_details>();
		qcSet.addAll(item_master.getItmItem_master_qc_details());
		for(Item_master_qc_details qcDts : qcSet)
		{
			qcDts.setItem_id(gen_sno);
			qcDts.setItem_code(item_master.getItem_code());
			if(Utility.isNullOrEmpty(qcDts.getQc_id())) {
				qcDts.setQc_description(qc_rules_setupRepository.getQcrulesDetails(qcDts.getQc_id()).getQc_name());
			}
			qcDts.setCompany_id(item_master.getCompany_id());
			qcDts.setFin_year(item_master.getFin_year());
			qcDts.setModified_type("INSERTED");
			qcDts.setInserted_by(item_master.getInserted_by());
			qcDts.setInserted_on(ldt);
			qcDts.setUpdated_by("NA");
			qcDts.setUpdated_on(ldt);
			qcDts.setDeleted_by("NA");
			qcDts.setDeleted_on(ldt);
		}
		item_master.setItmItem_master_qc_details(qcSet);
		
		Set<Item_master_alternative_dtls> altSet = new HashSet<Item_master_alternative_dtls>();
		altSet.addAll(item_master.getItem_master_alternative_dtls());
		for(Item_master_alternative_dtls itemAltDts : altSet)
		{
			itemAltDts.setItem_id(gen_sno);
			itemAltDts.setCompany_id(item_master.getCompany_id());
			itemAltDts.setFin_year(item_master.getFin_year());
			itemAltDts.setModified_type("INSERTED");
			itemAltDts.setInserted_by(item_master.getInserted_by());
			itemAltDts.setInserted_on(ldt);
			itemAltDts.setUpdated_by("NA");
			itemAltDts.setUpdated_on(ldt);
			itemAltDts.setDeleted_by("NA");
			itemAltDts.setDeleted_on(ldt);
		}
		item_master.setItem_master_alternative_dtls(altSet);
		
		Set<Item_master_pack_mat_tag> packSet = new HashSet<Item_master_pack_mat_tag>();
		packSet.addAll(item_master.getItem_master_pack_mat_tags());
		for(Item_master_pack_mat_tag packDts : packSet)
		{
			packDts.setItem_id(gen_sno);
			if(Utility.isNullOrEmpty(packDts.getItem_code())) {
				packDts.setItem_name(item_masterRepository.itemNameById(packDts.getItem_code()).getItem_name());
			}else {packDts.setItem_name("None");}
			packDts.setCompany_id(item_master.getCompany_id());
			packDts.setFin_year(item_master.getFin_year());
			packDts.setModified_type("INSERTED");
			packDts.setInserted_by(item_master.getInserted_by());
			packDts.setInserted_on(ldt);
			packDts.setUpdated_by("NA");
			packDts.setUpdated_on(ldt);
			packDts.setDeleted_by("NA");
			packDts.setDeleted_on(ldt);
		}
		item_master.setItem_master_pack_mat_tags(packSet);
		
		Set<Item_maintain> imSet = new HashSet<Item_maintain>();
		if(Utility.isNullOrEmpty(item_master.getItem_unit())) {
			String bunit[]=null;
			bunit=item_master.getItem_unit().split(",");
			for(int i=0;i<bunit.length;i++) {
				for(Item_master_pack_mat_tag packDts : packSet)
				{
					
					
					if(Utility.isNullOrEmpty(item_master.getStandard_rate())) 
					{
						
					}else 
					{
						item_master.setStandard_rate("0");
					}
					
					
					
					if(Utility.isNullOrEmpty(packDts.getItem_code())) {
						Item_maintain iMaintain=new Item_maintain(bunit[i],gen_sno,item_master.getItem_code(),packDts.getItem_code(),
								Double.parseDouble(item_master.getStandard_rate()),item_master.getMrp(), item_master.getItem_type(),
								item_master.getItem_group(),item_master.getItem_category(),item_master.isItem_active(),item_master.getFin_year(),item_master.getCompany_id(),
								item_master.getUsername(),"INSERTED",ldt,item_master.getInserted_by(),ldt,item_master.getUpdated_by());
						
						imSet.add(iMaintain);
					}
					else 
					{
						
						Item_maintain iMaintain=new Item_maintain(bunit[i],gen_sno,item_master.getItem_code(),"0",
								Double.parseDouble(item_master.getStandard_rate()),item_master.getMrp(), item_master.getItem_type(),
								item_master.getItem_group(),item_master.getItem_category(),item_master.isItem_active(),item_master.getFin_year(),item_master.getCompany_id(),
								item_master.getUsername(),"INSERTED",ldt,item_master.getInserted_by(),ldt,item_master.getUpdated_by());
						
						imSet.add(iMaintain);
					}
				}
			}
		}
		
		item_maintainRepository.saveAll(imSet);
		
		if(item_master.getItem_type().compareToIgnoreCase("STORE PURCHASE")==0)
		{
			Set<Item_master_classification> classification = new HashSet<Item_master_classification>();
			classification.addAll(item_master.getItem_master_classification());
			for(Item_master_classification classified : classification)
			{
				classified.setItem_id(gen_sno);
				classified.setCompany_id(item_master.getCompany_id());
				classified.setFin_year(item_master.getFin_year());
				classified.setModified_type("INSERTED");
				classified.setInserted_by(item_master.getInserted_by());
				classified.setInserted_on(ldt);
				classified.setUpdated_by("NA");
				classified.setUpdated_on(ldt);
				classified.setDeleted_by("NA");
				classified.setDeleted_on(ldt);
			}
			item_master.setItem_master_classification(classification);
		}
		else
		{
			item_master.getItem_master_classification().clear();
		}
		
		if(item_master.getItem_type().compareToIgnoreCase("PACKING ITEMS")==0)
		{
			Set<Item_master_size_weight> sizeandweight = new HashSet<Item_master_size_weight>();
			sizeandweight.addAll(item_master.getItem_master_size_weight());
			for(Item_master_size_weight size : sizeandweight)
			{
				size.setItem_id(gen_sno);
				if(Utility.isNullOrEmpty(size.getItem_code())) {
					size.setItem_name(item_masterRepository.itemNameById(size.getItem_code()).getItem_name());
				}else {size.setItem_name("None");}
				size.setCompany_id(item_master.getCompany_id());
				size.setFin_year(item_master.getFin_year());
				size.setModified_type("INSERTED");
				size.setInserted_by(item_master.getInserted_by());
				size.setInserted_on(ldt);
				size.setUpdated_by("NA");
				size.setUpdated_on(ldt);
				size.setDeleted_by("NA");
				size.setDeleted_on(ldt);
			}
			item_master.setItem_master_size_weight(sizeandweight);
		}
		else
		{
			item_master.getItem_master_size_weight().clear();
		}
		
		
		return item_masterRepository.save(item_master);
	}
	
	public List<Item_master> getItems(String company)
	{
		List<Item_master> itemList=item_masterRepository.findAll();
		
		List<Item_master> allItem = itemList
				  .parallelStream()
				  .filter(c -> c.getCompany_id().equals(company) && c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_id).reversed())
				  .collect(Collectors.toList());
			
		return allItem;
	}
	
	public List<Item_master> findAllItems()
	{
		List<Item_master> itemList=item_masterRepository.findAll();
		
		itemList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
		
		List<Item_master> allItem = itemList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		return allItem;
	}
	
	public List<Item_master> findItems(String company,String searchtext){
		
		List<Item_master> itemList=new ArrayList<Item_master>();
		itemList.addAll(item_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Item_master> allData = itemList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_master::getItem_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Item_master> allData = itemList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company) &&
							  (c.getItem_code()+c.getHsn_code()+c.getItem_name()+c.getItem_type()+c.getItem_group_name()+c.getMstock_unit_name()+c.getItem_category_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Item_master::getItem_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	@Transactional
	public Item_master update(Item_master iMaster,long id)
	{
		Optional<Item_master> op = item_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setGroup_main_id(item_masterRepository.getMainGroup(iMaster.getItem_group()));
		System.out.println("Ledger::"+iMaster.getSub_group());
		//iMaster.setSub_group_name(ledgermasterRepository.getLedgerDetails(iMaster.getSub_group()).getLedgername());
		
		if(Utility.isNullOrEmpty(iMaster.getItem_group())) {
			iMaster.setItem_group_name(item_group_masterRepository.getItemGroupUom(iMaster.getItem_group()).getGroup_name());
		}else {iMaster.setItem_group_name("None");}
		
		if(Utility.isNullOrEmpty(iMaster.getMstock_unit())) {
			iMaster.setMstock_unit_name(customUomMasterRepository.getUomByIGroup(iMaster.getMstock_unit()).getDescription());
		}else {iMaster.setMstock_unit_name("None");}
		
		if(Utility.isNullOrEmpty(iMaster.getItem_category())) {
			iMaster.setItem_category_name(itemCatRepository.catagoryNameByCode(iMaster.getItem_category()).getCatagory_name());
		}else {iMaster.setItem_category_name("None");}
		
		if(Utility.isNullOrEmpty(iMaster.getItem_unit())) {}else {iMaster.setItem_unit("");}
		
		iMaster.setExport(op.get().getExport());
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		iMaster.setInsert_status(false);
		iMaster.setStk_status(false);
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		item_master_stat_infoRepository.item_master_stat_infoupdate(op.get().getItem_id(),"UPDATED");

		Set<Item_master_stat_info> itemSet = new HashSet<Item_master_stat_info>();
		itemSet.addAll(iMaster.getItem_master_stat_infos());
		for(Item_master_stat_info itemDts : itemSet)
		{
			itemDts.setItem_id(op.get().getItem_id());
			itemDts.setItem_code(iMaster.getItem_code());
			itemDts.setCompany_id(iMaster.getCompany_id());
			itemDts.setFin_year(iMaster.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(iMaster.getInserted_by());
			itemDts.setInserted_on(iMaster.getInserted_on());
			itemDts.setUpdated_by(iMaster.getUpdated_by());
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
			itemDts.setInsert_status(true);
		}
		iMaster.setItem_master_stat_infos(itemSet);
		
		
		//opening stock 
	
		item_masterRepository.item_master_stock_detailsrest(op.get().getItem_id());
		Set<Item_master_stock_details> itemstock = new HashSet<Item_master_stock_details>();
		itemstock.addAll(iMaster.getItem_master_stock_details());
		for(Item_master_stock_details stock : itemstock)
		{
			stock.setItem_id(op.get().getItem_id());
			stock.setItem_code(iMaster.getItem_code());
			stock.setCompany_id(iMaster.getCompany_id());
			stock.setFin_year(iMaster.getFin_year());
			stock.setModified_type("INSERTED");
			stock.setInserted_by(iMaster.getInserted_by());
			stock.setInserted_on(iMaster.getInserted_on());
			stock.setUpdated_by(iMaster.getUpdated_by());
			stock.setUpdated_on(ldt);
			stock.setDeleted_by("NA");
			stock.setDeleted_on(ldt);
		}
		
		iMaster.setItem_master_stock_details(itemstock);
		
		
		//Static
		item_master_inv_data1Repository.getItem_master_inv_data1Update(op.get().getItem_id(),"UPDATED");
		
		Set<Item_master_inv_data1> data1Set = new HashSet<Item_master_inv_data1>();
		data1Set.add(iMaster.getItem_master_inv_data1());
		for(Item_master_inv_data1 inv_data1 : data1Set)
		{
			inv_data1.setItem_id(op.get().getItem_id());
			inv_data1.setItem_code(iMaster.getItem_code());
			inv_data1.setCompany_id(iMaster.getCompany_id());
			inv_data1.setFin_year(iMaster.getFin_year());
			inv_data1.setModified_type("INSERTED");
			inv_data1.setInserted_by(iMaster.getInserted_by());
			inv_data1.setInserted_on(iMaster.getInserted_on());
			inv_data1.setUpdated_by(iMaster.getUpdated_by());
			inv_data1.setUpdated_on(ldt);
			inv_data1.setDeleted_by("NA");
			inv_data1.setDeleted_on(ldt);
			inv_data1.setInsert_status(true);
			
		}
		if(!data1Set.isEmpty())	{
			iMaster.setItem_master_inv_data1(data1Set.iterator().next());
		}
		
		//Static
		item_master_inv_data2Repository.getItem_master_inv_data2Update(op.get().getItem_id(),"UPDATED");
		Set<Item_master_inv_data2> data2Set = new HashSet<Item_master_inv_data2>();
		data2Set.add(iMaster.getItem_master_inv_data2());
		for(Item_master_inv_data2 inv_data2 : data2Set)
		{
			inv_data2.setItem_id(op.get().getItem_id());
			inv_data2.setItem_code(iMaster.getItem_code());
			inv_data2.setCompany_id(iMaster.getCompany_id());
			inv_data2.setFin_year(iMaster.getFin_year());
			inv_data2.setModified_type("INSERTED");
			inv_data2.setInserted_by(iMaster.getInserted_by());
			inv_data2.setInserted_on(iMaster.getInserted_on());
			inv_data2.setUpdated_by(iMaster.getUpdated_by());
			inv_data2.setUpdated_on(ldt);
			inv_data2.setDeleted_by("NA");
			inv_data2.setDeleted_on(ldt);
			inv_data2.setInsert_status(true);
		}
		if(!data2Set.isEmpty())	{
			iMaster.setItem_master_inv_data2(data2Set.iterator().next());
		}
		
		//Static
		item_master_other_infoRepository.getItem_master_other_infoUpdate(op.get().getItem_id(),"UPDATED");
		Set<Item_master_other_info> infoSet = new HashSet<Item_master_other_info>();
		infoSet.add(iMaster.getItem_master_other_info());
		for(Item_master_other_info info : infoSet)
		{
			info.setItem_id(op.get().getItem_id());
			info.setItem_code(iMaster.getItem_code());
			info.setCompany_id(iMaster.getCompany_id());
			info.setFin_year(iMaster.getFin_year());
			info.setModified_type("INSERTED");
			info.setInserted_by(iMaster.getInserted_by());
			info.setInserted_on(iMaster.getInserted_on());
			info.setUpdated_by(iMaster.getUpdated_by());
			info.setUpdated_on(ldt);
			info.setDeleted_by("NA");
			info.setDeleted_on(ldt);
			info.setInsert_status(true);
		}
		if(!infoSet.isEmpty())	{
			iMaster.setItem_master_other_info(infoSet.iterator().next());
		}
			
		//Dynamic
		item_master_pack_mat_tagRepository.item_master_pack_mat_tagUpdate(op.get().getItem_id(),"UPDATED");
		
		Set<Item_master_pack_mat_tag> packSet = new HashSet<Item_master_pack_mat_tag>();
		packSet.addAll(iMaster.getItem_master_pack_mat_tags());
		for(Item_master_pack_mat_tag packDts : packSet)
		{
			packDts.setItem_id(op.get().getItem_id());
			if(Utility.isNullOrEmpty(packDts.getItem_code())) {
				packDts.setItem_name(item_masterRepository.itemNameById(packDts.getItem_code()).getItem_name());
			}else {packDts.setItem_name("None");}
			packDts.setCompany_id(iMaster.getCompany_id());
			packDts.setFin_year(iMaster.getFin_year());
			packDts.setModified_type("INSERTED");
			packDts.setInserted_by(iMaster.getInserted_by());
			packDts.setInserted_on(iMaster.getInserted_on());
			packDts.setUpdated_by(iMaster.getUpdated_by());
			packDts.setUpdated_on(ldt);
			packDts.setDeleted_by("NA");
			packDts.setDeleted_on(ldt);
			packDts.setInsert_status(true);
		}
		iMaster.setItem_master_pack_mat_tags(packSet);
		
		//Delete Item Maintain
		List<Item_maintain> itemStks=new ArrayList<Item_maintain>();
		itemStks.addAll(item_maintainRepository.getItemMaintains(op.get().getItem_id()));
		
		itemStks.forEach((iOrds) ->{
			item_maintainRepository.deleteItemMaintain(iOrds.getBusiness_unit(),iOrds.getItem_id(),iOrds.getPacking_id());
			//deleteItemMaintain(iOrds);
		});
		
		Set<Item_maintain> imSet = new HashSet<Item_maintain>();
		if(Utility.isNullOrEmpty(iMaster.getItem_unit())) {
			String bunit[]=null;
			bunit=iMaster.getItem_unit().split(",");
			for(int i=0;i<bunit.length;i++) {
				for(Item_master_pack_mat_tag packDts : packSet)
				{
					if(Utility.isNullOrEmpty(packDts.getItem_code())) {
						Item_maintain iMaintain=new Item_maintain(bunit[i],op.get().getItem_id(),iMaster.getItem_code(),packDts.getItem_code(),
								Double.parseDouble(iMaster.getStandard_rate()),iMaster.getMrp(), iMaster.getItem_type(),
								iMaster.getItem_group(),iMaster.getItem_category(),iMaster.isItem_active(),iMaster.getFin_year(),iMaster.getCompany_id(),
								iMaster.getUsername(),"INSERTED",ldt,iMaster.getInserted_by(),ldt,iMaster.getUpdated_by());
						
						imSet.add(iMaintain);
					}else {
						Item_maintain iMaintain=new Item_maintain(bunit[i],op.get().getItem_id(),iMaster.getItem_code(),packDts.getItem_code(),
								Double.parseDouble(iMaster.getStandard_rate()),iMaster.getMrp(), iMaster.getItem_type(),
								iMaster.getItem_group(),iMaster.getItem_category(),iMaster.isItem_active(),iMaster.getFin_year(),iMaster.getCompany_id(),
								iMaster.getUsername(),"INSERTED",ldt,iMaster.getInserted_by(),ldt,iMaster.getUpdated_by());
						
						imSet.add(iMaintain);
					}
				}
			}
		}
		item_maintainRepository.saveAll(imSet);
		
		//Dynamic
		item_master_qc_detailsRepository.getItmItem_master_qc_detailsUpdate(op.get().getItem_id(),"UPDATED");
		Set<Item_master_qc_details> qcSet = new HashSet<Item_master_qc_details>();
		qcSet.addAll(iMaster.getItmItem_master_qc_details());
		for(Item_master_qc_details qcDts : qcSet)
		{
			qcDts.setItem_id(op.get().getItem_id());
			qcDts.setItem_code(iMaster.getItem_code());
			if(Utility.isNullOrEmpty(qcDts.getQc_id())) {
				qcDts.setQc_description(qc_rules_setupRepository.getQcrulesDetails(qcDts.getQc_id()).getQc_name());
			}
			qcDts.setCompany_id(iMaster.getCompany_id());
			qcDts.setFin_year(iMaster.getFin_year());
			qcDts.setModified_type("INSERTED");
			qcDts.setInserted_by(iMaster.getInserted_by());
			qcDts.setInserted_on(iMaster.getInserted_on());
			qcDts.setUpdated_by(iMaster.getUpdated_by());
			qcDts.setUpdated_on(ldt);
			qcDts.setDeleted_by("NA");
			qcDts.setDeleted_on(ldt);
			qcDts.setInsert_status(true);
		}
		iMaster.setItmItem_master_qc_details(qcSet);
		
		//Dynamic
		item_master_alternative_dtlsRepository.getItem_master_alternative_dtlsUpdate(op.get().getItem_id(),"UPDATED");
		Set<Item_master_alternative_dtls> altSet = new HashSet<Item_master_alternative_dtls>();
		altSet.addAll(iMaster.getItem_master_alternative_dtls());
		for(Item_master_alternative_dtls itemAltDts : altSet)
		{
			itemAltDts.setItem_id(op.get().getItem_id());
			itemAltDts.setCompany_id(iMaster.getCompany_id());
			itemAltDts.setFin_year(iMaster.getFin_year());
			itemAltDts.setModified_type("INSERTED");
			itemAltDts.setInserted_by(iMaster.getInserted_by());
			itemAltDts.setInserted_on(iMaster.getInserted_on());
			itemAltDts.setUpdated_by(iMaster.getUpdated_by());
			itemAltDts.setUpdated_on(ldt);
			itemAltDts.setDeleted_by("NA");
			itemAltDts.setDeleted_on(ldt);
			itemAltDts.setInsert_status(true);
		}
		iMaster.setItem_master_alternative_dtls(altSet);
		
		if(iMaster.getItem_type().compareToIgnoreCase("STORE PURCHASE")==0)
		{
			item_master_classificationRepository.itemMasterClassificationUpdate(op.get().getItem_id(),"UPDATED");
			
			Set<Item_master_classification> classification = new HashSet<Item_master_classification>();
			classification.addAll(iMaster.getItem_master_classification());
			for(Item_master_classification classified : classification)
			{
				classified.setItem_id(op.get().getItem_id());
				classified.setCompany_id(iMaster.getCompany_id());
				classified.setFin_year(iMaster.getFin_year());
				classified.setModified_type("INSERTED");
				classified.setInserted_by(iMaster.getInserted_by());
				classified.setInserted_on(iMaster.getInserted_on());
				classified.setUpdated_by(iMaster.getUpdated_by());
				classified.setUpdated_on(ldt);
				classified.setDeleted_by("NA");
				classified.setDeleted_on(ldt);
			}
			iMaster.setItem_master_classification(classification);
		}
		else
		{
			iMaster.getItem_master_classification().clear();
		}
		
		if(iMaster.getItem_type().compareToIgnoreCase("PACKING ITEMS")==0)
		{
			item_master_size_weightRepository.itemSizeWeightUpdate(op.get().getItem_id(),"UPDATED");
			
			Set<Item_master_size_weight> sizeandweight = new HashSet<Item_master_size_weight>();
			sizeandweight.addAll(iMaster.getItem_master_size_weight());
			for(Item_master_size_weight size : sizeandweight)
			{
				size.setItem_id(op.get().getItem_id());
				if(Utility.isNullOrEmpty(size.getItem_code())) {
					size.setItem_name(item_masterRepository.itemNameById(size.getItem_code()).getItem_name());
				}else {size.setItem_name("None");}
				size.setCompany_id(iMaster.getCompany_id());
				size.setFin_year(iMaster.getFin_year());
				size.setModified_type("INSERTED");
				size.setInserted_by(iMaster.getInserted_by());
				size.setInserted_on(iMaster.getInserted_on());
				size.setUpdated_by(iMaster.getUpdated_by());
				size.setUpdated_on(ldt);
				size.setDeleted_by("NA");
				size.setDeleted_on(ldt);
			}
			iMaster.setItem_master_size_weight(sizeandweight);
		}
		else
		{
			iMaster.getItem_master_size_weight().clear();
		}
		
		return item_masterRepository.save(iMaster);
	}
	
	public void deleteItemMaintain(Item_maintain iMaintain) 
	{
		System.err.println("Calling Delete Method: "+iMaintain.getId()+" "+iMaintain.getItem_id());
		item_maintainRepository.delete(iMaintain);
	}
	
	@Transactional
	public Item_masterDTO deleteItemMaster(long id,Item_master item_master)
	{
		Optional<Item_master> op = item_masterRepository.findById(id);
		Item_master iMaster = item_masterRepository.getItemDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
			
		iMaster.setModified_type("DELETED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(op.get().getUpdated_by());
		iMaster.setUpdated_on(op.get().getUpdated_on());
		iMaster.setDeleted_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setDeleted_on(ldt);
		iMaster.setInsert_status(false);
		iMaster.setStk_status(false);
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		item_master_stat_infoRepository.item_master_stat_infoupdate(op.get().getItem_id(),"DELETED");
		
		item_master_inv_data1Repository.getItem_master_inv_data1Update(op.get().getItem_id(),"DELETED");
		
		item_master_inv_data2Repository.getItem_master_inv_data2Update(op.get().getItem_id(),"DELETED");
		
		item_master_other_infoRepository.getItem_master_other_infoUpdate(op.get().getItem_id(),"DELETED");
		
		item_master_pack_mat_tagRepository.item_master_pack_mat_tagUpdate(op.get().getItem_id(),"DELETED");
		
		item_master_qc_detailsRepository.getItmItem_master_qc_detailsUpdate(op.get().getItem_id(),"DELETED");
		
		item_master_alternative_dtlsRepository.getItem_master_alternative_dtlsUpdate(op.get().getItem_id(),"DELETED");
		
		if(iMaster.getItem_type().compareToIgnoreCase("STORE PURCHASE")==0)
		{
			item_master_classificationRepository.itemMasterClassificationUpdate(op.get().getItem_id(),"DELETED");
		}
		
		if(iMaster.getItem_type().compareToIgnoreCase("PACKING ITEMS")==0)
		{
			item_master_size_weightRepository.itemSizeWeightUpdate(op.get().getItem_id(),"DELETED");
		}
		
		Type listType = new TypeToken<Item_masterDTO>() {}.getType();
		Item_masterDTO items = new ModelMapper().map(iMaster,listType);
		
		item_masterRepository.save(iMaster);
		
		items.setStatus("Item Updated Successfully !!!");
		
		return items;
	}
	
	public List<Item_masterDTO> getAllItems() {
		
		List<Item_master> modelList=item_masterRepository.getAllItems(true);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
	
	public List<Item_masterDTO> getItemNameList() {
	
		List<Item_master> modelList=item_masterRepository.getItemCodeWithoutPacking(true,"Packing Items");
		
		modelList.forEach((e->{
			e.setItem_name(e.getItem_name().toUpperCase());
		}));

		List<Item_master> allData = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name).reversed())
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(allData,listType);
		
		return itemNameList;
	}
	
	public List<Map<String,Object>> getItemNameNewList() {
		
		List <Map<String,Object>> itemNameNewList = new ArrayList<>();
		
		itemNameNewList.addAll(item_masterRepository.getItemNameNewList(true));
		
		return itemNameNewList;
	}
	
	public List<Map<String,Object>> getPackingUom(String item_id) {
		
		List <Map<String,Object>> packingUomList = new ArrayList<>();
		
		packingUomList.addAll(item_masterRepository.getPackingUomList(item_id));
		
		return packingUomList;
	}
	
	public List<Item_masterDTO> getItemCodeNamecom(String company) {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeWithoutPacking(true,"Packing Items");
		
		modelList.forEach((e->{
			e.setItem_name(e.getItem_name().toUpperCase());
		}));

		List<Item_master> allData = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().contains(company))
				  .sorted(Comparator.comparing(Item_master::getItem_name).reversed())
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(allData,listType);
		
		return itemNameList;
	}
	
	public List<Item_masterDTO> getItemRawMaterials() {
		
		List<Item_master> modelList=item_masterRepository.getItemRawMaterials(true,"RAW MATERIAL");
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
	
	// Item filter with purchase active & item active.
	public List<Item_masterDTO> getItemThruType(String itype) {
		String iname="";
		if(itype.compareTo("0")!=0) {
			iname=item_type_masterRepository.getItemType(itype).getItem_name();
		}
		System.out.println("iname:"+iname);
		List<Item_master> modelList=item_masterRepository.getItemThruType(true,iname);
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		System.out.println("modelList"+modelList.size());
		List< Item_master> addAll =modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(addAll,listType);
		return itemNameList;
	}
	
	// Item filter with purchase active & item active.
	public List<Item_masterDTO> getItemThruType3(String itype) {
		String iname="";
		if(itype.compareTo("0")!=0) {
			iname=item_type_masterRepository.getItemType(itype).getItem_name();
		}
		System.out.println("iname:"+iname);
		List<Item_master> modelList=item_masterRepository.getItemThruType3(iname);
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		System.out.println("modelList"+modelList.size());
		List< Item_master> addAll =modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemNameList = new ModelMapper().map(addAll,listType);
		return itemNameList;
	}
	public List<Item_masterDTO> getweatreceivingitemlist(String businessunit_id)
	{

		String itemtype="WHEAT";
		List<Item_master> modelList=item_masterRepository.getweatreceivingitemlist(businessunit_id,itemtype);
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		
		List< Item_master> addAll =modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
	
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		List<Item_masterDTO> itemNameList = new ModelMapper().map(addAll,listType);
		return itemNameList;
	}
	
	public List<Item_masterDTO> getLabItemlist(String businessunit_id)
	{

		String itemtype="FINISHED PRODUCTS";
		List<Item_master> modelList=item_masterRepository.getLabItemlist(businessunit_id,itemtype);
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		
		List< Item_master> addAll =modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
	
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		List<Item_masterDTO> itemNameList = new ModelMapper().map(addAll,listType);
		return itemNameList;
	}
	
	public List<Item_masterDTO> getItemThruSalesThruBU(String bunit,String company) {
	//	System.out.println("bunit:"+bunit);
		List<Item_master> modelList=item_masterRepository.getItemThruSalesThruBU(true,"Packing Items",true,bunit);
		
		modelList.forEach((e->{
			e.setItem_name(e.getItem_name().toUpperCase());
		}));
		
		System.out.println("getItemThruSalesThruBU");
		List<Item_master> allData = modelList
				  .stream()
				  .filter(c -> c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
		
		return itemsalesList;
	}
	
	public List<Map<String, Object>> getItemThruSalesThruBUFastApi(String bunit,String company)
	{
		List<Map<String, Object>> itemPackingList = new ArrayList<Map<String, Object>>();
		
		itemPackingList.addAll(item_masterRepository.getItemThruSalesThruBUFastApi(bunit,company,"Packing Items"));
		
		return itemPackingList;
	}
	
	public List<Map<String, Object>> getFinishedItemlist(String businessunit_id)
	{
		List<Map<String, Object>> itemFinishedList = new ArrayList<Map<String, Object>>();
		String itemtype="FINISHED PRODUCTS";
		itemFinishedList.addAll(item_masterRepository.getFinishedItemlist(businessunit_id, itemtype));
		return itemFinishedList;
	}
	
	
	public List<Item_masterDTO> getItemThruSalesThruBU_inv_type(String bunit,String company,String inv_type) {
		//System.out.println("bunit:"+bunit);
		 if(inv_type.compareToIgnoreCase("INV00004") == 0) 
		{
			List<Item_master> modelList=item_masterRepository.getItemThruSalesThruBU_PMS(true,"Packing Items",true,bunit);
			modelList.forEach((e->{
				e.setItem_name(e.getItem_name().toUpperCase());
			}));
			
			//System.out.println("getItemThruSalesThruBU");
			List<Item_master> allData = modelList
					  .stream()
					  .filter(c -> c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_master::getItem_name))
					  .collect(Collectors.toList());
			
			// Create Conversion Type
			Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
			
			// Convert List of Entity objects to a List of DTOs objects 
			List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
			
			return itemsalesList;
		}
		 else if(inv_type.compareToIgnoreCase("INV00003") == 0) 
			{
				List<Item_master> modelList=item_masterRepository.getItemThroughJobWork(true,bunit);
				modelList.forEach((e->{
					e.setItem_name(e.getItem_name().toUpperCase());
				}));
				
				//System.out.println("getItemThruSalesThruBU");
				List<Item_master> allData = modelList
						  .stream()
						  .filter(c -> c.getCompany_id().equals(company))
						  .sorted(Comparator.comparing(Item_master::getItem_name))
						  .collect(Collectors.toList());
				
				// Create Conversion Type
				Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
				
				// Convert List of Entity objects to a List of DTOs objects 
				List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
				
				return itemsalesList;
			}
		 else if(inv_type.compareToIgnoreCase("INV00005") == 0) 
			{
				List<Item_master> modelList=item_masterRepository.getItemThroughTradingTax(true,bunit);
				modelList.forEach((e->{
					e.setItem_name(e.getItem_name().toUpperCase());
				}));
				
				//System.out.println("getItemThruSalesThruBU");
				List<Item_master> allData = modelList
						  .stream()
						  .filter(c -> c.getCompany_id().equals(company))
						  .sorted(Comparator.comparing(Item_master::getItem_name))
						  .collect(Collectors.toList());
				
				// Create Conversion Type
				Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
				
				// Convert List of Entity objects to a List of DTOs objects 
				List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
				
				return itemsalesList;
			}
		else 
		{
			List<Item_master> modelList_new=item_masterRepository.getItemThruSalesThruBU_withoutPMS(true,"Packing Items",true,bunit);
			
			List<Item_master> final_itemlist= new ArrayList<Item_master>();
			if(inv_type.compareToIgnoreCase("INV00002") == 0) 
			{
			
				modelList_new.forEach((e->{
					
					List<Item_master_stat_info>  item_master_stat_info=item_master_stat_infoRepository.retriveItemMasterStatInfo(e.getItem_id());
					if(item_master_stat_info.get(0).getTax_rate()>0) 
					{
						final_itemlist.add(e);
					}
					e.setItem_name(e.getItem_name().toUpperCase());
				}));
				
				//System.out.println("getItemThruSalesThruBU");
				List<Item_master> allData = final_itemlist
						  .stream()
						  .filter(c -> c.getCompany_id().equals(company))
						  .sorted(Comparator.comparing(Item_master::getItem_name))
						  .collect(Collectors.toList());
				Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType(); 
				List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
				return itemsalesList;
			}
			else 
			{
			
				modelList_new.forEach((e->{
					
					List<Item_master_stat_info>  item_master_stat_info=item_master_stat_infoRepository.retriveItemMasterStatInfo(e.getItem_id());
					if(item_master_stat_info.get(0).getTax_rate()==0) 
					{
						final_itemlist.add(e);
					}
					e.setItem_name(e.getItem_name().toUpperCase());
				}));
				
				//System.out.println("getItemThruSalesThruBU");
				List<Item_master> allData = final_itemlist
						  .stream()
						  .filter(c -> c.getCompany_id().equals(company))
						  .sorted(Comparator.comparing(Item_master::getItem_name))
						  .collect(Collectors.toList());
				Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType(); 
				List<Item_masterDTO> itemsalesList = new ModelMapper().map(allData,listType);
				return itemsalesList;
			}
			
		}
		
		//List<Item_master> modelList=item_masterRepository.getItemThruSalesThruBU(true,"Packing Items",true,bunit);
		
		
	}
	public List<Map<String,Object>> getJobworklist(String item_id)
	{
		return item_masterRepository.getJobworklist(item_id);
	}
	
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeReg(String bunit,String company)
	{
		return item_masterRepository.getItemThruSalesThruBU_inv_typeReg(bunit,company);
	}
	
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeGST(String bunit,String company)
	{
		return item_masterRepository.getItemThruSalesThruBU_inv_typeGST(bunit,company);
	}
	
	public List<Item_masterDTO> getItemThruSales() {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeWithoutPackSales(true,"Packing Items",true);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemsalesList = new ModelMapper().map(modelList,listType);
		
		return itemsalesList;
	}
	
	public List<Map<String, Object>> getItemThruSalesNew()
	{
		List<Map<String, Object>> itemSaleNewList = new ArrayList<Map<String, Object>>();
		 
		  itemSaleNewList.addAll(item_masterRepository.getItemCodeWithoutPackSaleNew(true,"Packing Items",true));
		return itemSaleNewList;
	}
	
	public List<Item_masterDTO> getItemThruProcessed(){
		
		List<Item_master> modelList=item_masterRepository.getItemThruProcessed(true);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemsalesList = new ModelMapper().map(modelList,listType);
		
		return itemsalesList;
	}
	
	public List<Item_masterDTO> getItemThruProcessed(String bunit){
		
		List<Item_master> modelList=item_masterRepository.getItemThruProcessed(true,bunit);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemsalesList = new ModelMapper().map(modelList,listType);
		
		return itemsalesList;
	}
	
	public List<Item_masterDTO> getItemThruPurchase() {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeWithoutPackPurchase(true,true);
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		
		List<Item_master> allList = modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itempurList = new ModelMapper().map(allList,listType);
		
		return itempurList;
	}
	
	public List<Map<String, Object>> getItemThruPurchasenew()
	{
		List<Map<String, Object>> itempurList = new ArrayList<Map<String, Object>>();
		// itempurList.addAll(item_masterRepository.getItemCodeWithoutPackPurchasenew(true,true));
		 
		  itempurList.addAll(item_masterRepository.getItemCodeWithoutPackPurchasenew(true,true));
		return itempurList;
	}
	
	
	public List<Item_masterDTO> getItemCodeByPacking(String company) {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeByPacking(true,"Packing Items",company);
		
		modelList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
 		
		List<Item_master> alllList = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Item_master::getItem_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemByPack = new ModelMapper().map(alllList,listType);
		
		return itemByPack;
	}
	//Delete
	public List<Item_masterDTO> getItemCodeByPacking() {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeByPacking(true,"Packing Items");
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemByPack = new ModelMapper().map(modelList,listType);
		
		return itemByPack;
	}
	
	public List<Item_masterDTO> getItemCodeWithoutPacking() {
		
		List<Item_master> modelList=item_masterRepository.getItemCodeWithoutPacking(true,"Packing Items");
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemWOPack = new ModelMapper().map(modelList,listType);
		
		return itemWOPack;
	}
	
	public List<Item_masterDTO> getItemListByGroup(String group,String item_id) {
		
		List<Item_master> modelList=item_masterRepository.getItemListByGroup(group,item_id);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemGroupList = new ModelMapper().map(modelList,listType);
		
		for(int i=0;i<itemGroupList.size();i++) {
			if(itemGroupList.get(i).getItem_group().compareTo("")!=0 && itemGroupList.get(i).getItem_group().compareTo("0")!=0) {
				itemGroupList.get(i).setGroup_name(item_group_masterRepository.getItemGroupUom(itemGroupList.get(i).getItem_group()).getGroup_name());
			}else {itemGroupList.get(i).setGroup_name("");}
			if(itemGroupList.get(i).getMstock_unit().compareTo("")!=0 && itemGroupList.get(i).getMstock_unit().compareTo("0")!=0) {
				itemGroupList.get(i).setUom_name(customUomMasterRepository.getUomByIGroup(itemGroupList.get(i).getMstock_unit()).getDescription());	
			}else {itemGroupList.get(i).setUom_name("");}
			if(itemGroupList.get(i).getItem_category().compareTo("")!=0 && itemGroupList.get(i).getItem_category().compareTo("0")!=0) {
				itemGroupList.get(i).setCategory_name(itemCatRepository.catagoryNameByCode(itemGroupList.get(i).getItem_category()).getCatagory_name());
			}else {itemGroupList.get(i).setCategory_name("");}
		}
		
		return itemGroupList;
	}
	
	public List<Item_masterDTO> getItemByItemGroup(String itemid) {
		
		List<Item_master> modelList=item_masterRepository.getItemListByGroup(item_masterRepository.getItemNameCodeStock(itemid).getItem_group());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_masterDTO> itemByGroup = new ModelMapper().map(modelList,listType);
		for(int i=0;i<itemByGroup.size();i++) {
			itemByGroup.get(i).setGroup_name(item_group_masterRepository.getGroupName(itemByGroup.get(i).getItem_group()));
		}
		return itemByGroup;
	}
	
	public Item_masterDTO getItemNameByCode(String code){
	    	
	 	Item_master modelList=item_masterRepository.itemNameByCode(code);
		
		// Create Conversion Type
		Type listType = new TypeToken<Item_masterDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		Item_masterDTO itemNameList1= new ModelMapper().map(modelList,listType);
		
		return itemNameList1;
	}
	 
	public Item_masterDTO getItemNameCodeStock(String itemID) {
		 
		Item_master modelList=item_masterRepository.getItemNameCodeStock(itemID);
		Type listType = new TypeToken<Item_masterDTO>() {}.getType();
		Item_masterDTO itemNameStockList= new ModelMapper().map(modelList,listType);
		return itemNameStockList;
	 }
	 
	public List<Item_masterDTO> getItemCodeByType(String type) {
		List<Item_master> modelList=item_masterRepository.getItemCodeByType(type);
		
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> itemCodeType= new ModelMapper().map(modelList,listType);
		return itemCodeType;
	 }
	 
	public Item_masterDTO getItemNameById(String code,String company){
	    	
	 	Item_master modelList=item_masterRepository.itemNameByIdcom(code, company);
	 	

	 	
	 
	 	//Create Conversion Type
		Type listType = new TypeToken<Item_masterDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		Item_masterDTO itmNameList= new ModelMapper().map(modelList,listType);
		
		return itmNameList;
	}
	 
	public Item_master_pack_mat_tagDTO itempackUom(String code,String code1,String company){
	    	System.out.println("code:"+code+"code:"+code1+"company:"+company);
	 	Item_master_pack_mat_tag modelList=item_master_pack_mat_tagRepository.itempackUom(code,code1,company);
	 	Type listType = new TypeToken<Item_master_pack_mat_tagDTO>() {}.getType();
	 	if(modelList == null) {
	 		Item_master_pack_mat_tagDTO def=new Item_master_pack_mat_tagDTO(0,"0","0","Choose an Option","0","0","0","0","0",0.0,"0","0");
	 		return def;
	 	}
	 	else {
	 		Item_master_pack_mat_tagDTO itemUom= new ModelMapper().map(modelList,listType);
	 		return itemUom;
	 	}
	}
	 
	public List<Item_master_qc_detailsDTO> getItemQCDetails(String code,String company)
	{
		System.out.println("getItemQCDetails:: "+code);
		List<Item_master_qc_details> modelList=item_master_qc_detailsRepository.getItemQCDetails(code,company);
		
		Type listType=new TypeToken<List<Item_master_qc_detailsDTO>>() {}.getType();
		
		List<Item_master_qc_detailsDTO> qcList=new ModelMapper().map(modelList,listType);
		
		return qcList;
	}
	public List<Item_master_stock_details> getItemstockDetails(String code,String company)
	{
		
		List<Item_master_stock_details> modelList=item_masterRepository.getItemstockDetails(code,company);
		
		
		
		return modelList;
	}

	@Override
	public Item_master findOne(Long id) {
		 Optional<Item_master> op=this.item_masterRepository.findById(id);
		 return op.get();
	}
	 
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(String code,String company){
		
		List<Item_master_pack_mat_tag> modelList=item_master_pack_mat_tagRepository.getItemMasterPackMat(code);
			
		modelList.forEach((e->{
			e.setItem_name(e.getItem_name().toUpperCase());
		}));
		
		 List<Item_master_pack_mat_tag> allData = modelList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().contains(company))
				  .sorted(Comparator.comparing(Item_master_pack_mat_tag::getItem_name))
				  .collect(Collectors.toList());
		Type listType=new TypeToken<List<Item_master_pack_mat_tagDTO>>() {}.getType();
		
		List<Item_master_pack_mat_tagDTO> itemList=new ModelMapper().map(allData,listType);
		
		return itemList;
	}
	
public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(String code){
		
		List<Item_master_pack_mat_tag> modelList=item_master_pack_mat_tagRepository.getItemMasterPackMat(code);
			
		modelList.forEach((e->{
			e.setItem_name(e.getItem_name().toUpperCase());
		}));
		
		 List<Item_master_pack_mat_tag> allData = modelList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED") ||  c.getModified_type().equals("UPDATED"))
				  .sorted(Comparator.comparing(Item_master_pack_mat_tag::getItem_name))
				  .collect(Collectors.toList());
		Type listType=new TypeToken<List<Item_master_pack_mat_tagDTO>>() {}.getType();
		
		List<Item_master_pack_mat_tagDTO> itemList=new ModelMapper().map(allData,listType);
		
		return itemList;
	}
	
	public Item_master_inv_data1DTO retriveItemMasterInvData1(String item_id,String company){

		Item_master_inv_data1 modelList=item_master_inv_data1Repository.retriveItemMasterInvData1(item_id,company);

		Type listType = new TypeToken<Item_master_inv_data1DTO>() {}.getType();

		Item_master_inv_data1DTO suppAddress= new ModelMapper().map(modelList,listType);
		
		return suppAddress;
	}
	
	public List<Item_master_alternative_dtlsDTO> retriveItemMasterAltDtls(String item_id,String company){
		
		List<Item_master_alternative_dtls> modelList=item_master_alternative_dtlsRepository.retriveItemMasterAltDtls(item_id,company);
		Type listType=new TypeToken<List<Item_master_alternative_dtlsDTO>>() {}.getType();
		
		List<Item_master_alternative_dtlsDTO> itemList=new ModelMapper().map(modelList,listType);
		
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItem_group().compareTo("")!=0 && itemList.get(i).getItem_group().compareTo("0")!=0) {
				itemList.get(i).setGroup_name(item_group_masterRepository.getItemGroupUom(itemList.get(i).getItem_group()).getGroup_name());
			}else {itemList.get(i).setGroup_name("");}
			if(itemList.get(i).getMstock_unit().compareTo("")!=0 && itemList.get(i).getMstock_unit().compareTo("0")!=0) {
				itemList.get(i).setUom_name(customUomMasterRepository.getUomByIGroup(itemList.get(i).getMstock_unit()).getDescription());	
			}else {itemList.get(i).setUom_name("");}
			if(itemList.get(i).getItem_category().compareTo("")!=0 && itemList.get(i).getItem_category().compareTo("0")!=0) {
				itemList.get(i).setCategory_name(itemCatRepository.catagoryNameByCode(itemList.get(i).getItem_category()).getCatagory_name());
			}else {itemList.get(i).setCategory_name("");}
		}
		
		return itemList;
	}
	
	public Item_master_inv_data2DTO retriveItemMasterInvData2(String item_id,String company){

		Item_master_inv_data2 modelList=item_master_inv_data2Repository.retriveItemMasterInvData2(item_id,company);

		Type listType = new TypeToken<Item_master_inv_data2DTO>() {}.getType();

		Item_master_inv_data2DTO suppAddress= new ModelMapper().map(modelList,listType);
		
		return suppAddress;
	}
	
	public Item_master_other_infoDTO retriveItemMasterOtherInfo(String item_id,String company)
	{
		Item_master_other_info modelList=item_master_other_infoRepository.retriveItemMasterOtherInfo(item_id,company);

		Type listType = new TypeToken<Item_master_other_infoDTO>() {}.getType();

		Item_master_other_infoDTO suppAddress= new ModelMapper().map(modelList,listType);
		
		return suppAddress;
	}
	
	public List<Item_master_stat_infoDTO> retriveItemMasterStatInfo(String item_id,String company)
	{
		List<Item_master_stat_info> modelList=item_master_stat_infoRepository.retriveItemMasterStatInfo(item_id);

		List<Item_master_stat_info> allData = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().contains(company))
				  .sorted(Comparator.comparing(Item_master_stat_info::getItem_id).reversed())
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Item_master_stat_infoDTO>>() {}.getType();
		
		List<Item_master_stat_infoDTO> itemList=new ModelMapper().map(allData,listType);
		
		return itemList;
		
	}
	
	public Item_master_pack_mat_tagDTO getItemCapacity(String code){

		Item_master_pack_mat_tag modelList=item_master_pack_mat_tagRepository.getItemCapacity(code);

		Type listType = new TypeToken<Item_master_pack_mat_tagDTO>() {}.getType();

		Item_master_pack_mat_tagDTO itemCapacity= new ModelMapper().map(modelList,listType);
		
		return itemCapacity;
	}
	
	@SuppressWarnings("deprecation")
	public Page<Item_master> getItemPaging(int pageno,int pagesize,String sortBy,String comp){
		System.err.println("Sort BY: >>>>>>>>>>>>"+sortBy);
		
		//return item_masterRepository.findAll( new PageRequest( pageno.orElse(0), pagesize,Sort.Direction.DESC, sortBy.orElse("id")));
		return item_masterRepository.findSortedItems(new PageRequest(pageno, pagesize,Sort.by(sortBy).descending()),comp);
	}
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Item_master> getItemPagingSorting(Integer pageno,Integer pagesize,String sortBy,String comp)
	    {
	        Pageable paging = PageRequest.of(pageno, pagesize, Sort.by(sortBy));
			/*
			 * //when use page Page<Item_master> pagedCount=
			 * itemRepository.getTotalpages(comp,paging);
			 * System.err.println("total no of pages: "+pagedCount.getTotalPages());
			 * System.err.println("current page's size : "+pagedCount.getSize());
			 */
	        //when use slice        
	       Slice<Item_master> pagedResult = itemRepository.PaginationByJPQL(comp,paging);
	       
	       System.err.println("current page size : "+pagedResult.getSize()); 
	       System.err.println("current page number : "+pagedResult.getNumber()); 
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Item_master>();
	        }
	    }
	
	public Paging_SortingDTO getItemPagingTotal(Integer pageno,Integer pagesize,String sortBy,String comp)
    {
        Pageable paging = PageRequest.of(pageno, pagesize, Sort.by(sortBy));
        //when use page
        Page<Item_master> pagedCount= itemRepository.getTotalpages(comp,paging);
        System.err.println("total no of pages: "+pagedCount.getTotalPages()); 
        System.err.println("current page's size : "+pagedCount.getSize()); 
 
        Paging_SortingDTO ps=new Paging_SortingDTO(pagedCount, pagedCount.getTotalPages(), pagedCount.getTotalElements(), pagedCount.getNumberOfElements(), pagedCount.getSize(), pagedCount.getNumber());
         
        /*if(pagedCount.hasContent()) {
            return pagedCount.getTotalPages();
        } else {
            return new ArrayList<Item_master>();
        }*/
        return ps;
    }
	
	public MessageStatusDTO chkItemNameStatus(String iname){
		String result=item_masterRepository.chkItemNameStatus(iname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	public StatusDTO chkItemCodeStatus(String icode){
		String result="";
		
		if(item_masterRepository.chkItemCodeStatus(icode).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
	public StatusDTO checkItemMasterUsage(String code){
		String result=item_masterRepository.checkItemMasterUsage(code);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
	
	/* @Transactional
		public Item_master accountitemposting(long id) 
		{
			Item_master itemlist=item_masterRepository.getItemDetails(id);
			
			String gst="";
			String output;
			Tallyrequest_ItemMaster tally=new Tallyrequest_ItemMaster();
			try  
			{
				List<Item_master_stat_info> taxinfo= new ArrayList<Item_master_stat_info>();
				taxinfo.addAll(item_master_stat_infoRepository.retriveItemMasterStatInfo(itemlist.getItem_id()));
				boolean generalstatus=true,no_kg=false,taxstatus=false;
				if(taxinfo.get(0).getTax_rate() == 0) 
				{
					taxstatus=false;
				}
				else 
				{
					taxstatus=true;
				}
				double tax_rate=taxinfo.get(0).getTax_rate();
//generalstatus  = false (Wheat)				
				double conversion=0;
				if(itemlist.getItem_name().contains("GENERAL")) 
				{
					//"    <BASEUNITS>BAGS</BASEUNITS>\n"+
	      			//		"    <ADDITIONALUNITS>"+stockkeeping+"</ADDITIONALUNITS>\n"+

					//String itemqty=itemlist.getItem_name().substring(itemlist.getItem_name().length()-4,itemlist.getItem_name().length());//getting number with kg
					//conversion=100/Integer.parseInt(itemqty.replace("KG", ""));//only getting KG
					
					String kgsplit[]=itemlist.getItem_name().split("KG");
					 String number_kg[]=kgsplit[0].split(" ");
					 
					 conversion=100/Double.parseDouble(number_kg[number_kg.length-1]);
					 System.out.println(conversion + " // tuhin " + number_kg[number_kg.length-1]);
				}
				else 
				{
					generalstatus=false;
					//"    <BASEUNITS>"+stockkeeping+"</BASEUNITS>\n"+
	      			//		"    <ADDITIONALUNITS>BAGS</ADDITIONALUNITS>\n"+

					if(itemlist.getItem_name().contains("TAX")) 
					{
						
						if(itemlist.getItem_name().contains("WHEAT")) 
						{
							generalstatus=true;
							String kgsplit[]=itemlist.getItem_name().split("KG");
							 String number_kg[]=kgsplit[0].split(" ");
							 
							 conversion=100/Double.parseDouble(number_kg[number_kg.length-1]);
							 System.out.println(conversion + " // tuhin " + number_kg[number_kg.length-1]);
						}
						else 
						{
							String kgsplit[]=itemlist.getItem_name().split("KG");
							 String number_kg[]=kgsplit[0].split(" ");
							 
							 String numbertokginclude=number_kg[number_kg.length-1];
							 if(numbertokginclude.contains("/")) 
							 {
								 String slash[]=numbertokginclude.split("/");
								 conversion=Double.parseDouble(slash[0])/100;
							 }
							 else 
							 {
								 conversion=Double.parseDouble(number_kg[number_kg.length-1])/100;
										 
							 }
							
						}
						
						 	
						
					}
					else 
					{
					
						
						if(itemlist.getItem_name().contains("KG")) 
						{
							//HDPE RAWA F 49KG
							//String itemqty=itemlist.getItem_name().substring(itemlist.getItem_name().length()-4,itemlist.getItem_name().length());//getting number with kg
							 //conversion=Integer.parseInt(itemqty.replace("KG", ""))/100;//only getting KG
							if(itemlist.getItem_name().contains("WHEAT")) 
							{
								generalstatus=true;
								String kgsplit[]=itemlist.getItem_name().split("KG");
								 String number_kg[]=kgsplit[0].split(" ");
								 
								 conversion=100/Double.parseDouble(number_kg[number_kg.length-1]);
								 System.out.println(conversion + " // tuhin " + number_kg[number_kg.length-1]);
							}
							else 
							{
								 String kgsplit[]=itemlist.getItem_name().split("KG");
								 String number_kg[]=kgsplit[0].split(" ");
								 
								 conversion=Double.parseDouble(number_kg[number_kg.length-1])/100;
								 System.out.println(conversion + " // tuhin " + number_kg[number_kg.length-1]);
									
							}	 
							 
							//kgnumber=49
							
						}
						else 
						{
							no_kg=true;
						}
					}	
					
					
				}
				
			     System.out.println("over here 12 ");
			
			
				
				output=tally.SendToTally(itemlist.getItem_name(),itemlist.getItem_category_name(),gst,itemlist.getMstock_unit_name(),itemlist.getExport(),itemlist.getSub_group_name(),generalstatus,conversion,itemlist.getHsn_code(),no_kg,taxstatus,tax_rate);
		        
				
				System.out.println(" output :: "+output);
				String [] splitoutput = output.split("\\|\\|");
				System.out.println("export status ::"+splitoutput[1]);
				System.out.println(splitoutput[0] + " / " + splitoutput[1]+"/" +id);
				int exportstatus=0;
				if(Integer.parseInt(splitoutput[1])==1) 
				{
					exportstatus=1;
				}
				if(Integer.parseInt(splitoutput[2])==1) 
				{
					exportstatus=1;
				}
				
				//item_masterRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
				item_masterRepository.exportuomtally(id,splitoutput[0],exportstatus);
				
			
			}
			catch(Exception e)
			{
			
				System.out.println(e);
			}
			
			Item_master itemlist1=item_masterRepository.getItemDetails(id);
			
			return itemlist1;
		}
	 */
	@Transactional
	public Item_master accountitemposting(long id) 
	{
		Item_master itemlist=item_masterRepository.getItemDetails(id);
		
		String gst="";
		String output;
		Tallyrequest_ItemMaster tally=new Tallyrequest_ItemMaster();
		try  
		{
			
			String itemname=itemlist.getItem_name();
			
			String itemcategory=itemlist.getItem_group_name();
			
			String masterstock=itemlist.getMstock_unit_name();//Master Stock Keeping Unit
		
		
			System.out.println(itemname);
			System.out.println(itemcategory);
			System.out.println(masterstock);
		
		
			
			List<Item_master_stat_info> taxdetails =new ArrayList<Item_master_stat_info>();
			taxdetails.addAll(item_master_stat_infoRepository.retriveItemMasterStatInfo(itemlist.getItem_id()));
			boolean taxinclude=false;
			double tax=taxdetails.get(0).getTax_rate();
			if(tax>0) 
			{
				taxinclude=true;
			}
			
			System.out.println(taxinclude + " // " + tax);
			boolean pakcingstatus=false;
			String unit_type=itemlist.getUnit_type();
			if(unit_type.compareToIgnoreCase("SUOM")==0) 
			{
				pakcingstatus=true;
			}
			
			String packingname="";
			String packingmasterstock="";
			String packingconv="";
			if(pakcingstatus==true) 
			{
				
			}
			else 
			{
				List<Item_master_pack_mat_tag> packingdetails=new ArrayList<Item_master_pack_mat_tag>();
				packingdetails.addAll(item_master_pack_mat_tagRepository.getItemMasterPackMat(itemlist.getItem_id()));
				 packingname=packingdetails.get(0).getItem_name();
				 packingmasterstock=item_masterRepository.getItemDetailsbyname(packingname).getMstock_unit_name();
				 packingconv=packingdetails.get(0).getCapacity();
				
			}
			String hsncode=itemlist.getHsn_code();
		
			System.out.println(packingmasterstock);
			System.out.println(hsncode);
			System.out.println(packingname);
			System.out.println(packingconv);
			
			
			
			boolean taxstatus=false;
			if(tax == 0) 
			{
				taxstatus=false;
			}
			else 
			{
				taxstatus=true;
			}
			double tax_rate=taxdetails.get(0).getTax_rate();
//generalstatus  = false (Wheat)				
			double conversion=0;

			//
			 if(itemlist.getItem_name().contains("KG"))
			 {
				 String kgsplit[]=itemlist.getItem_name().split("KG");
				 String number_kg[]=kgsplit[0].split(" ");
				 
				 String numbertokginclude=number_kg[number_kg.length-1];
				 if(numbertokginclude.contains("/")) 
				 {
					 String slash[]=numbertokginclude.split("/");
					 conversion=Double.parseDouble(slash[0])/100;
				 }
				 else 
				 {
					 conversion=Double.parseDouble(number_kg[number_kg.length-1])/100;
							 
				 }
			 }
			 else 
			 {
				 if(itemlist.getItem_name().contains("0G")) 
				 {
					 
					 if(itemlist.getItem_name().contains("/")) 
					 {
						 String slash[]=itemlist.getItem_name().split("/");//SOOJI 30/200G TAX
						 String number_kg[]=slash[0].split(" ");
						 conversion=Double.parseDouble(number_kg[number_kg.length-1])/100;
						 //conversion=Double.parseDouble(slash[0])/100;
					 }
					 else 
					 {
						 String kgsplit[]=itemlist.getItem_name().split("0G");//SOOJI 30G TAX
						 String number_kg[]=kgsplit[0].split(" ");//SOOJI 3    0G TAX
						 
						 conversion=Double.parseDouble(number_kg[number_kg.length-1]+"0")/100;// ( 3 + "0" )/100
								 
					 }
					 
				 }
				 if(itemlist.getItem_name().contains("BULK")) 
				 {
					 conversion=300.00;
				 }
				 if(itemlist.getItem_name().contains("RECYCLE")) 
				 {
					 conversion=Double.parseDouble(packingconv) ;
				 }
				 
			 }
			
		
		//
		
				
				
			
			
			System.out.println(conversion);
			System.out.println(tax);
			System.out.println(pakcingstatus);
			
			
			//output=tally.SendToTally(itemname,itemcategory,masterstock,taxinclude,packingmasterstock,hsncode,conversion,tax,pakcingstatus); // conversion is off as is it based on item Name
			output=tally.SendToTally(itemname,itemcategory,masterstock,taxinclude,packingmasterstock,hsncode,packingconv,tax,pakcingstatus); // conversion is replaced with packingconv with packing_mat_tagging capicity at index[0]
			
			System.out.println(" output :: "+output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println("export status ::"+splitoutput[1]);
			System.out.println(splitoutput[0] + " / " + splitoutput[1]+"/" +id);
			
			int exportstatus=0;
			if(Integer.parseInt(splitoutput[1])==1) 
			{
				exportstatus=1;
			}
			if(Integer.parseInt(splitoutput[2])==1) 
			{
				exportstatus=1;
			}
			//item_masterRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
			item_masterRepository.exportuomtally(id,splitoutput[0],exportstatus);
			System.out.println();
		
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		
		Item_master itemlist1=item_masterRepository.getItemDetails(id);
		
		return itemlist1;
	}
 
	
	
	
	
	 	@Transactional
		public Item_master accountpostingUndoItemMaster(long id) 
		{
			try  
			{
				Optional<Item_master> op=this.item_masterRepository.findById(id);
				LocalDateTime ldt = LocalDateTime.now();
				item_masterRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			Optional<Item_master> op1=this.item_masterRepository.findById(id);
			System.out.println(op1.get());
			return op1.get();
		}	
	 	
 	/* public List<Item_master> searchItemData(String itemname1,String itemgroup,String itemtype1,String company_name)
		{
			List<Item_master> searchITEM =new ArrayList<Item_master>();
			
			String tablename="item_master",item_id="item_id",item_group="item_group",item_category="item_category",item_type="item_type";
			searchITEM.addAll(item_masterRepository.getsearchItemdata(tablename,item_id,item_group,item_category,item_type,itemname1,itemgroup,itemcategory,itemtype1,finyear));
			List<Item_master> allItem = searchITEM
					  .parallelStream()
					  .filter(c -> c.getCompany_id().equals(company_name) && c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Item_master::getItem_id).reversed())
					  .collect(Collectors.toList());
			
			return allItem;
		}*/

		public List<Map<String, Object>> searchItemData(String type,String company)
	 	{
	 		List<Map<String,Object>> itemdatalist=new ArrayList <>();
	 		ArrayList<String> typemultiple=new ArrayList<String>();
	 		if(Utility.isNullOrEmpty(type))
	 		{
	 			//System.out.println("fdate:"+fromdate+"//"+todate+"//"+bunit+"//"+customer);
	 			String multitype[]=type.split(",");
				
				for(int i=0;i<multitype.length;i++)
				{
					typemultiple.add(multitype[i]);
				}
	 		}
	 		itemdatalist.addAll(item_masterRepository.searchItemData(typemultiple,company,true));
	 		
	 		return itemdatalist;
	 	}
		
 	public List<Item_masterDTO> itemnameproduction(String entrymode,String businessunit,String shopfloor)
 	{
 		 List<Item_master> itemlist=new ArrayList<Item_master>();
 		 
 		 
 		 if(entrymode.compareToIgnoreCase("input")==0) 
 		 {
 			 if(shopfloor.compareToIgnoreCase("SF00002")==0) //CHAKKI MILL
 	 		 {
 				itemlist.addAll(item_masterRepository.itemnameproductioninputchakki(businessunit));
 	 		 }
 			 else if(shopfloor.compareToIgnoreCase("SF00001")==0) //ROLLER FLOUR MILL
 	 		 {
 				itemlist.addAll(item_masterRepository.itemnameproductioninputroller(businessunit));
 	 		 }
 			 else //SF00003 //intake
 			 {
 				List<Object[]> itemlistdefault=new ArrayList<Object[]>();
 				itemlistdefault.addAll(item_masterRepository.itemnameproductioninputboth(businessunit));
 				
 				 for(final Object o : itemlistdefault)
 			    {
 					Item_master each=new Item_master();
 			        Object[] obj = (Object[]) o;
 			        each.setItem_id(obj[1].toString());
 			        each.setItem_name(obj[0].toString());
 			       itemlist.add(each);
 			    }
 			   
 				 
 				
 				
 			 }
 			
 		 }
 		 if(entrymode.compareToIgnoreCase("output")==0) 
 		 {
 			 
 			 if(shopfloor.compareToIgnoreCase("SF00002")==0) //CHAKKI MILL
 	 		 {
 				itemlist.addAll(item_masterRepository.itemnameproductionoutputchakki(businessunit));
 	 		 }
 			 else if(shopfloor.compareToIgnoreCase("SF00001")==0) //ROLLER FLOUR MILL
 	 		 {
 				itemlist.addAll(item_masterRepository.itemnameproductionoutputroller(businessunit));
 	 		 }
 			 else //SF00003 //common
 			 {
 				itemlist.addAll(item_masterRepository.itemnameproductionoutputboth(businessunit));
 			 }
 		 }
       
		
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> itemCodeType= new ModelMapper().map(itemlist,listType);
		return itemCodeType;
 	}
		
 	public List<Item_masterDTO> itemNameByGroupProduction(String businessunit,String shopfloor,String process)
 	{
 		System.out.println("businessunit::"+businessunit+"shopfloor:"+shopfloor+"process:"+process);
 		
 		String allgroup="";
 		 String plist[]=process.split(",");
		 
		 for(int i=0;i<plist.length;i++)
		 {
			allgroup +=process_masterRepository.getGroupByProcessName(businessunit,shopfloor,plist[i])+",";
		 }
		 System.out.println("allgroup::"+allgroup);
		 allgroup=allgroup.substring(0,allgroup.length()-1);
		 String grouplist[]=allgroup.split(",");
		 	List<String> itemgroupsplit=new ArrayList<String>();
			for(int i=0;i<grouplist.length;i++)
			{
				itemgroupsplit.add(grouplist[i]);
			}
			List<String> itemGroupList = itemgroupsplit.stream().distinct().collect(
		              Collectors.toList());
		 
		 
		
 		 List<Item_master> itemNamelist=new ArrayList<Item_master>();
 		
 		
 		 for(int i=0;i<itemGroupList.size();i++)
 		 {
 			 System.out.println("item group::"+itemGroupList.get(i));
 			itemNamelist.addAll(item_masterRepository.itemNameByGroupProduction(itemGroupList.get(i)));
 		 }
 		System.out.println("itemNamelist"+itemNamelist.size());
 		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> itemCodeType= new ModelMapper().map(itemNamelist,listType);
		
		return itemCodeType;
 	}
 	
	public List<Item_masterDTO> getItemThruTypeForUsedItem(String bunit,String itype)
 	{
		List<Item_master> inamelist=new ArrayList<Item_master>();
		inamelist.addAll(item_masterRepository.getItemOpening(bunit,itype.replace("%20", " ")));
		
		Type listType = new TypeToken<List<Item_masterDTO>>() {}.getType();
		List<Item_masterDTO> itemname= new ModelMapper().map(inamelist,listType);
		
		return itemname;
 	}
	
	
	public List<Map<String,Object>> getAlternativeItemList(String itemcode)
	{
		System.out.println("itemcode::"+itemcode);
		List<Map<String,Object>> itemdatalist=new ArrayList <>();
		itemdatalist.add(item_masterRepository.getAlternativeItemListitem(itemcode));
		itemdatalist.addAll(item_masterRepository.getAlternativeItemList(itemcode));
		
		return itemdatalist;
	}
	
	
	public Map<String, Object> getItemAlternateprice(String itemid,String alternateitemid)
	{
		return item_master_alternative_dtlsRepository.getItemAlternateprice(itemid,alternateitemid);
	}
	
	public List<Map<String,Object>> retriveClassifiedItem(String item_id,String company)
	{
		return item_master_classificationRepository.retriveClassifiedItem(item_id,company);
	}
	
	public List<Map<String,Object>> retriveItemSizeAndWeight(String item_id,String company)
	{
		return item_master_size_weightRepository.retriveItemSizeWeight(item_id,company);
	}
	
	public List<Map<String,Object>> getPackingMasterCode(String itemid,String packingid)
	{
		return item_master_size_weightRepository.getPackingMasterCode(itemid,packingid);
	}
	
	public Map<String, Object> retrivePackingDtls(String packingMasterCode, String packingid)
	{
		return item_master_size_weightRepository.retrivePackingDtls(packingMasterCode, packingid);
	}
	
	public List<Map<String,Object>> getItemMasterPackMatNew(String code)
	{
		return item_master_pack_mat_tagRepository.getItemMasterPackMatNew(code);
	}
	
	public Map<String,Object> getItemPackUomNew(String code,String code1,String company){
 	return item_master_pack_mat_tagRepository.getItemPackUomNew(code,code1,company);
	}
	
}	
