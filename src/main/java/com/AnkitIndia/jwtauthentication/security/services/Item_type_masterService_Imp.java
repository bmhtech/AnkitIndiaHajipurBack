package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_type_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_catagory_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_type_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Item_type_masterService_Imp implements Item_type_masterService {
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Item_catagory_masterRepository item_catagory_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;

	public SequenceIdDTO getItypeSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=item_type_masterRepository.getItypeSequenceId(fprefix,company);
			System.err.println("No: > "+gen_sno);
			
			if(gen_sno != null ) {
				slno=Long.parseLong(gen_sno);
			}
			
			String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
			
			Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
			
			SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
			
			genCode.setSequenceid(gen_slno);
			
			return genCode;
		}
	
	@Transactional
	public Item_type_master saveItemType(Item_type_master item_type_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="ITMT";
		if(item_type_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(item_type_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		long nslno=0;
		String gen_snonew=item_type_masterRepository.getItypeSequenceId(item_type_master.getItem_code().substring(0,7),item_type_master.getCompany_id());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(item_type_master.getItem_code().substring(0,7),nslno);
		item_type_master.setItem_code(gen_tslno);
		/*End checking transaction no for unique */
		
		item_type_master.setItem_id(gen_sno);
		item_type_master.setModified_type("INSERTED");
		item_type_master.setInserted_by(userRepository.getUserDetails(item_type_master.getUsername()).getName());
		item_type_master.setInserted_on(ldt);
		item_type_master.setUpdated_by("NA");
		item_type_master.setUpdated_on(ldt);
		item_type_master.setDeleted_by("NA");
		item_type_master.setDeleted_on(ldt);
		
		return  item_type_masterRepository.save(item_type_master);
	}
	
	public List<Item_type_master> getItemtypes(String company)
	{
		List<Item_type_master> iTypeList=item_type_masterRepository.findAll();
				
		List<Item_type_master> allData = iTypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
			  .sorted(Comparator.comparing(Item_type_master::getItem_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	@Transactional
	public Item_type_master update(Item_type_master iType_master,long id)
	{
		Optional<Item_type_master> op = item_type_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iType_master.setItem_id(op.get().getItem_id());
		iType_master.setModified_type("UPDATED");
		iType_master.setInserted_by(op.get().getInserted_by());
		iType_master.setInserted_on(op.get().getInserted_on());
		iType_master.setUpdated_by(userRepository.getUserDetails(iType_master.getUsername()).getName());
		iType_master.setUpdated_on(ldt);
		iType_master.setDeleted_by("NA");
		iType_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iType_master.setId(id);
		}
		return item_type_masterRepository.save(iType_master);
	}
	
	@Transactional
	public Item_type_master deleteItemTypes(Item_type_master iType,long id)
	{
		Optional<Item_type_master> op = item_type_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Item_type_master iType_master=op.get();
		
		iType_master.setModified_type("DELETED");
		iType_master.setInserted_by(op.get().getInserted_by());
		iType_master.setInserted_on(op.get().getInserted_on());
		iType_master.setUpdated_by(op.get().getUpdated_by());
		iType_master.setUpdated_on(op.get().getUpdated_on());
		iType_master.setDeleted_by(userRepository.getUserDetails(iType.getUsername()).getName());
		iType_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iType_master.setId(id);
		}
		return item_type_masterRepository.save(iType_master);
	}
	
	public List<Item_type_master> findItemTypes(String searchtext,String company)
	{
		List<Item_type_master> iTypeList=new ArrayList<Item_type_master>();
		iTypeList.addAll(item_type_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Item_type_master> allData = iTypeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_type_master::getItem_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Item_type_master> allData = iTypeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) &&
							  (c.getItem_id()+c.getItem_code()+c.getItem_name()+c.isItem_active()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Item_type_master::getItem_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public List<Item_type_masterDTO> getItemtypes(int psize,int pageNo)
	{
		long count = item_type_masterRepository.count();
		pageNo=pageNo-1;
		List<Item_type_master> modelList=null;
		
		if(count > psize) {
			long pages = count / psize;
			for (int i = 0; i < pages; i++) {
				if(i==pageNo ) {
					modelList=item_type_masterRepository.getItemTypesList(PageRequest.of(pageNo, psize,Sort.by("item_id").descending()));
				}
				else {
					modelList=item_type_masterRepository.getItemTypesList(PageRequest.of(pageNo, psize,Sort.by("item_id").descending()));
				}
			}
		}
		else {
			modelList=item_type_masterRepository.getItemTypesList(PageRequest.of(pageNo, psize,Sort.by("item_id").descending()));
		}
		
		Type listType = new TypeToken<List<Item_type_masterDTO>>() {}.getType();
		
		List<Item_type_masterDTO> iTypeList = new ModelMapper().map(modelList,listType);
		
		return iTypeList;
	}
	
	public Item_type_master findOne(long id)
	{
		Optional<Item_type_master> op=this.item_type_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Item_type_masterDTO> getItemType(String company) {
		
		List<Item_type_master> iTypeList=item_type_masterRepository.findAll();

		iTypeList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
				
		List<Item_type_master> allData = iTypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
			  .sorted(Comparator.comparing(Item_type_master::getItem_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_type_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_type_masterDTO> itemTypeList = new ModelMapper().map(allData,listType);
		
		return itemTypeList;
	}
	
	public List<Item_type_masterDTO> getItemTypesNew(String company) {
		
		List<Item_type_master> iTypeList=item_type_masterRepository.findAll();

		iTypeList.forEach((e)->{
			e.setItem_name(e.getItem_name().toUpperCase());
		});
				
		List<Item_type_master> allData = iTypeList
			  .stream()
			  //.filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) && !c.getItem_id().equals("ITMT00004") && !c.getItem_id().equals("ITMT00005"))
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
			  .sorted(Comparator.comparing(Item_type_master::getItem_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_type_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_type_masterDTO> itemTypeList = new ModelMapper().map(allData,listType);
		
		return itemTypeList;
	}
	
	public List<Map<String,Object>> itemTypeListFastAPI(String company)
	{
		List<Map<String, Object>> finallist=new ArrayList<Map<String, Object>>();
		finallist.addAll(item_type_masterRepository.itemTypeListFastAPI(company));
		return finallist;
	}
	
public List<Item_type_masterDTO> getItemTypeMaster(String company) {
		
		List<Item_type_master> iTypeList=item_type_masterRepository.findAll();

				
		List<Item_type_master> allData = iTypeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
			  .sorted(Comparator.comparing(Item_type_master::getItem_id).reversed())
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_type_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_type_masterDTO> itemTypeList = new ModelMapper().map(allData,listType);
		
		return itemTypeList;
	}
	
	 public Item_type_masterDTO getItemTypeNameByCode(String iType){
		 	System.err.println("Category: "+item_catagory_masterRepository.catagoryNameByCode(iType).getItem_type());
		 	Item_type_master modelList=item_type_masterRepository.itemTypeNameByCode(item_catagory_masterRepository.catagoryNameByCode(iType).getItem_type());
			
		 	System.err.println(modelList);
			Type listType = new TypeToken<Item_type_masterDTO>() {}.getType();
			
			Item_type_masterDTO itemTypeName= new ModelMapper().map(modelList,listType);
			
			return itemTypeName;
			
		}
	 
	public void deleteItemType(Item_type_master iType_master) 
	{
		item_type_masterRepository.delete(iType_master);
	}
	
	public StatusDTO chkItemTypeCodeStatus(String code){
		String result="";
		
		if(item_type_masterRepository.chkItemTypeCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}

}
