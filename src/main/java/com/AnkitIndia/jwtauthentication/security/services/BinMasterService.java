package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Bin_master;

import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.BingroupRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.BinMasterDTO;

import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class BinMasterService implements BinMasterTest {
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	BingroupRepository bingroupRepository;
	
	@Transactional
	public Bin_master save(Bin_master bin) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="BM";
		if(binMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(binMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(bin.getBusinessunit_code())) {
			bin.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(bin.getBusinessunit_code()).getBusinessunit_name());
		}else {bin.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(bin.getWarehouse_code())) {
			bin.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(bin.getWarehouse_code()).getWarehouse_name());
		}else {bin.setWarehouse_name("None");}
		
		if(Utility.isNullOrEmpty(bin.getBin_type())) {
			bin.setBin_typename(bingroupRepository.binGroupName(bin.getBin_type()).getBingroupname());
		}else {bin.setBin_typename("None");}
		
		bin.setBin_id(gen_sno);
		bin.setBin_code(gen_sno);
		bin.setModified_type("INSERTED");
		bin.setInserted_by(userRepository.getUserDetails(bin.getUsername()).getName());
		bin.setInserted_on(ldt);
		bin.setUpdated_by("NA");
		bin.setUpdated_on(ldt);
		bin.setDeleted_by("NA");
		bin.setDeleted_on(ldt);
		
		return binMasterRepository.save(bin);
	}
	
	@Transactional
	public Bin_master update(Bin_master bin,long id) 
	{
		Optional<Bin_master> binMasterOptional=binMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(bin.getBusinessunit_code())) {
			bin.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(bin.getBusinessunit_code()).getBusinessunit_name());
		}else {bin.setBusinessunit_name("None");}
		
		if(Utility.isNullOrEmpty(bin.getWarehouse_code())) {
			bin.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(bin.getWarehouse_code()).getWarehouse_name());
		}else {bin.setWarehouse_name("None");}
		
		if(Utility.isNullOrEmpty(bin.getBin_type())) {
			bin.setBin_typename(bingroupRepository.binGroupName(bin.getBin_type()).getBingroupname());
		}else {bin.setBin_typename("None");}
		
		bin.setBin_id(binMasterOptional.get().getBin_id());
		bin.setModified_type("UPDATED");
		bin.setInserted_by(binMasterOptional.get().getInserted_by());
		bin.setInserted_on(binMasterOptional.get().getInserted_on());
		bin.setUpdated_by(userRepository.getUserDetails(bin.getUsername()).getName());
		bin.setUpdated_on(ldt);
		bin.setDeleted_by("NA");
		bin.setDeleted_on(ldt);
		
		if(binMasterOptional.isPresent()) 
		{
			bin.setId(id);
		}
		return binMasterRepository.save(bin);
	}
	
	public List<Bin_master> findAll()
	{
		List<Bin_master> binList=new ArrayList<Bin_master>();
		binList.addAll(binMasterRepository.findAll());
		
		List<Bin_master> allData = binList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Bin_master::getBin_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	} 
	
	public List<BinMasterDTO> getBinNameCode() {
		
		List<Bin_master> binList=new ArrayList<Bin_master>();
		binList.addAll(binMasterRepository.findAll());
				
		List<Bin_master> allData = binList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.isBin_active()==true)
			  .sorted(Comparator.comparing(Bin_master::getBin_description))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<BinMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<BinMasterDTO> bin1List= new ModelMapper().map(allData,listType);
		
		return bin1List;
	}
	
	public List<BinMasterDTO> getbinlist() {
		
		List<Bin_master> binList=new ArrayList<Bin_master>();
		binList.addAll(binMasterRepository.getbinlist());
				
		List<Bin_master> allData = binList
			  .parallelStream()
			  .filter(c -> c.isBin_active()==true)
			  .sorted(Comparator.comparing(Bin_master::getBin_description))
			  .collect(Collectors.toList());
		
	
		Type listType = new TypeToken<List<BinMasterDTO>>() {}.getType();
	
		List<BinMasterDTO> bin1List= new ModelMapper().map(allData,listType);
		
		return bin1List;
	}
	
	
	 public List<BinMasterDTO> getbinlistbygroup(String bingroupid,String finyear)
	 {
		 List<Bin_master> binList=new ArrayList<Bin_master>();
		 binList.addAll(binMasterRepository.getbinlistbygroupbyyear(bingroupid,finyear));
		 
		 List<Bin_master> allData = binList
				  .parallelStream()
				  .filter(c -> c.isBin_active()==true)
				  .sorted(Comparator.comparing(Bin_master::getBin_description))
				  .collect(Collectors.toList());
			
		
			Type listType = new TypeToken<List<BinMasterDTO>>() {}.getType();
		
			List<BinMasterDTO> bin1List= new ModelMapper().map(allData,listType);
			
			return bin1List;
	 }
	 
	 public StatusDTO checkbingroup(String bingroupid,String binid,String finyear) 
	 {
		 boolean statbin=binMasterRepository.checkexistbin(bingroupid, binid, finyear);
		 StatusDTO res=new StatusDTO();
		 if(statbin == true) 
		 {
			 res.setStatus("YES");
		 }
		 else 
		 {
			 res.setStatus("NO");
		 }
				 
		 return res;
	 }
	 
	 
	 public StatusDTO bincalculation(String binid,double dip) 
	 {
		 StatusDTO res =new StatusDTO();
		 
		 Bin_master bindetails =binMasterRepository.getBinDesc(binid);
		 
		 double height=Double.parseDouble(bindetails.getEmpty_bin_height());
		 double capacity=Double.parseDouble(bindetails.getBin_capacity_kg());
		 double output=capacity-((capacity/height)*dip);
		 
		 
		 res.setStatus(String.valueOf(output));
		 return res;
	 }
	
	public List<BinMasterDTO> getBinDescByWarehouse(String wcode) {
		
		List<Bin_master> binList=binMasterRepository.findAll();
				
		List<Bin_master> allData = binList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getWarehouse_code().equals(wcode) && c.isBin_active()==true)
			  .sorted(Comparator.comparing(Bin_master::getBin_description))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<BinMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<BinMasterDTO> binDesc= new ModelMapper().map(allData,listType);
		
		return binDesc;
	}
	
	public List<Bin_master> getBinNCList()
	{
		List<Bin_master> binList=new ArrayList<Bin_master>();
		binList.addAll(binMasterRepository.getBinNCList());
		
		List<Bin_master> allData = binList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Bin_master::getBin_description))
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Bin_master findOne(long id) 
	{
		Optional<Bin_master> bin = this.binMasterRepository.findById(id);
		return bin.get();
	} 
	
	public void delete(Bin_master bin) 
	{
		binMasterRepository.delete(bin);
	}
	
	@Transactional
	public Bin_master deleteBin(Bin_master bin,long id)
	{
		Optional<Bin_master> op = binMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Bin_master binMaster=op.get();

		binMaster.setInserted_by(op.get().getInserted_by());
		binMaster.setInserted_on(op.get().getInserted_on());
		binMaster.setUpdated_by(op.get().getUpdated_by());
		binMaster.setUpdated_on(op.get().getUpdated_on());
		binMaster.setDeleted_by(userRepository.getUserDetails(bin.getUsername()).getName());
		binMaster.setDeleted_on(ldt);
		binMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			binMaster.setId(id);
		}
		return binMasterRepository.save(binMaster);
	}
	
	public SequenceIdDTO getBinSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=binMasterRepository.getBinSequenceId(fprefix,company);
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
	
	public List<Bin_master> findBin_master(String searchtext)
	{
		List<Bin_master> binList=new ArrayList<Bin_master>();
		binList.addAll(binMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Bin_master> allData = binList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Bin_master::getBin_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Bin_master> allData = binList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getBin_code()+c.getBin_description()+c.getBin_capacity_kg()+c.getBusinessunit_name()+c.getWarehouse_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Bin_master::getBin_description))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	
}
