package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Reason_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Reason_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Screen_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Reason_masterDTO;


@Service
public class Reason_masterService_Imp implements Reason_masterService{

	@Autowired
	Reason_masterRepository reason_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Screen_masterRepository screen_masterRepository;
	
	@Transactional
	public Reason_master save(Reason_master reason_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		String prefix="RSM";
		
		if(reason_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(reason_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(reason_master.getScreen_code().compareTo("0") !=0 && reason_master.getScreen_code().compareTo("") !=0 && reason_master.getScreen_code() !=null) {
			reason_master.setScreen_name(screen_masterRepository.getScreenDetails(reason_master.getScreen_code()).getScreen_name());
		}else {reason_master.setScreen_name("None");}
		
		reason_master.setReason_id(gen_sno);
		reason_master.setModified_type("INSERTED");
		reason_master.setInserted_by(userRepository.getUserDetails(reason_master.getUsername()).getName());
		reason_master.setInserted_on(ldt);
		reason_master.setUpdated_by("NA");
		reason_master.setUpdated_on(ldt);
		reason_master.setDeleted_by("NA");
		reason_master.setDeleted_on(ldt);
		
		return  reason_masterRepository.save(reason_master);
	}
	
	@Transactional
	public Reason_master update(Reason_master reason_master,long id)
	{
		Optional<Reason_master> op = reason_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(reason_master.getScreen_code().compareTo("0") !=0 && reason_master.getScreen_code().compareTo("") !=0 && reason_master.getScreen_code() !=null) {
			reason_master.setScreen_name(screen_masterRepository.getScreenDetails(reason_master.getScreen_code()).getScreen_name());
		}else {reason_master.setScreen_name("None");}
		
		reason_master.setReason_id(op.get().getReason_id());
		reason_master.setModified_type("UPDATED");
		reason_master.setInserted_by(op.get().getInserted_by());
		reason_master.setInserted_on(op.get().getInserted_on());
		reason_master.setUpdated_by(userRepository.getUserDetails(reason_master.getUsername()).getName());
		reason_master.setUpdated_on(ldt);
		reason_master.setDeleted_by("NA");
		reason_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			reason_master.setId(id);
		}
		return reason_masterRepository.save(reason_master);
	}
	
	@Transactional
	public Reason_master deleteReason(Reason_master reason_master,long id)
	{
		Optional<Reason_master> op = reason_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Reason_master reasonMaster=op.get();

		reasonMaster.setInserted_by(op.get().getInserted_by());
		reasonMaster.setInserted_on(op.get().getInserted_on());
		reasonMaster.setUpdated_by(op.get().getUpdated_by());
		reasonMaster.setUpdated_on(op.get().getUpdated_on());
		reasonMaster.setDeleted_by(userRepository.getUserDetails(reason_master.getUsername()).getName());
		reasonMaster.setDeleted_on(ldt);
		reasonMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			reasonMaster.setId(id);
		}
		return reason_masterRepository.save(reasonMaster);
	}
	
	public List<Reason_master> findAll()
	{
		List<Reason_master> reasonList=new ArrayList<>();
		reasonList.addAll(reason_masterRepository.findAll());
		
		List<Reason_master> allData =reasonList.stream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Reason_master::getReason_id).reversed())
				  .collect(Collectors.toList());
		return allData;
	}
	
	public Reason_master findOne(long id)
	{
		Optional<Reason_master> op=this.reason_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Reason_masterDTO> getReasonMNCList(){
		
		List<Reason_master> reaList=reason_masterRepository.findAll();

		reaList.forEach((e)->{
			e.setReason(e.getReason().toUpperCase());
		});
		
		List<Reason_master> allData = reaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Reason_master::getReason))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<Reason_masterDTO>>() {}.getType();
		
		List<Reason_masterDTO> reasonList= new ModelMapper().map(allData,listType);
		
		return reasonList;
	}
	
	public List<Reason_masterDTO> getReasonIndent(){
		List<Reason_master> reaList=new ArrayList<Reason_master>();
		reaList.addAll(reason_masterRepository.findAll());
				
		List<Reason_master> allData = reaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getScreen_name().equals("Indent"))
			  .sorted(Comparator.comparing(Reason_master::getReason))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<Reason_masterDTO>>() {}.getType();
		
		List<Reason_masterDTO> reasonInd= new ModelMapper().map(allData,listType);
		
		return reasonInd;
	}
	
	public List<Reason_masterDTO> getPurTermReasons(){
		
		List<Reason_master> reaList=reason_masterRepository.findAll();
		reaList.forEach((e)->{
			e.setReason(e.getReason().toUpperCase());
		});
	
		List<Reason_master> allData = reaList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getScreen_name().equals("PURCHASE ORDER"))
			  .sorted(Comparator.comparing(Reason_master::getReason))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<Reason_masterDTO>>() {}.getType();
		
		List<Reason_masterDTO> reasonList= new ModelMapper().map(allData,listType);
		
		return reasonList;
	}
	
	public List<Reason_master> findReason(String searchtext)
	{
		List<Reason_master> reasonList=new ArrayList<Reason_master>();
		reasonList.addAll(reason_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Reason_master> allData = reasonList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Reason_master::getReason))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Reason_master> allData = reasonList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getReason()+c.getScreen_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Reason_master::getReason))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
