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
import com.AnkitIndia.jwtauthentication.model.Screen_master;
import com.AnkitIndia.jwtauthentication.repository.Screen_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Screen_masterDTO;

@Service
public class Screen_masterService_Imp implements Screen_masterService{

	@Autowired
	Screen_masterRepository screen_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Screen_master save(Screen_master screen_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		String prefix="SCM";
		Optional<String> cntid=screen_masterRepository.countId(prefix);
		if(cntid.isPresent()) {
			slno=Long.parseLong(cntid.get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		screen_master.setScreen_id(gen_sno);
		screen_master.setModified_type("INSERTED");
		screen_master.setInserted_by(userRepository.getUserDetails(screen_master.getUsername()).getName());
		screen_master.setInserted_on(ldt);
		screen_master.setUpdated_by("NA");
		screen_master.setUpdated_on(ldt);
		screen_master.setDeleted_by("NA");
		screen_master.setDeleted_on(ldt);
		
		return  screen_masterRepository.save(screen_master);
	}
	
	@Transactional
	public Screen_master update(Screen_master screen_master,long id)
	{
		Optional<Screen_master> op = screen_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		screen_master.setScreen_id(op.get().getScreen_id());
		screen_master.setModified_type("UPDATED");
		screen_master.setInserted_by(op.get().getInserted_by());
		screen_master.setInserted_on(op.get().getInserted_on());
		screen_master.setUpdated_by(userRepository.getUserDetails(screen_master.getUsername()).getName());
		screen_master.setUpdated_on(ldt);
		screen_master.setDeleted_by("NA");
		screen_master.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			screen_master.setId(id);
		}
		return screen_masterRepository.save(screen_master);
	}
	
	@Transactional
	public Screen_master deleteScreen(Screen_master screen_master,long id)
	{
		Optional<Screen_master> op = screen_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Screen_master screenMaster=op.get();

		screenMaster.setInserted_by(op.get().getInserted_by());
		screenMaster.setInserted_on(op.get().getInserted_on());
		screenMaster.setUpdated_by(op.get().getUpdated_by());
		screenMaster.setUpdated_on(op.get().getUpdated_on());
		screenMaster.setDeleted_by(userRepository.getUserDetails(screen_master.getUsername()).getName());
		screenMaster.setDeleted_on(ldt);
		screenMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			screenMaster.setId(id);
		}
		
		
		return screen_masterRepository.save(screenMaster);
	}
	
	public List<Screen_master> findAll()
	{
		List<Screen_master> screenList=new ArrayList<>();
		screenList.addAll(screen_masterRepository.findAll());
		
		List<Screen_master> allData =screenList.stream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .sorted(Comparator.comparing(Screen_master::getScreen_id).reversed())
				  .collect(Collectors.toList());
		return allData;
		
	}
	
	public Screen_master findOne(long id)
	{
		Optional<Screen_master> op=this.screen_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Screen_masterDTO> getScreenMNCList(){
		
		List<Screen_master> screenList=new ArrayList<Screen_master>();
		screenList.addAll(screen_masterRepository.findAll());
				
		List<Screen_master> allData = screenList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Screen_master::getScreen_name))
			  .collect(Collectors.toList());
	
		Type listType = new TypeToken<List<Screen_masterDTO>>() {}.getType();
		
		List<Screen_masterDTO> screenNameList= new ModelMapper().map(allData,listType);
		
		return screenNameList;
	}
	
	public List<Screen_master> findScreen(String searchtext)
	{
		List<Screen_master> screenList=new ArrayList<Screen_master>();
		screenList.addAll(screen_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Screen_master> allData = screenList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Screen_master::getScreen_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Screen_master> allData = screenList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getScreen_name()+c.getScreen_type()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Screen_master::getScreen_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
