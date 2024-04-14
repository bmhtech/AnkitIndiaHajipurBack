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
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.GodownMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class Item_Service_masterService_Imp implements Item_Service_masterService{

	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	Tax_code_masterRepository tax_code_masterRepository;
	
	
	public SequenceIdDTO getItemServiceSequenceId(String company,String fin_year) 
	{
		int slno=0;
		String sno=item_Service_masterRepository.countId(company);
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="ISM-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public List<Map<String, Object>> getItemServiceList(String company){
		
		return item_Service_masterRepository.getItemServiceList(company);
	}
	
	public List<Item_Service_master> findItemServiceMaster(String searchtext,String company)
	{
		List<Item_Service_master> igroupList=new ArrayList<Item_Service_master>();
		igroupList.addAll(item_Service_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			
			List<Item_Service_master> allData = igroupList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") && c.getCompany_id().equals(company))
					  .sorted(Comparator.comparing(Item_Service_master::getService_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			
			List<Item_Service_master> allData = igroupList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED")  && c.getCompany_id().equals(company) && 
							  (c.getService_id()+c.getService_code()+c.getService_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Item_Service_master::getService_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public Item_Service_master findOne(long id)
	{
		Optional<Item_Service_master> op=this.item_Service_masterRepository.findById(id);
		return op.get();
	}
	
	public MessageStatusDTO chkItemServiceNameStatic(String cname)
	{
		String result=item_Service_masterRepository.chkItemServiceNameStatic(cname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	
	@Transactional
	public Item_Service_master save(Item_Service_master service) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(item_Service_masterRepository.countId(service.getCompany_id()) != null ) {
			slno=Long.parseLong(item_Service_masterRepository.countId(service.getCompany_id()));
		}
		String prefix="ISR";
		String gen_sno=UniqueID.uniqueid(prefix,slno);

		int slno1=0;
		String sno1=item_Service_masterRepository.countId(service.getCompany_id());
		
		if(sno1 != null )
		{
			slno=Integer.parseInt(sno1);
		}
		if(sno1 != null )
		{
			slno1=Integer.parseInt(sno1);
		}
		
		String fin_yearspit[]=service.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix1="ISM-"+final_fyear+"-";
		String gen_sno1=UniqueIDTransaction.uniqueId4(prefix1,slno1);
		
		service.setService_id(gen_sno1);
		service.setService_code(gen_sno);
		
		if(service.getAc_ledger().compareTo("0") !=0 && service.getAc_ledger().compareTo("") !=0 && service.getAc_ledger() !=null) {
			service.setAc_ledger_name(ledgermasterRepository.getLedgers(service.getAc_ledger()).getLedgername());
		}else {service.setAc_ledger_name("None");}
		
		if(service.getGst_code().compareTo("0") !=0 && service.getGst_code().compareTo("") !=0 && service.getGst_code() !=null) {
			service.setGst_name(tax_code_masterRepository.getTaxNameByTaxCodesaleorder(service.getGst_code()).getTax_name());
		}else {service.setGst_name("None");}
		
		service.setCompany_id(service.getCompany_id());
		service.setFin_year(service.getFin_year());
		service.setModified_type("INSERTED");
		service.setInserted_on(ldt);
		service.setInserted_by(userRepository.getUserDetails(service.getUsername()).getName());
		service.setUpdated_by(service.getUpdated_by());
		service.setUpdated_on(ldt);
		service.setDeleted_by("NA");
		service.setDeleted_on(ldt);
		
		return item_Service_masterRepository.save(service);
	}
	
	@Transactional
	public Item_Service_master update(Item_Service_master service,long id)
	{
		Optional<Item_Service_master> op =item_Service_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		service.setService_code(op.get().getService_code());
		
		if(service.getAc_ledger().compareTo("0") !=0 && service.getAc_ledger().compareTo("") !=0 && service.getAc_ledger() !=null) {
			service.setAc_ledger_name(ledgermasterRepository.getLedgers(service.getAc_ledger()).getLedgername());
		}else {service.setAc_ledger_name("None");}
		
		if(service.getGst_code().compareTo("0") !=0 && service.getGst_code().compareTo("") !=0 && service.getGst_code() !=null) {
			service.setGst_name(tax_code_masterRepository.getTaxNameByTaxCodesaleorder(service.getGst_code()).getTax_name());
		}else {service.setGst_name("None");}
		
		service.setCompany_id(service.getCompany_id());
		service.setFin_year(service.getFin_year());
		service.setModified_type("INSERTED");
		service.setInserted_on(op.get().getInserted_on());
		service.setInserted_by(op.get().getInserted_by());
		service.setUpdated_by(userRepository.getUserDetails(service.getUsername()).getName());
		service.setUpdated_on(ldt);
		service.setDeleted_by("NA");
		service.setDeleted_on(ldt);
		
		return item_Service_masterRepository.save(service);
	}
	
	@Transactional
	public Item_Service_master delete(Item_Service_master service,long id)
	{
		Optional<Item_Service_master> op = item_Service_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Item_Service_master iser=op.get();
		
		iser.setInserted_by(op.get().getInserted_by());
		iser.setInserted_on(op.get().getInserted_on());
		iser.setUpdated_by(op.get().getUpdated_by());
		iser.setUpdated_on(op.get().getUpdated_on());
		iser.setDeleted_by(userRepository.getUserDetails(iser.getUsername()).getName());
		iser.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iser.setId(id);
		}
		iser.setModified_type("DELETED");
			return item_Service_masterRepository.save(iser);
		
	}
	
}
