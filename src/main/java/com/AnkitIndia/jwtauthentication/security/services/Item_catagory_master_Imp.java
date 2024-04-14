package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import com.AnkitIndia.jwtauthentication.model.Item_catagory_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_catagory_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.System_settingsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_catagory_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;

@Service
public class Item_catagory_master_Imp implements Item_catagory_masterService {
	
	@Autowired
	Item_catagory_masterRepository item_catagory_masterRepository;
	
	@Autowired
	Item_type_masterRepository iTypeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	System_settingsRepository system_settingsRepository;

	public SequenceIdDTO getIcatSequenceId(String prefix,String company) {
			Long slno=0L;String fprefix="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			fprefix=prefix+"/"+code+"/";
			System.err.println("Code: > "+fprefix);
			String gen_sno=item_catagory_masterRepository.getIcatSequenceId(fprefix,company);
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
	public Item_catagory_master saveItemCategory(Item_catagory_master item_catagory_master)
	{
		Item_catagory_master iCatagory_master=new Item_catagory_master();
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0; String prefix="IC";
		if(item_catagory_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(item_catagory_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		item_catagory_master.setCatagory_id(gen_sno);
		item_catagory_master.setModified_type("INSERTED");
		item_catagory_master.setInserted_by(userRepository.getUserDetails(item_catagory_master.getUsername()).getName());
		item_catagory_master.setInserted_on(ldt);
		item_catagory_master.setUpdated_by("NA");
		item_catagory_master.setUpdated_on(ldt);
		item_catagory_master.setDeleted_by("NA");
		item_catagory_master.setDeleted_on(ldt);
		
		if(system_settingsRepository.getSystemSettingsByComp(item_catagory_master.getCompany_id()).get().isCode_generator()) {
			System.out.println("Auto");
			
			/*Start checking transaction no for unique */
			long nslno=0;String tprefix=item_catagory_master.getCatagory_code();
			String gen_snonew=item_catagory_masterRepository.getIcatSequenceId(tprefix.substring(0,7),item_catagory_master.getCompany_id());
			System.out.println("Got SL: "+gen_snonew);
			if(gen_snonew != null ) {
				nslno=Long.parseLong(gen_snonew);
			}
			String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,7),nslno);
			item_catagory_master.setCatagory_code(gen_tslno);
			System.out.println("Got Code: "+item_catagory_master.getCatagory_code());
			/*End checking transaction no for unique */
			
			return item_catagory_masterRepository.save(item_catagory_master);
		}else {
			System.out.println("User Gen");
			if(chkItemCatagoryCodeStatus(item_catagory_master.getCatagory_code()).getStatus().compareTo("EXIST")==0) {
				System.out.println("Exist !!!");
				return iCatagory_master;
			}else {
				System.out.println("Not Exist !!!");
				return item_catagory_masterRepository.save(item_catagory_master);
			}
		}
	}
	
	public List<Item_catagory_master> findAll(String company)
	{
		List<Item_catagory_master> icList=new ArrayList<Item_catagory_master>();
		icList.addAll(item_catagory_masterRepository.findAll());
		
		List<Item_catagory_master> allData = icList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Item_catagory_master::getCatagory_id).reversed())
				  .collect(Collectors.toList());
		
