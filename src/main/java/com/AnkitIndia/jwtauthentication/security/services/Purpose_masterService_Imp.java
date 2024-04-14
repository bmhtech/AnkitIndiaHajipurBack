package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Purpose_master;
import com.AnkitIndia.jwtauthentication.repository.Purpose_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Purpose_masterDTO;

@Service
public class Purpose_masterService_Imp implements Purpose_masterService {
	
	@Autowired
	Purpose_masterRepository purpose_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Purpose_master save(Purpose_master pMaster)
	{
		
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		String prefix="PM";
		if(purpose_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(purpose_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		pMaster.setPurpose_id(gen_sno);
		pMaster.setModified_type("INSERTED");
		pMaster.setInserted_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setInserted_on(ldt);
		pMaster.setUpdated_by("NA");
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);

		return purpose_masterRepository.save(pMaster);
	}
	
	@Transactional
	public Purpose_master update(Purpose_master pMaster,long id)
	{
		Optional<Purpose_master> op = purpose_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		pMaster.setPurpose_id(op.get().getPurpose_id());
		pMaster.setModified_type("UPDATED");
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		pMaster.setUpdated_on(ldt);
		pMaster.setDeleted_by("NA");
		pMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			pMaster.setId(id);
		}
		return purpose_masterRepository.save(pMaster);
	}
	
	@Transactional
	public Purpose_master deletePurpose(Purpose_master pMaster,long id)
	{
		Optional<Purpose_master> op = purpose_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Purpose_master purposeMaster=op.get();

		purposeMaster.setInserted_by(op.get().getInserted_by());
		purposeMaster.setInserted_on(op.get().getInserted_on());
		purposeMaster.setUpdated_by(op.get().getUpdated_by());
		purposeMaster.setUpdated_on(op.get().getUpdated_on());
		purposeMaster.setDeleted_by(userRepository.getUserDetails(pMaster.getUsername()).getName());
		purposeMaster.setDeleted_on(ldt);
		purposeMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			purposeMaster.setId(id);
		}
		
		
		return purpose_masterRepository.save(purposeMaster);
	}
	
	public List<Purpose_master> findAll()
	{

		List<Purpose_master> purList=new ArrayList<>();
		purList.addAll(purpose_masterRepository.findAll());
		//System.out.println("1"+purList.size());
		
		List<Purpose_master> allData1 =purList.stream()
				
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Purpose_master::getPurpose_id).reversed())
				  .collect(Collectors.toList());
		//System.out.println("2"+allData1.size());
		return allData1;
	}
	
	public Purpose_master findOne(long id)
	{
		Optional<Purpose_master> op=this.purpose_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Purpose_masterDTO> getPurposeList(){
		
		List<Purpose_master> purposeList=purpose_masterRepository.findAll();
		purposeList.forEach((e)->{
			e.setPurpose_name(e.getPurpose_name().toUpperCase());
		});

		List<Purpose_master> allData = purposeList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Purpose_master::getPurpose_name))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<Purpose_masterDTO>>() {}.getType();
		
		List<Purpose_masterDTO> purList= new ModelMapper().map(allData,listType);
		
		return purList;
		
	}
	
	public List<Purpose_master> findPurpose(String searchtext)
	{
		List<Purpose_master> cityList=new ArrayList<Purpose_master>();
		cityList.addAll(purpose_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Purpose_master> allData = cityList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Purpose_master::getPurpose_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Purpose_master> allData = cityList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getPurpose_name()+c.getPurpose_desc()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Purpose_master::getPurpose_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

}
