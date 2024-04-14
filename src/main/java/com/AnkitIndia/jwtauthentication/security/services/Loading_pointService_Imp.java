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
import com.AnkitIndia.generators.UniqueIDTransaction;

import com.AnkitIndia.jwtauthentication.model.Loading_point;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Loading_pointRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.Loading_pointDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service

public class Loading_pointService_Imp implements Loading_pointService{
	
	@Autowired
	Loading_pointRepository loading_pointRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Loading_point save(Loading_point loading_point)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="LP";
		if(loading_pointRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(loading_pointRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		loading_point.setLoading_id(gen_sno);
		loading_point.setModified_type("INSERTED");
		loading_point.setInserted_by(userRepository.getUserDetails(loading_point.getUsername()).getName());
		loading_point.setInserted_on(ldt);
		loading_point.setUpdated_by("NA");
		loading_point.setUpdated_on(ldt);
		loading_point.setDeleted_by("NA");
		loading_point.setDeleted_on(ldt);
		
		return loading_pointRepository.save(loading_point);
	
	}
	
	public List<Loading_point> findAll()
	{
		List<Loading_point> loadList=new ArrayList<Loading_point>();
		loadList.addAll(loading_pointRepository.findAll());
				
		List<Loading_point> allData = loadList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Loading_point::getLoading_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Loading_point findOne(Long id) {
		 Optional<Loading_point> op=this.loading_pointRepository.findById(id);
		 return op.get();
	}
	
	@Transactional
	public Loading_point update(Loading_point loading_point,long id)
	{
		Optional<Loading_point> op = loading_pointRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		loading_point.setLoading_id(op.get().getLoading_id());
		loading_point.setModified_type("UPDATED");
		loading_point.setInserted_by(op.get().getInserted_by());
		loading_point.setInserted_on(op.get().getInserted_on());
		loading_point.setUpdated_by(userRepository.getUserDetails(loading_point.getUsername()).getName());
		loading_point.setUpdated_on(ldt);
		loading_point.setDeleted_by("NA");
		loading_point.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			loading_point.setId(id);
		}
		return loading_pointRepository.save(loading_point);
	}
	
	@Transactional
	public Loading_point deleteLoading_point(Loading_point loading_point,long id)
	{
		Optional<Loading_point> op = loading_pointRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Loading_point loadingMaster=op.get();

		loadingMaster.setInserted_by(op.get().getInserted_by());
		loadingMaster.setInserted_on(op.get().getInserted_on());
		loadingMaster.setUpdated_by(op.get().getUpdated_by());
		loadingMaster.setUpdated_on(op.get().getUpdated_on());
		loadingMaster.setDeleted_by(userRepository.getUserDetails(loading_point.getUsername()).getName());
		loadingMaster.setDeleted_on(ldt);
		loadingMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			loadingMaster.setId(id);
		}
		return loading_pointRepository.save(loadingMaster);
	}
	
	public List<Loading_pointDTO> getLoadingPointThruBU(String bunit)
	{
		List<Loading_point> loadList=new ArrayList<Loading_point>();
		loadList.addAll(loading_pointRepository.findAll());
				
		List<Loading_point> allData = loadList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Loading_point::getLoading_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Loading_pointDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Loading_pointDTO> point= new ModelMapper().map(allData,listType);
		
		return point;
		
	}
	
	public List<Loading_pointDTO> getLoadingPointThruBUDiff(String bunit,String lpoint)
	{
		List<Loading_point> loadList=new ArrayList<Loading_point>();
		loadList.addAll(loading_pointRepository.findAll());
				
		List<Loading_point> allData = loadList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getBusiness_unit().equals(bunit) && c.getLoading_id()!=lpoint)
			  .sorted(Comparator.comparing(Loading_point::getLoading_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Loading_pointDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Loading_pointDTO> point= new ModelMapper().map(allData,listType);
		
		return point;
		
	}
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	public SequenceIdDTO getLoadingPointSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=loading_pointRepository.getLoadingPointSequenceId(fprefix,company);
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

	public List<Loading_point> findLoading_point(String searchtext)
	{
		List<Loading_point> loadList=new ArrayList<Loading_point>();
		loadList.addAll(loading_pointRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Loading_point> allData = loadList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Loading_point::getLoading_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Loading_point> allData = loadList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") && (c.getLoading_name()+c.getBusiness_unit()+c.getLoading_no()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(Loading_point::getLoading_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public StatusDTO checkLoadingPointUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean loading_advice=false;
		
		if(loading_pointRepository.checkLoadingPointUsage(code)) 
		{
			loading_advice=true;
		}
		if(loading_advice == true)
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