		allData.forEach((ic)->{
			if(Utility.isNullOrEmpty(ic.getItem_type())) {
				ic.setItem_type(iTypeRepository.itemTypeNameByCode(ic.getItem_type()).getItem_name());
			}else {ic.setItem_type("None");}
		});
		return allData;
	}
	
	public List<Item_catagory_master> findICategories(String company,String fin_year)
	{
		List<Item_catagory_master> icList=new ArrayList<Item_catagory_master>();
		icList.addAll(item_catagory_masterRepository.findAll());
		
		List<Item_catagory_master> allData = icList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) && c.getFin_year().equals(fin_year))
				  .sorted(Comparator.comparing(Item_catagory_master::getCatagory_id).reversed())
				  .collect(Collectors.toList());
		
		allData.forEach((ic)->{
			if(Utility.isNullOrEmpty(ic.getItem_type())) {
				ic.setItem_type(iTypeRepository.itemTypeNameByCode(ic.getItem_type()).getItem_name());
			}else {ic.setItem_type("None");}
		});
		return allData;
	}
	
	public List<Item_catagory_master> findAll(int psize,int pageNo)
	{
		//return item_catagory_masterRepository.findAll();
		List<Item_catagory_master> modelList1 = null;
		//System.err.println("Got Value: "+psize +" , "+pageNo);
		//Pageable paging = PageRequest.of(pageNo, psize);
		
		long count = item_catagory_masterRepository.count();
		
		System.err.println("List Size:-----------> "+count);
		int pageSize = 5;
		if(count > psize) {
			long pages = count / psize;
			System.err.println("Total Pages : "+pages);
			for (int i = 0; i < pages; i++) {
				System.err.println("Val: i"+(i+1)+" Page: "+pageNo);
				if((i+1)==pageNo ) {
					System.err.println("Got Result 1: Page Size:"+psize+" Page No: "+pageNo+" i:"+i);
					modelList1=item_catagory_masterRepository.itemCategoriesList(PageRequest.of(pageNo, psize,Sort.by("catagory_id").descending()));
				}
				/*else {
					System.err.println("Got Result 2: Page Size:"+psize+" P No: "+pageNo+" i:"+i);
					modelList1=item_catagory_masterRepository.itemCategoriesList(PageRequest.of(i, psize,Sort.by("catagory_id").descending()));
				}*/
				
			}
		}
		else {
			System.err.println("Got Result 3: Page Size:"+psize+" P No: "+pageNo);
			modelList1=item_catagory_masterRepository.itemCategoriesList(PageRequest.of(0, psize,Sort.by("catagory_id").descending()));
		}
		
		/*Page<Item_catagory_master> pagedResult = item_catagory_masterRepository.findAll(paging);
        
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Item_catagory_master>();
        }*/
		
		return modelList1;
	}
	
	public List<Item_catagory_master> findItemCategories(String searchtext,String company)
	{
		List<Item_catagory_master> icList=new ArrayList<Item_catagory_master>();
		icList.addAll(item_catagory_masterRepository.findAll());
		
		icList.forEach((ic) ->{
			if(Utility.isNullOrEmpty(ic.getItem_type())) {
				ic.setItem_type(iTypeRepository.itemTypeNameByCode(ic.getItem_type()).getItem_name());
			}else {ic.setItem_type("None");}
		});
			
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Item_catagory_master> allData = icList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_catagory_master::getCatagory_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Item_catagory_master> allData = icList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) && 
							  (c.getCatagory_code()+ c.getCatagory_name()+ c.getItem_type()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Item_catagory_master::getCatagory_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public List<Item_catagory_masterDTO> getIcategory(String company) {
		List<Item_catagory_master> icList=item_catagory_masterRepository.findAll();
		
		icList.forEach((e)->{
			e.setCatagory_name(e.getCatagory_name().toUpperCase());
		});
		
		List<Item_catagory_master> allData = icList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company) && c.isItem_active()==true)
				  .sorted(Comparator.comparing(Item_catagory_master::getCatagory_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Item_catagory_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Item_catagory_masterDTO> itemCategoryList = new ModelMapper().map(allData,listType);
		
		return itemCategoryList;
	}
  	
    public Item_catagory_masterDTO getItemTypeByName(String iType){
    	
    	Item_catagory_master modelList=item_catagory_masterRepository.catagoryNameByCode(iType);
		
		Type listType = new TypeToken<Item_catagory_masterDTO>() {}.getType();
		
		Item_catagory_masterDTO itemTypeList= new ModelMapper().map(modelList,listType);
		
		return itemTypeList;
	}
    
    public Item_catagory_master findOne(long id)
    {
    	Optional<Item_catagory_master> icategory=this.item_catagory_masterRepository.findById(id);
    	return icategory.get();
    }
    
    @Transactional
    public Item_catagory_master update(Item_catagory_master iMaster,long id)
	{
		Optional<Item_catagory_master> op = item_catagory_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setCatagory_id(op.get().getCatagory_id());
		iMaster.setModified_type("UPDATED");
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
		return item_catagory_masterRepository.save(iMaster);
	}
    
    @Transactional
    public Item_catagory_master deleteItemCategory(Item_catagory_master iCatagory_master,long id)
	{
		Optional<Item_catagory_master> op = item_catagory_masterRepository.findById(id);
		Item_catagory_master iCatagory=op.get();
		LocalDateTime ldt = LocalDateTime.now();
		
		iCatagory.setInserted_by(op.get().getInserted_by());
		iCatagory.setInserted_on(op.get().getInserted_on());
		iCatagory.setUpdated_by(op.get().getUpdated_by());
		iCatagory.setUpdated_on(op.get().getUpdated_on());
		iCatagory.setDeleted_by(userRepository.getUserDetails(iCatagory_master.getUsername()).getName());
		iCatagory.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iCatagory.setId(id);
		}
		
		if(item_masterRepository.getItemDtlsThruCategory(iCatagory.getCatagory_id()).isPresent()) {
			return iCatagory_master;
		}else {
			iCatagory.setModified_type("DELETED");
			return item_catagory_masterRepository.save(iCatagory);
		}
	}
    
    public MessageStatusDTO chkCatNameStatus(String cname)
	{
		String result=item_catagory_masterRepository.chkCatNameStatus(cname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
    
    public StatusDTO chkItemCatagoryCodeStatus(String code){
		String result="";
		
		if(item_catagory_masterRepository.chkItemCatagoryCodeStatus(code).isPresent()) {
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
