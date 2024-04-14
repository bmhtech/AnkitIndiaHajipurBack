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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Charge_Masterdtls;
import com.AnkitIndia.jwtauthentication.repository.Charge_MasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Screen_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Charge_MasterDetailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterdtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
@Repository
public class Charge_MasterService_Imp implements Charge_MasterService{
	
	@Autowired
	Charge_MasterRepository charge_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Screen_masterRepository screen_masterRepository;
	
	@Transactional
	public Charge_Master save(Charge_Master charge_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		String prefix="CHG";
		long slno=0;
		if(charge_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(charge_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		charge_master.setCharge_id(gen_sno);
		
		if(charge_master.getScreen_id().compareTo("0") !=0 && charge_master.getScreen_id().compareTo("") !=0 && charge_master.getScreen_id() !=null) {
			charge_master.setScreen_name(screen_masterRepository.getScreenDetails(charge_master.getScreen_id()).getScreen_name());
		}else {charge_master.setScreen_name("None");}
		
		charge_master.setModified_type("INSERTED");
		charge_master.setInserted_by(userRepository.getUserDetails(charge_master.getUsername()).getName());
		charge_master.setInserted_on(ldt);
		charge_master.setUpdated_by("NA");
		charge_master.setUpdated_on(ldt);
		charge_master.setDeleted_by("NA");
		charge_master.setDeleted_on(ldt);
	
		//Dynamic
		Set<Charge_Masterdtls> chargeset = new HashSet<Charge_Masterdtls>();
		chargeset.addAll(charge_master.getCharge_masterdtls());
		for(Charge_Masterdtls chargdts : chargeset)
		{
			chargdts.setCharge_id(gen_sno);
			chargdts.setModified_type("INSERTED");
			chargdts.setInserted_by(charge_master.getInserted_by());
			chargdts.setInserted_on(charge_master.getInserted_on());
			chargdts.setUpdated_by("NA");
			chargdts.setUpdated_on(ldt);
			chargdts.setDeleted_by("NA");
			chargdts.setDeleted_on(ldt);
		}
		charge_master.setCharge_masterdtls(chargeset);
		
		return charge_masterRepository.save(charge_master);
	}
	
	
	@Autowired
	Charge_MasterDetailsRepository charge_MasterdtlsRepository;
	
	@Transactional
	public Charge_Master update(Charge_Master charge_master,long id)
	{
		Optional<Charge_Master> op = charge_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(charge_master.getScreen_id().compareTo("0") !=0 && charge_master.getScreen_id().compareTo("") !=0 && charge_master.getScreen_id() !=null) {
			charge_master.setScreen_name(screen_masterRepository.getScreenDetails(charge_master.getScreen_id()).getScreen_name());
		}else {charge_master.setScreen_name("None");}
		
		charge_master.setCharge_id(op.get().getCharge_id());
		charge_master.setModified_type("INSERTED");
		charge_master.setInserted_by(op.get().getInserted_by());
		charge_master.setInserted_on(op.get().getInserted_on());
		charge_master.setUpdated_by(userRepository.getUserDetails(charge_master.getUsername()).getName());
		charge_master.setUpdated_on(ldt);
		charge_master.setDeleted_by("NA");
		charge_master.setDeleted_on(ldt);
		
		//Dynamic
		charge_MasterdtlsRepository.updateCharge_Masterdtls(charge_master.getCharge_id(),"UPDATED");
		Set<Charge_Masterdtls> chargeset = new HashSet<Charge_Masterdtls>();
		chargeset.addAll(charge_master.getCharge_masterdtls());
		for(Charge_Masterdtls chargdts : chargeset)
		{
			chargdts.setCharge_id(charge_master.getCharge_id());
			chargdts.setModified_type("INSERTED");
			chargdts.setInserted_by(charge_master.getInserted_by());
			chargdts.setInserted_on(charge_master.getInserted_on());
			chargdts.setUpdated_by("NA");
			chargdts.setUpdated_on(ldt);
			chargdts.setDeleted_by("NA");
			chargdts.setDeleted_on(ldt);
		}
	
		charge_master.setCharge_masterdtls(chargeset);
		
		if(op.isPresent())
		{
			charge_master.setId(id);
		}
		return charge_masterRepository.save(charge_master);
	}
	
	public List<Charge_Master> findAll()
	{
		List<Charge_Master> chargeList=new ArrayList<Charge_Master>();
		chargeList.addAll(charge_masterRepository.findAll());
				
		List<Charge_Master> allData = chargeList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Charge_Master::getCharge_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Charge_Master findOne(long id)
	{
		Optional<Charge_Master> op=this.charge_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Charge_MasterDTO> getChargeMasterList() {
		
		List<Charge_Master> chargeList=charge_masterRepository.findAll();
		
		chargeList.forEach((e)->{
			e.setCharge_desc(e.getCharge_desc().toUpperCase());
		});

				
		List<Charge_Master> allData = chargeList
			  .stream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Charge_Master::getCharge_desc))
			  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Charge_MasterDTO>>() {}.getType();
		
		List<Charge_MasterDTO> chargeMasterList = new ModelMapper().map(allData,listType);
		
		return chargeMasterList;
	}
	
	public List<Charge_MasterdtlsDTO> getChargeMasterdtlsList(String code)
	{
		System.out.println(code);
		String[] arrOfStr=code.split(",");
		
		List<Charge_Master> modelList=new ArrayList<Charge_Master>();
		
		for(int i=0;i<arrOfStr.length;i++)
		{
			System.out.println(arrOfStr[i]);
			modelList.addAll(charge_masterRepository.getChargeMasterdtlsList(arrOfStr[i]));
		}
			
		Type listType=new TypeToken<List<Charge_MasterdtlsDTO>>() {}.getType();
		List<Charge_MasterdtlsDTO> cDtlsList=new ModelMapper().map(modelList,listType);
		return cDtlsList;
	}
	
	public List<Charge_MasterdtlsDTO> chargeRetriveList(String c_id)
	{
		List<Charge_Masterdtls> chargeList=new ArrayList<Charge_Masterdtls>();
		
		chargeList.addAll(charge_masterRepository.getChargeMasterdtlsListnew(c_id));
	
		
		
		List<Charge_Masterdtls> allData = chargeList
			  .stream()
			 
			  .collect(Collectors.toList());
		
			
		Type listType=new TypeToken<List<Charge_MasterdtlsDTO>>() {}.getType();
		
		List<Charge_MasterdtlsDTO> charge=new ModelMapper().map(allData,listType);
		
		return charge;
	}
	
	
	public List<Charge_Master> findChargeMaster(String searchtext)
	{
		List<Charge_Master> chargeList=new ArrayList<Charge_Master>();
		chargeList.addAll(charge_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Charge_Master> allData = chargeList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Charge_Master::getCharge_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Charge_Master> allData = chargeList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getCharge_desc()+c.getScreen_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Charge_Master::getCharge_desc))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	
	@Transactional
	public Charge_Master deleteCharge(Charge_Master charge_master,long id)
	{
		Optional<Charge_Master> op = charge_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Charge_Master cMaster=op.get();
		
		cMaster.setInserted_by(op.get().getInserted_by());
		cMaster.setInserted_on(op.get().getInserted_on());
		cMaster.setUpdated_by(op.get().getUpdated_by());
		cMaster.setUpdated_on(op.get().getUpdated_on());
		cMaster.setDeleted_by(userRepository.getUserDetails(charge_master.getUsername()).getName());
		cMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			cMaster.setId(id);
		}
			cMaster.setModified_type("DELETED");
			charge_MasterdtlsRepository.updateCharge_Masterdtls(cMaster.getCharge_id(),"DELETED");
			return charge_masterRepository.save(cMaster);
		
	}
	
	public StatusDTO checkChargeMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false,sales=false,pur_bill=false,sales_invoice=false;
		
		if(charge_masterRepository.checkChargeMasterPurchaseUsage(code))
		{
			purchase=true;
		}
		
		if(charge_masterRepository.checkChargeMasterSalesUsage(code))
		{
			sales=true;
		}
		
		if(charge_masterRepository.checkChargeMasterPurBillUsage(code))
		{
			pur_bill=true;
		}
		
		if(charge_masterRepository.checkChargeMasterSalesInvUsage(code))
		{
			sales_invoice=true;
		}
		
		
		
		if(purchase == true || sales == true || pur_bill == true || sales_invoice == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		
		return result;
	 }
	
}
