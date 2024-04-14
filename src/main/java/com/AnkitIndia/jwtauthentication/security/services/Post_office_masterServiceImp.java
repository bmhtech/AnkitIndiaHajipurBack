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
import com.AnkitIndia.jwtauthentication.model.Post_office_master;
import com.AnkitIndia.jwtauthentication.repository.Area_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Post_office_masterDTO;

@Service
public class Post_office_masterServiceImp implements Post_office_masterService {

	@Autowired
	Post_office_masterRepository post_office_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Area_masterRepository  area_masterRepository;
	
	public Post_office_master save(Post_office_master post_office)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="PO";
		if(post_office_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(post_office_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);

		if(Utility.isNullOrEmpty(post_office.getDist_code())) {
			post_office.setDist_name(district_masterRepository.getDistrictDtls(post_office.getDist_code()).getDist_name());
		}else {post_office.setDist_name("None");}
		
		if(Utility.isNullOrEmpty(post_office.getState_code())) {
			post_office.setState_name(state_masterRepository.getState(post_office.getState_code()).getState_name());
		}else {post_office.setState_name("None");}
		
		post_office.setPostid(gen_sno);
		post_office.setModified_type("INSERTED");
		post_office.setInserted_by(userRepository.getUserDetails(post_office.getUsername()).getName());
		post_office.setInserted_on(ldt);
		post_office.setUpdated_by("NA");
		post_office.setUpdated_on(ldt);
		post_office.setDeleted_by("NA");
		post_office.setDeleted_on(ldt);
	
		return post_office_masterRepository.save(post_office);
	}
	
	public List<Post_office_master> findAll()
	{
		List<Post_office_master> postList=new ArrayList<Post_office_master>();
		postList.addAll(post_office_masterRepository.findAll());
				
		List<Post_office_master> allData = postList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Post_office_master::getPostid).reversed()).limit(50)
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Post_office_master findOne(long id)
	{
		Optional<Post_office_master> op=this.post_office_masterRepository.findById(id);
		return op.get();
	}
	
	public List<Post_office_masterDTO> getPostOfficeThruDist(String distid)
	{
		List<Post_office_master> postlist=new ArrayList<Post_office_master>();
		postlist.addAll(post_office_masterRepository.findAll());
				
		List<Post_office_master> allData = postlist
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getDist_code().equals(distid))
			  .sorted(Comparator.comparing(Post_office_master::getPost_office))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Post_office_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Post_office_masterDTO> postMasterList= new ModelMapper().map(allData,listType);
		
		return postMasterList;
	}
	
	public Post_office_masterDTO getPincodeThruPO(String po)
	{
		Post_office_masterDTO postMaster=new Post_office_masterDTO();
		Optional<Post_office_master> pData=post_office_masterRepository.getPincodeThruPO(po);
		if(pData.isPresent()) {
			Type listType = new TypeToken<Post_office_masterDTO>() {}.getType();
			postMaster= new ModelMapper().map(pData.get(),listType);
		}
		return postMaster;
	}
	
	@Transactional
	public Post_office_master update(Post_office_master post_office,long id)
	{
		Optional<Post_office_master> op = post_office_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(post_office.getDist_code())) {
			post_office.setDist_name(district_masterRepository.getDistrictDtls(post_office.getDist_code()).getDist_name());
		}else {post_office.setDist_name("None");}
		
		if(Utility.isNullOrEmpty(post_office.getState_code())) {
			post_office.setState_name(state_masterRepository.getState(post_office.getState_code()).getState_name());
		}else {post_office.setState_name("None");}
		
		post_office.setPostid(op.get().getPostid());
		post_office.setModified_type("UPDATED");
		post_office.setInserted_by(op.get().getInserted_by());
		post_office.setInserted_on(op.get().getInserted_on());
		post_office.setUpdated_by(userRepository.getUserDetails(post_office.getUsername()).getName());
		post_office.setUpdated_on(ldt);
		post_office.setDeleted_by("NA");
		post_office.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			post_office.setId(id);
		}
		return post_office_masterRepository.save(post_office);
	}
	
	@Transactional
	public Post_office_master deletePostOffice(Post_office_master post_office,long id)
	{
		Optional<Post_office_master> op = post_office_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Post_office_master pMaster=op.get();
		
		pMaster.setInserted_by(op.get().getInserted_by());
		pMaster.setInserted_on(op.get().getInserted_on());
		pMaster.setUpdated_by(op.get().getUpdated_by());
		pMaster.setUpdated_on(op.get().getUpdated_on());
		pMaster.setDeleted_by(userRepository.getUserDetails(post_office.getUsername()).getName());
		pMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			pMaster.setId(id);
		}
		
		/*if(area_masterRepository.getAreaDtlsThruCity(op.get().getCity_code()).isPresent()) {
			return post_office;
		}else {*/
			pMaster.setModified_type("DELETED");
			return post_office_masterRepository.save(pMaster);
		/*}*/
	}
	
	public List<Post_office_master> findPostOffice(String searchtext)
	{
		List<Post_office_master> post_officeList=new ArrayList<Post_office_master>();
		post_officeList.addAll(post_office_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Post_office_master> allData = post_officeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Post_office_master::getPost_office))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Post_office_master> allData = post_officeList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getPost_office()+c.getDist_name()+c.getState_name()+c.getCountry_name()+c.getPincode()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(Post_office_master::getPost_office))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public List<Post_office_masterDTO> findPOThruPincode(String pincode,String dist)
	{
		List<Post_office_master> post_officeList=post_office_masterRepository.findAll();
		post_officeList.forEach((e)->{
			e.setPost_office(e.getPost_office().toUpperCase());
		});		
		List<Post_office_master> allData = post_officeList
				  .parallelStream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getDist_code().equals(dist) && ("pin :"+c.getPincode()).contains(pincode))
				  .sorted(Comparator.comparing(Post_office_master::getPost_office)).limit(100)
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Post_office_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Post_office_masterDTO> poList= new ModelMapper().map(allData,listType);
		
		return poList;
	}
}
